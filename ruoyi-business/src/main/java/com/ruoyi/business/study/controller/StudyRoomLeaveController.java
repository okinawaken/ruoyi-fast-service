package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomLeaveClient;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveCancelReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveConfirmReqDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomUserLeaveEnterRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomUserLeaveEnterVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室请假域接口
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/leave")
public class StudyRoomLeaveController extends BaseController {

    private final StudyRoomLeaveClient leaveClient;

    /**
     * 进入请假页（校验可请假套餐）
     *
     * @param userPackageId 用户套餐 ID
     */
    @GetMapping("/enter")
    public R<StudyRoomUserLeaveEnterVO> userLeaveEnter(@RequestParam("userPackageId") Long userPackageId) {
        StudyRoomUserLeaveEnterRespDTO dto = leaveClient.userLeaveEnter(userPackageId).getData();
        StudyRoomUserLeaveEnterVO vo = MapstructUtils.convert(dto, StudyRoomUserLeaveEnterVO.class);
        return R.ok(vo);
    }

    /**
     * 确认请假
     */
    @PostMapping("/confirm")
    public R<Integer> userLeaveConfirm(@RequestBody StudyRoomUserLeaveConfirmReqDTO form) {
        Integer data = leaveClient.userLeaveConfirm(form).getData();
        return R.ok(data);
    }

    /**
     * 取消请假
     */
    @PostMapping("/cancel")
    public R<Integer> userLeaveCancel(@RequestBody StudyRoomUserLeaveCancelReqDTO form) {
        Integer data = leaveClient.userLeaveCancel(form).getData();
        return R.ok(data);
    }
}
