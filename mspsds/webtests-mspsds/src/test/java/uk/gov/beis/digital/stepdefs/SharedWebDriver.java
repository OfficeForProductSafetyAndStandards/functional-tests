package uk.gov.beis.digital.stepdefs;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import uk.gov.beis.digital.mspsds.Utils.AppProperties;
import uk.gov.beis.digital.mspsds.Utils.EnvironmentProperties;

public class SharedWebDriver  {
	// private static AndroidDriver driver1;
	private static WebDriver driver;
	/*
	 * Test run in below platforms In-progress
	 */
	static {
		final String _OS_LINUX = "linux";
		final String _OS_WINDOWS = "Windows";
		final String _OS_MAC = "MAC";

		try {
			String browser = AppProperties.get("browser");
			String platform = AppProperties.get("platform");
			String Envurl = EnvironmentProperties.getServiceUrl();
			String envurl = AppProperties.get("envurl");
			String env =null;

			// this is now de-commissioned as test can be run browser stack in required
			// browser/platform

			String OS = AppProperties.get("OS").toLowerCase();
			if (OS.length() == 0 || "detect".equals(OS.toLowerCase())) {
				if (SystemUtils.IS_OS_WINDOWS)
					OS = _OS_WINDOWS;
				else if (SystemUtils.IS_OS_MAC)
					OS = _OS_MAC;
				else if (SystemUtils.IS_OS_LINUX)
					OS = _OS_LINUX;
			}

			/**
			 * browser stack integration- Please note user name/pwd for browserstack should
			 * be provided as environment(not in GIT)
			 */
			if (platform.equalsIgnoreCase("browserstack")) {
				if(OS.equals(_OS_LINUX))
				{
					env = Envurl;
				}
				else {
					env = envurl;
				}
				DesiredCapabilities caps = new DesiredCapabilities();
				String USERNAME = AppProperties.get("bsUsername");
				String AUTOMATE_KEY = AppProperties.get("bsUserkey");

//				String USERNAME = System.getenv("BSUSERNAME");
//				String AUTOMATE_KEY = System.getenv("BSUSERKEY");

				String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
				caps.setCapability("browser", "IE");
				caps.setCapability("browser_version", "11.0");
				caps.setCapability("os", "Windows");
				caps.setCapability("os_version", "10");
				caps.setCapability("resolution", "1024x768");
				;
				caps.setCapability("browserstack.debug", "true");
				driver = new RemoteWebDriver(new URL(URL), caps);
				

				//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				driver.navigate().to(env);
				driver.manage().window().maximize();
				

			} 
			//local test to run in chrome by default
			else if (platform.equalsIgnoreCase("local")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/resources/chromedriver");
				ChromeOptions options = new ChromeOptions();
				//options.setHeadless(true);
				//options.addArguments("no-sandbox");
				//options.addArguments("disable-dev-shm-usage");
				//options.addArguments("window-size=1024x768");
				

				driver = new ChromeDriver();
				System.out.println("#####Started test run on  " + envurl + "  on " + browser + " browser #####");
		
				//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				driver.navigate().to(envurl);
				driver.manage().window().maximize();
				//driver.manage().deleteAllCookies();
			} 
			else {
				System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

				ChromeOptions options = new ChromeOptions();
				options.setHeadless(true);
				options.addArguments("no-sandbox");
				options.addArguments("disable-dev-shm-usage");
				options.addArguments("window-size=1024x768");

				driver = new ChromeDriver(options);

				driver.manage().window().maximize();
				System.out.println("#####Started test run on  " + Envurl + "  on " + browser + " browser #####");
				//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				driver.navigate().to(Envurl);
				driver.manage().window().maximize();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				try {
					driver.quit();
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		});
	}

	public static void launchBrowser()
	{
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	
	}
