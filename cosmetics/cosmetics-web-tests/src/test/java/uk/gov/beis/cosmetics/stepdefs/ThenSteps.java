package uk.gov.beis.cosmetics.stepdefs;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import uk.gov.beis.cosmetics.pagemodel.AddProductPage;
import uk.gov.beis.cosmetics.pagemodel.LoginPage;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

public class ThenSteps extends BasePage{
	private WebDriver driver;
	private LoginPage loginPage;
	private AddProductPage addProductPage;


	public ThenSteps(SharedWebdriver shrdDriver) {
		super(shrdDriver);
		this.driver = shrdDriver.getDriver();
		
		loginPage = new LoginPage(shrdDriver);
		addProductPage = new AddProductPage(shrdDriver);
	}

	@Then("^I should be presented with anti-dandruff agents form page to enter details$")
	public void i_should_be_presented_with_anti_dandruff_agents_form_page_to_enter_details() throws Throwable {
		//addProductPage.verify_cosmetics_trigger_rules_question("List the anti-dandruff agents and their concentration");
		Thread.sleep(3000);
		//addProductPage.click_back_on_cosmetics_page();
	}

	@Then("^I should see trigger rule question \"(.*?)\"$")
	public void i_should_see_trigger_rule_question(String arg1) throws Throwable {
		addProductPage.verify_cosmetics_trigger_rules_question(arg1);
	}

	@Then("^I should be presented with trigger rule question \"(.*?)\"$")
	public void i_should_be_presented_with_trigger_rule_question(String arg1) throws Throwable {
	    addProductPage.verify_cosmetics_trigger_rules_question(arg1);
	    Thread.sleep(3000);
	    addProductPage.click_back_on_cosmetics_page();
	}

	@Then("^I should see error message \"(.*?)\"$")
	public void i_should_see_error_message(String arg1) throws Throwable {
	    addProductPage.verify_trigger_rule_error(arg1);
	}

	@Then("^I should see manual journey \"(.*?)\"$")
	public void i_should_see_manual_journey(String arg1) throws Throwable {
	   addProductPage.verify_cosmetics_page_headers(arg1);
	    //addProductPage.verify_cosmetics_trigger_rules_question(arg1);
	}
	
	@Then("^I should see annexes regulation \"(.*?)\"$")
	public void i_should_see_annexes_regulation(String arg1) throws Throwable {
	    addProductPage.verify_annexes(arg1);
	}
	
	@Then("^I should see check your answer page \"(.*?)\"$")
	public void i_should_see_check_your_answer_page(String arg1) throws Throwable {
		addProductPage.verify_cosmetics_page_headers(arg1); 
	}

	@Then("^I click back$")
	public void i_click_back() throws Throwable {
	    addProductPage.click_back_on_cosmetics_page();
	}

	@Then("^I should see free text pH field to enter$")
	public void i_should_see_free_text_pH_field_to_enter() throws Throwable {
		addProductPage.Is_trigger_rule_question_displayed();
	}

	@Then("^I should see free text to enter amount contains in product$")
	public void i_should_see_free_text_to_enter_amount_contains_in_product() throws Throwable {
		addProductPage.Is_trigger_rule_question_displayed();
	}
	@Then("^I dismiss any existing errors from error tab$")
	public void i_dismiss_any_existing_errors_from_error_tab() throws Throwable {
	    addProductPage.dismiss_error();
	}

@Then("^I should see min pH free text field$")
public void i_should_see_min_pH_free_text_field() throws Throwable {
    
}


@Then("^I should not see any error in error tab$")
public void i_should_not_see_any_error_in_error_tab() throws Throwable {
	addProductPage.verify_no_error();
    
}

@Then("^I should see notification in incomplete tab$")
public void i_should_see_notification_in_incomplete_tab() throws Throwable {
    
}

@Then("^I should see max pH free text field$")
public void i_should_see_max_pH_free_text_field() throws Throwable {
	addProductPage.verify_element_present();
   
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
