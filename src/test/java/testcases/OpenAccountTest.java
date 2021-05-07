package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase{

	@Test(dataProviderClass=TestUtil.class,dataProvider ="dp")
	public void openAccountTest(Hashtable<String,String>data) {
	
		click("Openaccount_XPATH");
		select("Customer_XPATH", data.get("Customer"));
		select("Currency_XPATH", data.get("Currency"));
		click("Process_XPATH");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		 
		alert.accept();
		
	}
   
	
}
