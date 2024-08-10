package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Logout {

	@FindBy (name="user_name")
	private WebElement user;

	@FindBy (name="user_password")
	private WebElement pass;
	
	
	@FindBy(xpath="//td[@onmouseover=\"fnDropDownUser(this,'ondemand_sub','~admindk@gmail.com`');\"]")
	private WebElement mouselogout;
	
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement logout;
	
	public WebElement getMouselogout() {
		return mouselogout;
	}

	public void setMouselogout(WebElement mouselogout) {
		this.mouselogout = mouselogout;
	}

	public WebElement getLogout() {
		return logout;
	}

	public void setLogout(WebElement logout) {
		this.logout = logout;
	}

	public Login_Logout(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getUser() {
		return user;
	}

	public WebElement getPass() {
		return pass;
	}

}
