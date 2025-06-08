package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public AppiumDriverLocalService serviceBuilder;
    public AndroidDriver driver;

    @BeforeClass
    public void confiures() throws MalformedURLException {
         serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Lenovo\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
         serviceBuilder.start();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("appiumCourseandroid");
//        options.setApp("C:\\Users\\Lenovo\\appium\\src\\test\\resources\\ApiDemos-debug.apk");
        options.setApp("C:\\Users\\Lenovo\\appium\\src\\test\\resources\\General-Store.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void longGesture(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "duration" ,2000));
    }

    public void startActivity(){
//        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("Intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies"));
        Map<String, Object> params = new HashMap<>();
        params.put("command", "am start -n io.appium.android.apis/.preference.PreferenceDependencies");
        driver.executeScript("mobile: shell", params);
    }


    public void swipeGesture(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "direction" , "left",
                        "percent" , 0.75));
    }

    public void DragDropGesture(WebElement element, int x, int y){
        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "endX", x,
                        "endY", y));
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
        serviceBuilder.stop();
    }
}
