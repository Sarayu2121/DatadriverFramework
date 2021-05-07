package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import Base.TestBase;
import utilities.TestUtil;

public class CustumListeners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		test = report.startTest(result.getName().toUpperCase());
		
		//checking the runmode...if runmode=Y it has to execute if runmode=N then the test shoul ot execute
		if(!TestUtil.isTestRunnale(result.getName(), excel)) {
		
			
			throw new SkipException("Skipping the test"+result.getName().toUpperCase()+" as the Runmode is NO");
		}
		
		
	}

	public void onTestSuccess(ITestResult result) {

          test.log(LogStatus.PASS, result.getName().toUpperCase()+"PASS");
          report.endTest(test);
		  report.flush();
		  
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		//this is to see the Report logs with screenshot in Test-output html 
				//then we need to create this from Reportng
				System.setProperty("org.uncommons.reportng.escape-output", "false");
				 
		
		
		//this Reporter log will create the  message in the Report
				try {
					TestUtil.captureScreenshot();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 test.log(LogStatus.FAIL,result.getName().toUpperCase()+"Failed with Exception :"+result.getThrowable());
		          test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotName));
		          
		          
		          
				Reporter.log("Click to see the  Screenshot");
				Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotName+">Screenshot</a>");
				
				Reporter.log("<br>");
				Reporter.log("<a target=\"_blank\" href="+TestUtil.screenShotName+"><img src="+TestUtil.screenShotName+" height=200 width=200></img></a>");
				report.endTest(test);
				  report.flush();
				  
		
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP,result.getName().toUpperCase()+"Skipping the test as the Runmode is  NO");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	

}
