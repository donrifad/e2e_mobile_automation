package com.qa.utils;

import com.qa.common.ConfigPropertReader;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class CapabilityUtil {

    public static DesiredCapabilities getAndroidCapabilities(File appDir, String appName) {
        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("LOCAL")) {
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigPropertReader.getProperty("device"));
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigPropertReader.getProperty("platform"));
            capabilities.setCapability(MobileCapabilityType.TAKES_SCREENSHOT, "true");
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_bs")) {
            capabilities.setCapability("browserstack.user", ConfigPropertReader.getProperty("browserstack.user"));
            capabilities.setCapability("browserstack.key", ConfigPropertReader.getProperty("browserstack.key"));
            capabilities.setCapability("app", ConfigPropertReader.getProperty("android.app"));
            // Specify device and os_version for testing
            capabilities.setCapability("device", ConfigPropertReader.getProperty("device"));
            capabilities.setCapability("os_version", ConfigPropertReader.getProperty("os_version"));
            capabilities.setCapability("autoDismissAlerts", false);
            capabilities.setCapability("browserstack.gpsLocation", ConfigPropertReader.getProperty("gps.location"));
            // Set other BrowserStack capabilities
            capabilities.setCapability("project", ConfigPropertReader.getProperty("project"));
            capabilities.setCapability("build", ConfigPropertReader.getProperty("build"));
            capabilities.setCapability("name", ConfigPropertReader.getProperty("name"));
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_head_spin")) {
            // Specify device and os_version for testing
            capabilities.setCapability("deviceName", ConfigPropertReader.getProperty("head.spin.device"));
            capabilities.setCapability("autoDismissAlerts", true);
            capabilities.setCapability("udid", ConfigPropertReader.getProperty("device.udid"));
            capabilities.setCapability("automationName", "UiAutomator2");
            // capabilities.setCapability("platformName", ConfigPropertReader.getProperty("os_version"));
            capabilities.setCapability("appPackage", "<app package>");
            capabilities.setCapability("appActivity", "<app activity>");
            // we've issue with proxy headspin is using causing some calls fail with TLS Exceptions
            // so don't enabled headspin:capture
            capabilities.setCapability("headspin:capture", "true");
            capabilities.setCapability("headspin:capture.video", "true");
            capabilities.setCapability("headspin:testName", ConfigPropertReader.getProperty("name"));
        }

        return capabilities;
    }

    public static DesiredCapabilities getIOSCapabilities(File appDir, String appName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        File app = new File(appDir, appName);
        if(ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("local")){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
            capabilities.setCapability("useNewWDA", false);
            capabilities.setCapability("platformVersion", "12.0.1");
            capabilities.setCapability("deviceName", "iPhone 8");
            capabilities.setCapability("udid", "auto");
            capabilities.setCapability("bundleId", "<your bundle id>");
            capabilities.setCapability("xcodeOrgId", "<your org id>");
            capabilities.setCapability("xcodeSigningId", "iPhone Developer");
            capabilities.setCapability("updatedWDABundleId", "<bundle id in scope of provisioning profile>");
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        }
        else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_bs")) {
            capabilities.setCapability("browserstack.user", ConfigPropertReader.getProperty("browserstack.user"));
            capabilities.setCapability("browserstack.key", ConfigPropertReader.getProperty("browserstack.key"));

            // Set URL of the application under test
            capabilities.setCapability("app", ConfigPropertReader.getProperty("ios.app"));

            // Specify device and os_version for testing
            capabilities.setCapability("device", ConfigPropertReader.getProperty("ios.device"));
            capabilities.setCapability("os_version", ConfigPropertReader.getProperty("ios.version"));
            capabilities.setCapability("autoAcceptAlerts", ConfigPropertReader.getProperty("autoAcceptAlerts"));
            capabilities.setCapability("deviceOrientation", ConfigPropertReader.getProperty("screen.mode"));
            capabilities.setCapability("browserstack.gpsLocation", ConfigPropertReader.getProperty("gps.location"));
            // Set other BrowserStack capabilities
            capabilities.setCapability("project", ConfigPropertReader.getProperty("project"));
            capabilities.setCapability("build", ConfigPropertReader.getProperty("build"));
            capabilities.setCapability("name", ConfigPropertReader.getProperty("name"));
        } else if (ConfigPropertReader.getProperty("run.mode").equalsIgnoreCase("remote_head_spin")) {
            // Specify device and os_version for testing
            capabilities.setCapability("deviceName", ConfigPropertReader.getProperty("ios.head.spin.device"));
            capabilities.setCapability("udid", ConfigPropertReader.getProperty("ios.head.spin.device.udid"));
            capabilities.setCapability("automationName", "XCUITest");
            capabilities.setCapability("bundleId", ConfigPropertReader.getProperty("bundleId"));
            // we've issue with proxy headspin is using causing some calls fail with TLS Exceptions
            // so don't enabled headspin:capture
            //capabilities.setCapability("headspin:capture", "true");
            capabilities.setCapability("headspin:capture.video", "true");
            capabilities.setCapability("headspin:testName", ConfigPropertReader.getProperty("name"));
            capabilities.setCapability("autoAcceptAlerts", ConfigPropertReader.getProperty("autoAcceptAlerts"));
            //capabilities.setCapability("fullReset", true);
            //capabilities.setCapability("autoDismissAlerts", true);
            //capabilities.setCapability("headspin:restartDeviceOnSessionStart", true);
            //capabilities.setCapability("resetOnSessionStartOnly", true);
            //capabilities.setCapability("headspin:appId", ConfigPropertReader.getProperty("headspin.appId"));
        }
        return capabilities;
    }

}
