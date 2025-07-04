package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
//import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static io.appium.java_client.android.nativekey.AndroidKey.BACK;
import static io.appium.java_client.android.nativekey.AndroidKey.ENTER;

public class CheckOutPage extends BaseTest {

    @Test(description = "Verifying user able to buy product")
    public void fillingForms() throws InterruptedException {

        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.widget.TextView)[1]")));
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ADD TO CART\"));"));
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();


        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
        wait.until(ExpectedConditions.attributeContains(By.id("com.androidsample.generalstore:id/toolbar_title"), "text", "Cart"));

        int productsNameSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        double sum = 0;
        for (int i = 0; i < productsNameSize; i++) {
            String price = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(i).getText();
            sum += replace(price);
        }
        String ExpectedSum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();

        Assert.assertEquals(sum, replaceString(ExpectedSum));

        WebElement terms = driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
        longGesture(terms);
        String alert = driver.findElement(By.id("com.androidsample.generalstore:id/alertTitle")).getText();
        Assert.assertEquals(alert, "Terms Of Conditions");

        WebElement close = driver.findElement(By.id("android:id/button1"));
        close.click();
        WebElement checkboxEmails = driver.findElement(AppiumBy.className("android.widget.CheckBox"));
        checkboxEmails.click();
        WebElement visitWebsite = driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed"));
        visitWebsite.click();
        WebElement webView = driver.findElement(By.id("com.androidsample.generalstore:id/webView"));
        Assert.assertTrue(isDisplayed(webView));
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println(context);
        }
//        ChromeOptions chromeOptions = new ChromeOptions();
//        WebDriver driverchrome = new ChromeDriver(chromeOptions);
        driver.activateApp("com.android.chrome");
//                options.setCapability("appPackage", "com.android.chrome");
//        driver.context("com.androidsample.generalstore.WebViewActivity");
        driver.findElement(By.id("com.android.chrome:id/search_box_text")).sendKeys("Appium");
        driver.pressKey(new KeyEvent(ENTER));
        driver.pressKey(new KeyEvent (BACK));
        driver.context("NATIVE_APP");

    }
}
