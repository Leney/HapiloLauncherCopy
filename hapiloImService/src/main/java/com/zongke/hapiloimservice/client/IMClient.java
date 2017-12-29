package com.zongke.hapiloimservice.client;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.zhongke.local.message.control.MessageManager;
import com.zhongke.local.message.model.ZkLocalMessage;
import com.zk.NetFace;
import com.zk.ZkDeviceType;
import com.zongke.hapiloimservice.utils.ConversionObjectUtils;
import com.zongke.hapiloimservice.utils.IntentBuilder;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static android.R.attr.id;

/**
 * Created by ${xinGen} on 2017/11/24.
 */

public class IMClient {
    private final String TAG = IMClient.class.getSimpleName();
    private static IMClient client;
    private Context appContext;
    private ContentResolver mResolver;
    private String userId;
    private String token;

    private IMClient() {

    }

    public static synchronized IMClient getInstance() {
        if (client == null) {
            client = new IMClient();
        }
        return client;
    }

    /**
     * 初始化IMClient配置
     */
    public void initConfig(Context context) {
        appContext = context.getApplicationContext();
        mResolver = context.getContentResolver();
        queryAccount();
    }


    private void queryAccount() {
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                Uri uri = Uri.parse("content://com.zhongke.account/account_table");
                try {
                    Cursor cursor = mResolver.query(uri, null, null, null, null);
                    cursor.moveToFirst();
                    userId = cursor.getString(cursor.getColumnIndex("user_id"));
                    token = cursor.getString(cursor.getColumnIndex("token"));
                    Log.i("llj", "查询出的id------>>>" + id);
                    Log.i("llj", "查询出的token------>>>" + token);
                    cursor.close();
                    NetFace.getDefault().getImClientConfig().devMac = BuildConfig.DEVICE_MAC;
                    NetFace.getDefault().getImClientConfig().devType = BuildConfig.DEVICE_TYPE;
                    NetFace.getDefault().getImClientConfig().token = token;
                    NetFace.getDefault().getImClientConfig().userId = userId;
                    NetFace.getDefault().getImClientConfig().mHost = BuildConfig.HOST;
                    NetFace.getDefault().getImClientConfig().mPort = BuildConfig.PORT;
                    //配置后，在初始化
                    NetFace.getDefault().Init(appContext);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribe();
    }


    /**
     * 账户信息改变
     */
    public void accountChange() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Uri uri = Uri.parse("content://com.zhongke.account/account_table");
                try {
                    Cursor cursor = mResolver.query(uri, null, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        String ids = cursor.getString(cursor.getColumnIndex("user_id"));
                        token = cursor.getString(cursor.getColumnIndex("token"));
                        Log.i("llj", "查询出的id------>>>" + id);
                        Log.i("llj", "查询出的token------>>>" + token);
                        cursor.close();
                        if ((!TextUtils.isEmpty(userId)) && (!userId.equals(ids))) {
                            userId = ids;
                            subscriber.onNext(true);
                        } else {
                            subscriber.onNext(false);
                        }
                    } else {
                        token = null;
                        userId = null;
                        subscriber.onNext(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean b) {
                        if (b) {
                            NetFace.getDefault().unInit(appContext);
                            NetFace.getDefault().getImClientConfig().userId = userId;
                            NetFace.getDefault().getImClientConfig().token = token;
                            NetFace.getDefault().Init(appContext);
                        } else {
                            if (TextUtils.isEmpty(token)) {
                                appContext.sendBroadcast(new Intent("com.zongke.hapiloimservice.service.MessageHandlerService.AcceptStopReceiver"));
                            }
                        }
                    }
                });
    }

