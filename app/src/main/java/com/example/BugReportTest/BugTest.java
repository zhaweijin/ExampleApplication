package com.example.BugReportTest;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by carter on 2/28/17.
 */
public class BugTest {

    public void bugReport(){
        CrashReport.testJavaCrash();
    }

    public int bugReport2(){
        return 3/0;
    }

}
