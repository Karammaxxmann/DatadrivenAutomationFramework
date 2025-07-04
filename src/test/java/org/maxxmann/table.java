package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class table {
    WebDriver driver;
    String URL = "https://testautomationpractice.blogspot.com/";

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test(priority = 1)
    public void FullTableDisplay() {
        System.out.println("---------table Data-----------");
        List<WebElement> fulltable = driver.findElements(By.xpath("//table[@name=\"BookTable\"]//tbody//tr//td"));
        for (WebElement data : fulltable) {

            System.out.println(data.getText() + "\t");
        }
    }
    @Test(priority = 2)
    public void TableHeader() {
        System.out.println("---------table Header-----------");
        List<WebElement> table_header = driver.findElements(By.xpath("//table[@name=\"BookTable\"]//tbody//tr//th"));
        for (WebElement header : table_header) {
            System.out.println(header.getText() + "\t");
        }
    }
    @Test(priority = 3)
    public void Display_row_one() {
        List<WebElement> one_row_data = driver.findElements(By.xpath("//table[@name=\"BookTable\"]//tbody//tr[4]"));
        for (WebElement row_data : one_row_data) {
            System.out.println("---------table row-----------");
            System.out.println(row_data.getText() + "\t");
        }
    }
    @Test(priority = 4)
    public void Display_row_two() {  //other method display data
        System.out.println("---------column_Data-----------");
        List<WebElement> column_Data = driver.findElements(By.xpath("//table[@name='BookTable']//tr[position()>3]"));
        for (WebElement colm : column_Data) {
            System.out.println(colm.getText() + "\t");
            System.out.println("---------cols-----------");
            List<WebElement> cols = colm.findElements(By.xpath("//td"));
            for (WebElement colls : cols) {
                System.out.println(colls.getText() + "\t");
            }
        }
    }
        @Test(priority = 5)
        public void sum_ofPrice() {
            System.out.println("--------sum_ofPrice----------");
             List <WebElement> sum =driver.findElements(By.xpath("//table[@name='BookTable']//tr//td[4]"));
             int Total =0;
             for(WebElement price : sum) {
                 System.out.println(price.getText() + "\t");
                 Total += Integer.parseInt(price.getText());
             }
            System.out.println("Total Price is:" +Total );
           }
    @Test(priority = 6)
    public void compareVales() {
        System.out.println("--------sum_ofPrice----------");
        List <WebElement> cc =driver.findElements(By.xpath("//table[@name='BookTable']//tr"));
        for (int i = 2; i <= cc.size() ; i++) {
            String  value = driver.findElement(By.xpath("//table[@name='BookTable']//tr[" +i+ "]/td[3]")).getText();
            if(value.equalsIgnoreCase("Selenium")){
                String book = driver.findElement(By.xpath("//table[@name='BookTable']//tr[" +i+ "]/td[1]")).getText();
                String Price = driver.findElement(By.xpath("//table[@name='BookTable']//tr[" +i+ "]/td[4]")).getText();
                System.out.println("book: " +book +  " | Subject: Selenium | Price: " + Price);
            }
        }

    }
        }

