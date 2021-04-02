package com.orangehrm.testcases;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.Test;

import com.orangehrm.framework.webdriver.WebDriverBase;
import com.orangehrm.pages.ForgotPwdPage;
import com.orangehrm.pages.LoginPage;

public class OrangeHrmTest extends WebDriverBase {

	@Test(dataProvider = "ApplicationTestData")
	public void verifyValidLogin(Map<String, String> testdata) throws Exception {
		initiateTestCaseLogs("verifyValidLogin");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.AppLogin(testdata);

	}

	@Test(dataProvider = "ApplicationTestData")
	public void InvalidUsername(Map<String, String> testdata) throws InterruptedException, IOException {
		initiateTestCaseLogs("InvalidUsername");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.InvalidUsername(testdata);
	}

	@Test(dataProvider = "ApplicationTestData")
	public void InvalidPwd(Map<String, String> testdata) throws InterruptedException, IOException {
		initiateTestCaseLogs("InvalidPwd");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.InvalidPwd(testdata);

	}

	@Test
	public void VerifyForgotPwdLink() throws InterruptedException, IOException {
		initiateTestCaseLogs("VerifyForgotPwdLink");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.VerifyForgotPwdLink();
	}

	@Test
	public void VerifyLinkeDin() throws InterruptedException, IOException {
		initiateTestCaseLogs("VerifyLinkeDin");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.VerifyLinkeDin();
	}

	@Test
	public void VerifyFaceBookBtn() throws InterruptedException, IOException {
		initiateTestCaseLogs("VerifyFaceBookBtn");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.VerifyFaceBookBtn();
	}

	@Test
	public void YoutubeBtn() throws InterruptedException, IOException {
		initiateTestCaseLogs("YoutubeBtn");
		LoginPage logiPage = LoginPage.getLoginPage();
		logiPage.YoutubeBtn();
	}

	@Test(dataProvider = "ApplicationTestData")
	public void ResetPwdWithOutData(Map<String, String> testdata) throws Exception {
		initiateTestCaseLogs("ResetPwdWithOutData");
		ForgotPwdPage RePwd = ForgotPwdPage.getForgotPwdPage();
		RePwd.ResetPwdWithOutData(testdata);
	}

	@Test(dataProvider = "ApplicationTestData")
	public void ResetPwdWitheData(Map<String, String> testdata) throws Exception {
		initiateTestCaseLogs("ResetPwdWitheData");
		ForgotPwdPage RePwd = ForgotPwdPage.getForgotPwdPage();
		RePwd.ResetPwdWitheData(testdata);
	}
	@Test(dataProvider = "ApplicationTestData")
	public void ResetPwdWitheWrongData(Map<String, String> testdata) throws Exception {
		initiateTestCaseLogs("ResetPwdWitheWrongData");
		ForgotPwdPage RePwd = ForgotPwdPage.getForgotPwdPage();
		RePwd.ResetPwdWitheWrongData(testdata);
	}
	
	@Test
	public void Cancel() throws Exception {
		initiateTestCaseLogs("ResetPwdWitheWrongData");
		ForgotPwdPage RePwd = ForgotPwdPage.getForgotPwdPage();
		RePwd.Cancel();
	}
}
