package com.qa.utils;

public class AppiumUtil {
    public static final String APPIUM_HOST = System.getProperty("appium.host", "http://0.0.0.0:4723/wd/hub/");
    public static final String BROWSERSTACK_HOST = System.getProperty("bs.host", "http://hub-cloud.browserstack.com/wd/hub");
    public static final String HEAD_SPIN_HOST = System.getProperty("hp.host", "https://dev-usdsds-sdsdpao-3.headspin.io:7001/v0/7b7146c2a5f9447s785606d998s71c68668ae6/wd/hub");
    public static final String IOS_HEAD_SPIN_HOST = System.getProperty("hp.host", "https://dev-ssdus-pao-5.headspin.io:7010/v0/98b660sdsdsdad236534dfeb71967ad98952695387/wd/hub");
}
