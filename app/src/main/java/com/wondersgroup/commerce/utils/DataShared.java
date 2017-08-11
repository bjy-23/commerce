package com.wondersgroup.commerce.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yclli on 2015/11/19.
 */
public class DataShared {

    private static final String FILE_NAME = "commerce";

    private static SharedPreferences mySharedPreferences;
    private static SharedPreferences.Editor editor;

    public DataShared(Context context){
        mySharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = mySharedPreferences.edit();
    }

    public static void put(String key, Object object){

        if (object instanceof String)
        {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer)
        {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean)
        {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float)
        {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long)
        {
            editor.putLong(key, (Long) object);
        } else
        {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }

    public static Object get(String key, Object defaultObject){
        if (defaultObject instanceof String)
        {
            return mySharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer)
        {
            return mySharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean)
        {
            return mySharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float)
        {
            return mySharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long)
        {
            return mySharedPreferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    public static void remove(String key)
    {
        if(contains(key)){
            editor.remove(key);
            SharedPreferencesCompat.apply(editor);
        }
    }

    public static void clear()
    {
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    public static boolean contains(String key)
    {
        return mySharedPreferences.contains(key);
    }


    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     *
     */
    private static class SharedPreferencesCompat
    {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({ "unchecked", "rawtypes" })
        private static Method findApplyMethod()
        {
            try
            {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e)
            {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor)
        {
            try
            {
                if (sApplyMethod != null)
                {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e)
            {
            } catch (IllegalAccessException e)
            {
            } catch (InvocationTargetException e)
            {
            }
            editor.commit();
        }
    }
}
