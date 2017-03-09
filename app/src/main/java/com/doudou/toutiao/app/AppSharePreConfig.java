package com.doudou.toutiao.app;

import android.content.Context;
import android.content.SharedPreferences;

import static com.doudou.toutiao.app.AppConfig.APP_NAME;

/**
 * Created by Administrator on 2017/2/23.
 */

public class AppSharePreConfig {

    static SharedPreferences.Editor mEditor;
    static SharedPreferences mPreference;

    static final String SHAREPREFERENCE_NAME = "sp_" + APP_NAME;
    static final String FIRST_START = "is_first_start";

    public static SharedPreferences getSharedPreference () {
        return Appcontext.getInstance().getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor () {
        return Appcontext.getInstance().getSharedPreferences(SHAREPREFERENCE_NAME, Context.MODE_PRIVATE).edit();
    }

    /**
     * 获取是否是第一次启动app
     * @return false：第一次启动app， true：不是第一次启动
     */
    public static boolean getIsFirstStart () {
        return getSharedPreference().getBoolean(FIRST_START, false);
    }

    /**
     * 设置是否是第一次启动app
     * @param isFirstStart true： 不是第一次启动， false：第一次启动
     */
    public static void setIsFirstStart (Boolean isFirstStart) {
        getEditor().putBoolean(FIRST_START, isFirstStart).commit();
    }
}
