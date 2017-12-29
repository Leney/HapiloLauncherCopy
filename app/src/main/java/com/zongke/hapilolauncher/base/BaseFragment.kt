package com.zongke.hapilolauncher.base

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ${xingen} on 2017/7/5.
 */

abstract class BaseFragment : Fragment() {
    private lateinit var rootView: View
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.rootView = inflater!!.inflate(layoutId, container, false)
        return rootView
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAfterActivityCreated(rootView, savedInstanceState)
    }

    /**
     * 获取制定的布局id
     * @return
     */
    protected abstract val layoutId: Int

    /**
     * Activity被创建后
     * @param rootView
     * *
     * @param savedInstanceState
     */
    protected abstract fun initAfterActivityCreated(rootView: View, savedInstanceState: Bundle?)
}
