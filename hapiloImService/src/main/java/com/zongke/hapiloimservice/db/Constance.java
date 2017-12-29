package com.zongke.hapiloimservice.db;

import android.net.Uri;

/**
 * Created by llj on 2017/10/10.
 */

public class Constance {
/*...................................................................         共享账户的配置             .........................................................................*/

    /**
     * METHOD_GET_ITEM_COUNT和KEY_ITEM_COUNT两个常量是调用ContentProvider接口的一个未公开函数call来查询数据时用的，
     * 它类似于微软COM中的IDispatch接口的Invoke函数，使用这个call函数时，
     * 传入参数METHOD_GET_ITEM_COUNT表示我们要调用我们自定义的ContentProvider子类中的getItemCount函数来获取数据库中的文章信息条目的数量，
     * 结果放在一个Bundle中以KEY_ITEM_COUNT为关键字的域中
     */
    public static final String METHOD_GET_ITEM_COUNT = "METHOD_GET_ITEM_COUNT";
    public static final String KEY_ITEM_COUNT = "KEY_ITEM_COUNT";

    /*Authority*/
    public static final String AUTHORITY = "com.zhongke.account";

    /*Match Code*/
    // 只有表名的uri匹配code
    public static final int ACCOUNT = 1;
    // 表名后面跟着id的匹配code
    public static final int ACCOUNT_ID = 2;

    /*URI*/
    public static final Uri ACCOUNT_URI = Uri.parse("content://" + AUTHORITY + "/" + AccountColumns.TABLE_NAME);

    /**
     * 账户信息通知配置
     */
    public static final String ACCOUNT_MESSAGE_ACTION="com.zhongke.account.notify.AccountMessageReceiver";
    public static final String ACTION_KEY="action_key";
    public static final int INSET=2;
    public static final int UPDATE=3;
    public static final int DELETE=4;


    public static final String key_activity_id="activityId";
    public static final String key_start_time="startTime";
    public static final String prefix_message_answer="Answer_";

}
