package com.modules.explore;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.explore.ExplorePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class LocalNumberSearchGroupTest extends BaseTest {
    @Test(priority = 36)
    public void testLocalNumberGroup() throws IOException, InterruptedException {
        logger = extent.startTest("Test Local Number Group");
        ExplorePage explorePage = new ExplorePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Explore");
        explorePage.navigateToExplore();
        BasePage.pageRefresh();
        BasePage.verifyElement(explorePage.getTitle().getText(), "Explore");
        explorePage.enterDataInSearchBox(prop.getProperty("localNumberGroup"));
        logger.log(LogStatus.PASS, "Search TN Group : "+prop.getProperty("localNumberGroup"));
        explorePage.clickOnSearch();
        BasePage.verifyElement(explorePage.getSectionTitle().getText(), "Telephone Numbers");
        explorePage.verifyGroupList();
        explorePage.verifyTNRange();
        explorePage.printSearchLocalNumber();
        explorePage.clickOnTN();
        logger.log(LogStatus.PASS, "Click on TN: "+prop.getProperty("localNumberGroup"));
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