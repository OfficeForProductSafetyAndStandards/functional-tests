package uk.gov.beis.cosmetics.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertTrue;
import uk.gov.beis.digital.*;

public class AddProductPage extends BasePage {
	private WebDriver driver;
	SharedWebdriver shrdWebdriver;
	public AddProductPage(SharedWebdriver shrdDriver) {
		super(shrdDriver);
		this.driver = shrdDriver.getDriver();

	}

	By ele_fileupload = By.xpath("//input[@multiple='multiple'][contains(@id,'files')]");
	By add_product = By.xpath("//a[contains(@href,'notification/new')]");
	By ingredients_upload = By.xpath("//input[@id='component_formulation_file']");
	By upload_button = By.xpath("//input[contains(@type,'submit')]");
	By login_button = By.cssSelector("#kc-login");
	By signOut_link = By.xpath("//a[contains(text(),'Sign out')]");
	By signIn_link = By.xpath("//a[text()='Sign in']");
	By reason_error = By.xpath("//tbody[@class='govuk-table__body']//tr[1]//td[2]");
	By notification_check_status = By.xpath("//a[contains(.,'Refresh the browser to see uploaded products')]");
	By dismiss_error = By.xpath("//input[contains(@value,'Dismiss all error messages')]");
	By page_header = By.xpath("//h1[@class='govuk-fieldset__heading']");
	By prod_name = By.xpath("//input[contains(@type,'text')]");
	By prod_import_country = By.xpath("//input[@id='location-autocomplete']");
	By prod_category = By.id("component_sub_sub_category");
	By frame_formulation = By.id("component_frame_formulation");
	By prod_UK_register = By.xpath("//div[@class='product']");
	By prod_reference_field = By.xpath("//input[@id='notification_industry_reference']");
	// By trigger_rule_summary_error = By.xpath("//a[contains(text(),'No
	// substance added')]");
	By trigger_rule_question_1 = By.cssSelector("#trigger_question_trigger_question_elements_attributes_0_answer");
	By trigger_rule_question_2 = By.cssSelector("#trigger_question_trigger_question_elements_attributes_1_answer");

	By trigger_rule_question_header = By.xpath("//h1[@class='govuk-fieldset__heading']");
	By pH_min_field = By.cssSelector("#component_minimum_ph");
	By pH_max_field = By.cssSelector("#component_maximum_ph");
	By incomplete_tab = By.xpath("//a[@id='tab_incomplete']");

	public void login_as(String user, String pwd) throws InterruptedException {
		this.click(signIn_link);
		this.click(login_button);
		assertTrue("Failed to signIn", this.IsElementDisplayed(signOut_link));

	}

	public void enter_productname(String name) {
		this.type(prod_name, name);
		this.click_continue_on_cos();
	}
	public void enter_min_ph_value(String value)
	{
		this.type(pH_min_field, value);
	}
	public void enter_max_ph_value(String value)
	{
		this.type(pH_max_field, value);
	}

	public void enter_trigger_rule1(String text) {
		this.type(trigger_rule_question_1, text);
	}

	public void enter_trigger_rule2(String text) {
		this.type(trigger_rule_question_2, text);

	}

	public void enter_reference_number(String refer_numb) {

		this.select_radio_button_by_text("Yes");
		this.type(prod_reference_field, refer_numb);
		//this.click_continue();
		this.click_continue_on_cos();

	}

	public void enter_country_imported_from(String country) {
		this.type(prod_import_country, country);
		find(prod_import_country).sendKeys(Keys.ENTER);
	}

	public void select_frame_formulation(String formulation) {
		this.type(frame_formulation, "Skin");
		find(frame_formulation).sendKeys(Keys.ENTER);
	}

	public void Is_trigger_rule_displayed() throws InterruptedException {
		this.IsElementDisplayed(trigger_rule_question_header);
	}

	public void Is_trigger_rule_question_displayed() throws InterruptedException {
		this.IsElementDisplayed(trigger_rule_question_1);
	}

	public void select_prod_category(String cat) {
		this.select_radio_button_by_text("Skin products");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("Skin care products");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("Hand care products");
		this.click_continue_on_cos();

	}

	public boolean verify_cosmetic_page_header1(String title) {

		boolean flag = false;
		if (driver.findElement(page_header).getText().equals(title))
			return flag = true;
		else {
			return flag = false;
		}
	}

