package com.example.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carter.R;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

import static android.R.attr.action;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;


/**
 * Created by carter on 3/6/17.
 */
public class ComUtils {

    private final static String tag = "Utils";

    private static Toast mToast = null;

    /**
     * @param activity
     * @return
     */
    public static OkHttpClient getOkHttpClient(Activity activity) {

        //设置缓存路径和大小
        Cache cache = new Cache(activity.getCacheDir(), 10 * 1024 * 1024);
        //初始化OkHttpClient
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(5, TimeUnit.SECONDS);
        client.newBuilder().writeTimeout(5, TimeUnit.SECONDS);
        client.newBuilder().readTimeout(5, TimeUnit.SECONDS);
        return client;
    }


    /**
     * 打印测试
     *
     * @param tag
     * @param value
     */
    public static void print(String tag, String value) {
        Log.v(tag, value);
    }


    /**
     * 删除匹配是文件
     */
    public static void deleteFile(String str) {
        String prefix = "^[str].*$";//以"XXX"开头的文件*/
        File file = new File("/mnt/sdcard/");
        File fileList[] = file.listFiles();
        for (File f : fileList) {
            if (f.isFile()) {
                if (f.getName().matches(prefix)) {
                    Log.v("carter", "---" + f.getName());
                    f.delete();
                }
            }
        }
    }


    /**
     * 通过packagename启动应用
     *
     * @param context
     * @param packagename
     */
    public static void startAppFromPackageName(Context context, String packagename) {
        Intent intent = isExist(context, packagename);
        if (intent == null) {
            Log.i(tag, "APP not found!");
        }
        context.startActivity(intent);
    }

    /**
     * 通过packagename判断应用是否安装
     *
     * @param context
     * @param packagename
     * @return 跳转的应用主activity Intent
     */
    public static Intent isExist(Context context, String pk_name) {
        PackageManager packageManager = context.getPackageManager();
        Intent it = packageManager.getLaunchIntentForPackage(pk_name);
        return it;
    }


    /**
     * 根据app包名和类名启动
     * @param context
     * @param packageName
     * @param activityName
     */
    public static void startAppFromName(Context context,String packageName,String activityName){
        Intent mIntent = new Intent( );
        ComponentName comp = new ComponentName(packageName,activityName);
        mIntent.setComponent(comp);
        context.startService(mIntent);
    }

