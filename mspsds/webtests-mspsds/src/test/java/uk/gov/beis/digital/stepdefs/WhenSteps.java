package uk.gov.beis.digital.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.java.After;
import io.cucumber.java.PendingException;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.pagemodel.AddProductPage;
import uk.gov.beis.digital.mspsds.pagemodel.CasesPage;
import uk.gov.beis.digital.mspsds.pagemodel.DashboardPage;
import uk.gov.beis.digital.mspsds.pagemodel.LoginPage;

public class WhenSteps extends BasePage {

	private WebDriver driver;
	DashboardPage dashpge;
	CasesPage casepge;
	LoginPage loginPage;
	AddProductPage addProductPage;
	SharedWebdriver shrdDriver;

	public WhenSteps(SharedWebdriver driver) {
		super(driver);
		this.shrdDriver = driver;

		dashpge = new DashboardPage(shrdDriver);
		casepge = new CasesPage(shrdDriver);
		loginPage = new LoginPage(shrdDriver);
		addProductPage = new AddProductPage(shrdDriver);
	}

	@When("^I click on \"(.*?)\" tab$")
	public void i_click_on_tab(String arg1) throws Throwable {
		dashpge.click_by_text(arg1);

	}

	@When("^I am on dashboard$")
	public void i_am_on_dashboard() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dashpge.verify_dashboard();
	}

	@Then("^I should see my team name \"(.*?)\" displayed$")
	public void i_should_see_my_team_name_displayed(String arg1) throws Throwable {

		dashpge.verify_element_by_text(arg1);

	}

	@When("^I click team \"(.*?)\"$")
	public void i_click_team(String arg1) throws Throwable {
		dashpge.click_by_text(arg1);
	}

	@When("^I click button \"(.*?)\"$")
	public void i_click_button(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		dashpge.click_by_text(arg1);
	}

	@When("^I select case type \"(.*?)\"$")
	public void i_select_case_type(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		dashpge.select_radio_button_by_text(arg1);
		Thread.sleep(2000);
		dashpge.click_continue_input();
	}

	@When("^I select reporter as \"(.*?)\"$")
	public void i_select_reporter_as(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Thread.sleep(2000);
		dashpge.select_radio_button_by_text(arg1);
		dashpge.click_continue_input();
	}

	@When("^I enter contact details$")
	public void i_enter_contact_details() throws Throwable {
		casepge.enter_contact_details();

	}

	@When("^I enter allegation details$")
	public void i_enter_allegation_details() throws Throwable {
		casepge.enter_allegation_details("", "");

	}

	@Then("^I should see \"(.*?)\"$")
	public void i_should_see(String arg1) throws Throwable {
		casepge.verify_banner_message(arg1);

	}

	@When("I open the product {string}")
	public void i_open_the_product(String string) {
		casepge.click_by_text(string);
	}

	@When("^I enter allegation details for product category \"(.*?)\"$")
	public void i_enter_allegation_details_for_product_category(String arg1) throws Throwable {
		casepge.select_allegation_prod_cat(arg1);
	}

	@When("^I enter hazard type as \"(.*?)\"$")
	public void i_enter_hazard_type_as(String arg1) throws Throwable {
		casepge.select_hazard_type(arg1);
	}

	@When("^I click add user$")
	public void i_click_add_user() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.add_user();

	}

	@When("^I enter testuser(\\d+)@test\\.com$")
	public void i_enter_testuser_test_com(String arg1) throws Throwable {
		loginPage.enter_username(arg1);

	}

	@When("^I click save$")
	public void i_click_save() throws Throwable {
		this.driver.findElement(By.xpath(
				"//div[@class='col-md-10 col-md-offset-2']//button[@class='ng-binding btn btn-primary'][contains(text(),'Save')]"))
				.click();
		Thread.sleep(3000);
	}

	@When("I select hazard {string}")
	public void i_select_hazard(String string) {
		casepge.select_hazard(string);
	}

	@When("I enter details why unsafe")
	public void i_enter_details_why_unsafe() {

	}

	@When("I enter reference number")
	public void i_enter_reference_number() {
		casepge.select_radio_button_by_text("Yes");

	}

	@When("I enter case name {string}")
	public void i_enter_case_name(String string) {
		casepge.enter_case_name(string);

	}

	@Then("I should see success banner {string}")
	public void i_should_see_success_banner(String string) {

	}

	@Then("^I should be able to add to Lincolnshire County Council$")
	public void i_should_be_able_to_add_to_Lincolnshire_County_Council() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^I should be able to invite user$")
	public void i_should_be_able_to_invite_user() throws Throwable {
		loginPage.invite_user();

	}

	@When("^I enter username \"(.*?)\"$")
	public void i_enter_username(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enter_username(arg1);
		loginPage.enter_email(arg1);
	}

	@When("^I enter emnail \"(.*?)\"$")
	public void i_enter_emnail(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.enter_email(arg1);
	}

	@Then("^I should be able to add to \"(.*?)\"$")
	public void i_should_be_able_to_add_to(String arg1) throws Throwable {
		loginPage.add_user_to_group(arg1);

	}

	@Then("^I select role as team_admin$")
	public void i_select_role_as_team_admin() throws Throwable {
		loginPage.select_user_role("team_admin");
	}

	@Then("^I select role as \"(.*?)\"$")
	public void i_select_role_as(String arg1) throws Throwable {
		loginPage.select_user_role(arg1);
	}

	@When("^I go to groups$")
	public void i_go_to_groups() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.go_to_groups();
	}

	@When("^I click new$")
	public void i_click_new() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

	}

	@Then("^I should be able to create new group \"(.*?)\"$")
	public void i_should_be_able_to_create_new_group(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.add_group(arg1);

	}

	@Then("^I should be abel to create new team \"(.*?)\"$")
	public void i_should_be_abel_to_create_new_team(String arg1) throws Throwable {
		loginPage.add_team_name(arg1);

	}

	@Then("^I add group email box \"(.*?)\"$")
	public void i_add_group_email_box(String arg1) throws Throwable {
		loginPage.add_team_mail_box(arg1);

	}

	@When("^I enter product details for product category \"(.*?)\"$")
	public void i_enter_product_details_for_product_category(String arg1) throws Throwable {
		addProductPage.enter_product_details(arg1);
	}

	@When("^I enter product name$")
	public void i_enter_product_name() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I enter description of the product \"(.*?)\"$")
	public void i_enter_description_of_the_product(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("I click the button {string}")
	public void i_click_the_button(String string) {
		dashpge.click_create_project();
	}

	@When("^I click continue$")
	public void i_click_continue() throws Throwable {
		dashpge.click_continue();
		Thread.sleep(2000);
	}

	@When("^I click continue input$")
	public void i_click_continue_input() throws Throwable {
		dashpge.click_continue_input();
	}

	@Given("^I click continue button$")
	public void i_click_continue_button() throws Throwable {
		addProductPage.click_continue_button();
		Thread.sleep(2000);
	}

	@When("^I click continue on coivid page$")
	public void i_click_continue_on_coivid_page() throws Throwable {
		this.driver.findElement(By.xpath("//button[contains(@class,'govuk-button ')]")).click();
	}

	@When("^I select compliance type \"(.*?)\"$")
	public void i_select_compliance_type(String arg1) throws Throwable {
		addProductPage.select_compliance(arg1);
	}

	@When("^I enter hazard details \"(.*?)\"$")
	public void i_enter_hazard_details(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@When("^I select which parts of chain do you know as \"(.*?)\"$")
	public void i_select_which_parts_of_chain_do_you_know_as(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		addProductPage.select_radio_button_by_text(arg1);
		Thread.sleep(2000);
	}

	@When("^I click on continue$")
	public void i_click_on_continue() throws Throwable {

	}

	@When("^I enter business tradign name \"(.*?)\"$")
	public void i_enter_business_tradign_name(String arg1) throws Throwable {
		addProductPage.enter_retailer_business(arg1);
	}

	@When("^I enter legal name \"(.*?)\"$")
	public void i_enter_legal_name(String arg1) throws Throwable {

	}

	@When("^I select \"(.*?)\"$")
	public void i_select(String arg1) throws Throwable {
		addProductPage.select_radio_button_by_text(arg1);
	}

	@When("^I select corrective action \"(.*?)\"$")
	public void i_select_corrective_action(String arg1) throws Throwable {
		addProductPage.select_radio_button_by_text(arg1);
	}

	@When("^I click \"(.*?)\"$")
	public void i_click(String arg1) throws Throwable {
		addProductPage.select_radio_button_by_text(arg1);
	}

	@When("^I click create case$")
	public void i_click_create_case() throws Throwable {
		addProductPage.create_case();

	}

	@When("^I go to activity log$")
	public void i_go_to_activity_log() throws Throwable {

	}

	@When("^I go to supporting information tab$")
	public void i_go_to_supporting_information_tab() throws Throwable {
		casepge.click(casepge.ts_support_info);

	}

	@Then("^I should be able to add activity \"(.*?)\"$")
	public void i_should_be_able_to_add_activity(String arg1) throws Throwable {
		if (arg1.equalsIgnoreCase("Comment")) {
			casepge.add_comment_activity();
		} else if (arg1.equalsIgnoreCase("Record corrective action")) {
			casepge.add_corrective_action("no");

		}
	}

	@Then("^I should be add \"(.*?)\" with a file$")
	public void i_should_be_add_with_a_file(String arg1) throws Throwable {
		casepge.add_corrective_action("yes");

	}

	@When("^I enter a date as when it was received$")
	public void i_enter_a_date_as_when_it_was_received() throws Throwable {
		casepge.enter_date_enquiry_form();

	}

	@When("^I select how it was received \"(.*?)\"$")
	public void i_select_how_it_was_received(String arg1) throws Throwable {
		casepge.select_radio_button_by_text(arg1);

	}

	@When("^I select source \"(.*?)\"$")
	public void i_select_source(String arg1) throws Throwable {
		casepge.select_radio_button_by_text(arg1);

	}

	@When("^I enter contact person name \"(.*?)\"$")
	public void i_enter_contact_person_name(String arg1) throws Throwable {
		casepge.enter_complainant_name(arg1);

	}

	@When("^I enter enquriy details$")
	public void i_enter_enquriy_details() throws Throwable {
		casepge.enter_enquiry_details();
	}

	@When("^I click create enquiry$")
	public void i_click_create_enquiry() throws Throwable {

	}

	@Then("^I should be able to create enquiry$")
	public void i_should_be_able_to_create_enquiry() throws Throwable {

	}

	@When("^I Keep attachment title field empty$")
	public void i_Keep_attachment_title_field_empty() throws Throwable {
		casepge.type(casepge.attachment_title, "");
	}

	@When("^I submit the form$")
	public void i_submit_the_form() throws Throwable {
		casepge.click(casepge.submit);

	}

	@When("^I click continue on ts case creation page$")
	public void i_click_continue_on_ts_case_creation_page() throws Throwable {
		Thread.sleep(2000);
		// this.shrdDriver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
	}

	@When("^I submit file upload$")
	public void i_submit_file_upload() throws Throwable {
		casepge.click(casepge.file_upload_submit);
	}

	@Then("^I should see error \"(.*?)\"$")
	public void i_should_see_error(String arg1) throws Throwable {
		casepge.verify_error(arg1);
	}

	@When("^I fill in attachment title$")
	public void i_fill_in_attachment_title() throws Throwable {
		casepge.type(casepge.attachment_title, "Auto test attachment");

	}

	@When("^I click save attachment$")
	public void i_click_save_attachment() throws Throwable {
		this.driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
	}

	@When("^I click back$")
	public void i_click_back() throws Throwable {
		casepge.click(casepge.back_link);
	}

	@When("^I click cancel$")
	public void i_click_cancel() throws Throwable {
		casepge.click(casepge.cancel_link);
	}

	@When("^I enter project title$")
	public void i_enter_project_title() throws Throwable {
		casepge.type(casepge.project_title, "Automated test- project title");

	}

	@When("^I enter project summary$")
	public void i_enter_project_summary() throws Throwable {
		casepge.type(casepge.project_summary, "Automated tests -project description");
	}

	@When("^I click create project$")
	public void i_click_create_project() throws Throwable {

	}

	@When("I go to {string}")
	public void i_go_to(String string) {
		addProductPage.open_products_page(string);
	}

	@When("I click {string} on case summary page")
	public void i_click_on_case_summary_page(String string) {
		casepge.click_link_on_summary_page(string);
	}

	@When("I enter the productid {string}")
	public void i_enter_the_productid(String string) {
		addProductPage.enter_prod_id(string);
	}

	@When("I click search")
	public void i_click_search() {
	    addProductPage.click_continue();
		
	}

	@Then("I should see product {string}")
	public void i_should_see_product(String string) throws InterruptedException {
	addProductPage.verify_page_h1(string);
	}

	@When("I click No and submit")
	public void i_click_no_and_submit() {
		addProductPage.select_radio_button_by_text("No");
		addProductPage.click_continue("Save and continue");
	}

	@Then("I should see product search")
	public void i_should_see_product_search() {
		
	}

	@When("I click Yes and submit")
	public void i_click_yes_and_submit() {
		addProductPage.select_radio_button_by_text("Yes");
		addProductPage.click_continue("Save and continue");
	}


	@Then("I see validation error message {string}")
	public void i_see_validation_error_message(String string) {
		
		
	}

//	@After()
//	/*
//	 * Embed a screenshot in test report if test is marked as failed
//	 */
//	public void embedScreenshot(Scenario scenario) {
//		if (scenario.isFailed()) {
//			try {
//				//scenario.write("Current Page URL is " + driver.getCurrentUrl());
//				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				//scenario.embed(screenshot, "image/png");
//			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
//				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
//			}
//		}
//	}

}
