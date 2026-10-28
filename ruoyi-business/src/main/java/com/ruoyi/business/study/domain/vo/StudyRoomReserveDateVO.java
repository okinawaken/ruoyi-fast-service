package com.ruoyi.business.study.domain.vo;

import com.ruoyi.business.study.domain.dto.StudyRoomReserveDateRespDTO;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StudyRoomReserveDateRespDTO 视图对象
 *
 * @author Amadeus
 */
@Data
@AutoMapper(target = StudyRoomReserveDateRespDTO.class)
public class StudyRoomReserveDateVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * 可预约日期，格式 yyyy-MM-dd (周X)
     */
    private List<String> reserveDateList;
}
