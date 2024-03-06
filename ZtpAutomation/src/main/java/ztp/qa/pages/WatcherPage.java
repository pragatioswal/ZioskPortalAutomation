//This is watcher clients page
//@author : Praggati Oswal
package ztp.qa.pages;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ztp.qa.base.TestBase;
import ztp.qa.util.TestUtil;

public class WatcherPage extends TestBase {

	// Page Factory - OR:

	//@FindBy(xpath = "//span[@class='mat-option-text'][1]")
	//WebElement WatcherClientToolDisplay;

	@FindBy(xpath = "//a[contains(text(),' Services ')]")
	WebElement ServicesLink;

	@FindBy(xpath = "//a[contains(text(),' Processes ')]")
	WebElement ProcessesLink;

	@FindBy(xpath = "//a[contains(text(),' Files ')]")
	WebElement FilesLink;

	@FindBy(xpath = "//span[@class='inner-label']")
	WebElement RefreshServicesLink;

	@FindBy(xpath = "//mat-icon[normalize-space()='search']")
	WebElement SearchIcon;

	@FindBy(xpath = "//input[@data-placeholder='Process Name']")
	WebElement SearchProcessInputBox;

	@FindBy(xpath = "//input[@data-placeholder='Service Name']")
	WebElement SearchInputBox;

	@FindBy(xpath = "//div[@class='mat-dialog-content styles-custom-scroll dialog-wrapper-content']//p")
	WebElement AlertMessage;
	// alert message
	
	@FindBy(xpath = "//mat-card")
	WebElement PageReload;
	
	@FindBy(xpath = "//mat-icon[normalize-space()='arrow_back']")
	WebElement BackArrowLink;

	@FindBy(xpath = "//button[@class='mat-focus-indicator font-weight-bold mat-stroked-button mat-button-base']")
	WebElement ConfirmBtn;

