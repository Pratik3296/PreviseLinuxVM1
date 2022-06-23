package com.modules.explore;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.explore.ExplorePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class LocalNumberRangeLookupTest extends BaseTest {
    @Test(priority = 35)
    public void testLocalNumberRangeLookup() throws IOException, InterruptedException {
        logger = extent.startTest("Test Local Number Range Lookup");
        ExplorePage explorePage = new ExplorePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Explore");
        explorePage.navigateToExplore();
        BasePage.pageRefresh();
        BasePage.verifyElement(explorePage.getTitle().getText(), "Explore");
        explorePage.enterDataInSearchBox(prop.getProperty("localNumberRange"));
        logger.log(LogStatus.PASS, "Search TN Range b/w : "+prop.getProperty("localNumberRange"));
        explorePage.clickOnSearch();
        BasePage.verifyElement(explorePage.getSectionTitle().getText(), "Telephone Numbers");
        explorePage.verifyTNRange();
        explorePage.printSearchLocalNumber();
        explorePage.clickOnTN();
        logger.log(LogStatus.PASS, "Click on TN: "+prop.getProperty("localNumberRange"));
//        BasePage.verifyElement(explorePage.getTNPopUpHeader().getText(), prop.getProperty("localNumberRange"));
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