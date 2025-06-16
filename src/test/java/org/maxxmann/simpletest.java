package org.maxxmann;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.Duration;

public class simpletest {
        WebDriver driver;
        String URL ="https://www.saucedemo.com/";

       @BeforeTest
        public void setDriver(){
            driver = new ChromeDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        }

        @Test()
        public void test(){
            WebElement username = driver.findElement(By.id("user-name"));
            username.sendKeys("standard_user");
            WebElement psw = driver.findElement(By.id("password"));
            psw.sendKeys("secret_sauce");
            WebElement btn = driver.findElement(By.id("login-button"));
            btn.click();
        }
        }
