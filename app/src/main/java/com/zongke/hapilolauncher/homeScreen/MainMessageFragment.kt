package com.zongke.hapilolauncher.homeScreen

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.zongke.hapilolauncher.R
import com.zongke.hapilolauncher.base.BaseFragment


/**
 * Created by ${xingen} on 2017/7/5.
 * 显示主页中收到的信息
 */

class MainMessageFragment : BaseFragment(), View.OnClickListener {
    override val layoutId: Int get() = R.layout.fragment_main_message

    override fun initAfterActivityCreated(rootView: View, savedInstanceState: Bundle?) {

     var view:TextView=   rootView.findViewById(R.id.main_message_see_tv)
        view.setOnClickListener(this)

    //    SuspensionWindowManagerUtils.openSuspensionWindow(activity)
    }

    override fun onClick(view: View) {
        Log.i(TAG," onclick")
        when (view.id) {
            R.id.main_message_see_tv//点击查看
            -> {
                Log.i(TAG," onclick  点击查看")
                var component=ComponentName("com.zhongke.content","com.zhongke.content.activity.RewardActivity")
                var intent=Intent()
                intent.component=component
                startActivity(intent)
            }
            R.id.main_message_look_tv//前往
            -> {

            }
        }
    }

    companion object {
        val TAG = MainMessageFragment::class.java.simpleName
        fun newInstance(): MainMessageFragment {
            return MainMessageFragment()
        }
    }
}
