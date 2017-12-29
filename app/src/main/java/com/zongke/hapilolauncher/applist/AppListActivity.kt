package com.zongke.hapilolauncher.applist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.zhongke.launcher.fragment.AppListFragment
import com.zongke.hapilolauncher.R


/**
 * Created by ${xingen} on 2017/7/27.
 *
 * app程序列表
 *
 */
class  AppListActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applist)

        supportFragmentManager.beginTransaction().add(R.id.main_content_layout, AppListFragment.instance,AppListFragment.TAG).commit()

    }
    companion object {
        fun  openActivity(context: Context){
           var intent= Intent(context,AppListActivity::class.java)
            if (context !is Activity){
                /**
                 *  非Activity打开的，需要添加FLAG_ACTIVITY_NEW_TASK flag。
                 */
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }

}