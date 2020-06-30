package com.salesforce.ui.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.salesforce.ui.CreateOpportunityPage;
import com.salesforce.ui.HomePage;
import com.salesforce.ui.LoginPage;
import com.salesforce.ui.OpenedOpportunitiesPage;
import com.salesforce.ui.OpportunitiesTabPage;

public class OpportunityTest extends BaseUiTest {
    private static final String accountName = "Test";
    private static final String stageType = "Negotiation";
    private static final String login = "iakymchuk1991-euqw@force.com";
    private static final String password = "Passw0rd!";
    public LoginPage loginPage;
    public HomePage homePage;
    public OpportunitiesTabPage opportunities;
    public CreateOpportunityPage createOpportunity;
    public OpenedOpportunitiesPage openedOpportunitiesPage;
    private String randomName = "AQA_Opportunity" + Math.random();

    @BeforeClass
    public void beforeClass() {
        loginPage = PageFactory.initElements(getWebDriver(), LoginPage.class);
        homePage = PageFactory.initElements(getWebDriver(), HomePage.class);
        opportunities = PageFactory.initElements(getWebDriver(), OpportunitiesTabPage.class);
        createOpportunity = PageFactory.initElements(getWebDriver(), CreateOpportunityPage.class);
        openedOpportunitiesPage = PageFactory.initElements(getWebDriver(), OpenedOpportunitiesPage.class);
    }

    @Test
    public void verifyCreationOfNewOpportunity() {
        loginPage.login(login, password);
        homePage.waitHomePageOpened();
        homePage.navigateToOpportunities();
        opportunities.verifyOpportunitiesTabOpened();
        opportunities.clickNewOpportunities();
        createOpportunity.filledInAllRequiredField(randomName, accountName, stageType);
        openedOpportunitiesPage.verifyOpportunityOpenedByName(randomName);
    }
}
