package com.ruoyi.business.study.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 门禁开门（/mp/guard/open）
 *
 * @author Amadeus
 */
@Data
public class StudyRoomGuardOpenReqDTO {

    /**
     * 门禁 ID（/mp/guard/select 返回的 id）（必填）
     */
    @NotNull(message = "门禁ID不能为空")
    private Long guardId;

}
