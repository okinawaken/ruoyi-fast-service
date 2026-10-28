package com.ruoyi.common.mybatis.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.satoken.utils.LoginHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * MP注入处理器
 *
 * @date 2021/4/25
 */
@Slf4j
public class InjectionMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        String loginUsername = this.getLoginUsername();

        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
            if (StringUtils.isEmpty(baseEntity.getCreateBy())) {
                baseEntity.setCreateBy(loginUsername);
            }
            if (StringUtils.isEmpty(baseEntity.getUpdateBy())) {
                baseEntity.setUpdateBy(loginUsername);
            }
            if (Objects.isNull(baseEntity.getCreateTime())) {
                baseEntity.setCreateTime(nowTime);
            }
            if (Objects.isNull(baseEntity.getUpdateTime())) {
                baseEntity.setUpdateTime(nowTime);
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime nowTime = LocalDateTime.now();
        String loginUsername = this.getLoginUsername();
        if (Objects.nonNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
            if (StringUtils.isEmpty(baseEntity.getUpdateBy())) {
                baseEntity.setUpdateBy(loginUsername);
            }
            if (Objects.isNull(baseEntity.getUpdateTime())) {
                baseEntity.setUpdateTime(nowTime);
            }
        }
    }

    /**
     * 获取登录用户名
     */
    private String getLoginUsername() {
        if (LoginHelper.isLogin()) {
            String username = LoginHelper.getUsername();
            if (StringUtils.isEmpty(username)) {
                return StrUtil.EMPTY;
            }
            return username;
        }
        return StrUtil.EMPTY;
    }

}
