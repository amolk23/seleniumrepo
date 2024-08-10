package com.crm.generic.baseutility;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Generic.databaseUtility.DataBaseUtility;

import Generic.fileUtility.ExcelUtility;
import Generic.fileUtility.FileUtility;
import Generic.webdriverUtility.JavaUtility;
import Generic.webdriverUtility.WebDriverUtility;
import objectRepo.HomePage;
import objectRepo.LoginPage;


public class BaseClass {
  	public WebDriver driver = null;
  	public static WebDriver sdriver =null;
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib= new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	@BeforeSuite(groups = {"smoke", "regression"})
	public void configBS()throws Throwable {
		System.out.println("==connect toDB, Report Config===");
		dbLib.getDbConnection();
	}		
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws Throwable {
   	 System.out.println(" == Launch the Browser=="); 
   	 String BROWSER = fLib.getDataFromPropertiesFile("browser");
	
	if (BROWSER.equals("chrome")) {
		 driver = new ChromeDriver();
	}else if (BROWSER.equals("firefox")) {
		driver = new FirefoxDriver();
	}else if (BROWSER.equals("edge")) {
		driver=new EdgeDriver();
	}else {
		driver= new ChromeDriver();
	}
	//this decision of initialization is takesn at runtime
    sdriver = driver;
	}
	@BeforeMethod(groups= {"smokeTest", "regresstionTest"})
	    public void configBM() throws Throwable {
		System.out.println(" == login to the application== ");
		LoginPage lp = new LoginPage(driver);
		
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");		
	
		lp.LoginToApp(URL, USERNAME, PASSWORD);		
    }  
   @Test 
	public void CreateContact(){
		System.out.println("execute createContact");	
	}
   @Test 
     public void CreateContactWithDate(){
		System.out.println("execute CreateContactWithDate");
	}
	 @AfterMethod(groups = {"smokeTest","regressionTest"})
	 public void confiAM() {
		 System.out.println("logout from application");
		 HomePage hp = new HomePage(driver);
		 hp.getSignOut();
	 }
	 @AfterClass
		public void configAC() {
	   	 System.out.println(" ===close the BROWSER=== "); 
	   	 driver.quit();
	    }
	 @AfterSuite(groups= {"smoke", "regression"})
		public void configAS() throws SQLException {
			System.out.println("close DB, report backup");
           dbLib.closeDbConnection();	
           }

}
