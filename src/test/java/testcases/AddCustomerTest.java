package testcases;



import java.util.Hashtable;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	
	
	@Test(dataProviderClass=TestUtil.class,dataProvider ="dp")
	
	//here  if we need to give more Columns then we need to add all the coumns instead of that we can use Hashtable<String,String>
	//public void addCustomerTest(String Fname,String Lname,String Pcode,String Alertmsg,String runmode) throws InterruptedException {
		public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
		//if(!(TestUtil.isTestRunnale("addCustomerTest", excel))) {
			
			//throw new SkipException("Skipping the test "+"addCustomerTest".toUpperCase()+"as the runmode as NO");
			
		//}
		
		if(!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test case as the Runmode for data set is NO");
		}
		click("Addcustomer_XPATH");
		type("Fname_XPATH",data.get("Fname"));
		type("Lname_XPATH",data.get("Lname"));
		type("Pcode_XPATH",data.get("Pcode"));
		click("AddcustomerBTN_XPATH");
		Reporter.log("Customer added Successfully");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(data.get("Alertmsg")));
		Thread.sleep(2000);
		alert.accept();
	  // Assert.fail("Customer not added Successfull");	
	}
    
	
}

