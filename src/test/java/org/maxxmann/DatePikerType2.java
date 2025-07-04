package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DatePikerType2 {
    WebDriver driver;
    String URL = "https://testautomationpractice.blogspot.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @Test
    public void datePikerFutureDate() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        String Year = "2026";
        String Month = "Dec";
        String Date = "6";
        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"start-date\"]"));
        js.executeScript("arguments[0].scrollIntoView();", dateTable);
        dateTable.click();

    }
}
