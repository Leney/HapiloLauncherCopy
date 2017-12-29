package com.zongke.hapilolauncher.window

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.WindowManager

/**
 * Created by ${xinGen} on 2017/7/20.
 * 悬浮窗口的工具类
 */
class SuspensionWindowManagerUtils {
    companion object {
        var windowManager: WindowManager? = null
        var layoutParams: WindowManager.LayoutParams? = null
        var suspensionWindowWidget: SuspensionWindowLayout? = null

        /**
         * 开启悬浮弹窗
         */
        @JvmStatic
        fun openSuspensionWindow(context: Activity) {
            if (checkPermission(context)) {
                if (!windowIsOpen()) {
                    SuspensionWindowManagerUtils.createSuspensionWindow(context.application)
                }
            } else {
                requestPermission(context)
            }
        }

        /**
         * 申请权限的状态code
         */
        @JvmField
        val request_code_window = 1

        /**
         * 开启权限管理界面，授权。
         */
        fun requestPermission(context: Activity) {
            var intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
            context.startActivityForResult(intent, request_code_window)
        }

        /**
         * 悬浮窗口是否已经打开
         */
        fun windowIsOpen(): Boolean {
            return if (suspensionWindowWidget != null) true  else  false
        }

        /**
         * 当目标版本大于23时候，检查权限
         */
        @JvmStatic
        fun checkPermission(context: Context): Boolean {
            if (Build.VERSION.SDK_INT >= 23)
                return Settings.canDrawOverlays(context)
            else
                return true
        }
        fun createSuspensionWindow(context: Context) {
            createSuspensionWindow(context,0)
        }
        /**
         * 创建悬浮窗口
         */
        fun createSuspensionWindow(context: Context,layoutId:Int) {
                if (layoutId==0){
                    suspensionWindowWidget = SuspensionWindowLayout(context)
                }else{
                   suspensionWindowWidget= SuspensionWindowLayout(context,layoutId)
                }
            getWindowManager(context)?.addView(suspensionWindowWidget, getWidgetLayoutParams())
        }
        /**
         * 移除悬浮窗口
         */
        fun removeSuspensionWindow(context: Context) {
            if (suspensionWindowWidget != null) {
                getWindowManager(context)?.removeView(suspensionWindowWidget)
                suspensionWindowWidget = null
            }
        }

        /**
         * 获取悬浮窗口的布局参数
         */
        fun getWidgetLayoutParams(): WindowManager.LayoutParams? {
            if (layoutParams == null) {
                layoutParams = WindowManager.LayoutParams()
                layoutParams!!.type = WindowManager.LayoutParams.TYPE_PHONE
                layoutParams!!.format = PixelFormat.RGBA_8888
                layoutParams!!.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                layoutParams!!.gravity = Gravity.LEFT or Gravity.TOP
                layoutParams!!.x = windowManager!!.defaultDisplay.width
                layoutParams!!.y = 0
                layoutParams!!.width = SuspensionWindowLayout.widget_width
                layoutParams!!.height = SuspensionWindowLayout.widget_height
            }
            return layoutParams
        }

        /**
         * 获取窗口管理器
         */
        fun getWindowManager(context: Context): WindowManager? {
            if (windowManager == null) {
                windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            }
            return windowManager
        }
    }
}