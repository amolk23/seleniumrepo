package createOrg;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.Properties;
	import java.util.Random;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;

	public class createOrgnizationWithPhoneNumber {

		public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
			// TODO Auto-generated method stub
			//read the data from properties file
			FileInputStream fis = new FileInputStream("D:\\Workspaces\\comcast.CRM.main.Vtiger\\src\\main\\resources\\CrOrgData.properties");
			Properties oPro= new Properties();
			oPro.load(fis);
			
			String BROWSER = oPro.getProperty("browser");
			String URL = oPro.getProperty("url");
			String USERNAME = oPro.getProperty("username");
			String PASSWORD = oPro.getProperty("password");
			
			//genreate the random number
			Random random = new Random();
			 int randomInt = random.nextInt(1000);
			 
			//read testscript data from excle file
				FileInputStream fis1 = new FileInputStream("‪D:\\Workspaces\\comcast.CRM.main.Vtiger\\src\\main\\resources\\testScriptData.xlsx");
				Workbook wb= WorkbookFactory.create(fis1);
				Sheet sh= wb.getSheet("org");
				Row row = sh.getRow(7);
				String orgName = row.getCell(2).toString()+ randomInt;
				String phoneNumber = row.getCell(3).toString();
				wb.close();
				
				WebDriver driver = null;
				
				if (BROWSER.equals("chrome")) {
					 driver = new ChromeDriver();
				}else if (BROWSER.equals("firefox")) {
					driver = new FirefoxDriver();
				}
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
				
				driver.get(URL);
				driver.manage().window().maximize();
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				Thread.sleep(2000);
				//driver.findElement(By.xpath("//input[@type='submit']/ancestor::form[@name='DetailView']")).sendKeys(USERNAME);
				
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				driver.findElement(By.linkText("Organizations")).click();
				//driver.findElement(By.xpath("//a[text()='Organizations']/ancestor::td[@class='tabUnSelected']")).click();
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				
				driver.findElement(By.name("accountname")).sendKeys(orgName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				driver.findElement(By.id("phone")).sendKeys(phoneNumber);
				System.out.println(orgName + " Organization is created");				
				
				//verify expected msg expectes result
				String actphoneNumber = driver.findElement(By.id("dtlview_Phone")).getText();
				if (actphoneNumber.equals(phoneNumber)) {
				   System.out.println(phoneNumber + " phoneNumber is created == Pass");
				}else {
					System.out.println(phoneNumber + " is not Created == Fail");
				}			
				
				//logout
				Actions act = new Actions(driver);
				act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				driver.quit();
			
		}
	}


	
