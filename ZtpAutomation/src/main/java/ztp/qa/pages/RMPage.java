//This is POS Plugin Tester page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;





import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ztp.qa.base.TestBase;


public class RMPage extends TestBase {

	// Page Factory - OR:

	
	@FindAll({ @FindBy(xpath = "//span[contains(@class,'mat-select-min-line')]") })
	List<WebElement> Dropdowns;
	
	@FindAll({ @FindBy(xpath = "//span[@class='health-label']") })
	List<WebElement> RMSiteDetails;
	@FindBy(xpath = "//mat-select//span[contains(@class,'mat-select-min-line')]")
	WebElement CurrentTool;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'RM Portal')]")
	WebElement Tool_RMPortal;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'POS Tester')]")
	WebElement Tool_POSTester;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'QR Code')]")
	WebElement Tool_QRCode;

	// Initializing the Page Objects:
	public RMPage() {
		PageFactory.initElements(driver, this);
	}

	public POSPage RMtoPOS() {
		waitForElementToDisplay(CurrentTool, 10);
		waitForVisibilityOf(CurrentTool, 10);
		CurrentTool.click();
		waitForVisibilityOf(Tool_POSTester, 10);
		Tool_POSTester.click();
		return new POSPage();
	}

	public boolean VerifyRMPage() {
		if(RMSiteDetails.size()==15)
			return true;
		else 
			return false;
	}



	
	
}
