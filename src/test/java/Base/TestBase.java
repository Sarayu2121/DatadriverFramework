package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;


public class TestBase {
	
	
	//WebDriver
	//properties
	//logs---Log4j dependency,Logs,Log4j.properties,Logger file
	//reports
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	
	@BeforeSuite
	public void setUp() throws IOException {
		if(driver==null) {
		
		 fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			
			config.load(fis);
			log.debug("Config file loaded !!!");
			
		 fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			
			OR.load(fis);
			log.debug("OR file loaded !!!");
		if(config.getProperty("browser").equals("edge")) {
			
			System.setProperty("Webdriver.edge.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\msedgedriver.exe");
		    driver=new EdgeDriver();
		    log.debug("Edge Driver has Launched !!!");
		    
		}
		
		if(config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
		
		   driver = new ChromeDriver();
		   log.debug("Chrome Driver has Launched !!!");
		
		}
			
		driver.get(config.getProperty("testsiteurl"));
		log.debug("Navigated to----"+config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		
		wait = 	new WebDriverWait(driver, 5);
		}
		
		
		
	}
	public void click(String locator) {
		
		
		if(locator.endsWith("_CSS")){
		
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if(locator.endsWith("_XPATH")){
			
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			}
              else if(locator.endsWith("_ID")){
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
			}
		test.log(LogStatus.INFO, "Clicking on :"+locator);
		
	}
	
	public void type(String locator,String value) {
		
		if(locator.endsWith("_CSS")){
		
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")){
			
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			}else if(locator.endsWith("_ID")){
				
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
				}
		test.log(LogStatus.INFO, "Clicking on :"+locator+"Entering the values as :"+value);
		
	}
	
	static WebElement dropdown;
	public void select(String locator,String value) {
		
		if(locator.endsWith("_CSS")) {
			
			dropdown= driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}else if(locator.endsWith("_XPATH")) {
				
				dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
			}else if(locator.endsWith("_ID")) {
					
					dropdown= driver.findElement(By.id(OR.getProperty(locator)));
			}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		test.log(LogStatus.INFO, "Selecting from dropdown :  "+locator+"Value as :  "+value);
	}
	public static void verifyEquals(String expected,String actual) throws IOException {
		try {
		Assert.assertEquals(expected, actual);
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot();
			//ReportNG reports 
			Reporter.log("<br>"+"Verification Failure : "+t.getMessage()+"<br>");
			Reporter.log("<a target =\"_blank\" href = "+TestUtil.screenShotName+"><img src =  "+TestUtil.screenShotName+" height=200 width=200></img> </a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			//ExtentReports
			test.log(LogStatus.FAIL,"Verification Failed with Exception:"+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotName));
			
			
		}
		
	}
	public boolean IsElementPresent(By by ) {
		
		try {
			driver.findElement(by);
			return true;
		}catch(NoSuchElementException e) {
			return false;
		}
		
		
	}
	@AfterSuite
	public void tearDown() {
		if(driver!=null)
		driver.quit();
		log.debug("Test Excecution completed !!!");
	}
   
	 
}
