package com.pages.explore;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static com.genericUtils.BasePage.*;
import static com.genericUtils.BaseTest.logger;

public class ExplorePage {
    String spid;
    public WebDriver driver;
    @FindBy(xpath = "//span[.='Explore']/..")
    private WebElement explore;
    @FindBy(xpath = "//input[@placeholder='Look up any local or toll-free number.']")
    private WebElement search;
    @FindBy(xpath = "//button[.='Search']")
    private WebElement searchButton;
    @FindBy(xpath = "//h3")
    private List<WebElement> sectionTitle;
    @FindBy(xpath = "//h1[@class='display-3 pc-number-explorer-bg']")
    private WebElement title;
    @FindBy(xpath = "//div[@role='columnheader']")
    private List<WebElement> header;
    @FindBy(xpath = "//tr[@role='row']//td|//tr[@role='row']//td//button")
    private List<WebElement> rowsValues;
    @FindBy(xpath = "//div[@class='avatar rounded-circle bg-primary']/../../../..//h3")
    private List<WebElement> historyReport;
    @FindBy(xpath ="//a[@download='result.csv']" )
    private WebElement exportCSV;
    @FindBy(xpath = "(//button[@type='button'])[5]")
    private WebElement exportToXSLX;
    @FindBy(xpath = "(//div[contains(@class,'mb-0')])[4]|//h5[contains(@class,'mb-0')]")
    private List<WebElement> SPIDHeader;
    @FindBy(xpath = "//i[@class='far fa-times text-primary']")
    private WebElement closePopUp;
    @FindBy(xpath = "//*[@id=\"root\"]/div[2]/div/div[3]/div/div[2]/table/tbody/tr[1]/td[13]/i")
    private WebElement actionButton;
    @FindBy(xpath = "//table//tbody//tr//td//span")
    private List<WebElement> telephoneNumberList;
    @FindBy(xpath = "//h3")
    private List<WebElement> actionPopUpHeaders;
    @FindBy(xpath = "//span[@class='cursor-pointer text-primary text-underline']")
    private List<WebElement>  groupNumbersList;
    public ExplorePage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }
    public void navigateToExplore() throws InterruptedException {
        Actions action = new Actions(driver);
        Thread.sleep(10000);
        action.moveToElement(explore).clickAndHold(explore).pause(1000).click(explore).build().perform();
    }
    public void enterDataInSearchBox(String tn) throws IOException {
        enterValue(search,tn);
    }
    public void clearDataInSearchBox() throws IOException {
        elementClear(search);
    }
    public void clickOnSearch() throws IOException {
        elementClick(searchButton);
    }
    public void printSearchLocalNumber(){
        spid=rowsValues.get(2).getText();
        ArrayList column1 = new ArrayList();
        ArrayList column2 = new ArrayList();
        for (int j = 0; j< telephoneNumberList.size(); j++) {

            for (int i = 0; i < header.size(); i++) {

                column1.add(header.get(i).getText());
            }
            for (int i = 0; i < rowsValues.size(); i++) {
                if (i != 1) {
                    column2.add(rowsValues.get(i).getText());
                }
            }
            for (int i = 0; i < header.size(); i++) {
                logger.log(LogStatus.PASS, column1.get(i) + " : " + column2.get(i) + "\n");
            }
            column1.clear();
            column2.clear();
        }
    }
    public WebElement getExportCSV() {
        return exportCSV;
    }

    public WebElement getExportToXSLX() {
        return exportToXSLX;
    }
    public void clickOnExportCSV() throws IOException {
        elementClick(exportCSV);
    }
    public void clickOnExportXslx() throws IOException {
        elementClick(exportToXSLX);
    }
    public void verifySPIDHeader(){
        int i=Integer.parseInt(spid.replace("Y",""));
        waitForPageLoad();
        SoftAssert softAssert = new SoftAssert();
        if(i==228){
            softAssert.assertEquals(SPIDHeader.get(0).getText(),"Y228/1");
            softAssert.assertEquals(SPIDHeader.get(1).getText(),"SPID Y228");
        }
        else if(i==128){
            softAssert.assertEquals(SPIDHeader.get(0).getText(),"PortControl SPID Y128");
            softAssert.assertEquals(SPIDHeader.get(1).getText(),"SPID Y128");
        }
        softAssert.assertEquals(SPIDHeader.get(2).getText(),"Wireline");
        softAssert.assertEquals(SPIDHeader.get(3).getText(),"United");
    }

    public WebElement getSectionTitle() {
        return sectionTitle.get(0);
    }

    public WebElement getTitle() {
        return title;
    }
    public void clickOnTN() throws IOException {
        elementClick(rowsValues.get(0));
    }
    public WebElement getTNPopUpHeader(){
        return sectionTitle.get(1);
    }
    public WebElement getTNPopUpSectionHeader(){
        return sectionTitle.get(2);
    }
    public void clickOnSpid() throws IOException {
        elementClick(rowsValues.get(2));
    }
    public void clickOnClosePopUp() throws IOException {
        elementClick(closePopUp);
    }
    public void verifyTNRange(){
        ArrayList tn = new ArrayList();
        for (WebElement element : telephoneNumberList) {
            tn.add(element.getText());
        }
        if(telephoneNumberList.size()>1){
            logger.log(LogStatus.PASS, "Searched TN's: "+tn);

        }
        else {
            logger.log(LogStatus.INFO, "Searched TN's: "+tn);
        }
    }
    public void clickOnActionButton() throws IOException {
        elementClick(actionButton);
    }
    public void verifyActionPopUpHeaders(){
        waitForPageLoad();
        for (int i = 0; i < actionPopUpHeaders.size(); i++) {
            if(i==2){
                verifyElement("DPC/SSN data",actionPopUpHeaders.get(i).getText());
            }
            else if(i==3){
                verifyElement("Optional data",actionPopUpHeaders.get(i).getText());
            }
        }
    }
    public void verifyGroupList(){
        if(groupNumbersList.size()==3){
            logger.log(LogStatus.INFO, "Searched Group displayed");
        }
        else {
            logger.log(LogStatus.WARNING, "Searched Group not displayed");

        }
    }
}
