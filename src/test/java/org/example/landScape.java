package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class landScape extends BaseTest{


    @Test
    public void appium() throws InterruptedException {

// You can also include an optional intent if needed:
        Map<String, Object> params = new HashMap<>();
        params.put("command", "am start -n io.appium.android.apis/.preference.PreferenceDependencies");

        // Execute the ADB command using Appium's executeScript
        driver.executeScript("mobile: shell", params);

//        driver.findElement(By.id("android:id/checkbox")).click();
//        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
//        String actual = driver.findElement(By.id("android:id/alertTitle")).getText();
//        Assert.assertEquals(actual, "WiFi settings");
//        driver.setClipboardText("hello appium");
//        Thread.sleep(3000);
//        driver.findElement(By.id("android:id/edit")).sendKeys("hello appium");
//        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
//        driver.findElements(AppiumBy.className("android.widget.Button")).get(1).click();
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

}
