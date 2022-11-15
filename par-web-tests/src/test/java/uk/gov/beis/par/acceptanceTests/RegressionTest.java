package uk.gov.beis.par.acceptanceTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	format = { "html:target/cucumber-reports/html", "json:target/cucumber-reports/cucumber.json" },
	features = { "src/test/resources/functional/features/" },
	tags = { "@regression","~@ignore,@Pending" },
	glue="uk.gov.beis.par.stepdefs"
)
public class RegressionTest {
}
