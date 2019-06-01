package uk.gov.beis.cosmetics.stepdefs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.When;
import uk.gov.beis.cosmetics.pagemodel.AddProductPage;
import uk.gov.beis.cosmetics.pagemodel.LoginPage;

public class WhenSteps extends SharedWebDriver {

	private WebDriver driver;
	private LoginPage loginPage;
	private AddProductPage addProductPage;

	public WhenSteps(SharedWebDriver driver) {
		this.driver = driver;
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		addProductPage = PageFactory.initElements(driver, AddProductPage.class);
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
	    addProductPage.click_continue();
	}

	@When("^I should see manual-journey step \"(.*?)\"$")
	public void i_should_see_manual_journey_step(String arg1) throws Throwable {
	   addProductPage.verify_cosmetics_page_headers(arg1);
	}

	@When("^I notify product manually$")
	public void i_notify_product_manually() throws Throwable {
		  loginPage.click_by_text("Add cosmetic products");
		  addProductPage.select_radio_button_by_text("No, they have not been notified in the EU");
		  addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("No");
	      addProductPage.click_continue();
	      addProductPage.enter_productname("TestManual-Prod");
	      addProductPage.enter_reference_number("TestRef-2003");
	      addProductPage.select_radio_button_by_text("Yes");
		  addProductPage.click_continue();
	      addProductPage.enter_country_imported_from("India");
	      addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("The cosmetic product is a single item");
	      addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("No");
	      addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("Loose powder");
	      addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("No");
	      addProductPage.click_continue();
	      addProductPage.select_radio_button_by_text("No");
	      addProductPage.click_continue();
	      addProductPage.select_prod_category("Skin care products - Hand care products");
	      addProductPage.select_radio_button_by_text("Choose a predefined frame formulation");
	      addProductPage.click_continue();
	}

	@When("^I choose frame formulation$")
	public void i_choose_frame_formulation() throws Throwable {
		addProductPage.select_frame_formulation("Skin Care Cream, Lotion, Gel");
	    addProductPage.click_continue();
	}
}
