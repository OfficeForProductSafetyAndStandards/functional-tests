package uk.gov.beis.cosmetics.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uk.gov.beis.cosmetics.Utils.EnvironmentProperties;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

import static org.junit.Assert.assertTrue;

public class LoginPage extends BasePage {

	private WebDriver driver;

	private By usernameField = By.xpath("//input[@name='submit_user[email]']");
	private By passwordField = By.xpath("//input[contains(@name,'submit_user[password]')]");
	private By loginButton = By.xpath("//button[contains(@class,'govuk-button ')]");
	private By signInLink = By.xpath("//a[text()='Sign in']");
	private By signOutLink = By.xpath("//a[text()='Sign out']");
	private By otp_code = By.id("otp_code");

	
	SharedWebdriver shrdWebdriver;
	
	public LoginPage(SharedWebdriver shrdWebdriver) {
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
		
	}

	public void login_as(String username, String password) throws InterruptedException {
		if (this.IsElementDisplayed(signOutLink)) {
			this.click(signOutLink);
		}

		if (this.IsElementDisplayed(signInLink)) {
			this.click(signInLink);
			this.type(usernameField, username);
			this.type(passwordField, password);
			this.click(loginButton);
			if (this.IsElementDisplayed(otp_code)) {
				System.out.println("Found 2fa");
			this.type(otp_code, "11222");
			Thread.sleep(2000);
			this.click(loginButton);
			}
			
			Thread.sleep(4000);
		}

		assertTrue("Failed to sign in", this.IsElementDisplayed(signOutLink));
	}

	public void login_as_responsible_person() throws InterruptedException {
		login_as(EnvironmentProperties.getResponsiblePersonUsername(),
				EnvironmentProperties.getResponsiblePersonPassword());
	}

	public void login_as_poison_centre() throws InterruptedException {
		login_as(EnvironmentProperties.getPoisonCentreUsername(), EnvironmentProperties.getPoisonCentrePassword());
	}
}
