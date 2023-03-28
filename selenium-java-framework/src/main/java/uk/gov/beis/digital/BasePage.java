package uk.gov.beis.digital;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

/**
 * @ @author nasirkhan @
 *
 */
public class BasePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private static final int TIMEOUT = 5;
	private static final int POLLING = 50;

	// GDS component elements

	By page_h1 = By.cssSelector("h1.govuk-heading-l");
	By banner_message = By.xpath("//p[@class='govuk-notification-banner__heading']");
	By confirmation_panel_message = By.cssSelector(".govuk-panel__title");
	SharedWebdriver shrdDriver;
//	protected SearchContext getSearchCtx() {
//		return driver;
//	}

	public BasePage(SharedWebdriver shrdDriver) {
		this.shrdDriver = shrdDriver;
		this.driver = shrdDriver.getDriver();
	}

	/*
	 * Test Framework related re-usable methods These are used across any gov
	 * services which is following gds design pattern
	 */

	public void launch_app(String url) throws InterruptedException {

		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().to(url);
		driver.manage().window().maximize();
	}

	public String generate_string(int len) {
		String generated_string;
		return generated_string = RandomStringUtils.randomAlphanumeric(len).toLowerCase();
	}

	/*
	 * this method is return WebDriver class FindElement(by locator)
	 */
	public WebElement find(By locator) throws NoSuchElementException {
		return driver.findElement(locator);
	}

	/*
	 * this method is used when there's more than one element from a locator
	 */
	public List<WebElement> findelements(By locator) {
		return driver.findElements(locator);
	}

	/*
	 * this method is navigate to any given url
	 */

	public void got_to(String page_url) {
		driver.navigate().to(page_url);
	}

	protected void waitForElementToLoad(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void click(By locator) {
		find(locator).click();
	}

	public void type(By locator, String arg1) {
		find(locator).clear();
		find(locator).sendKeys(arg1);
	}

	public void value(By locator, String value) {
		find(locator).getAttribute("value").contentEquals(value);
	}

	public void emptytextbox(By locator) {
		find(locator).clear();
	}

	public String getText(By locator) {
		return find(locator).getText();
	}

	public boolean isTextPresent(By locator, String exp) {
		if (getText(locator).equals(exp)) {

			return true;
		} else {
			return false;
		}
	}

	public void SelectItem(By locator, String Item) {

		Select contentsSelect = new Select(find(locator));
		List<WebElement> options = contentsSelect.getOptions();
		for (WebElement e : options) {
			System.out.println(e.getText());
		}
		// contentsSelect.selectByValue(Item);
		contentsSelect.selectByVisibleText(Item);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean IsElementPresent(By locator) {
		return findelements(locator).size() > 0;
	}

	public boolean linkNotDisplayed(String link) {
		if (this.driver.findElements(By.linkText("myLinkText")).size() > 0)
			;
		return true;

	}

	public boolean verifyElementAbsent(String locator) throws Exception {
		try {
			driver.findElement(By.xpath(locator));
			System.out.println("Element Present");
			return false;

		} catch (NoSuchElementException e) {
			System.out.println("Element absent");
			return true;
		}
	}

	public boolean IsElementDisplayed(By locator) throws InterruptedException {
		WebElement element;
		try {
			element = find(locator);
		} catch (NoSuchElementException e) {
			return false;
		}

		if (element.isDisplayed()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid green'", element);
			Thread.sleep(2000);

			return true;
		} else {
			return false;
		}
	}

	public boolean IsElementDisplayed(WebElement findElement) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		boolean flag;

		if ((findElement).isDisplayed()) {

			WebElement element = findElement;
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			Thread.sleep(2000);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**********
	 * 
	 * @param title
	 * @return boolean
	 */

	public boolean verify_page_header1(String title) {

		boolean flag = false;
		if (driver.findElement(By.cssSelector("h1.govuk-heading-l")).getText().equals(title))
			return flag = true;
		else {
			return flag = false;
		}
	}

	/************************* Service specific methods ************************/

	/**************
	 * 
	 * @param message
	 * @throws InterruptedException
	 */

	public void verify_banner_message(String message) throws InterruptedException {
		this.IsElementDisplayed(banner_message);
		assertTrue("Failed: Expected sucess" + message + " did not match to actual  ",
				find(banner_message).getText().equals(message));

	}

	/*******
	 * 
	 * @param message
	 * @throws InterruptedException
	 */
	public void verify_confirmation_panel_message(String message) throws InterruptedException {
		this.IsElementDisplayed(confirmation_panel_message);
		assertTrue("Failed: Expected notification" + message + " did not match to actual  ",
				find(confirmation_panel_message).getText().equals(message));
	}

	public Boolean verify_elements_text(String text, By locator) throws InterruptedException {
		boolean flag = false;
		List<WebElement> elements = this.findelements(locator);
		for (WebElement element : elements) {
			System.out.println(element.getText());
			if (element.getText().contains(text)) {
				// if (element.getText().equalsIgnoreCase(text)) {

				if (element.isDisplayed()) {
					JavascriptExecutor js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].style.border='3px solid blue'", element);
					Thread.sleep(4000);
				}

				return flag = true;

			}
		}

		return flag;
	}

	public void verifyPageTitle(String title) {
		assertTrue("Failed: Expected Page " + title + " did not match to actual  " + driver.getTitle() + "",
				driver.getTitle().equals(title));

	}

	public boolean verify_par_page_header1(String title) {

		// System.out.println(driver.findElement(By.cssSelector("h1.heading-xlarge")).getText());
		boolean flag = false;
		if (driver.findElement(By.cssSelector("h1.heading-xlarge")).getText().equals(title))

			return flag = true;
		else {
			return flag = false;
		}
	}

	public boolean verify_page_contains(String text) {
		boolean flag = false;
		if (driver.getPageSource().contains(text)) {
			return flag = true;
		} else {
			return flag = false;
		}

	}

	public boolean verify_cosmetics_page_headers(String title) {

		boolean flag = false;
		if (driver.findElement(By.cssSelector("h1.govuk-heading-xl")).getText().equals(title))
			return flag = true;
		else {
			return flag = false;
		}
	}

	public boolean verify_cosmetics_trigger_rules_question(String question)

	{

		boolean flag = false;
		if (driver.findElement(By.xpath("//h1[@class='govuk-fieldset__heading']")).getText().equals(question))
			return flag = true;
		else {
			return flag = false;
		}
	}

	public boolean verify_annexes(String question)

	{

		boolean flag = false;
		if (driver.findElement(By.xpath("//h1[@class='govuk-fieldset__heading govuk-label--xl']")).getText()
				.equals(question))
			return flag = true;
		else {
			return flag = false;
		}
	}

	public void click_create_project() {
		driver.findElement(By.xpath("//button[normalize-space()='Create project']")).click();
	}

	public void click_back_on_cosmetics_page() {
		driver.findElement(By.xpath("//a[@class='govuk-back-link']")).click();

	}

	public boolean verify_element_by_text(String text) {
		boolean flag = false;
		if (driver.findElement(By.xpath("//a[contains(.,'" + text + "')]")).getText().equals(text))
			return flag = true;
		else {
			return flag = false;
		}
	}

	/**
	 * 
	 * @param radio button text
	 * @throws InterruptedException
	 * @returns true if radio button exist
	 */

	public boolean verify_radio_button_by_text(String text) throws InterruptedException {
		boolean flag = false;
		Thread.sleep(4000);
		if (driver.findElement(By.xpath("//label[contains(text(),'" + text + "')]")).getText().equals(text))
			return true;
		else {
			return flag = false;
		}
	}

	public void click_continue_on_cos() {
		// driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
		driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
	}

	public void click_continue() {
		driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();

		// driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();
	}

	public void click_continue(String button_text) {
		driver.findElement(By.xpath("//button[normalize-space()='" + button_text + "']")).click();
	}

	public void click_create_button() {
		driver.findElement(By.xpath("//button[normalize-space()='Create project']")).click();

	}

	public void click_continue_input() {
		driver.findElement(By.xpath("//input[contains(@type,'submit')]")).click();
		// driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}

	public void click_continue_button() {
		driver.findElement(By.xpath("//input[@name='commit']")).click();
	}

	public void click_continue_enforcement() throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//input[contains(@id,'edit-next')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);

		driver.findElement(By.xpath("//input[contains(@id,'edit-next')]")).click();
	}

	/**
	 * 
	 * @param radio button text
	 */
	public void select_radio_button_by_text(String text) {
		try {
			driver.findElement(By.xpath("//label[contains(.,'" + text + "')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void click_by_text(String text) {
		try {
			driver.findElement(By.xpath("//a[contains(.,'" + text + "')]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method is used to upload a file on a given page Use this method across
	 * any page class where it is needed
	 */

	public void file_upload(By locator, String file) {
		String testFile;
		testFile = System.getProperty("properties",
				System.getProperty("user.dir") + "/src/test/resources/testdata/" + file);
		System.out.println(testFile);
		WebElement uploadElement = this.find(locator);
		uploadElement.sendKeys(testFile);
	}

	public void highlightElements(By locator) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = find(locator);
		js.executeScript("arguments[0].style.border='3px solid red'", element);
		Thread.sleep(3000);
	}

	public boolean find_summary_element_value(String expected_value) {
		boolean element_flag = false;
		List<WebElement> summary_list = this.driver.findElements(By.cssSelector(".govuk-summary-list__value"));

		for (WebElement dd : summary_list) {
			if (dd.getText().contains(expected_value)) {
				element_flag = true;
				break;
			}
		}
		return element_flag;

	}

	/*
	 * This method is to verify actual validation error with expected errors
	 * 
	 */
	public String read_error_summary(By locator) {

		String actual_error;
		return actual_error = driver.findElement(locator).getText();

	}

	/*
	 * This method is to return h1 on any given page To Validate with expected h1
	 * and ensure it is correct page
	 */
	public void verify_page_h1(String h1) throws InterruptedException {
		By h1_header = By.xpath("//h1[contains(text(),'" + h1 + "')]");
		this.IsElementDisplayed(page_h1);
		String actual_h1 = driver.findElement(h1_header).getText();
		assertTrue("Failed:" + actual_h1 + " doesn't match expected " + h1, (actual_h1).equals(h1));
	}

}
