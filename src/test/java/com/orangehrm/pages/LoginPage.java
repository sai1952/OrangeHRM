package com.orangehrm.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orangehrm.framework.webdriver.BasePage;
import com.orangehrm.framework.webdriver.WebDriverBase;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@id='txtUsername']")
	public WebElement UsernameTxtb;

	@FindBy(xpath = "//input[@id='txtPassword']")
	public WebElement PasswordTxtb;

	@FindBy(xpath = "//input[@id='btnLogin']")
	public WebElement SubmitBtn;

	@FindBy(xpath = "//img[contains(@src,'logo.png')]]")
	public WebElement AppLogo;

	@FindBy(xpath = "//div[contains(text(),'OrangeHRM 4.7')]")
	public WebElement FooterLbl;

	@FindBy(xpath = "//a[contains(@href,'linkedin')]")
	public WebElement LinkdinBtn;

	@FindBy(xpath = "//a[contains(@href,'facebook')]")
	public WebElement FacebookBtn;

	@FindBy(xpath = "//a[contains(@href,'twitter')]")
	public WebElement TwitterBtn;

	@FindBy(xpath = "//a[contains(@href,'youtube')]")
	public WebElement YoutubeBtn;

	@FindBy(xpath = "//a[contains(text(),'Forgot')]")
	public WebElement ForgotPswdlink;

	@FindBy(xpath = "//span[contains(text(),'Invalid')]")
	public WebElement InvalidMsgLbl;

	@FindBy(xpath = "//span[contains(text(),'Username cannot')]")
	public WebElement BlankMsgLbl;

	@FindBy(xpath = "//span[contains(text(),'Password cannot')]")
	public WebElement BlankPwdMsgLbl;

	@FindBy(xpath = "//a[@id='welcome']")
	public WebElement WlcmTitle;
	
	public void openAppUrl() throws IOException {
		driver.get(WebDriverBase.AppProperties().getProperty("app_url"));
	}

	public void AppLogin(Map<String, String> testdata) throws Exception {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		UpdateDataIntoField(UsernameTxtb, testdata.get("Username"));
		UpdateDataIntoField(PasswordTxtb, testdata.get("Password"));
		Click(SubmitBtn);
		hardWait(5);
		try {
			if (WlcmTitle.isDisplayed()) {
				Logger.pass("Login Successfully");
				System.out.println("Login Successfully");
			} else {
				System.out.println("Home Page not opened");
			}
		} catch (Exception e) {

			System.out.println("Login is not Successful");
			Logger.fail("Login is not Successful");
			Logger.addScreenCaptureFromPath(getScreenshot(driver,"Login Page"), "Login Page");
			Assert.fail("Login is not Successful");
		}

	}

	public void InvalidUsername(Map<String, String> testdata) throws InterruptedException, IOException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		UpdateDataIntoField(UsernameTxtb, testdata.get("Username"));
		UpdateDataIntoField(PasswordTxtb, testdata.get("Password"));
		Click(SubmitBtn);
		hardWait(5);
		if (InvalidMsgLbl.getText().contains("Invalid credentials")) {
			Logger.pass("login failed Successfully with invalid Username");
			System.out.println("login failed Successfully with invalid Username");
		} else {
			System.out.println("test failed ");
			Assert.fail("test failed ");
		}
	}

	public void InvalidPwd(Map<String, String> testdata) throws InterruptedException, IOException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		UpdateDataIntoField(UsernameTxtb, testdata.get("Username"));
		UpdateDataIntoField(PasswordTxtb, testdata.get("Password"));
		Click(SubmitBtn);
		hardWait(5);
		if (InvalidMsgLbl.getText().contains("Invalid credentials")) {
			Logger.pass("login failed Successfully with invalid Password");
			System.out.println("Login Successfully");
		} else {
			System.out.println("Login is not Successful");
			Assert.fail("Login is not Successful");
		}
	}

	public void VerifyForgotPwdLink() throws IOException, InterruptedException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		Click(ForgotPswdlink);

		String ActTitle = title();
		if (ActTitle.equalsIgnoreCase("OrangeHRM")) {
			Logger.pass("Clicked Successfully");
			System.out.println("Login Successfully");
		} else {
			System.out.println("Login is not Successful");
			Assert.fail("Login is not Successful");
		}

	}

	public void VerifyLinkeDin() throws IOException, InterruptedException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		
		
		String parent = driver.getWindowHandle();
		Click(LinkdinBtn);
		Set<String> s = driver.getWindowHandles();

		int count = s.size();

		System.out.println(count);

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				String title = driver.switchTo().window(child_window).getTitle();
				if (title.equals("Sign Up | LinkedIn")) {
					Logger.pass("Clicked Successfully");
					System.out.println("Login Successfully");
				} else {
					System.out.println("Login is not Successful");
					Assert.fail("Login is not Successful");
				}

			}

		}
	}

	public void VerifyFaceBookBtn() throws IOException, InterruptedException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		String parent = driver.getWindowHandle();
		Click(FacebookBtn);
		Set<String> s = driver.getWindowHandles();

		int count = s.size();

		System.out.println(count);

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				String title = driver.switchTo().window(child_window).getTitle();
				if (title.equals("OrangeHRM - World's Most Popular Opensource HRIS - Home | Facebook")) {
					Logger.pass("Clicked Successfully");
					System.out.println("Login Successfully");
				} else {
					System.out.println("Login is not Successful");
					Assert.fail("Login is not Successful");
				}

			}

		}
	}

	public void YoutubeBtn() throws IOException, InterruptedException {
		Logger = WebDriverBase.getLogger();
		openAppUrl();
		Thread.sleep(5000);
		
		
		String parent = driver.getWindowHandle();
		Click(YoutubeBtn);
		Set<String> s = driver.getWindowHandles();

		int count = s.size();

		System.out.println(count);

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				String title = driver.switchTo().window(child_window).getTitle();
				if (title.equals("OrangeHRM Inc - YouTube")) {
					Logger.pass("Clicked Successfully");
					System.out.println("Login Successfully");
				} else {
					System.out.println("Login is not Successful");
					Assert.fail("Login is not Successful");
				}

			}

		}
	}
	
	public static LoginPage getLoginPage() {
		LoginPage loginPage = PageFactory.initElements(new BasePage().driver, LoginPage.class);
		return loginPage;
	}

}
