package com.salesforce.ui;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage {

    private static Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
    private final WebDriver driver;

    @FindBy(xpath = "//*[@data-id='Opportunity']")
    private WebElement opportunityTab;

    @FindBy(xpath = "//*[contains(@data-aura-class,'forceBreadCrumbItem')]")
    private WebElement breadCrumbOpportunity;

    @FindBy(xpath = "//*[@data-id='home']")
    private WebElement homeTab;

    @FindBy(xpath = "//*[@class='loadingText']")
    private WebElement loadingScreen;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitHomePageOpened() {
        LOGGER.info("Wait for Home page opened");
        await("Home page is not opened").atMost(20, TimeUnit.SECONDS)
                .pollInterval(2, TimeUnit.SECONDS).until(() -> !loadingScreen.isDisplayed());
        await("Home page is not opened").atMost(15, TimeUnit.SECONDS)
                .pollInterval(2, TimeUnit.SECONDS).until(() -> homeTab.isDisplayed());
    }

    public void navigateToOpportunities() {
        LOGGER.info("Navigate to Opportunities tab");
        opportunityTab.click();
        opportunityTab.isSelected();
    }
}
