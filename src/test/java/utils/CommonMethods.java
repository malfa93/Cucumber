package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializer {

    public static WebDriver driver;


    public static void openBrowserAndLaunchApplication(){

        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }


        driver.manage().window().maximize();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/dashboard");
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

        initializePageObjects();
    }


    public void closeBrowser(){
        driver.quit();
    }


    public static void sendText(WebElement element, String textToSend){

        element.clear();
        element.sendKeys(textToSend);

    }

    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    public static void waitForClickability(WebElement element){

        getWait().until(ExpectedConditions.elementToBeClickable(element));

    }

    public static void click(WebElement element){
        waitForClickability(element);
        element.click();
    }

    public static JavascriptExecutor getJSExecutor(){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;

    }

    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click()", element);
    }


    //for dropdown selection using

    public static void selectDropdown(WebElement element, String text){
        Select s = new Select(element);
        s.selectByVisibleText(text);
    }

    //screenshot method

    public static byte[] takeScreenshot(String fileName){

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(sourceFile,new File(Constants.SCREENSHOT_FILEPATH + fileName+" "+getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return picBytes;

    }

    public static String getTimeStamp(String pattern){
        Date date = new Date(); //yyyy-mm-dd-hh-mm-ss-ms
        SimpleDateFormat sdf  = new SimpleDateFormat(pattern); // to format the date according to our choice we have to use this function

        return sdf.format(date);



    }

    public static void frameSwitch(WebElement element){
        driver.switchTo().frame(element);
    }

    public static void defaultFrame(){
        driver.switchTo().defaultContent();
    }

    public static void selectRadio(WebElement element){
        boolean isEnabled = element.isEnabled();
        try {
            if(isEnabled){
                element.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The radio button is disabled");
        }

    }


}



