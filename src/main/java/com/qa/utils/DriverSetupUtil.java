package com.qa.utils;

import com.qa.common.ConfigPropertReader;
import com.qa.pages.DriverPageUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetupUtil {
    static File appDir = new File("apps/");

    public static AndroidDriver getAndroidDriver(boolean noResetApp) {
        DesiredCapabilities capabilities = CapabilityUtil.getAndroidCapabilities(appDir, "YOUR_APP_NAME");
        AndroidDriver<AndroidElement> androidDriver = null;
        String url = "";
        if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote")) {
            url = AppiumUtil.BROWSERSTACK_HOST;
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("local")) {
            url = AppiumUtil.APPIUM_HOST;
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_head_spin")) {
            url = AppiumUtil.HEAD_SPIN_HOST;
        }
        if (DriverPageUtil.getDriver() == null) {
            try {
                androidDriver = new AndroidDriver<AndroidElement>(
                        new URL(url), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            androidDriver = (AndroidDriver) DriverPageUtil.getDriver();
            androidDriver.launchApp();
        }
        return androidDriver;
    }

    public static IOSDriver getIosDriver(boolean noResetApp) {
        DesiredCapabilities capabilities = CapabilityUtil.getIOSCapabilities(appDir, "Test_v4.0_apkpure.com.ipa");
        IOSDriver<IOSElement> iosDriver = null;
        String url = "";
        if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote")) {
            url = AppiumUtil.BROWSERSTACK_HOST;
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_head_spin")) {
            url = AppiumUtil.IOS_HEAD_SPIN_HOST;
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("local")) {
            url = AppiumUtil.APPIUM_HOST;
        }

        if (DriverPageUtil.getDriver() == null) {
            //Install app using install app api of headspin
            try {
                iosDriver = new IOSDriver<IOSElement>(
                        new URL(url), capabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            iosDriver = (IOSDriver) DriverPageUtil.getDriver();
            iosDriver.launchApp();
        }
        return iosDriver;
    }
}
