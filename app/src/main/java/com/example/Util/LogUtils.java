package com.example.Util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Log工具，类似android.util.Log。 tag自动产生，格式:
 * customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * <p/>
 * Author: wyouflf Date: 13-7-24 Time: 下午12:23
 */
public class LogUtils {

	public static String customTagPrefix = "";

	private LogUtils() {
	}

	public static boolean allow = true;

	private static String generateTag(StackTraceElement caller) {
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
				.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(),
				caller.getLineNumber());
		tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":"
				+ tag;
		return tag;
	}

	public static void d(String content) {
		if (!allow)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);
		Log.d(tag, content);

	}

	public static void e(String content) {
		if (!allow)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		Log.e(tag, content);

	}

	public static void i(String content) {
		if (!allow)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		Log.i(tag, content);

	}

	public static void v(String content) {
		if (!allow)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		Log.v(tag, content);

	}

	public static StackTraceElement getCallerStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];
	}


	/**
	 * 打印接口调用的堆栈
	 *
	 */
	public static void printStackTrace(){
		Log.d("trace",Log.getStackTraceString(new Throwable()));
	}
}
