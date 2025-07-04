package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class dynamicwebtable {
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
        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='taskTable']/tbody/tr"));
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            for (WebElement col : cols) {
                System.out.print(col.getText() + " | ");
            }
            System.out.println();
        }
    }
    @Test(priority = 2)
    public void Compare_value() {
    String data1 =driver.findElement(By.xpath("//table[@id=\"taskTable\"]//tbody//tr[position()>0]//td[1]")).getText();
    String data2 =driver.findElement(By.xpath("//table[@id=\"taskTable\"]//tbody//tr[position()>0]//td[4]")).getText();
        System.out.println("Name" +data1+  " is Disk (MB/s)"  +data2);
    }
    @Test(priority = 3)
    public void ChromeNetworkWithDisplay() {
        String chromeCpu =driver.findElement(By.xpath("//table[@id=\"taskTable\"]//tbody//tr[position()>3]//td[3]")).getText();
        String cpuDisplay  =driver.findElement(By.xpath("//strong[@class=\"firefox-memory\"]")).getText();
        System.out.println("Table CPU: " + chromeCpu + " | Display CPU: " + cpuDisplay);
        Assert.assertNotEquals(chromeCpu,cpuDisplay,"Chrome CPU mismatch!");
    }
}

