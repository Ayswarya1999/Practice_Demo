package com.HireProUs.Engine;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ClientApi;
import org.zaproxy.clientapi.core.ClientApiException;
import com.HireProUs.ReusableMethods.ReusableMethodCommon;
import com.HireProUs.ReusableMethods.ReusableMethodsLogin;
import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BaseClass {

	public static String BrowserName;
	public static String Environment;
	public static String securtiyTest;
	public static WebDriver driver;
	public static XlsReader data;
	public static XlsReader pl;
	public static XlsReader portfolioLocationDownload;
	public static int timeout = 60;
	public static int Scorecardtimeout = 1200;
	public static boolean recordingStarted = false;
	public static ExtentTest testlog;
	public static ExtentReports extent;
	public static String TestCaseName;
	public static String RoleName;
	public static String TestNGTestName;
	public static String SecurityAssesment;
	public static String objectvalue;
	private ClientApi api;
	private ApiResponse response;
	public static StopWatch pageLoad;
	public static Faker USfaker = new Faker(new Locale("en-US"));
	static final String ZAP_PROXY_ADDRESS = "localhost";
	static final int ZAP_PROXY_PORT = 8091;
	public static SoftAssert softAssert = new SoftAssert();
	public static SoftAssert negativesoftAssert = new SoftAssert();
	public static SoftAssert negativeFieldSoftAssert = new SoftAssert();
	public static ReusableMethodCommon rc = new ReusableMethodCommon();

	public static String SamplePdffile = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "Files" + File.separator + "Resume.pdf";
	
	public static String OS = System.getProperty("os.name");
	public static String downloadPath = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator;
	public static String ScreenshotsPath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
	public static String CandidateResume = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources" + File.separator + "Files" + File.separator + "CandidateResume.pdf";
	public static String CandidateImage = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources" + File.separator + "Files" + File.separator + "CandidateImage.png";

			
	public static ReusableMethodsLogin login = new ReusableMethodsLogin();

	public static Map<String, Object> pathprms = new HashMap<String, Object>();
	public String envVar;

	
	@SuppressWarnings("deprecation")
	@BeforeSuite
	@Parameters({ "browserName", "environment", "SecurtiyTest" })
	public void setup(String browserName, String environment, @Optional("false") String SecurtiyTest)
			throws InterruptedException, IOException, ClientApiException {

		data = new XlsReader(System.getProperty("user.dir") + "/TestData.xlsx");
		Properties prop = new Properties();
		if ((System.getenv("browserName") != null && !System.getenv("browserName").isEmpty())

				&& System.getenv("environment") != null && !System.getenv("environment").isEmpty()) {

			browserName = System.getenv("browserName");
			environment = System.getenv("environment");
			Environment = environment;
			
			System.out.println(browserName);
			System.out.println(environment);
			

		}

		else {
			
			Environment = environment;
			BrowserName = browserName;
			securtiyTest = SecurtiyTest;

		}

		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("chrome") && SecurtiyTest.equalsIgnoreCase("true")) {
			SecurityAssesment = "true";
			String ProxyServerURL = ZAP_PROXY_ADDRESS + ":" + ZAP_PROXY_PORT;
			Proxy proxy = new Proxy();
			proxy.setAutodetect(false);
			proxy.setHttpProxy(ProxyServerURL);
			proxy.setSslProxy(ProxyServerURL);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--proxy-bypass-list=<-loopback>");
			options.setAcceptInsecureCerts(true);
			options.setProxy(proxy);
			WebDriverManager.chromedriver().setup();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("safebrowsing.enabled", "false");
			// options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("useAutomationExtension", false);
			// options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			options.addArguments("--window-size=1920,1280");
			options.addArguments("--window-position=0,0");
			options.addArguments("--disable-web-security");
			options.setHeadless(false);
			driver = new ChromeDriver(options);
			JSWaiter.setDriver(driver);
			api = new ClientApi(ZAP_PROXY_ADDRESS, ZAP_PROXY_PORT);

		}

		else if (browserName.equalsIgnoreCase("chrome") && SecurtiyTest.equalsIgnoreCase("false")) {
			SecurityAssesment = "false";
			ChromeOptions options = new ChromeOptions();
			if (OS.contains("Window")) {
				WebDriverManager.chromedriver().driverVersion("123.0.6312.59").setup();
				
				
			} else {
				WebDriverManager.chromedriver().setup();
			}
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", downloadPath);
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("safebrowsing.enabled", "false");
			// options.addArguments("--incognito");
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-application-cache");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.addArguments("disable-infobars");
			options.addArguments("--disable-notifications");
			options.setExperimentalOption("useAutomationExtension", false);
			// options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			options.addArguments("--window-size=1920,1280");
			options.addArguments("--window-position=0,0");
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-web-security");
			options.addArguments("disable-save-password-bubble");
			options.setHeadless(false);
			driver = new ChromeDriver(options);
			driver.manage().deleteAllCookies();
			JSWaiter.setDriver(driver);
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(120));
		prop = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/Environment.properties");
		prop.load(file);
		String qasurl = prop.getProperty("ENV_QAS");
		String stgurl = prop.getProperty("ENV_STAGING");
		String testurl = prop.getProperty("ENV_TEST");
		if (Environment.equalsIgnoreCase("TEST")) {
			driver.get(testurl);
			
		} else if (Environment.equalsIgnoreCase("QAS")) {
			driver.get(qasurl);
			
		}
	}
	
	@BeforeTest(alwaysRun = true)
	public void getTestNGTestName(final ITestContext testContext) {
		TestNGTestName = testContext.getName();
	}

	@BeforeMethod(alwaysRun = true)
	public static ExtentReports ExtentReportConfig() throws IOException {
		softAssert = new SoftAssert();
		pathprms.clear();
		if (extent == null) {
			final File CONF = new File(System.getProperty("user.dir") + "/src/main/resources/Extentconfig.json");
			extent = new ExtentReports();
			extent.setSystemInfo("Host Name", "Jenkins");
			extent.setSystemInfo("Environment", Environment);
			extent.setSystemInfo("User Name", "Abhishek Gupta");
			ExtentSparkReporter spark = new ExtentSparkReporter(
					System.getProperty("user.dir") + "/Report/WELL_AutomationReport" + /* TestNGTestName + */ ".html");
			extent.attachReporter(spark);
			spark.loadJSONConfig(CONF);

		}
		return extent;
	}

	public static void StartTest(String TestcaseName, String Description) {

		testlog = extent.createTest(TestcaseName, "This Test is intented to verify -" + Description);
		System.out.println("Starting Test : " + TestNGTestName);
		System.out.println("Starting Test : " + TestCaseName);
		// testlog.info("Starting TestSuite : "+ SuiteName+" and Test : "+
		// TestNGTestName);
	}

	public void logTestFailure() throws IOException, NumberFormatException, InterruptedException {

		testlog.log(Status.FAIL, MarkupHelper.createLabel(TestCaseName + " - Test Case Failed", ExtentColor.RED));
		/*
		 * testlog.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() +
		 * " - Test Case Failed", ExtentColor.RED));
		 */
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100))
				.takeScreenshot(driver);

		// File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// BufferedImage img = ImageIO.read(screenshot);
		File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		int num = Integer.parseInt(CommonMethod.randomNumber(9999));
		ImageIO.write(screenshot.getImage(), "png", new File(filetest + File.separator + "Screenshots" + File.separator
				+ /* ScreenshotCreditName+ */"_" + num + ".png"));
		/*
		 * testlog.info("Details of " + "Fail Test screenshot", MediaEntityBuilder
		 * .createScreenCaptureFromPath(System.getProperty("user.dir") +File.separator
		 * +"Screenshots"+File.separator + TestCaseName +".png") .build());
		 */
	}

	public static void captureScreenshot() throws IOException, InterruptedException {

		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage img = ImageIO.read(screen);
		File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		ImageIO.write(img, "png", new File(
				filetest + File.separator + "Screenshots" + File.separator + CommonMethod.randomNumber() + ".png"));

	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) throws Exception {

		if (api != null && SecurityAssesment.equalsIgnoreCase("true")) {

			api.pscan.enableAllScanners();
			response = api.pscan.recordsToScan();
			while (!response.toString().equals("0")) {
				response = api.pscan.recordsToScan();
			}
			String Title = "ZAP Security Report";
			String Template = "traditional-html";
			String Description = "ZAP Security Report";
			String ReportFileName = "ZAP_report.html";
			String TargetFolder = System.getProperty("user.dir") + "/Report";

			response = api.reports.generate(Title, Template, null, Description, null, null, null, null, null,
					ReportFileName, null, TargetFolder, null);
			System.out.println("ZAP report generated at this location : " + response.toString());
		}
		/*
		 * String RS = RatingSystem; System.out.println(RS);
		 */
		if (result.getStatus() == ITestResult.FAILURE) {

			testlog.log(Status.FAIL,
					MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			testlog.log(Status.FAIL,
					MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			BufferedImage image = Shutterbug.shootPage(driver, Capture.FULL).getImage();
			File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
			ImageIO.write(image, "png", new File(filetest + File.separator + "Screenshots"
					+ File.separator /* +SuiteName+"_"+TestNGTestName +"-"+ */ + TestCaseName + ".png"));

			// Screenshot s = new
			// AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);

			/*
			 * File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			 * BufferedImage img = ImageIO.read(screen);
			 */
			/*
			 * File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
			 * ImageIO.write(s.getImage(), "png", new File(filetest + File.separator +
			 * "Screenshots" + File.separator +SuiteName+"_"+TestNGTestName
			 * +"-"++TestCaseName +".png"));
			 *//*
				 * testlog.info("Details of " + "Test screenshot", MediaEntityBuilder
				 * .createScreenCaptureFromPath("." +System.getProperty("user.dir") +
				 * File.separator + "Screenshots" + File.separator + TestCaseName +".png")
				 * .build());
				 */
			// search.SearchProjectOnFailure(SheetName, rowNum);
		} else if (result.getStatus() == ITestResult.SKIP) {
			// testlog.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			testlog.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}

	}

	@AfterClass(alwaysRun = true)
	public void flushReport() {

		extent.flush();
		// extent=null;
		System.out.println("EndClass");

	}

	@AfterTest(alwaysRun = true)
	public void quit() throws InterruptedException, IOException, ClientApiException {

		System.out.println("EndTest");
		rc.SignOut();
		Thread.sleep(3000);
		try {
			FileUtils.cleanDirectory(new File(downloadPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		negativesoftAssert = new SoftAssert();
		negativeFieldSoftAssert = new SoftAssert();
	}

	@AfterSuite(alwaysRun = true)
	public void end() {
		if (recordingStarted) {
			ScreenRecorderUtil.stopRecord();
		}
		System.out.println("EndSuite");
		driver.quit();
	}

	@BeforeSuite
	public void clearScreenshotInWidows() {
		if (OS.contains("Window")) {
			try {
				FileUtils.cleanDirectory(new File(ScreenshotsPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}