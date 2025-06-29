package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartPage extends BaseTest {

    @Test(description = "Verifying user able to buy product")
    public void fillingForms() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Toka Mohamed");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
//        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));")).click();
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
    }
}
