package com.zongke.hapiloimservice.client;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.zongke.hapiloimservice.db.Constance;
import com.zongke.hapiloimservice.model.ActionFlowNodeData;
import com.zongke.hapiloimservice.service.MessageHandlerService;
import com.zongke.hapiloimservice.utils.ActivityUtils;

/**
 * Created by ${xingen} on 2017/12/2.
 * 引擎端处理类
 */

public class EngineClient {
    private static EngineClient instance;
    private final Gson gson;

    private EngineClient() {
        this.gson = new Gson();
    }

    public synchronized static EngineClient getInstance() {
        if (instance == null) {
            instance = new EngineClient();
        }
        return instance;
    }

    /**
     * 处理引擎信息
     *
     * @param content
     */
    public void handlerEngineMessage(MessageHandlerService.MainHandler  mainHandler,Context context, String content) {
        ActionFlowNodeData flowNodeData = parseJson(content, ActionFlowNodeData.class);
        Log.i(MessageHandlerService.class.getSimpleName(), " 解析后的数据为 " + flowNodeData);
        if (flowNodeData == null) {
            return;
        }
        responseEngine(mainHandler,context, flowNodeData);
    }

    private String keyName = "keyName";

    /**
     * 响应引擎信息,做出不同的操作
     *
     * @param flowNodeData
     */
    private void responseEngine(MessageHandlerService.MainHandler mainHandler,Context context, ActionFlowNodeData flowNodeData) {
        switch (flowNodeData.getfNodeType()) {
            case "start":
                mainHandler.obtainMessage(MessageHandlerService.MainHandler.TASK_ENGINE_START_PLAY_AUDIO).sendToTarget();
                break;
            //关键行为
            case "behavior": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String behavior = flowNodeData.getBehaviorName();
                Intent intent = new Intent();
                intent.putExtra(keyName, behavior);
                intent.setClassName("com.zongke.hapilolauncher", "com.zongke.hapilolauncher.test.TestActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            break;
            //弹窗消息
            case "sendMessage": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String content = flowNodeData.getMsgContent();
                Intent intent = new Intent("com.zongke.hapilolauncher.broadcast.StartWindowDialogBroadcastReceiver");
                intent.putExtra("content", content);
                intent.putExtra("type", "showContent");
                context.sendBroadcast(intent);
            }
            break;
            //直接播放视频,这里调用系统播放器
            case "video": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String resourceName = flowNodeData.getResName();
                String resourceUrl = flowNodeData.getResUrl();
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(resourceUrl), "video/mp4");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            //直接播放音频
            case "audio": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String resourceName = flowNodeData.getResName();
                String resourceUrl = flowNodeData.getResUrl();
                MediaPlayer mediaPlayer = null;
                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.reset();
                    mediaPlayer.setDataSource(resourceUrl);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
            break;
            //打开情景灯
            case "sceneLight": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String resourceName = flowNodeData.getResName();
                String resourceUrl = flowNodeData.getResUrl();

            }
            break;
            //打开相机
            case "camera": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                ActivityUtils.startCameraApp(context);
            }
            break;
            //开启麦克风
            case "microphone": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
            }
            break;
            //操作台灯
            case "lamp": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
            }
            break;
            //红外检测
            case "infrared": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
            }
            break;
            //温度
            case "temperature": {

            }
            break;
            //湿度
            case "humidity": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
            }
            break;
            //光照强度
            case "lightIntensity": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
            }
            break;
            //活动道具
            case "tools": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String toolsName = flowNodeData.getToolsName();

            }
            break;
            //活动邀请,弹窗
            case "invitation": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String resourceUrl = flowNodeData.getResUrl();
            }
            break;
            //活动聊天
            case "actionChat": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String resourceUrl = flowNodeData.getResUrl();
                ActivityUtils.startOtherLauncherApp(context, "com.zhongke.chat");
            }
            break;
            //活动抢答,少了试卷名
            case "answer": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                int actionId = flowNodeData.getActionId();
                try {
                    {
                        //开启活动的通知
                        Intent intent = new Intent();
                        intent.setClassName("com.zhongke.content", "com.zhongke.content.activity.AnswerActivity");
                        intent.putExtra(Constance.key_activity_id, String.valueOf(actionId));
                        ActivityUtils.starActivity(context, intent);
                    }
                    {
                        Intent intent = new Intent("com.zongke.hapiloimservice.service.MessageHandlerService.ActivityStartBroadcastReceiver");
                        intent.putExtra(Constance.key_activity_id, String.valueOf(actionId));
                        context.sendBroadcast(intent);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            //上传活动成果
            case "uploadResult": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                Intent intent = new Intent();
                intent.putExtra(keyName, "上传成果");
                intent.setClassName("com.zongke.hapilolauncher", "com.zongke.hapilolauncher.test.TestActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            break;
            //活动评价,少了评价机制，Haplior无评价页面
            case "evaluate": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();

                Intent intent = new Intent();
                intent.putExtra(keyName, "活动评价");
                intent.setClassName("com.zongke.hapilolauncher", "com.zongke.hapilolauncher.test.TestActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            break;
            //活动奖励
            case "award": {
                int runTime = flowNodeData.getRunTime();
                int doObjectId = flowNodeData.getDoObject();
                String awardName = flowNodeData.getAwardName();
                ActivityUtils.startOtherActivity(context, "com.zhongke.content", "com.zhongke.content.activity.RewardActivity");
            }
            break;
            //泳道开关
            case "group": {
                Intent intent = new Intent();
                intent.putExtra(keyName, "游泳开关");
                intent.setClassName("com.zongke.hapilolauncher", "com.zongke.hapilolauncher.test.TestActivity");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            break;
            default:

        }
    }

    /**
     * 解析字符串
     *
     * @param content
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> T parseJson(String content, Class<T> tClass) {
        T t = null;
        try {

            t = gson.fromJson(content, tClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
