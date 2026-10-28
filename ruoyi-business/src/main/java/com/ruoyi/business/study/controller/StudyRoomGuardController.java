package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomGuardClient;
import com.ruoyi.business.study.domain.dto.StudyRoomGuardListRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomGuardOpenReqDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomGuardListVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室门禁域接口：门禁区域列表、开门
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/guard")
public class StudyRoomGuardController extends BaseController {

    private final StudyRoomGuardClient guardClient;

    /**
     * 门店门禁区域列表
     * <p>
     * 不返回上游的 {@code configJsonTwo}（TTLock ekey），避免门锁钥匙透出到管理后台。
     */
    @GetMapping("/select")
    public R<StudyRoomGuardListVO> guardSelect(@RequestParam("venueId") Integer venueId) {
        StudyRoomGuardListRespDTO dto = guardClient.guardSelect(venueId).getData();
        StudyRoomGuardListVO vo = MapstructUtils.convert(dto, StudyRoomGuardListVO.class);
        return R.ok(vo);
    }

    /**
     * 门禁开门（服务端校验/记录）
     * <p>
     * 该接口只完成小程序里的 {@code /mp/guard/open} 这一步；真正的锁动作由客户端使用
     * {@code /mp/guard/select} 下发的 ekey 通过 TTLock 蓝牙完成，Java 服务端无法直接触发。
     */
    @PostMapping("/open")
    public R<Void> guardOpen(@Valid @RequestBody StudyRoomGuardOpenReqDTO form) {
        guardClient.guardOpen(form);
        return R.ok();
    }

}
