package com.ruoyi.business.study.service.autoreserve;

/**
 * 自动预约执行器
 *
 * @author Amadeus
 */
public interface StudyRoomAutoReserveExecutorService {

    /**
     * 提交一次执行，方法立即返回，实际执行在异步线程中进行
     */
    void execute(Long taskId, Integer triggerType);
}
