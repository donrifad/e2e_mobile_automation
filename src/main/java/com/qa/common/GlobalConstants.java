package com.qa.common;

public class GlobalConstants {
    public static final String BROWSER = System.getProperty("test.browser", "ANDROID");
    public static final String RUNNING_MODE = System.getProperty("run.mode", "BROWSER STACK");
    public static final String RUNNING_PLATFORM = System.getProperty("run.platform", "Android");
    public static long DEFAULT_TIMEOUT = Long.parseLong(System.getProperty("test.timeout", "10"));
}
