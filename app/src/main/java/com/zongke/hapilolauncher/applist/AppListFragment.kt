package com.zhongke.launcher.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zongke.hapilolauncher.R
import com.zongke.hapilolauncher.applist.AppListAdapter
import com.zongke.hapilolauncher.applist.AppModel
import com.zongke.hapilolauncher.applist.AppsLoader
import com.zongke.hapilolauncher.applist.PackageIntentReceiver

import kotlinx.android.synthetic.main.fragment_applist.view.*
/**
 * Created by ${新根} on 2017/7/16.
 *
 */
class AppListFragment :Fragment(),LoaderManager.LoaderCallbacks<ArrayList<AppModel>>{
    val   LOAD_APP_ID=1
    lateinit var rootView:View
    lateinit var  adapter: AppListAdapter
    lateinit var appsLoader: AppsLoader
    lateinit  var packageIntentReceiver: PackageIntentReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.appsLoader= AppsLoader(activity)
        //注册监听的广播
        this. packageIntentReceiver= PackageIntentReceiver(appsLoader)
        //注册加载器
        this.loaderManager.initLoader(LOAD_APP_ID,null,this)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView=inflater.inflate(R.layout.fragment_applist,container,false)
        rootView.applist_recyclerView.layoutManager=GridLayoutManager(activity,5)
        adapter=AppListAdapter()
        rootView.applist_recyclerView.adapter=adapter
        return rootView
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<ArrayList<AppModel>> {
        return appsLoader
    }
    override fun onLoadFinished(loader: Loader<ArrayList<AppModel>>?, data: ArrayList<AppModel>) {
                    adapter.addData(data)
    }
    override fun onLoaderReset(loader: Loader<ArrayList<AppModel>>?) {
        //当重置的时候，传递一个空的集合
        adapter.addData(arrayListOf())
    }

    /**
     * 释放资源，这里销毁加载器，和取消注册广播
     */
    override fun onDestroy() {
        super.onDestroy()
        this. loaderManager.destroyLoader(LOAD_APP_ID)
        this.packageIntentReceiver.unRegisterReceiver()
    }

    /**
     * 静态
     */
    companion object{
        val TAG=AppListFragment::class.java.simpleName
        var instance=AppListFragment()
    }
}
