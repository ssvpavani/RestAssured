package TestRunner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src\\test\\resources\\Features\\PlaceValidations.feature"},
		glue= "StepDefinitions",
		//tags="@DeletePlace",
		plugin={"pretty", "html:target/cucumber", "json:target/cucumber-report.json"}
		
		)


public class TestRunner {
	
	

}
