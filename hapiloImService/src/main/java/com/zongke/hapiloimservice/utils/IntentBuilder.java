package com.zongke.hapiloimservice.utils;

import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by ${xingen} on 2017/11/24.
 */

public class IntentBuilder {
    public static final String ACTION_ACCEPT_MESSAGE = "com.zongke.hapiloimservice.service.MessageHandlerService.AcceptMessageBroadcastReceiver";
    public static final String ACTION_SEND_MESSAGE = "com.zongke.hapiloimservice.service.MessageHandlerService.SendMessageBroadcastReceiver";
    public static final String ACTION_JOIN_GROUP= "com.zongke.hapiloimservice.service.MessageHandlerService.JoinGroupMessageBroadcastReceiver ";
    public static final String KEY_MESSAGE_ID = "messageId";
    public static final String KEY_GROUP_ID="groupId";
    public static final String KEY_NET_RESULT="result";
    public static final String ACCOUNT_MESSAGE_ACTION="com.zhongke.account.notify.AccountMessageReceiver";
    public static final String ACTION_KEY="action_key";
    public static final int INSET=2;
    public static final int UPDATE=3;
    public static final int DELETE=4;
    public static IntentFilter builderIntentFilter(String action){
       return new IntentFilter(action);
    }
    public static Intent builderIntent(String action,String messageId){
        Intent intent=  new Intent(action);
        intent.putExtra(KEY_MESSAGE_ID,messageId);
        return intent;
    }
    public static Intent builderIntentNet(boolean netResult){
        Intent intent=  new Intent(ACTION_SEND_MESSAGE);
        intent.putExtra(KEY_NET_RESULT,netResult);
        return intent;
    }

    public static Intent builderIntentEngine(String content){
        Intent intent=  new Intent("com.zongke.hapiloimservice.service.MessageHandlerService.ResponseEngineReceiver");
        intent.putExtra("engineMessage",content);
        return  intent;
    }
    public static Intent builderGroupIntent(String action, String groupId) {
        Intent intent = new Intent(action);
        intent.putExtra("groupId", groupId);
        return intent;
    }

}
