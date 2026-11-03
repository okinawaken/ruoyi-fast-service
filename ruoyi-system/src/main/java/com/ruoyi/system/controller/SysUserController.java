package com.ruoyi.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.system.domain.dto.SysUserDTO;
import com.ruoyi.system.domain.vo.SysUserInfoVO;
import com.ruoyi.system.domain.vo.SysUserVO;
import com.ruoyi.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 用户信息
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/user")
public class SysUserController extends BaseController {

    private final SysUserService userService;

    /**
     * 获取用户列表
     */
    @SaCheckPermission(value = "system:user:list")
    @GetMapping(value = "/list")
    public TableDataInfo<SysUserVO> list(SysUserDTO user, PageQuery pageQuery) {
        IPage<SysUserDTO> sysUserDTOIPage = userService.selectPageUserList(user, pageQuery);
        IPage<SysUserVO> voPage = sysUserDTOIPage.convert(dto -> MapstructUtils.convert(dto, SysUserVO.class));
        return TableDataInfo.build(voPage);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @SaCheckPermission(value = "system:user:query")
    @GetMapping(value = {"/", "/{userId}"})
    public R<SysUserInfoVO> getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        SysUserDTO sysUserDTO = new SysUserDTO();
        if (Objects.nonNull(userId)) {
            sysUserDTO = userService.selectUserById(userId);
        }
        SysUserVO sysUserVO = MapstructUtils.convert(sysUserDTO, SysUserVO.class);
        SysUserInfoVO vo = new SysUserInfoVO(sysUserVO);
        return R.ok(vo);
    }

    /**
     * 新增用户
     */
    @SaCheckPermission(value = "system:user:add")
    @PostMapping
    public R<Void> add(@Validated @RequestBody SysUserDTO user) {
        boolean checkUserNameUnique = userService.checkUserNameUnique(user);
        if (!checkUserNameUnique) {
            return R.fail("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        int result = userService.insertUser(user);
        return this.toAjax(result);
    }

    /**
     * 修改用户
     */
    @SaCheckPermission(value = "system:user:edit")
    @PutMapping
    public R<Void> edit(@Validated @RequestBody SysUserDTO user) {
        if (Objects.isNull(user.getId())) {
            return R.fail("用户ID不能为空");
        }
        userService.checkUserAllowed(user.getId());
        boolean checkUserNameUnique = userService.checkUserNameUnique(user);
        if (!checkUserNameUnique) {
            return R.fail("修改用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        int result = userService.updateUser(user);
        return this.toAjax(result);
    }

    /**
     * 删除用户
     */
    @SaCheckPermission(value = "system:user:remove")
    @DeleteMapping(value = "/{userIds}")
    public R<Void> remove(@PathVariable List<Long> userIds) {
        if (userIds.contains(LoginHelper.getUserId())) {
            return R.fail("当前用户不能删除");
        }
        int result = userService.deleteUserByIds(userIds);
        return this.toAjax(result);
    }

    /**
     * 重置密码
     */
    @SaCheckPermission(value = "system:user:resetPwd")
    @PutMapping(value = "/resetPwd")
    public R<Void> resetPwd(@RequestBody SysUserDTO user) {
        userService.checkUserAllowed(user.getId());
        int result = userService.resetUserPwd(user.getId(), user.getPassword());
        return this.toAjax(result);
    }

    /**
     * 状态修改
     */
    @SaCheckPermission(value = "system:user:edit")
    @PutMapping(value = "/changeStatus")
    public R<Void> changeStatus(@RequestBody SysUserDTO user) {
        userService.checkUserAllowed(user.getId());
        int result = userService.updateUserStatus(user.getId(), user.getStatus());
        return this.toAjax(result);
    }

}
