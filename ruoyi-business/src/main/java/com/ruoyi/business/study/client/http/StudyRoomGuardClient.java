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
 * <p>
 * 小程序里的「点击开门」是两段式：
 * <ol>
 *     <li>{@code /mp/guard/select} 返回门禁列表，元素里带 {@code configJsonTwo}（TTLock ekey）；</li>
 *     <li>点击后先调 {@code /mp/guard/open} 做服务端校验/记录，成功后客户端再用 ekey 走 BLE 开锁。</li>
 * </ol>
 * 本客户端只封装服务端请求；物理开锁动作仍依赖手机蓝牙，无法仅靠 Java 服务端完成。
 * <p>
 * 公共请求配置（baseURL / appid / token 拦截器 / 超时）见 {@link StudyRoomBaseClient}。
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
    StudyRoomApiResultDTO<StudyRoomGuardListRespDTO> guardSelect(@Query("venueId") Integer venueId);

    /**
     * 门禁开门（服务端校验/记录；物理开锁需客户端 BLE）
     */
    @Post(url = "/mp/guard/open")
    StudyRoomApiResultDTO<Void> guardOpen(@JSONBody StudyRoomGuardOpenReqDTO form);

}
