package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage {

	@FindBy(xpath="//input[contains(@id,'searchSystemUser_userName')]")
	public WebElement UserNameTxtb; 
	
	@FindBy(xpath="//select[contains(@id,'searchSystemUser_userType')]")
	public WebElement UserRoleDrpdwn; 
	
	@FindBy(xpath="//input[contains(@id,'searchSystemUser_employeeName_empName')]")
	public WebElement EmpNameTxtb; 
	
	@FindBy(xpath="//select[contains(@id,'searchSystemUser_status')]")
	public WebElement StatusDrpDwn; 
	
	@FindBy(xpath="//input[contains(@id,'searchBtn')]")
	public WebElement SearchBtn; 
	
	@FindBy(xpath="//input[contains(@id,'btnDelete')]")
	public WebElement DeleteBtn; 
	
	
	
}
