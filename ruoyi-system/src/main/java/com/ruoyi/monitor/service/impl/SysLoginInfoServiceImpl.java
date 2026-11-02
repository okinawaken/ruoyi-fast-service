package com.ruoyi.monitor.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.constant.Constants;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.ServletUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.AddressUtils;
import com.ruoyi.common.log.event.LoginInfoEvent;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.monitor.domain.dto.SysLoginInfoDTO;
import com.ruoyi.monitor.domain.entity.SysLoginInfoPO;
import com.ruoyi.monitor.mapper.SysLoginInfoMapper;
import com.ruoyi.monitor.service.SysLoginInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 系统访问日志情况信息 服务层实现
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class SysLoginInfoServiceImpl implements SysLoginInfoService {

    private final SysLoginInfoMapper loginInfoMapper;

    @Override
    @Async
    @EventListener
    @Transactional(rollbackFor = Exception.class)
    public void recordLoginInfo(LoginInfoEvent loginInfoEvent) {
        HttpServletRequest request = loginInfoEvent.getRequest();
        final UserAgent userAgent = UserAgentUtil.parse(request.getHeader("User-Agent"));
        final String ip = ServletUtils.getClientIP(request);

        String address = AddressUtils.getRealAddressByIP(ip);
        StringBuilder s = new StringBuilder();
        s.append(this.getBlock(ip));
        s.append(address);
        s.append(this.getBlock(loginInfoEvent.getUsername()));
        s.append(this.getBlock(loginInfoEvent.getStatus()));
        s.append(this.getBlock(loginInfoEvent.getMessage()));
        // 打印信息到日志
        log.info(s.toString(), loginInfoEvent.getArgs());
        // 获取客户端操作系统
        String os = userAgent.getOs().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        // 封装对象
        SysLoginInfoDTO loginInfo = new SysLoginInfoDTO();
        loginInfo.setUserName(loginInfoEvent.getUsername());
        loginInfo.setIpaddr(ip);
        loginInfo.setLoginLocation(address);
        loginInfo.setBrowser(browser);
        loginInfo.setOs(os);
        loginInfo.setMsg(loginInfoEvent.getMessage());
        // 日志状态
        if (StringUtils.equalsAny(loginInfoEvent.getStatus(), Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER)) {
            loginInfo.setStatus(Constants.SUCCESS);
        } else if (Objects.equals(Constants.LOGIN_FAIL, loginInfoEvent.getStatus())) {
            loginInfo.setStatus(Constants.FAIL);
        }
        // 插入数据
        this.insertLoginInfo(loginInfo);
    }

    private String getBlock(Object msg) {
        if (Objects.isNull(msg)) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }

    @Override
    public IPage<SysLoginInfoDTO> selectPageLoginInfoList(SysLoginInfoDTO loginInfo, PageQuery pageQuery) {
        Map<String, Object> params = loginInfo.getParams();
        LambdaQueryWrapper<SysLoginInfoPO> lqw = new LambdaQueryWrapper<SysLoginInfoPO>()
            .like(StringUtils.isNotBlank(loginInfo.getIpaddr()), SysLoginInfoPO::getIpaddr, loginInfo.getIpaddr())
            .eq(Objects.nonNull(loginInfo.getStatus()), SysLoginInfoPO::getStatus, loginInfo.getStatus())
            .like(StringUtils.isNotBlank(loginInfo.getUserName()), SysLoginInfoPO::getUserName, loginInfo.getUserName())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysLoginInfoPO::getLoginTime, params.get("beginTime"), params.get("endTime"));
        if (StringUtils.isBlank(pageQuery.getOrderByColumn())) {
            pageQuery.setOrderByColumn("id");
            pageQuery.setIsAsc("desc");
        }
        return loginInfoMapper.selectVoPage(pageQuery.build(), lqw, SysLoginInfoDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLoginInfo(SysLoginInfoDTO bo) {
        SysLoginInfoPO loginInfo = MapstructUtils.convert(bo, SysLoginInfoPO.class);
        loginInfo.setLoginTime(LocalDateTime.now());
        loginInfoMapper.insert(loginInfo);
    }

    @Override
    public List<SysLoginInfoDTO> selectLoginInfoList(SysLoginInfoDTO loginInfo) {
        Map<String, Object> params = loginInfo.getParams();
        return loginInfoMapper.selectVoList(new LambdaQueryWrapper<SysLoginInfoPO>()
            .like(StringUtils.isNotBlank(loginInfo.getIpaddr()), SysLoginInfoPO::getIpaddr, loginInfo.getIpaddr())
            .eq(Objects.nonNull(loginInfo.getStatus()), SysLoginInfoPO::getStatus, loginInfo.getStatus())
            .like(StringUtils.isNotBlank(loginInfo.getUserName()), SysLoginInfoPO::getUserName, loginInfo.getUserName())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysLoginInfoPO::getLoginTime, params.get("beginTime"), params.get("endTime"))
            .orderByDesc(SysLoginInfoPO::getId), SysLoginInfoDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteLoginInfoByIds(Long[] infoIds) {
        return loginInfoMapper.deleteBatchIds(Arrays.asList(infoIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cleanLoginInfo() {
        loginInfoMapper.delete(new LambdaQueryWrapper<>());
    }
}
