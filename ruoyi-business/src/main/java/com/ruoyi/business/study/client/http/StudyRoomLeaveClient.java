package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;

import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveCancelReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveEnterRespDTO;

/**
 * 请假域：套餐请假
 *
 * @author Amadeus
 */
public interface StudyRoomLeaveClient extends StudyRoomBaseClient {

    /**
     * 进入请假页（校验可请假套餐）
     */
    @Get(url = "/mp/user/userLeave/enterLeave")
    StudyRoomApiResultDTO<StudyRoomUserLeaveEnterRespDTO> userLeaveEnter(@Query(value = "userPackageId") Long userPackageId);

    /**
     * 确认请假
     */
    @Post(url = "/mp/user/userLeave/confirmLeave")
    StudyRoomApiResultDTO<Integer> userLeaveConfirm(@JSONBody StudyRoomUserLeaveConfirmReqDTO form);

    /**
     * 取消请假
     */
    @Post(url = "/mp/user/userLeave/cancelLeave")
    StudyRoomApiResultDTO<Integer> userLeaveCancel(@JSONBody StudyRoomUserLeaveCancelReqDTO form);

}
