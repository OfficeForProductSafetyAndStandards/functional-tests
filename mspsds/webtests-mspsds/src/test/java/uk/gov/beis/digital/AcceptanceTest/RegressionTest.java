package uk.gov.beis.digital.AcceptanceTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	format = { "html:target/cucumber-reports/html", "json:target/cucumber-reports/cucumber.json" },
	features = { "src/test/resources/Features" },
	tags = { "@regression","~@ignore,@Pending" },
	glue="uk.gov.beis.digital.stepdefs"
)
public class RegressionTest {
}
