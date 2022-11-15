package uk.gov.beis.par.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.par.Utils.AppProperties;

import static org.junit.Assert.assertTrue;

public class SearchPartnershipPage extends BasePage {

	private WebDriver driver;

	
	private By search_field =  By.cssSelector("#edit-keywords");
	private By search_button = By.cssSelector("#edit-submit-partnership-search");
	private By manage_authorities = By.xpath("//a[contains(text(),'Manage authorities')]");
    private By manage_organisations = By.xpath("//a[contains(text(),'Manage organisations')]");
    private By partnership_page = By.xpath("//a[@href='/partnership/34499']");
	
	public SearchPartnershipPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
 public void search_by_keyword(String text)
 {
	 this.type(search_field,text);
	 this.click(search_button);
	 
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
