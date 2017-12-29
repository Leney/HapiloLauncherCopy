package com.zongke.hapiloimservice.db;

import android.provider.BaseColumns;

/**
 * 表字段名称
 * Created by llj on 2017/10/10.
 */

public class AccountColumns implements BaseColumns {
    /**
     * 表名称
     */
    public static final String TABLE_NAME = "account_table";
    /**
     * 用户id(Hapilo设备id)
     */
    public static final String USER_ID = "user_id";

    /**
     * 用户昵称
     */
    public static final String NICK_NAME = "nick_name";
    /**
     * 生日
     */
    public static final String BIRTH = "birth";
    /**
     * 用户头像地址
     */
    public static final String ICON = "icon";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * 性别
     */
    public static final String SEX = "sex";
    /**
     * 年龄
     */
    public static final String AGE = "age";

    /**
     * 标签
     */
    public static final String LABS = "labs";

    /**
     * majorList
     */
    public static final String MAJOR_LIST ="majorList";
    /**
     * 学校名称
     */
    public static final String SCHOOL_NAME = "school_name";
    /**
     * 手机号码
     */
    public static final String PHONECODE="phoneCode";

    /**
     * 注册码
     */
    public static final String REGISTERCODE="registerCode";
    /**
     * Info
     */
    public static final String INFO="info";
    /**
     * Token
     */
    public static final String TOKEN="token";

    public static final String DEVICE_CODE="deviceCode";

    /**
     * 创建账户表的sql语句
     */
    public static final String CREATE_ACCOUNT_TABLE = "create table if not exists "
            + AccountColumns.TABLE_NAME
            + " ("
            + AccountColumns._ID
            + " integer primary key autoincrement, "
            + AccountColumns.USER_ID
            + " text,"
            + AccountColumns.PHONECODE
            +" text,"
            + AccountColumns.REGISTERCODE
            +" text,"
            + AccountColumns.INFO
            +" text,"
            + AccountColumns.TOKEN
            +" text,"
            + AccountColumns.MAJOR_LIST
            +" text,"
            + AccountColumns.DEVICE_CODE
            +" text,"
            + AccountColumns.NAME
            + " text, "
            + AccountColumns.NICK_NAME
            + " text, "
            + AccountColumns.ICON
            + " text, "
            + AccountColumns.SEX
            + " integer, "
            + AccountColumns.AGE
            + " integer, "
            + AccountColumns.BIRTH
            + " text,"
            + AccountColumns.SCHOOL_NAME
            + " text,"
            + AccountColumns.LABS
            + " text);";

    /**
     * 删除账户表的sql语句
     */
    public static final String DELETE_ACCOUNT_TABLE = "drop table " + AccountColumns.TABLE_NAME;
}
