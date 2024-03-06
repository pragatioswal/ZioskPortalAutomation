//This is login page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ztp.qa.base.TestBase;




public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	
	@FindBy(id="signInName")
	WebElement username;
	

	@FindBy(id="password")
	WebElement password;
	

	@FindBy(id="next")
	WebElement loginBtn;
	

	@FindBy(xpath = "//img[@alt='Company Logo']")
	WebElement mainlogo;
	
	@FindBy(xpath = "//h2[normalize-space()='Sign in with your existing account']")
	WebElement loginHeader;
	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
	
		waitForVisibilityOf(loginHeader, 10);
		
		
		return driver.getTitle();
	}
	
	public boolean validateMainLogoImage(){
		waitForElementToDisplay(mainlogo,10);
		return mainlogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		waitForElementToDisplay(loginBtn,10);
		loginBtn.click();		    	
		return new HomePage();
	}

}
