package com.modules.network;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.network.NetworkProfilePage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class NPANXXSearchTest extends BaseTest {
    @Test(priority = 32)
    public void testNPANXXSearch() throws IOException, InterruptedException {
        logger = extent.startTest("Test NPA-NXX Search");
        NetworkProfilePage networkProfilePage=new NetworkProfilePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Network");
        networkProfilePage.navigateToNetwork();
        BasePage.pageRefresh();
        BasePage.verifyElement(networkProfilePage.getNetworkPageTitle().getText(), "Network");
        networkProfilePage.enterDataInSearchBox(prop.getProperty("NPANXXSearch"));
        logger.log(LogStatus.PASS, "Enter NPA-NXX Search code: "+prop.getProperty("NPANXXSearch"));
        networkProfilePage.clickOnSearch();
        logger.log(LogStatus.PASS, "click on Search");
        BasePage.verifyElement("Code",networkProfilePage.getCodeHeader(0).getText());
        networkProfilePage.printCodeDataInformation();

    }
}
