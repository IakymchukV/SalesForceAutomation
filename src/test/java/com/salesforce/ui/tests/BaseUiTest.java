package com.salesforce.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseUiTest {
    private static final String salesForceUrl = "https://login.salesforce.com/";
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        initializeBrowser();
        driver.get(salesForceUrl);
    }

    private void initializeBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 5);
    }

    @AfterClass
    public void afterClass() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getWebDriver() {
        return driver;
    }

}
