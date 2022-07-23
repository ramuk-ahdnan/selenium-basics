package runner;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
tags = "@Amazon",
features = "src/test/resources/features",
glue = "steps",
plugin = {"json:target/testng-report.json","html:target/testng-report.html","pretty"},
monochrome = false)
public class TestNGRunner extends  AbstractTestNGCucumberTests{
	
	public static ExtentSparkReporter reporter = new ExtentSparkReporter("./ExtentReport.html");
	public static ExtentReports extent = new ExtentReports();
	public static ExtentTest test = null;
	
	@BeforeSuite
	public void hello() {
		System.out.println("HELLO");
		extent.attachReporter(reporter);
		//reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("NK Automation");
	}
	
	@SuppressWarnings("deprecation")
	@AfterSuite
	public void bye() throws Throwable {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
		extent.flush();
	}
	
}
