package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.JSONBody;
import com.dtflys.forest.annotation.Post;
import com.dtflys.forest.annotation.Query;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomGuardListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomGuardOpenReqDTO;

/**
 * 门禁域：门禁区域列表、开门
 *
 * @author Amadeus
 */
public interface StudyRoomGuardClient extends StudyRoomBaseClient {

    /**
     * 门店门禁区域列表
     *
     * @param venueId 门店 ID
     */
    @Get(url = "/mp/guard/select")
    StudyRoomApiResultDTO<StudyRoomGuardListRespDTO> guardSelect(@Query(value = "venueId") Integer venueId);

    /**
     * 门禁开门（服务端校验/记录；物理开锁需客户端 BLE）
     */
    @Post(url = "/mp/guard/open")
    StudyRoomApiResultDTO<Void> guardOpen(@JSONBody StudyRoomGuardOpenReqDTO form);

}
