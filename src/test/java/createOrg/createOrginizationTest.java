package createOrg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic.fileUtility.ExcelUtility;
import Generic.webdriverUtility.JavaUtility;
import Generic.webdriverUtility.WebDriverUtility;
import objectRepo.CreateNewOrganizationPage;
import objectRepo.HomePage;
import objectRepo.LoginPage;
import objectRepo.OrganizationInfoPage;
import objectRepo.OrganizationPage;

public class createOrginizationTest {

	@Test
	public void createOrgTest() throws Exception {
		JavaUtility ju=new JavaUtility();
		ExcelUtility elib = new ExcelUtility();
		String orgName = elib.getDataFromExcel("org", 1, 2);
		int r_no=ju.getRandomNum(1000);
		WebDriver driver=new ChromeDriver();
		//driver.get("http://localhost:8888");
		WebDriverUtility wbu=new WebDriverUtility();
		LoginPage lp=new LoginPage(driver);
		wbu.waitForPageToLoad(driver);
		lp.LoginToApp("http://localhost:8888" ,"admin", "admin");
		HomePage hp=new HomePage(driver);
		hp.getOrganizations().click();
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateOrganizationbtn().click();
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg("orgName"+r_no);
		op.getCreateOrganizationbtn().click();
        cnop.createOrg(null, null);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		SoftAssert soft=new SoftAssert();
		soft.assertTrue(oip.verifyOrgNameinTable(driver, "DineshBhau_"+r_no));
		
		Assert.assertTrue(oip.verifyOrgNameinHeader(driver, "DineshBhau_"+r_no));
		
		hp.signOut(driver);
		driver.quit();
	}
}
