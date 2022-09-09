package com.qa.pages;

import com.qa.common.ConfigPropertReader;
import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.qa.common.GlobalConstants.DEFAULT_TIMEOUT;
import static io.appium.java_client.ios.touch.IOSPressOptions.iosPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class DriverPageUtil {

    static AppiumDriver<WebElement> driver = null;

    public static AppiumDriver getDriver() {
        return (AppiumDriver) driver;
    }

    public static void setDriver(AppiumDriver driver) {
        DriverPageUtil.driver = driver;
        System.out.println("\n Appium Session id: " + driver.getSessionId().toString());
    }

    public static WebDriver getWebDriver() {
        return driver;
    }

    public static void quiteDriver() {
        driver.quit();
        driver = null;
    }

    protected static void javaScriptTouch(String xpath) {
        String js = "var targetElement = document.evaluate(\"" + xpath + "\",document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null ).singleNodeValue;" +
                "var evt = document.createEvent('UIEvent');"
                + "evt.initUIEvent('touchstart', true, true);"
                + "targetElement.dispatchEvent(evt);";

        ((JavascriptExecutor) driver).executeScript(js);
    }

    protected static void javaScriptTouch(WebElement xpath) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", xpath);
    }

    public static void iosterminateApp(String appBundleId) {
//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        HashMap<String, String> bundleArgs = new HashMap<>();
//        bundleArgs.put("bundleId", appBundleId);
//        js.executeScript("mobile:terminateApp", bundleArgs);
        driver.terminateApp(ConfigPropertReader.getProperty("bundleId"));
    }

    protected void enterText(By by, String text) {
        findElement(by, (int) DEFAULT_TIMEOUT).sendKeys(text);
    }

    protected void enterText(By element, String text, int timeout) {
        ((MobileElement) findElement(element, timeout)).setValue(text);
    }

    protected void clearText(By by) {
        findElement(by, (int) DEFAULT_TIMEOUT).clear();
    }

    // use this method with caution. It is creating staleelement exceptions on some context
    protected void clickElement(By by) {
        try {
            click(by, DEFAULT_TIMEOUT);
        } catch (Exception e) {
            click(by, DEFAULT_TIMEOUT);
        }
    }

    protected void tap(By by) {
        TouchActions action = new TouchActions(driver);
        action.singleTap(findElement(by, (int) DEFAULT_TIMEOUT));
        action.perform();
    }

    protected void doubleTap(By by) {
        TouchActions action = new TouchActions(driver);
        action.doubleTap(findElement(by, (int) DEFAULT_TIMEOUT));
        action.perform();
    }

    protected void doubleClick(By by) {
        Actions action = new Actions(driver);
        action.doubleClick(findElement(by, (int) DEFAULT_TIMEOUT));
        action.perform();
    }

    protected void longPress(By by) {
        TouchActions action = new TouchActions(driver);
        action.longPress(findElement(by, (int) DEFAULT_TIMEOUT));
        action.perform();

    }

    protected WebElement findElement(By by) {
        return driver.findElement(by);
    }

    protected void clickElement(By by, long time) {
        new WebDriverWait(driver, time).until(
                ExpectedConditions.elementToBeClickable(by)).click();
    }

    public boolean isDisplayed(By by) {
        try {
            return this.findElement(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDisplayed(By by, int timeoutInSeconds) {
        try {
            //return this.findElement(by,timeoutInSeconds).isDisplayed();
            waitUntilElementIsVisible(by, timeoutInSeconds);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }

    public boolean isEnabled(By by, int timeoutInSeconds) {
        try {
            return this.findElement(by, timeoutInSeconds).isEnabled();
        } catch (Exception var3) {
            return false;
        }
    }

    public void scrollToBottom() {
        Dimension dim = driver.manage().window().getSize();
        int height = dim.getHeight();
        int width = dim.getWidth();
        int x = width / 2;
        int top_y = (int) (height * 0.80);
        int bottom_y = (int) (height * 0.20);
        System.out.println("coordinates :" + x + "  " + top_y + " " + bottom_y);
        TouchAction ts = new TouchAction(driver);
        ts.press(PointOption.point(x, top_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(x, bottom_y)).release().perform();
    }

    public WebElement waitTillElementLoaded(By by, long timeout) {
        return (WebElement) (new WebDriverWait(driver, timeout)).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitTillElementNotPresent(By by, long timeout) {
        (new WebDriverWait(driver, timeout)).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected WebElement findElement(By by, long timeout) {
        try {
            return new WebDriverWait(getDriver(), timeout).ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            return null;
        }
    }

    protected void scrollToAndClick(By by, String direction) {
        mobileScrollToElement(by, direction);
        clickElement(by);
    }

    /**
     * Use it only for IOS
     *
     * @param by
     * @param direction
     * @return
     */
    protected void mobileScrollToElement(By by, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = findElement(by, (int) DEFAULT_TIMEOUT);
        HashMap scrollObjects = new HashMap();
        scrollObjects.put("direction", direction);
        scrollObjects.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scroll", scrollObjects);
    }

    /**
     * Use it only for IOS
     *
     * @param direction
     * @return
     */
    protected void mobileScrollToElement(String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObjects = new HashMap();
        scrollObjects.put("direction", direction);
        js.executeScript("mobile: scroll", scrollObjects);
    }

    protected void androidScrollToAndClick(String elementText) {
        AndroidDriver driver = (AndroidDriver) getDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + elementText + "\").instance(0))").click();
    }

    public WebElement scrollToId(String id) {
        AndroidDriver driver = (AndroidDriver) getDriver();
        return driver.findElementByAndroidUIAutomator(
                "new UiScrollable(" + "new UiSelector().scrollable(true)).scrollIntoView("
                        + "new UiSelector().resourceIdMatches(\"" + id + "\"));");
    }

    public String getText(By by) {
        try {
            return this.findElement(by, (int) DEFAULT_TIMEOUT).getText();
        } catch (Exception var3) {
            return "Not displayed";
        }
    }

    public String getText(By by, int timeout) {
        try {
            return this.findElement(by, timeout).getText();
        } catch (Exception var3) {
            return "Not displayed";
        }
    }

    public void scrollToIdAndClick(String id) {
        scrollToId(id).click();
    }

    protected List<WebElement> findElements(By by) {
        try {
            return new WebDriverWait(getDriver(), DEFAULT_TIMEOUT).ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            return null;
        }
    }

    protected void clickAlert(String approval) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> tapObject = new HashMap<String, String>();
        tapObject.put("action", "accept");
        tapObject.put("label", approval);
        js.executeScript("mobile:alert", tapObject);
    }

    protected void clickAlert(String approval, boolean isAccept) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> tapObject = new HashMap<String, String>();
        if (isAccept) {
            tapObject.put("action", "accept");
        } else {
            tapObject.put("action", "dismiss");
        }
        tapObject.put("label", approval);
        js.executeScript("mobile:alert", tapObject);
    }

    protected MobileElement androidScrollToText(String text) {
        AndroidDriver driver = (AndroidDriver) getDriver();
        return (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().text(\"" + text + "\"));");
    }

    protected MobileElement androidScrollToTextContains(String text) {
        AndroidDriver driver = (AndroidDriver) getDriver();
        return (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable("
                + "new UiSelector().scrollable(true)).scrollIntoView(" + "new UiSelector().textContains(\"" + text + "\"));");
    }

    public WebElement waitTillElementToClickable(By by, long timeout) {
        return (WebElement) (new WebDriverWait(driver, timeout)).until(ExpectedConditions.elementToBeClickable(by));
    }

    public Alert waitUntilAlertIsPresent(long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilElementIsVisible(By by, long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void clearText(WebElement element) {
        element.clear();
    }

    public void moveToAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(this.findElement(by));
        actions.click().perform();
    }

    protected void mobileTap(By by) {
        new TouchAction((PerformsTouchActions) driver).tap(tapOptions().withElement(element(findElement(by)))).perform();
    }

    protected void iosDoubleTap(By by) {
        IOSTouchAction iosTouchAction = new IOSTouchAction((PerformsTouchActions) driver);
        iosTouchAction.doubleTap(element(findElement(by)));
    }

    protected void touchWithPressure(By by) {
        new IOSTouchAction((PerformsTouchActions) driver)
                .press(iosPressOptions()
                        .withElement(element(findElement(by)))
                        .withPressure(1))
                .waitAction(waitOptions(ofMillis(100)))
                .release()
                .perform();
    }

    protected void tapByCoordinates() {

    }

    protected void pressByCoordinates(By by, long seconds) {
        MobileElement element = (MobileElement) findElement(by);
        new TouchAction((PerformsTouchActions) driver)
                .press(point(element.getCenter().x, element.getCenter().y))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }

    protected void selectDatePickerValue(WebElement element, String order) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Map<String, Object> datePickerProperties = new HashMap<>();
        datePickerProperties.put("order", order);
        datePickerProperties.put("offset", 0.15);
        datePickerProperties.put("element", element);
        jsExecutor.executeScript("mobile: selectPickerWheelValue", datePickerProperties);
    }

    protected void moveToElement(By by, int xOffset, int yOffset) {
        Actions action = new Actions(driver);
        action.moveToElement(findElement(by), xOffset, xOffset);
        action.perform();
    }

    protected void moveByCoordinates(int startX, int startY, int endY) {
        TouchAction action = new TouchAction(getDriver());
        action.press(new PointOption().withCoordinates(startX, startY)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(new PointOption().withCoordinates(startX, endY)).release().perform();
    }

    protected void click(By by, long timeout) {
        findElement(by, timeout).click();
    }

    public void iosbringAppForward(String appBundleId) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        HashMap<String, String> bundleArgs = new HashMap<>();
        bundleArgs.put("bundleId", appBundleId);
        js.executeScript("mobile: activateApp", bundleArgs);
    }

    public void dragAndDrop(WebElement elem1, WebElement elem2) {
        TouchAction action = new TouchAction(driver);

        //build drag and drop action...Dragging Battery element on Sound element
        action.press(new PointOption().point(elem1.getLocation().getX(), elem1.getLocation().getY()))
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(1)))
                .moveTo(new PointOption().point(elem2.getLocation().getX(), elem2.getLocation().getY())).release();

        //Perform drag and drag action
        MultiTouchAction multiAction = new MultiTouchAction(driver);
        multiAction.add(action).perform();

    }
    protected void hideKeyBoard(){
        getDriver().hideKeyboard();
    }
}
