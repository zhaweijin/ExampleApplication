package com.example.Util;

import android.text.TextUtils;


public final class CarterLogger {

    private static final String TAG = "Demo";

    /**
     * Set true or false if you want read logs or not
     */
    private static boolean logEnabled_d = true;
    private static boolean logEnabled_i = true;
    private static boolean logEnabled_e = true;
    private static boolean logEnabled_v = true;

    public static void v() {
        if (logEnabled_v) {
            android.util.Log.v(TAG, getLocation());
        }
    }

    public static void v(String msg) {
        if (logEnabled_v) {
            android.util.Log.v(TAG, getLocation() + msg);
        }
    }


    public static void d() {
        if (logEnabled_d) {
            android.util.Log.d(TAG, getLocation());
        }
    }

    public static void d(String msg) {
        if (logEnabled_d) {
            android.util.Log.d(TAG, getLocation() + msg);
        }
    }

    public static void i(String msg) {
        if (logEnabled_i) {
            android.util.Log.i(TAG, getLocation() + msg);
        }
    }

    public static void i() {
        if (logEnabled_i) {
            android.util.Log.i(TAG, getLocation());
        }
    }

    public static void e(String msg) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation() + msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation() + msg, e);
        }
    }

    public static void e(Throwable e) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation(), e);
        }
    }

    public static void e() {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation());
        }
    }

    private static String getLocation() {
        final String className = CarterLogger.class.getName();
        final StackTraceElement[] traces = Thread.currentThread()
                .getStackTrace();

        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":"
                                + trace.getMethodName() + ":"
                                + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }

}
