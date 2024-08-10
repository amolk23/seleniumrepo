package createContact;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.generic.baseutility.BaseClass;

import objectRepo.ContactInfoPage;
import objectRepo.CreateNewOrganizationPage;
import objectRepo.HomePage;
import objectRepo.OrganizationInfoPage;
import objectRepo.OrganizationPage;

public class createContactTest extends BaseClass {
	
	@Test(groups= "smokeTest")
	public createContactTest() throws IOException, InterruptedException {
	// read test script data from excel file
			String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

			// Step 2: Navigate to Contacts module
			HomePage hp = new HomePage(driver);
			hp.getContacts().click();

			// Step 3: Click on "Create Contact" button
			ContactsPage cp = new ContactsPage(driver);
			cp.getCreateNewContactBtn().click();

			// Step 4: Create new Contact
			CreateNewContactPage cnc = new CreateNewContactPage(driver);

			// Business Logic
			cnc.createContact(lastName);

			// Verify Header msg Expected Result
			ContactInfoPage cip = new ContactInfoPage(driver);
			String headerMsg = cip.getHeaderMsg().getText();
			boolean status = headerMsg.contains(lastName);
			Assert.assertEquals(status, true);

			// Verify orgName info Expected Result
			String actualLastName = cip.getListedLastName().getText();
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(actualLastName, lastName);		
		}
		
		@Test(groups = "regressionTest")
		public void createContactWithSupportDateTest() throws IOException, InterruptedException {

			// read test script data from excel file
			String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNum(90);

			// Step 2: Navigate to Contacts module
			HomePage hp = new HomePage(driver);
			hp.getContacts().click();

			// Step 3: Click on "Create Contact" button
			Contacts cp = new ContactsPage(driver);
			cp.getCreateNewContactBtn().click();

			// Step 4: Create new Contact
			CreateNewContactPage cnc = new CreateNewContactPage(driver);

			String startDate = jLib.getSystemDate(startDate);
			String endDate = jLib.getRequiredDate(endDate, 30);

			// Business Logic
			cnc.createContactWithSupportDate(lastName, startDate, endDate);

			// Verify start date
			ContactInfoPage cip = new ContactInfoPage(driver);
			String actualStartDate = cip.getListedSuppStartDate().getText();
			if (actualStartDate.equals(startDate)) {
				System.out.println(startDate + " is created == PASS");
			} else {
				System.out.println(startDate + " is not created == FAIL");
			}

			// Verify end date
			String actualEndDate = cip.getListedSuppEndDate().getText();
			if (actualEndDate.equals(endDate)) {
				System.out.println(endDate + " is created == PASS");
			} else {
				System.out.println(endDate + " is not created == FAIL");
			}
		}
		
		@Test(groups = "regressionTest")
		public void createContactWithOrgTest() throws Throwable {

			// read test script data from excel file
			String orgName = eLib.getDataFromExcel("contact", 7, 2) + jLib.getRandomNumber();
			String lastName = eLib.getDataFromExcel("contact", 7, 3) + jLib.getRandomNumber();

			// Step 2: Navigate to Organization page
			HomePage hp = new HomePage(driver);
			hp.getOrgLink().click();

			// Step 3: Click on "Create Organization" button
			OrganizationPage op = new OrganizationPage(driver);
			op.getCreateOrganizationbtn().click();
 
			// Step 4: Create new Organization
			CreateNewOrganizationPage cno = new CreateNewOrganizationPage(driver);
			// business logic
			cno.createOrg(orgName);

			// Verify Header msg Expected Result
			OrganizationInfoPage oip = new OrganizationInfoPage(driver);
			String headerMsg = oip.getOrgHeader().getText();
			if (headerMsg.contains(orgName)) {
				System.out.println(orgName + " is created == PASS");
			} else {
				System.out.println(orgName + " is not created == FAIL");
			}

			// Step 6: Navigate to Contacts module
			hp.getContacts().click();

			// Step 8: Click on "Create Contact" button
			ContactsPage cp = new ContactsPage(driver);
			cp.getCreateNewContactBtn().click();

			// Step 9: Create new Contact
			CreateNewContactPage cnc = new CreateNewContactPage(driver);

			// Business Logic
			cnc.createContactWithOrg(lastName, wLib, orgName);

			// Verify Header msg Expected Result
			ContactInfoPage cip = new ContactInfoPage(driver);
			headerMsg = cip.getHeaderMsg().getText();
			if (headerMsg.contains(lastName)) {
				System.out.println(lastName + " is created == PASS");
			} else {
				System.out.println(lastName + " is not created == FAIL");
			}

			// Verify lastName info Expected Result
			String actualLastName = cip.getListedLastName().getText();
			if (actualLastName.equals(lastName)) {
				System.out.println(lastName + " is created == PASS");
			} else {
				System.out.println(lastName + " is not created == FAIL");
			}

			// Verify orgName info Expected Result
			String actualOrgName = cip.getListedOrgName().getText();
			if (actualOrgName.trim().equals(orgName)) {
				System.out.println(orgName + " is created == PASS");
			} else {
				System.out.println(orgName + " is not created == FAIL");
			}
		}
	

}
