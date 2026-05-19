package TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver; // browser object (chrome/firefox/safari)
	public static Logger logger; // logs for debugging steps
	public Properties p; // to read config.properties file

	// =========================================================
	// BeforeClass - runs before starting test class
	// =========================================================
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	@Parameters({ "os", "browser" })
	public void setUp( @Optional ("mac") String os, @Optional("chrome") String br) throws IOException {

		// Step 1: load config.properties file
		FileReader file = new FileReader("./src/test/resources/config.properties");
		p = new Properties();
		p.load(file);

		// Step 2: initialize logger (for logs)
		logger = LogManager.getLogger(this.getClass());

		// Step 3: open browser based on testng.xml parameter
		switch (br.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver(); // open chrome
			break;

		case "firefox":
			driver = new FirefoxDriver(); // open firefox
			break;

		case "safari":
			driver = new SafariDriver(); // open safari
			break;

		default:
			System.out.println("Invalid Browser");
			return;
		}

		// Step 4: maximize browser window
		driver.manage().window().maximize();

		// Step 5: set implicit wait (for elements loading)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		// driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

		// Step 6: open application URL from config file
		driver.get(p.getProperty("appURL"));

		// Step 7: delete cookies (fresh session start)
		driver.manage().deleteAllCookies();
	}

	// =========================================================
	// AfterClass - runs after finishing all test methods
	// =========================================================
	@AfterClass(groups = { "Sanity", "Regression", "Master" })
	public void tearDown() {

		if (driver != null) {
			driver.quit();
		}
	}

	// =========================================================
	// generate random string (for test data)
	// =========================================================
	public String randomstring() {

		@SuppressWarnings("deprecation")
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}

	// =========================================================
	// generate random number (for test data)
	// =========================================================
	public String randomeNumber() {

		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}

	// =========================================================
	// generate random email-like value (for test data)
	// =========================================================
	public String randomeAlphaNumberic() {

		@SuppressWarnings("deprecation")
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		@SuppressWarnings("deprecation")
		String generatednumber = RandomStringUtils.randomNumeric(3);

		return (generatedstring + "@" + generatednumber);
	}

	// =========================================================
	// capture screenshot method
	// =========================================================
	public String captureScreen(String testName) {

		// screenshot save path
		String timeStamp = String.valueOf(System.currentTimeMillis());
		// generate unique timestamp
	    // so every screenshot name will be different
		  String screenshotName = testName + "_" + timeStamp + ".png";
		  
		  // final screenshot save path
		    // screenshots will be stored inside reports folder
		    // important for Jenkins + Extent Report
		String targetFilePath = System.getProperty("user.dir")
				 + "/reports/screenshots/"
		            + screenshotName;

		try {

			// convert driver into TakesScreenshot
			TakesScreenshot ts = (TakesScreenshot) driver;

			// capture screenshot file temp
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);

			 // create target file object
			File targetFile = new File(targetFilePath);
			
			 // create screenshots folder automatically
	        // if folder does not exist
			 targetFile.getParentFile().mkdirs();

			// copy screenshot from temp location
		        // to final screenshots folder
			FileUtils.copyFile(sourceFile, targetFile);

			System.out.println("Screenshot saved at: " + targetFilePath);

		}

		catch (Exception e) {

			 System.out.println("Screenshot capture failed: " + e.getMessage());

		}
		 // returning relative screenshot path
	    // Extent Report uses this path to display image

		 return targetFilePath;
	}
}