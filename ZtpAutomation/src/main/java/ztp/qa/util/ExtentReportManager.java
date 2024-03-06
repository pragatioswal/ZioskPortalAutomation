//This is class for creating html reports
//@author : Praggati Oswal
package ztp.qa.util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtentReportManager {
	
	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReporter;
	
	public static ExtentReports getReportInstance(){
		
		if(report == null){
			//String reportName = System.currentTimeMillis()  + ".html";
			String reportName = "ZioskSupportPortal.html";
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\" + reportName);
			System.out.println(htmlReporter);
			report =  new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 11");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "x.x");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReporter.config().setDocumentTitle("Ziosk Support Portal Automation Results");
			htmlReporter.config().setReportName("Ziosk Support Portal UI Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}
		
		return report;

	}
}
