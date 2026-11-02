package com.ruoyi.business.study.service.autosign.impl;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.business.domain.dto.FilterResultDTO;
import com.ruoyi.business.study.client.http.StudyRoomSignV2Client;
import com.ruoyi.business.study.constant.StudyRoomCacheConstants;
import com.ruoyi.business.study.domain.context.StudyRoomAutoSignContext;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomSignSeatQueryReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserPackageRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserReserveRespDTO;
import com.ruoyi.business.study.filter.autosign.StudyRoomAutoSignFilterChain;
import com.ruoyi.business.study.service.autosign.StudyRoomAutoSignExecutorService;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.json.utils.JsonUtils;
import com.ruoyi.common.redis.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 自动签到执行器实现
 *
 * @author Amadeus
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudyRoomAutoSignExecutorServiceImpl implements StudyRoomAutoSignExecutorService {

    private final StudyRoomSignV2Client signV2Client;
    private final StudyRoomAutoSignFilterChain filterChain;

    @Override
    public void execute(Long userId) {
        String lockKey = StudyRoomCacheConstants.AUTO_SIGN_EXECUTE_LOCK + userId;
        RLock lock = RedisUtils.getClient().getLock(lockKey);
        if (!lock.tryLock()) {
            log.error("自动签到正在执行中，加锁失败，忽略，userId={}", userId);
            return;
        }

        StudyRoomAutoSignContext context = new StudyRoomAutoSignContext(userId);

        try {
            FilterResultDTO<?> filterResultDTO = filterChain.doFilter(context);
            if (!filterResultDTO.isSuccess()) {
                return;
            }

            StudyRoomUserReserveRespDTO reserveRespDTO = context.getReserveRespDTO();
            StudyRoomUserPackageRespDTO userPackageRespDTO = context.getUserPackageRespDTO();

            StudyRoomSignSeatQueryReqDTO reqDTO = new StudyRoomSignSeatQueryReqDTO();
            reqDTO.setQrCode(StrUtil.EMPTY);
            reqDTO.setVenueId(reserveRespDTO.getVenueId());
            reqDTO.setSignInUserPackageId(userPackageRespDTO.getSignInUserPackageId());
            reqDTO.setSignAnotherFlag(0);
            StudyRoomApiResultDTO<StudyRoomSignSeatListRespDTO> resultDTO = signV2Client.studySeatList(reqDTO, userId);
            if (Objects.isNull(resultDTO) || Objects.isNull(resultDTO.getData()) || !resultDTO.getData().isSignInSuccess()) {
                throw new ServiceException("签到失败");
            }
        } catch (Exception e) {
            log.error("自动签到执行失败，userId={}，context={}", userId, JsonUtils.toJsonString(context), e);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
