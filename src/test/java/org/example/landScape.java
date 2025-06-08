package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class landScape extends BaseTest{


    @Test
    public void appium() throws InterruptedException {


        Map<String, Object> params = new HashMap<>();
        params.put("command", "am start -n io.appium.android.apis/.preference.PreferenceDependencies");

        // Execute the ADB command using Appium's executeScript
        driver.executeScript("mobile: shell", params);
//        startActivity();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String actual = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(actual, "WiFi settings");
        driver.setClipboardText("hello appium");
        Thread.sleep(3000);
        driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

}
