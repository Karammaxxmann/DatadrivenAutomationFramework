package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DataPikertype1 {
    WebDriver driver;
    String URL ="https://testautomationpractice.blogspot.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";

    @BeforeTest
    public void setDriver(){
        driver=new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));;
    }
    @Test
    public void datePikerFutureDate(){
        String Year = "2026";
        String Month ="Dec";
        String Date ="6";

        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"txtDate\"]"));
        dateTable.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement selectYear = driver.findElement(By.className("ui-datepicker-year"));
        Select year_dd =new Select(selectYear);
        year_dd.selectByVisibleText(Year);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement selectMonth = driver.findElement(By.className("ui-datepicker-month"));
        Select month_dd =new Select(selectMonth);
        month_dd.selectByVisibleText(Month);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//table//tbody//tr//td//a[@class=\"ui-state-default\" and @data-date='" +Date+ "']")).click();
    }
}
