package com.zongke.hapiloimservice.extra;

import com.google.gson.Gson;

/**
 * Created by ${xingen} on 2017/12/9.
 */

public class ConversionObjectUtils {

    public static <T> T parseJson(String s,Class<T> mClass){
        T t=null;
        try {
           t= new Gson().fromJson(s,mClass);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  t;
    }
    /**
     *
     *
     * @param chatContent
     * @param
     * @return
     */
    public static  ExtraMessage parseJson(String chatContent) {
        ExtraMessage message =null;
        try {
           message= parseJson(chatContent,ExtraMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
    /**
     *  将对象生成String
      * @param object
     * @return
     */
    public static String   fromObjectToString(Object object){
        Gson gson=new Gson();
        String s=null;
        try {
          s=  gson.toJson(object);
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
}
