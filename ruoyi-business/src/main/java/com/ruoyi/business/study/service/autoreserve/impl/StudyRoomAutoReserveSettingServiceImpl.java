package com.ruoyi.business.study.service.autoreserve.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import com.ruoyi.business.study.domain.entity.StudyRoomAutoReserveSettingPO;
import com.ruoyi.business.study.mapper.StudyRoomAutoReserveSettingMapper;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveSettingService;
import com.ruoyi.common.json.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.Objects;

/**
 * 自动预约用户设置服务实现
 *
 * @author Amadeus
 */
@Service
@RequiredArgsConstructor
public class StudyRoomAutoReserveSettingServiceImpl implements StudyRoomAutoReserveSettingService {

    private final StudyRoomAutoReserveSettingMapper settingMapper;

    @Override
    public StudyRoomAutoReserveUserConfigBO getConfig(Long userId) {
        LambdaQueryWrapper<StudyRoomAutoReserveSettingPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveSettingPO::getUserId, userId);
        StudyRoomAutoReserveSettingPO entity = settingMapper.selectOne(lqw);
        if (Objects.isNull(entity)) {
            return this.defaultConfig();
        }
        StudyRoomAutoReserveUserConfigBO bo = JsonUtils.parseObject(entity.getConfigJson(), StudyRoomAutoReserveUserConfigBO.class);
        if (Objects.isNull(bo)) {
            return this.defaultConfig();
        }
        return bo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Long userId, StudyRoomAutoReserveUserConfigBO config) {
        LambdaQueryWrapper<StudyRoomAutoReserveSettingPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomAutoReserveSettingPO::getUserId, userId);
        StudyRoomAutoReserveSettingPO exist = settingMapper.selectOne(lqw);
        if (Objects.nonNull(exist)) {
            exist.setConfigJson(JsonUtils.toJsonString(config));
            settingMapper.updateById(exist);
        } else {
            StudyRoomAutoReserveSettingPO entity = new StudyRoomAutoReserveSettingPO();
            entity.setUserId(userId);
            entity.setConfigJson(JsonUtils.toJsonString(config));
            settingMapper.insert(entity);
        }
    }

    private StudyRoomAutoReserveUserConfigBO defaultConfig() {
        StudyRoomAutoReserveUserConfigBO studyRoomAutoReserveUserConfigBO = new StudyRoomAutoReserveUserConfigBO();
        studyRoomAutoReserveUserConfigBO.setReleaseTime(LocalTime.of(20, 0, 0));
        return studyRoomAutoReserveUserConfigBO;
    }
}
