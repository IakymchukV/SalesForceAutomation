package com.salesforce.ui;


import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.waitAtMost;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class OpportunitiesTabPage {

    private static Logger LOGGER = Logger.getLogger(OpenedOpportunitiesPage.class);
    private WebDriver driver;

    @FindBy(xpath = "//*[contains(@data-aura-class,'forceBreadCrumbItem')]")
    private WebElement opportunityTitle;

    @FindBy(xpath = "//*[contains(@data-aura-class,'forceBreadCrumbItem')]")
    private WebElement breadCrumbOpportunity;

    @FindBy(xpath = "//div[@title='New']")
    private WebElement buttonNew;

    @FindBy(xpath = "//div[@title='New']")
    private WebElement newOpportunityForm;

    public OpportunitiesTabPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyOpportunitiesTabOpened() {
        LOGGER.info("Verify opportunity tab is opened");
        waitAtMost(5, TimeUnit.SECONDS).pollInterval(2, TimeUnit.SECONDS).until(() -> opportunityTitle.isDisplayed());
        Assert.assertEquals(opportunityTitle.getText(),
                "Opportunities", "Opportunities title is not present");
    }

    public void clickNewOpportunities() {
        LOGGER.info("Click on in 'New' button in Opportunities page");
        buttonNew.isDisplayed();
        buttonNew.click();
        await("New Opportunity window is not opened").pollInterval(2, TimeUnit.SECONDS)
                .atMost(5, TimeUnit.SECONDS).until(() -> opportunityTitle.isDisplayed());
    }
}
