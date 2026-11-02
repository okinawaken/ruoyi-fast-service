package com.ruoyi.business.study.client.http;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.ruoyi.business.study.domain.dto.StudyRoomApiResultDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomConfigRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomNoticeRespDTO;

/**
 * 配置域：全局配置、公告
 *
 * @author Amadeus
 */
public interface StudyRoomConfigClient extends StudyRoomBaseClient {

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     */
    @Get(url = "/mp/sys/config/data")
    StudyRoomApiResultDTO<StudyRoomConfigRespDTO> sysConfig();

    /**
     * 公告栏
     * <p>
     * 小程序传 {@code venueId=0} 表示不区分门店
     */
    @Get(url = "/mp/notice/board/info")
    StudyRoomApiResultDTO<StudyRoomNoticeRespDTO> noticeBoard(@Query(value = "venueId") Integer venueId);

}
