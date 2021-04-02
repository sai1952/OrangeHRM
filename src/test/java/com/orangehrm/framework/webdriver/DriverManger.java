package com.orangehrm.framework.webdriver;

import org.openqa.selenium.WebDriver;

public class DriverManger {
	
	public static ThreadLocal<WebDriver> thread = new ThreadLocal<WebDriver>();
	
	public synchronized static WebDriver getDriver() {
		return thread.get();		
	}
	
	public synchronized static void setDriver(WebDriver driver) {
		thread.set(driver);		
	}
	

}
