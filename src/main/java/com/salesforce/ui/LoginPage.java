package com.salesforce.ui;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {
    private static Logger LOGGER = Logger.getLogger(LoginPage.class);
    private WebDriver driver;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;

    @FindBy(css = "[id=Login]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String userName, String password) {
        LOGGER.info("Login to SalesForce as" + userName);
        loginField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
