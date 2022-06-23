package com.modules.network;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.network.NetworkProfilePage;
import com.pages.orders.dashboard.PortInPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.genericUtils.BasePage.waitForPageLoad;

public class NetworkCreateTest extends BaseTest {

    @Test(priority = 27)
    public void testNetworkCreate() throws IOException, InterruptedException {
        logger = extent.startTest("Test Network Create");
        NetworkProfilePage networkProfilePage=new NetworkProfilePage(driver);
        logger.log(LogStatus.PASS, "Navigate To Network");
        networkProfilePage.navigateToNetwork();
        new  PortInPage(driver).changeProfile("Y228");
        BasePage.pageRefresh();
        BasePage.verifyElement(networkProfilePage.getNetworkPageTitle().getText(), "Network");
        networkProfilePage.clickOnCreate();
        logger.log(LogStatus.PASS, "Click on Network create");
        networkProfilePage.clickOnNetworkProfile();
        networkProfilePage.enterLATA(prop.getProperty("LATA"));
        networkProfilePage.enterLRN(prop.getProperty("LRN"));
        String desc = networkProfilePage.enterDescription();
        networkProfilePage.clickOnSaveChanges();
        logger.log(LogStatus.PASS, "Click on Save changes");
        BasePage.verifyElement(networkProfilePage.getNetworkPageTitle().getText(), "Network");
        waitForPageLoad();
        networkProfilePage.changeToLastPage();
        waitForPageLoad();
        BasePage.verifyElement(networkProfilePage.getLastDesc().getText(), desc);

    }
}
