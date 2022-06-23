package com.modules.orders.dashboard;

import com.genericUtils.BasePage;
import com.genericUtils.BaseTest;
import com.pages.orders.dashboard.DashboardPage;
import com.pages.orders.dashboard.PortInPage;
import com.pages.orders.dashboard.PortOutPage;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class PortOutTest extends BaseTest {
    @Test(priority = 14)
    public void testPortOut() throws IOException, InterruptedException {
        logger = extent.startTest("Test Port-Out");
        DashboardPage dashboardPage = new DashboardPage(driver);
        PortOutPage portOutPage = new PortOutPage(driver);
        PortInPage portInPage=new PortInPage(driver);
        dashboardPage.navigateToDashboard();
        logger.log(LogStatus.PASS,"Navigate To Dashboard");
        BasePage.pageRefresh();
        portOutPage.clickOnCreateButton();
        logger.log(LogStatus.PASS,"Clicked on Create");
        portOutPage.clickOnPortOutButton();
        logger.log(LogStatus.PASS,"Selected PortIn option");
        BasePage.verifyTitle("Port out");
        portInPage.changeProfile("Y128");
        portOutPage.verifyPagesTitle(0,"1: Enter porting numbers and generate card");
        BasePage.fileUpdate(prop.getProperty("uploadFileName"), Integer.parseInt(prop.getProperty("numberOfPortNumbers")));
        logger.log(LogStatus.PASS,prop.getProperty("uploadFileName")+ " File got Updated with new"+ Integer.parseInt(prop.getProperty("numberOfPortNumbers"))+" PortNumbers ");
        portOutPage.uploadFiles(prop.getProperty("uploadFileName"));
        portOutPage.selectServiceProvider();
        portOutPage.clickOnGenerateCard();
        portOutPage.verifyFileUpload();
        portOutPage.verifyPagesTitle(1,"2: Port out order");
        portOutPage.selectDueDate();
        String projectID = portOutPage.enterProjectID();
        portOutPage.clickOnCreateButton();
        System.out.println("PID"+projectID);
        dashboardPage.search(projectID);
        logger.log(LogStatus.PASS,"Searched with Project ID :"+projectID);
        dashboardPage.compareAndOpenId(projectID, "ProjectId");
        portOutPage.verifyPortTypeAndStatus("Pending");
        portInPage.changeProfile("Y228");

    }

}
