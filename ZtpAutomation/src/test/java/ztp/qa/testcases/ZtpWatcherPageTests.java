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

public class ZtpWatcherPageTests extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	ExtentReports report;
	ExtentTest test;

	public ZtpWatcherPageTests() {
		super();
	}

	@BeforeSuite
	public void initializeExtentReports() {
		report = ExtentReportManager.getReportInstance();
	}
	
	@BeforeTest
	public void starttest() {
		//report = ExtentReportManager.getReportInstance();
		initialization();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.validateSPLabel());
		supportToolsPage = homePage.RMSearch();

		Assert.assertTrue(supportToolsPage.ValidateWatcherClientImg());
		log.info("On Support Tools page");
		watcherPage = supportToolsPage.GotoWatcherClient();
		Assert.assertTrue(watcherPage.ValidateServicesTablePopulated(), "Services not found");
	}

	@BeforeMethod
	public void setUp() {
		
	}
	
	@Test(priority = 1)
	public void ValidateStartToStopService() {

		test = report.createTest("ValidateStartToStopService");
		log.info("ValidateStartToStopService Test started");
		int i = watcherPage.FindFirstStoppedService();
		Assert.assertTrue(watcherPage.StartToStopServiceFuncationality(i));
		log.info("Start to Stop works for service");
		log.info("ValidateStartToStopService Test completed");
	}
	
	@Test(priority = 2)
	public void ValidateRestartService() {

		test = report.createTest("ValidateRestartService");
		log.info("ValidateRestartService Test started");
		int i = watcherPage.FindFirstRunningService();
		Assert.assertTrue(watcherPage.RestartServiceFuncationality(i));
		log.info("Restart functionality works for service");
		log.info("ValidateRestartService Test completed");
	}

	
	@Test(priority = 3)
	public void ValidateStopToStartService() {

		test = report.createTest("ValidateStopToStartService");
		log.info("ValidateStopToStartService Test started");
		int i = watcherPage.FindFirstRunningService();
		Assert.assertTrue(watcherPage.StopToStartServiceFuncationality(i));
		log.info("Stop to start works for service");
		log.info("ValidateStopToStartService Test completed");
	}
	
		
	@Test(priority = 4)
	public void ValidateSortByDisplayName() {
		test = report.createTest("ValidateSortByDisplayName");
		log.info("ValidateSortByDisplayName Test started");
		Assert.assertTrue(watcherPage.SortByDisplayName());
		log.info("Sort by Display Name works");
		log.info("ValidateSortByDisplayName Test completed");
	}
	
	@Test(priority = 5)
	public void ValidateTotalNoOfServices() {
		test = report.createTest("ValidateTotalNoOfServices");
		log.info("ValidateTotalNoOfServices Test started");
		Assert.assertTrue(watcherPage.ConfirmTotalNoOfServices());
		log.info("Total number of dislayed services matches count");
		log.info("ValidateTotalNoOfServices Test completed");
	}
	
	@Test(priority = 6)
	public void ValidateNextPage() {
		test = report.createTest("ValidateNextPage");
		log.info("ValidateNextPage Test started");
		Assert.assertTrue(watcherPage.ClickNextBtn());
		log.info("Next Page Functionality works");
		log.info("ValidateNextPage Test completed");
	}
	
	@Test(priority = 7)
	public void ValidatePreviousPage() {
		test = report.createTest("ValidatePreviousPage");
		log.info("ValidatePreviousPage Test started");
		Assert.assertTrue(watcherPage.ClickPreviousBtn());
		log.info("Previous Page Functionality works");
		log.info("ValidatePreviousPage Test completed");
	}
	
	@Test(priority = 8)
	public void ValidateRefreshServices() {
		test = report.createTest("ValidateRefreshServices");
		log.info("ValidateRefreshServices Test started");
		Assert.assertTrue(watcherPage.ClickRefreshLink("Services"));
		log.info("Refresh Services Functionality works");
		log.info("ValidateRefreshServices Test completed");
	}

	
	
	@Test(priority = 9)
	public void ValidateSearchZioskServices() {
		
	test=report.createTest("ValidateSearchZioskServices");
	log.info("ValidateSearchZioskServices Test started");
	TestUtil.sleep(5000);
	log.info("Services loaded on Watcher Client page");
	Assert.assertTrue(watcherPage.SearchZioskServices());
	log.info("4 Ziosk Service loaded on Watcher Client page");
	log.info("ValidateSearchZioskServices Test completed");
	
	}

	@Test(priority = 10)
	public void ValidateZOOBServiceStopDisabled() {
	
		test = report.createTest("ValidateZOOBServiceStopDisabled");
		log.info("ValidateZOOBServiceStopDisabled Test started");
		Assert.assertTrue(watcherPage.ZOOBServiceStopDisabled());
		log.info("Stop button is disabled for Ziosk Online Ordering Bridge Service");
		log.info("ValidateZOOBServiceStopDisabled Test completed");
		
	}

	@Test(priority = 11)
	public void ValidateItemsPerPageFunctionality() {
	
		test = report.createTest("ValidateItemsPerPageFunctionality");
		log.info("ValidateItemsPerPageFunctionality Test started");
		Assert.assertTrue(watcherPage.ItemsPerPageFunctionality());
		log.info("Items per Page displayed correctly for 5,10,25 and 100");
		log.info("ValidateItemsPerPageFunctionality Test completed");
		
	}
	
	@Test(priority = 12)
		public void ValidateSortByServiceName() {
			test = report.createTest("ValidateSortByServiceName");
			log.info("ValidateSortByServiceName Test started");
			Assert.assertTrue(watcherPage.SortByServiceName());
			log.info("Sort by Service Name works");
			log.info("ValidateSortByServiceName Test completed");
		}
		
		@Test(priority = 13)
		public void ValidateSortByStatus() {
			test = report.createTest("ValidateSortByStatus");
			log.info("ValidateSortByStatus Test started");
			Assert.assertTrue(watcherPage.SortByStatus());
			log.info("Sort by Status works");
			log.info("ValidateSortByStatus Test completed");
		}	
		
		@Test(priority = 14)
			public void ValidateSortByProcessName() {
				test = report.createTest("ValidateSortByProcessName");
				log.info("ValidateSortByProcessName Test started");
				Assert.assertTrue(watcherPage.SortByProcessName());
				log.info("Sort by Process name works on Process Page");
				log.info("ValidateSortByProcessName Test completed");
			}	
		
		@Test(priority = 15)
		public void ValidateRefreshProcesses() {
			test = report.createTest("ValidateRefreshProcesses");
			log.info("ValidateRefreshProcesses Test started");
			Assert.assertTrue(watcherPage.ClickRefreshLink("Processes"));
			log.info("Refresh Processes Functionality works");
			log.info("ValidateRefreshProcesses Test completed");
		}	
		
		@Test(priority = 16)
		public void ValidateSearchZioskProcesses() {
			
		test=report.createTest("ValidateSearchZioskProcesses");
		log.info("ValidateSearchZioskProcesses Test started");
		TestUtil.sleep(5000);
		log.info("Services loaded on Watcher Client page");
		Assert.assertTrue(watcherPage.SearchZioskProcesses());
		log.info("4 Ziosk Service loaded on Watcher Client page");
		log.info("ValidateSearchZioskProcesses Test completed");
	
		}
		
		@Test(priority = 17)
		public void ValidateSwitchingBetTabs() {
			test = report.createTest("ValidateSwitchingBetTabs");
			log.info("ValidateSwitchingBetTabs Test started");
			Assert.assertTrue(watcherPage.SwitchingBetTabs());
			log.info("Switching between Services, Processes and Files tabs works");
			log.info("ValidateSwitchingBetTabs Test completed");
		}	
		
		@Test(priority = 18)
		public void ValidateFileSize() {
			test = report.createTest("ValidateFileSize");
			log.info("ValidateFileSize Test started");
			Assert.assertTrue(watcherPage.TraverseFiles());
			log.info("Files are color coded correctly in Files tabs");
			log.info("ValidateFileSize Test completed");
		}	
		
		@Test(priority = 19)
		public void ValidateExpandAll() {
			test = report.createTest("ValidateExpandAll");
			log.info("ValidateExpandAll Test started");
			Assert.assertTrue(watcherPage.ExpandAllFiles());
			log.info("Expand All feature works for Files tab");
			log.info("ValidateExpandAll Test completed");
		}	
		
		@Test(priority = 20)
		public void ValidateDownloadFile() {
			test = report.createTest("ValidateDownloadFile");
			log.info("ValidateDownloadFile Test started");
			Assert.assertTrue(watcherPage.DownloadFile());
			log.info("Download feature works for Files tab");
			log.info("ValidateDownloadFile Test completed");
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

	@AfterTest
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage());
		driver.quit();
		
	}
	
	@AfterSuite
	public void generateExtentReports() {
		//report.flush();
		
	}

}
