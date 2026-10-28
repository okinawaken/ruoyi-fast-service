package com.ruoyi.business.study.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 门禁区域（/mp/guard/select 返回的 guardList 元素）
 *
 * @author Amadeus
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudyRoomGuardRespDTO {

    /**
     * 门禁 ID（开门时作为 guardId 传给 /mp/guard/open）
     */
    private Long id;

    /**
     * 区域别名（按钮展示“开锁 xxx”）
     */
    private String areaAliasName;

    /**
     * 是否可开门：1 可开，2 不可开
     */
    private Integer canOpen;

    /**
     * 不可开门时的提示文案
     */
    private String canNotOpenDesc;

    /**
     * TTLock ekey / lockData，客户端蓝牙开锁使用
     * <p>
     * 属于门锁钥匙，敏感数据；管理后台仅在浏览器蓝牙开锁时按需使用，禁止持久化或输出到日志。
     */
    private String configJsonTwo;

}
