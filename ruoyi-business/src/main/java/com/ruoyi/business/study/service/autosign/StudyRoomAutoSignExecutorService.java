package com.ruoyi.business.study.service.autosign;

/**
 * 自动签到执行器
 *
 * @author Amadeus
 */
public interface StudyRoomAutoSignExecutorService {

    /**
     * 为指定用户执行一次自动签到
     */
    void execute(Long userId);
}
