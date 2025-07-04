package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class DatepikerTypes {
    WebDriver driver;
    String URL = "https://testautomationpractice.blogspot.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        ;
    }

    @Test(dataProvider = "DatePiker1")
    public void datePikerFutureDate(String Year, String Month, String Date) {
        //String Year = "2026";
        // String Month ="December";
        //String Date ="6";

        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"datepicker\"]"));
        dateTable.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        while (true) {
            String selectMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
            String selectYear = driver.findElement(By.className("ui-datepicker-year")).getText();

            if (selectMonth.equals(Month) && selectYear.equals(Year)) {
                break;
            } else {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.findElement(By.xpath("//a[@data-handler=\"next\"]")).click();
            }
        }
        //driver.findElement(By.xpath("//table//tbody//tr//td//a[@data-date=\"6\"]")).click();
        driver.findElement(By.xpath("//table//tbody//tr//td//a[@class=\"ui-state-default\" and @data-date='" + Date + "' ]")).click();
        //a[@class="ui-state-default" and @data-date="5" ]
    }

    @Test(dataProvider = "DatePiker2")
    public void datePikerPastDate(String Year, String Month, String Date) {
        //String Year = "2023";
        //String Month = "May";
       // String Date = "7";


        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"datepicker\"]"));
        dateTable.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        while (true) {
            String selectMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
            String selectYear = driver.findElement(By.className("ui-datepicker-year")).getText();

            if (selectMonth.equals(Month) && selectYear.equals(Year)) {
                break;
            } else {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.findElement(By.xpath("//a[@data-handler=\"prev\"]")).click();
            }
        }
        driver.findElement(By.xpath("//table//tbody//tr//td//a[@class=\"ui-state-default\" and @data-date='" + Date + "' ]")).click();
    }
    @DataProvider(name = "DatePiker1")
    public Object[] testData() {
        return new Object[][]{
                {"2026", "December", "6"}
        };
    }

    @DataProvider(name = "DatePiker2")
    public Object[] testData2() {
        return new Object[][]{
                {"2023", "May", "7"}
        };
    }
}
