package uk.gov.beis.digital.mspsds.pagemodel;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uk.gov.beis.digital.BasePage;
import uk.gov.beis.digital.SharedWebdriver;
import uk.gov.beis.digital.mspsds.Utils.EnvironmentProperties;
import uk.gov.beis.digital.stepdefs.SharedWebDriver;



public class LoginPage extends BasePage{
	
	private SharedWebDriver launchbrowser;
	
	private By usernameField = By.id("email");
	private By passwordField = By.id("password");
	private By otp_code = By.id("otp_code");
	private By loginButton = By.xpath("//button[normalize-space()='Continue']");
	
	private By signInLink = By.xpath("//a[text()='Sign in']");
	private By signOutLink = By.xpath("//a[text()='Sign out']");
	private By expiryUnits = By.name("reqActionsEmailTimeoutUnit");
	private By client_app = By.id("clients");
	private By req_actions = By.id("reqActions");
	private WebDriver driver;
	
    SharedWebdriver shrdWebdriver;
	
	public LoginPage(SharedWebdriver shrdWebdriver)
	{
		super(shrdWebdriver);
		this.shrdWebdriver = shrdWebdriver;
		this.driver = shrdWebdriver.getDriver();
	}
	
	
	public void login_as(String username, String password) throws InterruptedException
	{
		this.driver.manage().deleteAllCookies();
		if (this.IsElementDisplayed(signOutLink)) {
			this.click(signOutLink);
		}
		this.driver.manage().deleteAllCookies();
        this.click_by_text("Sign in to your account");
		this.type(usernameField, username);
		this.type(passwordField, password);
		this.click(loginButton);
		this.type(otp_code, "11222");	
		Thread.sleep(2000);
		this.click(loginButton);
		Thread.sleep(4000);
		assertTrue("Failed to sign in", this.IsElementDisplayed(signOutLink));
	}
public void login_as_kc_admin(String username, String password) throws InterruptedException
{
	if(!this.driver.getPageSource().contains("Admin"))
	{
	this.type(usernameField, username);
	this.type(passwordField, password);
	this.click(loginButton);
	Thread.sleep(500);
	}
}

public void go_to_users() throws InterruptedException {
	this.driver.findElement(By.xpath("//a[contains(text(),'Users')]")).click();
	Thread.sleep(2000);
	
}

public void go_to_groups() throws InterruptedException
{
	Thread.sleep(3000);
	this.driver.findElement(By.xpath("//a[contains(text(),'Groups')]")).click();
	Thread.sleep(2000);
	this.driver.findElement(By.xpath("//span[contains(text(),'Organisations')]")).click();
	this.driver.findElement(By.xpath("//button[@id='createGroup']")).click();
	Thread.sleep(3000);
	
}

public void add_group(String group_name) throws InterruptedException
{
	this.driver.findElement(By.xpath("//input[@id='name']")).sendKeys(group_name);
	this.driver.findElement(By.xpath("//button[@class='ng-binding btn btn-primary']")).click();
	Thread.sleep(3000);
	
}

public void add_team_name(String team_name) throws InterruptedException
{
	this.driver.findElement(By.xpath("//a[contains(text(),'Groups')]")).click();
	Thread.sleep(2000);
	this.driver.findElement(By.xpath("//span[contains(text(),'"+team_name+"')]")).click();
	this.driver.findElement(By.xpath("//button[@id='createGroup']")).click();
	Thread.sleep(3000);
	this.driver.findElement(By.xpath("//input[@id='name']")).sendKeys(team_name);
	this.driver.findElement(By.xpath("//button[@class='ng-binding btn btn-primary']")).click();	
	
}

public void add_team_mail_box(String mail_box) throws InterruptedException
{
	Thread.sleep(3000);
	this.driver.findElement(By.xpath("//a[contains(text(),'Attributes')]")).click();
	Thread.sleep(2000);
	this.driver.findElement(By.xpath("//input[@id='newAttributeKey']")).sendKeys("teamRecipientEmail");
	this.driver.findElement(By.xpath("//input[@id='newAttributeValue']")).sendKeys(mail_box);
	this.driver.findElement(By.xpath("//td[@class='kc-action-cell ng-binding']")).click();
	Thread.sleep(2000);
	this.driver.findElement(By.xpath("//button[@class='ng-binding btn btn-primary']")).click();
	Thread.sleep(2000);
	
}

public void enter_username(String username) {
	
	this.driver.findElement(By.id("username")).sendKeys(username);
	
	
	
}
public void enter_email(String email) {
	
	
	this.driver.findElement(By.id("email")).sendKeys(email);
	
	
}
public void save_user() throws InterruptedException
{
	this.driver.findElement(By.xpath("//div[@class='col-md-10 col-md-offset-2']//button[@class='ng-binding btn btn-primary'][contains(text(),'Save')]")).click();
    Thread.sleep(3000);
}
public void add_user_to_group(String groupname) throws InterruptedException
{
	this.driver.findElement(By.xpath("//ul[@class='nav nav-tabs']//a[@class='ng-binding'][contains(text(),'Groups')]")).click();
	Thread.sleep(3000);
	this.driver.findElement(By.xpath("//li[@class='ng-scope']//li[@class='ng-scope']//li[@class='ng-scope']//span[@class='ng-binding'][contains(text(),'"+groupname+"')]")).click();
	this.driver.findElement(By.xpath(" //button[@id='joinGroup']")).click();
	Thread.sleep(3000);
}

public void invite_user() throws InterruptedException
{
	this.driver.findElement(By.xpath("//a[contains(text(),'Credentials')]")).click();
	Thread.sleep(4000);
	this.SelectItem(req_actions, "Update Mobile Number");
	this.SelectItem(req_actions, "Update Profile");
	this.SelectItem(req_actions, "Verify Email");
	this.SelectItem(req_actions, "Update Password");
	this.driver.findElement(By.id("reqActionsEmailTimeout")).clear();
	this.driver.findElement(By.id("reqActionsEmailTimeout")).sendKeys("14");
	this.SelectItem(expiryUnits,"Days");
	this.driver.findElement(By.id("reqActionsEmail")).click();
	Thread.sleep(2000);
	this.driver.findElement(By.xpath("//button[@class='ng-binding btn btn-danger']")).click();
	Thread.sleep(2000);
	
}

public void select_user_role(String role) throws InterruptedException
{
	Thread.sleep(3000);
	this.driver.findElement(By.xpath("//a[contains(text(),'Role Mappings')]")).click();
	Thread.sleep(3000);
	this.SelectItem(client_app, "psd-app");
	this.driver.findElement(By.xpath("//option[@label='"+role+"']")).click();
	this.driver.findElement(By.xpath("//div[@class='form-group']//div[2]//div[1]//button[1]")).click();
	Thread.sleep(3000);
}

public void add_user() throws InterruptedException
{
	this.driver.findElement(By.xpath("//a[@id='createUser']")).click();
	Thread.sleep(4000);	
}
	public void login_as_opss() throws InterruptedException
	{
		login_as(EnvironmentProperties.getOpssUsername(), EnvironmentProperties.getOpssPassword());
	}

	public void login_as_ts() throws InterruptedException
	{
		login_as(EnvironmentProperties.getTradingStandardsUsername(), EnvironmentProperties.getTradingStandardsPassword());
	}

	public void log_out()
	{
		if (driver.getPageSource().contains("Sign out")) {
			this.click(signOutLink);
		}
	}
}
