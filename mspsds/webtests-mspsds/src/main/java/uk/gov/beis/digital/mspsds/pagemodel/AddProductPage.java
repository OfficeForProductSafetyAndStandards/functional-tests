package uk.gov.beis.digital.mspsds.pagemodel;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

import static org.junit.Assert.assertTrue;

public class AddProductPage extends BasePage {
	
	

	By create_product_record = By.xpath("//a[contains(text(),'Create a product record')]");
	By ts_prod_category = By.cssSelector("#category");
	By ts_product_type = By.cssSelector("#subcategory");
	By ts_product_name = By.cssSelector("#name");
	By ts_product_description = By.cssSelector("#product_description");
	By ts_continue = By.xpath("//input[@name='commit']");
	By ts_page_heading = By.xpath("//h1[@class='govuk-heading-l']");
	By ts_hazard_type = By.cssSelector("#hazard_type");
	By ts_hazard_description = By.cssSelector("#hazard_description");
	By ts_hazard_non_compliant_reason = By.cssSelector("#investigation_non_compliant_reason");
	By ts_business_name = By.cssSelector("#business_trading_name");
	By ts_business_legalname = By.cssSelector("#business_legal_name");
	By ts_create_case = By.xpath("//input[@name='commit']");
	By ts_product_units = By.xpath("//input[@id='exact_units']");
	
	private WebDriver driver;
	
SharedWebdriver shrdWebdriver;
	
	public AddProductPage(SharedWebdriver shrdWebdriver)
	{
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
	}
		
public void enter_product_details(String category) throws InterruptedException
{
	this.click(this.create_product_record);
	Thread.sleep(3000);
	this.SelectItem(this.ts_prod_category, category);
	//this.type(this.ts_prod_category,category);
	//find(this.ts_prod_category).sendKeys(Keys.ENTER);
	this.type(this.ts_product_type,"Auto-test dishwasher");
	Thread.sleep(2000);
	this.select_radio_button_by_text("Yes");
	Thread.sleep(5000);
	this.driver.findElement(By.name("product[has_markings]")).click();
	this.driver.findElement(By.name("product[markings][]")).click();
	this.select_radio_button_by_text("Exact number known");
	//this.type(this.ts_product_units, "20");
	this.type(this.ts_product_name,"Day to night cream");
	Thread.sleep(5000);
	this.driver.findElement(By.name("product[when_placed_on_market]")).click();
	this.type(this.ts_product_description,"This is a auto test product");
	this.driver.findElement(By.xpath("//input[@name='commit']")).click();
	
	
}

public void verify_create_product_link() throws InterruptedException
{
	this.IsElementDisplayed(create_product_record);
}


public void select_compliance(String compliant_type) throws InterruptedException
{
	if(compliant_type.equalsIgnoreCase("unsafe"))
	{
	this.driver.findElement(By.cssSelector("#base")).click();
	this.type(this.ts_hazard_type,"Chemical");
	find(this.ts_hazard_type).sendKeys(Keys.ENTER);
	this.type(this.ts_hazard_description,"Automated-test");
	}
	else 
	{
		this.driver.findElement(By.cssSelector("#investigation_"+compliant_type)).click();
		this.type(this.ts_hazard_non_compliant_reason,"Automated test");
	}
	Thread.sleep(2000);
	
}
		

public void enter_retailer_business(String name)
{
	this.type(this.ts_business_name,name);
	this.type(this.ts_business_legalname,"automated test - legal name");
	
}
public void ts_click_continue() throws InterruptedException
{
	this.click(this.ts_continue);
	Thread.sleep(2000);
}


public void create_case() throws InterruptedException
{
	this.click(this.ts_create_case);
	Thread.sleep(1000);
}

	}