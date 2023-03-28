package uk.gov.beis.cosmetics.pagemodel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import uk.gov.beis.cosmetics.Utils.EnvironmentProperties;
import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;

import static org.junit.Assert.assertTrue;

public class NanoMaterialPage extends BasePage {

	private WebDriver driver;
	SharedWebdriver shrdDriver;
	By nanomaterial_inci1 = By.xpath("//input[@id='nano_material_nano_elements_attributes_0_inci_name']");
	By inci_name1 = By.xpath("//input[@id='nano_material_nano_elements_attributes_0_inci_name']");
	By inci_name2 = By.xpath("//input[@id='nano_material_nano_elements_attributes_1_inci_name']");
	

	public NanoMaterialPage(SharedWebdriver shrdDriver) {
		super(shrdDriver);
		this.driver = shrdDriver.getDriver();
	}

	public void choose_expose_to(String option)
	{
	driver.findElement(By.xpath("//label[contains(text(),'"+option+"')]")).click();
	}
	
	public void enter_inci_name(String name)
	{
		this.type(inci_name1,name);
	}
}
