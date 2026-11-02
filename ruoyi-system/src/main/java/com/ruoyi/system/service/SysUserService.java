package com.ruoyi.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.service.UserService;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.system.domain.dto.SysUserDTO;

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
    IPage<SysUserDTO> selectPageUserList(SysUserDTO user, PageQuery pageQuery);

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    SysUserDTO selectUserById(Long userId);

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    boolean checkUserNameUnique(SysUserDTO user);

    /**
     * 校验用户是否允许操作
     *
     * @param userId 用户ID
     */
    void checkUserAllowed(Long userId);

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int insertUser(SysUserDTO user);

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUserDTO user);

    /**
     * 修改用户状态
     *
     * @param userId 用户ID
     * @param status 帐号状态
     * @return 结果
     */
    int updateUserStatus(Long userId, Integer status);

    /**
     * 重置用户密码
     *
     * @param userId   用户ID
     * @param password 密码
     * @return 结果
     */
    int resetUserPwd(Long userId, String password);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    int deleteUserByIds(List<Long> userIds);

}
