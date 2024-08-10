package com.crm.generic.baseutility;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class BaseClass {
  	public WebDriver driver = null;
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib= new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility(); 
	
	@BeforeSuite
	public void configBS() throws Throwable {
		System.out.println("==connect toDB, Report Config===");
		//dbLib.getDbconnection();
	}		
	@BeforeClass
	public void configBC() throws Throwable {
   	 System.out.println(" == Launch the Browser=="); 
   	 String BROWSER = fLib.getDataFromPropertiesFile("browser");
   	 
	
	if (BROWSER.equals("chrome")) {
		 driver = new ChromeDriver();
	}else if (BROWSER.equals("firefox")) {
		driver = new FirefoxDriver();
	}
    }
	@BeforeMethod
	    public void configBM() throws Throwable {
		System.out.println(" == login to the application== ");
		LoginPage lp = new LoginPage(driver);
		
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");		
	
		lp.loginToapp(URL, USERNAME, PASSWORD);	
		
    }  
   @Test 
	public void CreateContact(){
		System.out.println("execute createContact");	
	}
   @Test 
     public void CreateContactWithDate(){
		System.out.println("execute CreateContactWithDate");
		
	}
	 @AfterMethod
		     public void configAM(){
	    	 System.out.println("===LogOut===");
	    	 HomePage hp = new HomePage(driver);
	    	 hp.logout(driver);
        	 }
	 @AfterClass
		public void configAC() {
	   	 System.out.println(" ===close the BROWSER=== "); 
	   	 driver.quit();
	    }
	 @AfterSuite
		public void configAS() {
			System.out.println("execute AS");
		}

}
