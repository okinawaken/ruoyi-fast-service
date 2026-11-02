package com.ruoyi.business.study.controller;

import com.ruoyi.business.study.client.http.StudyRoomConfigClient;
import com.ruoyi.business.study.domain.dto.StudyRoomConfigRespDTO;
import com.ruoyi.business.study.domain.dto.StudyRoomNoticeRespDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomConfigVO;
import com.ruoyi.business.study.domain.vo.StudyRoomNoticeVO;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.web.core.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自习室配置域接口
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/business/study/config")
public class StudyRoomConfigController extends BaseController {

    private final StudyRoomConfigClient configClient;

    /**
     * 小程序全局配置（含可提前预约天数、保留时长等）
     */
    @GetMapping(value = "/sys")
    public R<StudyRoomConfigVO> sysConfig() {
        StudyRoomConfigRespDTO dto = configClient.sysConfig().getData();
        StudyRoomConfigVO vo = MapstructUtils.convert(dto, StudyRoomConfigVO.class);
        return R.ok(vo);
    }

    /**
     * 公告栏
     */
    @GetMapping(value = "/notice")
    public R<StudyRoomNoticeVO> noticeBoard(@RequestParam(value = "venueId") Integer venueId) {
        StudyRoomNoticeRespDTO dto = configClient.noticeBoard(venueId).getData();
        StudyRoomNoticeVO vo = MapstructUtils.convert(dto, StudyRoomNoticeVO.class);
        return R.ok(vo);
    }
}
