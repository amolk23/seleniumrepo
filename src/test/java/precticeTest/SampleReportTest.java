package precticeTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class SampleReportTest {	
	ExtentReports report;
	@BeforeSuite
	public void configBS() {
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./ADVreport/report13.html");
		spark.config().setDocumentTitle("automation Test report result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);
				
		//add env information & create test
		report = new ExtentReports() ;
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("BROWSER", "CHROME");		
	}
	@AfterSuite
	public void configAS() {
		report.flush();
	}
	@Test
	public void createContactTest() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888");
		TakesScreenshot eDriver = (TakesScreenshot)driver;
		String filePath =eDriver.getScreenshotAs(OutputType.BASE64);	
		
		ExtentTest test = report.createTest("createConatctTast");
		test.log(Status.INFO,"Login to app");	
		test.log(Status.INFO,"navigate to contact page");	
		test.log(Status.INFO,"create contact");
		if ("HDFC".equals("HDFCs")) { 
			test.log(Status.PASS, "contact is created");
		}else {
			test.addScreenCaptureFromBase64String(filePath,"ErrorScreen");	
		}	report.flush();
	}
	@Test
	public void createContactWithOrg() {	
		ExtentTest test = report.createTest("createContactWithOrg");	
		test.log(Status.INFO,"Login to app");	
		test.log(Status.INFO,"navigate to contact page");	
		test.log(Status.INFO,"create contact");
		if ("HDFC".equals("HDFCs")) { 
			test.log(Status.PASS, "contact is created");
		}else {
			test.log(Status.FAIL,"contact is not createed");
		}	
	}
	@Test
	public void createContactWithPhoneNumber() {	
		ExtentTest test = report.createTest("createContactWithPhoneNumber");	
		test.log(Status.INFO,"Login to app");	
		test.log(Status.INFO,"navigate to contact page");	
		test.log(Status.INFO,"create contact");
		
		if ("HDFC".equals("HDFC")) { 
			test.log(Status.PASS, "contact is created");
		}else {
			test.log(Status.PASS,"contact is not createed");
		}	report.flush();
	}

}
