package uk.gov.beis.par.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.par.Utils.AppProperties;

import static org.junit.Assert.assertTrue;

import java.util.Date;

public class CreatePartnershipPage extends BasePage {

	private WebDriver driver;

	private By aboutPartnership = By.cssSelector("#edit-about-partnership");
	private By continueButton = By.xpath("//input[@id='edit-next']");
	private By confirm_checkbox = By.cssSelector("#edit-confirm");
	private By orga_name = By.cssSelector("#edit-name");
	
	private By passwordField = By.cssSelector("#edit-pass");
	private By loginButton = By.cssSelector("#edit-submit");
	private By signInLink = By.xpath("//a[contains(text(),'Sign in')]");
	private By signOutLink = By.xpath("//a[contains(text(),'Sign out')]");
	
	private By mem_addressline1= By.cssSelector("#edit-address-line1");
	private By mem_town = By.cssSelector("#edit-town-city");

	private By mem_postcode= By.cssSelector("#edit-postcode");
	private By mem_title = By.cssSelector("#edit-salutation");
	private By mem_first_name= By.cssSelector("#edit-first-name");
	private By mem_last_name = By.cssSelector("#edit-last-name");
	private By mem_phone= By.cssSelector("#edit-work-phone");
	private By mem_email = By.cssSelector("	#edit-email");
	private By terms_conditions = By.cssSelector("#edit-terms-authority-agreed");
	private By save_button =  By.cssSelector("#edit-save");
	
	private String email = "Auto-test"+this.generate_string(8)+"@test.com";
	private By select_sic = By.id("edit-sic-code");
	private By select_employee_band = By.id("edit-employees-band");

	
	public CreatePartnershipPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void Enteraboutpartnership()
	{
		this.type(aboutPartnership, "Partnership-created by automated tests");
		
	}
	
	public void click_continue()
	{
		this.click(continueButton);
	}
	
	public void fill_in_partnership_details() throws InterruptedException
	{
		
		this.type(aboutPartnership, "test inspection plan "+ (new Date().getDate()));
		Thread.sleep(2000);
		this.click_continue();
		this.type(orga_name, "Automated test org"+this.generate_string(5));
		this.click_continue();
		
	}
	
	public void fill_org_mem_details()
	{
		this.type(mem_addressline1, "Auto-test addressline1");
		this.type(mem_town, "Auto-test town");
		this.type(mem_postcode, "B28 9LL");
		this.click_continue();
		this.type(mem_title, "Mr");
		this.type(mem_first_name,"Auto-test firstname");
		this.type(mem_last_name, "Auto-test lastname");
		this.type(mem_phone, "070989989");
		this.type(mem_email,email);
		this.click_continue();
	}
	
	public void click_confirm_checkbox() throws InterruptedException
	{
		this.click(confirm_checkbox);
		this.click_continue();
		Thread.sleep(4000);
		if(driver.getPageSource().contains("Please confirm that all conditions for a new partnership have been met."))
		{
			this.click(confirm_checkbox);
			this.click_continue();
			
		}
	}
	
	public void click_confirm_org_checkbox() throws InterruptedException
	{
		this.click(confirm_checkbox);
		this.click_continue();
		Thread.sleep(4000);
		if(driver.getPageSource().contains("Please confirm that you have been given permission."))
		{
			this.click(confirm_checkbox);
			this.click_continue();
			
		}
	}

	
	
	public void confirm_terms_conditions()
	{
		this.click(terms_conditions);
		this.click(save_button);
		if(driver.getPageSource().contains("Please confirm you have read the terms & conditions."))
		{
			this.click(terms_conditions);
			this.click(save_button);
			
		}
		
		
	}
	
	public void select_sic_code(String item)
	{
		this.SelectItem(select_sic,item);
	}
	
	public void select_employee_band(String item)
	{
		this.SelectItem(select_employee_band, item);
	}

}

