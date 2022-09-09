package com.qa.pages.IOS.login;

import com.qa.pages.AbstractPages.LoginPage;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class IOSLoginPage extends LoginPage {

    public IOSLoginPage() {

        btnAllow = MobileBy.name("ALLOW");
        chkbxAllow = MobileBy.xpath("//*[@label='UncheckedBox']");
        txtUsername = MobileBy.name("Enter Email Input");
        txtPassword = MobileBy.name("Enter Password Input");
        signIn = MobileBy.name("SIGN IN");
        btnLogout = MobileBy.name("LOGOUT");
    }

    public void clickOnAlert(String approval, String alertName) {
        try {
            if (findElements(By.xpath("//XCUIElementTypeAlert[contains(@name,\'" + alertName + "\')]")).size() > 0) {
                clickAlert(approval);
            }
        } catch (Exception e) {
            System.out.println("Alert " + alertName + " contains " + approval + " is Not Present");
        }
    }

    public void clickOnAlert(String approval, String alertName, boolean isApproval) {
        try {
            if (findElements(By.xpath("//XCUIElementTypeAlert[contains(@name,\'" + alertName + "\')]")).size() > 0) {
                clickAlert(approval, isApproval);
            }
        } catch (Exception e) {
            System.out.println("Alert Not Present");
        }
    }

    @Override
    public void clickAlertOkWhileUsingApp() {
        try {
            clickElement(alertOkWhileUsingApp);
        } catch (Exception e) {
            System.out.println("Could not click on alert");
            clickOnAlert("Allow While Using App", "Allow", true);
        }
    }

    @Override
    public void scrollToSignInButton() {

    }

    public void clickOnAlert(String approval) {
        clickAlert(approval);
    }


    public void navigateToCreateAccount() {
        scrollToAndClick(btnCreateAccount, "down");
    }

    @Override
    public void clickLogOut() {
        scrollToAndClick(btnLogout, "down");
        if (isDisplayed(btnCloseCreateAccount, 10)) {
            clickElement(btnCloseCreateAccount, 10);
        }
    }
}