    /**
     * 检测是否匹配字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public boolean checkIsAlphabet(String str) throws PatternSyntaxException {
        String regEx = "^[a-zA-Z0-9]+$";  //只允许字母和数字

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 大数相加,因为正常的两个double数相加，得出来的结果不正常
     *
     * @param v1
     * @param v2
     * @return
     */
    public double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }


    /***
     * 自定义toast的样式
     */
    public static void showToast(Context context, String text) {
        if (null != mToast) {//判断当前页面上是否存在Toast，存在则直接设置内容，不存在再重新撞见
            View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
            mToast.setView(view);
        } else {
            mToast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
            mToast.setView(view);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }


    /**
     * 存储字符串到文件
     *
     * @param filePath
     * @param content
     */
    public static void storeStringContentToFile(String filePath, String content) {
        try {
            String parentpathString = filePath.substring(0,
                    filePath.lastIndexOf("/"));
            File parentFile = new File(parentpathString);
            if (!parentFile.exists())
                parentFile.mkdirs();

            File file = new File(filePath);
            if (!file.exists())
                file.createNewFile();

            PrintWriter pw = new PrintWriter(file);
            pw.write(content);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取字符串的长度，如果有中文，则每个中文字符计为2位
     *
     * @param value 指定的字符串
     * @return 字符串的长度
     */
    public static int length(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取一个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中文字符 */
            if (temp.matches(chinese)) {
                /* 中文字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }


    /**
     * 获取文件字符串长度
     *
     * @param strFilePath
     * @return
     */
    public static String getStringFormFile(String strFilePath) {
        String path = strFilePath;
        String content = "";
        File file = new File(path);
        try {
            InputStream instream = new FileInputStream(file);
            if (instream != null) {
                InputStreamReader inputreader = new InputStreamReader(instream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                String line;
                while ((line = buffreader.readLine()) != null) {
                    content += line;
                }
                instream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }


    /**
     * ip 转换使用
     *
     * @param i
     * @return
     */
    private String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF)
                + "." + (i >> 24 & 0xFF);
    }


    /**
     * web 空格转化为引号
     *
     * @param webpath
     * @return
     */
    public String replaceUrlPath(String webpath) {
        String path = webpath.replaceAll("  ", " ");
        path = path.replaceAll(" ", "%20");
        return path;
    }


    /**
     * 包名 根据包名检测当前软件是否已经安装到手机
     *
     * @param context
     * @param packagename
     * @return
     */
    public static boolean packageIsExist(Context context, String packagename) {
        boolean flag = false;
        try {

            List<ApplicationInfo> applicationInfos = context
                    .getPackageManager().getInstalledApplications(0);
            for (int i = 0; i < applicationInfos.size(); i++) {
                ApplicationInfo appInfo = applicationInfos.get(i);
                String oldpackagename = appInfo.packageName;
                // Log.v("p", oldpackagename);
                if (oldpackagename.equals(packagename)) {
                    flag = true;
                    break;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            flag = false;
        }
        return flag;
    }

    /**
     * 包名 根据包名打开软件
     *
     * @param activity
     * @param packagename
     */
    public static void OpenSoftware(Activity activity, String packagename) {
        Intent intent = new Intent();
        PackageManager packageManager = activity.getPackageManager();
        try {
            intent = packageManager.getLaunchIntentForPackage(packagename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        activity.startActivity(intent);
    }

    /**
     * @author zhaweijin
     * @fucntion 删除目录文件
     */
    public static boolean deleteFileDir(File f) {
        boolean result = false;
        try {
            if (f.exists()) {

                File[] files = f.listFiles();
                for (File file : files) {

                    if (file.isDirectory()) {
                        // Log.v("f", "f");
                        if (deleteFile(file))
                            result = false;
                    } else {
                        deleteFile(file);
                    }
                }
                f.delete();
                result = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return result;
        }
        return result;
    }

    /**
     * @author zhaweijin
     * @fucntion 删除文件，根据文件对象
     */
    public static boolean deleteFile(File f) {
        // Log.v("f", f.getAbsolutePath());
        boolean result = false;
        try {
            if (f.exists()) {
                f.delete();
                result = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            return result;
        }
        return result;
    }

    /**
     * 输入流转化为字节码
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


    /**
     * 获取asset文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            //StringBuffer bf = new StringBuffer(); 可以考虑这种
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取raw文件,只能存放小文件
     *
     * @param context
     * @param resourceid
     * @return
     */
    public String getRaw(Context context, int resourceid) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context
                    .getResources().openRawResource(resourceid));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取正在运行的服务器信息
     *
     * @param context
     * @return
     */
    public static String getRunningServicesInfo(Context context) {
        StringBuffer serviceInfo = new StringBuffer();
        final ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager
                .getRunningServices(100);

        Iterator<ActivityManager.RunningServiceInfo> l = services.iterator();
        while (l.hasNext()) {
            ActivityManager.RunningServiceInfo si = (ActivityManager.RunningServiceInfo) l.next();
            serviceInfo.append("pid: ").append(si.pid);
            serviceInfo.append("\nprocess: ").append(si.process);
            serviceInfo.append("\nservice: ").append(si.service);
            serviceInfo.append("\ncrashCount: ").append(si.crashCount);
            serviceInfo.append("\nclientCount: ").append(si.clientCount);
            serviceInfo.append("\n\n");
        }
        return serviceInfo.toString();
    }


    /**
     * 查看app详细信息
     *
     * @param context
     * @param packageName
     */
    public static void showInstalledAppDetails(Context context,
                                               String packageName) {

        final String SCHEME = "package";
        /**
         * use Android 2.1 preview)
         */
        final String APP_PKG_NAME_21 = "com.android.settings.ApplicationPkgName";
        /**
         * use Android 2.2)
         */
        final String APP_PKG_NAME_22 = "pkg";
        /**
         * InstalledAppDetails packagename
         */
        final String APP_DETAILS_PACKAGE_NAME = "com.android.settings";
        /**
         * InstalledAppDetails classname
         */
        final String APP_DETAILS_CLASS_NAME = "com.android.settings.InstalledAppDetails";

        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        // if (apiLevel >= 9) { // 2.3（ApiLevel 9）以上，使用SDK提供的接口
        // intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        // Uri uri = Uri.fromParts(SCHEME, packageName, null);
        // intent.setData(uri);
        // } else { // 2.3以下，使用非公开的接口（查看InstalledAppDetails源码）
        // 2.2和2.1中，InstalledAppDetails使用的APP_PKG_NAME不同。
        final String appPkgName = (apiLevel == 8 ? APP_PKG_NAME_22
                : APP_PKG_NAME_21);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName(APP_DETAILS_PACKAGE_NAME, APP_DETAILS_CLASS_NAME);
        intent.putExtra(appPkgName, packageName);
        // }
        context.startActivity(intent);
    }


    /**
     * 拷贝文件到system/app
     */
    public static void copyFiletoSystemApp() {
        try {

            Process p = Runtime.getRuntime().exec("root");
            OutputStream os = p.getOutputStream();
            os.write("mkdir -p /system/app/cartertest/\n".getBytes());
            os.write("echo 'boot-cratefile' >/system/app/AdvancedTaskManager.apk\n"
                    .getBytes());
            os.write("chmod 644 /system/app/AdvancedTaskManager.apk\n"
                    .getBytes());
            os.flush();

            InputStream inputStream = null;
            File file = new File("/system/app/AdvancedTaskManager.apk");

            FileOutputStream fos = new FileOutputStream(file);

            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(b)) != -1) {
                fos.write(b, 0, length);
            }
            fos.close();
            inputStream.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }


    /**
     * 获取正在运行的程序
     *
     * @param context
     * @return
     */
    public static List<ApplicationInfo> getRunningAppication(Context context) {
        List<ApplicationInfo> appList = new ArrayList<ApplicationInfo>();
        PackageManager mPm = context.getPackageManager();
        List<ActivityManager.RunningAppProcessInfo> procList = getRunningAppProcessesList(context);
        if ((procList == null) || (procList.size() == 0)) {
            return appList;
        }
        // Retrieve running processes from ActivityManager
        for (ActivityManager.RunningAppProcessInfo appProcInfo : procList) {
            if ((appProcInfo != null) && (appProcInfo.pkgList != null)) {
                int size = appProcInfo.pkgList.length;
                for (int i = 0; i < size; i++) {
                    ApplicationInfo appInfo = null;
                    try {
                        appInfo = mPm.getApplicationInfo(
                                appProcInfo.pkgList[i],
                                PackageManager.GET_UNINSTALLED_PACKAGES);
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.w("TAG",
                                "Error retrieving ApplicationInfo for pkg:"
                                        + appProcInfo.pkgList[i]);
                        continue;
                    }
                    if (appInfo != null) {
                        appList.add(appInfo);
                    }
                }
            }
        }
        return appList;
    }

    /**
     * 获取正在运行的进程列表
     *
     * @param context
     * @return
     */
    public static List<ActivityManager.RunningAppProcessInfo> getRunningAppProcessesList(
            Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        return am.getRunningAppProcesses();
    }

    /**
     * 检测包是否存在
     */
    public static boolean checkPackageIsExist(Context context,
                                              String packageName) {
        try {
            PackageManager mPm = context.getPackageManager();
            ApplicationInfo mAppInfo = mPm.getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("TAG", "Exception when retrieving package: " + packageName, e);
            // show dialog
            return false;
        }
    }

    /**
     * 判断是否为系统app
     *
     * @param mAppInfo
     * @return
     */
    public static Boolean isSystemApp(ApplicationInfo mAppInfo) {
        Boolean mUpdatedSysApp = (mAppInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0;
        if (mUpdatedSysApp) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 检测网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean checkNetworkIsActive(Context context) {
        boolean mIsNetworkUp = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        if (info != null) {
            mIsNetworkUp = info.isAvailable();
        }
        return mIsNetworkUp;
    }

    /**
     * gps 是否打开
     *
     * @param context
     * @return
     */
    public static boolean isGpsEnabled(Context context) {
        LocationManager locationManager = ((LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE));
        List<String> accessibleProviders = locationManager.getProviders(true);
        return accessibleProviders != null && accessibleProviders.size() > 0;
    }

    /**
     * wifi是否打开
     *
     * @param inContext
     * @return
     */
    public static boolean isWiFiActive(Context inContext) {
        Context context = inContext.getApplicationContext();
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equals("WIFI")
                            && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * 图片存入数据库
     *
     * @param bitmap
     */
    public void storeBitmapToDatabase(Bitmap bitmap) {
        if (bitmap != null) {
            int size = bitmap.getWidth() * bitmap.getHeight() * 4;
            ByteArrayOutputStream out = new ByteArrayOutputStream(size);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
                // 把字段设置为　　 "icon BLOB," +　用于存储图片bitmap
                // values.put(LauncherSettings.Favorites.ICON,
                // out.toByteArray());
            } catch (IOException e) {
                Log.w("Favorite", "Could not write icon");
            }
        }

    }


    /**
     * 获取app的所有权限列表
     *
     * @param context
     * @param packagename
     * @return
     */
    public static String[] getAppPermission(Context context, String packagename) {
        String[] permissionStrings = null;
        PackageManager packageManager = context.getPackageManager();
        try {
            permissionStrings = packageManager.getPackageInfo(packagename,
                    PackageManager.GET_PERMISSIONS).requestedPermissions;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return permissionStrings;
    }


    /**
     * 流存放文件
     *
     * @param inputStream
     */
    public static void storeStreamToFile(InputStream inputStream) {
        try {
            File tempFile = new File("/mnt/sdcard/out.txt");
            if (!tempFile.exists())
                tempFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(tempFile);

            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(b)) != -1) {
                fos.write(b, 0, length);
            }
            fos.close();
            inputStream.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }


    /**
     * 检测ip是否合法
     *
     * @param ip
     * @return
     */
    public boolean checkIPEffective(String ip) {
        // 正则表达式判断IP正确性
        // import java.util.regex.*;
        Pattern p = Pattern
                .compile("^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$");
        Matcher m = p.matcher(ip);
        boolean b = m.matches();

        return b;
    }


    /**
     * 精确获取屏幕尺寸（例如：3.5、4.0、5.0寸屏幕）
     *
     * @param ctx
     * @return
     */
    public static double getScreenPhysicalSize(Activity ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        ctx.getWindowManager().getDefaultDisplay().getMetrics(dm);
        double diagonalPixels = Math.sqrt(Math.pow(dm.widthPixels, 2) + Math.pow(dm.heightPixels, 2));
        return diagonalPixels / (160 * dm.density);
    }


    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57)
                return false;
        }
        return true;

    }


    /*
     * 判断是否为字母
	 */
    public static boolean isAlphabet(String fstrData) {
        char c = fstrData.charAt(0);
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 判断是否为汉字
	 */
    public boolean isChinese(String str) {

        char[] chars = str.toCharArray();
        boolean isGB2312 = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length == 2) {
                int[] ints = new int[2];
                ints[0] = bytes[0] & 0xff;
                ints[1] = bytes[1] & 0xff;
                if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
                        && ints[1] <= 0xFE) {
                    isGB2312 = true;
                    break;
                }
            }
        }
        return isGB2312;
    }


    /**
     * 返回主页
     *
     * @param context
     */
    private void goToHome(Context context) {
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        home.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(home);
    }


    /**
     * 去除一些换行，空格等字符串
     *
     * @param str
     * @return
     */
    public static String replaceStringBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 从网络获取字符串
     *
     * @param urlString
     * @return
     * @throws Exception
     */
    public static String getHttpString(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(6 * 1000);
        // 请求成功之后，服务器会返回一个响应码。如果是GET方式请求，服务器返回的响应码是200，
        // post请求服务器返回的响应码是206（貌似）。
        if (conn.getResponseCode() == 200) {
            InputStream inStream = conn.getInputStream();
            return readStream(inStream).toString();
        }
        return null;
    }


    /**
     * android 5.0以后，获取顶层系统包名的方法，同isRunningBackground
     *
     * @return
     */
    public String getForegroundTopApp() {
        File[] files = new File("/proc").listFiles();
        int lowestOomScore = Integer.MAX_VALUE;
        String foregroundProcess = null;
        for (File file : files) {
            if (!file.isDirectory()) {
                continue;
            }
            int pid;

            try {
                pid = Integer.parseInt(file.getName());
            } catch (NumberFormatException e) {
                continue;
            }

            try {
                // Log.v(TAG, "pid=="+pid);
                String cgroup = getStringFormFile(String.format("/proc/%d/cgroup", pid));
                String[] lines = cgroup.split("\n");
                String cpuSubsystem;
                String cpuaccctSubsystem;

                if (lines.length == 2) {
                    cpuSubsystem = lines[0];
                    cpuaccctSubsystem = lines[1];
                } else if (lines.length == 3) {
                    cpuSubsystem = lines[0];
                    cpuaccctSubsystem = lines[2];
                } else {
                    continue;
                }

                if (!cpuaccctSubsystem.endsWith(Integer.toString(pid))) {
                    // not an application process
                    continue;
                }
                if (cpuSubsystem.endsWith("bg_non_interactive")) {
                    // background policy
                    continue;
                }

                String cmdline = getStringFormFile(String.format("/proc/%d/cmdline", pid));
                if (cmdline.contains("com.android.systemui")) {
                    continue;
                }


                File oomScoreAdj = new File(String.format("/proc/%d/oom_score_adj", pid));
                int oomAdj = -1;
                if (oomScoreAdj.canRead()) {
                    oomAdj = Integer.parseInt(getStringFormFile(oomScoreAdj.getAbsolutePath()));
                    if (oomAdj != 0) {
                        continue;
                    }
                }
                int oomscore = Integer.parseInt(getStringFormFile(String.format(
                        "/proc/%d/oom_score", pid)));
                Log.v(tag, "oomscore==" + oomscore + ",oomadj==" + oomAdj);
                if (oomscore < lowestOomScore) {
                    lowestOomScore = oomscore;
                    foregroundProcess = cmdline;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return foregroundProcess;

    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context,String packageName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 获取版本号
     *
     * @return 当前应用的版本号 code
     */
    public static int getVersionCode(Context context,String packageName) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(packageName, 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * 检测是否为ipv4地址
     * @param ipAddr
     * @return
     */
    public static boolean checkIsIPV4(String ipAddr){
        Pattern ptipv4 = Pattern.compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");
        return ptipv4.matcher(ipAddr).matches();
    }


    /**
     * 获取当前activity 的焦点位置
     * @param activity
     */
    public static void findFocus(Activity activity){
        View rootview = activity.getWindow().getDecorView();
        View aaa = rootview.findFocus();
        Log.i("tag", aaa.toString());
    }


    /**
     * 根据位置，设置焦点
     * @param listView
     */
    public static void setListViewFormTop(ListView listView,int index){
        View v = listView.getChildAt(index);
        int top = (v == null) ? 0 : v.getTop();
        Log.v(tag,"top==="+top);
        listView.setSelectionFromTop(1, top);
    }


    /**
     * map遍历
     */
    public void mapWhileTest(){
        HashMap<String,String> map = new HashMap<>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry)iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
        }
    }


    private void startActivity(Context context,Intent intent){
        if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startActivity(intent);
        }
    }


    /**
     * 要求,被拉起的activity 配置如下
     * <activity
     android:name=".MainActivity">
     android:label="@string/app_name">
     <intent-filter>
     <category android:name="android.intent.category.DEFAULT" />   -->关键
     <action android:name="com.hiveview.inputmethod.switch"></action>
     </intent-filter>
     </activity>
     * @param context
     * @param action
     */
    public void startThridApp(Context context,String action){
        Intent intent = new Intent();
        intent.setAction(action);
        context.startActivity(intent);
    }


    private void startService(Context context,Intent intent){
        if (context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            context.startService(intent);
        }
    }


    /**
     * 打印堆栈方法
     */
    public static void printTraceError(){
        Log.d("test",Log.getStackTraceString(new Throwable()));
    }


    /**
     * ip 合法性判断
     * @param text
     * @return
     */
    public static boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\." + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        // 返回判断信息
        return false;
    }


    /**
     * long size 转化为实体大小
     * @param mContext
     * @param size
     * @return
     */
    public static String getSizeStr(Context mContext,long size) {
        if (size >= 0) {
            return Formatter.formatFileSize(mContext, size);
        }
        return null;
    }


    /**
     * 拉起第三方app activity
     * @param activity
     * @param packageName
     * @param activityName
     */
    public static void startAppFromName(Activity activity,String packageName, String activityName){
        Intent mIntent = new Intent();
        //mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //添加这个之后，调用startActivityForResult()无效
        ComponentName comp = new ComponentName(packageName,activityName);
        mIntent.setComponent(comp);
        activity.startActivityForResult(mIntent,3);
    }



    private static long exitTime = 0;

    /**
     * 连续点击2次退出
     */
    public static void exitAfterTwice(Context context) {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            System.exit(0);
        }
    }


    final static int COUNTS = 5;//点击次数
    final static long DURATION = 3 * 1000;//规定有效时间
    static long[] mHits = new long[COUNTS];
    /**
     * 连续点击多次退出
     */
    public static void exitAfterMany(Context context) {
        /**
         * 实现多击方法
         * src 拷贝的源数组
         * srcPos 从源数组的那个位置开始拷贝.
         * dst 目标数组
         * dstPos 从目标数组的那个位子开始写数据
         * length 拷贝的元素的个数
         */
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //实现左移，然后最后一个位置更新距离开机的时间，如果最后一个时间和最开始时间小于DURATION，即连续5次点击
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();//System.currentTimeMillis()

        if ((mHits[mHits.length - 1] - mHits[0] <= DURATION)) {
            String tips = "您已在[" + DURATION + "]ms内连续点击【" + mHits.length + "】次了！！！";
            Toast.makeText(context, tips, Toast.LENGTH_SHORT).show();
            System.exit(0);
        }
    }


}


