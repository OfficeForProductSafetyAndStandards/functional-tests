/**
 * 
 */
package uk.gov.beis.cosmetics.stepdefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import uk.gov.beis.cosmetics.Utils.AppProperties;





/**
 * @author nasirkhan
 *
 */
public class Hooks {

	WebDriver driver;
	InitialiseDriverObject driverobject;

	String envurl = AppProperties.get("envurl");

	public Hooks(InitialiseDriverObject driverobject) {
		this.driverobject = driverobject;
		
	}

	@Before
	public  void bf() {
		driverobject.setDriver();
		driverobject.getDriver().navigate().to(envurl);
	}

	@After
	public void af() {
		//shrdDriver.getDriver().quit();
	}
}