    /**
     * 开始连接
     */
    public void startConnect() {
        try {
            NetFace.getDefault().connect(BuildConfig.PORT, BuildConfig.HOST);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "开始连接通道,发生异常！！！");
        }
    }

    /**
     * 注销整个即时通讯连接
     */
    public void destroyConnect() {
        NetFace.getDefault().disConnect();
        NetFace.getDefault().unInit(appContext);
    }

    /**
     * 重新连接服务器
     */
    public void reConnect() {
        try {
            NetFace.getDefault().reConnect();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "重新连接服务器出现错误");
        }
    }

    /**
     * 加入群号
     *
     * @param chatGroupId
     */
    public void joinGroup(String chatGroupId) {
        try {
            NetFace.getDefault().sendJoinGroup(chatGroupId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "加入群通讯错误");
        }
    }

    /**
     * 离开群号
     *
     * @param chatGroupId
     */
    public void leaveJoinGroup(String chatGroupId) {
        try {
            NetFace.getDefault().leaveJoinGroup(chatGroupId);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "加入群通讯错误");
        }
    }

    /**
     * 主动和服务器断开连接
     */
    public void disConnect() {
        NetFace.getDefault().disConnect();
        Log.i(TAG, "主动和服务器断开连接");
    }

    /**
     * 处理接收到的信息
     *
     * @param zkLocalMessage
     * @param messageManager
     */
    public void handlerReceiverMessage(final ZkLocalMessage zkLocalMessage, MessageManager messageManager) {
        messageManager.insertMessage(zkLocalMessage, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                notifyAnotherClient(zkLocalMessage.getMsgId());
            }

            @Override
            public void onError(Throwable e) {
                notifyAnotherClient(zkLocalMessage.getMsgId());
            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }

    /**
     * 处理接收到额外的信息
     *
     * @param zkLocalMessage
     * @param messageManager
     */
    public void handlerReceiverExtraMessage(final ZkLocalMessage zkLocalMessage, final MessageManager messageManager) {
        insertMessage(zkLocalMessage,messageManager);
    }

    private void insertMessage(final ZkLocalMessage zkLocalMessage, MessageManager messageManager) {
        messageManager.insertMessage(zkLocalMessage, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                notifyAnotherClient(zkLocalMessage.getMsgId());
            }

            @Override
            public void onError(Throwable e) {
                notifyAnotherClient(zkLocalMessage.getMsgId());
            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }

    /**
     * 处理发送额外信息的结果，成功或者失败
     */
    public void handlerExtraSendMessage(final String messageId, int state, MessageManager messageManager) {
        messageManager.updateMessageState(messageId, state, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                notifyAnotherClient(messageId);
            }

            @Override
            public void onError(Throwable e) {
                notifyAnotherClient(messageId);
            }

            @Override
            public void onNext(Boolean aBoolean) {
            }
        });
    }

    public void notifyAnotherClient(String messageId) {
        appContext.sendBroadcast(IntentBuilder.builderIntent(IntentBuilder.ACTION_SEND_MESSAGE, messageId));
    }

    public void notifyAnotherClientNetChange(boolean result) {
        appContext.sendBroadcast(IntentBuilder.builderIntentNet(result));
    }

    /**
     * 处理发送信息的结果，成功或者失败
     */
    public void handlerSendMessage(final String messageId, int state, MessageManager messageManager) {
        messageManager.updateMessageState(messageId, state, new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {
                notifyAnotherClient(messageId);
            }

            @Override
            public void onError(Throwable e) {
                notifyAnotherClient(messageId);
            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }

    /**
     * 发送消息(聊天信息和额外信息共存)
     *
     * @param messageId
     * @param messageManager
     */
    public void sendMessageById(String messageId, MessageManager messageManager) {
        messageManager.queryMessageById(messageId, new Subscriber<ZkLocalMessage>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ZkLocalMessage zkLocalMessage) {
                try {
                    if (zkLocalMessage.getMessageType() == ZkLocalMessage.COMMAND_EXT_REQ) {
                        NetFace.getDefault().sendExtMsgRequest(ConversionObjectUtils.conversionExtraObject(zkLocalMessage, NetFace.getDefault()));
                    } else {
                        NetFace.getDefault().sendChatMsgRequest(ConversionObjectUtils.conversionObject(zkLocalMessage, NetFace.getDefault()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String getUserId() {
        return userId;
    }

    /**
     * 配置类
     */
    public final static class BuildConfig {
        public static final String HOST = "121.42.36.103";
        public static final int PORT = 5566;
        //   public static final String USER_ID = "100001";
        // public static final String TOKE = "sdfsdfsdf1111sdf";
        public static final String DEVICE_MAC = "111112";
        public static final String DEVICE_TYPE = ZkDeviceType.Mobile;
    }
}
