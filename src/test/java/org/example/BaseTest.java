package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    public AppiumDriverLocalService serviceBuilder;
    public AndroidDriver driver;
    public WebDriverWait wait;
    public UiAutomator2Options options;


    @BeforeClass
    public void confiures() throws MalformedURLException {
         serviceBuilder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\Lenovo\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
         serviceBuilder.start();
        options = new UiAutomator2Options();
        options.setCapability("automationName", "UiAutomator2");
        options.setDeviceName("vivo V2229A");
        options.setApp("C:\\Users\\Lenovo\\appium\\src\\test\\resources\\General-Store.apk");



//        options.setCapability("appPackage", "com.android.chrome");
//        options.setCapability("appActivity", "com.androidsample.generalstore.MainActivity");

        options.setChromedriverExecutable("D:\\chromedriver-win64\\chromedriver.exe");
//        options.setApp("C:\\Users\\Lenovo\\appium\\src\\test\\resources\\ApiDemos-debug.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        options.setCapability("browserName", "Chrome");
//        WebDriverManager.chromedriver().driverVersion("124.0.6367.0").setup();
//        options.setCapability("chromedriverExecutable", WebDriverManager.chromedriver().getDownloadedDriverPath());
//        options.setChromedriverExecutable(WebDriverManager.chromedriver().getDownloadedDriverPath());
//        options.setCapability("chromedriver_autodownload", true);

//        options.setCapability("automationName", "Chromium");



    }

    @BeforeMethod
    public void login(){
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Toka Mohamed");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
//        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));")).click();
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

    public Double replace(String price){
        Double amount = Double.parseDouble(price.replaceAll("[$, ]",""));
        return amount;
    }

    public Double replaceString(String price){
        Double amount = Double.parseDouble(price.substring(2));
        return amount;
    }

    public boolean isDisplayed(WebElement ele){
        return ele.isDisplayed();
    }


//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//        serviceBuilder.stop();
//    }
}
