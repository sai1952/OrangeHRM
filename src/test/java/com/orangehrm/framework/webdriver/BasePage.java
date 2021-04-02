package com.orangehrm.framework.webdriver;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {
	public WebDriver driver;
	public static ExtentTest Logger ;
	
	public BasePage() {
		if(driver == null) {
			this.driver = DriverManger.getDriver();
		}
	}

	public void Click(WebElement pageelement) {
		if (pageelement.isDisplayed()) {
			System.out.println("Element is Available");
		} else {
			System.out.println("Element is Not Available");
			Assert.fail("Element is Not Available");
		}

		if (pageelement.isEnabled()) {
			System.out.println("Element is Enabled");
		} else {
			System.out.println("Element is Disabled");
			Assert.fail("Element is Disabled");
		}

//		pageelement.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageelement);
	}

	public void UpdateDataIntoField(WebElement pageelement, String value) {

		
		 if (pageelement.isDisplayed()) { System.out.println("Element is Available");
		 } else { System.out.println("Element is Not Available");
		 Assert.fail("Element is Not Available"); }
		 
		  if (pageelement.isEnabled()) { System.out.println("Element is Enabled"); }
		 else { System.out.println("Element is Disabled");
		  Assert.fail("Element is Disabled"); }
		 
		pageelement.sendKeys(value);
	}

	public void SelectListBox(WebElement pageelement, String selectby, String value) {

		if (pageelement.isDisplayed()) {
			System.out.println("Element is Available");
		} else {
			System.out.println("Element is Not Available");
			Assert.fail("Element is Not Available");
		}

		if (pageelement.isEnabled()) {
			System.out.println("Element is Enabled");
		} else {
			System.out.println("Element is Disabled");
			Assert.fail("Element is Disabled");
		}
		Select select = new Select(pageelement);

		if (selectby.equalsIgnoreCase("ByVisibleText")) {
			select.selectByVisibleText(value);
		} else if (selectby.equalsIgnoreCase("value")) {
			select.selectByValue(value);
		} else if ((selectby.equalsIgnoreCase("Index"))) {
			select.selectByIndex(Integer.parseInt(value));
		}

	}

	public void hardWait(int seconds) throws InterruptedException {
		Thread.sleep(seconds * 1000);
	}

	public void Scrolling(WebElement pageelement) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", pageelement);
	}

	public void PageLoadTimeOut(int T, TimeUnit TimeUnit) {

		driver.manage().timeouts().pageLoadTimeout(T, TimeUnit);
	}

	public void setScript(int T, TimeUnit TimeUnit) {

		driver.manage().timeouts().setScriptTimeout(T, TimeUnit);
	}

	public void implicitywait(int T, TimeUnit TimeUnit) {
		driver.manage().timeouts().implicitlyWait(T, TimeUnit);
	}

	public String title() {
		return	driver.getTitle();
	
	}
	
	public static String getUniqueId() {
		SimpleDateFormat format = new SimpleDateFormat("MMddyyyyHHmm");
		Calendar cal = Calendar.getInstance();
		String UniqueID = format.format(cal.getTime());
		return UniqueID; 
	} 
	
	public static String getScreenshot(WebDriver driver, String screenshotname) throws Exception {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+"/FailedTestsScreenshots/"+ screenshotname + getUniqueId() + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source,finalDestination);
		return destination;
	}

}
