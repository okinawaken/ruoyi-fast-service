package com.ruoyi.common.redis.manager;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.utils.RedisUtils;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonCache;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.transaction.TransactionAwareCacheDecorator;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A {@link org.springframework.cache.CacheManager} implementation
 * backed by Redisson instance.
 * <p>
 * 修改 RedissonSpringCacheManager 源码
 * 重写 cacheName 处理方法 支持多参数
 *
 */
@SuppressWarnings("unchecked")
public class PlusSpringCacheManager implements CacheManager {

    private boolean dynamic = true;

    private boolean allowNullValues = true;

    private boolean transactionAware = true;

    Map<String, CacheConfig> configMap = new ConcurrentHashMap<>();
    ConcurrentMap<String, Cache> instanceMap = new ConcurrentHashMap<>();

    /**
     * Creates CacheManager supplied by Redisson instance
     */
    public PlusSpringCacheManager() {
    }


    /**
     * Defines possibility of storing {@code null} values.
     * <p>
     * Default is <code>true</code>
     *
     * @param allowNullValues stores if <code>true</code>
     */
    public void setAllowNullValues(boolean allowNullValues) {
        this.allowNullValues = allowNullValues;
    }

    /**
     * Defines if cache aware of Spring-managed transactions.
     * If {@code true} put/evict operations are executed only for successful transaction in after-commit phase.
     * <p>
     * Default is <code>false</code>
     *
     * @param transactionAware cache is transaction aware if <code>true</code>
     */
    public void setTransactionAware(boolean transactionAware) {
        this.transactionAware = transactionAware;
    }

    /**
     * Defines 'fixed' cache names.
     * A new cache instance will not be created in dynamic for non-defined names.
     * <p>
     * `null` parameter setups dynamic mode
     *
     * @param names of caches
     */
    public void setCacheNames(Collection<String> names) {
        if (Objects.nonNull(names)) {
            for (String name : names) {
                getCache(name);
            }
            dynamic = false;
        } else {
            dynamic = true;
        }
    }

    /**
     * Set cache config mapped by cache name
     *
     * @param config object
     */
    public void setConfig(Map<String, ? extends CacheConfig> config) {
        this.configMap = (Map<String, CacheConfig>) config;
    }

    protected CacheConfig createDefaultConfig() {
        return new CacheConfig();
    }

    @Override
    public Cache getCache(String name) {
        // 重写 cacheName 支持多参数
        String[] array = StringUtils.split(name, "#");
        name = array[0];

        Cache cache = instanceMap.get(name);
        if (Objects.nonNull(cache)) {
            return cache;
        }
        if (!dynamic) {
            return cache;
        }

        CacheConfig config = configMap.get(name);
        if (Objects.isNull(config)) {
            config = createDefaultConfig();
            configMap.put(name, config);
        }

        if (array.length > 1) {
            config.setTTL(DurationStyle.detectAndParse(array[1]).toMillis());
        }
        if (array.length > 2) {
            config.setMaxIdleTime(DurationStyle.detectAndParse(array[2]).toMillis());
        }
        if (array.length > 3) {
            config.setMaxSize(Integer.parseInt(array[3]));
        }

        if (config.getMaxIdleTime() == 0 && config.getTTL() == 0 && config.getMaxSize() == 0) {
            return this.createMap(name, config);
        }

        return this.createMapCache(name, config);
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(configMap.keySet());
    }

    private Cache createMap(String name, CacheConfig config) {
        RMap<Object, Object> map = RedisUtils.getClient().getMap(name);

        Cache cache = new CaffeineCacheDecorator(new RedissonCache(map, allowNullValues));
        if (transactionAware) {
            cache = new TransactionAwareCacheDecorator(cache);
        }
        Cache oldCache = instanceMap.putIfAbsent(name, cache);
        if (Objects.nonNull(oldCache)) {
            cache = oldCache;
        }
        return cache;
    }

    private Cache createMapCache(String name, CacheConfig config) {
        RMapCache<Object, Object> map = RedisUtils.getClient().getMapCache(name);

        Cache cache = new CaffeineCacheDecorator(new RedissonCache(map, config, allowNullValues));
        if (transactionAware) {
            cache = new TransactionAwareCacheDecorator(cache);
        }
        Cache oldCache = instanceMap.putIfAbsent(name, cache);
        if (Objects.nonNull(oldCache)) {
            cache = oldCache;
        } else {
            map.setMaxSize(config.getMaxSize());
        }
        return cache;
    }

}
