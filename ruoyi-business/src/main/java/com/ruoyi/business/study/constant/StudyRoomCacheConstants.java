package com.ruoyi.business.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 缓存的key 常量
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudyRoomCacheConstants {

    /**
     * 自动预约执行锁前缀，完整 key：前缀 + taskId
     */
    public static final String AUTO_RESERVE_EXECUTE_LOCK = "study:auto_reserve:execute:";

}
