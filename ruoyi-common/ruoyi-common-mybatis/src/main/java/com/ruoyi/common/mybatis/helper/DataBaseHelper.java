package com.ruoyi.common.mybatis.helper;

import cn.hutool.core.convert.Convert;
import com.ruoyi.common.core.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 数据库助手
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataBaseHelper {

    public static String findInSet(Object var1, String var2) {
        String var = Convert.toStr(var1);
        // find_in_set(100 , '0,100,101')
        return StringUtils.format("find_in_set('{}' , {}) <> 0", var, var2);
    }
}
