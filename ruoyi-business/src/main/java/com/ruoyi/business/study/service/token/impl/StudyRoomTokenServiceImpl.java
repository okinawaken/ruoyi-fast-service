package com.ruoyi.business.study.service.token.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.business.study.client.http.StudyRoomUserClient;
import com.ruoyi.business.study.domain.entity.StudyRoomUserTokenPO;
import com.ruoyi.business.study.mapper.StudyRoomUserTokenMapper;
import com.ruoyi.business.study.service.token.StudyRoomTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 系统用户与小程序 token 映射服务实现
 *
 * @author Amadeus
 */
@Service
@RequiredArgsConstructor
public class StudyRoomTokenServiceImpl implements StudyRoomTokenService {

    private final StudyRoomUserTokenMapper tokenMapper;
    private final StudyRoomUserClient studyRoomUserClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Long userId, String token) {
        LambdaQueryWrapper<StudyRoomUserTokenPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenPO::getUserId, userId);
        StudyRoomUserTokenPO existing = tokenMapper.selectOne(lqw);
        if (Objects.nonNull(existing)) {
            existing.setToken(token);
            tokenMapper.updateById(existing);
        } else {
            StudyRoomUserTokenPO entity = new StudyRoomUserTokenPO();
            entity.setUserId(userId);
            entity.setToken(token);
            tokenMapper.insert(entity);
        }

        this.validateToken();
    }

    @Override
    public String get(Long userId) {
        LambdaQueryWrapper<StudyRoomUserTokenPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenPO::getUserId, userId);
        StudyRoomUserTokenPO entity = tokenMapper.selectOne(lqw);
        return Objects.nonNull(entity) ? entity.getToken() : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long userId) {
        LambdaQueryWrapper<StudyRoomUserTokenPO> lqw = Wrappers.lambdaQuery();
        lqw.eq(StudyRoomUserTokenPO::getUserId, userId);
        tokenMapper.delete(lqw);
    }

    private void validateToken() {
        studyRoomUserClient.userDetail();
    }
}
