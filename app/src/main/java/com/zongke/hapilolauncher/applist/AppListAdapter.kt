package com.zongke.hapilolauncher.applist

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup

import com.zongke.hapilolauncher.R

import kotlinx.android.synthetic.main.applist_item_view.view.*

/**
 * reated by ${新根} on 2017/7/15.C
 * blog ：http://blog.csdn.net/hexingen
 */
class AppListAdapter : RecyclerView.Adapter<AppListAdapter.ViewHolder>() {

   private var appList = arrayListOf<AppModel>()
    private val tag=AppListAdapter::class.java.simpleName
    override fun getItemCount() = appList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var rootView = View.inflate(parent.context, R.layout.applist_item_view, null)
        var viewHolder = ViewHolder(rootView)
        rootView.setOnClickListener {
            var position = viewHolder.layoutPosition
            var appModel = appList[position]
            var context = parent.context
            //打开对应的运用程序
            if (appModel != null) {
                var intent = context.packageManager.getLaunchIntentForPackage(appModel.applicationPackageName)
                if (intent != null) {
                    context.startActivity(intent)
                }
            }
        }
        return viewHolder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageDrawable(appList[position].getIcon())
        holder.textView.text= appList[position].appLabel

        Log.i(tag,"标签 是： ${appList[position].appLabel}")
    }

    /**
     * 内部类ViewHolder
     */
    inner class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        var imageView = rootView.item_iv
        var textView = rootView.item_tv
    }
    /**
     * 添加数据的方法
     */
    fun addData(list: ArrayList<AppModel>) {
        appList.clear()
        appList.addAll(list)
        notifyDataSetChanged()
    }
}
