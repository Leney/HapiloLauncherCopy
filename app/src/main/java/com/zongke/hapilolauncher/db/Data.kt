package com.zongke.hapilolauncher.db

import android.provider.BaseColumns

/**
 * Created by ${xingen} on 2017/7/7.
 * 创建BaseColumn的实现类，存放数据库需要用到的常量配置
 */

class Data : BaseColumns {
    companion object {
        /**
         * 数据库信息
         */
        val SQLITE_NAME = "HapiloLaucncher.db"
        /**
         * 数据库版本
         */
        val SQLITE_VERSON = 1

        /**
         * 收藏应用程序的表
         */
        @JvmField
        val TABLE_NAME_COLLECTION_APP = "collection_app"
        /**
         * 程序包名
         */
        @JvmField
        val COLUMN_NAME_APP_PACAKAGE = "appPackage"

        val CREATE_TABLE_COLLECTION_APP =
                "create table " +
                        Data.TABLE_NAME_COLLECTION_APP + "(" +
                        BaseColumns._ID + " integer primary key autoincrement," +
                        ContentProviderConfig.COLUMN_NAME_APP_PACAKAGE + " text ,"+
                        ContentProviderConfig.COLUMN_NAME_APP_POSTION+" integer , "+
                        ContentProviderConfig.COLUMN_NAME_APP_VISIBLE+" integer"+ ")"
    }

}
