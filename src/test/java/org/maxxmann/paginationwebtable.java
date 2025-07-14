package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class paginationwebtable {
    WebDriver driver;
    String URL = "https://testautomationpractice.blogspot.com/";
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void FullTableDisplay() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement table = driver.findElement(By.id("productTable"));  // or use any valid locator
        js.executeScript("arguments[0].scrollIntoView(true);", table);
        List<WebElement> pagination = driver.findElements(By.xpath("//ul[@id=\"pagination\"]//li//a"));
        int pagesno = pagination.size();
        System.out.println("Number of Pages =" + pagesno);
        for (int i = 1; i <= pagination.size(); i++) {
            WebElement AllPages = driver.findElement(By.xpath("//ul[@id=\"pagination\"]//li[" + i + "]//a"));
            AllPages.click();
            System.out.println("Table page :" + i);
            List<WebElement> row = driver.findElements(By.xpath("//table[@id=\"productTable\"]//tbody//tr"));
            for (WebElement rows : row) {
                String Id = rows.findElement(By.xpath("td[1]")).getText();
                String Name = rows.findElement(By.xpath("td[2]")).getText();
                String PriceText = rows.findElement(By.xpath("td[3]")).getText();
                WebElement Checkbox = rows.findElement(By.xpath("td//input[@type=\"checkbox\"]"));
                System.out.println(" id is | " + Id + " Name is | " + Name + " Price is " + PriceText);
                Assert.assertTrue(PriceText.startsWith("$"), "Price start with");
                if (!Checkbox.isSelected()) {
                    Checkbox.click();
                }
            }
        }
    }

    @Test
    public void Sum_of_Price() {
        double TotalPrice = 0.0;
        List<WebElement> pagination = driver.findElements(By.xpath("//ul[@id=\"pagination\"]//li//a"));
        int totalPages = pagination.size();
        for (int i = 1; i <= totalPages; i++) {
            WebElement AllPages = driver.findElement(By.xpath("//ul[@id=\"pagination\"]//li[" + i + "]//a"));
            AllPages.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            List<WebElement> row = driver.findElements(By.xpath("//table[@id=\"productTable\"]//tbody//tr"));
            for (WebElement rows : row) {
                String PriceText = rows.findElement(By.xpath("td[3]")).getText();
                PriceText = PriceText.replace("$", "").trim();
                double price = Double.parseDouble(PriceText);
                TotalPrice += price;
            }
        }
        System.out.println("Total Sum of Prices: $ " + TotalPrice);
    }

    @Test
    public void FooterURL() throws IOException {
        List<WebElement> lists = driver.findElements(By.xpath("//div[@class=\"widget-content\"]//ul//li//a"));
        System.out.println(lists.size());
        for (int i = 0; i <= lists.size() - 3; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            WebElement webElement = lists.get(i);
            String urls = webElement.getAttribute("href");
            System.out.println("URLs is =" + urls);
            URL links = new URL(urls);
            HttpURLConnection http_connection = (HttpURLConnection) links.openConnection();
            http_connection.setConnectTimeout(5000);
            int ResponseCode = http_connection.getResponseCode();
            if (ResponseCode >= 400) {
                System.out.println("url break 400" + ResponseCode);
            } else {
                System.out.println("urls fine" + ResponseCode);
            }
        }
    }
}

