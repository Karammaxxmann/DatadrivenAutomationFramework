package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class findbrokenlinks {
    WebDriver driver;
    String URL = "https://www.inmotionhosting.com/";

    @BeforeTest
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void test_found_url() {
        List<WebElement> lists = driver.findElements(By.tagName("a"));
        System.out.println(lists.size());
        for (int i = 0; i <= lists.size() - 3; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            WebElement webElement = lists.get(i);
            String urls = webElement.getAttribute("href");
            System.out.println("URLs is =" + urls);
        }
    }

    @Test(dataProvider = "urlProvider")
    public void foundHttpURL(String url) throws IOException {
        URL links = new URL(url);
        HttpURLConnection http_connection = (HttpURLConnection) links.openConnection();
        http_connection.setConnectTimeout(5000);
        int ResponseCode = http_connection.getResponseCode();
        if (ResponseCode >= 400) {
            System.out.println("url break 400" + ResponseCode);
        } else {
            System.out.println("urls fine" + ResponseCode);
        }

    }
    @Test
    public void openlinktab() {
        List<WebElement> lists = driver.findElements(By.tagName("a"));
        System.out.println(lists.size());
        for (int i = 0; i <= lists.size() - 3; i++) {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            WebElement webElement = lists.get(i);
            String urls = webElement.getAttribute("href");
            System.out.println("URLs is =" + urls);
        }
    }
    @DataProvider(name = "urlProvider")
    public Object[][] provideURLs() {
        return new Object[][]{
                {"https://www.inmotionhosting.com/"}
        };
    }
}
