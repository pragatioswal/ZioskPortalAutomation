//This is QR Code page for support portal
//@author : Praggati Oswal
package ztp.qa.pages;

import java.io.File;

import java.util.Iterator;

import java.util.Set;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ztp.qa.base.TestBase;
import ztp.qa.util.TestUtil;

public class QRCodePage extends TestBase {

	// Page Factory - OR:

	@FindBy(xpath = "//b[normalize-space()='Generate QR Code']")
	WebElement GenerateQRLabel;

	@FindBy(xpath = "//input[@formcontrolname='ssid']")
	WebElement SSID;

	@FindBy(xpath = "//input[@formcontrolname='psk']")
	WebElement Pwd;

	@FindBy(xpath = "//button[@class='mat-focus-indicator qr-button mat-button mat-button-base']")
	WebElement Submitbtn;

	@FindBy(xpath = "//mat-card-title[@class='mat-card-title arrow_box pl-4 pr-4 pt-2 pb-2 ng-star-inserted']")
	WebElement ScanLabel;

	@FindBy(css = "img[src^='data:image/png']")
	WebElement QRCodeImage;

	@FindBy(xpath = "//span[@class='mat-button-wrapper' and text()=' Download ']")
	WebElement Downloadbtn;

	@FindBy(xpath = "//span[@class='mat-button-wrapper' and text()='Print QR']")
	WebElement PrintQRbtn;

	@FindBy(xpath = "//mat-select//span[contains(@class,'mat-select-min-line')]")
	WebElement CurrentTool;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'RM Portal')]")
	WebElement Tool_RMPortal;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'POS Tester')]")
	WebElement Tool_POSTester;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'QR Code')]")
	WebElement Tool_QRCode;
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'Watcher Client')]")
	WebElement Tool_WatcherClient;

	// Initializing the Page Objects:
	public QRCodePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean VerifyQRPage() {
		return GenerateQRLabel.isDisplayed();
	}

	public void QRCodeCreation() {
		SSID.click();
		SSID.sendKeys("aaa");
		Pwd.click();
		Pwd.sendKeys("aaa");
		Submitbtn.click();
	}

	public boolean verifyQRImage() {
		
		waitForVisibilityOf(QRCodeImage, 10);
		return QRCodeImage.isDisplayed();
	}



	public boolean DownloadQRImage() {
		String filePath = System.getProperty("user.dir")+"\\qr-code.png";
		File file = new File(filePath);
		boolean fileExists = file.exists();
		if(fileExists) {
			file.delete();
		}
		Downloadbtn.click();
		TestUtil.sleep(5000);
		return file.exists();
	}

	public boolean PrintQRImage() {
		
		boolean b=false;
		PrintQRbtn.click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentid = (String) it.next();
		String childid= (String) it.next();
		driver.switchTo().window(childid);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement PrintBtn =  (WebElement) jse.executeScript("return document.querySelector('print-preview-app').shadowRoot.querySelector('print-preview-sidebar#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('div > cr-button.action-button')");
		WebElement CancelBtn =  (WebElement) jse.executeScript("return document.querySelector('print-preview-app').shadowRoot.querySelector('print-preview-sidebar#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('div > cr-button.cancel-button')");
		b = PrintBtn.isDisplayed();
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", CancelBtn);
		 driver.switchTo().window(parentid);
		 return b;
	}
	


	public boolean verifyPrint() {
		boolean b=false;
		PrintQRbtn.click();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentid = (String) it.next();
		String childid= (String) it.next();
		System.out.println("Parent handle from test script:" + driver.getWindowHandle());
		System.out.println("Child handle from test script:" + driver.getWindowHandle());
		driver.switchTo().window(childid);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement PrintBtn =  (WebElement) jse.executeScript("return document.querySelector('print-preview-app').shadowRoot.querySelector('print-preview-sidebar#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('div > cr-button.action-button')");
		WebElement CancelBtn =  (WebElement) jse.executeScript("return document.querySelector('print-preview-app').shadowRoot.querySelector('print-preview-sidebar#sidebar').shadowRoot.querySelector('print-preview-button-strip').shadowRoot.querySelector('div > cr-button.cancel-button')");
		b = PrintBtn.isDisplayed();
		 ((JavascriptExecutor)driver).executeScript("arguments[0].click();", CancelBtn);
		 driver.switchTo().window(parentid);
		 return b;
		
	}

	public WatcherPage QRtoWatcher() {
		waitForElementToDisplay(CurrentTool, 10);
		waitForVisibilityOf(CurrentTool, 10);
		CurrentTool.click();
		waitForVisibilityOf(Tool_WatcherClient, 10);
		Tool_WatcherClient.click();
		return new WatcherPage();
	}
	


	
}
