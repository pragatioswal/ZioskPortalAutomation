package ztp.qa.testcases;

import org.testng.Assert;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import ztp.qa.base.TestBase;
import ztp.qa.pages.*;
import ztp.qa.util.*;

public class WatcherTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	SupportToolsPage supportToolsPage;
	WatcherPage watcherPage;
	ExtentReports report;
	ExtentTest test;

	public WatcherTest() {
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

	
	@Test(priority = 1, description ="Checks Start to Stop works for service")
	public void ValidateStartToStopService() {

		log.info("ValidateStartToStopService Test started");
		int i = watcherPage.FindFirstStoppedService();
		Assert.assertTrue(watcherPage.StartToStopServiceFuncationality(i),
				"Start to Stop does not work for service");
		log.info("Start to Stop works for service");
		log.info("ValidateStartToStopService Test completed");
	}

	@Test(priority = 2, description ="Checks Restart functionality works for service")
	public void ValidateRestartService() {

		log.info("ValidateRestartService Test started");
		int i = watcherPage.FindFirstRunningService();
		Assert.assertTrue(watcherPage.RestartServiceFuncationality(i),
				"Restart functionality does not work for service");
		log.info("Restart functionality works for service");
		log.info("ValidateRestartService Test completed");
	}

	@Test(priority = 3, description ="Checks Stop to start works for service")
	public void ValidateStopToStartService() {

		log.info("ValidateStopToStartService Test started");
		int i = watcherPage.FindFirstRunningService();
		Assert.assertTrue(watcherPage.StopToStartServiceFuncationality(i),
				"Stop to start does not work for service");
		log.info("Stop to start works for service");
		log.info("ValidateStopToStartService Test completed");
	}

	@Test(priority = 4, description ="Sort by Display Name on Service Tab")
	public void ValidateSortByDisplayName() {
		log.info("ValidateSortByDisplayName Test started");
		Assert.assertTrue(watcherPage.SortByDisplayName(),
				"Sort by Display Name does not work");
		log.info("Sort by Display Name works");
		log.info("ValidateSortByDisplayName Test completed");
	}

	@Test(priority = 5, description ="Checks Total number of dislayed services")
	public void ValidateTotalNoOfServices() {
		log.info("ValidateTotalNoOfServices Test started");
		Assert.assertTrue(watcherPage.ConfirmTotalNoOfServices(),
				"Total number of dislayed services does not matchcount");
		log.info("Total number of dislayed services matches count");
		log.info("ValidateTotalNoOfServices Test completed");
	}

	@Test(priority = 6, description ="Next Page Functionality")
	public void ValidateNextPage() {
		log.info("ValidateNextPage Test started");
		Assert.assertTrue(watcherPage.ClickNextBtn(),
				"Next Page Functionality does not work");
		log.info("Next Page Functionality works");
		log.info("ValidateNextPage Test completed");
	}

	@Test(priority = 7, description ="Previous Page Functionality")
	public void ValidatePreviousPage() {
		log.info("ValidatePreviousPage Test started");
		Assert.assertTrue(watcherPage.ClickPreviousBtn(),
				"Previous Page Functionality does not work");
		log.info("Previous Page Functionality works");
		log.info("ValidatePreviousPage Test completed");
	}

	@Test(priority = 8, description ="Refresh Services Functionality")
	public void ValidateRefreshServices() {
		log.info("ValidateRefreshServices Test started");
		Assert.assertTrue(watcherPage.ClickRefreshLink("Services"),
				"Refresh Services Functionality does not work");
		log.info("Refresh Services Functionality works");
		log.info("ValidateRefreshServices Test completed");
	}

	@Test(priority = 9, description ="4 Ziosk Service loaded on Watcher Client page")
	public void ValidateSearchZioskServices() {

		log.info("ValidateSearchZioskServices Test started");
		TestUtil.sleep(5000);
		log.info("Services loaded on Watcher Client page");
		Assert.assertTrue(watcherPage.SearchZioskServices(),
				"4 Ziosk Service not loaded on Watcher Client page");
		log.info("4 Ziosk Service loaded on Watcher Client page");
		log.info("ValidateSearchZioskServices Test completed");
	}

	@Test(priority = 10, description ="Stop and start button is disabled for Ziosk Online Ordering Bridge Service")
	public void ValidateZOOBServiceStopStartDisabled() {

		log.info("ValidateZOOBServiceStopStartDisabled Test started");
		Assert.assertTrue(watcherPage.ZOOBServiceStopDisabled(),
				"Stop and start button is disabled for Ziosk Online Ordering Bridge Service");
		log.info("Stop and start button is disabled for Ziosk Online Ordering Bridge Service");
		log.info("ValidateZOOBServiceStopStartDisabled Test completed");
	}

	@Test(priority = 11, description ="Items per Page displayed: 5,10,25 and 100")
	public void ValidateItemsPerPageFunctionality() {

		log.info("ValidateItemsPerPageFunctionality Test started");
		Assert.assertTrue(watcherPage.ItemsPerPageFunctionality(),
				"Items per Page displayed incorrectly for 5,10,25 or 100");
		log.info("Items per Page displayed correctly for 5,10,25 and 100");
		log.info("ValidateItemsPerPageFunctionality Test completed");

	}

	@Test(priority = 12, description ="Sort by Service Name on Services Tab")
	public void ValidateSortByServiceName() {
		
		log.info("ValidateSortByServiceName Test started");
		Assert.assertTrue(watcherPage.SortByServiceName(),
				"Sort by Service Name does not work");
		log.info("Sort by Service Name works");
		log.info("ValidateSortByServiceName Test completed");
	}

	@Test(priority = 13, description ="Sort by Status on services tab")
	public void ValidateSortByStatus() {
		
		log.info("ValidateSortByStatus Test started");
		Assert.assertTrue(watcherPage.SortByStatus(),"Sort by Status does not work");
		log.info("Sort by Status works");
		log.info("ValidateSortByStatus Test completed");
	}

	@Test(priority = 14, description ="Sort by Process name on Process tab")
	public void ValidateSortByProcessName() {
		
		log.info("ValidateSortByProcessName Test started");
		Assert.assertTrue(watcherPage.SortByProcessName(),
				"Sort by Process name does not work on Process Page");
		log.info("Sort by Process name works on Process Page");
		log.info("ValidateSortByProcessName Test completed");
	}

	@Test(priority = 15, description ="Refresh Processes Functionality")
	public void ValidateRefreshProcesses() {
	
		log.info("ValidateRefreshProcesses Test started");
		Assert.assertTrue(watcherPage.ClickRefreshLink("Processes"),
				"Refresh Processes Functionality doe not work");
		log.info("Refresh Processes Functionality works");
		log.info("ValidateRefreshProcesses Test completed");
	}

	@Test(priority = 16, description ="4 Ziosk Processes on Watcher Client page")
	public void ValidateSearchZioskProcesses() {

		log.info("ValidateSearchZioskProcesses Test started");
		TestUtil.sleep(5000);
		log.info("Services loaded on Watcher Client page");
		Assert.assertTrue(watcherPage.SearchZioskProcesses(),
				"4 Ziosk Processes not loaded on Watcher Client page");
		log.info("4 Ziosk Processes loaded on Watcher Client page");
		log.info("ValidateSearchZioskProcesses Test completed");
		//watcherPage.ResetSearch("Processes");
	}

	@Test(priority = 17, description ="Switching between Services, Processes and Files tabs")
	public void ValidateSwitchingBetTabs() {
		
		log.info("ValidateSwitchingBetTabs Test started");
		Assert.assertTrue(watcherPage.SwitchingBetTabs(),
				"Switching between Services, Processes and Files tabs does not work");
		log.info("Switching between Services, Processes and Files tabs works");
		log.info("ValidateSwitchingBetTabs Test completed");
	}

	@Test(priority = 18, description ="Color Coding of files a per size in Files tab")
	public void ValidateFileSize() {
		
		log.info("ValidateFileSize Test started");
		Assert.assertTrue(watcherPage.TraverseFiles(),
				"Files are not color coded correctly as per size");
		log.info("Files are color coded correctly in Files tabs");
		log.info("ValidateFileSize Test completed");
	}

	@Test(priority = 19, description ="Download feature for Files tab")
	public void ValidateDownloadFile() {
	
		log.info("ValidateDownloadFile Test started");
		Assert.assertTrue(watcherPage.DownloadFile(),
				"Download feature does not work for Files tab");
		log.info("Download feature works for Files tab");
		log.info("ValidateDownloadFile Test completed");
	}
	
	@Test(priority = 20, description ="Expand All feature for Files tab")
	public void ValidateExpandAll() {
		
		log.info("ValidateExpandAll Test started");
		Assert.assertTrue(watcherPage.ExpandAllFiles(),
				"Expand All feature does not work for Files tab");
		log.info("Expand All feature works for Files tab");
		log.info("ValidateExpandAll Test completed");
	}

	
	
	
	
	@Test(priority = 21, description ="Switch between pages using Tool dropdown")
	public void ValidateSwitchBetPageswithTool() {
	
		log.info("ValidateSwitchBetPageswithTool Test started");
		Assert.assertTrue(watcherPage.SwitchBetPageswithTool(),
				"Switch between pages using Tool dropdown does not work");
		log.info("Switch between pages using Tool dropdown works");
		log.info("ValidateSwitchBetPageswithTool Test completed");
	}
	
		
	@Test(priority = 22, description ="Switching between stores from top page dropdown")
	public void ValidateSwitchStore() {
	
		log.info("ValidateSwitchStore Test started");
		Assert.assertTrue(watcherPage.SwitchStore(),
				"Switching between stores does not work");
		log.info("Switching between stores from top page dropdown works");
		log.info("ValidateSwitchStore Test completed");
	}
	@Test(priority = 23, description ="Backward Navigate from Watcher client")
	public void ValidateBackwardNavigate() {
	
		log.info("ValidateBackwardNavigate Test started");
		Assert.assertTrue(watcherPage.BackwardNavigate(),
				"Backward Navigate from Watcher client does not work");
		log.info("Backward Navigate from Watcher client works");
		log.info("ValidateBackwardNavigate Test completed");
	}

	
	@AfterClass
	public void logout() {
		loginPage = homePage.Logout();
		Assert.assertTrue(loginPage.validateMainLogoImage(),
				"Not logged out successfully");
		log.info("Logged out successfully");
		driver.quit();

	}

}
