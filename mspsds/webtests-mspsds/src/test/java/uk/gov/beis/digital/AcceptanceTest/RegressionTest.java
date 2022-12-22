package uk.gov.beis.digital.AcceptanceTest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(
		
		
		features = { "src/test/resources/Features" },
		glue="uk.gov.beis.digital.stepdefs",
		dryRun = false,
        tags = "@regression",
        plugin = {"pretty","html:target/CucumberHTMLRep.html"})
				
public class RegressionTest {
}
