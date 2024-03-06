//This is POS Plugin Tester page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;





import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ztp.qa.base.TestBase;
import ztp.qa.util.TestUtil;


public class POSPage extends TestBase {

	// Page Factory - OR:

	
	@FindAll({ @FindBy(xpath = "//span[contains(@class,'mat-select-min-line')]") })
	List<WebElement> Dropdowns;
	
	@FindAll({ @FindBy(xpath = "//li") })
	List<WebElement> posSettings;
	
	@FindBy(xpath = "//table//tr//td[1]")
	WebElement SystemName;
	
	@FindBy(xpath = "//mat-select//span[contains(@class,'mat-select-min-line')]")
	WebElement CurrentTool;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'RM Portal')]")
	WebElement Tool_RMPortal;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'Watcher Client')]")
	WebElement Tool_WatcherClient;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'POS Tester')]")
	WebElement Tool_POSTester;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'QR Code')]")
	WebElement Tool_QRCode;

	@FindAll({ @FindBy(xpath = "//tr/td") })
	List<WebElement> posFeatures;
	
	@FindAll({ @FindBy(xpath = "//div[contains(@class,'health-container')]//span") })
	List<WebElement> AgentHealthTagNames;
	
	@FindAll({ @FindBy(xpath = "//div[contains(@class,'health-container')]//span/following-sibling::div") })
	List<WebElement> AgentHealthTagValues;
	
	@FindAll({ @FindBy(xpath = "//div[contains(@class,'health-container')]") })
	List<WebElement> AgentHealthBlocks;

	//div[contains(@class,'health-container')]
	//AgentHealthBlocks
	
	//table//tbody//tr//td[1]
	//opn agent config key
	
	//table//tbody//tr//td[2]
		//opn agent config value
	
	//webelements for two buttons
	//span[@class='mat-button-wrapper']
	// Initializing the Page Objects:
	public POSPage() {
		PageFactory.initElements(driver, this);
	}

	

	public boolean VerifyPOSPage() {
		waitForElementContentToDisplay(posSettings.get(0),10);
		return posSettings.get(0).getText().equals("POS Information");	
	}



	public boolean POSInformation() {		
		waitForElementContentToDisplay(SystemName,20);
		return SystemName.getText().equals("Darden DASH POS");
	}



	public boolean POSSystemNameVersion(int i) {
		boolean b=false;
		waitForElementContentToDisplay(posSettings.get(i-1),20);
		posSettings.get(i-1).click();
		waitForElementContentToDisplay(SystemName,20);
		if(i==1) {
			if(SystemName.getText().equals("Darden DASH POS"))
				b=true;
		}else if(i==2){
			if(SystemName.getText().contains("ZioskPOSAgent"))
				b=true;
		}else if(i==3){
			if(SystemName.getText().contains("ZioskPOSPlugin-DASH"))
				b=true;
		}else if(i==8){
			if(!SystemName.getText().isEmpty())
				b=true;
		}else if(i==10){
			if(CheckNum(SystemName.getText()))
				b=true;
		}
		return b;
	}


	public WatcherPage POStoWatcher() {
		waitForElementToDisplay(CurrentTool, 10);
		waitForVisibilityOf(CurrentTool, 10);
		CurrentTool.click();
		waitForVisibilityOf(Tool_WatcherClient, 10);
		Tool_WatcherClient.click();
		return new WatcherPage();
	}

	public boolean CheckNum(String s) {
		boolean isNum=true;
		try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			isNum=false;
		}
		return isNum;
	}

	public boolean CheckDate(String s) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		boolean isDate=true;
		try {
			sdf.parse(s);
		}catch(ParseException e) {
			e.printStackTrace();
			isDate=false;
		}
		return isDate;
	}

	public boolean CheckStringNotNull(String s) {
		if(!s.isEmpty())
			return true;
		else
			return false;
	}

	public boolean PosFeatures() {
		
		boolean b1=false;
		boolean b2=false;
		boolean b3=false;
		waitForElementContentToDisplay(posSettings.get(3),10);
		posSettings.get(3).click();
		waitForElementContentToDisplay(SystemName,20);
		TestUtil.sleep(2000);
			
		if(posFeatures.get(0).getText().contains("PaymentStructure") && 
				CheckNum(posFeatures.get(1).getText()))
			b1= true;
		if(posFeatures.get(2).getText().contains("EncryptedPayment") && 
				CheckNum(posFeatures.get(3).getText()))
			b2= true;
		if(posFeatures.get(4).getText().contains("QuickEMV") && 
				CheckNum(posFeatures.get(5).getText()))
			b3= true;		
	if(b1&&b2&&b3) 
		return true;	
	else 
		return false;	
	}



	public boolean AgentHealth() {
		
		boolean AgentHealthIsDisplayed=true;
		int k;
		waitForElementContentToDisplay(posSettings.get(3),10);
		posSettings.get(4).click();
		TestUtil.sleep(2000);
		
		waitForElementContentToDisplay(AgentHealthTagNames.get(0),20);
		int n=AgentHealthBlocks.size();
		boolean[] b = new boolean[n*3];
		int j=0;
		for(int i=0;i<n;i++)
		{
			//first (Agent started/operation	
			//b[j++]=CheckStringNotNull(AgentHealthTagNames.get(7*i).getText());
			b[j++]=CheckStringNotNull(AgentHealthTagValues.get(7*i).getText());
			
			//second (last requested)
		//	b[j++]=CheckStringNotNull(AgentHealthTagNames.get(7*i+1).getText());
			b[j++]=CheckDate(AgentHealthTagValues.get(7*i+1).getText());
			
			//third (Request count)
		//	b[j++]=CheckStringNotNull(AgentHealthTagNames.get(7*i+2).getText());
			b[j++]=CheckNum(AgentHealthTagValues.get(7*i+2).getText());
		}
		
		for(k=0;k<n*3;k++) {
			if(!b[k]) {
				AgentHealthIsDisplayed=false;			
				break;
			}
		}
		return AgentHealthIsDisplayed;
	}



	public boolean GetRVCList() {
		// TODO Auto-generated method stub
		return false;
	}



	public boolean GetMenuItems() {
		// TODO Auto-generated method stub
		return false;
	}
}
