package com.modules.network;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.network.NetworkProfilePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class NPANXXXBlockSearchTest extends BaseTest {
    @Test(priority = 33)
    public void testNPANXXXBlockSearch() throws IOException, InterruptedException {
        logger = extent.startTest("Test NPA-NXX-X Block Search");
        NetworkProfilePage networkProfilePage=new NetworkProfilePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Network");
        networkProfilePage.navigateToNetwork();
        BasePage.pageRefresh();
        BasePage.verifyElement(networkProfilePage.getNetworkPageTitle().getText(), "Network");
        networkProfilePage.enterDataInSearchBox(prop.getProperty("NPANXXXBlock"));
        logger.log(LogStatus.PASS, "Enter NPA-NXX-X Block Search code: "+prop.getProperty("NPANXXXBlock"));
        networkProfilePage.clickOnSearch();
        logger.log(LogStatus.PASS, "click on Search");
        logger.log(LogStatus.PASS, "Confirm Block Details Populated");
        BasePage.verifyElement("Block Details",networkProfilePage.getCodeHeader(0).getText());
        networkProfilePage.printBlockDetails();
        logger.log(LogStatus.PASS, "Confirm NPAC Details Populated ");
        BasePage.verifyElement("NPAC Details",networkProfilePage.getCodeHeader(1).getText());
        networkProfilePage.printNPACDetails();
        logger.log(LogStatus.PASS, " Confirm Geiographic Details Popluated");
        BasePage.verifyElement("Geographic Details",networkProfilePage.getCodeHeader(2).getText());
        networkProfilePage.printGeographicDetails();
        logger.log(LogStatus.PASS, " Confirm Contaminated Number list is popluated");
        BasePage.verifyElement("Contaminated Numbers",networkProfilePage.getCodeHeader(3).getText());
        networkProfilePage.printContaminatedNumbers();
        logger.log(LogStatus.PASS, "Confirm contaminated number list is exportable");
        BasePage.verifyElement("Export to .csv",networkProfilePage.getExportCSV().getText());
        networkProfilePage.clickOnExportCSV();
        BasePage.verifyElement("Export to .xslx",networkProfilePage.getExportToXSLX().getText());
        networkProfilePage.clickOnExportXslx();
    }
}
