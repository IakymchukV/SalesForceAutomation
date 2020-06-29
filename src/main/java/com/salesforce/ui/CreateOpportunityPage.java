package com.salesforce.ui;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreateOpportunityPage {

    private final WebDriver driver;
    @FindBy(xpath = "//span[text()='Opportunity Name']/parent::label/following-sibling::input")
    WebElement opportunityName;
    @FindBy(xpath = "//input[@title='Search Accounts']")
    WebElement accountName;
    @FindBy(xpath = "//ul[contains(@class,'lookup__list')]//div[contains(@class,'primaryLabel')]")
    List<WebElement> listAccountNames;
    @FindBy(xpath = "//*[contains(@class,'datePicker-openIcon')]")
    WebElement closeDate;

    @FindBy(xpath = "//table[@class= 'calGrid']//button")
    WebElement todayButton;

    @FindBy(xpath = "//span[text()='Stage']/parent::span/following-sibling::div")
    WebElement stage;
    @FindBy(xpath = "//ul[@class='scrollable']/li")
    List<WebElement> stageTypes;

    @FindBy(xpath = "//button[@title='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//div[@data-key='success']")
    WebElement successAlert;

    WebDriverWait wait;

    public CreateOpportunityPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 1);
    }

    public void filledInAllRequiredField(String name, String nameForAccount, String typeForStage) {
        opportunityName.sendKeys(name);
        filledAccountName(nameForAccount);
        chooseTodayDate();
        chooseStageType(typeForStage);
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(successAlert));
    }

    public void filledAccountName(String account) {
        accountName.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(listAccountNames));
        listAccountNames.forEach(name -> {
            if (name.getText().equals(account)) {
                name.click();
            }
        });
    }

    public void chooseTodayDate() {
        closeDate.click();
        wait.until(ExpectedConditions.elementToBeClickable(todayButton));
        todayButton.click();
    }

    public void chooseStageType(String type) {
        stage.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(stageTypes));
        stageTypes.forEach(stageType -> {
            if (stageType.getText().equals(type)) {
                stageType.click();
            }
        });
    }
}


