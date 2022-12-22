package uk.gov.beis.digital.mspsds.pagemodel;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.Utils.AppProperties;



public class CasesPage extends BasePage {
	String env = AppProperties.get("envurl");
	private WebDriver driver;
	By team_elements = By.cssSelector("#govuk-radios__item");
	By cont_name = By.xpath("//input[@name='complainant[name]']");
	By cont_phone = By.xpath("//input[@id='complainant_phone_number']");
	By cont_email = By.xpath("//input[@id='complainant_email_address'])");
	By Continue = By.xpath("//input[@value='Continue']");
	
	//Case summary page elements
	By keyvalue = By.cssSelector("dd[class='govuk-summary-list__value'] p");
	By summary_list = By.xpath("//dl[@class='govuk-summary-list']");
	
	
	// Allegation details Page Elements
	By product_reason_unsafe= By.xpath("//label[@for='reported_reason']");
	By product_reaso_noncompliant = By.xpath("//label[@for='investigation_reported_reason_non_compliant']");
	By AllegationDetail = By.xpath("//textarea[@id='allegation_description']");
	By Prod_Category = By.cssSelector("#allegation_product_category");
	By Hazard_type = By.cssSelector("#allegation_hazard_type");
	By hazard_type = By.cssSelector("#hazard_type");
	By save_case = By.cssSelector("input[value='Save']");
	By create_allegation = By.xpath("//input[@value='Create allegation']");
	By ts_case_page = By.xpath("//h1[contains(@class,'govuk-heading-l')]");
	By ts_activity = By.xpath("//a[@id='Activity_id']");
	public By ts_support_info = By.id("Supporting information_id");
	By case_add_activity = By.xpath("//a[contains(text(),'Add activity')]");
	By case_comment = By.xpath("//textarea[@class='govuk-textarea ']");
	By corrective_summary = By.cssSelector("#corrective_action_summary");
	public By corrective_action_continue = By.xpath("//button[contains(.,'Continue')]");
	
	By date_day = By.xpath("//input[@name='corrective_action[date_decided][day]']");
	By date_month = By.xpath("//input[@name='corrective_action[date_decided][month]']");
	By date_year = By.xpath("//input[@name='corrective_action[date_decided][year]']");
	By legislation = By.xpath("//input[contains(@id,'legislation')]");
	By action_mandatory = By.xpath("//input[contains(@value,'mandatory')]");
	By geographic_scope = By.xpath("//select[@id='geographic_scope']");
	By files_related = By.cssSelector("	#related_file-1");
	
	//Enquiry page elements
	By enquiry_date_day = By.xpath("//input[@name='enquiry[date_received][day]']");
	By enquiry_date_month = By.xpath("//input[@name='enquiry[date_received][month]']");
	By enquiry_date_year = By.xpath("//input[@name='enquiry[date_received][year]']");
	By enquiry_complainant_name = By.xpath("//input[contains(@name,'complainant[name]')]");
	By enquiry_description =  By.xpath("//textarea[contains(@id,'enquiry_description')]");
	public By enquiry_title = By.xpath("//input[contains(@id,'enquiry_user_title')]");
	public By submit = By.xpath("//button[contains(@class,'govuk-button ')]");
	
	//Project page elements
	public By project_title = By.xpath("//input[contains(@id,'investigation_user_title')]");
	public By project_summary = By.xpath("//textarea[contains(@id,'investigation_description')]");

	
	//Attachment page objects
	public By supporting_information = By.xpath("//a[@id='Supporting information_id']");
	public By attachment_title = By.xpath("//input[contains(@id,'document_file_title')]");
	public By file_upload = By.xpath("//input[contains(@name,'document[file][file]')]");
	public By back_link = By.xpath("//a[contains(@class,'govuk-back-link')]");
	public By cancel_link = By.xpath("//a[contains(.,'Cancel')]");
	public By file_upload_submit = By.xpath("//button[@class='govuk-button '][contains(.,'Upload')]");
	public By corrective_action_file = By.xpath("//input[contains(@class,'govuk-file-upload')]");
	//covid badge element
	public By covid_badge = By.xpath("//span[contains(.,'COVID-19 related case')]");
	
	//summary error 
	public By page_error = By.xpath("//a[contains(text(),'Enter title')]");
	
	
	
SharedWebdriver shrdWebdriver;
	
	public CasesPage(SharedWebdriver shrdWebdriver)
	{
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
	}
	
	public void open_case_page()
	{
	this.got_to(env+"casses");
	}
	
	
	public void verify_on_correct_page(String page_name)
	{
		assertTrue("Failed:not on the correct page",find(this.ts_case_page).getText().contains(page_name));
	}
	
	public void verify_covid_badge_displayed() throws InterruptedException
	{
		//assertTrue("Failed:covid badge not displayed",this.IsElementDisplayed(this.covid_badge));
	}
	
	public void open_case(String case_title)
	{
		this.open_case(case_title);
	}
	public void verify_text_summary_page(String text) throws InterruptedException
	{
	    assertTrue("Failed: Element not displayed",this.IsElementDisplayed(keyvalue));
		assertTrue("Failed:"+ text + "Not displayed on the summary page",this.driver.findElement(By.xpath("//p[normalize-space()='"+ text+"']")).getText().equalsIgnoreCase(text));
	}
	
