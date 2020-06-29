package com.salesforce.ui;

import static org.awaitility.Awaitility.await;
import static org.awaitility.Awaitility.waitAtMost;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage {

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

    public void openHomePage() {
        driver.get("https://eu13.lightning.force.com/lightning/page/home");
    }

    public void waitHomePageOpened() {
        await("Home page is not opened").atMost(15, TimeUnit.SECONDS)
                .pollInterval(2,TimeUnit.SECONDS).until(() -> !loadingScreen.isDisplayed());
        await("Home page is not opened").atMost(15, TimeUnit.SECONDS)
                .pollInterval(2,TimeUnit.SECONDS).until(() -> homeTab.isDisplayed());
    }

    public void navigateToOpportunities() {
        opportunityTab.click();
        opportunityTab.isSelected();
    }
}