	@FindBy(xpath = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[2]/td[1]")
	WebElement FirstService;


	@FindBy(xpath = "//span[@class='mat-select-min-line ng-tns-c154-14 ng-star-inserted']")
	WebElement ItemsPerPage;

	@FindAll({ @FindBy(xpath = "//mat-select") })
	List<WebElement> DropDowns;

	@FindBy(xpath = "//div[@class='mat-paginator-range-label']")
	WebElement TotalNumOfServices;

	@FindBy(xpath = "//mat-select[@aria-controls='mat-select-0-panel']")
	WebElement DropDownforModType;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'5')]")
	WebElement NoOfService5;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'10')]")
	WebElement NoOfService10;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'25')]")
	WebElement NoOfService25;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'100')]")
	WebElement NoOfService100;

	// mat-select[@aria-label='Items per page:']

	@FindBy(xpath = "//mat-select[@aria-label='Items per page:']")
	WebElement DropDownNoOfServices;

	@FindBy(xpath = "//div[(text()=' Display Name ')]")
	WebElement DisplayNameLink;

	@FindBy(xpath = "//div[@class='ngx-spinner-overlay ng-tns-c91-9 ng-trigger ng-trigger-fadeIn ng-star-inserted']")
	WebElement Spinner;

	@FindBy(xpath = "//div[(text()=' Service Name ')]")
	WebElement ServiceNameLink;

	@FindBy(xpath = "//div[(text()=' Status ')]")
	WebElement StatusLink;

	@FindBy(xpath = "//div[(text()=' Process Name ')]")
	WebElement ProcessNameLink;

	@FindBy(xpath = "//div[@class='mat-sort-header-stem ng-tns-c239-30']")
	WebElement DisplayNameSortArrow;

	@FindBy(xpath = "//button[@aria-label='Last page']")
	WebElement LastPageBtn;

	@FindBy(xpath = "//button[@aria-label='First page']")
	WebElement FirstPageBtn;

	@FindBy(xpath = "//button[@aria-label='Next page']")
	WebElement NextPageBtn;

	@FindBy(xpath = "//button[@aria-label='Previous page']")
	WebElement PreviousPageBtn;

	@FindBy(xpath = "//mat-select[@aria-label='Items per page:']//span[contains(@class,'mat-select-min-line')]")
	WebElement CurrentItemsPerPage;

	@FindAll({ @FindBy(xpath = "//span[@class='d-flex' and @style='color: rgb(124, 233, 189);']") })
	List<WebElement> CurrentOpenGreenFolders;

	@FindAll({ @FindBy(xpath = "//span[@class='inner-label']") })
	List<WebElement> TopLinksonFilesTab;

	@FindAll({ @FindBy(xpath = "//span[contains(text(),'free')]") })
	List<WebElement> FileDrives;

	@FindBy(xpath = "//mat-select//span[contains(@class,'mat-select-min-line')]")
	WebElement CurrentTool;

	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'RM Portal')]")
	WebElement Tool_RMPortal;
	
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'POS Tester')]")
	WebElement Tool_POSTester;
	
	@FindBy(xpath = "//span[contains(@class,'mat-option-text') and contains(text(),'QR Code')]")
	WebElement Tool_QRCode;
	
	@FindBy(xpath = "//mat-icon[normalize-space()='arrow_drop_down']")
	WebElement MainPageDropdown;
	
	@FindBy(xpath = "//span[@class='mat-button-wrapper' and text()=' Apply ']")
	WebElement ApplyBtn;
	
	@FindBy(xpath = "//div[@class='text-center warning-text']")
	WebElement WarningText;
	
	
	@FindAll({ @FindBy(xpath = "//input") })
	List<WebElement> AllTextBoxes;
	
	@FindAll({ @FindBy(xpath = "//mat-icon[text()='close']") })
	List<WebElement> Crosses;
	
	public static String ServicePerPageXpath = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table//tr//td[1]";
	SupportToolsPage supportToolsPage;

	// Initializing the Page Objects:
	public WatcherPage() {
		PageFactory.initElements(driver, this);
	}

	// Verify that 4 ziosk services are present under services tab on watcher client
	// page
	public boolean SearchZioskServices() {

		boolean fourZServicepresent = true;
		String[] ZioskServiceArray = { "ziosk online ordering bridge", "ziosk pos agent service", "ziosk virtual dms",
				"ziosk watcher client" };
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForVisibilityOf(SearchIcon, 10);
		SearchIcon.click();
		SearchInputBox.click();
		SearchInputBox.sendKeys("ziosk");
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		List<String> ZioskServices = GetListOfAllCurrentRowsForCol(1);
		int i = 0;
		
		for (String ServiceName : ZioskServices) {
			System.out.println(ServiceName);
			if (!ZioskServiceArray[i].equals(ServiceName)) {

				fourZServicepresent = false;
				break;
			}
			i++;
		}
		waitForVisibilityOf(TotalNumOfServices, 10);
		return fourZServicepresent;
	}

	// Verify that search icon populates on watcher client page
	public boolean ValidateServicesTablePopulated() {

		waitForelementToBeClickable(FirstService, 10);
		return FirstService.isDisplayed();

	}

	// This method verifies that Ziosk Online Ordering Bridge is always on and
	// cannot be stopped
	public boolean ZOOBServiceStopDisabled() {

		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		SearchIcon.click();
		SearchInputBox.clear();
		SearchInputBox.sendKeys(" ");
		SearchInputBox.click();
		SearchInputBox.sendKeys("ziosk");
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DisplayNameLink, 10);
		String ZOOBServiceName = "Ziosk Online Ordering Bridge";
		boolean serviceIsRunning = true;
		String FirstHalfXpath = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		String LastHalfXpath = "]/td[5]//button[3]";
		String LastHalfStartXpath = "]/td[5]//button[1]";
		List<WebElement> ZioskServices = driver.findElements(By.xpath(ServicePerPageXpath));

		int p = 1;
		int i = 1;
		for (WebElement ServiceName : ZioskServices) {
			if (ZOOBServiceName.equals(ServiceName.getText()))
				p = i;
			else
				i++;
		}
		String FinalXpathStop = FirstHalfXpath + p + LastHalfXpath;
		String FinalXpathStart = FirstHalfXpath + p + LastHalfStartXpath;
		// Stop and start button on Ziosk Online Ordering Bridge is enabled, then fail
		// the test
		// else pass the test
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		if (driver.findElement(By.xpath(FinalXpathStop)).isEnabled()
				|| driver.findElement(By.xpath(FinalXpathStart)).isEnabled())
			serviceIsRunning = false;
		SearchIcon.click();
		SearchInputBox.clear();
		SearchInputBox.sendKeys(" ");
		return serviceIsRunning;

	}

	// This method finds the first service that is stopped in the list of services
	public int FindFirstStoppedService() {
		int i = 1;
		String firsthalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		int n = 10;
		while (i <= n) {
			String FirstServiceStartBtn = firsthalf + i + "]/td[5]//button[3]";
			if (!driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled())
				break;
			i++;
		}
		return i;
	}

	// This method verifies the start to stop functionality
	public boolean StartToStopServiceFuncationality(int i) {

		boolean stopstart = false;
		boolean correctMsg = false;
		String firsthalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		String FirstServiceStopBtn = firsthalf + i + "]/td[5]//button[3]";
		String FirstServiceRestartBtn = firsthalf + i + "]/td[5]//button[2]";
		String FirstServiceStartBtn = firsthalf + i + "]/td[5]//button[1]";
		if (!driver.findElement(By.xpath(FirstServiceStopBtn)).isEnabled()) {
			driver.findElement(By.xpath(FirstServiceStartBtn)).click();
			if (ConfirmBtn.isDisplayed()) {
				correctMsg = AlertMessage.getText().contains("Are you sure you want to Start the service");
				ConfirmBtn.click();
			}
		}

		TestUtil.sleep(5000);
	//	IsSpinningComplete();

		if (driver.findElement(By.xpath(FirstServiceStopBtn)).isEnabled()
				&& driver.findElement(By.xpath(FirstServiceRestartBtn)).isEnabled() && correctMsg) {
			stopstart = true;
		}
		return stopstart;

	}

	// This method finds the first service that is running in the list of services
	public int FindFirstRunningService() {

		int i = 1;
		String firsthalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		int n = 10;
		while (i <= n) {
			String FirstServiceStartBtn = firsthalf + i + "]/td[5]//button[1]";
			if (!driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled())
				break;
			i++;
		}
		return i;

	}

	// This method verifies the stop to start functionality
	public boolean StopToStartServiceFuncationality(int i) {

		boolean stopstart = false;
		boolean correctMsg = false;
		String firsthalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		String FirstServiceStopBtn = firsthalf + i + "]/td[5]//button[3]";
		String FirstServiceStartBtn = firsthalf + i + "]/td[5]//button[1]";
		if (!driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled()) {
			driver.findElement(By.xpath(FirstServiceStopBtn)).click();
			if (ConfirmBtn.isDisplayed()) {
				correctMsg = AlertMessage.getText().contains("Are you sure you want to Stop the service");
				ConfirmBtn.click();
			}
		}
		TestUtil.sleep(5000);
	//	IsSpinningComplete();
		if (driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled() && correctMsg) {
			stopstart = true;
		}
		return stopstart;

	}

	// This method verifies that the sort by display name functionality works for
	// the table of services
	public boolean SortByDisplayName() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		List<String> BeforeSortList = GetListOfAllCurrentRowsForCol(1);
		List<String> AfterSortList = BeforeSortList.stream().sorted().collect(Collectors.toList());
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		TestUtil.sleep(4000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DisplayNameLink, 10);
		DisplayNameLink.click();
		
		List<String> AfterSortListFromWebsite = GetListOfAllCurrentRowsForCol(1);
		
		if (AfterSortList.equals(AfterSortListFromWebsite))
			return true;
		else
			return false;

	}

	// This method confirms the total number of services is correct
	public boolean ConfirmTotalNoOfServices() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		if (FirstPageBtn.isEnabled())
			FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);

		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		List<String> TotalList = GetListOfAllCurrentRowsForCol(1);
		String t = TotalNumOfServices.getText();
		// String s = " 1 – 100 of 224 ";
		int n = Integer.parseInt(t.split("of")[1].trim());
		if (n == TotalList.size())
			return true;
		else
			return false;

	}

	// This method verifies that the next button exists on the page depending on
	// total number of services and items per page
	public boolean ClickNextBtn() {

		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		
		if (FirstPageBtn.isEnabled()) {
			FirstPageBtn.click();
			waitForElementContentToDisplay(FirstService, 10);
			waitForElementToDisplay(PageReload, 10);
		}
		int ip = Integer.parseInt(CurrentItemsPerPage.getText());
		int tp = Integer.parseInt(TotalNumOfServices.getText().split("of")[1].trim());
		int q = tp / ip;
		int r = tp % ip;
		int NoOfNext;
		if (q < 1) {
			NoOfNext = 0;
		} else {
			if (r == 0) {
				NoOfNext = q - 1;
			} else {
				NoOfNext = q;
			}
		}
		if (NoOfNext == 0) {
			if ((!NextPageBtn.isEnabled()) && (!LastPageBtn.isEnabled())) {
				return true;
			}
		} else {
			for (int i = 0; i < NoOfNext; i++) {
				NextPageBtn.click();
				TestUtil.sleep(3000);
			}
			return true;
		}
		return false;

	}

	// This method verifies that the previous button exists on the page depending on
	// total number of services and items per page
	public boolean ClickPreviousBtn() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		if (LastPageBtn.isEnabled()) {
			LastPageBtn.click();
			waitForElementContentToDisplay(FirstService, 10);
			waitForElementToDisplay(PageReload, 10);
		}
		int ip = Integer.parseInt(CurrentItemsPerPage.getText());
		int tp = Integer.parseInt(TotalNumOfServices.getText().split("of")[1].trim());
		int q = tp / ip;
		int r = tp % ip;
		int NoOfPrevious;
		if (q < 1) {
			NoOfPrevious = 0;
		} else {
			if (r == 0) {
				NoOfPrevious = q - 1;
			} else {
				NoOfPrevious = q;
			}
		}
		if (NoOfPrevious == 0) {
			if ((!PreviousPageBtn.isEnabled()) && (!FirstPageBtn.isEnabled())) {
				return true;
			}
		} else {
			for (int i = 0; i < NoOfPrevious; i++) {
				PreviousPageBtn.click();
				TestUtil.sleep(3000);
			}
			return true;
		}
		return false;

	}

	// This method verifies that refresh functionality works
	public boolean ClickRefreshLink(String SorP) {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		if (SorP.equals("Services")) {
			if (!ServicesLink.getAttribute("class").contains("design-active")) {
				ServicesLink.click();
			}
		}
		if (SorP.equals("Processes")) {
			if (!ProcessesLink.getAttribute("class").contains("design-active")) {
				ProcessesLink.click();
			}
		}
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		waitForElementToDisplay(FirstPageBtn,10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		List<String> BeforeRefreshList = GetListOfAllCurrentRowsForCol(1);
		waitForElementToDisplay(RefreshServicesLink,10);
		RefreshServicesLink.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		List<String> AfterRefreshList = GetListOfAllCurrentRowsForCol(1);
		if (BeforeRefreshList.equals(AfterRefreshList))
			return true;
		else
			return false;
	}

	// This method verifies that services are loaded on watcher page
	public boolean ValidateWatcherServicesLoaded() {

		waitForVisibilityOf(FirstService, 10);
		if (FirstService.isDisplayed())
			return true;
		else
			return false;

	}

	public boolean ItemsPerPageFunctionality() {
		boolean b5 = false;
		boolean b10 = false;
		boolean b25 = false;
		boolean b100 = false;
		TestUtil.sleep(4000);
		// verify 5 services are displayed when 5 is set for items per page
		List<WebElement> elementsList;
		waitForElementContentToDisplay(FirstService, 10);
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForelementToBeClickable(NoOfService5, 10);
		NoOfService5.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		elementsList = driver.findElements(By.xpath(ServicePerPageXpath));
		if (elementsList.size() == 5)
			b5 = true;
		else
			b5 = false;
		TestUtil.sleep(2000);
		// verify 10 services are displayed when 10 is set for items per page
		waitForElementToDisplay(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForelementToBeClickable(NoOfService10, 10);
		NoOfService10.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		elementsList = driver.findElements(By.xpath(ServicePerPageXpath));
		if (elementsList.size() == 10)
			b10 = true;
		else
			b10 = false;

		// verify 25 services are displayed when 25 is set for items per page
		TestUtil.sleep(2000);
		waitForElementToDisplay(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForelementToBeClickable(NoOfService5, 10);
		NoOfService25.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		elementsList = driver.findElements(By.xpath(ServicePerPageXpath));
		
		if (elementsList.size() == 25)
			b25 = true;
		else
			b25 = false;
		TestUtil.sleep(2000);
		// verify 100 services are displayed when 10 is set for items per page
		waitForElementToDisplay(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForelementToBeClickable(NoOfService100, 10);
		NoOfService100.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		elementsList = driver.findElements(By.xpath(ServicePerPageXpath));
		if (elementsList.size() == 100)
			b100 = true;
		else
			b100 = false;
		if (b5 && b10 && b25 && b100)
			return true;
		else
			return false;
	}

	// This method verifies that the sort by service name functionality works for
	// the table of services

	public boolean SortByServiceName() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		TestUtil.sleep(5000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		List<String> BeforeSortList = GetListOfAllCurrentRowsForCol(3);
		List<String> AfterSortList = BeforeSortList.stream().sorted().collect(Collectors.toList());
		
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		TestUtil.sleep(4000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(ServiceNameLink, 10);
		ServiceNameLink.click();
		List<String> AfterSortListFromWebsite = GetListOfAllCurrentRowsForCol(3);
		if (AfterSortList.equals(AfterSortListFromWebsite))
			return true;
		else
			return false;
	}

	// This method gets a list of rows on the current page based on argument passes
	public List<String> GetListOfAllCurrentRowsForCol(int ColumnNumber) {
		String firstHalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table//tr//td[";
		String lastHalf = "]";
		String ServiceNamesPerPageXpath = firstHalf + ColumnNumber + lastHalf;
		List<String> tempList = new ArrayList<String>();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		List<WebElement> elementsList = driver.findElements(By.xpath(ServiceNamesPerPageXpath));
		List<String> originalList = elementsList.stream().map(s -> s.getText().toLowerCase())
				.collect(Collectors.toList());
		boolean b = true;
		if (!LastPageBtn.isEnabled())
			b = false;
		while (b) {
			waitForElementToDisplay(NextPageBtn, 10);
			waitForelementToBeClickable(NextPageBtn, 10);
			TestUtil.sleep(6000);
			NextPageBtn.click();
			waitForElementContentToDisplay(FirstService, 10);
			waitForElementToDisplay(PageReload, 10);
			elementsList = driver.findElements(By.xpath(ServiceNamesPerPageXpath));
			tempList = elementsList.stream().map(s -> s.getText().toLowerCase()).collect(Collectors.toList());
			originalList.addAll(originalList.size(), tempList);
			if (!LastPageBtn.isEnabled())
				b = false;
		}
		return originalList;

	}

	// This method verifies that the sort by status functionality works for the
	// table of services

	public boolean SortByStatus() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		TestUtil.sleep(4000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		List<String> BeforeSortList = GetListOfAllCurrentRowsForCol(2);
		List<String> AfterSortList = BeforeSortList.stream().sorted().collect(Collectors.toList());
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		TestUtil.sleep(4000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(StatusLink, 10);
		StatusLink.click();
		List<String> AfterSortListFromWebsite = GetListOfAllCurrentRowsForCol(2);
		if (AfterSortList.equals(AfterSortListFromWebsite))
			return true;
		else
			return false;
	}

	public boolean SortByProcessName() {
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(ProcessesLink, 10);
		ProcessesLink.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(DropDownNoOfServices, 10);
		DropDownNoOfServices.click();
		waitForVisibilityOf(NoOfService100, 10);
		NoOfService100.click();
		List<String> BeforeSortList = GetListOfAllCurrentRowsForCol(1);
		List<String> AfterSortList = BeforeSortList.stream().sorted().collect(Collectors.toList());
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForElementToDisplay(FirstPageBtn, 10);
		TestUtil.sleep(5000);
		waitForElementToDisplay(FirstPageBtn, 10);
		waitForelementToBeClickable(FirstPageBtn, 10);
		FirstPageBtn.click();
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForelementToBeClickable(ProcessNameLink, 10);
		ProcessNameLink.click();
		List<String> AfterSortListFromWebsite = GetListOfAllCurrentRowsForCol(1);
		if (AfterSortList.equals(AfterSortListFromWebsite))
			return true;
		else
			return false;

	}

	public boolean SwitchingBetTabs() {
		boolean b1 = false;
		boolean b2 = false;
		boolean b3 = false;

		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		// services to processes
		if (ServicesLink.getAttribute("class").contains("ng-star-inserted")) {
			ProcessesLink.click();
			b1 = true;
			waitForElementContentToDisplay(FirstService, 10);
			waitForElementToDisplay(PageReload, 10);

		}
		if (ProcessesLink.getAttribute("class").contains("ng-star-inserted")) {
			FilesLink.click();
			b2 = true;
			waitForElementToDisplay(FileDrives.get(0), 10);
		}
		if (FilesLink.getAttribute("class").contains("ng-star-inserted")) {
			ServicesLink.click();
			b3 = true;
			waitForElementContentToDisplay(FirstService, 10);
			waitForElementToDisplay(PageReload, 10);
		}
		if (b1 && b2 && b3)
			return true;
		else
			return false;
	}

	// this method verifies that the size of the file and color matches
	public boolean ConfirmColorForFileSize(float size, String FileColor) {
		boolean b = false;
		// white color
		if (size <= 5) {
			if (FileColor.isEmpty())
				b = true;
		}
		// yellow color
		else if (size > 5 && size <= 20) {
			if (FileColor.contains("yellow"))
				b = true;

		}
		// orange color
		else if (size > 20 && size <= 100) {
			if (FileColor.contains("orange"))
				b = true;
		}
		// red color
		else if (size > 100) {
			if (FileColor.contains("rgb(255, 117, 130)"))
				b = true;
		}
		return b;
	}

//qa	
	public void FileStructureSetup() {
		waitForVisibilityOf(FilesLink, 10);
		waitForElementToDisplay(FilesLink, 10);
		if (!FilesLink.getAttribute("class").contains("design-active"))
			FilesLink.click();
		TestUtil.sleep(3000);
		String IdivFileXpath = "//span[@class='pl-1 pointer']";
		List<WebElement> elementsList = driver.findElements(By.xpath(IdivFileXpath));

		elementsList.get(0).click();
		TestUtil.sleep(3000);
		elementsList = driver.findElements(By.xpath(IdivFileXpath));
		TestUtil.sleep(3000);
		for(int i=0;i<elementsList.size();i++) {
			if(elementsList.get(i).getText().contains("ColorValidation"))
			{
				elementsList.get(i).click();
				TestUtil.sleep(5000);
				break;
			}
		}

	}

	public void FileStructureOpen() {
		
		waitForVisibilityOf(FilesLink, 10);
		waitForElementToDisplay(FilesLink, 10);
		if (!FilesLink.getAttribute("class").contains("design-active"))
			FilesLink.click();
		TopLinksonFilesTab.get(0).click();
		TestUtil.sleep(3000);
		String IdivFileXpath = "//span[@class='pl-1 pointer']";
		List<WebElement> elementsList = driver.findElements(By.xpath(IdivFileXpath));

		elementsList.get(0).click();
		TestUtil.sleep(5000);

		elementsList = driver.findElements(By.xpath(IdivFileXpath));
		elementsList.get(28).click();
		TestUtil.sleep(5000);

		elementsList = driver.findElements(By.xpath(IdivFileXpath));
		elementsList.get(53).click();
		TestUtil.sleep(5000);

		elementsList = driver.findElements(By.xpath(IdivFileXpath));
		elementsList.get(56).click();
		TestUtil.sleep(5000);
	}

	public boolean TraverseFiles() {

		FileStructureSetup();
		String IdivFileXpath1 = "//span[@class='pl-1']/parent::span";
		List<WebElement> elementsList1 = driver.findElements(By.xpath(IdivFileXpath1));

		String[] FullFileNames = new String[4];
		String[] FileNames = new String[4];
		String[] ActualFileColors = new String[4];
		float[] FileSizes = new float[4];
		boolean b = false;
		for (int i = 0; i < 4; i++) {
			FullFileNames[i] = elementsList1.get(i).getText();
			ActualFileColors[i] = elementsList1.get(i).getAttribute("style");
			FileSizes[i] = Float.parseFloat(FullFileNames[i].split("-")[2].trim().split(" ")[0].trim());
			FileNames[i] = FullFileNames[i].split("-")[1].trim();
			if (ConfirmColorForFileSize(FileSizes[i], ActualFileColors[i]))
				b = true;
			else
				b = false;
		}

		return b;
		
	}

	public boolean SearchZioskProcesses() {
		boolean fourZServicepresent = true;
		if (!ProcessesLink.getAttribute("class").contains("design-active")) {
			waitForVisibilityOf(ProcessesLink, 10);
			ProcessesLink.click();
		}
		String[] ZioskServiceArray = { "zioskiotbridge", "zioskposagentservice", "zioskvirtualdmsapi",
				"zioskwatcherclient" };
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		waitForVisibilityOf(SearchIcon, 10);
		SearchIcon.click();
		SearchProcessInputBox.click();
		SearchProcessInputBox.sendKeys("ziosk");
		waitForElementContentToDisplay(FirstService, 10);
		waitForElementToDisplay(PageReload, 10);
		List<String> ZioskServices = GetListOfAllCurrentRowsForCol(1);
		int i = 0;
		for (String ServiceName : ZioskServices) {
			if (!ZioskServiceArray[i].equals(ServiceName)) {

				fourZServicepresent = false;
				break;
			}
			i++;
		}
		return fourZServicepresent;
	}

	public boolean RestartServiceFuncationality(int i) {
		boolean restart = false;
		boolean correctMsg = false;
		String firsthalf = "//div[@class='brand-ed-body my-2 row ml-0 mr-0 ng-star-inserted']//table/tbody/child::tr[";
		String FirstServiceStopBtn = firsthalf + i + "]/td[5]//button[3]";
		String FirstServiceRestartBtn = firsthalf + i + "]/td[5]//button[2]";
		String FirstServiceStartBtn = firsthalf + i + "]/td[5]//button[1]";
		if (!driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled()) {
			driver.findElement(By.xpath(FirstServiceRestartBtn)).click();

			if (ConfirmBtn.isDisplayed()) {
				correctMsg = AlertMessage.getText().contains("Are you sure you want to Restart the service");
				ConfirmBtn.click();
			}

		}
	TestUtil.sleep(5000);
		//IsSpinningComplete();
		if (!driver.findElement(By.xpath(FirstServiceStartBtn)).isEnabled()
				&& driver.findElement(By.xpath(FirstServiceRestartBtn)).isEnabled()
				&& driver.findElement(By.xpath(FirstServiceStopBtn)).isEnabled() && correctMsg) {
			restart = true;
		}
		return restart;
	}

	public boolean ExpandAllFiles() {

		
		FileStructureOpen();

		List<String> originalList = CurrentOpenGreenFolders.stream().map(s -> s.getText().split("-")[1].trim())
				.collect(Collectors.toList());
		// System.out.println(CurrentOpenGreenFolders.get(0).getText());
		TestUtil.sleep(4000);
		if (TopLinksonFilesTab.get(3).getText().trim().equals("Expand All")) {
			TopLinksonFilesTab.get(3).click();
			TestUtil.sleep(3000);
			TopLinksonFilesTab.get(3).click();
			TestUtil.sleep(3000);
			TopLinksonFilesTab.get(3).click();
		}

		List<String> AfterCollapseList = CurrentOpenGreenFolders.stream().map(s -> s.getText().split("-")[1].trim())
				.collect(Collectors.toList());

		if (originalList.equals(AfterCollapseList))
			return true;
		else
			return false;
	}

	public boolean DownloadFile() {

		boolean b = false;
	

		String CheckboxXpath = "//span[normalize-space()='WhiteColorSoftware.zip   -  3.57 MB']/parent::*/preceding-sibling::mat-checkbox";
		WebElement chkbx = driver.findElement(By.xpath(CheckboxXpath));
		chkbx.click();
		TopLinksonFilesTab.get(1).click();
		ConfirmBtn.click();

		TestUtil.sleep(40000);
		File file = new File(System.getProperty("user.dir"));
		File ff[] = file.listFiles();
		for (File f : ff) {
			if (f.getName().contains("download")) {
				// System.out.println(f.getName());
				String filePath = System.getProperty("user.dir") + "\\" + f.getName();
				// System.out.println(filePath);
				File delfile = new File(filePath);
				delfile.delete();
				b = true;
			}
		}

		return b;

	}

	public boolean BackwardNavigate() {
		supportToolsPage = GotoSupportToolsPage();
		if (supportToolsPage.WatcherClientSelection.isDisplayed())
			return true;
		else
			return false;
	}

	public SupportToolsPage GotoSupportToolsPage() {
		waitForElementToDisplay(PageReload, 10);
		waitForElementToDisplay(BackArrowLink, 10);
		waitForVisibilityOf(BackArrowLink, 10);
		BackArrowLink.click();
		return new SupportToolsPage();
	}

	public boolean IsSpinningComplete() {
		String spinnerXpath = "//div[@class='ngx-spinner-overlay ng-tns-c91-9 ng-trigger ng-trigger-fadeIn ng-star-inserted']";
		WebElement spinner = driver.findElement(By.xpath(spinnerXpath));
		return waitForElementToHide(spinner, 20);
	}

	public RMPage WatcherToRM() {
		waitForElementToDisplay(CurrentTool, 10);
		waitForVisibilityOf(CurrentTool, 10);
		CurrentTool.click();
		waitForVisibilityOf(Tool_RMPortal, 10);
		Tool_RMPortal.click();
		return new RMPage();
	}
	
	public QRCodePage WatcherToQRcode() {
		waitForElementToDisplay(CurrentTool, 10);
		waitForVisibilityOf(CurrentTool, 10);
		CurrentTool.click();
		waitForVisibilityOf(Tool_QRCode, 10);
		Tool_QRCode.click();
		return new QRCodePage();
	}
	public boolean SwitchBetPageswithTool() {
		boolean b1,b2,b3,b4,b5;
		RMPage rmPage = WatcherToRM();
		b1= rmPage.VerifyRMPage();
		
		POSPage posPage = rmPage.RMtoPOS();
		b2= posPage.VerifyPOSPage();
		
		WatcherPage watcherPage = posPage.POStoWatcher();
		b3= watcherPage.ValidateServicesTablePopulated();
		
		QRCodePage qrPage = watcherPage.WatcherToQRcode();
		b4= qrPage.VerifyQRPage();
		
		WatcherPage watcherPage1 = qrPage.QRtoWatcher();
		b5= watcherPage1.ValidateServicesTablePopulated();
		
		if(b1&& b2 && b3 && b4 &&b5)
			return true;
		else
			return false;
	}

	public boolean SwitchStore() {
		String store= "Starlink 2";
		String storeXpath= "//span[@class='mat-option-text' and contains(text(),'"+ store +"')]";

		waitForElementToDisplay(MainPageDropdown, 10);
		waitForVisibilityOf(MainPageDropdown, 10);
		MainPageDropdown.click();
		AllTextBoxes.get(2).click();
		Crosses.get(2).click();
		AllTextBoxes.get(2).click();
		waitForVisibilityOf(AllTextBoxes.get(2), 10);
		WebElement storeNameOrNumber = driver.findElement(By.xpath(storeXpath));
		storeNameOrNumber.click();
		ApplyBtn.click();
		if(WarningText.getText().contains("This service is temporarily down."))
			return true;
		else
			return false;
	}

	

}