package com.zongke.hapiloimservice.client;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.zhongke.local.message.model.ZkLocalMessage;
import com.zk.NetFace;
import com.zk.net.ZkMessagePacket;
import com.zk.net.message.ExtCmdResponseMessage;
import com.zongke.hapiloimservice.db.Constance;
import com.zongke.hapiloimservice.extra.ConversionObjectUtils;
import com.zongke.hapiloimservice.extra.ExtraMessage;
import com.zongke.hapiloimservice.extra.message.WishBindActivityNotice;
import com.zongke.hapiloimservice.utils.ActivityUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${xingen} on 2017/12/12.
 *
 * 额外信息处理类
 */

public class ExtraMessageClient {
    private static ExtraMessageClient instance;
    private final Gson gson;
    private final CompositeSubscription compositeSubscrption;
    private final String TAG = ExtraMessageClient.class.getSimpleName();

    private ExtraMessageClient() {
        this.gson = new Gson();
        this.compositeSubscrption = new CompositeSubscription();
    }

    public static synchronized ExtraMessageClient getInstance() {
        if (instance == null) {
            instance = new ExtraMessageClient();
        }
        return instance;
    }

    public void handlerExtraMessage(final ExtCmdResponseMessage extCmdResponseMessage, final Context context) {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<ExtraMessage>() {
            @Override
            public void call(Subscriber<? super ExtraMessage> subscriber) {
                ExtraMessage extraMessage = ConversionObjectUtils.parseJson( extCmdResponseMessage.getChatContent());
                subscriber.onNext(extraMessage);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<ExtraMessage>() {
            @Override
            public void call(ExtraMessage extraMessage) {
                handlerResult(extraMessage, context);
            }
        });
        this.compositeSubscrption.add(subscription);
    }

    private void handlerResult(ExtraMessage extraMessage, Context context) {
        if (extraMessage == null || extraMessage.code == 0) {
            return;
        }
        try {
            switch (extraMessage.code) {
                case ExtraMessage.wish_bind_activiy: {
                    WishBindActivityNotice wishBindActivityNotice = ConversionObjectUtils.parseJson( extraMessage.message,WishBindActivityNotice.class);
                    switch (wishBindActivityNotice.code) {
                        //家长已经绑定活动
                        case WishBindActivityNotice.code_wish_bind_activity: {
                            Log.i(TAG, " 家长绑定活动了的通知 ");
                            Intent intent = new Intent();
                            intent.setClassName("com.zhongke.content", "com.zhongke.content.activity.RemindSeeActivity");
                            intent.putExtra(Constance.key_activity_id, wishBindActivityNotice.bindActivityId);
                            ActivityUtils.starActivity(context, intent);
                        }
                        break;
                        //家长已经评价活动
                        case WishBindActivityNotice.code_activity_evaluation: {
                            Log.i(TAG, " 家长评价活动了的通知 ");
                            {
                            Intent intent = new Intent();
                            intent.setClassName("com.zhongke.content", "com.zhongke.content.activity.RemindSeeCommentActivity");
                            intent.putExtra(Constance.key_activity_id, wishBindActivityNotice.bindActivityId);
                            ActivityUtils.starActivity(context, intent);
                            }
                        }
                        break;
                        default:
                            break;
                    }
                }
                break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送活动开始的通知
     * @param sendUserId
     * @param groupId
     * @param activityId
     */
    public void startActivityNotify(String sendUserId,String groupId,String activityId){
        try {
            ExtraMessage extraMessage=new ExtraMessage();
            extraMessage.code=ExtraMessage.wish_bind_activiy;
            WishBindActivityNotice notice=new WishBindActivityNotice();
            notice.code=WishBindActivityNotice.code_activity_start;
            notice.bindActivityId=activityId;
            notice.wishUserId=sendUserId;
            extraMessage.message= ConversionObjectUtils.fromObjectToString(notice);
            ZkLocalMessage zkLocalMessage = new ZkLocalMessage();
            zkLocalMessage.setSendUserId(sendUserId);
            zkLocalMessage.setGroupId(groupId);
            zkLocalMessage.setChatContent(gson.toJson(extraMessage));
            ZkMessagePacket zkMessagePacket=com.zongke.hapiloimservice.utils.ConversionObjectUtils.conversionExtraObject(zkLocalMessage, NetFace.getDefault());
            Log.i(TAG," 通知其他客户端开始活动: " +zkLocalMessage.getMsgId()+" "+zkMessagePacket.toJson() );
           NetFace.getDefault().sendExtMsgRequest(zkMessagePacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
