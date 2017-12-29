package com.zongke.hapilolauncher.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by ${xingen} on 2017/7/19.
 *
 * Toast工具类
 */

class ToastUtils {
    companion object {
        @JvmStatic
        fun showToast(context:Context,content:String) {
            Toast.makeText(context,content, Toast.LENGTH_SHORT).show()
        }
    }
}
