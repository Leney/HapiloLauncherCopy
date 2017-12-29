package com.zk.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.zk.server.IZkScmService;
import com.zongke.hapiloimservice.utils.IntentBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ${xingen} on 2017/12/12.
 * <p>
 * 引擎服务的操作类
 */

public class EngineServiceAidlClient {
    private final String TAG = EngineServiceAidlClient.class.getSimpleName();
    private IZkScmService zkScmService = null;
    public static final String baseUrl = "http://yanfayi.cn:8889";
    private Context appContext;

    public EngineServiceAidlClient(Context context) {
        this.appContext = context.getApplicationContext();
        try {
            Intent intent = new Intent(IZkScmService.class.getName());
            intent.setPackage("com.zk.server");
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解除绑定
     *
     * @param context
     */
    public void unBindService(Context context) {
        Log.i(TAG, " 开始解除绑定服务");
        context.unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            zkScmService = IZkScmService.Stub.asInterface(service);
            Log.i(TAG, " 引擎服务连接成功： onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            zkScmService = null;
            Log.i(TAG, "引擎服务断开： onServiceDisconnected");
        }
    };

    /**
     * 初始化配置
     *
     * @param baseUrl
     */
    public void initConfig(String baseUrl) {
        try {
            if (zkScmService != null) {
                zkScmService.InitBaseUrl(baseUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 托管一个活动
     *
     * @param actionId 以下时间不确定的，传入*
     * @param minute
     * @param hour
     * @param day
     * @param month
     * @param week     显示案例： (145,"1-59","*","*","*","*")
     */
    public void startAction(int actionId, String minute, String hour, String day, String month, String week) {
        try {
            if (zkScmService != null) {
                initConfig(EngineServiceAidlClient.baseUrl);
                Log.i(TAG, "开始 托管引擎到活动中 ");
                appContext.sendBroadcast(IntentBuilder.builderGroupIntent("com.zongke.hapiloimservice.service.MessageHandlerService.JoinGroupMessageBroadcastReceiver ", "Answer_" + String.valueOf(actionId)));
                zkScmService.StartAction(actionId, minute, hour, day, month, week);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理活动托管到引擎中
     * <p>
     * //	 *  * cron 表达式由五部分组成：分 时 天 月 周
     * //* 分 ：从 0 到 59
     * //* 时 ：从 0 到 23
     * //* 天 ：从 1 到 31，字母 L 可以表示月的最后一天
     * //* 月 ：从 1 到 12，可以别名：jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov" and "dec"
     * //* 周 ：从 0 到 6，0 表示周日，6 表示周六，可以使用别名： "sun", "mon", "tue", "wed", "thu", "fri" and "sat"
     * //*
     * //* 数字 n：表示一个具体的时间点，例如 5 * * * * 表示 5 分这个时间点时执行
     * //* 逗号 , ：表示指定多个数值，例如 3,5 * * * * 表示 3 和 5 分这两个时间点执行
     * //* 减号 -：表示范围，例如 1-3 * * * * 表示 1 分、2 分再到 3 分这三个时间点执行
     * //* 星号 *：表示每一个时间点，例如 * * * * * 表示每分钟执行
     * //* 除号 /：表示指定一个值的增加幅度。例如 n/m表示从 n 开始，每次增加 m 的时间点执行
     *
     * @param activityId
     * @param startTime
     */
    public void handlerActivityToEngine(String activityId, String startTime) {
        try {
            int actionId = Integer.valueOf(activityId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(startTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
            String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
            String minute = String.valueOf(calendar.get(Calendar.MINUTE));
            Log.i(TAG, " 托管活动 " + " id " + activityId + "开始时间 " + startTime + " 解析后 " + month + " " + day + " " + hour + " " + minute);
            startAction(actionId, minute, hour, day, month, "*");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 一个引擎活动开始播放
     */
    public void playStartAudio() {
        try {
            if (zkScmService != null) {
                zkScmService.PlayFaceWithAudio(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
