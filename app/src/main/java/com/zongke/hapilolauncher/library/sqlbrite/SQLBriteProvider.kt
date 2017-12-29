package com.zongke.hapilolauncher.library.sqlbrite

import android.content.ContentResolver
import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.squareup.sqlbrite.BriteContentResolver

import com.squareup.sqlbrite.BriteDatabase
import com.squareup.sqlbrite.SqlBrite
import com.zongke.hapilolauncher.db.DBHelper

import rx.schedulers.Schedulers

/**
 * Created by ${xingen} on 2017/7/7.
 * SQLBrite的操作类
 */

class SQLBriteProvider private constructor(context: Context) {

    private val sqlBrite: SqlBrite
    /**
     * 获取到SQLBrite中数据库对象
     * @return
     */
    val briteDatabase: BriteDatabase

    val briteContentResolver: BriteContentResolver

    init {
        this.sqlBrite = providerSQLBrite()
        this.briteDatabase = createDatabase(this.sqlBrite, providerOpenHelper(context))
        this.briteContentResolver=createContentResolver( context.contentResolver)
    }

    /**
     * 创建SQLiteOpenHelper对象
     * @param context
     * *
     * @return
     */
    private fun providerOpenHelper(context: Context): SQLiteOpenHelper {
        return DBHelper(context)
    }

    /**

     * 创建SqlBrite对象

     * @return
     */
    private fun providerSQLBrite(): SqlBrite {
        return SqlBrite.Builder().build()
    }

    /**
     * 通过SQLBrite对象和SQLiteOpenHel对象
     * @param sqlBrite
     * *
     * @param sqLiteOpenHelper
     * *
     * @return
     */
    fun createDatabase(sqlBrite: SqlBrite, sqLiteOpenHelper: SQLiteOpenHelper): BriteDatabase {
        val db = sqlBrite.wrapDatabaseHelper(sqLiteOpenHelper, Schedulers.io())
        return db
    }

    fun createContentResolver(contentResolver: ContentResolver): BriteContentResolver {
        return sqlBrite.wrapContentProvider(contentResolver, Schedulers.io())
    }

    companion object {
        private var instance: SQLBriteProvider? = null
        @JvmStatic
        @Synchronized fun getInstance(context: Context): SQLBriteProvider {
            if (instance == null) {
                instance = SQLBriteProvider(context)
            }
            return instance!!
        }
    }
}