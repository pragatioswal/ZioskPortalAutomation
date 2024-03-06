package ztp.qa.testcases;




import org.testng.Assert;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import ztp.qa.base.TestBase;
import ztp.qa.pages.*;


public class POSTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	POSPage posPage;
	QRCodePage qrCodePage;
	ExtentReports report;
	ExtentTest test;

	public POSTest() {
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
		
		posPage = supportToolsPage.GotoPOSPage();
		Assert.assertTrue(posPage.VerifyPOSPage(),"Unable to load POS plugin page");
		log.info("On POS page");
		
	}



	@Test(priority = 1, description ="Checks POS information")
	public void ValidatePOSInformation() {

		log.info("ValidatePOSInformation Test started");
		Assert.assertTrue(posPage.POSSystemNameVersion(1),"POS Information not displayed");
		log.info("POS information  present");
		log.info("ValidatePOSInformation Test completed");
	}

	@Test(priority = 2, description ="Checks Agent Version")
	public void ValidateAgentVersion() {

		log.info("ValidateAgentVersion Test started");
		Assert.assertTrue(posPage.POSSystemNameVersion(2),"Agent Version not displayed");
		log.info("Agent Version  present");
		log.info("ValidateAgentVersion Test completed");
	}

	@Test(priority = 3, description ="Checks Plugin Version")
	public void ValidatePluginVersion() {

		log.info("ValidatePluginVersion Test started");
		Assert.assertTrue(posPage.POSSystemNameVersion(3),"Agent Version not displayed");
		log.info("Plugin Version  present");
		log.info("ValidatePluginVersion Test completed");
	}

	@Test(priority = 4, description ="Checks Plugin Features")
	public void ValidatePosFeatures() {

		log.info("ValidatePosFeatures Test started");
		Assert.assertTrue(posPage.PosFeatures(),"POS Features not displayed");
		log.info("Plugin Version  present");
		log.info("ValidatePosFeatures Test completed");
	}

	@Test(priority = 5, description ="Checks Agent Health parameters")
	public void ValidateAgentHealth() {

		log.info("ValidateAgentHealth Test started");
		Assert.assertTrue(posPage.AgentHealth(),"Agent Health parameters not displayed");
		log.info("Agent Health paramters displayed");
		log.info("ValidateAgentHealth Test completed");
	}
	
	@Test(priority = 6, description ="Checks GetRVCList parameters")
	public void ValidateGetRVCList() {

		log.info("ValidateGetRVCList Test started");
		Assert.assertTrue(posPage.POSSystemNameVersion(8),"GetRVCList parameters not displayed");
		log.info("GetRVCList parameters displayed");
		log.info("ValidateGetRVCList Test completed");
	}
	
	@Test(priority = 7, description ="Checks GetMenuItems paramters")
	public void ValidateGetMenuItems() {

		log.info("ValidateGetMenuItems Test started");
		Assert.assertTrue(posPage.POSSystemNameVersion(10),"GetMenuItems parameters not displayed");
		log.info("GetMenuItems parameters displayed");
		log.info("ValidateGetMenuItems Test completed");
	}

	

	
	@AfterClass
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("Logged out successfully");
		driver.quit();
	}
}