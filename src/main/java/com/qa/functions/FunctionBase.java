package com.qa.functions;

import com.qa.common.ConfigPropertReader;
import com.qa.pages.DriverPageUtil;
import com.qa.utils.DriverSetupUtil;

public class FunctionBase {

    public static void startApp(boolean noResetApp) {
        if (ConfigPropertReader.getProperty("platform").equalsIgnoreCase("Android")) {
            DriverPageUtil.setDriver(DriverSetupUtil.getAndroidDriver(true));
        } else if (ConfigPropertReader.getProperty("platform").equalsIgnoreCase("IOS")) {
            DriverPageUtil.setDriver(DriverSetupUtil.getIosDriver(true));
        }
    }

    public static void closeApp() {
        DriverPageUtil.quiteDriver();
    }

    public static void sleep(int sleepInMilliSeconds) {
        try {
            Thread.sleep(sleepInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
