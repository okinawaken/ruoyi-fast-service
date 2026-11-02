package com.ruoyi.business.study.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 自动签到执行事件
 *
 * @author Amadeus
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyRoomAutoSignExecuteEvent implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 系统用户 ID
     */
    private Long userId;
}
