package com.ruoyi.business.study.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 自习室小程序（时不我待自习馆）接口常量
 *
 * @author Amadeus
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StudyRoomApiConstants {

    /**
     * 网关基础信息
     */
    public static final class Gateway {

        /**
         * 小程序网关根地址（不含 /mp 前缀）
         */
        public static final String BASE_URL = "https://mini.winsalesaas.com/study-saas";

        /**
         * 小程序 appid，随每个请求放入 appid 请求头（实测非必填，保持与小程序一致）
         */
        public static final String APPID = "wx97ec90d8480be46e";
    }

    /**
     * 鉴权请求头
     */
    public static final class Header {

        /**
         * 小程序用户 token（JWT）
         */
        public static final String HEADER_TOKEN = "token";

        /**
         * 小程序 appid
         */
        public static final String HEADER_APPID = "appid";
    }

    /**
     * 请求变量名
     */
    public static final class Variable {

        /**
         * 系统用户 ID
         * <p>
         * 由调用方通过 {@code @Var} 传入，用于在无登录上下文（如定时任务线程）中指定 token 归属用户；
         * 未传时取当前登录用户
         */
        public static final String USER_ID = "userId";
    }

    /**
     * API业务响应码
     */
    public static final class ApiCode {

        /**
         * 业务成功码（注意：是字符串 "200"，不是数字）
         */
        public static final String SUCCESS_CODE = "200";

        /**
         * 未登录 / token 失效（实测无 token 时返回 {"code":"401","content":"用户未登录"}）
         */
        public static final String CODE_UNAUTHORIZED = "401";

        /**
         * 未登录 / token 失效（小程序内的业务码）
         */
        public static final String CODE_NOT_LOGIN = "SV0001";

        /**
         * 账号异常（黑名单用户）
         */
        public static final String CODE_BLACK_USER = "SV99999";
    }
}
