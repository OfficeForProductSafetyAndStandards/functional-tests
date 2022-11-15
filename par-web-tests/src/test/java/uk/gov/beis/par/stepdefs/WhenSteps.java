package uk.gov.beis.par.stepdefs;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.beis.par.pagemodel.CreatePartnershipPage;
import uk.gov.beis.par.pagemodel.LoginPage;
import uk.gov.beis.par.pagemodel.SearchPartnershipPage;

public class WhenSteps extends SharedWebDriver
{
	
	private WebDriver driver;
	LoginPage loginPage;
	CreatePartnershipPage createPartnership;
	SearchPartnershipPage searchPartnership;
	
	
 public WhenSteps(SharedWebDriver driver) {
	 this.driver = driver;
	 loginPage = PageFactory.initElements(driver, LoginPage.class);
	 createPartnership = PageFactory.initElements(driver,CreatePartnershipPage.class);
	 searchPartnership = PageFactory.initElements(driver, SearchPartnershipPage.class);
 }
 @When("^I accept terms and conditions$")
 public void i_accept_terms_and_conditions() throws Throwable {
    createPartnership.confirm_terms_conditions();
     
 }

 @When("^I click on save$")
 public void i_click_on_save() throws Throwable {
	 
	
     
   
 }

 @When("^I click send invite$")
 public void i_click_send_invite() throws Throwable {
     createPartnership.click_continue();
     
 }

 @Then("^I should see \"(.*?)\"$")
 public void i_should_see(String arg1) throws Throwable {
     // Write code here that turns the phrase above into concrete actions
	 createPartnership.verify_par_page_header1(arg1);
	 Thread.sleep(2000);
     
 }

 @When("^I enter search term \"(.*?)\"$")
 public void i_enter_search_term(String arg1) throws Throwable {
    searchPartnership.search_by_keyword(arg1); 
 }

 @Then("^I should be able to find partnerships related to search term \"(.*?)\"$")
 public void i_should_be_able_to_find_partnerships_related_to_search_term(String arg1) throws Throwable {
	 searchPartnership.search_results(arg1);
     
 }
 
 @When("^I confirm on behalf organisation$")
 public void i_confirm_on_behalf_organisation() throws Throwable {
	 Thread.sleep(2000);
	// assertTrue("Failed:Not on confirmation page",createPartnership.verify_par_page_header1("Declaration for completion by proxy"));
     createPartnership.click_confirm_org_checkbox();
 }

 @When("^I select sic code  \"(.*?)\"$")
 public void i_select_sic_code(String arg1) throws Throwable {
     createPartnership.select_sic_code(arg1);;
 }

	

@When("^I select employee band \"(.*?)\"$")
public void i_select_employee_band(String arg1) throws Throwable {
	createPartnership.select_employee_band(arg1);
    
}
@When("^I click on dashboard link \"(.*?)\"$")
public void i_click_on_dashboard_link(String arg1) throws Throwable {
	createPartnership.click_by_text(arg1);
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


