package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class PracticingAutomationDataDrivenTesting {
    WebDriver driver;
    String URL ="https://testautomationpractice.blogspot.com/";
    public void setDriver(){
        driver=new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    public void Test1(String Name){
        WebElement Ename =driver.findElement(By.className("class=\"form-control\""));
        Ename.sendKeys(Name);

    }
}

