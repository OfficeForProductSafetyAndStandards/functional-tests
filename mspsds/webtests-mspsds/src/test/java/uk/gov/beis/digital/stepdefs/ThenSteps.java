package uk.gov.beis.digital.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.messages.types.Scenario;

import static org.junit.Assert.assertTrue;

import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.pagemodel.AssigneePage;
import uk.gov.beis.digital.mspsds.pagemodel.CasesPage;
import uk.gov.beis.digital.mspsds.pagemodel.DashboardPage;
import uk.gov.beis.digital.mspsds.pagemodel.LoginPage;

public class ThenSteps extends BasePage {
	private WebDriver driver;
	AssigneePage assignpge;
	DashboardPage dashpge;
	CasesPage casesPage;
	
SharedWebdriver shrdDriver;

public ThenSteps(SharedWebdriver driver)
{
	super(driver);
	shrdDriver=driver;
	assignpge = new AssigneePage(shrdDriver);
	dashpge = new DashboardPage(shrdDriver);
	casesPage = new CasesPage(shrdDriver);
}


	@Then("^I should be able to see prioritise team list under teams$")
	public void i_should_be_able_to_see_prioritise_team_list_under_teams(DataTable arg1) throws Throwable {
	   assignpge.click_change_owner();
	   assignpge.verify_priority_team(arg1);
	}

	@Then("^I should see \"(.*?)\" tab$")
	public void i_should_see_tab(String arg1) throws Throwable {
		dashpge.verify_tabs(arg1);
	  
	}
	

	@Then("^I should see Businesses list page$")
	public void i_should_see_Businesses_list_page() throws Throwable {
		
	    
	}

	@Then("^I should see \"(.*?)\" list page$")
	public void i_should_see_list_page(String arg1) throws Throwable {
	    dashpge.Verify_tab_info(arg1);
	}

	@Then("^I should see team \"(.*?)\"$")
	public void i_should_see_team(String arg1) throws Throwable {
	   dashpge.verify_radio_button_by_text(arg1);
	}
	
	@Then("^I should see team page \"(.*?)\"$")
	public void i_should_see_team_page(String arg1) throws Throwable {
	   dashpge.verify_page_header1(arg1);
	}
	@Then("^I should see team member \"(.*?)\"$")
	public void i_should_see_team_member(String arg1) throws Throwable {
	   assertTrue("Failed:team member not displayed",dashpge.IsElementDisplayed(driver.findElement(By.xpath("//span[contains(text(),'"+arg1+"')]"))));
	}
	
	@Then("^I should see page \"(.*?)\"$")
	public void i_should_see_page(String arg1) throws Throwable {
		Thread.sleep(2000);
		casesPage.verify_on_correct_page(arg1); 
	}
	
	@Then("^I should see covid badge displayed on the case overview page$")
	public void i_should_see_covid_badge_displayed_on_the_case_overview_page() throws Throwable {
		casesPage.verify_covid_badge_displayed();
	}
	
	@Then("^I click continue on corrective action confirm page$")
	public void i_click_continue_on_corrective_action_confirm_page() throws Throwable {
		casesPage.click(casesPage.corrective_action_continue);
	}
	
//	@After()
//	/*
//	 * Embed a screenshot in test report if test is marked as failed
//	 */
//	public void embedScreenshot(Scenario scenario) {
//		if (scenario.equals("failed:")) {
//			try {
//			//	scenario.write("Current Page URL is " + driver.getCurrentUrl());
//				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
//				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//				//scenario.attach(screenshot, "image/png");
//			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
//				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
//			}
//		}
//	}
	
}
