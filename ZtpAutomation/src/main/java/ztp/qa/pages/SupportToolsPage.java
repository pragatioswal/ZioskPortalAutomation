//This is Support Tools page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ztp.qa.base.TestBase;
public class SupportToolsPage extends TestBase{
	
	//Page Factory - OR:
	
		@FindBy(xpath="//mat-card-content[normalize-space()='Watcher Client helps to monitor services at one particular site']")
		WebElement WatcherClientSelection;
		
		@FindBy(xpath="//mat-card-content[normalize-space()='Restaurant Management Portal helps to monitor one particular site.']")
		WebElement RMPortalSelection;
		
		@FindBy(xpath="//mat-card-content[normalize-space()='Restaurants can create QR Code to join on their network']")
		WebElement QRCodeSelection;
		
		@FindBy(xpath="//mat-card-content[normalize-space()='This tool helps to provides support regarding the POS Plugin']")
		WebElement POSSelection;
		
	// Initializing the Page Objects:
		public SupportToolsPage() {
			PageFactory.initElements(driver, this);
		}
		
	
		//mat-card-content[normalize-space()='Watcher Client helps to monitor services at one particular site']
		public WatcherPage GotoWatcherClient () {
			WatcherClientSelection.click();
			return new WatcherPage();
		}

		public boolean ValidateWatcherClientImg() {
		 return WatcherClientSelection.isDisplayed();
		}
		
		public QRCodePage GotoQRCodePage () {
			QRCodeSelection.click();
			return new QRCodePage();
		}
		
		public POSPage GotoPOSPage () {
			POSSelection.click();
			return new POSPage();
		}
}
