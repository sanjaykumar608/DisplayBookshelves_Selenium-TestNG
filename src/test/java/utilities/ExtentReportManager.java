package utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;   // UI of the report
    public ExtentReports extent;                // Populate common info of the report
    public ExtentTest test;  // Create test case entries in the report and update status of test methods

    String repName;

    // This method is invoked when the test starts
    public void onStart(ITestContext testContext) {
    	
        try {
	        // Initialize the ExtentSparkReporter with the report file path
	        sparkReporter = new ExtentSparkReporter(".\\reports\\report.html");
	
	        // Configure the report
	        sparkReporter.config().setDocumentTitle("UrbanLadder Automation Report"); // Title of the report
	        sparkReporter.config().setReportName("UrbanLadder Functional Testing"); // Name of the report
	        sparkReporter.config().setTheme(Theme.DARK); // Theme of the report
	
	        // Initialize ExtentReports and attach the reporter
	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	
	        // Set system information for the report
	        extent.setSystemInfo("Application", "UrbanLadder");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");
	
	        // Get OS and browser information from test context parameters
	        extent.setSystemInfo("Operating System", "OS");
	
	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }

        
    }

    // This method is invoked when a test method succeeds
    public void onTestSuccess(ITestResult result) {
    	try {
            
    		 	test = extent.createTest(result.getTestClass().getName());
    	        test.assignCategory(result.getMethod().getGroups()); // Display groups in the report
    	        test.log(Status.PASS, result.getName() + " got successfully executed");
    	        
    	        
        } catch (Exception e1) {
            e1.printStackTrace();
        }
       
    }

    // This method is invoked when a test method fails
    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            // Capture screenshot on failure
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    // This method is invoked when a test method is skipped
    public void onTestSkipped(ITestResult result) {
    	try {
    		test = extent.createTest(result.getTestClass().getName());
            test.assignCategory(result.getMethod().getGroups());
            test.log(Status.SKIP, result.getName() + " got skipped");
            test.log(Status.INFO, result.getThrowable().getMessage());
    	}
    	catch (Exception e1) {
            e1.printStackTrace();
        }
        
    }

    // This method is invoked when all tests are finished
    public void onFinish(ITestContext testContext) {

        try {
        	extent.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
