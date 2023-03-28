/**
 * 
 */
package uk.gov.beis.digital.stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.Utils.AppProperties;
import uk.gov.beis.digital.mspsds.Utils.EnvironmentProperties;

/**
 * @author nasirkhan
 *
 */
public class Hooks {
	
	WebDriver driver;
	SharedWebdriver shrdDriver;
	
	String envurl = AppProperties.get("envurl");
	
	public Hooks(SharedWebdriver shrdDriver)
	{
		this.shrdDriver=shrdDriver;
		//this.driver = basepge.getDriver();
	}

	@Before
	public void bf()
	{
		shrdDriver.setDriver();
		shrdDriver.getDriver().navigate().to(envurl);
	}
	
	
	@After
	public void af()
	{
	shrdDriver.getDriver().quit();
	}
}
