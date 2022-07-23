package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
   //path of feature file
   features = "src/test/resources/features/Sample.feature",
   //path of step definition file
   glue = "steps",
   plugin = {"pretty", "html:target/cucumber-report.html"})
public class TestRunner {
	
}