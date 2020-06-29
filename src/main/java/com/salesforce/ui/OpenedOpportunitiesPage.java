package com.salesforce.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class OpenedOpportunitiesPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public OpenedOpportunitiesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 1);
    }

    @FindBy(xpath = "//slot[@slot='primaryField']/lightning-formatted-text")
    WebElement OpportunityName;

    public void verifyOpportunityOpenedByName(String name){
        wait.until(ExpectedConditions.visibilityOf(OpportunityName));
        Assert.assertTrue(OpportunityName.getText().equals(name),"Expected opportunity name is "+ name
                + " but found: " +OpportunityName.getText() );

    }
}
