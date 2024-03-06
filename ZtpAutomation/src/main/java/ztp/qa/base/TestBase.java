//This is base class which contains all the initialization code
//@author : Praggati Oswal
package ztp.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import ztp.qa.util.ExtentReportManager;
import ztp.qa.util.TestUtil;
import ztp.qa.util.WebListener;

public class TestBase {

	public static WebDriver webdriver;
	public static Properties prop;
	public static WebDriver driver;
	public static ChromeOptions co;
	public static WebDriverListener listener = new WebListener();
	public static ExtentReports report;
	public static ExtentTest extentTest;
	public static Logger log;

	public TestBase() {
		log = LogManager.getLogger(TestBase.class);
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\ztp" + "\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Could not find config.properties file");;
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");
		
		switch (browserName) {
		case "chrome":
			co = new ChromeOptions();
			co.addArguments("--disable-infobars");
			co.addArguments("--disable-notifications");
			co.setAcceptInsecureCerts(true);
			co.addArguments("--remote-allow-origins=*");
			co.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
			
			co.setExperimentalOption("prefs",chromePrefs);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			webdriver = new ChromeDriver(co);
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver",
			System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			webdriver = new FirefoxDriver();
			break;

		default:
			co = new ChromeOptions();
			co.addArguments("--disable-infobars");
			co.addArguments("--disable-notifications");
			co.setAcceptInsecureCerts(true);
			co.addArguments("--remote-allow-origins=*");
			co.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			webdriver = new ChromeDriver(co);co = new ChromeOptions();
			
			break;
		}

	
		driver = new EventFiringDecorator<WebDriver>(listener).decorate(webdriver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));
		driver.get(prop.getProperty("url"));
		log.info("Logged into Support Portal Home Page");
		
		report = ExtentReportManager.getReportInstance();
	}

	
	
	
//	Wait for alert present and then switch to it 
	protected Alert switchToAlert() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.alertIsPresent());
		return driver.switchTo().alert();
	}
	
    public static boolean waitForVisibilityOf(WebElement element,int sec){
    	boolean result=false;
    	try {
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(sec));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        result=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return result;
    }

    
/*    public static boolean waitForelementToBeClickable(WebElement element,int sec){
    	boolean result=false;
    	try {
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(sec));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	     
	        result=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return result;
    }*/
    public static boolean waitForelementToBeClickable(WebElement element,int sec){
    	boolean result=false;
    	try {
	        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(sec));
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	     
	        result=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    		
    	}
        return result;
    }
    
  
	 public static boolean waitForElementToDisplay(WebElement element,int timeOutInSeconds){
	        boolean isDisplayed=false;
	        for(int i=0;i<timeOutInSeconds;i++){
	            try {
	                if(element.isDisplayed()){
	                	
	                	log.info(element.toString()+" is visible");
	                    isDisplayed=true;
	                    TestUtil.sleep(1000);
	                    break;
	                }
	            }catch (NoSuchElementException exception){
	            	log.info(element.toString()+" is not visible");
	                TestUtil.sleep(1000);
	            }
	        }
	        return isDisplayed;
	    }

	    public static boolean waitForElementToHide(WebElement element,int timeOutInSeconds){
	        boolean isNotDisplayed=false;
	       // System.out.println("Inside wait function");
	        try {
	            for (int i=0;i<timeOutInSeconds;i++){
	                if(element.isDisplayed()){
	                	log.info(element.toString()+" is visible");
	                	TestUtil.sleep(1000);
	                }
	                
	            }
	        }catch (NoSuchElementException | StaleElementReferenceException exception){
	        	log.info(element.toString()+" is hidden");
	            isNotDisplayed=true;
	        }
	        return isNotDisplayed;
	    }
	    
	    public static boolean waitForElementContentToDisplay(WebElement element,int timeOutInSeconds){
	        boolean _isContentDisplayed=false;
	        for(int i=0;i<timeOutInSeconds;i++){
	            String text=element.getText();
	            if(text==null){
	                text=element.getText();
	            }
	            if(text!=null && !text.equals("")){
	            	log.info(element.toString()+" is visible");
	            	TestUtil.sleep(1000);
	            	_isContentDisplayed=true;
	                break;
	            }else{
	            	log.info(element.toString()+" is not visible");
	                TestUtil.sleep(1000);
	            }
	        }
	        return _isContentDisplayed;
	    }

	

}
