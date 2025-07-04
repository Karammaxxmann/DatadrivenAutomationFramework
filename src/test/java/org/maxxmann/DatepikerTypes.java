package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class DatepikerTypes {
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
        String Month ="December";
        String Date ="6";

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
            driver.findElement(By.xpath("//table//tbody//tr//td//a[@data-date=\"6\"]")).click();
        }
    @Test
    public void datePikerPastDate(){
        String Year = "2023";
        String Month ="May";
        String Date ="7";


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
        driver.findElement(By.xpath("//table//tbody//tr//td//a[@data-date=\"7\"]")).click();
    }
    }
