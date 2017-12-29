package com.zongke.hapiloimservice.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.zhongke.local.message.control.MessageManager;
import com.zk.ZkNetEvent;
import com.zk.ZkRetCode;
import com.zk.android.sqlite.ZkLocalMessage;
import com.zk.client.EngineServiceAidlClient;
import com.zk.net.message.AckS2CMessage;
import com.zk.net.message.ExtCmdResponseMessage;
import com.zk.net.message.JoinGroupNotifyResponseMessage;
import com.zongke.hapiloimservice.client.EngineClient;
import com.zongke.hapiloimservice.client.ExtraMessageClient;
import com.zongke.hapiloimservice.client.IMClient;
import com.zongke.hapiloimservice.db.Constance;
import com.zongke.hapiloimservice.utils.ConversionObjectUtils;
import com.zongke.hapiloimservice.utils.IntentBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * 统一处理：各种消息的分发和传递
 */
public class MessageHandlerService extends Service {
    private final String TAG = MessageHandlerService.class.getSimpleName();
    private volatile MainHandler mainHandler;
    private IMClient imClient;
    private MessageManager messageDBManager;
    private EngineServiceAidlClient engineServiceAidlClient;
    private AcceptMessageBroadcastReceiver acceptMessageBroadcastReceiver;
    private JoinGroupMessageBroadcastReceiver joinGroupMessageBroadcastReceiver;
    private AccountChangeBroadcastReceiver accountChangeBroadcastReceiver;
    private ExtraMessageClient extraMessageClient;
    private List<String> groupArray;
    private EngineClient engineClient;

    /**
     * 开启服务
     *
     * @param context
     */
    public static void startService(Context context) {
        Intent intent = new Intent("com.zongke.hapiloimservice.service.MessageHandlerService");
        //在5.0以上，需要添加程序报名，才能正常调用
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }

