package com.orangehrm.framework.webdriver;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.orangehrm.framework.util.ExcelService;
import com.orangehrm.framework.util.ReadDataFromProps;

public class WebDriverBase {

	protected WebDriver driver;
	public static ExtentReports report = null;
	public static ExtentTest logger = null;

	public static Properties AppProperties() throws IOException {
		Properties prop = ReadDataFromProps.readprop(System.getProperty("user.dir") + "\\Resources\\application.properties");
		return prop;
		
	}
	
	@BeforeSuite
	public void setupReport() {
		ExtentHtmlReporter extent = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\ProjectTestReport_"+BasePage.getUniqueId()+".html");
		report = new ExtentReports();
		report.attachReporter(extent);
		report.setSystemInfo("Automated By", "Sai");
		report.setSystemInfo("Project Name", "Orange HRM Project");
		report.setSystemInfo("Browser", "Chrome");
	}
	
	public static void initiateTestCaseLogs(String testcasename) {
		logger = report.createTest(testcasename);
	}
	
	public static ExtentTest getLogger() {
		return logger;
	}

	@BeforeMethod(alwaysRun = true)

	public void setup() throws IOException {
		String BroswerName = AppProperties().getProperty("browser");
		if (BroswerName.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			DriverManger.setDriver(driver);

		} else if (BroswerName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			DriverManger.setDriver(driver);
		}

		else if (BroswerName.equalsIgnoreCase("IE")) {

			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\Drivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			DriverManger.setDriver(driver);

		}
		DriverManger.getDriver().manage().window().maximize();
		DriverManger.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	}

	@AfterMethod(alwaysRun = true)
	public void TearDown() {
		report.flush();
		driver.quit();
	}


	
	@DataProvider(name = "ApplicationTestData")
	
	public Iterator<Object[]> appdataprovider(Method method) throws IOException {
		return new ExcelService().readTestDataFromExcel(AppProperties().getProperty("TestDataWorkbook"), "Sheet1", method.getName());
		
	}
	
	
	
	
	
	
	
	
	
	
}
