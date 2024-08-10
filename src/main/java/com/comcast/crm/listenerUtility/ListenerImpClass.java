package com.comcast.crm.listenerUtility;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.crm.generic.baseutility.BaseClass;

public class ListenerImpClass implements ITestListener, ISuiteListener{
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public ExtentTest test;
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");
		//spark report config
		ExtentSparkReporter spark = new ExtentSparkReporter("./ADVreport/report333.html");		
		spark.config().setDocumentTitle("automation Test report result");
		spark.config().setReportName("CRM report");
		spark.config().setTheme(Theme.DARK);		
		//add env information & create test
		 report = new ExtentReports() ;
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("BROWSER", "CHROME");
	}	
	public void onFinish(ISuite suite) {
		System.out.println("Report backup");
		report.flush();
	}	
	public void onTestStart(ITestResult result) {
		System.out.println("===== =====>"+result.getMethod().getMethodName()+"====START======");
	    test = report.createTest(result.getMethod().getMethodName());
	    test.log(Status.INFO, result.getMethod().getMethodName()+"==>STARTED<==");
	}	
	public void onTestSuccess(ITestResult result) {
		System.out.println("===== =====>"+result.getMethod().getMethodName()+"====ENDED======");
		test.log(Status.PASS, result.getMethod().getMethodName()+"==>COMPLETE<==");
	}
	public void onTestFailure(ITestResult result) {
		String testName= result.getMethod().getMethodName();		
		TakesScreenshot eDriver = (TakesScreenshot)BaseClass.sdriver;
		String filePath =eDriver.getScreenshotAs(OutputType.BASE64);	
		
		String time = new Date().toString().replace(' ', '_').replace(':', '_');
		test.addScreenCaptureFromBase64String(filePath,testName +"_"+ time);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==>NOT COMPLETED<==");	 
	}
	public void onTestSkipped(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName()+"==>SKIPPED<==");		
	}
}
