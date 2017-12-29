package com.zongke.hapilolauncher.utils

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import java.util.*

/**
 * Created by ${xingen} on 2017/7/26.
 *
 * 字体加载工具类。
 *
 * 多次重复加载一个字体，容易耗掉性能。
 *
 */
object TypefaceUtils {
    /**
     * 站酷快乐体
     */
    const val font_standing_cool="fonts/站酷快乐体2016修订版.ttf"
    /**
     * 思源 Medium字体
     */
   const val font_roboto_mdium="fonts/Roboto-Medium.ttf"
    /**
     * 思源 Bold字体
     */
   const val font_roboto_bold="fonts/Roboto-Bold.ttf"
    /**
     * 用于存储，加载过得字体
     */
    val typefaces=HashMap<String,Typeface>()

    /**
     * 从assert文件下获取字体
     */
    @JvmStatic
    fun getTypeFace(context: Context,typeFaceName:String):Typeface?{
        if (typefaces[typeFaceName]==null){
            typefaces[typeFaceName]= Typeface.createFromAsset(context.assets,typeFaceName)
        }
        return   typefaces[typeFaceName]
    }

    /**
     * 为TextView设置自定义的字体
     */
    @JvmStatic
    fun setCustomFont(textView: TextView, typeFaceName:String){
        textView.typeface= getTypeFace(textView.context,typeFaceName)
    }
}