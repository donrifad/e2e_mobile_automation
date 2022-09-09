package com.qa.test.common.smoke;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.annotations.attribute.Attribute;
import com.epam.reportportal.annotations.attribute.Attributes;
import com.qa.data.User;
import com.qa.functions.FunctionBase;
import com.qa.functions.Login;
import com.qa.utils.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

public class LoginTest extends TestBase {
    public User loginUser;

    @BeforeMethod
    public void startApp(Method method) {
        loginUser = User.getUserData();

        //name for BS
        System.setProperty("name", method.getName());
        FunctionBase.startApp(false);
        Login.loadApp();
    }

    @TestCaseId("B58280,B58293")
    @Attributes(attributes = {@Attribute(key = "suite", value = "Smoke shared"),
            @Attribute(key = "feature", value = "Logging in,Logging out"),
            @Attribute(key = "priority", value = "High")})
    @Test(priority = 1)
    public void testUserIsAbleToLoginWithoutAnyIssues() throws InterruptedException, IOException, URISyntaxException {
        SoftAssert softAssert = new SoftAssert();
        Login.loginToApp(loginUser);
        Login.logout();
        softAssert.assertTrue(Login.isUserNameFieldDisplayed(), "User Name field should displayed");
        softAssert.assertAll();
    }
}
