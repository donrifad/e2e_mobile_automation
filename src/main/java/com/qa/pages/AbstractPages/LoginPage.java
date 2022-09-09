package com.qa.pages.AbstractPages;

import com.qa.pages.DriverPageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;


import java.util.HashMap;

import static com.qa.common.GlobalConstants.DEFAULT_TIMEOUT;

public abstract class LoginPage extends DriverPageUtil {
    protected By btnGetStarted;
    protected By alertAllow;
    protected By alertDontAllow;
    protected By btnContinue;
    protected By btnSkip;
    protected By btnAllow;
    protected By chkbxAllow;
    protected By txtToRead;
    protected By txtUsername;
    protected By txtPassword;
    protected By signIn;
    protected By btnLogout;
    protected By btnHomeSignIn;
    protected By alertOkWhileUsingApp;
    protected By btnCreateAccount;

    //select language
    protected By lblSelectYourLanguage;
    protected By lblLanguage;
    protected By lblRegion;

    protected By lblLocation;
    protected By btnLblSignIn;
    protected By btnJoinChallenge;

    protected By alertAllowOnlyThisTime;
    protected By alertDeny;
    protected By lblNotfication;

    protected By lnkPrivacyPolicy;
    protected By lnkTermsOfUse;
    protected By lnkForgotPassword;
    protected By txtDontHaveAccountyet;
    protected By btnClose;
    protected By btnCloseCreateAccount;

    public abstract void clickOnAlert(String approval, String alertName);

    public abstract void clickOnAlert(String approval, String alertName, boolean isApproval);

    public abstract void clickAlertOkWhileUsingApp();

    public abstract void scrollToSignInButton();

    public void enterUserName(String userName) {
        enterText(txtUsername, userName);
    }

    public void enterPassword(String pw) {
        enterText(txtPassword, pw, (int) DEFAULT_TIMEOUT);
    }

    public void clickSignIn() {
        clickElement(signIn, DEFAULT_TIMEOUT);
    }

    public void clickLogOut() {
        scrollToAndClick(btnLogout, "down");
    }

    public boolean isUserNameFieldDisplayed() {
        return isDisplayed(txtUsername, (int) DEFAULT_TIMEOUT);
    }

    //IOS Default
    public void clickHomeSignIn() {
        scrollToAndClick(signIn, "down");
    }

    protected void clickAlert(String approval) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> tapObject = new HashMap<String, String>();
        tapObject.put("action", "accept");
        tapObject.put("label", approval);
        js.executeScript("mobile:alert", tapObject);
    }

}