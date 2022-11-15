package uk.gov.beis.par.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.par.Utils.AppProperties;

import static org.junit.Assert.assertTrue;

public class CreateEnforcementPage extends BasePage {

	private WebDriver driver;

	
	private By search_field =  By.cssSelector("#edit-keywords");
	private By search_button = By.cssSelector("#edit-submit-partnership-search");
	private By manage_authorities = By.xpath("//a[contains(text(),'Manage authorities')]");
    private By manage_organisations = By.xpath("//a[contains(text(),'Manage organisations')]");
    private By partnership_page = By.xpath("//a[@href='/partnership/34497']");
	
    private By enforcement_officer_firstname = By.xpath("//input[contains(@id,'edit-first-name')]");
    private By enforcement_officer_lastname = By.xpath("//input[contains(@id,'edit-last-name')]");
    private By enforcement_officer_phone = By.xpath("//input[contains(@id,'edit-work-phone')]");
    private By enforcement_new_entity = By.xpath("//input[@id='edit-legal-entities-select-add-new']");
    private By enforcement_entity = By.xpath("//input[contains(@id,'edit-alternative-legal-entity')]");
	private By enforcement_summary = By.xpath("//textarea[contains(@id,'edit-summary')]");
    
	private By enforcement_title = By.xpath("//input[@id='edit-par-component-enforcement-action-0-title']");
	private By enforcement_details = By.xpath("//textarea[@id='edit-par-component-enforcement-action-0-details']");
	
	private By enforcement_save = By.xpath("//input[@id='edit-save']");
    
    
    public CreateEnforcementPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
 public void search_by_keyword(String text)
 {
	 this.type(search_field,text);
	 this.click(search_button);
	 
 }
 public void  validate_enforcement_contact_details()
 {
	 System.out.println(find(enforcement_officer_firstname).getAttribute("value"));
	 System.out.println(find(enforcement_officer_lastname).getAttribute("value"));
	 
	 assertTrue("Failed:incorrect value",find(enforcement_officer_firstname).getAttribute("value").equals("Grover"));
	assertTrue("Failed:incorrect value",find(enforcement_officer_lastname).getAttribute("value").contentEquals("01723456789"));
	assertTrue("Failed:incorrect value",find(enforcement_officer_phone).getAttribute("value").contentEquals("01723456789"));
 }
 
 public void select_new_entity() throws InterruptedException
 {
	 this.click(enforcement_new_entity);
	 this.type(enforcement_entity, "Test new entity");
	 this.click_continue_enforcement();
	 
 }
 
 public void select_enforcement_type(String type) throws InterruptedException
 {
	this.driver.findElement(By.xpath("//input[@id='edit-notice-type-"+type+"']")).click();
	this.type(enforcement_summary, "Test enforcement1");
	this.click_continue_enforcement();
 }
 public void enter_enforcement_details() throws InterruptedException
 {
	
	 this.type(enforcement_title, "auto enforcement1");
	 //this.driver.findElement(By.xpath("(//input[contains(@type,'radio')])[1]")).click();
	 this.select_radio_button_by_text("Cookie control");
	 this.type(enforcement_details, "Test enforcement1");
	 //select regulatory funciton
	 
	 this.click_continue_enforcement();
	 
 }
 
 public void review_enforcement_save() throws InterruptedException
 {
	 this.click(enforcement_save);
	 this.verify_par_page_header1("Raise notice of enforcement action\n" + 
	 		"Enforcement notice sent");
 }
 
 public void search_results(String text)
 {
	 assertTrue("Failed: didnt find the partnership with keyword search",this.driver.getPageSource().contains(text));
	 
 }
 
 public void verify_link_displayed(String link) throws Exception
 {
	 assertTrue("Failed:"+link+"is not displayed",driver.getPageSource().contains(link));
	 
 }
 
    
 
 
     public void Verify_link_not_displayed(String link)
     {
    	assertTrue("Failed:"+link+"is displayed",!driver.getPageSource().contains(link));
    	 
     }
     
     public void open_partnership_page()
     {
    	 this.click(partnership_page);
     }
	
}
