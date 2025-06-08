package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage extends BaseTest{

    @Test(description = "Verifying user able to buy product")
    public void fillingForms(){
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Toka Mohamed");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Egypt\"));")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"));"));
        driver.findElement(By.xpath("(//android.widget.TextView)[1]")).click();
        int productsNameSize = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

        for (int i = 0 ; i < productsNameSize ; i++){
           String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();

            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                String addedToCart = driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).getText();
                Assert.assertEquals(addedToCart, "ADDED TO CART");
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
    }
}
