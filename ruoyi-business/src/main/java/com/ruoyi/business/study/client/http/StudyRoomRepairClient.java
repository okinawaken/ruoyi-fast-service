package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Var;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomRepairSubmitReqDTO;

/**
 * 报修域：提交报修、报修记录
 *
 * @author Amadeus
 */
public interface StudyRoomRepairClient extends StudyRoomBaseClient {

    /**
     * 提交报修（实测成功时 {@code data} 为 {@code null}）
     */
    @Post(url = "/mp/userRepair/submit")
    StudyRoomApiResultDTO<Void> submitRepair(@JSONBody StudyRoomRepairSubmitReqDTO form);

    /**
     * 报修列表
     */
    @Get(url = "/mp/userRepair/list")
    StudyRoomApiResultDTO<StudyRoomRepairListRespDTO> repairList();

    /**
     * 报修详情
     */
    @Get(url = "/mp/userRepair/detail/${id}")
    StudyRoomApiResultDTO<StudyRoomRepairRespDTO> repairDetail(@Var(value = "id") Long id);

}
