package org.maxxmann;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;
import java.util.List;

public class PracticingAutomation {
    WebDriver driver;
    String URL = "https://testautomationpractice.blogspot.com/";

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /* @Test()
     public void verifyToggleToStop() {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         WebElement buttonStart = driver.findElement(By.xpath("//div[@class=\"widget-content\"]//button[normalize-space()='START']"));
         // WebElement buttonStart = driver.findElement(By.xpath("//button[normalize-space()='START']"));
         js.executeScript("arguments[0].scrollIntoView(true);", buttonStart);
         if (buttonStart.equals("START")) {
             System.out.println("Button is in START state. Clicking to STOP...");
             buttonStart.click();
             // Optional: Verify it changed
             String newText = buttonStart.getText();
             if (newText.equalsIgnoreCase("STOP")) {
                 System.out.println("Button successfully toggled to STOP.");
             } else {
                 System.out.println("Toggle failed!");
             }
         } else if (buttonStart.equals("STOP")) {
             System.out.println("Button is in STOP state. Clicking to START...");
             buttonStart.click();
             // Optional: Verify it changed
             String newText = buttonStart.getText();
             if (newText.equalsIgnoreCase("START")) {
                 System.out.println("Button successfully toggled to START.");
             } else {
                 System.out.println("Toggle failed!");
             }
         } else {
             System.out.println("Unexpected button text: " + buttonStart);
         }*/
    @Test
    public void Simple_Alert() {
        WebElement simple_alert = driver.findElement(By.xpath("//button[@id=\"alertBtn\"]"));
        simple_alert.click();
        driver.switchTo().alert().accept();

    }

    @Test
    public void Confirmation_Alert() {
        WebElement confirmation_alert = driver.findElement(By.xpath("//button[@id=\"confirmBtn\"]"));
        confirmation_alert.click();
        driver.switchTo().alert().accept();
    }

    @Test
    public void Prompt_Alert() {
        WebElement prompt_alert = driver.findElement(By.xpath("//button[@id=\"promptBtn\"]"));
        prompt_alert.click();
        driver.switchTo().alert().sendKeys("Karamjeet");
        driver.switchTo().alert().accept();
        WebElement message = driver.findElement(By.xpath("//p[@id=\"demo\"]"));
        String msg = message.getText();
        Assert.assertEquals(msg, "Hello Karamjeet! How are you today?");
    }

    @Test
    public void WindowTab() {
        String main_window = driver.getWindowHandle();
        WebElement tab = driver.findElement(By.xpath("//button[@onclick=\"myFunction()\"]"));
        tab.click();
        Set<String> new_tab = driver.getWindowHandles();
        for (String tabs : new_tab) {
            if (!main_window.equals(tabs)) {
                driver.switchTo().window(tabs);
                break;
            }
        }
        System.out.println("title " + driver.getTitle());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[normalize-space()='Manual Testing Materials']")).click();
        driver.close();
        driver.switchTo().window(main_window);

    }

    @Test
    public void popup_Window() {
        String main_window = driver.getWindowHandle();
        WebElement new_window = driver.findElement(By.xpath("//button[@id=\"PopUp\"]"));
        new_window.click();
        Set<String> new_tab = driver.getWindowHandles();
        for (String pop_window : new_tab) {
            if (!main_window.equals(pop_window)) {
                driver.switchTo().window(pop_window);
                break;
            }
        }
        System.out.println("Window Title is : " + driver.getTitle());
        driver.close();
        driver.switchTo().window(main_window);
    }

    @Test
    public void double_click() {
        WebElement doubleClick = driver.findElement(By.xpath("//button[@ondblclick=\"myFunction1()\"]"));
        WebElement moveToElement = driver.findElement(By.xpath("//input[@id=\"field1\"]"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClick).perform();
        actions.moveToElement(moveToElement).perform();
        String msg = moveToElement.getAttribute("value");
        Assert.assertEquals(msg, "Hello World!");
        actions.contextClick(moveToElement).perform();
    }

    @Test
    public void drag_drop() {
        WebElement drag = driver.findElement(By.xpath("//div[@id=\"draggable\"]"));
        WebElement drop = driver.findElement(By.xpath("//div[@id=\"droppable\"]"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(drag, drop).build().perform();

        String droppedText = drop.getText();
        Assert.assertEquals(droppedText, "Dropped!");
        if (droppedText.contains("Dropped!")) {
            System.out.println("dropped sucessfully");
        } else {
            System.out.println("not dropped");
        }
    }

    @Test
    public void mouse_hover() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement mouse_hover = driver.findElement(By.xpath("//div[@class=\"dropdown\"]"));
        js.executeScript("arguments[0].scrollIntoView(true);", mouse_hover);
        Actions actions = new Actions(driver);
        actions.moveToElement(mouse_hover).perform();
        WebElement mobileLink = driver.findElement(By.xpath("//a[normalize-space()='Mobiles']"));
        mobileLink.click();


    }

    @Test
    public void price_slider() {
        WebElement minSlider = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[1]"));
        WebElement maxSlider = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle')])[2]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(minSlider).moveByOffset(80, 0).release().perform();
        actions.clickAndHold(maxSlider).moveByOffset(-90, 0).release().perform();
        // Output slider positions (optional)
        System.out.println("Min slider position: " + minSlider.getAttribute("style"));
        System.out.println("Max slider position: " + maxSlider.getAttribute("style"));
    }

    @Test
    public void drop_down() {
        // Step 1: Click to open the custom dropdown
        WebElement dropDownInput = driver.findElement(By.id("comboBox"));
        dropDownInput.click();
        WebElement dropdown = driver.findElement((By.id("dropdown")));
        List<WebElement> All_option = dropdown.findElements(By.xpath("//div[@class=\"option\"]"));
        for (WebElement option : All_option) {
            System.out.println("Option text: " + option.getText());
            if (option.getText().equalsIgnoreCase("Item 4")) {
                option.click();
                break;
            }
        }
    }
@Test
    public void keyword_Action() throws AWTException {
        String main_window = driver.getWindowHandle();
        WebElement link = driver.findElement((By.xpath("//div[@id=\"laptops\"]//a[@id=\"apple\"]")));
        System.out.println(link.getText());
        Actions action = new Actions(driver);
        action.keyDown(Keys.CONTROL).click(link).keyUp(Keys.CONTROL).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Set<String> new_tab = driver.getWindowHandles();
        for (String next_window : new_tab) {
            if (!main_window.equals(next_window)) {
                driver.switchTo().window(next_window);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                System.out.println("new tab title  :" +driver.getTitle());
                break;
            }
        }
        driver.switchTo().window(main_window);
     System.out.println("Main Window :" +driver.getTitle());

    }
}
