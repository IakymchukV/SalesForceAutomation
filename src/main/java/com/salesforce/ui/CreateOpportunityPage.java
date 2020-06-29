package com.salesforce.ui;


import java.util.List;
import java.util.Optional;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateOpportunityPage {

    private static Logger LOGGER = LoggerFactory.getLogger(CreateOpportunityPage.class);
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
        LOGGER.info("Fill in all required fields to create new Opportunity");
        wait.until(ExpectedConditions.visibilityOf(opportunityName));
        opportunityName.sendKeys(name);
        filledAccountName(nameForAccount);
        chooseTodayDate();
        chooseStageType(typeForStage);
        saveButton.click();
        wait.until(ExpectedConditions.visibilityOf(successAlert));
    }

    public void filledAccountName(String account) {
        LOGGER.info("Fill in account name for opportunity as: {}", account);
        accountName.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(listAccountNames));
        Optional<WebElement> directAccountName =
                Optional.ofNullable(listAccountNames.stream().filter(name -> account.equalsIgnoreCase(name.getText()))
                .findFirst().orElseThrow(NotFoundException::new));
        directAccountName.ifPresent(WebElement::click);
    }

    public void chooseTodayDate() {
        LOGGER.info("Pick Today day for date");
        closeDate.click();
        wait.until(ExpectedConditions.elementToBeClickable(todayButton));
        todayButton.click();
    }

    public void chooseStageType(String type) {
        LOGGER.info("Choose stage type as: {}", type);
        stage.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(stageTypes));
        Optional<WebElement> stageTypeToPick =
                Optional.ofNullable(stageTypes.stream().filter(name -> type.equalsIgnoreCase(name.getText()))
                .findFirst().orElseThrow(NotFoundException::new));
        stageTypeToPick.ifPresent(WebElement::click);
    }
}


