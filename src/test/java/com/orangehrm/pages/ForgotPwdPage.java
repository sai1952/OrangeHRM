package com.orangehrm.pages;

import java.io.IOException;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.framework.webdriver.BasePage;
import com.orangehrm.framework.webdriver.WebDriverBase;

public class ForgotPwdPage extends BasePage{

	@FindBy(xpath="//img[contains(@src,'webres')]")
	public WebElement AppLogo; 
	
	@FindBy(xpath="//input[contains(@id,'securityAuthentication_userName')]")
	public WebElement UsernameTxtb; 
	
	@FindBy(xpath="//input[contains(@id,'btnSearchValues')]")
	public WebElement RstBtn; 
	
	@FindBy(xpath="//input[contains(@id,'btnCancel')]")
	public WebElement CancelBtn; 
	
	@FindBy(xpath="//a[contains(@href,'linkedin')]")
	public WebElement LinkdinBtn;
	
	@FindBy(xpath="//a[contains(@href,'facebook')]")
	public WebElement FacebookBtn;
	
	@FindBy(xpath="//a[contains(@href,'twitter')]")
	public WebElement TwitterBtn;
	
	@FindBy(xpath="//a[contains(@href,'youtube')]")
	public WebElement YoutubeBtn;
	
	
	@FindBy(xpath="//*[contains(text(),'Could not find a user with given details')]")
	public WebElement  BlankMsg;
	
	

	@FindBy(xpath="//*[contains(text(),'Instructions for resetting your password have been sent to paul1@osohrm.com')]")
	public WebElement  ResetMsg;
	
	@FindBy(xpath="//*[contains(text(),'Please contact HR admin in order to reset the password')]")
	public WebElement  WrongUsenameMsg;
	
	@FindBy(xpath="//div[@id='logInPanelHeading']")
	public WebElement  MainPagEPanel;
	
	
	public void openAppUrlPwdReset() throws IOException {
		driver.get(WebDriverBase.AppProperties().getProperty("app_url"));
		driver.findElement(By.xpath("//a[contains(text(),'Forgot')]")).click();
	} 
	
	public void ResetPwdWithOutData(Map<String, String> testdata) throws Exception {
		
		Logger = WebDriverBase.getLogger();
		openAppUrlPwdReset();
		Thread.sleep(5000);
		
		
		UpdateDataIntoField(UsernameTxtb,testdata.get("Username")); 
		Click(RstBtn);
		
		
		try {
		if(BlankMsg.isDisplayed()) {
			Logger.pass("text displayed ");
			System.out.println("text displayed");
		} else {
			System.out.println("text not displayed");
		}
		}catch (Exception e) {
			System.out.println("unable to indentify the msg");
			Logger.fail("unable to indentify the msg");
			Logger.addScreenCaptureFromPath(getScreenshot(driver,"Forgot Pwd"), "Forgot Pwd");
			Assert.fail("Login is not Successful");
		}
		
	}
	
	
public void ResetPwdWitheData(Map<String, String> testdata) throws Exception {
		
		Logger = WebDriverBase.getLogger();
		openAppUrlPwdReset();
		Thread.sleep(5000);
		
		
		UpdateDataIntoField(UsernameTxtb,testdata.get("Username")); 
		Click(RstBtn);
		
		
		try {
		if(ResetMsg.isDisplayed()) {
			Logger.pass("Password Sent to email Successfully");
			System.out.println("Password Sent to email Successfully");
		} else {
			System.out.println("Password not Sent to email Successfully");
		}
		}catch (Exception e) {
			System.out.println("unable to indentify the msg");
			Logger.fail("unable to indentify the msg");
			Logger.addScreenCaptureFromPath(getScreenshot(driver,"Forgot Pwd"), "Forgot Pwd");
			Assert.fail("Login is not Successful");
		}
		
	}

public void ResetPwdWitheWrongData(Map<String, String> testdata) throws Exception {
	
	Logger = WebDriverBase.getLogger();
	openAppUrlPwdReset();
	Thread.sleep(5000);
	
	
	UpdateDataIntoField(UsernameTxtb,testdata.get("Username")); 
	Click(RstBtn);
	
	
	try {
	if(WrongUsenameMsg.isDisplayed()) {
		Logger.pass("Valid Msg");
		System.out.println("Valid Msg");
	} else {
		System.out.println("Valid Msg is not displayed");
	}
	}catch (Exception e) {
		System.out.println("unable to indentify the msg");
		Logger.fail("unable to indentify the msg");
		Logger.addScreenCaptureFromPath(getScreenshot(driver,"Forgot Pwd"), "Forgot Pwd");
		Assert.fail("Login is not Successful");
	}
	
}
	public void Cancel() throws Exception {
		Logger = WebDriverBase.getLogger();
		openAppUrlPwdReset();
		Thread.sleep(5000);
		Click(CancelBtn);
		
	try {	
		if(MainPagEPanel.isDisplayed()) {
			Logger.pass("Valid Msg");
			System.out.println("Valid Msg");
		} else {
			System.out.println("Valid Msg is not displayed");
		}
		}catch (Exception e) {
			System.out.println("unable to indentify the msg");
			Logger.fail("unable to indentify the msg");
			Logger.addScreenCaptureFromPath(getScreenshot(driver,"Forgot Pwd"), "Forgot Pwd");
			Assert.fail("Login is not Successful"); 
			Logger.pass("Valid Msg");
			System.out.println("Valid Msg");
		
	}}
	
	
	
	public static ForgotPwdPage getForgotPwdPage() {
		ForgotPwdPage ForgotPage = PageFactory.initElements(new BasePage().driver, ForgotPwdPage.class);
		return ForgotPage;
	}

}

