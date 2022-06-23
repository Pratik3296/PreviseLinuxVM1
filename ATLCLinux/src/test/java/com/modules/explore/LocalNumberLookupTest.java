package com.modules.explore;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.explore.ExplorePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class LocalNumberLookupTest extends BaseTest {
    @Test(priority = 34)
    public void testLocalNumberLookup() throws IOException, InterruptedException {
        logger = extent.startTest("Test Local Number Lookup");
        ExplorePage explorePage = new ExplorePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Explore");
        explorePage.navigateToExplore();
        BasePage.pageRefresh();
        BasePage.verifyElement(explorePage.getTitle().getText(), "Explore");
        explorePage.enterDataInSearchBox(prop.getProperty("localNumber"));
        logger.log(LogStatus.PASS, "Search TN: "+prop.getProperty("localNumber"));
        explorePage.clickOnSearch();
        BasePage.verifyElement(explorePage.getSectionTitle().getText(), "Telephone Numbers");
        explorePage.printSearchLocalNumber();
        explorePage.clickOnTN();
        logger.log(LogStatus.PASS, "Click on TN: "+prop.getProperty("localNumber"));
        BasePage.verifyElement(explorePage.getTNPopUpHeader().getText(), prop.getProperty("localNumber"));
        BasePage.verifyElement(explorePage.getTNPopUpSectionHeader().getText(), "History Report");
        explorePage.clickOnClosePopUp();
        explorePage.clickOnSpid();
        logger.log(LogStatus.PASS, " Open SPID ");
        explorePage.verifySPIDHeader();
        explorePage.clickOnClosePopUp();
        logger.log(LogStatus.PASS, "Close SPID");
        explorePage.clickOnExportCSV();
        logger.log(LogStatus.PASS, "Click On ExportCSV");
        explorePage.clickOnExportXslx();
        logger.log(LogStatus.PASS, "Click On Export xslx");
        explorePage.clickOnActionButton();
        logger.log(LogStatus.PASS, "Click On Action Button");
        explorePage.verifyActionPopUpHeaders();
        explorePage.clickOnClosePopUp();
    }
}