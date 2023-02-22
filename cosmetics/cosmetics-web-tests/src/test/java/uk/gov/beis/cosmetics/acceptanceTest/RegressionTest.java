package uk.gov.beis.cosmetics.acceptanceTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
	
	features = { "src/test/resources/Features" },
	tags = " @regression",
	dryRun = false,
	glue = "uk.gov.beis.cosmetics.stepdefs",
	 plugin = {"pretty","html:target/CucumberHTMLRep.html"}
)
public class RegressionTest {
}
