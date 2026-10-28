package com.ruoyi.business.study.controller.autoreserve;

import com.ruoyi.business.study.domain.bo.StudyRoomAutoReserveUserConfigBO;
import com.ruoyi.business.study.domain.dto.StudyRoomAutoReserveSettingReqDTO;
import com.ruoyi.business.study.domain.vo.StudyRoomAutoReserveSettingVO;
import com.ruoyi.business.study.service.autoreserve.StudyRoomAutoReserveSettingService;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.MapstructUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.common.web.core.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动预约设置：放号时刻
 *
 * @author Amadeus
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/business/study/auto-reserve/setting")
public class StudyRoomAutoReserveSettingController extends BaseController {

    private final StudyRoomAutoReserveSettingService settingService;

    /**
     * 读取放号时刻，未设置时返回默认值
     */
    @GetMapping("/")
    public R<StudyRoomAutoReserveSettingVO> getSetting() {
        StudyRoomAutoReserveUserConfigBO config = settingService.getConfig(LoginHelper.getUserId());
        StudyRoomAutoReserveSettingVO vo = MapstructUtils.convert(config, StudyRoomAutoReserveSettingVO.class);
        return R.ok(vo);
    }

    /**
     * 覆盖写放号时刻
     */
    @PutMapping("/")
    public R<Void> updateSetting(@Valid @RequestBody StudyRoomAutoReserveSettingReqDTO dto) {
        StudyRoomAutoReserveUserConfigBO config = MapstructUtils.convert(dto, StudyRoomAutoReserveUserConfigBO.class);
        settingService.updateConfig(LoginHelper.getUserId(), config);
        return R.ok();
    }
}
