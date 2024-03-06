package ztp.qa.testcases;

import java.io.IOException;


import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ztp.qa.base.TestBase;
import ztp.qa.pages.*;
import ztp.qa.util.*;

public class ZtpQRCodePageTests extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	QRCodePage qrCodePage;
	ExtentReports report;
	ExtentTest test;

	public ZtpQRCodePageTests() {
		super();
	}

//	@BeforeTest
	@BeforeClass
	public void starttest() {
		//report = ExtentReportManager.getReportInstance();
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel());
		supportToolsPage = homePage.RMSearch();

		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg());
		log.info("On Support Tools page");
		qrCodePage = supportToolsPage.GotoQRCodePage();
		Assert.assertTrue(qrCodePage.VerifyQRPage());
		log.info("On QR Code page");
		qrCodePage.QRCodeCreation();
		TestUtil.sleep(2000);
		Assert.assertTrue(qrCodePage.verifyQRImage());
		log.info("QR Code image created");
	}



	@Test(priority = 21)
	public void ValidateDownloadQRImage() {

		test = report.createTest("ValidateDownloadQRImage");
		qrCodePage.DownloadQRImage();
		Assert.assertTrue(qrCodePage.DownloadQRImage());
		log.info("QR Code downloaded");
		log.info("ValidateDownloadQRImage Test completed");
	}

	@Test(priority = 22)
	public void ValidatePrintQRImage() {
		test = report.createTest("ValidatePrintQRImage");
		Assert.assertTrue(qrCodePage.PrintQRImage());
		log.info("QR Print page found");
		log.info("ValidatePrintQRImage Test completed");
	}

	@AfterMethod

	public void setTestResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
			test.log(Status.FAIL, result.getThrowable());
			String screenShot = TestUtil.captureScreen();
			test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
			log.error(result.getName() + " FAILED");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName());
			log.info(result.getName() + " PASSED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case : " + result.getName() + " has been skipped");
			log.info(result.getName() + " SKIPPED");
		}
		report.flush();

	}

	@AfterClass
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		driver.quit();
	}
}