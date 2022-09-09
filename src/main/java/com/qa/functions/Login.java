package com.qa.functions;

import com.qa.data.User;
import com.qa.pages.AbstractPages.LoginPage;

public class Login extends FunctionBase {
    private static LoginPage loginPage = PageFactory.getInstance(LoginPage.class);

    public static void loadApp() {

    }

    public static void loginToApp(User user) {
        loginPage.enterUserName(user.userName);
        loginPage.enterPassword(user.password);
        loginPage.clickSignIn();
    }

    public static void logout() {
        loginPage.clickLogOut();
    }

    public static boolean isUserNameFieldDisplayed() {
        return loginPage.isUserNameFieldDisplayed();
    }

}
