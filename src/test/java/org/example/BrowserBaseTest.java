package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class BrowserBaseTest {

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
        options.setDeviceName("vivo V2229A");
//        WebDriverManager.chromedriver().setup();
//        options.setChromedriverExecutable("D:\\chromedriver-win64\\chromedriver.exe");
//        options.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
        driver.activateApp("com.android.chrome");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

//    @BeforeMethod
//    public void login(){
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Toka Mohamed");
//        driver.hideKeyboard();
//        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
// }


    public Double replace(String price){
        Double amount = Double.parseDouble(price.replaceAll("[$, ]",""));
        return amount;
    }

    public Double replaceString(String price){
        Double amount = Double.parseDouble(price.substring(2));
        return amount;
    }

    public void scrollGesture(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }

    public void swipeGesture(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "direction" , "down",
                        "percent" , 0.75));
    }

    public void longGesture(WebElement element){
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "duration" ,2000));
    }
    @AfterClass
    public void tearDown(){
        driver.quit();
        serviceBuilder.stop();
    }
}
