/*package adhoc;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class ExtentReport {
	
	public ExtentSparkReporter reporter = new ExtentSparkReporter("C:\\Users\\cuten\\IDE-Handson\\nk-automation\\Report.html");
	public ExtentReports extent = new ExtentReports();
	public ExtentTest test = null;
	public WebDriver driver;
	public String baseDir = System.getProperty("user.dir"); 
	
	@BeforeSuite
	public void createReport() {
		extent.attachReporter(reporter);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setReportName("NK Automation");
	}
	
	@AfterSuite
	public void closeReport() {
		extent.flush();
		driver.quit();
	}
	
	public void initializeDriver() throws Throwable {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--incognito");
			//options.addArguments("--headless");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\cuten\\Downloads\\chromedriver_win32\\chromedriver.exe");
			
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_custrec_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&");
			waitForElementVisible(driver.findElement(By.xpath("//input[@id='continue']")));
			reportLog("Application launch successful","PASS");
			
		}catch(Exception e) {
			e.printStackTrace();
			reportLog("Application launch successful","FAIL");
		}
	}
	
	public void waitForElementVisible (WebElement element) throws Throwable {
		  try {
			  new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));
		  }catch(Exception e) {
			  e.printStackTrace();
			  System.out.println(element+"not visible");
		  }
	  }
	  
	  public void click (WebElement element) throws Throwable {
		  try {
			  new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(element));
			  element.click();
		  }catch(Exception e) {
			  //e.printStackTrace();
			  System.out.println(element+" not clickable");
		  }
	  
	  }
	  
	  public String getDateTime() throws Exception {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			return dtf.format(now);
		}
	  
	  public void reportLog(String message, String status) throws Exception{
		  try {
			  
			  String time = getDateTime().replace("/", "").replace(":","").replace(" ", "");
			  System.out.println("Modified Time: "+time);
			  TakesScreenshot scrShot =((TakesScreenshot) driver);
			  String sourceFile = scrShot.getScreenshotAs(OutputType.BASE64);
			  if(status.trim().equalsIgnoreCase("PASS"))
				  test.log(Status.PASS, message, MediaEntityBuilder.createScreenCaptureFromBase64String(sourceFile).build());
			  else
				  test.log(Status.FAIL, message, MediaEntityBuilder.createScreenCaptureFromBase64String(sourceFile).build());

		  }catch(Exception e) {
			  e.printStackTrace();
			  System.out.println("Take snapshot failed for "+message);
			  test.log(Status.FAIL, message);
		  }
	  }
	  
	  	//Application Functions
		public void applicationLogin() throws Throwable {
			try {
				driver.switchTo().defaultContent();
				waitForElementVisible(driver.findElement(By.xpath("//input[@id='continue']")));
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys("9488892405");
				click(driver.findElement(By.xpath("//input[@id='continue']")));
				waitForElementVisible(driver.findElement(By.xpath("//input[@name='password']")));
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("n15/09/1996");
				click(driver.findElement(By.xpath("//input[@id='signInSubmit']")));
				waitForElementVisible(driver.findElement(By.xpath("//input[@value='Go']")));
				reportLog("Application Login successful","PASS");
				
			}catch(Exception e) {
				e.printStackTrace();
				reportLog("Application Login successful","FAIL");
			}
		}
		
		public void productSearch() throws Throwable {
			String product = "OnePlus Nord CE 2 5G";
			try {
				driver.switchTo().defaultContent();
				click(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")));
				driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).clear();
				driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(product);
				System.out.println("Product details entered in search panel");
				click(driver.findElement(By.xpath("//input[@value='Go']")));
				waitForElementVisible(driver.findElement(By.xpath("//span[text()='RESULTS']")));
				try {
					int size = driver.findElements(By.xpath("//span[contains(text(),'"+product+"') and @class='a-size-medium a-color-base a-text-normal']")).size();
					if(size>0) {
						driver.findElements(By.xpath("//span[contains(text(),'"+product+"') and @class='a-size-medium a-color-base a-text-normal']"));
						Actions action = new Actions(driver);
						action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'"+product+"') and @class='a-size-medium a-color-base a-text-normal']/../../../..//a//span[@class='a-price']"))).perform();
						reportLog("The results for "+product+" are displayed","PASS");
					}
				}catch(Exception e) {
					reportLog("The results for "+product+" are not displayed","PASS");
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				reportLog("Product search failed for: "+product,"FAIL");
			}
			
		}
	
	@Test
	public void Launch() throws Throwable {
		test = extent.createTest("Launch");
		initializeDriver();
	}
	
	@Test
	public void Login() throws Throwable {
		test = extent.createTest("Login");
		applicationLogin();
		
	}
	
	@Test
	public void search() throws Throwable {
		test = extent.createTest("Search");
		productSearch();
		
	}
	
	
	

}*/
