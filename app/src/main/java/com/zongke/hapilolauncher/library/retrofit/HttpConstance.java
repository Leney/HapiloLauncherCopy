package com.zongke.hapilolauncher.library.retrofit;

/**
 * Created by Karma on 2017/6/8.
 * 类描述：接口地址配置
 */

public class HttpConstance {
    /**
     * 基础的网络路径(测试地址)
     */
    public static final String BASE_URL = "http://api.wedoou.com:8889";
//    /**
//     * 基础的网络路径(真实发布地址)
//     */
//    public static final String BASE_URL = "http://api.wedoou.com:8086";
    /**
     * 当前应用号，服务器统一分配
     * 【hapilo为1，微舵为2】
     */
    public static final String APP_ID = "1";
    /**
     * 请求成功code
     */
    public static final int RESULT_CODE_SUCCESS = 1;
    /**
     * 请求失败
     */
    public static final int RESULT_CODE_ERROR = 0;
    /**
     * token过期
     */
    public static final int RESULT_CODE_EXPIRED = 2;


}
