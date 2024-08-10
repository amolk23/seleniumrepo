package createOrg;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.Property;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.internal.BaseClassFinder;

import com.crm.generic.baseutility.BaseClass;

import Generic.fileUtility.FileUtility;
import Generic.webdriverUtility.JavaUtility;

public class CreateOrganizationWithIndustriesTest extends BaseClass {

	@Test
	public void createOrgWithIndustry() throws Throwable{

		FileInputStream fis = new FileInputStream("./src/main/resources/CrOrgData.properties");
		Properties oPro = new Properties();
		oPro.load(fis);

		String BROWSER = oPro.getProperty("browser");
		String URL = oPro.getProperty("url");
		String USERNAME = oPro.getProperty("username");
		String PASSWORD = oPro.getProperty("password");

		// genreate the random number
		// read testscript data from excle file
		FileInputStream fis1 = new FileInputStream("./src/main/resources/testScriptData.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("org");
		Row row = sh.getRow(4);
		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNum(1000);
		String induestry = row.getCell(3).toString();
		String type = row.getCell(4).toString();
//			wb.close();

		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));

		driver.get(URL);
		driver.manage().window().maximize();
		// login
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		// navigate to orgnization
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		// entering orgnization dropdown details
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select sel = new Select(wbsele1);
		sel.selectByVisibleText("Induestry is " + induestry);

		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wbsele2);
		sel2.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verfy the industories and type info
		String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
		System.out.println(actIndustries);
		if (actIndustries.equals(induestry)) {
			System.out.println(induestry + " information id verified == Pass");
		} else {
			System.out.println(induestry + "information is not verifyed == Fail");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		System.out.println(actType);
		if (actType.equals(type)) {
			System.out.println(type + " information id verified == Pass");
		} else {
			System.out.println(type + " information is not verifyed == Fail");
		}
		// logout
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		driver.quit();

	}
}
