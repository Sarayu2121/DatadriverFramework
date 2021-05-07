package Pages;

import java.nio.channels.NonWritableChannelException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./Browsers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://amazon.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
	}
	

}
