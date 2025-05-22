package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

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
        options.setApp("C:\\Users\\Lenovo\\appium\\src\\test\\resources\\ApiDemos-debug.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
        serviceBuilder.stop();
    }
}