	public void verify_summary_page_element_text(String text) throws InterruptedException
	{
		assertTrue("Failed:" + text +" Not displayed", this.verify_elements_text(text,this.keyvalue));
	}
	
	// new flow - enter test case name 
	public void enter_case_name(String name)
	{
		this.driver.findElement(By.cssSelector("#user_title")).sendKeys(name + this.generate_string(8));
		this.driver.findElement(save_case).click();
	}
	
	public void enter_contact_details()
	{
		this.type(cont_name, "AutoTest-Nasir");
		this.click(Continue);
	}
	public void enter_allegation_details(String prod_cat,String hazard_type) throws InterruptedException
	{
		this.type(AllegationDetail, "Auto-Test-allegation");
		this.type(Prod_Category,"Baby/Children's Products");
		driver.findElement(By.cssSelector("#allegation_product_category")).sendKeys(Keys.RETURN);
		
		Thread.sleep(2000);
		this.type(Hazard_type,"Asphyxiation");
		driver.findElement(By.cssSelector("#allegation_hazard_type")).sendKeys(Keys.RETURN);
		this.click(create_allegation);
		Thread.sleep(3000);
	}
	
	public void select_allegation_prod_cat(String category) throws InterruptedException{
		this.type(AllegationDetail, "Auto-Test-allegation");
		this.type(Prod_Category,category);
		driver.findElement(By.cssSelector("#allegation_product_category")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
	}
	
	public void open_a_case(String case_title)
	{
		this.click_by_text("All cases");
		this.click_by_text(case_title);
	}
	//new case flow journey
	
	public void select_hazard(String hazard)
	{
	this.SelectItem(hazard_type, hazard);
	this.driver.findElement(By.cssSelector("#hazard_description")).sendKeys("Auto test - hazard description");
	}
	
		
	public void select_hazard_type(String type) throws InterruptedException{
		this.type(Hazard_type,type);
		driver.findElement(By.cssSelector("#allegation_hazard_type")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		this.click(create_allegation);
		
	}
	
	public void add_comment_activity() throws InterruptedException
	{
		this.click(this.ts_activity);
		this.click_by_text("Add comment");
		this.type(this.case_comment,"Automated test-comment1");
		this.click_continue_input();
		Thread.sleep(2000);
		this.verify_banner_message("Comment was successfully added.");	
		
	}
	
	public void add_corrective_action(String file_flag) throws InterruptedException
	{
		
		this.click_by_text("Add supporting information");
		this.select_radio_button_by_text("Corrective action");
		this.click_continue_button();
		this.select_radio_button_by_text("Import rejected at border");
		//this.type(this.corrective_summary,"Auto test corrective action");
		//this.driver.findElement(this.date_day).sendKeys("12");
		this.type(this.date_day,"12");
		this.type(this.date_month,"12");
		this.type(this.date_year,"2019");
		this.type(this.legislation,"Aerosol Dispensers Regulations 2009");
		this.driver.findElement(this.legislation).sendKeys(Keys.ENTER);
		WebElement element = driver.findElement(By.xpath("//input[contains(@value,'mandatory')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		this.click(this.action_mandatory);
		this.select_radio_button_by_text("Not relevant");
		this.select_radio_button_by_text("Permanent");
		this.driver.findElement(By.xpath("//label[contains(.,'Local')]")).click();
		//this.driver.findElement(this.geographic_scope).sendKeys(Keys.ENTER);
//		WebElement element = driver.findElement(By.xpath("//button[contains(.,'Continue')]"));
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//		Thread.sleep(500);
		if(file_flag.equalsIgnoreCase("yes"))
		{
			this.driver.findElement(By.cssSelector("#related_file")).click();
			this.add_corrective_action_file();
		}
		
		else
		{
		this.click(this.files_related);
		}
		this.driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
		//this.click_continue();
		Thread.sleep(2000);
	}
	
	public void enter_date_enquiry_form()
	{
		this.type(this.enquiry_date_day,"12");
		this.type(this.enquiry_date_month,"12");
		this.type(this.enquiry_date_year,"2019");
	}
	public void enter_complainant_name(String name)
	{
		this.type(this.enquiry_complainant_name,name);
	}
	
	public void enter_enquiry_details()
	{
		this.type(this.enquiry_description,"This is an auto test enquiry");
		this.type(this.enquiry_title,"Auto Test enquiry title-test");
	}
	
	public void add_file_case()
	{
//		this.click(this.attachment_tab);
//		this.click_by_text("Add attachment");
		this.file_upload(this.file_upload, "attachment.txt");
		this.click(this.submit);
	}
	
	public void add_corrective_action_file()
	{
		this.file_upload(this.corrective_action_file, "attachment.txt");
		//this.click(this.submit);
	}
	
	public void verify_error(String error)
	{
		assertTrue("Failed:error not shown",this.driver.findElement(By.xpath("//a[contains(text(),'"+error+"')]")).getText().contains(error));
	}
	
	public void read_summary_elements()
	{
		this.return_summary_elements(summary_list);
	}
	
}
