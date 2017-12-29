package com.zongke.hapiloimservice.extra.message;

/**
 * Created by ${xingen} on 2017/12/8.
 *
 * 愿望通知
 *
 */

public class WishBindActivityNotice {

    /**
     * 愿望的Id
     */
    public String  wishId;
    /**
     * 许愿人员的Id
     */
    public String wishUserId;
    /**
     * 许愿人员的名字Evaluation
     */
    public String wishUserName;

    /**
     * 绑定活动的Id
     */
    public String  bindActivityId;
    /**
     * 活动开始时间
     */
    public int activityStartTime;
    /**
     * 推举活动人员的Id
     */
    public String recommendUserId;
    /**
     * 推荐活动人员的名字
     */
    public String recommendUserName;
    /**
     *  活动的名字
     */
    public String activityName;

    public int code;
    /**
     * 孩子许了一个愿望
     */
    public static final int code_start_wishi=10;
    /**
     * 家长将愿望绑定活动
     */
    public static final int code_wish_bind_activity=11;
    /**
     * 孩子接受活动
     */
    public static final int code_accept_activity=12;
    /**
     * 绑定的活动已经结束
     */
    public static final int code_activity_finish=13;
    /**
     * 家长评价活动，奖励孩子
     */
    public static final int code_activity_evaluation=14;

    public static final int code_activity_start=15;
}
