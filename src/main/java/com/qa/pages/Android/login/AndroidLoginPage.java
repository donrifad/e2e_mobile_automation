package com.qa.pages.Android.login;

import com.qa.common.ConfigPropertReader;
import com.qa.pages.AbstractPages.LoginPage;
import io.appium.java_client.MobileBy;

public class AndroidLoginPage extends LoginPage {

    public AndroidLoginPage() {

        alertAllow = MobileBy.name("OK");
        alertDontAllow = MobileBy.xpath("XCUIElementTypeButton[@name='Don’t Allow']");
        txtUsername = MobileBy.id("com.harley_davidson.ride_planner:id/emailField");
        txtPassword = MobileBy.id("com.harley_davidson.ride_planner:id/passwordField");
        signIn = MobileBy.id("com.harley_davidson.ride_planner:id/loginButton");
        btnLogout = MobileBy.xpath("//*[@Resource-Id='com.harley_davidson.ride_planner:id/logoutButton']");
        alertOkWhileUsingApp = MobileBy.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
        btnHomeSignIn = MobileBy.xpath("//*[@Resource-Id=com.harley_davidson.ride_planner:id/bikesEmptyButton]");
    }

    public void clickOnAlert(String approval, String alertName) {
    }

    public void clickOnAlert(String approval, String alertName, boolean isApproval) {
        if (isApproval) {
            try {
                clickElement(alertOkWhileUsingApp, 5);
                System.out.println("Successfully selected 'Allow while using app' permission");
            } catch (Exception e) {
                System.out.println("Unable to click 'Allow while using app' permission. Trying allow one time");
                clickElement(MobileBy.id("com.android.packageinstaller:id/permission_allow_button"));
            }
        }
    }

    public void clickLogOut() {
        androidScrollToAndClick("LOGOUT");
    }

    public void clickHomeSignIn() {
        scrollToIdAndClick("com.harley_davidson.ride_planner:id/bikesEmptyButton");
    }

    public void clickAlertOkWhileUsingApp() {
        try {
            if (Integer.parseInt(ConfigPropertReader.getProperty("os_version")) < 10) {
                clickElement(MobileBy.xpath("//*[@text='ALLOW']"));
            } else {
                try {
                    clickElement(alertOkWhileUsingApp, 5);
                    System.out.println("Successfully selected 'Allow while using app' permission");
                } catch (Exception e) {
                    System.out.println("Alert Allow Location not displayed");
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to click 'Allow while using app' permission. Trying allow one time");
            try {
                clickElement(MobileBy.id("com.android.packageinstaller:id/permission_allow_one_time_button"));
            } catch (Exception e1) {
                System.out.println("Alert allow location permission not present; It is OK. Ignore and proceed");
            }
        }
    }

    public void scrollToSignInButton() {
        androidScrollToText("SIGN IN");
    }

    public void clickAlertDeny() {
        try {
            if (Integer.parseInt(ConfigPropertReader.getProperty("os_version")) < 10) {
                clickElement(MobileBy.xpath("//*[@text='Deny']"));
            } else {
                try {
                    clickElement(alertDeny, 5);
                    System.out.println("Successfully selected 'Allow while using app' permission");
                } catch (Exception e) {
                    System.out.println("Alert Allow Location not displayed");
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to click 'Allow while using app' permission. Trying allow one time");
            try {
                clickElement(MobileBy.id("com.android.packageinstaller:id/permission_allow_one_time_button"));
            } catch (Exception e1) {
                System.out.println("Alert allow location permission not present; It is OK. Ignore and proceed");
            }
        }
    }

}
