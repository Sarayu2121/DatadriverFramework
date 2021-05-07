package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	@Test
	public void bankmanagerLoginTest() throws InterruptedException, IOException {
		
		verifyEquals("abc", "xyz");
		log.debug("Inside Login Test");
		
		click("BankManagerBtn_XPATH");
		Thread.sleep(2000);
		
		Assert.assertTrue(IsElementPresent(By.xpath(OR.getProperty("Addcustomer_XPATH"))),"Login not Successfull");
		log.debug("Login Test Successfully Excecuted");
		//Assert.fail("Login not Successfull");
		
	}

}
