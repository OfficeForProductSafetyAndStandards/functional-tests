package uk.gov.beis.digital.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.beis.digital.mspsds.Utils.AppProperties;
import uk.gov.beis.digital.mspsds.Utils.EnvironmentProperties;
import uk.gov.beis.digital.mspsds.pagemodel.AssigneePage;
import uk.gov.beis.digital.mspsds.pagemodel.CasesPage;
import uk.gov.beis.digital.mspsds.pagemodel.LoginPage;

public class GivenSteps {

	private LoginPage loginPage;
	private AssigneePage assigneePage;
	private CasesPage casesPage;
	private String platform=AppProperties.get("platform");
	private WebDriver driver;
	
	private String kc_url = AppProperties.get("KC_URL");
	private String kc_pwd = AppProperties.get("KC_password");


	public GivenSteps(SharedWebDriver driver) {
		this.driver=driver;
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		assigneePage = PageFactory.initElements(driver, AssigneePage.class);
		casesPage = PageFactory.initElements(driver,CasesPage.class);
		
	}

	@Given("^I login as OPSS user$")
	public void i_login_as_OPSS_user() throws Throwable {
		loginPage.login_as_opss();
		loginPage.verifyPageTitle("Your cases - Product Safety Database - GOV.UK");
		
}

	@Given("^I login as Trading standard user$")
	public void i_login_as_Trading_standard_user() throws Throwable {
		loginPage.login_as_ts();
		//loginPage.verifyPageTitle("Home Page - Product safety database - GOV.UK");
	}

	@When("^I want to assign any case$")
	public void i_want_to_assign_any_case() throws Throwable {
		loginPage.open_mspsds_case("Cosmetics – burns hazard (no product specified)");
		Thread.sleep(4000);

		assigneePage.click_change_owner();
}
	@Given("^I login as keycloack Admin user$")
	public void i_login_as_keycloack_Admin_user() throws Throwable {
		loginPage.launch_app(kc_url);
		Thread.sleep(3000);
		loginPage.login_as_kc_admin("Admin",kc_pwd);
		//loginPage.login_as_kc_admin("",kc_pwd);
	    
	}

	@When("^I go to users$")
	public void i_go_to_users() throws Throwable {
		loginPage.go_to_users();
	}
	
	@Given("^I open case \"(.*?)\"$")
	public void i_open_case(String arg1) throws Throwable {
		casesPage.open_a_case(arg1);
	}
	@Given("^I add attachment to the case$")
	public void i_add_attachment_to_the_case() throws Throwable {
		casesPage.add_file_case();
	}
	
	@Given("^I go to attachment tab$")
	public void i_go_to_attachment_tab() throws Throwable {
		casesPage.click(casesPage.supporting_information);
	}

	@Then("^I should see error message \"(.*?)\"$")
	public void i_should_see_error_message(String arg1) throws Throwable {
		Thread.sleep(2000);
		casesPage.verify_error(arg1);
	}
	
	@Given("^I click link \"(.*?)\"$")
	public void i_click_link(String arg1) throws Throwable {
		casesPage.click_by_text(arg1);
	}

	
	@After()
	/*
	 * Embed a screenshot in test report if test is marked as failed
	 */
	public void embedScreenshot(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}


}
