package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.appium.java_client.android.nativekey.AndroidKey.ENTER;


public class BrowserTest extends BrowserBaseTest{

    @Test
    public void mobileTest() throws InterruptedException {
        Actions actions = new Actions(driver);

        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"Products (current)\"]")).click();
        driver.findElement(AppiumBy.className("android.widget.Button")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//android.widget.Button)[2]")).click();
//        Thread.sleep(2000);
//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
//                        ".setAsVerticalList()" +
//                        ".scrollForward()" +
//                        ".scrollIntoView(new UiSelector().text(\"Devops\"))"));

//
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).flingToEnd(10)"));
        String elementText = driver.findElement(By.xpath("//android.view.View[@content-desc=\"Devops\"]/android.widget.TextView")).getText();
        Assert.assertEquals(elementText, "Devops");

    }

}
