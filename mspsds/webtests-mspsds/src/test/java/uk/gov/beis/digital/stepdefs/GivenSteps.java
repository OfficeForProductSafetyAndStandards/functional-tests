package uk.gov.beis.digital.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.core.*;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.Utils.AppProperties;
import uk.gov.beis.digital.mspsds.Utils.EnvironmentProperties;
import uk.gov.beis.digital.mspsds.pagemodel.AssigneePage;
import uk.gov.beis.digital.mspsds.pagemodel.CasesPage;
import uk.gov.beis.digital.mspsds.pagemodel.LoginPage;

public class GivenSteps extends BasePage {

	private LoginPage loginPage;
	private AssigneePage assigneePage;
	private CasesPage casesPage;
	private String platform=AppProperties.get("platform");
	//private WebDriver driver;
	
	SharedWebdriver shrdDriver;
	
	public GivenSteps(SharedWebdriver driver)
	{
		super(driver);
		shrdDriver=driver;
		//this.driver= basepge.getDriver();
		loginPage = new LoginPage(shrdDriver);
		casesPage = new CasesPage(shrdDriver);
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
		
		assigneePage.click_change_owner();
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
	
	
	//New-flow test cases
	
	@Given("I go to {string} in left nav")
	public void i_go_to_in_left_nav(String string) {
		casesPage.click_link_case_left_nav(string);
	}

	@Given("I go to enter product reference number page")
	public void i_go_to_enter_product_reference_number_page() {
		casesPage.click_link_case_left_nav("Products");
		casesPage.click_link_on_summary_page("Add a product to the case");
	}

	
//	@After()
//	/*
//	 * Embed a screenshot in test report if test is marked as failed
//	 */
//	public void embedScreenshot(Scenario scenario) {
//		if (scenario.equals("failed:")) {
//			try {
//			//	scenario.write("Current Page URL is " + driver.getCurrentUrl());
//				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				//scenario.attach(screenshot, "image/png");
//			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
//				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
//			}
//		}
//	}


}
