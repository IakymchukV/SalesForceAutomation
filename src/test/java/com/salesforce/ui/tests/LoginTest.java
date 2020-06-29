package com.salesforce.ui.tests;


import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.salesforce.ui.CreateOpportunityPage;
import com.salesforce.ui.HomePage;
import com.salesforce.ui.LoginPage;
import com.salesforce.ui.OpportunitiesTabPage;

public class LoginTest extends BaseUiTest {
    public LoginPage loginPage;
    public HomePage homePage;
    public OpportunitiesTabPage opportunities;
    public CreateOpportunityPage createOpportunity;
    private static final String accountName = "Test";
    private static final String stageType = "Negotiation";

    @BeforeClass
    public void setUp() {
        super.setUp();
        loginPage = PageFactory.initElements(getWebDriver(),LoginPage.class);
        homePage = PageFactory.initElements(getWebDriver(),HomePage.class);
        opportunities = PageFactory.initElements(getWebDriver(), OpportunitiesTabPage.class);
        createOpportunity = PageFactory.initElements(getWebDriver(), CreateOpportunityPage.class);
    }

    @Test
    public void loginTest() {
        loginPage.login("iakymchuk1991-euqw@force.com", "Passw0rd!");
        homePage.waitHomePageOpened();
        homePage.navigateToOpportunities();
        opportunities.verifyOpportunitiesTabOpened();
        opportunities.clickNewOpportunities();
        createOpportunity.filledInAllRequiredField(accountName,stageType);
    }

    @AfterClass
    public void afterClass(){
        super.afterClass();
    }
}
