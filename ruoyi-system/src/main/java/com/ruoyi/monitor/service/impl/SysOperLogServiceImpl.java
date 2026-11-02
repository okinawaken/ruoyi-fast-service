package com.ruoyi.monitor.service.impl;

import cn.hutool.core.util.ArrayUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.ip.AddressUtils;
import com.ruoyi.common.log.event.OperLogEvent;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.monitor.domain.bo.SysOperLogDTO;
import com.ruoyi.monitor.domain.entity.SysOperLogPO;
import com.ruoyi.monitor.mapper.SysOperLogMapper;
import com.ruoyi.monitor.service.SysOperLogService;
import lombok.RequiredArgsConstructor;
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
 * 操作日志 服务层实现
 */
@RequiredArgsConstructor
@Service
public class SysOperLogServiceImpl implements SysOperLogService {

    private final SysOperLogMapper operLogMapper;

    @Override
    @Async
    @EventListener
    @Transactional(rollbackFor = Exception.class)
    public void recordOper(OperLogEvent operLogEvent) {
        SysOperLogDTO operLog = MapstructUtils.convert(operLogEvent, SysOperLogDTO.class);
        // 远程查询操作地点
        operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
        this.insertOperLog(operLog);
    }

    @Override
    public IPage<SysOperLogDTO> selectPageOperLogList(SysOperLogDTO operLog, PageQuery pageQuery) {
        Map<String, Object> params = operLog.getParams();
        LambdaQueryWrapper<SysOperLogPO> lqw = new LambdaQueryWrapper<SysOperLogPO>()
            .like(StringUtils.isNotBlank(operLog.getOperIp()), SysOperLogPO::getOperIp, operLog.getOperIp())
            .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLogPO::getTitle, operLog.getTitle())
            .eq(Objects.nonNull(operLog.getBusinessType()) && operLog.getBusinessType() > 0,
                SysOperLogPO::getBusinessType, operLog.getBusinessType())
            .func(f -> {
                if (ArrayUtil.isNotEmpty(operLog.getBusinessTypes())) {
                    f.in(SysOperLogPO::getBusinessType, Arrays.asList(operLog.getBusinessTypes()));
                }
            })
            .eq(Objects.nonNull(operLog.getStatus()),
                SysOperLogPO::getStatus, operLog.getStatus())
            .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLogPO::getOperName, operLog.getOperName())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysOperLogPO::getOperTime, params.get("beginTime"), params.get("endTime"));
        if (StringUtils.isBlank(pageQuery.getOrderByColumn())) {
            pageQuery.setOrderByColumn("id");
            pageQuery.setIsAsc("desc");
        }
        return operLogMapper.selectVoPage(pageQuery.build(), lqw, SysOperLogDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOperLog(SysOperLogDTO bo) {
        SysOperLogPO operLog = MapstructUtils.convert(bo, SysOperLogPO.class);
        operLog.setOperTime(LocalDateTime.now());
        operLogMapper.insert(operLog);
    }

    @Override
    public List<SysOperLogDTO> selectOperLogList(SysOperLogDTO operLog) {
        Map<String, Object> params = operLog.getParams();
        return operLogMapper.selectVoList(new LambdaQueryWrapper<SysOperLogPO>()
            .like(StringUtils.isNotBlank(operLog.getOperIp()), SysOperLogPO::getOperIp, operLog.getOperIp())
            .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLogPO::getTitle, operLog.getTitle())
            .eq(Objects.nonNull(operLog.getBusinessType()) && operLog.getBusinessType() > 0,
                SysOperLogPO::getBusinessType, operLog.getBusinessType())
            .func(f -> {
                if (ArrayUtil.isNotEmpty(operLog.getBusinessTypes())) {
                    f.in(SysOperLogPO::getBusinessType, Arrays.asList(operLog.getBusinessTypes()));
                }
            })
            .eq(Objects.nonNull(operLog.getStatus()) && operLog.getStatus() > 0,
                SysOperLogPO::getStatus, operLog.getStatus())
            .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLogPO::getOperName, operLog.getOperName())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysOperLogPO::getOperTime, params.get("beginTime"), params.get("endTime"))
            .orderByDesc(SysOperLogPO::getId), SysOperLogDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOperLogByIds(Long[] operIds) {
        return operLogMapper.deleteBatchIds(Arrays.asList(operIds));
    }

    @Override
    public SysOperLogDTO selectOperLogById(Long operId) {
        return operLogMapper.selectVoById(operId, SysOperLogDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cleanOperLog() {
        operLogMapper.delete(new LambdaQueryWrapper<>());
    }
}
