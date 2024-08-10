package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CraeteWithNameOrg {

	
	@FindBy(xpath="//a[text()='Organizations']")
	private WebElement orgmodule;
	
	@FindBy(xpath="//img[@title=\"Create Organization...\"]")
	private WebElement craeteorg;
	
	@FindBy(name="accountname")
	private WebElement orgname;
	
	@FindBy(id="otherphone")
	private WebElement phn;
	
	@FindBy(name="button")
	private WebElement save;
	
	public CraeteWithNameOrg(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrgmodule() {
		return orgmodule;
	}

	public void setOrgmodule(WebElement orgmodule) {
		this.orgmodule = orgmodule;
	}

	public WebElement getCraeteorg() {
		return craeteorg;
	}

	public void setCraeteorg(WebElement craeteorg) {
		this.craeteorg = craeteorg;
	}

	public WebElement getOrgname() {
		return orgname;
	}

	public void setOrgname(WebElement orgname) {
		this.orgname = orgname;
	}

	public WebElement getPhn() {
		return phn;
	}

	public void setPhn(WebElement phn) {
		this.phn = phn;
	}

	public WebElement getSave() {
		return save;
	}

	public void setSave(WebElement save) {
		this.save = save;
	}
	
}
