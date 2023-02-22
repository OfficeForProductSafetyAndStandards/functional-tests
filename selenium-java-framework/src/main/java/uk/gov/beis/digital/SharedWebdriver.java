/**
 * 
 */
package uk.gov.beis.digital;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author nasirkhan
 *
 */
public class SharedWebdriver {
	 public WebDriver driver;
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	
	public void  setDriver()
	{
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}

}
