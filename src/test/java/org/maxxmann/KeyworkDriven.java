package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utiles.excelReader;

import java.time.Duration;

public class KeyworkDriven {
       WebDriver driver;
        String URL = "https://www.saucedemo.com/";
       // String excel_fileName = "src/test/resources/saucedemo.xlsx";

        @BeforeMethod
        public void setDriver() {
            driver = new ChromeDriver();
            driver.get(URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        }

        @Test(dataProvider = "login")
        public void test(String userName, String Password) {
            WebElement username = driver.findElement(By.id("user-name"));
            username.sendKeys(userName);
            WebElement psw = driver.findElement(By.id("password"));
            psw.sendKeys(Password);
            WebElement btn = driver.findElement(By.id("login-button"));
            btn.click();
        }

    @DataProvider(name ="login")
    public Object[] tesData() {
        return new Object[][]{
                {"standard_user", "secret_sauce",},
                //{"admin@admin.com", "1234", "Invalid"},
        };
    }
        @AfterMethod
        public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        }
    }
