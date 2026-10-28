package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomGuardRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * StudyRoomGuardRespDTO 视图对象
 * <p>
 * 包含 {@code configJsonTwo}（TTLock ekey），供管理后台在浏览器蓝牙开锁时使用。
 * 该字段属于门锁钥匙，前端只能在内存中短暂持有，禁止持久化或输出到日志。
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomGuardRespDTO.class)
public class StudyRoomGuardVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 门禁 ID
     */
    private Long id;

    /**
     * 区域别名
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
     * TTLock ekey / lockData，浏览器蓝牙开锁使用
     * <p>
     * 敏感数据：只在管理后台登录态下下发，前端用完即弃，禁止持久化或写日志。
     */
    private String configJsonTwo;

}
