package ztp.qa.testcases;




import org.testng.Assert;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import ztp.qa.base.TestBase;
import ztp.qa.pages.*;


public class sampleTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	QRCodePage qrCodePage;
	ExtentReports report;
	ExtentTest test;

	public sampleTest() {
		super();
	}


	@BeforeClass
	public void starttest() {
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel(), "Unable to load homepage");
		supportToolsPage = homePage.RMSearch();

		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg());
		log.info("On Support Tools page");
		watcherPage = supportToolsPage.GotoWatcherClient();
		Assert.assertTrue(watcherPage.ValidateServicesTablePopulated(), "Services not found");
	}




	@Test(priority = 11, description ="Items per Page displayed: 5,10,25 and 100")
	public void ValidateItemsPerPageFunctionality() {

		log.info("ValidateItemsPerPageFunctionality Test started");
		Assert.assertTrue(watcherPage.ItemsPerPageFunctionality(),
				"Items per Page displayed incorrectly for 5,10,25 or 100");
		log.info("Items per Page displayed correctly for 5,10,25 and 100");
		log.info("ValidateItemsPerPageFunctionality Test completed");

	}


	
	@AfterClass
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("Logged out successfully");
		driver.quit();
	}
}