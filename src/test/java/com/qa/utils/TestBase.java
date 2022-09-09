package com.qa.utils;

import com.epam.reportportal.annotations.TestCaseId;
import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.qa.common.ConfigPropertReader;
import com.qa.functions.FunctionBase;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(ReportPortalTestNGListener.class)
public class TestBase extends ReportPortalTestNGListener {

    @AfterMethod
    public void closeApp(ITestResult result, Method method) {
        System.setProperty("install.ios_app.headspin", "false");
        FunctionBase.closeApp();
        if (Boolean.parseBoolean(ConfigPropertReader.getProperty("tr.updateTestCase"))) {
            Arrays.stream(
                    method.getAnnotation(TestCaseId.class).value().split(","))
                    .forEach(id -> {
                        try {
                            TestRailUtil.updateTestCase(id.substring(1), result.getStatus());
                            System.out.println("Successfully updated status in TestRail for testcase id: " + id);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
