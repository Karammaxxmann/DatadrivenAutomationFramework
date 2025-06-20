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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));;
    }
    @Test
    public void datePiker1(){
        WebElement dateTable = driver.findElement(By.xpath("//input[@id=\"datepicker\"]"));
        dateTable.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        while (true) {
            String selectMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
            String selectYear = driver.findElement(By.className("ui-datepicker-year")).getText();
            System.out.println(selectYear);
            System.out.println(selectMonth);
            if (selectMonth.equals("December") && selectYear.equals("2025")) {
                break;
            } else {
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.findElement(By.xpath("//a[@title='Next']")).click();
            }
            driver.findElement(By.xpath("//a[text()='20']")).click();
        }
    }
}
