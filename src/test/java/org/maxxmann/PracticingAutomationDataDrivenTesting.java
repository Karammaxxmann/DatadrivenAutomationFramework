package org.maxxmann;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utiles.excelReader;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PracticingAutomationDataDrivenTesting {
    WebDriver driver;
    String URL ="https://testautomationpractice.blogspot.com/";
    String excel_fileName = "src/test/resources/saucedemo.xlsx";
    JavascriptExecutor js =(JavascriptExecutor)driver;

    @BeforeTest
    public void setDriver(){
        driver=new ChromeDriver();
        driver.navigate().to(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));;
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement dropdown =driver.findElement(By.id("country"));
        Select dd =new Select(dropdown);
        dd.selectByValue(value);

    }
    @Test(dataProvider = "Colour" )
    public void multiSelect (List<String> colorsToSelect){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement colorElement =driver.findElement(By.id("colors"));
        Select colorSelect =new Select(colorElement);
        for (String color : colorsToSelect)
        {
            try {
                colorSelect.selectByValue(color);
                System.out.println("select colour from for loop" + color);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
}
    }
    @Test(dataProvider = "animalList")
    public void sortedList(List<String> animal){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement selectListElement =driver.findElement(By.id("animals"));
        Select selectList =new Select((selectListElement));
        for (String select : animal){
            try {
                selectList.selectByValue(select);
                System.out.println("selected values are for loop: " + select);
            }catch (Exception e){
                System.out.println("not selected value" +animal+ "-" +e.getMessage());
            }
        }
    }
@Test(dataProvider ="fileUploadData")
    public void uploadFiles(String filePath) {
    File file = new File("C:\\Users\\karamjeet\\Documents\\1736913361.png");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    WebElement uploadFile = driver.findElement(By.id("singleFileInput"));
    Actions actions = new Actions(driver);
    actions.moveToElement(uploadFile).perform();
    if (file.exists()) {
        uploadFile.sendKeys(file.getAbsolutePath());
        System.out.println("Uploading file: " + file.getName());
        System.out.println("File path received: " + filePath);
        System.out.println("Absolute path: " + file.getAbsolutePath());
        System.out.println("File exists? " + file.exists());
        driver.findElement((By.xpath("//button[normalize-space()='Upload Single File']"))).click();
    } else {
        System.out.println("File not found!");

    }
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
    @DataProvider(name ="Colour")
    public Object[] testData5() {
        return new Object[][]{
                {Arrays.asList("blue", "red", "green","yellow","white")}
        };
    }
    @DataProvider(name ="animalList")
    public Object[] testData6() {
        return new Object[][]{
                {Arrays.asList("rabbit", "giraffe", "elephant")}
        };
    }
     @DataProvider(name ="upload")
        public Object[] testData7() {
         return new Object[][]{
                 {"C:\\Users\\karamjeet\\Documents\\1736913361.png"}
         };
     }
         @DataProvider(name ="fileUploadData")
         public Object[][] uploadFilesData() {
             return new Object[][]{
                     {"C:\\Users\\karamjeet\\Documents\\file1.png"}
             };
    }


    /*@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }*/

    }



