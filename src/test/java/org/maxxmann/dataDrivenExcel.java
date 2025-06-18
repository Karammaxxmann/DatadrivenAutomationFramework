package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.utiles.excelReader;
import java.time.Duration;

public class  dataDrivenExcel {
    WebDriver driver;
    String URL = "https://www.saucedemo.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";

    @BeforeMethod
    public void setDriver() {
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
    }

    @Test(dataProvider = "login")
    public void test(String userName, String Password, String Message) {
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys(userName);
        WebElement psw = driver.findElement(By.id("password"));
        psw.sendKeys(Password);
        WebElement btn = driver.findElement(By.id("login-button"));
        btn.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
        WebElement e_msg =driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']"));
        String r_msg= e_msg.getText();
        Assert.assertEquals(r_msg, Message, "value");
        System.out.println("Text displayed: " + r_msg);


    }

    @DataProvider(name = "login")
    public String[][] DataExcel() throws Exception {
        excelReader reader = new excelReader();
        String[][] data = reader.readExcel(excel_fileName, "Sheet1");
        System.out.println("Rows loaded: " + (data != null ? data.length : 0));
        return data;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}