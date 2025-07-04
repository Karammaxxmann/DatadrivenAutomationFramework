package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataPikertype1 {
    WebDriver driver;
    String URL ="https://testautomationpractice.blogspot.com/";
    @BeforeTest
    public void setDriver(){
        driver=new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));;
    }
    @Test(dataProvider = "DatePiker")
    public void datePikerFutureDate(String Year, String Month, String Date) {
        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"txtDate\"]"));
        dateTable.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement selectYear = driver.findElement(By.className("ui-datepicker-year"));
        Select year_dd = new Select(selectYear);
        year_dd.selectByVisibleText(Year);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement selectMonth = driver.findElement(By.className("ui-datepicker-month"));
        Select month_dd = new Select(selectMonth);
        month_dd.selectByVisibleText(Month);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//table//tbody//tr//td//a[@class=\"ui-state-default\" and @data-date='" + Date + "']")).click();
    }
        @DataProvider(name = "DatePiker")
        public Object[] testData2() {
            return new Object[][]{
                    {"2023", "May", "7"}
            };
        }
    }
