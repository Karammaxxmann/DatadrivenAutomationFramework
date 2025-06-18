package org.maxxmann;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utiles.excelReader;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PracticingAutomationDataDrivenTesting {
    WebDriver driver;
    String URL ="https://testautomationpractice.blogspot.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";

    @BeforeTest
    public void setDriver(){
        driver=new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test(dataProvider = "login")
    public void forms(String Name, String Email, String Phone, String Address) {
        //String Name, String Email, Long Phone, String Address
        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys(Name);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys(Email);

        WebElement phone = driver.findElement(By.id("phone"));
        phone.sendKeys(Phone);

        WebElement address = driver.findElement(By.xpath("//textarea[@id='textarea']"));
        address.sendKeys(Address);
    }
    @Test (dataProvider = "automation_Practice")
    public void selectGender(String genderValue){
        WebElement genderRadio = driver.findElement(By.xpath("//input[@name='gender' and @value='" + genderValue + "']"));

        try {
            if ((genderRadio.isDisplayed() && (genderRadio.isEnabled()))) {
                if (!genderRadio.isSelected()) {
                    genderRadio.click();
                }
            }
        } catch (ElementNotInteractableException e) {
            System.out.println("Radio button not selectable: " + e.getMessage());

        }

    }
    @Test (dataProvider  ="Days")
    public void selectDays(List<String> days){
        for(String day : days) {
            try {
                WebElement weeksDay = driver.findElement(By.xpath("//input[@type='checkbox' and @value='" + day + "']"));
                if (weeksDay.isDisplayed() && weeksDay.isEnabled() && !weeksDay.isSelected()) {
                    weeksDay.click();
                }
            }catch (Exception e){
                    System.out.println("Select the all values" + day+ "-" +e.getMessage());
                }

            }
    }
    @Test(dataProvider = "dropdown" )
    public void selectDropdown (String value){
        WebElement dropdown =driver.findElement(By.id("country"));
        Select dd =new Select(dropdown);
        dd.selectByValue(value);

    }
    @Test(dataProvider = "multi select" )
    public void multiSelect (String value){
        WebElement dropdown =driver.findElement(By.id("country"));
        Select dd =new Select(dropdown);
        dd.selectByValue(value);

    }

    @DataProvider(name ="login")
    public Object[] testData() {
        return new Object[][]{
                {"karamjeet", "kaurk161@gmail.com","8872682291" ,"AG-229 Mohali"},
                //{"admin@admin.com", "1234", "Invalid"},
        };
    }


    @DataProvider(name ="automation_Practice")
    public Object[] testData2() {
        return new Object[][]{
                {"male"},
        };
    }
    @DataProvider(name ="Days")
    public Object[] testData3() {
        return new Object[][]{
                {Arrays.asList("sunday", "monday", "tuesday","wednesday","thursday","friday")}
        };
    }
    @DataProvider(name ="dropdown")
    public Object[] testData4() {
        return new Object[][]{
                {"india"},
        };
    }

    @DataProvider(name ="multi select")
    public Object[] testData5() {
        return new Object[][]{
                {"india"},
        };
    }


    /*@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }*/

    }



