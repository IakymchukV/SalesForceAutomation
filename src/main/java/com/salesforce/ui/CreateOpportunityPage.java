package com.salesforce.ui;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOpportunityPage {

    private static Logger LOGGER = Logger.getLogger(CreateOpportunityPage.class);
    private final WebDriver driver;
    @FindBy(xpath = "//span[text()='Opportunity Name']/parent::label/following-sibling::input")
    private WebElement opportunityName;

    @FindBy(xpath = "//input[@title='Search Accounts']")
    private WebElement accountName;

    @FindBy(xpath = "//ul[contains(@class,'lookup__list')]//div[contains(@class,'primaryLabel')]")
    private List<WebElement> listAccountNames;

    @FindBy(xpath = "//*[contains(@class,'datePicker-openIcon')]")
    private WebElement closeDate;

    @FindBy(xpath = "//table[@class= 'calGrid']//button")
    private WebElement todayButton;

    @FindBy(xpath = "//span[text()='Stage']/parent::span/following-sibling::div")
    private WebElement stage;
    @FindBy(xpath = "//ul[@class='scrollable']/li")
    private List<WebElement> stageTypes;

    @FindBy(xpath = "//button[@title='Save']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@data-key='success']")
    private WebElement successAlert;

    private WebDriverWait wait;

    public CreateOpportunityPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }

    public void filledInAllRequiredField(String name, String nameForAccount, String typeForStage) {
        LOGGER.info("Fill in all required field to create new Opportunity");
        wait.until(ExpectedConditions.visibilityOf(opportunityName));
        opportunityName.sendKeys(name);
        filledAccountName(nameForAccount);
        chooseTodayDate();
        chooseStageType(typeForStage);
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(successAlert));
    }

    public void filledAccountName(String account) {
        LOGGER.info("Fill in account name for opportunity as: " + account);
        accountName.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(listAccountNames));
        listAccountNames.forEach(name -> {
            if (name.getText().equals(account)) {
                name.click();
            }
        });
    }

    public void chooseTodayDate() {
        LOGGER.info("Pick Today day for date");
        closeDate.click();
        wait.until(ExpectedConditions.elementToBeClickable(todayButton));
        todayButton.click();
    }

    public void chooseStageType(String type) {
        LOGGER.info("Choose stage type as:" + type);
        stage.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(stageTypes));
        stageTypes.forEach(stageType -> {
            if (stageType.getText().equals(type)) {
                stageType.click();
            }
        });
    }
}


