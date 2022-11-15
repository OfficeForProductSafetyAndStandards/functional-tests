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
import uk.gov.beis.par.pagemodel.CreateEnforcementPage;
import uk.gov.beis.par.pagemodel.CreatePartnershipPage;
import uk.gov.beis.par.pagemodel.LoginPage;
import uk.gov.beis.par.pagemodel.SearchPartnershipPage;

public class ThenSteps extends SharedWebDriver
{
	
	private WebDriver driver;
	LoginPage loginPage;
	CreatePartnershipPage createPartnership;
	SearchPartnershipPage searchPartnerships;
	CreateEnforcementPage enforcement_page;
	
	
 public ThenSteps(SharedWebDriver driver) {
	 this.driver = driver;
	 loginPage = PageFactory.initElements(driver, LoginPage.class);
	 createPartnership = PageFactory.initElements(driver,CreatePartnershipPage.class);
	 searchPartnerships = PageFactory.initElements(driver,SearchPartnershipPage.class);
	 enforcement_page = PageFactory.initElements(driver,CreateEnforcementPage.class);
 }
	 
	
 @Then("^I should see partnership creation confirmation \"(.*?)\"$")
 public void i_should_see_partnership_creation_confirmation(String arg1) throws Throwable {
     // Write code here that turns the phrase above into concrete actions
    createPartnership.verify_par_page_header1(arg1);
 }
 
 
 @Then("^I should see \"(.*?)\" page$")
 public void i_should_see_page(String arg1) throws Throwable {
     // Write code here that turns the phrase above into concrete actions
	 createPartnership.verify_par_page_header1(arg1);
 }

 @Then("^I see invite email with the text \"(.*?)\"$")
 public void i_see_invite_email_with_the_text(String arg1) throws Throwable {
     // Write code here that turns the phrase above into concrete actions
	 createPartnership.verify_page_contains(arg1);
	 Thread.sleep(4000);
     
 }
 
 @Then("^I should see \"(.*?)\" link$")
 public void i_should_see_link(String arg1) throws Throwable {
	 
	 createPartnership.verify_element_by_text(arg1);
     
 }
 
 @Then("^I should not see \"(.*?)\" link$")
 public void i_should_not_see_link(String arg1) throws Throwable {
	
 searchPartnerships.Verify_link_not_displayed(arg1);
 }


@Then("^I should see \"(.*?)\" against each authority$")
public void i_should_see_against_each_authority(String arg1) throws Throwable {
    searchPartnerships.verify_element_by_text(arg1);
}
 
@Then("^I should see \"(.*?)\" against each organisation$")
public void i_should_see_against_each_organisation(String arg1) throws Throwable {
    searchPartnerships.verify_element_by_text(arg1);
}

@Then("^I should be able to send a \"(.*?)\" enforcement notice$")
public void i_should_be_able_to_send_a_enforcement_notice(String arg1) throws Throwable {
	Thread.sleep(2000);
	assertTrue("Failed: wrong page, check manually",createPartnership.verify_par_page_header1("Raise notice of enforcement action\n" + 
			"Contact details for enforcement officer"));
	 enforcement_page.validate_enforcement_contact_details();
	 searchPartnerships.click_continue_enforcement();
	 enforcement_page.select_new_entity();
	 enforcement_page.select_enforcement_type(arg1);
	 enforcement_page.enter_enforcement_details();
	 enforcement_page.review_enforcement_save();
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


