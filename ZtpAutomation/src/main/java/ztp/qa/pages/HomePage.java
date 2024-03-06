//This is home page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import ztp.qa.base.TestBase;


public class HomePage extends TestBase {

	LoginPage loginPage;
	// Page Factory - OR:

	@FindBy(xpath = "//mat-card-title[normalize-space()='Support Portal']")
	WebElement SPLabel;

	@FindBy(xpath = "//button[@class='mat-menu-trigger btn btn-light rounded-circle initial-button']")
	WebElement LogoutMenu;
	
	@FindBy(xpath = "//button[normalize-space()='Log Out']")
	WebElement LogoutBtn;

	@FindBy(xpath = "//div[@role='listbox']")
	WebElement StoreListBox;
	
	@FindAll({ @FindBy(xpath = "//input") })
	List<WebElement> AllTextBoxes;
	
	@FindBy(xpath = "//span[@class='mat-option-text']")
	WebElement Value;
	
	
	@FindBy(xpath = "//button[@class='mat-focus-indicator search-button mat-flat-button mat-button-base']")
	WebElement RM_SearchBtn;
	
	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}


	public boolean validateSPLabel() {
		waitForVisibilityOf(SPLabel, 10);
		return SPLabel.isDisplayed();
	}


	public LoginPage Logout() {

		waitForVisibilityOf(LogoutMenu, 10);
		LogoutMenu.click();
		LogoutBtn.click();
		return new LoginPage();
	}
	
	public SupportToolsPage RMSearch() {
		String store= prop.getProperty("store");
		String storeXpath= "//span[@class='mat-option-text' and contains(text(),'"+ store +"')]";
		//System.out.println(storeXpath);

		
		AllTextBoxes.get(0).click();
		Value.click();
		AllTextBoxes.get(1).click();
		Value.click();
		AllTextBoxes.get(2).click();
		waitForVisibilityOf(StoreListBox, 10);
		WebElement storeNameOrNumber = driver.findElement(By.xpath(storeXpath));
		storeNameOrNumber.click();
		waitForVisibilityOf(RM_SearchBtn, 10);
		RM_SearchBtn.click();
		return new SupportToolsPage();
	}
}