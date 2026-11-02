package com.ruoyi.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.common.sensitive.core.SensitiveService;
import org.springframework.stereotype.Service;

/**
 * 脱敏 服务层实现
 * 默认管理员不过滤
 * 需自行根据业务重写实现
 */
@Service
public class SysSensitiveServiceImpl implements SensitiveService {

    /**
     * 是否脱敏
     */
    @Override
    public boolean isSensitive(String roleKey, String perms) {
        if (!LoginHelper.isLogin()) {
            return true;
        }
        boolean roleExist = StringUtils.isNotBlank(roleKey);
        boolean permsExist = StringUtils.isNotBlank(perms);
        if (roleExist && !StpUtil.hasRole(roleKey)) {
            return !LoginHelper.isAdmin();
        }
        if (permsExist && !StpUtil.hasPermission(perms)) {
            return !LoginHelper.isAdmin();
        }
        if (roleExist || permsExist) {
            return false;
        }
        return !LoginHelper.isAdmin();
    }

}
