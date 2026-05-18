package utilities;

// for generating timestamp in report name
import java.text.SimpleDateFormat;
// for current date and time
import java.util.Date;
// for handling test groups list
import java.util.List;

// TestNG listener interfaces
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Extent Report classes
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
// Extent Spark Reporter (HTML report)
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// for report theme
import com.aventstack.extentreports.reporter.configuration.Theme;

// importing BaseClass for screenshot capture
import TestBase.BaseClass;

// listener class implements ITestListener
// so TestNG can track test execution events
public class ExtentReportManager implements ITestListener {

	// object for HTML report generation
	public ExtentSparkReporter sparkReporter;

	// main extent report object
	public ExtentReports extent;

	// used to create individual test entries
	public ExtentTest test;

	// variable to store report name
	String repName;

	// ===== executes before starting all test cases =====
	@Override
	public void onStart(ITestContext testContext) {

		// creating timestamp for unique report name
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

		// final report name
		repName = "Test-Report-" + timeStamp + ".html";

		// report location path
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + repName);

		// report title shown in browser tab
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report");

		// report heading
		sparkReporter.config().setReportName("OpenCart Functional Testing");
		// setting report theme dark
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimelineEnabled(true);
		// creating ExtentReports object
		extent = new ExtentReports();
		// attaching spark reporter to extent report
		extent.attachReporter(sparkReporter);
		// system/project information in report
		extent.setSystemInfo("Application", "OpenCart");

		extent.setSystemInfo("Module", "User Account");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		// getting OS parameter from XML file
		String os = testContext.getCurrentXmlTest().getParameter("os");

		// adding OS info into report
		extent.setSystemInfo("Operating System", os);

		// getting browser parameter from XML
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		// adding browser info into report
		extent.setSystemInfo("Browser", browser);

		// getting included test groups from XML
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();

		// checking groups list is not empty
		if (!includedGroups.isEmpty()) {

			// adding group details into report
			extent.setSystemInfo("Groups", includedGroups.toString());

		}
	}

	// ===== executes when test case passes =====
	@Override
	public void onTestSuccess(ITestResult result) {

		// creating test entry in report
		test = extent.createTest(result.getName());

		// assigning groups into report
		test.assignCategory(result.getMethod().getGroups());

		// logging PASS status
		test.log(Status.PASS, result.getName() + " got successfully executed");

	}

	// ===== executes when test case fails =====
	@Override
	public void onTestFailure(ITestResult result) {

		// creating failed test entry
		test = extent.createTest(result.getName());

		// assigning groups
		test.assignCategory(result.getMethod().getGroups());

		// logging FAIL status
		test.log(Status.FAIL, result.getName() + " got failed");

		// printing failure reason
		test.log(Status.INFO, result.getThrowable().getMessage());

		// screenshot capture block
		try {

			// creating BaseClass object
			BaseClass base = (BaseClass) result.getInstance();

			// capturing screenshot
			String imgPath = base.captureScreen(result.getName());

			// attaching screenshot into report
			test.addScreenCaptureFromPath(imgPath);

		}

		// exception handling
		catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ===== executes when test case gets skipped =====
	@Override
	public void onTestSkipped(ITestResult result) {

		// creating skipped test entry
		test = extent.createTest(result.getName());

		// assigning groups
		test.assignCategory(result.getMethod().getGroups());

		// logging SKIP status
		test.log(Status.SKIP, result.getName() + " got skipped");

		// showing skip reason
		test.log(Status.INFO, result.getThrowable().getMessage());

	}

	// ===== executes after all test cases finish =====
	@Override
	public void onFinish(ITestContext testContext) {

		// saving/finalizing report
		extent.flush();

	}
}