	public void add_notification_file(String file) throws InterruptedException {
		this.file_upload(ele_fileupload, file);
		click(upload_button);
		Thread.sleep(3000);
	}
	
	public void add_ingredients_file(String file) throws InterruptedException
	{
		this.file_upload(ingredients_upload, file);
		click(upload_button);
		Thread.sleep(3000);	
	}
	
	public void click_add_cosmetics_product()
	{
		this.click(add_product);
	}
	

	public void add_product_manually() throws InterruptedException {
		Thread.sleep(3000);
		this.click_add_cosmetics_product();
		this.click_by_text("Add cosmetic products");
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.enter_productname("TestManual-Prod");
		this.enter_reference_number("TestRef-2003");
		this.select_radio_button_by_text("Yes");
		this.click_continue_on_cos();
		this.enter_country_imported_from("India");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No, this is a single product");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("Loose powder");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("A typical non-pressurised bottle, jar, sachet or other package");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_prod_category("Skin care products - Hand care products");
		this.select_radio_button_by_text("Choose a predefined frame formulation");
		this.click_continue_on_cos();
	}

	public void add_product_manually_prebrexit() throws InterruptedException {
		Thread.sleep(3000);
		this.click_by_text("Add cosmetic products");
		this.select_radio_button_by_text("Yes");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No, I’ll enter information manually");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("Yes");
		this.click_continue_on_cos();	
		this.enter_productname("TestManual-Prod");
		this.enter_reference_number("TestRef-2003");
		this.select_radio_button_by_text("Yes");
		this.click_continue_on_cos();
		this.enter_country_imported_from("India");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("The cosmetic product is a single item");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("Loose powder");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("A typical non-pressurised bottle, jar, sachet or other package");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_radio_button_by_text("No");
		this.click_continue_on_cos();
		this.select_prod_category("Skin care products - Hand care products");
		this.select_radio_button_by_text("Choose a predefined frame formulation");
		this.click_continue_on_cos();
	}

	
	

	public void notify_manually()
	{
		this.click_by_text("Add cosmetic products");
		  this.select_radio_button_by_text("No");
		  this.click_continue_on_cos();
	      this.select_radio_button_by_text("No");
	      this.click_continue_on_cos();
	      this.enter_productname("TestManual-Prod");
	      this.enter_reference_number("TestRef-2003");
	      this.select_radio_button_by_text("Yes");
		  this.click_continue_on_cos();
	      this.enter_country_imported_from("India");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("No");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("No, this is a single product");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("No");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("Loose powder");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("A typical non-pressurised bottle, jar, sachet or other package");
	      this.click_continue_on_cos();
	      this.select_radio_button_by_text("No");
	      this.click_continue_on_cos();
	}

	public void verify_trigger_rule_error(String error)
	{
	   By trigger_rule_summary_error = By.xpath("//a[contains(text(),'"+error+"')]");
		assertTrue(this.getText(trigger_rule_summary_error).equals(error));
	}
	
	public void verify_no_error() throws InterruptedException
	{
		if (driver.getPageSource().contains("Refresh the browser to see uploaded products")) {
			this.click(notification_check_status);
		}
		Thread.sleep(3000);
		assertTrue("Failed:Notification didn't uploaded propertly",!driver.getPageSource().contains("Try again or manually enter the production notification data"));
		assertTrue("Failed:notification already exist",!driver.getPageSource().contains("A notification for this product already exists for this responsible person"));
	}
	
	public void verify_incomplete_tab(String filename)
	{
		this.click(incomplete_tab);
		assertTrue("Failed:notification didn't landed in incomplete-tab",driver.getPageSource().contains(filename));
	}

	public void validate_notification_error(String error) {

		if (driver.getPageSource().contains("Refresh the browser to see uploaded products")) {
			this.click(notification_check_status);
		}
		System.out.println(this.getText(reason_error));
		assertTrue(this.getText(reason_error).contains(error));

	}

	public void dismiss_error()
	{
		if(driver.getPageSource().contains("Dismiss all error messages"))
		{
		this.click(dismiss_error);
		}
	}
	
	public void verify_element_present() throws InterruptedException
	{
		assertTrue("Failed: min pH not displayed",this.IsElementDisplayed(pH_min_field));
		assertTrue("Failed:max pH not displayed",this.IsElementDisplayed(pH_max_field));
	}

}
