package com.ruoyi.monitor.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.monitor.domain.dto.SysOperLogDTO;
import com.ruoyi.monitor.domain.entity.SysOperLogPO;
import com.ruoyi.monitor.mapper.SysOperLogMapper;
import com.ruoyi.monitor.service.SysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void insertOperLog(SysOperLogDTO dto) {
        SysOperLogPO operLog = MapstructUtils.convert(dto, SysOperLogPO.class);
        operLogMapper.insert(operLog);
    }

    @Override
    public IPage<SysOperLogDTO> selectPageOperLogList(SysOperLogDTO operLog, PageQuery pageQuery) {
        LambdaQueryWrapper<SysOperLogPO> lqw = this.buildQueryWrapper(operLog);
        lqw.orderByDesc(SysOperLogPO::getId);
        IPage<SysOperLogPO> poPage = operLogMapper.selectPage(pageQuery.build(), lqw);
        return poPage.convert(po -> MapstructUtils.convert(po, SysOperLogDTO.class));
    }

    @Override
    public List<SysOperLogDTO> selectOperLogList(SysOperLogDTO operLog) {
        LambdaQueryWrapper<SysOperLogPO> lqw = this.buildQueryWrapper(operLog);
        lqw.orderByDesc(SysOperLogPO::getId);
        List<SysOperLogPO> poList = operLogMapper.selectList(lqw);
        return MapstructUtils.convert(poList, SysOperLogDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteOperLogByIds(List<Long> operIds) {
        return operLogMapper.deleteByIds(operIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cleanOperLog() {
        LambdaQueryWrapper<SysOperLogPO> lqw = Wrappers.lambdaQuery();
        operLogMapper.delete(lqw);
    }

    private LambdaQueryWrapper<SysOperLogPO> buildQueryWrapper(SysOperLogDTO operLog) {
        Map<String, Object> params = operLog.getParams();
        LambdaQueryWrapper<SysOperLogPO> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(operLog.getOperIp()), SysOperLogPO::getOperIp, operLog.getOperIp())
            .like(StringUtils.isNotBlank(operLog.getTitle()), SysOperLogPO::getTitle, operLog.getTitle())
            .eq(Objects.nonNull(operLog.getBusinessType()),
                SysOperLogPO::getBusinessType, operLog.getBusinessType())
            .eq(Objects.nonNull(operLog.getStatus()),
                SysOperLogPO::getStatus, operLog.getStatus())
            .like(StringUtils.isNotBlank(operLog.getOperName()), SysOperLogPO::getOperName, operLog.getOperName())
            .between(Objects.nonNull(params.get("beginTime")) && Objects.nonNull(params.get("endTime")),
                SysOperLogPO::getCreateTime, params.get("beginTime"), params.get("endTime"));
        if (CollUtil.isNotEmpty(operLog.getBusinessTypes())) {
            lqw.in(SysOperLogPO::getBusinessType, operLog.getBusinessTypes());
        }
        return lqw;
    }
}