    /**
     * 接收到其他客户端发送过来的信息
     */
    protected class AcceptMessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String messageId = intent.getStringExtra(IntentBuilder.KEY_MESSAGE_ID);
            Log.i(TAG, "接收其他客户端 " + messageId);
            mainHandler.obtainMessage(MainHandler.TASK_USER_SEND_MESSAGE, messageId).sendToTarget();
        }
    }

    /**
     * 接受加入群组的信息
     */
    private class JoinGroupMessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String groupId = intent.getStringExtra(IntentBuilder.KEY_GROUP_ID);
            if (!groupArray.contains(groupId)) {
                groupArray.add(groupId);
                Log.i(TAG, "接收其他客户端 的群组号" + groupId);
                mainHandler.obtainMessage(MainHandler.TASK_CONNECT_JOIN_GROUP, groupId).sendToTarget();
            }
        }
    }
    private ResponseEngineReceiver responseEngineReceiver;
    /**
     * 响应搜索引擎的广播
     */
    private class ResponseEngineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "接受到引擎信息");
            mainHandler.obtainMessage(MainHandler.TASK_RESPONSE_ENGINE, intent.getStringExtra("engineMessage")).sendToTarget();
        }
    }

    /**
     * 账户信息发送改变的时候，监听
     */
    private class AccountChangeBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG, "账户信息发生了改变");
            mainHandler.obtainMessage(MainHandler.TASK_ACCOUNT_CHANGE).sendToTarget();
        }
    }

    /**
     * 注销登入
     */
    private class AcceptStopReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            mainHandler.obtainMessage(MainHandler.TASK_RESPONSE_STOP).sendToTarget();
        }
        public void register(Context context) {
            IntentFilter intentFilter = new IntentFilter("com.zongke.hapiloimservice.service.MessageHandlerService.AcceptStopReceiver");
            context.registerReceiver(this, intentFilter);
        }
        public void unRegister(Context context) {
            context.unregisterReceiver(this);
        }
    }

    /**
     * 接受活动，从而托管到引擎中
     */
    private class ActivityToEngineBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG," 引擎托管活动的开始的广播通知");
            Message message = mainHandler.obtainMessage(MainHandler.TASK_ACTIVITY_TO_ENGINE);
            Bundle bundle = new Bundle();
            bundle.putString(Constance.key_activity_id, intent.getStringExtra(Constance.key_activity_id));
            bundle.putString(Constance.key_start_time, intent.getStringExtra(Constance.key_start_time));
            message.setData(bundle);
            message.sendToTarget();
        }
        public void register(Context context) {
            IntentFilter intentFilter = new IntentFilter("com.zongke.hapiloimservice.service.MessageHandlerService.ActivityToEngineBroadcastReceiver");
            context.registerReceiver(this, intentFilter);
        }
        public void unRegister(Context context) {
            context.unregisterReceiver(this);
        }
    }

    /**
     * 活动开始通知其他人员
     */
    private class  ActivityStartBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Message message = mainHandler.obtainMessage(MainHandler.TASK_ACTIVITY_START_NOFITY);
            Bundle bundle = new Bundle();
            String activityId=intent.getStringExtra(Constance.key_activity_id);
            bundle.putString(Constance.key_activity_id, activityId);
            message.setData(bundle);
            Log.i(TAG," 活动开始的广播通知  "+activityId);
            message.sendToTarget();
        }
        public void register(Context context) {
            IntentFilter intentFilter = new IntentFilter("com.zongke.hapiloimservice.service.MessageHandlerService.ActivityStartBroadcastReceiver");
            context.registerReceiver(this, intentFilter);
        }
        public void unRegister(Context context) {
            context.unregisterReceiver(this);
        }
    }


    /**
     * 一个主线程的Handler执行一些，特殊指令
     */
    public final class MainHandler extends Handler {
        /**
         * 开始任务,IM SDK建立连接
         */
        public static final int TASK_CONNECT_START = 1;
        /**
         * 消息接受
         */
        public static final int TASK_MESSAGE_RECEIVER = 2;
        /**
         * 发送消息的结果
         */
        public static final int TASK_MESSAGE_SEND_RESULT = 3;
        /**
         * 重新建立连接
         */
        public static final int TASK_CONNECT_RESTART = 4;
        /**
         * 加入全部需要的群组,当断开连接，重连的时候
         */
        public static final int TASK_CONNECT_ALL_JOIN_GROUP = 5;
        /**
         * 用户发送的信息,发送给其他客户端
         */
        public static final int TASK_USER_SEND_MESSAGE = 6;
        /**
         * 新加入群组
         */
        public static final int TASK_CONNECT_JOIN_GROUP = 7;
        /**
         * 账户信息发生改善
         */
        public static final int TASK_ACCOUNT_CHANGE = 8;
        /**
         * 额外消息发送结果
         */
        public static final int TASK_SEND_EXTRA_MESSAGE = 9;
        /**
         * 额外消息接受
         */
        public static final int TASK_RECEIVER_EXTRA_MESSAGE = 10;
        /**
         * 网络连接情况
         */
        public static final int TASK_NET_CONNECT_RESULT = 11;
        /**
         * 引擎信息
         */
        public static final int TASK_RESPONSE_ENGINE = 12;
        /**
         * 停止服务
         */
        public static final int TASK_RESPONSE_STOP = 13;
        /**
         * 托管活动到引擎
         */
        public static final int TASK_ACTIVITY_TO_ENGINE = 14;
        /**
         *  通知群组，抢答活动开始了
         */
        public static final int TASK_ACTIVITY_START_NOFITY=15;
        /**
         * 引擎开始活动，播放
         */
        public static final int TASK_ENGINE_START_PLAY_AUDIO=16;

        public MainHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case TASK_CONNECT_START: {
                    imClient.startConnect();
                   // engineServiceAidlClient.initConfig(EngineServiceAidlClient.baseUrl);
                }
                break;
                case TASK_CONNECT_RESTART: {
                    imClient.reConnect();
                }
                break;
                case TASK_MESSAGE_SEND_RESULT: {
                    ZkNetEvent event = (ZkNetEvent) msg.obj;
                    int state = (event.retCode == ZkRetCode.FAILED ?
                            com.zhongke.local.message.model.ZkLocalMessage.SEND_MSG_FAILED
                            : com.zhongke.local.message.model.ZkLocalMessage.SEND_MSG_SECCUSS);
                    String messageId = (event.retCode == ZkRetCode.FAILED ? event.localMessage.getMsgId() : ((AckS2CMessage) event.eventData).getMsgId());
                    imClient.handlerSendMessage(messageId, state, messageDBManager);
                }
                break;
                case TASK_MESSAGE_RECEIVER: {
                    ZkNetEvent event = (ZkNetEvent) msg.obj;
                    ZkLocalMessage zkLocalMessage = event.localMessage;
                    if (zkLocalMessage == null) {
                        return;
                    }
                    imClient.handlerReceiverMessage(ConversionObjectUtils.conversionObject(zkLocalMessage), messageDBManager);
                }
                break;
                case TASK_USER_SEND_MESSAGE: {
                    imClient.sendMessageById((String) msg.obj, messageDBManager);
                }
                break;
                case TASK_SEND_EXTRA_MESSAGE: {
                    ZkNetEvent event = (ZkNetEvent) msg.obj;
                    String messageId = (String) event.eventData;
                    Log.i(TAG, " 发送一个额外信息是： " + event.eventData + " " + event.retCode);
                    int state = (event.retCode == ZkRetCode.FAILED ?
                            com.zhongke.local.message.model.ZkLocalMessage.SEND_MSG_FAILED
                            : com.zhongke.local.message.model.ZkLocalMessage.SEND_MSG_SECCUSS);
                    imClient.handlerExtraSendMessage(messageId, state, messageDBManager);
                }
                break;
                case TASK_RECEIVER_EXTRA_MESSAGE: {
                    ZkNetEvent event = (ZkNetEvent) msg.obj;
                    Log.i(TAG, " 接受到一个额外信息： " + event.eventData + " " + event.retCode);
                    ExtCmdResponseMessage extCmdResponseMessage = (ExtCmdResponseMessage) event.extData;
                    if (extCmdResponseMessage == null) {
                        return;
                    }
                    //群发消息，服务器重推自己发送的信息过滤
                    if (extCmdResponseMessage.getSendUserId().equals(IMClient.getInstance().getUserId())) {
                        return;
                    }
                    imClient.handlerReceiverExtraMessage(ConversionObjectUtils.conversionExtraObject(extCmdResponseMessage), messageDBManager);
                    extraMessageClient.handlerExtraMessage(extCmdResponseMessage, MessageHandlerService.this);
                }
                break;
                case TASK_CONNECT_JOIN_GROUP: {
                    String groupId = (String) msg.obj;
                    imClient.joinGroup(groupId);
                }
                break;
                case TASK_CONNECT_ALL_JOIN_GROUP: {
                    Log.i(TAG, " 开始加入群组列表");
                    for (String groupId : groupArray) {
                        imClient.joinGroup(groupId);
                        Log.i(TAG, " 加入的群组Id " + groupId);
                    }
                }
                break;
                case TASK_ACCOUNT_CHANGE: {
                    imClient.accountChange();
                }
                break;
                case TASK_NET_CONNECT_RESULT:
                    boolean result = (boolean) msg.obj;
                    imClient.notifyAnotherClientNetChange(result);
                    break;
                case TASK_RESPONSE_ENGINE:
                    engineClient.handlerEngineMessage(this,MessageHandlerService.this, (String) msg.obj);
                    break;
                case TASK_RESPONSE_STOP:
                    stopSelf();
                    break;
                case TASK_ACTIVITY_TO_ENGINE: {
                    Bundle bundle = msg.getData();
                    if (bundle == null) {
                        return;
                    }
                    String activityId = bundle.getString(Constance.key_activity_id);
                    String startTime = bundle.getString(Constance.key_start_time);
                    engineServiceAidlClient.handlerActivityToEngine(activityId,startTime);
                }
                break;
                case TASK_ACTIVITY_START_NOFITY:
                    final Bundle bundle = msg.getData();
                    if (bundle == null) {
                        return;
                    }
                    final String activityId=bundle.getString(Constance.key_activity_id);
                    final String groupId =Constance.prefix_message_answer+ activityId;
                    Log.i(TAG," 通知活动开始 "+activityId+" "+groupId);
                    if (!groupArray.contains(groupId)){
                        imClient.joinGroup(groupId);
                        groupArray.add(groupId);
                    }
                    this.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           extraMessageClient.startActivityNotify(imClient.getUserId(),groupId,activityId);
                        }
                    },200);
                    break;
                case TASK_ENGINE_START_PLAY_AUDIO:
                    engineServiceAidlClient.playStartAudio();
                    break;
                default:
                    break;
            }
        }
    }

    private AcceptStopReceiver acceptStopReceiver;

    /**
     * 设置各种配置
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, TAG + " onCreate");
        this.groupArray = new ArrayList<>();
        //事件注册
        EventBus.getDefault().register(this);
        mainHandler = new MainHandler();
        imClient = IMClient.getInstance();
        imClient.initConfig(getApplicationContext());
        messageDBManager = MessageManager.getInstance();
        messageDBManager.init(this.getApplicationContext());
        engineClient = EngineClient.getInstance();
        //动态注册广播
        acceptMessageBroadcastReceiver = new AcceptMessageBroadcastReceiver();
        registerReceiver(acceptMessageBroadcastReceiver, IntentBuilder.builderIntentFilter(IntentBuilder.ACTION_ACCEPT_MESSAGE));
        joinGroupMessageBroadcastReceiver = new JoinGroupMessageBroadcastReceiver();
        registerReceiver(joinGroupMessageBroadcastReceiver, IntentBuilder.builderIntentFilter(IntentBuilder.ACTION_JOIN_GROUP));

        accountChangeBroadcastReceiver = new AccountChangeBroadcastReceiver();
        registerReceiver(accountChangeBroadcastReceiver, IntentBuilder.builderIntentFilter(IntentBuilder.ACCOUNT_MESSAGE_ACTION));

        responseEngineReceiver = new ResponseEngineReceiver();
        registerReceiver(responseEngineReceiver, new IntentFilter("com.zongke.hapiloimservice.service.MessageHandlerService.ResponseEngineReceiver"));

        acceptStopReceiver = new AcceptStopReceiver();
        acceptStopReceiver.register(this);
        this.activityToEngineReceiver = new ActivityToEngineBroadcastReceiver();
        this.activityToEngineReceiver.register(this);
        this.activityStartBroadcastReceiver=new ActivityStartBroadcastReceiver();
        this.activityStartBroadcastReceiver.register(this);
        /**
         *  绑定引擎服务
         */
        this.engineServiceAidlClient = new EngineServiceAidlClient(this);
        this.extraMessageClient = ExtraMessageClient.getInstance();
    }
  private ActivityStartBroadcastReceiver activityStartBroadcastReceiver;
    private ActivityToEngineBroadcastReceiver activityToEngineReceiver;

    /**
     * 执行任务
     *
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mainHandler.obtainMessage(MainHandler.TASK_CONNECT_START).sendToTarget();
        Log.i(TAG, TAG + " onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, TAG + " onDestroy");
        if (imClient != null) {
            imClient.destroyConnect();
        }
        // 注销监听
        EventBus.getDefault().unregister(this);
        //取消广播
        unregisterReceiver(acceptMessageBroadcastReceiver);
        unregisterReceiver(joinGroupMessageBroadcastReceiver);
        unregisterReceiver(accountChangeBroadcastReceiver);
        unregisterReceiver(responseEngineReceiver);
        acceptStopReceiver.unRegister(this);
        if (engineServiceAidlClient != null) {
            engineServiceAidlClient.unBindService(this);
        }
        this.activityToEngineReceiver.unRegister(this);
        this.activityStartBroadcastReceiver.unRegister(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 在子线程中处理这些消息
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventMainThread(ZkNetEvent event) {
        switch (event.netEventType) {
            //聊天消息发送
            case MSG_CHAT_REQ_EVENT: {
                Log.i(TAG, "发送信息的结果回调---->>> ");
                mainHandler.obtainMessage(MainHandler.TASK_MESSAGE_SEND_RESULT, event).sendToTarget();
            }
            break;
            //聊天消息接收
            case MSG_CHAT_RES_EVNT: {
                Log.i(TAG, "接收到聊天消息---->>>" + event);
                mainHandler.obtainMessage(MainHandler.TASK_MESSAGE_RECEIVER, event).sendToTarget();
                if (event.localMessage != null) {
                    // Log.i(TAG, "接收到聊天消息---->>>" + event.localMessage.chatContent);
                }
            }
            break;
            //额外消息发送结果
            case MSG_EXT_REQ_EVENT:
                Log.i(TAG, "发送额外信息的结果回调---->>> ");
                mainHandler.obtainMessage(MainHandler.TASK_SEND_EXTRA_MESSAGE, event).sendToTarget();
                break;
            //额外消息接收
            case MSG_EXT_RES_EVENT:
                Log.i(TAG, "接收到额外的指令---->>>" + event);
                mainHandler.obtainMessage(MainHandler.TASK_RECEIVER_EXTRA_MESSAGE, event).sendToTarget();
                break;
            //加入群
            case MSG_JOINGROUP_RES_EVENT:
                Log.i(TAG, " 加入群组的结果" + event.retCode);
                if (event.retCode == ZkRetCode.FAILED) {
                    JoinGroupNotifyResponseMessage responseMessage = (JoinGroupNotifyResponseMessage) event.eventData;
                    mainHandler.obtainMessage(MainHandler.TASK_CONNECT_JOIN_GROUP, responseMessage.getGroupId()).sendToTarget();
                }
                break;
            //离开群
            case MSG_LEAVEGROUP_RES_EVENT:
                Log.i(TAG, " 离开群组");
                break;
            //套接字通道异常
            case CHANNEL_EXCEPTION: {
                mainHandler.obtainMessage(MainHandler.TASK_CONNECT_RESTART).sendToTarget();
                Log.e(TAG, "套接字通道异常,重新连接！！！");
            }
            break;
            //套接字通道被关闭中断
            case CHANNEL_INTERRUPT: {
                mainHandler.obtainMessage(MainHandler.TASK_CONNECT_RESTART).sendToTarget();
                Log.e(TAG, "套接字通道被关闭中断！");
            }
            break;
            //套接字通道连接成功
            case CHANNEL_CONNECT_SUCCESS: {
                Log.i(TAG, "套接字通道连接成功！");
            }
            break;
            //套接字通道被踢下线
            case CHANNEL_KILL_OFF: {
                mainHandler.obtainMessage(MainHandler.TASK_CONNECT_RESTART).sendToTarget();
                Log.e(TAG, "套接字通道被踢下线！");
            }
            break;
            /**
             *  通道鉴权成功,可以进行，加入群组的操作
             */
            case CHANEEL_AUTH_SECCUSS: {
                Log.i(TAG, "通道鉴权成功");
                mainHandler.obtainMessage(MainHandler.TASK_CONNECT_ALL_JOIN_GROUP).sendToTarget();
            }
            break;
            //WIFI状态变化
            case NETWORK_WIFI_STATE_CHANGE: {
                Log.i(TAG, "wifi状态变化");
            }
            break;
            //WIFI连接路由状态变化
            case NETWORK_WIFT_CONNECT_ROUTER_CHANGE: {
                Log.i(TAG, "WIFI连接路由状态变化");
            }
            break;
            //网络信息变化包含移动和WIFI的变化
            case NETWORK_INFO_CHANGE: {
                if (event.eventData != null) {
                    NetworkInfo activeNetInfo = (NetworkInfo) event.eventData;
                    if (activeNetInfo.isAvailable() && activeNetInfo.isConnected()) {
                        Log.i(TAG, "物理网络连接正常！");
                        mainHandler.obtainMessage(MainHandler.TASK_NET_CONNECT_RESULT, true).sendToTarget();
                        return;
                    } else {
                        mainHandler.obtainMessage(MainHandler.TASK_NET_CONNECT_RESULT, false).sendToTarget();
                    }
                }
                Log.e(TAG, "物理网络连接异常！");
            }
            break;
            default: {

            }
        }
    }

}
