package com.zongke.hapilolauncher.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by ${xinGen} on 2017/7/7.
 * 数据库
 */

class DBHelper(context: Context) : SQLiteOpenHelper(context, Data.SQLITE_NAME, null, Data.SQLITE_VERSON) {


    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(Data.CREATE_TABLE_COLLECTION_APP)
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {

    }
}
