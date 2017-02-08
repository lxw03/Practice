package com.example.cw.practice.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cw on 2017/2/8.
 */

public class SharedPreferenceUtil {

    public static SharedPreferences.Editor getEditor(String name, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        return editor;
    }

    public static SharedPreferences getSharedPreferences(String name, Context context){
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void setItem(String name, Context context, String key, String value){
        SharedPreferences.Editor editor = getEditor(name, context);
        editor.putString(key, value);
        editor.apply();
    }

    public static String getItem(String name, Context context, String key){
        return  getSharedPreferences(name, context).getString(key, "");
    }
}
