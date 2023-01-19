package uk.gov.beis.cosmetics.stepdefs;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.beis.cosmetics.pagemodel.AddProductPage;
import uk.gov.beis.cosmetics.pagemodel.LoginPage;
import uk.gov.beis.cosmetics.pagemodel.NanoMaterialPage;
import uk.gov.beis.digital.SharedWebdriver;

public class WhenSteps extends SharedWebDriver {

	private WebDriver driver;
	private LoginPage loginPage;
	private AddProductPage addProductPage;
	private NanoMaterialPage nanoMaterialPage;
	SharedWebDriver shrdDriver;

	public WhenSteps(SharedWebdriver shrdDriver) {
		
		this.driver = shrdDriver.getDriver();
		
		loginPage =  new LoginPage(shrdDriver);
		addProductPage = new AddProductPage(shrdDriver);
		nanoMaterialPage = new NanoMaterialPage(shrdDriver);
	}

	@When("^I enter internal reference number \"(.*?)\"$")
	public void i_enter_internal_reference_number(String arg1) throws Throwable {
		addProductPage.enter_reference_number(arg1);
	}

	@When("^I select physical form as \"(.*?)\"$")
	public void i_select_physical_form_as(String arg1) throws Throwable {
		addProductPage.select_radio_button_by_text(arg1);
		addProductPage.click_continue();
	}

	@When("^I click on continue$")
	public void i_click_on_continue() throws Throwable {
		Thread.sleep(2000);
		addProductPage.click_continue_on_cos();
	}

	@When("^I should see manual-journey step \"(.*?)\"$")
	public void i_should_see_manual_journey_step(String arg1) throws Throwable {
		addProductPage.verify_cosmetics_trigger_rules_question(arg1);
	}

	@When("^I notify product manually$")
	public void i_notify_product_manually() throws Throwable {
		addProductPage.click_add_cosmetics_product();
		addProductPage.select_radio_button_by_text("No, they have not been notified in the EU");
		addProductPage.click_continue_on_cos();
		addProductPage.select_radio_button_by_text("No");
		addProductPage.click_continue_on_cos();
		addProductPage.enter_productname("TestManual-Prod");
		addProductPage.enter_reference_number("TestRef-2003");
		addProductPage.select_radio_button_by_text("Yes");
		addProductPage.click_continue_on_cos();
		addProductPage.enter_country_imported_from("India");
		addProductPage.click_continue_on_cos();
		addProductPage.select_radio_button_by_text("The cosmetic product is a single item");
		addProductPage.click_continue_on_cos();
		addProductPage.select_radio_button_by_text("No");
		addProductPage.click_continue_on_cos();
		addProductPage.select_radio_button_by_text("Loose powder");
		addProductPage.click_continue_on_cos();
		addProductPage.select_radio_button_by_text("No");
		addProductPage.click_continue();
		addProductPage.select_radio_button_by_text("No");
		addProductPage.click_continue_on_cos();
		addProductPage.select_prod_category("Skin care products - Hand care products");
		addProductPage.select_radio_button_by_text("Choose a predefined frame formulation");
		addProductPage.click_continue_on_cos();
	}

	@When("^I choose frame formulation$")
	public void i_choose_frame_formulation() throws Throwable {
		addProductPage.select_frame_formulation("Skin Care Cream, Lotion, Gel");
		addProductPage.click_continue_on_cos();
	}

	@Then("^I should be asked if the product contains anti-dandruff agents$")
	public void i_should_be_asked_if_the_product_contains_anti_dandruff_agents() throws Throwable {
	}

	@When("^I select notify product manually$")
	public void i_select_notify_product_manually() throws Throwable {
		addProductPage.notify_manually();

	}

	@When("^I select Yes it contains nano material$")
	public void i_select_Yes_it_contains_nano_material() throws Throwable {
		addProductPage.select_radio_button_by_text("Yes");

	}

	@When("^I select user likely to be exposed by \"(.*?)\"$")
	public void i_select_user_likely_to_be_exposed_by(String arg1) throws Throwable {
		nanoMaterialPage.choose_expose_to(arg1);

	}

	@When("^I click on \"(.*?)\" on annex$")
	public void i_click_on_on_annex(String arg1) throws Throwable {
		nanoMaterialPage.select_radio_button_by_text(arg1);
	}

	@Then("^I should see multi selectable exposure route \"(.*?)\"$")
	public void i_should_see_multi_selectable_exposure_route(String arg1) throws Throwable {

	}
	
	@When("^I upload poisonous ingredients file \"(.*?)\"$")
	public void i_upload_poisonous_ingredients_file(String arg1) throws Throwable {
	  addProductPage.add_ingredients_file(arg1);
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