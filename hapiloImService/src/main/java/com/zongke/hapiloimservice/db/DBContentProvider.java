package com.zongke.hapiloimservice.db;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * 共享数据库数据的ContentProvider类
 * Created by llj on 2017/10/10.
 */

public class DBContentProvider extends ContentProvider {

    private DBHelper dbHelper;

    private ContentResolver contentResolver;

    // 常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // 注册所需要匹配的uri路径
        uriMatcher.addURI(Constance.AUTHORITY, AccountColumns.TABLE_NAME, Constance.ACCOUNT);
    }

    @Override
    public boolean onCreate() {
        contentResolver = getContext().getContentResolver();
        dbHelper = new DBHelper(getContext());
        return true;
    }

    /**
     * 查询
     *
     * @param uri
     * @param projection    需要查找的字段名数组
     * @param selection     查询条件 如：name=?
     * @param selectionArgs 查询条件的具体参数值数组，对应的是条件的"?"
     * @param sortOrder     排序
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (uriMatcher.match(uri) == Constance.ACCOUNT) {
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            cursor = database.query(AccountColumns.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            cursor.setNotificationUri(contentResolver, uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Uri u = null;
        if (uriMatcher.match(uri) == Constance.ACCOUNT) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            long d = database.insert(AccountColumns.TABLE_NAME, AccountColumns._ID, values);
            // 为传入的uri路径后面加上id
            u = ContentUris.withAppendedId(uri, d);
            contentResolver.notifyChange(u, null);
            notifyAccountChange(MessageConfig.INSET);
        }
        return u;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = 0;
        if (uriMatcher.match(uri) == Constance.ACCOUNT) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            id = database.delete(AccountColumns.TABLE_NAME, selection, selectionArgs);
            contentResolver.notifyChange(uri, null);
            notifyAccountChange(MessageConfig.DELETE);

        }
        return id;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String whereClause, @Nullable String[] whereArgs) {
        int id = 0;
        if (uriMatcher.match(uri) == Constance.ACCOUNT) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            id = database.update(AccountColumns.TABLE_NAME, values, whereClause, whereArgs);
            contentResolver.notifyChange(uri, null);
            notifyAccountChange(MessageConfig.UPDATE);
        }
        return id;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        if (uriMatcher.match(uri) == Constance.ACCOUNT) {
            SQLiteDatabase database = dbHelper.getWritableDatabase();
            int length = values.length;
            try {
                database.beginTransaction();
                for (int i = 0; i < length; i++) {
                    database.insert(AccountColumns.TABLE_NAME, null, values[i]);
                }
                database.setTransactionSuccessful();
            } catch (Exception e) {
                length = 0;
                e.printStackTrace();
            } finally {
                database.endTransaction();
            }
            contentResolver.notifyChange(uri, null);
            notifyAccountChange(MessageConfig.INSET);
            return length;
        }
        return super.bulkInsert(uri, values);
    }

    /**
     * 广播通知
     */
    public void notifyAccountChange(int  actionContent){
        Intent intent=new Intent(MessageConfig.ACCOUNT_MESSAGE_ACTION);
        intent.putExtra(MessageConfig.ACTION_KEY,actionContent);
        getContext().sendBroadcast(intent);
    }

    /**
     * 消息通知相关配置
     */
    private final static class MessageConfig{
        public static final String ACCOUNT_MESSAGE_ACTION="com.zhongke.account.notify.AccountMessageReceiver";
        public static final String ACTION_KEY="action_key";
        public static final int INSET=2;
        public static final int UPDATE=3;
        public static final int DELETE=4;
    }
}
