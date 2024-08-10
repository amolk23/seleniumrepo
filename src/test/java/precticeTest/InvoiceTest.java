package precticeTest;

import org.testng.annotations.Test;

import com.crm.generic.baseutility.BaseClass;

public class InvoiceTest extends BaseClass {
	@Test
	public void createInvoiceTest() {
		String actTitle= driver.getTitle();
		System.out.println();
		System.out.println("executeInvoiceTest");
	}
	@Test
	public void createInvoicewithContactTest() {
		System.out.println("execute createInvoiceWithContactTest");
	}

}
