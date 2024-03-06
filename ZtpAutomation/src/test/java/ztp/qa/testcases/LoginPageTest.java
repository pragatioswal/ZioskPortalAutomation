package ztp.qa.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ztp.qa.base.TestBase;
import ztp.qa.pages.*;
import ztp.qa.util.*;

//@Listeners(TestListener.class)

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;

	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage ;
	ExtentReports report;
	ExtentTest test;
	
	public LoginPageTest() {
		super();
	}

	@BeforeTest
	public void starttest() {
		report = ExtentReportManager.getReportInstance();

	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();

	}


	
	//@Test(priority = 1)
	public void loginPageTitleTest() {
		test = report.createTest("loginPageTitleTest");
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Sign up or sign in");
		log.info("loginTitleTest completed");
	}

	//@Test(priority = 2)
	public void ztpLogoImageTest() {
		test = report.createTest("ztpLogoImageTest");
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("ztpLogoImageTest Test completed");
	}

	// @Test(priority=1)
	public void ValidateLogin() {
		test = report.createTest("ValidateLogin");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	//@Test(priority = 3)
	public void ValidateLoginLogout() {
		test = report.createTest("ValidateLoginLogout");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel());
		log.info("Logged in");
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("Logged out");
		log.info("ValidateLoginLogout Test completed");
	}


	@Test(priority = 4)
	public void ValidateRMSearch() {
		//String expectedUrl = "https://supportportalqa.azurewebsites.net/support-tools";
		
		test = report.createTest("ValidateRMSearch");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel());
		supportToolsPage = homePage.RMSearch();
	//	Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg());
		log.info("On Support Tools page");
	/*	loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("ValidateRMSearch Test completed");*/
	}

	//@Test(priority = 5)
	public void ValidateWatcherPage() {
		//String expectedUrl = "https://supportportalqa.azurewebsites.net/watcher/services";
		test = report.createTest("ValidateWatcherPage");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel());
		supportToolsPage = homePage.RMSearch();
	//	Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg());
		log.info("On Support Tools page");
		watcherPage = supportToolsPage.GotoWatcherClient();
		TestUtil.sleep(5000);
		//Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
		//Assert.assertTrue(watcherPage.ValidateServiceLink());
		Assert.assertTrue(watcherPage.ValidateWatcherServicesLoaded());
		
		TestUtil.sleep(5000);
		log.info("On Watcher Client page");
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		log.info("ValidateWatcherPage Test completed");
	}

	@AfterMethod

	public void setTestResult(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getName());
			test.log(Status.FAIL, result.getThrowable());
			String screenShot = TestUtil.captureScreen();
			test.fail("Screen Shot : " + test.addScreenCaptureFromPath(screenShot));
			log.error( result.getName() + " FAILED");
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName());
			log.info(result.getName() + " PASSED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case : " + result.getName() + " has been skipped");
			log.info(result.getName() + " SKIPPED");
		}
		report.flush();
		//driver.quit();
	}

}
