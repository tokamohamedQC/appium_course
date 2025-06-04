package org.example;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragDrop extends BaseTest{

    @Test
    public void DragDropGestures() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        WebElement element = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        DragDropGesture(element, 620, 529);
        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
        Assert.assertEquals(result, "Dropped!");
    }
}
