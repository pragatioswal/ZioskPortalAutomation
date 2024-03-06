package ztp.qa.testcases;




import org.testng.Assert;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import ztp.qa.base.TestBase;
import ztp.qa.pages.*;
import ztp.qa.util.*;

public class QRCodeTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	QRCodePage qrCodePage;
	ExtentReports report;
	ExtentTest test;

	public QRCodeTest() {
		super();
	}


	@BeforeClass
	public void starttest() {
		
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel(), "Unable to load home page");
		supportToolsPage = homePage.RMSearch();

		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg(),
				"Unable to load support tools page");
		log.info("On Support Tools page");
		
		qrCodePage = supportToolsPage.GotoQRCodePage();
		Assert.assertTrue(qrCodePage.VerifyQRPage(),"Unable to load QR code page");
		log.info("On QR Code page");
		qrCodePage.QRCodeCreation();
		TestUtil.sleep(2000);
		Assert.assertTrue(qrCodePage.verifyQRImage(),"Unable to create QR code");
		log.info("QR Code image created");
	}



	@Test(priority = 1, description ="QR Code Download")
	public void ValidateDownloadQRImage() {

		log.info("ValidateDownloadQRImage Test started");
		qrCodePage.DownloadQRImage();
		Assert.assertTrue(qrCodePage.DownloadQRImage(),
				"QR Code downloaded not working");
		log.info("QR Code downloaded");
		log.info("ValidateDownloadQRImage Test completed");
	}

	@Test(priority = 2, description ="QR Code Print")
	public void ValidatePrintQRImage() {
	
		log.info("ValidatePrintQRImage Test started");
		Assert.assertTrue(qrCodePage.PrintQRImage(),
				"QR Print page not found");
		log.info("QR Print page found");
		log.info("ValidatePrintQRImage Test completed");
	}

	
	@AfterClass
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage(),
				"Unable to log out successfully");
		log.info("Logged out successfully");
		driver.quit();
	}
}