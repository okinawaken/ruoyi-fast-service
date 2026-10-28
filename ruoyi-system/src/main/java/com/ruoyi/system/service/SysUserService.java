package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.UserService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.bo.SysUserBO;

import java.util.List;

/**
 * 用户 服务层
 */
public interface SysUserService extends UserService {

    /**
     * 分页查询用户列表
     *
     * @param user      用户信息
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    IPage<SysUserBO> selectPageUserList(SysUserBO user, PageQuery pageQuery);

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    List<SysUserBO> selectUserList(SysUserBO user);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    SysUserBO selectUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUserBO selectUserById(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkUserNameUnique(SysUserBO user);

    /**
     * 校验手机号码是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkPhoneUnique(SysUserBO user);

    /**
     * 校验用户是否允许操作
     *
     * @param userId 用户ID
     */
    void checkUserAllowed(Long userId);

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    void checkUserDataScope(Long userId);

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUserBO user);

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUserBO user);

    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 帐号状态
     * @return 结果
     */
    int updateUserStatus(Long userId, String status);

    /**
     * 重置用户密码
     *
     * @param userId   用户ID
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(Long userId, String password);

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    int deleteUserById(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByIds(Long[] userIds);

}
