package org.maxxmann;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;
import org.utiles.excelReader;
import org.utiles.excelReader1;

import java.time.Duration;

public class podjinnlogin {
    WebDriver driver;
    String URL ="https://maxxmann.info/sign-in";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";
@BeforeTest
    public void setDriver(){
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12));
        driver.getCurrentUrl();
    }
@Test(dataProvider = "logind")
    public void Test(String EmailId , String Password , String Message){
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    WebElement userEmail = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
    userEmail.sendKeys(EmailId);
    WebElement userPassword  =driver.findElement(By.name("password"));
    userPassword.sendKeys(Password);
    WebDriverWait waitSubmit = new WebDriverWait(driver, Duration.ofSeconds(50));
    WebElement user = waitSubmit.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Sign In']")));
    js.executeScript("arguments[0].click();", user);

    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
        WebElement getText =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text-xs capitalize']")));
        String actualText = (String) js.executeScript("return arguments[0].textContent;", getText);
        Assert.assertEquals(actualText, Message);
        System.out.println(actualText);
    }

    @DataProvider(name = "logind")
    public  String[][] DataExcel() throws Exception{
    excelReader reader =new excelReader();
    String[][] data =reader.readExcel(excel_fileName,"Sheet2");
    System.out.println("Rows loaded: " + (data != null ? data.length : 0));
    return data;

    }
}
