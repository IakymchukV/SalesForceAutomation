package com.salesforce.ui;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {
    WebDriver driver;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginField;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordField;
    @FindBy(css = "[id=Login]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://login.salesforce.com/");
    }

    public void login(String userName, String password) {
        loginField.sendKeys(userName);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
