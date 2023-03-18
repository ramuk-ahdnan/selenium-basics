package steps;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import adhoc.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.*;
import runner.TestNGRunner;

public class stepDefinitions {
	
	public String description = null;
	public String tag = null;
	public String status = null;
	public String time = null;
	public WebDriver driver;
	
	public appLocators appLocators = new appLocators(driver);;
	public ExcelReadWrite excelwrite = new ExcelReadWrite();
	
	@Before
	public void createTest(Scenario scenario) throws Throwable {
		 description = scenario.getName();
		 tag = scenario.getSourceTagNames().toString();
		 String temp[] = tag.split(",");
		 tag = null;
		 for(int i=0;i<temp.length;i++) {
			 if(i==0) 
				 tag=temp[i];
			 else
				tag+=":"+temp[i].trim();
		 }
		 TestNGRunner.test = TestNGRunner.extent.createTest(tag+":"+description);
	}
	
	@After
	public void updateStatus(Scenario scenario) throws Throwable{
		
		System.out.println("Updating Data to excel......");
		status=scenario.getStatus().toString();
		time = appLocators.getDateTime();
		List<String> list = new ArrayList<String>();
		list.add(time);
		list.add(tag);
		list.add(description);
		list.add(status);
		excelwrite.updateStatus(list);
		TestNGRunner.extent.flush();
		try {
			driver.quit();
		}catch(Exception e) {
			System.out.println("Driver already closed");
		}
	}
	
	
	@Given("I launch the application")
	public void i_launch_the_application() throws Throwable {
		appLocators.initializeDriver();
	}
	
	@And("login using my credentials")
	public void login_using_my_credentials() throws Throwable {
		appLocators.applicationLogin();
	}
	
	@When("^I search for the product \"([^\"]*)\"$")
	public void i_search_for_the_product(String product) throws Throwable {
		appLocators.productSearch(product);
	}
	
	@When("I search for the product using data table")
	public void i_search_for_the_product_using_data_table(DataTable dataTable) throws Throwable {
		appLocators.productSearch(dataTable);
	    
	}
	
	@Then("I logout of the application")
	public void i_logout_of_the_application() throws Throwable {
		appLocators.applicationLogout();
	}
	
	
	
}
