package uk.gov.beis.digital.mspsds.pagemodel;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.datatable.DataTable;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

import static org.junit.Assert.assertTrue;

public class AssigneePage extends BasePage {
	By team_elements = By.xpath("//label[contains(@class,'label')]");
	By change_case_owner = By.xpath("//a[contains(.,'Change case owner')]");
	private WebDriver driver;

	
	SharedWebdriver shrdWebdriver;
	
	public AssigneePage(SharedWebdriver shrdWebdriver)
	{
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
	}
	
	public void click_change_owner()
	{
		this.click(change_case_owner);
	}
	
	
public void verify_priority_team(DataTable team_list)
{
	List<String> list = team_list.asList();
	assertTrue("Failed:team not found",this.getText(team_elements).contains(list.get(0).subSequence(1,0)));
	assertTrue("Failed:team not found",this.getText(team_elements).contains(list.get(0).subSequence(2, 0)));
	assertTrue("Failed:team not found",this.getText(team_elements).contains(list.get(0).subSequence(3, 0)));
}
	

//	public void verify_priority_team_list(DataTable team_list) throws InterruptedException
//	{
//		List<List<String>> list = team_list.raw();
//			for(int j=0;j<=list.size();j++)
//			{
//				Thread.sleep(3000);
//				System.out.println(this.getText(team));
//				
//			    assertTrue("Failed:Expected team not displayed",this.getText(team).contains(list.get(j).toString()));
//			}	
//	}
//	
//	
//	public Boolean verify_priority_team_list(DataTable team_list) throws Exception
//	{
//		Boolean flag=false;
//		List<List<String>> list = team_list.raw();
//		
//		List<WebElement> team = this.findelements(team_elements);
//		do {
//			Iterator<WebElement> iter = team.iterator();
//			team = findelements(team_elements);
//			WebElement team_name = iter.next();
//			for(int j=0;j<list.get(0).size();j++)
//			{
//			if(team_name.getText().equals(list.get(0).get(j)))
//			{
//				flag=true;
//				break;
//			}
//			}
//		}	
//		while (team.size() >= 1);
//		return flag;
//}

	}