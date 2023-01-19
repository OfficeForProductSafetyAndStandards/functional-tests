/**
 * 
 */
package uk.gov.beis.cosmetics.stepdefs;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import uk.gov.beis.cosmetics.Utils.AppProperties;
import uk.gov.beis.digital.SharedWebdriver;



/**
 * @author nasirkhan
 *
 */
public class Hooks {

	WebDriver driver;
	SharedWebdriver shrdDriver;

	String envurl = AppProperties.get("envurl");

	public Hooks(SharedWebdriver shrdDriver) {
		this.shrdDriver = shrdDriver;
		// this.driver = basepge.getDriver();
	}

	@Before
	public void bf() {
		shrdDriver.setDriver();
		shrdDriver.getDriver().navigate().to(envurl);
	}

	@After
	public void af() {
		shrdDriver.getDriver().quit();
	}
}
