package uk.gov.beis.digital.mspsds.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

import static org.junit.Assert.assertTrue;



public class DashboardPage extends BasePage {
	private WebDriver driver;
	By tab_case = By.xpath("//a[contains(.,'Cases')]");
	By tab_product = By.xpath("//a[contains(.,'Products')]");
	
SharedWebdriver shrdWebdriver;
	
	public DashboardPage(SharedWebdriver shrdWebdriver)
	{
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
	}
	
 public void  verify_tabs(String tab) throws InterruptedException
 {
	 assertTrue("Failed:"+tab+"not displayed check manually",this.verify_element_by_text(tab));
 }

 public void Verify_tab_info(String tab){
	 assertTrue("Failed:"+tab+"not displayed check manually",this.verify_element_by_text(tab));
	 this.click_by_text(tab);
	 assertTrue("Failed:"+tab+"not displayed check manually",this.verify_element_by_text(tab));	 
	 
 }

 public void verify_dashboard() throws InterruptedException
 {
	 this.IsElementDisplayed(tab_case);
 }


	
	
	
}
