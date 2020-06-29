package com.salesforce.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class OpenedOpportunitiesPage {

    private static Logger LOGGER = LoggerFactory.getLogger(OpenedOpportunitiesPage.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//slot[@slot='primaryField']/lightning-formatted-text")
    private  WebElement OpportunityName;

    public OpenedOpportunitiesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
    }

    public void verifyOpportunityOpenedByName(String name) {
        LOGGER.info("Verify opportunity opened by name{}", name);
        wait.until(ExpectedConditions.visibilityOf(OpportunityName));
        Assert.assertTrue(OpportunityName.getText().equals(name), "Expected opportunity name is " + name
                + " but found: " + OpportunityName.getText());

    }
}
