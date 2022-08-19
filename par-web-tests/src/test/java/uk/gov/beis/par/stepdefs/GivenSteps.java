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
import junit.framework.Assert;
import uk.gov.beis.par.pagemodel.CreateEnforcementPage;
import uk.gov.beis.par.pagemodel.CreatePartnershipPage;
import uk.gov.beis.par.pagemodel.LoginPage;
import uk.gov.beis.par.pagemodel.SearchPartnershipPage;

public class GivenSteps extends SharedWebDriver
{
	
	private WebDriver driver;
	LoginPage loginPage;
	CreatePartnershipPage createPartnership;
	SearchPartnershipPage searchPartnerships;
	CreateEnforcementPage enforcement_page;
	
	
 public GivenSteps(SharedWebDriver driver) {
	 this.driver = driver;
	 loginPage = PageFactory.initElements(driver, LoginPage.class);
	 createPartnership = PageFactory.initElements(driver,CreatePartnershipPage.class);
	 searchPartnerships = PageFactory.initElements(driver, SearchPartnershipPage.class);
	 enforcement_page = PageFactory.initElements(driver,CreateEnforcementPage.class);
 }
	 
	
		
		@Given("^I login as \"(.*?)\"$")
		public void i_login_as(String arg1) throws Throwable {
			loginPage.login_as(arg1,"Nasir@123");
			
		   
		}

		@Then("^I should be able to see \"(.*?)\"$")
		public void i_should_be_able_to_see(String arg1) throws Throwable {
		   
		    
		}

		
		@When("^I click on \"(.*?)\"$")
		public void i_click_on(String arg1) throws Throwable {
		    loginPage.click_by_text(arg1);
		}

		@When("^I fill-in the create partnershp form$")
		public void i_fill_in_the_create_partnershp_form() throws Throwable {
		   createPartnership.Enteraboutpartnership();
		}

		@When("^I select authority \"(.*?)\"$")
		public void i_select_authority(String arg1) throws Throwable {
			createPartnership.select_radio_button_by_text(arg1);
		    
		}

		@When("^I select partnership type \"(.*?)\"$")
		public void i_select_partnership_type(String arg1) throws Throwable {
			createPartnership.select_radio_button_by_text(arg1);
			Thread.sleep(2000);
			createPartnership.click_continue();
			Thread.sleep(3000);
		}
		
		@When("^I click continue$")
		public void i_click_continue() throws Throwable {
		   createPartnership.click_continue();
		}
		
		@When("^I fill in partnership details$")
		public void i_fill_in_partnership_details() throws Throwable {
		   createPartnership.click_confirm_checkbox();
		   createPartnership.fill_in_partnership_details();
		   createPartnership.fill_org_mem_details();
		}
		@Given("^I search for a partnership \"(.*?)\"$")
		public void i_search_for_a_partnership(String arg1) throws Throwable {
		   createPartnership.click_by_text("Search for a partnership");
		   searchPartnerships .search_by_keyword(arg1);
		}

		@Given("^I open partnership page$")
		public void i_open_partnership_page() throws Throwable {
			searchPartnerships.open_partnership_page();
			
		}

		@When("^I click send enforcement notice$")
		public void i_click_send_enforcement_notice() throws Throwable {
		    // Write code here that turns the phrase above into concrete actions
		   searchPartnerships.click_by_text("Send a notification of a proposed enforcement action");
		   Thread.sleep(5000);
		  assertTrue("Failed:Page not loaded properly",searchPartnerships.verify_par_page_header1("Raise notice of enforcement action\n" + 
		   "Have you discussed this issue with the Primary Authority?"));
		  
		}
		@When("^I click continue enforcement$")
		public void i_click_continue_enforcement() throws Throwable {
		    searchPartnerships.click_continue_enforcement();
		}
	

		@Then("^I should be able to send a proposed notificaiton$")
		public void i_should_be_able_to_send_a_proposed_notificaiton() throws Throwable {
			Thread.sleep(2000);
			assertTrue("Failed: wrong page, check manually",createPartnership.verify_par_page_header1("Raise notice of enforcement action\n" + 
					"Contact details for enforcement officer"));
			 enforcement_page.validate_enforcement_contact_details();
			 searchPartnerships.click_continue_enforcement();
			 enforcement_page.select_new_entity();
			 enforcement_page.select_enforcement_type("proposed");
			 enforcement_page.enter_enforcement_details();
			 enforcement_page.review_enforcement_save();
			 
		    // Write code here that turns the phrase above into concrete actions
		   // throw new PendingException();
			
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


