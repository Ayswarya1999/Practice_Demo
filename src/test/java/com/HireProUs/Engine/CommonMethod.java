package com.HireProUs.Engine;

import static org.testng.Assert.assertTrue;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.google.common.base.Verify;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

public class CommonMethod extends BaseClass {

	static Format formatter = new SimpleDateFormat("YYYY-MM-dd");
	static Date date = new Date();
	public static String fetchedID;
	public static String ProgramID;
	public static String signupID;
	public static String screenshotfile = System.getProperty("user.dir") + "/Screenshots/";
	public static File extentconfigfile = new File(
			System.getProperty("user.dir") + "/src/main/resources/extent-config.xml");
	public static String Reportfile = System.getProperty("user.dir") + "/Report/WELL-AutomationReport" + "_"
			+ formatter.format(date) + ".html";
	public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	static WebElement element;
	public static List<String> uploadedFilesList = new ArrayList<String>();
	public static ArrayList<String> narrativevalues = new ArrayList<String>();
	public static ArrayList<String> numericvalues = new ArrayList<String>();
	public static ArrayList<String> textvalues = new ArrayList<String>();
	public static ArrayList<String> dropdownvalues = new ArrayList<String>();
	public static ArrayList<String> datevalues = new ArrayList<String>();

	public static void printlogs() {

		if (OS.contains("Window")) {

			System.out.println("obj val: " + objectvalue);

		}
	}

	public static WebElement findElementWithRelative(final String objectLocaterFirst, final String objectLocaterSecond,
			String Type) throws IOException {

		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectLocaterSecond);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];
		objectvalue = splits[1];
		printlogs();

		switch (objecttype) {

		case "id":
			return driver.findElement(By.id(objectvalue));

		case "xpath":
			switch (Type) {

			case "above":
				return driver.findElement(
						RelativeLocator.with(By.xpath(objectvalue)).above(LocatorStrategy(objectLocaterFirst)));
			}
		case "name":

			return driver.findElement(By.name(objectvalue));

		case "class":

			return driver.findElement(By.className(objectvalue));

		case "tagname":

			return driver.findElement(By.tagName(objectvalue));

		case "css":

			return driver.findElement(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElement(By.linkText(objectvalue));
		default:

			return null;
		}

	}

	public static By LocatorStrategy(final String objectLocater) throws IOException {

		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectLocater);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];
		objectvalue = splits[1];
		printlogs();

		switch (objecttype) {

		case "id":
			return (By.id(objectvalue));

		case "xpath":

			return (By.xpath(objectvalue));

		case "name":

			return (By.name(objectvalue));

		case "class":

			return (By.className(objectvalue));

		case "tagname":

			return (By.tagName(objectvalue));

		case "css":

			return (By.cssSelector(objectvalue));

		case "linkText":

			return (By.linkText(objectvalue));
		default:

			return null;
		}

	}

	public static WebElement findElement(final String objectLocater) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectLocater);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();

		switch (objecttype) {

		case "id":
			return driver.findElement(By.id(objectvalue));

		case "xpath":

			return driver.findElement(By.xpath(objectvalue));

		case "name":

			return driver.findElement(By.name(objectvalue));

		case "class":

			return driver.findElement(By.className(objectvalue));

		case "tagname":

			return driver.findElement(By.tagName(objectvalue));

		case "css":

			return driver.findElement(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElement(By.linkText(objectvalue));
		default:

			return null;
		}

	}

	public static List<WebElement> findElements(String objectLocater) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectLocater);
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		//
		objectvalue = splits[1];
		printlogs();

		switch (objecttype) {

		case "id":
			return driver.findElements(By.id(objectvalue));

		case "xpath":

			return driver.findElements(By.xpath(objectvalue));

		case "name":

			return driver.findElements(By.name(objectvalue));

		case "class":

			return driver.findElements(By.className(objectvalue));

		case "tagname":

			return driver.findElements(By.tagName(objectvalue));

		case "css":

			return driver.findElements(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElements(By.linkText(objectvalue));
		default:

			return null;
		}

	}

	public static List<WebElement> findElementsCustom(String objectLocater) throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);
		String objecttypeandvalues = OR.getProperty(objectLocater);
		//
		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];
		//
		objectvalue = splits[1];
		printlogs();

		switch (objecttype) {

		case "id":
			return driver.findElements(By.id(objectvalue));

		case "xpath":

			return driver.findElements(By.xpath(objectvalue));

		case "name":

			return driver.findElements(By.name(objectvalue));

		case "class":

			return driver.findElements(By.className(objectvalue));

		case "tagname":

			return driver.findElements(By.tagName(objectvalue));

		case "css":

			return driver.findElements(By.cssSelector(objectvalue));

		case "linkText":

			return driver.findElements(By.linkText(objectvalue));
		default:

			return null;
		}

	}

	// user defined click Method
	public static void click(String objectLocater) throws IOException {

		CommonMethod.WaitUntilClickble(objectLocater, timeout);
		findElement(objectLocater).click();

	}

	public static void Robustclick(String objectLocater) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		do {
			Thread.sleep(3000);
			if (CommonMethod.isElementsExist(objectLocater, 5)) {
				try {
					findElement(objectLocater).click();
					Thread.sleep(3000);
					System.out.println("Inside");
				}

				catch (Exception e) {

				}
			}
		} while (!(!CommonMethod.isElementsExist(objectLocater, 2)
				|| !((System.currentTimeMillis() - startTime) < 60000)));

	}

	public static void Robustclick(String objectLocater, String objectLocater1)
			throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		do {
			Thread.sleep(2000);
			if (CommonMethod.isElementsExist(objectLocater, 5)) {
				try {
					findElement(objectLocater).click();
					Thread.sleep(2000);
					System.out.println("Inside");
				} catch (Exception e) {
				}
			}
		} while (!(!CommonMethod.isElementsExist(objectLocater1, 2)
				|| !((System.currentTimeMillis() - startTime) < 60000)));

	}

	public static void RobustclickElementVisible(String objectLocater, String objectLocater1)
			throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		do {
			Thread.sleep(2000);
			if (CommonMethod.isElementsExist(objectLocater, 5)) {
				try {
					findElement(objectLocater).click();
					Thread.sleep(2000);
					System.out.println("Inside");
				}

				catch (Exception e) {

				}
			}
		} while (!(CommonMethod.isElementsExist(objectLocater1, 10)
				|| !((System.currentTimeMillis() - startTime) < 60000)));

	}

	public static void click(WebElement objectLocater) throws IOException {

		objectLocater.click();

	}

	// user defined sendkeys Method
	public static void sendKeys(String objectLocater, String value) throws IOException {
		CommonMethod.WaitUntilClickble(objectLocater, timeout);
		findElement(objectLocater).sendKeys(value);

	}

	public static void sendKeyEnter(String objectLocater) throws IOException {
		findElement(objectLocater).sendKeys(Keys.ENTER);

	}

	// user defined gettext Method
	public static String getText(String objectLocater) throws IOException {

		return findElement(objectLocater).getText();

	}

	public static String getText(WebElement objectLocater) throws IOException {

		return objectLocater.getText();

	}

	public static void refreshBrowser() {

		driver.navigate().refresh();
		JSWaiter.waitAllRequest();
		waitForPageLoaded();

	}

	public static void GotoURL(String URL) throws IOException {

		// driver.navigate().to(URL);
		driver.get(URL);

	}

	public static String getattributeValue(String objectLocater) throws IOException {

		return findElement(objectLocater).getAttribute("value");

	}

	public static String getattributeValueByTextContent(String objectLocater) throws IOException {

		return findElement(objectLocater).getAttribute("textContent");

	}

	public static String getattributeValueWebElement(WebElement objectLocater, String attribute) throws IOException {

		return objectLocater.getAttribute(attribute);

	}

	public static String getattributeLabel(String objectLocater) throws IOException {

		return findElement(objectLocater).getAttribute("label");

	}

	// user defined clear Method
	public static void clear(String objectLocater) throws IOException {
		findElement(objectLocater).clear();
		testlog.pass("Clearing TextField");

	}

	public static void uploadFile(String objectLocater, String file) throws IOException {
		findElement(objectLocater).sendKeys(file);

	}

	public static void uploadFile(String objectLocater, String file, String VerifyobjectLocater) throws IOException {
		findElement(objectLocater).sendKeys(file);
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(VerifyobjectLocater, 0);
	}

	public static void uploadFile(WebElement objectLocater, String file) throws IOException {
		objectLocater.sendKeys(file);

	}

	public static void navigateBack() {

		driver.navigate().back();
	}

	/*
	 * public static void renameAndCopyfile(String CreditID) throws IOException{
	 * File oldfile = new File(CreditUploadFile1); v2009file =
	 * downloadPath+CreditID+"_"+CreditUploadFile; newfile = new File(v2009file);
	 * 
	 * FileUtils.copyFile(oldfile, newfile);
	 * 
	 * if(oldfile.renameTo(newfile)) {
	 * System.out.println("File name changed succesful"); } else {
	 * System.out.println("Rename failed"); } }
	 */

	public static void assertcontainsmessage(ArrayList<String> Expected, String Actual, String message)
			throws IOException {

		// System.out.println(Arrays.toString(Expected.toArray()));
		// System.out.println(Actual);
		Assert.assertTrue(Expected.contains(Actual), message);
	}

	public static void assertTruegetAttributeComparision(String objectLocater, String name, String message)
			throws IOException {
		Assert.assertTrue(findElement(objectLocater).getAttribute("value").equalsIgnoreCase(name), message);
	}

	public static void moveToElement(String objectLocator) throws IOException {

		Actions action = new Actions(driver);
		action.moveToElement(findElement(objectLocator)).build().perform();

	}

	public static void moveToElement(WebElement objectLocator) throws IOException {

		Actions action = new Actions(driver);
		action.moveToElement(objectLocator).build().perform();

	}

	public static void moveToElementinLoop(String objectLocator, String objectLocator1) throws IOException {

		Actions action = new Actions(driver);
		do {

			action.click(findElement(objectLocator)).perform();
			;
		} while (!isElementsExist(objectLocator1, 3));

	}

	public static void PressKey() throws IOException {
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys("s").keyUp(Keys.CONTROL).build().perform();
		System.out.println("Done");
	}

	public static void moveToElementAndClick(String objectLocator) throws IOException {

		Actions action = new Actions(driver);
		action.moveToElement(findElement(objectLocator)).click().perform();

	}

	public static void doublemoveToElementAndClick(String objectLocator, String objectLocator1) throws IOException {

		Actions action = new Actions(driver);
		action.moveToElement(findElement(objectLocator)).clickAndHold().moveToElement(findElement(objectLocator1))
				.click().build().perform();

	}

	public static void moveToElementAndClick(WebElement objectLocator) throws IOException {

		Actions action = new Actions(driver);
		action.moveToElement(objectLocator).click().build().perform();

	}

	public static void movedownaction() throws IOException {

		Actions actions = new Actions(driver);
		// Page Down
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		// Page Up
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.UP).perform();

	}

	public static boolean isSelected(WebElement objectLocater) throws IOException {

		return objectLocater.isSelected();

	}

	public static boolean isEnabled(WebElement objectLocater) throws IOException {

		return objectLocater.isSelected();

	}

	public static boolean isSelected(String objectLocater) throws IOException {

		return findElement(objectLocater).isSelected();

	}

	public static void ClickFirstElementInList(String objectLocator) throws IOException, InterruptedException {

		if (IsDimensionOK(objectLocator)) {
			List<WebElement> li = findElements(objectLocator);
			if (!CommonMethod.isSelected(li.get(0))) {
				Thread.sleep(2000);
				li.get(0).click();
			}
		}
	}

	public static void ClickSecondElementInList(String objectLocator) throws IOException {

		List<WebElement> li = findElements(objectLocator);
		if (!CommonMethod.isSelected(li.get(1))) {
			li.get(1).click();
		}
	}

	public static void ClickLastElementInList(String objectLocator) throws IOException {

		List<WebElement> li = findElements(objectLocator);
		if (!CommonMethod.isSelected(li.get(li.size() - 1))) {
			(li.get(li.size() - 1)).click();
		}
	}

	public static void OpenNewTab(String objectLocator) throws IOException {

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		findElement(objectLocator).sendKeys(selectLinkOpeninNewTab);
		@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	//	wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	}

	public static void OpenNewTab(WebElement objectLocator) throws IOException {

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		objectLocator.sendKeys(selectLinkOpeninNewTab);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
	}

	public static void OpenNewTab(WebElement objectLocator, String URL) throws IOException {

		// ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		objectLocator.sendKeys(selectLinkOpeninNewTab);
		// driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");

		// driver.findElement(By.linkText("Team")).sendKeys(selectLinkOpeninNewTab);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		GotoURL(URL);
	}

	public static void OpenNewTabChild(WebElement objectLocator) throws IOException {

		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		objectLocator.sendKeys(selectLinkOpeninNewTab);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.numberOfWindowsToBe(3));
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(2));
	}
	
	public static void switchToParentTabWithoutClose() throws InterruptedException {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
	}


	public static void switchToParentTab() throws InterruptedException {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		for (String handle : tabs) {
			if (!handle.equals(tabs.get(0))) {

				driver.close();
				Thread.sleep(2000);
			}
		}

		driver.switchTo().window(tabs.get(0));
	}

	public static void switchToChildTab() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		for (String handle : tabs) {
			if (!handle.equals(tabs.get(1)) && !handle.equals(tabs.get(0))) {

				driver.close();
			}
		}

		driver.switchTo().window(tabs.get(1));
	}

	public static void checkTextFieldIsNotEmpty(String objectLocator, String Message) throws IOException {

		String textInsideInputBox = CommonMethod.getattributeValue(objectLocator);

		// Check whether input field is blank
		if (textInsideInputBox.isEmpty()) {
			testlog.fail(Message);
		}
	}

	public static void VerifyRadioOrCheckboxSelcted(String objectlocator) throws IOException {
		List<WebElement> checkboxOrRadio = findElements(objectlocator);
		for (WebElement ele : checkboxOrRadio) {
			if (!CommonMethod.isSelected(ele)) {
				negativesoftAssert.fail(objectlocator + ": Radio Or Checkbox is not selected: "  + ", TESTCASE NAME: " + TestCaseName);
			}
		}
	}

	public static void VerifyRadioOrCheckboxSelcted(String objectlocator, String FieldName) throws IOException {

		if (!CommonMethod.isSelected(objectlocator)) {
			negativesoftAssert
					.fail(FieldName + ": Radio Or Checkbox is not selected" + ", TESTCASE NAME: " + TestCaseName);
		}
	}

	public static void VerifyAllCheckboxesChecked() throws IOException {
		List<WebElement> checkbox = findElements("Checkbox");
		System.out.println("Checkbox size: " + checkbox.size());
		testlog.pass("Checking number of Checkboxes on Page - " + checkbox.size());

		for (WebElement check : checkbox) {

			if (!CommonMethod.isSelected(check)) {
				/*
				 * CommonMethod.scrolldowntoElement(check); testlog.pass("Moving to Checkbox");
				 * CommonMethod.click(check); testlog.pass("Checkbox is clicked");
				 */
				System.out.println("All Checkboxes are not checked");
			}
		}
	}

	public static void ClickParticularCheckbox(String objectlocator) throws IOException {
		List<WebElement> checkbox = findElements(objectlocator);
		System.out.println("Checkbox size: " + checkbox.size());
		testlog.pass("Checking number of Checkboxes on Page - " + checkbox.size());

		for (WebElement check : checkbox) {

			do {
				// CommonMethod.scrolldowntoElement(check);
				// testlog.pass("Moving to Checkbox");
				CommonMethod.click(check);
				// testlog.pass("Checkbox is clicked");
			} while (!CommonMethod.isSelected(check));

		}
	}
	
	public static void ClickCheckbox(String objectlocator) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		boolean flag = true;
		do {
			Thread.sleep(2000);
			JavascriptClickElement(objectlocator);
			Thread.sleep(3000);
				System.out.println("Inside");
				if ((System.currentTimeMillis() - startTime) > 60000) {
					flag = false;
					break;
				}
		} while (!CommonMethod.isSelected(objectlocator));
			CommonMethod.assertTruebooleanCondition(flag,"Selecting Checkbox exceeds the timeout");
	}

	public static void ClickCheckbox(WebElement objectLocater) throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		boolean flag = true;
		do {
			Thread.sleep(2000);
			JavascriptClickElement(objectLocater);
			Thread.sleep(3000);
				System.out.println("Inside");
				if ((System.currentTimeMillis() - startTime) > 60000) {
					flag = false;
					break;
				}
		} while (!CommonMethod.isSelected(objectLocater));
			CommonMethod.assertTruebooleanCondition(flag,"Selecting Checkbox exceeds the timeout");
	}

	public static void softAssertEqualsMessage(String Actual, String expected, String message) {
		System.out.println("Actual: " + Actual + "expected: " + expected);
		softAssert.assertEquals(Actual, expected, message + "Actual: " + Actual + "expected: " + expected);
	}

	public static void negativesoftassertPageSource(String expectedtext, String message)
			throws IOException, InterruptedException {

		if (!driver.getPageSource().contains(expectedtext)) {
			captureNegativeAssertScreenshot();
			negativesoftAssert.assertTrue(driver.getPageSource().contains(expectedtext),
					message + " expected code was Not Found: " + expectedtext + ", TESTCASE NAME: " + TestCaseName);
		} else {
			negativesoftAssert.assertTrue(driver.getPageSource().contains(expectedtext),
					message + " expected code was Not Found: " + expectedtext + ", TESTCASE NAME: " + TestCaseName);
		}
	}

	public static void negativesoftassertFieldValid(String ActualText, String ExpectedText, String message)
			throws IOException, InterruptedException {
		System.out.println("ActualText: " + ActualText.replaceAll("\\s+", " ").trim() + ", ExpectedText: " + ExpectedText);
		if (!ActualText.contains(ExpectedText)) {
			captureNegativeAssertScreenshot();
			System.lineSeparator();
			System.lineSeparator();
			negativeFieldSoftAssert.assertTrue(ActualText.replaceAll("\\s+", " ").trim().contains(ExpectedText),
					message + "expected code was Not Found: " + "ActualText: " + ActualText + ", ExpectedText: "
							+ ExpectedText + ", TESTCASE NAME: " + TestCaseName);
		} else {
			negativeFieldSoftAssert.assertTrue(ActualText.contains(ExpectedText),
					message + " expected code was Not Found: " + "ActualText: " + ActualText + ", ExpectedText: "
							+ ExpectedText + ", TESTCASE NAME: " + TestCaseName);
		}
	}

	public static void negativesoftassertFieldValidEquals(String ActualText, String ExpectedText, String message)
			throws IOException, InterruptedException {
		System.out.println("ActualText: " + ActualText + ", ExpectedText: " + ExpectedText);
		if (!ActualText.contains(ExpectedText)) {
			captureNegativeAssertScreenshot();
			negativeFieldSoftAssert.assertTrue(ActualText.equals(ExpectedText),
					message + " expected code was Not Found: " + "ActualText: " + ActualText + ", ExpectedText: "
							+ ExpectedText + ", TESTCASE NAME: " + TestCaseName);
		} else {
			negativeFieldSoftAssert.assertTrue(ActualText.equals(ExpectedText),
					message + " expected code was Not Found: " + "ActualText: " + ActualText + ", ExpectedText: "
							+ ExpectedText + ", TESTCASE NAME: " + TestCaseName);
		}
	}

	public static void softAssertContainsMessage(String Actual, String expected, String message) {
		System.out.println("Actual: " + Actual + "expected: " + expected);
		softAssert.assertTrue(Actual.contains(expected),
				message + " expected code was Not Found: " + "ActualText: " + Actual + ", ExpectedText: " + expected);
	}

	public static void assertTruebooleanCondition(boolean boo, String message) {

		Assert.assertTrue(boo, message);
	}

	public static void assertEqualsmessage(String objectLocator, String expected, String message) throws IOException {

		Assert.assertEquals(getText(objectLocator), expected, message + " expected code was Not Found: " + expected);
	}

	public static void assertEqualsmessageWithoutCase(String Actual, String expected, String message)
			throws IOException {

		Assert.assertEquals(Actual.toLowerCase(), expected.toLowerCase(),
				message + " expected code was Not Found: " + "ActualText: " + Actual + ", ExpectedText: " + expected);
	}

	public static void assertEqualsmessageAttributevalue(String objectLocator, String expected, String message)
			throws IOException {

		Assert.assertEquals(getattributeValue(objectLocator), expected, message);
	}

	public static void assertNotSame(String objectLocator, String expected, String message) throws IOException {
		System.out.println(CommonMethod.getText("ErrorMessage"));
		Assert.assertNotSame((findElement(objectLocator)).getText(), expected, message);
	}

	public static void assertcontainsmessage(String objectLocater, String expected, String message)
			throws IOException, InterruptedException {
		Thread.sleep(2000);
		System.out.println("Actual: " + CommonMethod.getText(objectLocater) + "expected: " + expected);
		testlog.info("Message captured : " + CommonMethod.getText(objectLocater));
		Assert.assertTrue(getText(objectLocater).contains(expected),
				message + " expected code was Not Found: " + expected);
	}

	public static void assertActualContainsExpected(String Actual, String expected) throws IOException {
		System.out.println("Actual: " + Actual + "expected: " + expected);
		Assert.assertTrue(Actual.contains(expected),
				"Did not match --- Actual : " + Actual + " Expected : " + expected);
	}

	public static void assertExpectedContainsActual(String Actual, String expected, String message) throws IOException {
		System.out.println("Actual: " + Actual + "expected: " + expected);
		Assert.assertTrue(expected.contains(Actual),
				message + " expected code was Not Found: " + "ActualText: " + Actual + ", ExpectedText: " + expected);
	}

	public static void assertstringcontainsmessage(String actual, String expected, String message) throws IOException {

		Assert.assertTrue(actual.contains(expected), message + " expected code was Not Found: " + expected);
	}

	public static void assertcontainsattributevalue(String objectLocator, String expected, String message)
			throws IOException {

		System.out.println((findElement(objectLocator)).getAttribute("value"));
		Assert.assertTrue((findElement(objectLocator)).getAttribute("value").contains(expected),
				message + " expected code was Not Found: " + expected);
	}

	public static void assertisElementPresentTrue(String objectLocator, String message) throws IOException {
		CommonMethod.WaitUntilNumberOfElementToBePresentWithException(objectLocator,1);
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		boolean boo = findElements(objectLocator).size() > 0;
		System.out.println(boo);
		Assert.assertTrue(boo, message);

	}

	public static void assertisElementPresentTrueHandled(String objectLocator, String message, String SheetName)
			throws IOException, InterruptedException {
		try {
			// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			boolean boo = findElements(objectLocator).size() > 0;
			System.out.println(boo);
			Assert.assertTrue(boo, message);
			// data.setCellData(SheetName, "Status", rownumber, "PASS");
		} catch (AssertionError err) {
			testlog.fail(err.toString());
			captureScreenshot();
			System.out.println("Inside Catch 3");
			// data.setCellData(SheetName, "Status", rownumber, "FAIL");
			/*
			 * switchToParentTab(); testlog.info("Switching back to parent tab");
			 */
		}
	}

	public static void assertisElementPresentTrueHandled(String objectLocator, String message, String SheetName,
			int rowNum) throws IOException {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			boolean boo = findElements(objectLocator).size() > 0;
			System.out.println(boo);
			Assert.assertTrue(boo, message);
			// data.setCellData(SheetName, "Status", rowNum, "PASS");
		} catch (AssertionError err) {
			testlog.fail(err.toString());
			// captureScreenshotGeneric(rowNum);
			System.out.println("Inside Catch 3");
			// data.setCellData(SheetName, "Status", rowNum, "FAIL");
			/*
			 * switchToParentTab(); testlog.info("Switching back to parent tab");
			 */
		}
	}

	public static void assertisElementPresentTrue(String objectLocator, String message, int time) throws IOException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		boolean boo = findElements(objectLocator).size() > 0;
		System.out.println(boo);
		Assert.assertTrue(boo, message);

	}

	public static boolean IsElementPresentTrue(String objectLocator) throws IOException {

		boolean boo = (findElement(objectLocator) != null);
		System.out.println(boo);
		return boo;

	}

	public static boolean isElementsExist(String objectLocator, int TimeInSec) throws IOException {
		boolean boo;
		long startTime = System.currentTimeMillis();
		long endTime = TimeInSec * 1000;
		do {
			boo = findElementsCustom(objectLocator).size() > 0;
			System.out.println(boo);
		} while (!(boo || !((System.currentTimeMillis() - startTime) < endTime)));
		return boo;

	}

	public static boolean isElementsExistRaw(String xpath, int TimeInSec) throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeInSec));
		boolean boo = driver.findElements(By.xpath(xpath)).size() > 0;
		System.out.println(boo);
		return boo;
	}

	public static void assertisElementPresentFalse(String objectLocator, String message) throws IOException {

		boolean boo = findElements(objectLocator).size() > 0;
		System.out.println(boo);
		Assert.assertFalse(boo, message);

	}

	public static void ListDropdownValues(String objectLocator) throws IOException, InterruptedException {

		Select se = new Select(findElement(objectLocator));
		List<WebElement> ele = se.getOptions();
		for (WebElement list : ele) {
			System.out.println(list.getText());
			testlog.info("Rating System available : " + list.getText());
		}

	}

	public static void selectdropdownrandomCondition(String objectLocator, String Value)
			throws IOException, InterruptedException {

		Select se = new Select(findElement(objectLocator));
		List<WebElement> ele = se.getOptions();
		int n = new Random().nextInt(ele.size());
		if (n == 0) {
			n = n + 1;
		}

		se.selectByIndex(n);
		Thread.sleep(2000);
		WebElement option = se.getFirstSelectedOption();
		System.out.println(option.getText());

		if (option.getText().contains(Value)) {
			selectdropdownrandomCondition(objectLocator, Value);
		}
	}

	public static int getdropdownSize(String objectLocator) throws IOException, InterruptedException {

		Select se = new Select(findElement(objectLocator));
		List<WebElement> ele = se.getOptions();
		return ele.size();
	}

	public static void selectdropdownVisibletextwithoutdelay(String objectLocator, String text) throws IOException {
		Select se = new Select(findElement(objectLocator));
		se.selectByVisibleText(text);
	}

	public static void selectdropdownIndex(String objectLocator, int value) throws IOException {

		Select se = new Select(findElement(objectLocator));
		se.selectByIndex(value);

	}

	public static void selectdropdownWebelement(WebElement objectLocator, String value) throws IOException {

		Select se = new Select(objectLocator);
		se.selectByVisibleText(value);

	}

	public static void selectdropdownWebelementByValue(WebElement objectLocator, String value) throws IOException {

		Select se = new Select(objectLocator);
		se.selectByValue(value);

	}

	public static String getSelectedDropdownValue(String objectLocator) throws IOException {

		Select select = new Select(findElement(objectLocator));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		return defaultItem;

	}

	public static String getSelectedDropdownAttribute(String objectLocator) throws IOException {
		Select select = new Select(findElement(objectLocator));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getAttribute("value");
		return defaultItem;

	}

	public static void verifySelectedDropdownText(String objectLocator, String value, String message) throws IOException, InterruptedException {

		Select select = new Select(findElement(objectLocator));
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		negativesoftassertFieldValidEquals(defaultItem, value, message);
		//Assert.assertEquals(defaultItem, value, "Dropdown selected value is not correct");

	}

	public static void switchToParentFrame() {

		driver.switchTo().parentFrame();
	}

	public static void switchToDefaultContent() {

		driver.switchTo().parentFrame();
	}

	public static void switchToFrame(String objectLocator) throws IOException {

		driver.switchTo().frame(CommonMethod.findElement(objectLocator));
	}

	public static void ClickRandomWebElement(String objectLocator) throws IOException {

		List<WebElement> options = CommonMethod.findElements(objectLocator);
		Random random = new Random();
		int index = random.nextInt(options.size());
		// options.get(index).click();
		scrolldowntoElement(options.get(index));
		WaitUntilClickble(objectLocator, 10);
		if (!CommonMethod.isSelected(options.get(index))) {
			options.get(index).click();
		}
	}

	public static void ClickRandomWebElementWithIterationCount(String objectLocator, int NumberOfTimes)
			throws IOException {

		List<WebElement> options = CommonMethod.findElements(objectLocator);
		for (int i = 0; i <= NumberOfTimes; i++) {
			System.out.println(NumberOfTimes);
			Random random = new Random();
			int index = random.nextInt(options.size());
			// options.get(index).click();
			scrolldowntoElement(options.get(index));
			WaitUntilClickble(objectLocator, 10);
			if (!CommonMethod.isSelected(options.get(index)) && !CommonMethod.isEnabled(options.get(index))) {
				options.get(index).click();
			} else {
				NumberOfTimes = NumberOfTimes + 1;
			}
		}
	}

	// Is displayed Method (Assertion)
	public static void Isdisplayed(String objectLocater, String message) throws IOException {

		Assert.assertTrue(findElement(objectLocater).isDisplayed(), message);

	}

	public static boolean Isdisplayed(String objectLocater, int Timeout) throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Timeout));
		Boolean boo = null;
		try {
			boo = findElement(objectLocater).isDisplayed();
			System.out.println(boo);

		}

		catch (Exception e) {
			System.out.println("Not displayed");
		}
		return boo;

	}

	public static void IsEnabled(String objectLocater, String message) throws IOException {

		Assert.assertTrue(findElement(objectLocater).isEnabled(), message);

	}

	public static boolean IsEnabled(String objectLocater) throws IOException {

		Boolean boo = findElement(objectLocater).isEnabled();
		System.out.println(boo);
		return boo;

	}

	public static boolean IsDimensionOK(String objectLocater) throws IOException {

		Dimension d = findElement(objectLocater).getSize();
		boolean isVisible = (d.getHeight() > 0 && d.getWidth() > 0);
		System.out.println(isVisible);
		return isVisible;

	}

	public static void assertcontentPageSource(String expectedtext, String message) {

		Assert.assertTrue(driver.getPageSource().contains(expectedtext), message);

	}

	public static void assertcontentPageSourceFalse(String expectedtext, String message) {

		Assert.assertFalse(driver.getPageSource().contains(expectedtext), message);

	}

	public static void assertcontainshandledPageSource(String expectedtext, String message) {
		try {

			Assert.assertTrue(driver.getPageSource().contains(expectedtext), message);
		} catch (AssertionError err) {
			testlog.fail(err.toString());
			System.out.println("Verification Failed");

			/*
			 * switchToParentTab(); testlog.info("Switching back to parent tab");
			 */
		}
	}

	public static void assertcontentPageSource(String expectedtext, String message, String SheetName) {
		try {

			Assert.assertTrue(driver.getPageSource().toLowerCase().contains(expectedtext.toLowerCase()), message);
		} catch (AssertionError err) {
			testlog.fail(err.toString());
			System.out.println("Inside Catch 2");
			// data.setCellData(SheetName, "Status", rownumber, "FAIL");
			/*
			 * switchToParentTab(); testlog.info("Switching back to parent tab");
			 */
		}
	}

	/*
	 * public static void assertFalse(Boolean boo, String message, String SheetName,
	 * String CreditName, String URL) { try {
	 * 
	 * System.out.println("Current RatingSystem : " + TestNGTestName);
	 * Assert.assertFalse(boo, message); System.out.println("Current Rownumber : " +
	 * rownumber); data.setCellData(SheetName, "RatingSystem", rownumber,
	 * TestNGTestName); data.setCellData(SheetName, "CreditName", rownumber,
	 * CreditName); data.setCellData(SheetName, "RedirectedCreditLink", rownumber,
	 * URL); data.setCellData(SheetName, "URLStatus", rownumber, "PASS"); } catch
	 * (AssertionError err) { testlog.fail(err.toString());
	 * System.out.println("Inside Catch 1"); System.out.println("Fail Rownumber : "
	 * + rownumber); data.setCellData(SheetName, "RatingSystem", rownumber,
	 * TestNGTestName); data.setCellData(SheetName, "CreditName", rownumber,
	 * CreditName); data.setCellData(SheetName, "RedirectedCreditLink", rownumber,
	 * URL); data.setCellData(SheetName, "URLStatus", rownumber, "URLBroken");
	 * 
	 * } }
	 */
	public static void verifycontentPageSource(String expectedtext, String message) {
		try {
			Verify.verify(driver.getPageSource().contains(expectedtext), message);
		} catch (Exception e) {
			System.out.println("Verification Failed");
			testlog.fail(e.toString());
		}
	}

	public static boolean verifycontentPageSource(String expectedtext) {

		Boolean boo = driver.getPageSource().contains(expectedtext);
		return boo;

	}

	public static void verifycontentPageSource(String[] expectedtext) {
		int i = 1;
		for (String str : expectedtext) {
			try {

				Verify.verify(driver.getPageSource().contains(str), str + "  doesn't exist on page");
			}

			catch (Exception e) {
				System.out.println(i);
				i = i + 1;
				testlog.fail(e.toString());
			}
		}
	}

	public static String getCurrentUrl() {
		System.out.println(driver.getCurrentUrl());
		String getCurrentUrl = driver.getCurrentUrl();
		return getCurrentUrl;
	}

	public static void assertcurrentUrl(String expectedUrl, String message) {
		System.out.println(driver.getCurrentUrl());
		System.out.println(expectedUrl);
		Assert.assertTrue(driver.getCurrentUrl().equals(expectedUrl), message);

	}

	public static void CheckExternalLink(String objectLink, String expectedUrl, String message)
			throws IOException, InterruptedException {

		moveToElement(objectLink);
		Thread.sleep(1000);
		click(objectLink);
		Thread.sleep(3000);
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, message);

	}

	public static void CheckExternalLinkMiddleElements(String objectLink, String expectedUrl, String message)
			throws IOException, InterruptedException {

		scrolldowntoElement(objectLink);
		scrollUp();
		click(objectLink);
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, message);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		Thread.sleep(3000);

	}

	public static void CheckExternalLinkNewTab(String objectLink, String expectedUrl, String message)
			throws IOException, InterruptedException {

		moveToElement(objectLink);
		Thread.sleep(1000);
		click(objectLink);
		Thread.sleep(3000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, message);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		Thread.sleep(3000);

	}

	public static void SwichToNewWindow() throws IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windows.get(1));
	}

	public static void switchToParentWindow() {
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		ArrayList<String> windows = new ArrayList<String>(driver.getWindowHandles());

		for (String handle : windows) {
			if (!handle.equals(windows.get(0))) {

				driver.close();
			}
		}

		driver.switchTo().window(windows.get(0));
	}

	public static void JavascriptClickElement(String Objectlocator) throws IOException {
		WebElement ele = findElement(Objectlocator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// Objectlocator);
	}

	public static void JavascriptRemoveWebElement() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("return document.getElementsByTagName('h2').remove();");
	}

	public static void JavascriptRemoveWebElementByClassName() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript(
				"return document.getElementsByClassName('ml-64 pr-72 shadow bg-white fixed z-10 left-0 mt-16 top-0 w-full py-4 px-11')[0].remove();");
	}

	public static void scrolldowntoLast() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scrollUp() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollTo(0, 0)", "");
	}

	public static void scrollDown() {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0,1500)", "");
	}

	//

	public static void scrolldowntoElement(String objectLocater) throws IOException {

		// scrolldowntoLast();

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", findElement(objectLocater));
	}

	public static void scrolldowntoElement(WebElement objectLocater) throws IOException {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", objectLocater);
	}

	public static String FileNameDownloaded(String downloadPath, int Time) throws InterruptedException {
		Thread.sleep(Time);
		String Filename = null;
		File file = new File(downloadPath);
		String[] fileList = file.list();
		for (String name : fileList) {
			System.out.println("Downloaded File name : " + name);
			Filename = name;
		}
		return Filename;
	}

	public static boolean isFileExists(String downloadPath) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].exists())
				return flag = true;
		}

		return flag;
	}

	public static boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().equals(fileName))
				return flag = true;
		}

		return flag;
	}

	public static boolean isFileDownloaded1(String downloadPath, String fileName) throws InterruptedException {
		boolean flag = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();
		System.out.println(dir_contents.length);

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().contains(fileName)) {
				File file = dir_contents[i];
				long lastModified;

				while (true) {
					lastModified = file.lastModified();
					System.out.println(lastModified);
					Thread.sleep(4000);
					System.out.println(file.lastModified());
					if (file.lastModified() == 0)
						Thread.sleep(1000);
					break;
				}

				/*
				 * lastModified = file.lastModified(); latestModified = file.lastModified();
				 * System.out.println(lastModified); System.out.println(latestModified); while
				 * (latestModified == lastModified) {
				 * 
				 * Thread.sleep(3000); latestModified = file.lastModified();
				 * System.out.println(lastModified); System.out.println(latestModified); if
				 * (lastModified != latestModified) Thread.sleep(20000); break;
				 * 
				 * }
				 */
				return flag = true;

			}

		}

		return flag;
	}

	public static int FileCountInsideZip(String FileName) {
		int numberOfEntries = 0;
		try {
			int count = 0;
			ZipFile zipFile = new ZipFile(downloadPath + File.separator + FileName);
			System.out.println(zipFile);

			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				System.out.println(entry.getName());
				if (!entry.isDirectory()) {
					count = count + 1;
				}

			}

			numberOfEntries = count;

			System.out.println("There are ");
			System.out.print(numberOfEntries);
			System.out.print(" entries in zip file :");
			System.out.print(zipFile.getName());

			zipFile.close();

		}

		catch (Exception e) {
			System.out.println("Error opening zip file" + e.getMessage());
		}
		return numberOfEntries;
	}

	public static void IsFilePresentInsideZip() throws IOException {
		int numberOfEntries = 0;
		File f = new File(downloadPath); // use here your file directory path
		String[] allFiles = f.list();
		for (@SuppressWarnings("unused") String Files : allFiles) {

			int count = 0;
			ZipFile zipFile = new ZipFile(
					downloadPath + File.separator + "1000005741-Design_and_Construction_Preliminary_Review.zip");
			System.out.println(zipFile);

			Enumeration<? extends ZipEntry> entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = entries.nextElement();
				System.out.println(entry.getName());

				if (entry.isDirectory()) {

					File dir = new File(entry.getName());
					File[] dir_contents = dir.listFiles();
					if (dir_contents != null) {
						System.out.println("HIIIII");
						for (int i = 0; i < dir_contents.length; i++) {
							if (dir_contents[i].getName().contains("CreditForm1.pdf")) {
								Assert.assertTrue(true);
							} else {
								Assert.fail("No credit form found");
							}
						}
					}
				}

				if (!entry.isDirectory()) {
					count = count + 1;

				}
			}
			numberOfEntries = count;

			System.out.println("There are ");
			System.out.print(numberOfEntries);
			System.out.print(" entries in zip file :");
			System.out.print(zipFile.getName());

			zipFile.close();
		}
	}

	public static boolean isFileDownloaded() throws IOException, InterruptedException {
		Boolean flag = true;
		long startTime = System.currentTimeMillis();
		String folderName = downloadPath;
		String fileName;
		File[] listOfFiles;
		listOfFiles = new File(folderName).listFiles();
		//wait for file download to present
		do {
            Thread.sleep(3000);
            listOfFiles = new File(folderName).listFiles();
            if (!((System.currentTimeMillis() - startTime) < 60000) ) {
                break;
            }
        } while (!(listOfFiles.length > 0));
		//Wait for file to be Downloaded
		if (listOfFiles.length > 0) {
			for (File file : listOfFiles) {
				fileName = file.getName().toLowerCase();
				if (fileName.contains("crdownload")) {
					Thread.sleep(2000);
				}
				else {
					break;
				}
				if ((System.currentTimeMillis() - startTime) >= 90000) {
					flag = false;
					break;
				}
				listOfFiles = new File(folderName).listFiles();
			}
		} else {
			flag = false;
		}
		return flag;
	}

	public static boolean isFileDownloaded1() throws IOException {
		// Download Folder Path
		String folderName = downloadPath;

		// Array to Store List of Files in Directory
		File[] listOfFiles;

		// Store File Name
		String fileName;

		// Consider file is not downloaded
		boolean fileDownloaded = false;
		long startTime = Instant.now().toEpochMilli();

		// Time to wait for download to finish
		long waitTime = startTime + 60000;

		// while current time is less than wait time
		while (Instant.now().toEpochMilli() < waitTime) {
			// get all the files of the folder
			listOfFiles = new File(folderName).listFiles();

			// iterate through each file
			for (File file : listOfFiles) {
				// get the name of the current file
				fileName = file.getName().toLowerCase();

				if (!fileName.contains("crdownload")) {
					// File Found
					fileDownloaded = true;
					break;
				}
			}
			// File Found Break While Loop
			if (fileDownloaded)
				break;
		}
		return fileDownloaded;
	}

	public static void VerifyDownloadWithFileName(String filename) throws InterruptedException {

		Assert.assertTrue(isFileDownloaded(downloadPath, filename), "Failed to download Expected document");
	}

	public static void VerifyFileDownloadWithExtension(String filename, String extension) throws InterruptedException {

		Assert.assertTrue(isFileDownloaded(downloadPath, filename), "Failed to download Expected document");
	}

	public static String extractPDFContent(String PDFFile) throws IOException {

		PdfReader reader = new PdfReader(PDFFile);
		StringBuffer sb = new StringBuffer();
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);
		TextExtractionStrategy strategy;
		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			sb.append(strategy.getResultantText());
		}
		reader.close();
		return sb.toString();

	}

	public static String setDateFormat(String Dateformat) {

		Format formatter = new SimpleDateFormat(Dateformat);
		String date = formatter.format(new Date());
		return date;
	}

	public static String setDateFormatFuture(String Dateformat, int FutureDays) {

		Format formatter = new SimpleDateFormat(Dateformat);
		formatter.format(new Date());
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, FutureDays);
		String newDate = formatter.format(c.getTime());
		return newDate;
	}

	public static List<String> fetchTableData(String objectLocator) throws IOException {

		List<String> value = new ArrayList<String>();
		CommonMethod.WaitUntilNumberOfElementToBePresentMoreThan(objectLocator, 0);
		WebElement table = (findElement(objectLocator));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				System.out.println("content >>   " + cell.getText());
				// testlog.info("content >> " + cell.getText());
				value.add(cell.getText());
			}
		}
		return value;

	}

	public static List<String> fetchTableHeaders(String objectLocator) throws IOException {

		List<String> value = new ArrayList<String>();

		WebElement table = (findElement(objectLocator));

		// Now get all the TR elements from the table
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		// And iterate over them, getting the cells
		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("th"));
			for (WebElement cell : cells) {
				System.out.println("content >>   " + cell.getText());
				value.add(cell.getText());
			}
		}
		return value;

	}

	public static String takeScreenshot(String methodname) {
		try {

			TakesScreenshot ts = (TakesScreenshot) driver;
			File Source = ts.getScreenshotAs(OutputType.FILE);
			String Dest = screenshotfile + methodname + ".png";
			File Destination = new File(Dest);
			FileUtils.copyFile(Source, Destination);
			return Dest;
		}

		catch (Exception e) {

			System.out.println("Exception Taking screenshot" + e.getMessage());
			return e.getMessage();
		}

	}

	public static String randomNumber() {

		int random_num = 1;
		Random t = new Random();

		// random integers in [1000, 800000]
		random_num = (t.nextInt(800000));
		ProgramID = String.valueOf(random_num);

		System.out.println(ProgramID);
		return ProgramID;

	}

	public static String randomNumber(int lastnumber) throws IOException, InterruptedException {

		int random_num = 1;
		Random t = new Random();

		int lowest = 1000;
		random_num = (t.nextInt(lastnumber - lowest) + lowest);
		ProgramID = String.valueOf(random_num);

		return ProgramID;

	}

	public static String randomNumberBetweenRanges(int firstNum, int LastNum) throws IOException, InterruptedException {

		Random r = new Random();
		int low = firstNum;
		int high = LastNum;
		int result = r.nextInt(high - low) + low;
		ProgramID = String.valueOf(result);

		return ProgramID;

	}

	public static String removeSpecialCharFromNumber(String str) throws IOException, InterruptedException {

		ProgramID = str.replaceAll("[-+.^:,]", "");

		return ProgramID;

	}

	public static String randomnumberBNone(String Url) throws IOException, InterruptedException {

		int random_num = 1;
		Random t = new Random();

		// random integers in [1000, 800000]
		random_num = (t.nextInt(800000));
		ProgramID = String.valueOf(random_num);

		System.out.println(ProgramID);
		Thread.sleep(1000);

		File file = new File(Url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("BuildingNone" + " " + ProgramID);
		bw.close();
		return ProgramID;

	}

	public static String randomnumber(String Url) throws IOException, InterruptedException {

		int random_num = 1;
		Random t = new Random();

		// random integers in [1000, 800000]
		random_num = (t.nextInt(800000));
		ProgramID = String.valueOf(random_num);

		System.out.println(ProgramID);
		Thread.sleep(1000);

		File file = new File(Url);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("MachineTestProject" + " " + ProgramID);
		bw.close();
		return ProgramID;

	}

	public static String filereadID(String url) throws IOException {

		FileReader inputFile = new FileReader(url);

		// Instantiate the BufferedReader Class
		BufferedReader bufferReader = new BufferedReader(inputFile);

		// Variable to hold the one line data

		String text;
		// Read file line by line and print on the console
		while ((text = bufferReader.readLine()) != null) {

			fetchedID = text;
			// System.out.println(CommonMethod.ProgramID);

		}

		// Close the buffer reader
		bufferReader.close();
		return fetchedID;
	}

	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
			wait.until(expectation);
		} catch (Exception e) {
			System.out.println("Timeout waiting for Page Load Request to complete.");

		}
	}

	public static WebElement WaitUntilPresence(String objectlocator, int TimeinSeconds) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.name(objectvalue))));

		case "class":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static Boolean WaitUntilStaleness(WebElement objectlocator, int TimeinSeconds) throws IOException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		return wait.until(ExpectedConditions.stalenessOf(objectlocator));

	}

	/***** Added on Nov 27 11.30 AM *****/
	public static Boolean WaitUntilInVisibility(String objectlocator, int TimeinSeconds) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(objectvalue))));

		case "class":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}

	}

	public static WebElement WaitUntilVisibility(String objectlocator, int TimeinSeconds) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(objectvalue))));

		case "xpath":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(objectvalue))));

		case "name":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(objectvalue))));

		case "class":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(objectvalue))));

		case "tagname":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(objectvalue))));

		case "css":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(objectvalue))));

		case "linkText":

			return (wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(objectvalue))));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static WebElement WaitUntilClickble(String objectlocator, int TimeinSeconds) throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.id(objectvalue))));

		case "xpath":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.xpath(objectvalue))));

		case "name":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.name(objectvalue))));

		case "class":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.className(objectvalue))));

		case "tagname":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.tagName(objectvalue))));

		case "css":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(objectvalue))));

		case "linkText":

			return (wait.until(ExpectedConditions.elementToBeClickable(By.linkText(objectvalue))));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static Boolean WaitUntilAttributeByValueLoaded(String objectlocator, String Attribute, String AttributeValue,
			int TimeinSeconds) throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.attributeToBe(By.xpath(objectvalue), Attribute, AttributeValue)));

		case "xpath":

			return (wait.until(ExpectedConditions.attributeToBe(By.xpath(objectvalue), Attribute, AttributeValue)));

		case "name":

			return (wait.until(ExpectedConditions.attributeToBe(By.name(objectvalue), Attribute, AttributeValue)));

		case "class":

			return (wait.until(ExpectedConditions.attributeToBe(By.className(objectvalue), Attribute, AttributeValue)));

		case "tagname":

			return (wait.until(ExpectedConditions.attributeToBe(By.tagName(objectvalue), Attribute, AttributeValue)));

		case "css":

			return (wait
					.until(ExpectedConditions.attributeToBe(By.cssSelector(objectvalue), Attribute, AttributeValue)));

		case "linkText":

			return (wait.until(ExpectedConditions.attributeToBe(By.linkText(objectvalue), Attribute, AttributeValue)));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static Boolean WaitUntilTextToBePresentInLocator(String objectlocator, String ValueToBePresent,
			int TimeinSeconds) throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait
					.until(ExpectedConditions.textToBePresentInElementLocated(By.id(objectvalue), ValueToBePresent)));

		case "xpath":

			return (wait.until(
					ExpectedConditions.textToBePresentInElementLocated(By.xpath(objectvalue), ValueToBePresent)));

		case "name":

			return (wait
					.until(ExpectedConditions.textToBePresentInElementLocated(By.name(objectvalue), ValueToBePresent)));

		case "class":

			return (wait.until(
					ExpectedConditions.textToBePresentInElementLocated(By.className(objectvalue), ValueToBePresent)));

		case "tagname":

			return (wait.until(
					ExpectedConditions.textToBePresentInElementLocated(By.tagName(objectvalue), ValueToBePresent)));

		case "css":

			return (wait.until(
					ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(objectvalue), ValueToBePresent)));

		case "linkText":

			return (wait.until(
					ExpectedConditions.textToBePresentInElementLocated(By.linkText(objectvalue), ValueToBePresent)));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static List<WebElement> WaitUntilNumberOfElementToBePresent(String objectlocator, int Number)
			throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.id(objectvalue), Number)));

		case "xpath":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(objectvalue), Number)));

		case "name":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.name(objectvalue), Number)));

		case "class":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.className(objectvalue), Number)));

		case "tagname":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.tagName(objectvalue), Number)));

		case "css":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(objectvalue), Number)));

		case "linkText":

			return (wait.until(ExpectedConditions.numberOfElementsToBe(By.linkText(objectvalue), Number)));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static List<WebElement> WaitUntilNumberOfElementToBePresentLessThan(String objectlocator, int Number)
			throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id(objectvalue), Number)));

		case "xpath":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.xpath(objectvalue), Number)));

		case "name":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.name(objectvalue), Number)));

		case "class":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.className(objectvalue), Number)));

		case "tagname":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.tagName(objectvalue), Number)));

		case "css":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(objectvalue), Number)));

		case "linkText":

			return (wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.linkText(objectvalue), Number)));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static List<WebElement> WaitUntilNumberOfElementToBePresentMoreThan(String objectlocator, int Number)
			throws IOException {

		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
		Properties OR = new Properties();
		FileInputStream fp = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/resources/ObjectLocator.properties");
		OR.load(fp);

		String objecttypeandvalues = OR.getProperty(objectlocator);

		String[] splits = objecttypeandvalues.split("~");
		String objecttype = splits[0];

		objectvalue = splits[1];
		printlogs();
		switch (objecttype) {

		case "id":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id(objectvalue), Number)));

		case "xpath":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(objectvalue), Number)));

		case "name":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.name(objectvalue), Number)));

		case "class":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className(objectvalue), Number)));

		case "tagname":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName(objectvalue), Number)));

		case "css":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(objectvalue), Number)));

		case "linkText":

			return (wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.linkText(objectvalue), Number)));
		default:

			return null;
		}
		// By css = findElement(objectlocator);

	}

	public static WebElement WaitUntilClickble(WebElement objectlocator, int TimeinSeconds) throws IOException {
		JSWaiter.waitAllRequest();
		waitForPageLoaded();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeinSeconds));

		return (wait.until(ExpectedConditions.elementToBeClickable(objectlocator)));

	}

	public static void displayhiddenElement(String objectLocator) throws IOException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'display:block !important;')", findElement(objectLocator));
	}

	public static void hideElement(String objectLocator) throws IOException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'display:none')", findElement(objectLocator));
	}

	public static void setClipboardData(String string) {
		// StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public static void removeReadOnly(String id) {

		((JavascriptExecutor) driver)
				.executeScript("document.getElementById('" + id + "').removeAttribute('readonly',0);");

	}

	public static WebDriverWait waitSec(WebDriver driver, int sec) {
		return new WebDriverWait(driver, Duration.ofSeconds(sec));
	}

	public static File waitToDownloadFile(WebDriver driver, int sec, String fileName) {
		String filePath = downloadPath + fileName;
		waitSec(driver, 30).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (Files.exists(Paths.get(filePath))) {
					System.out.println("Downloading " + filePath + " finished.");
					return true;
				} else {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println("Downloading " + filePath + " not finished yet.");
					}
				}
				return false;
			}
		});
		File downloadedFile = new File(filePath);
		return downloadedFile;
	}

	/*
	 * public static void waitUntilElementCount (WebDriver driver) { waitSec(driver,
	 * 30).withMessage("element count didn't match after timeout").until((
	 * ExpectedCondition<Boolean>) d -> d.findElements(By.id("foo").size() > 0)); }
	 */
	public static String getDownloadedDocumentName(String fileExtension) {
		String downloadedFileName = null;
		boolean valid = true;
		boolean found = false;

		// default timeout in seconds
		long timeOut = 300;
		try {
			Path downloadFolderPath = Paths.get(downloadPath);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			downloadFolderPath.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
			long startTime = System.currentTimeMillis();
			do {
				WatchKey watchKey;
				watchKey = watchService.poll(timeOut, TimeUnit.SECONDS);
				long currentTime = (System.currentTimeMillis() - startTime) / 1000;
				if (currentTime > timeOut) {
					System.out.println("Download operation timed out.. Expected file was not downloaded");
					return downloadedFileName;
				}

				for (WatchEvent<?> event : watchKey.pollEvents()) {
					WatchEvent.Kind<?> kind = event.kind();
					if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
						String fileName = event.context().toString();
						System.out.println("New File Created:" + fileName);
						if (fileName.endsWith(fileExtension) && fileName.length() > 10) {
							downloadedFileName = fileName;
							System.out.println(
									"Downloaded file found with extension " + fileExtension + ". File name is " +

											fileName);
							Thread.sleep(500);
							found = true;
							break;
						}
					}
				}
				if (found) {
					return downloadedFileName;
				} else {
					currentTime = (System.currentTimeMillis() - startTime) / 1000;
					if (currentTime > timeOut) {
						System.out.println("Failed to download expected file");
						return downloadedFileName;
					}
					valid = watchKey.reset();
				}
			} while (valid);
		}

		catch (InterruptedException e) {
			System.out.println("Interrupted error - " + e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("Download operation timed out.. Expected file was not downloaded");
		} catch (Exception e) {
			System.out.println("Error occured - " + e.getMessage());
			e.printStackTrace();
		}
		return downloadedFileName;
	}

	public static String getAlphaNumericString(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdeghijklmnopqrstuvxyz";

		// String SpCharString = "Here are a few other common escape codes: for double
		// quotation marks, Â© for the copyright symbol, Â® for the registered `
		// trademark
		// ~ symbol, Â¢ for the cent sign, Â° for the degree sign, Â¶ for the paragraph
		// symbol, and Ã· for the division 6:9 symbol.Remember that $ (browsers ignore)
		// extra spaces in your HTML. You've learned how to use the @formatted # text
		// tag ^ pair to get around this, but that can be $cumbersome if you want just
		// an extra space or two. In these cases, use the escape character for a
		// non-breaking space. To create an extra space in your HTML, type";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		// sb.append(SpCharString);
		return sb.toString();
	}

	public static void clear(WebElement objectLocater) throws IOException {
		objectLocater.clear();

	}

	public static void sendKeys(WebElement objectLocater, String value) throws IOException {
		objectLocater.sendKeys(value);
	}

	public static String randomNumber1To99() {
		int max = 100;
		int min = 1;
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		String number = String.valueOf(randomNum);
		return number;
	}

	public static boolean Isdisplayed(WebElement objectLocater) throws IOException {

		Boolean boo = objectLocater.isDisplayed();
		return boo;

	}

	public static boolean Isdisplayed(String objectLocater) throws IOException {

		Boolean boo = findElement(objectLocater).isDisplayed();
		return boo;

	}

	public static void selectdropdownbyIndex(WebElement objectLocator, int index) throws IOException {
		Select se = new Select(objectLocator);
		List<WebElement> ele = se.getOptions();
		System.out.println(ele.size());
		if (ele.size() > 1) {
			se.selectByIndex(index);
		} else {
			se.selectByIndex(0);
		}

	}

	public static String getSelectedDropdownValue(WebElement objectLocator) throws IOException {

		Select select = new Select(objectLocator);
		WebElement option = select.getFirstSelectedOption();
		String defaultItem = option.getText();
		return defaultItem;

	}

	public static void assertisElementPresentFalse(String objectLocator, String message, int TimeInSec)
			throws IOException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeInSec));
		boolean boo = findElements(objectLocator).size() > 0;
		System.out.println(boo);
		Assert.assertFalse(boo, message);

	}

	public static int ElementSize(String objectLocator) throws IOException {
		int size = findElements(objectLocator).size();
		return size;
	}

	public static String getCurrentDateTime() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy:HH.mm.ss");
		return formatter.format(currentDate.getTime());
	}

	public static String getCurrentDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
		return formatter.format(currentDate.getTime());
	}

	public static WebElement SelectRandomfromList(String objectLocator, int Startindex, int Endindex)
			throws IOException, NumberFormatException, InterruptedException {
		int index = Integer.parseInt(randomNumberBetweenRanges(Startindex, Endindex));
		List<WebElement> ele = CommonMethod.findElements(objectLocator);
		return ele.get(index);
	}

	public static void clearAndSendKey(String objectLocator, String value) throws IOException {

		findElement(objectLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"), value);
		System.out.println("Clear the input and updated the value");
	}

	public static boolean verifyTextPresentInElement(String Actual, String expected, String message) throws Exception {
		boolean flag = false;
		if (Actual.contains(expected)) {
			Assert.assertTrue(Actual.contains(expected), message);
			testlog.info(message);
			System.out.println("matched");
			flag = true;
		} else {
			testlog.fail("Did not match --- Actual : " + Actual + " Expected : " + expected);
			System.out.println("Not matching");
		}
		return flag;
	}

	public static void clickListWebelementFromIndex(String ObjectLocator, int Index)
			throws IOException, InterruptedException {
		List<WebElement> ele;
		WebElement ele1;
		ele = CommonMethod.findElements(ObjectLocator);
		ele1 = ele.get(Index);
		do {
			CommonMethod.WaitUntilClickble(ele1, 60);
			click(ele1);
			Thread.sleep(2000);
		} while (!CommonMethod.isSelected(ele1));

	}

	public static void clickOnListWebelementFromIndex(String ObjectLocator, int Index)
			throws IOException, InterruptedException {
		List<WebElement> ele;
		WebElement ele1;
		ele = CommonMethod.findElements(ObjectLocator);
		ele1 = ele.get(Index);
		CommonMethod.WaitUntilClickble(ele1, 60);
		click(ele1);
	}

	public static void declickListWebelementFromIndex(String ObjectLocator, int Index)
			throws IOException, InterruptedException {
		List<WebElement> ele;
		WebElement ele1;
		ele = CommonMethod.findElements(ObjectLocator);
		ele1 = ele.get(Index);
		do {
			CommonMethod.WaitUntilClickble(ele1, 60);
			click(ele1);
			Thread.sleep(2000);
		} while (CommonMethod.isSelected(ele1));

	}

	public static void clickListWebelementFromRange(String ObjectLocator, int Start, int End)
			throws IOException, InterruptedException {
		List<WebElement> ele;
		// WebElement ele1;
		ele = CommonMethod.findElements(ObjectLocator);
		ele = ele.subList(Start, End);
		for (WebElement ele1 : ele) {
			do {
				scrolldowntoElement(ele1);
				JavascriptClickElement(ele1);
				Thread.sleep(2000);
			} while (!CommonMethod.isSelected(ele1));

		}

	}

	public static void assertcountListWebelementFromIndex(String ObjectLocator, int Count) throws IOException {
		List<WebElement> ele;

		ele = CommonMethod.findElements(ObjectLocator);
		System.out.println("Actual: " + ele.size() + " " + "Expected: " + Count );
		softAssert.assertEquals(ele.size(), Count, " ,ObjectLocator: "+ObjectLocator+", Count Validation failed");

	}

	

//	 public static void clearDriverCache() { ((HasDevTools)
//	  driver).getDevTools().send(Network.clearBrowserCookies()); ((HasDevTools)
//	 driver).getDevTools().send(Network.clearBrowserCookies()); }
//}

	public static void JavascriptClickElement(WebElement Objectlocator) throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", Objectlocator);
	}

	public static String FileRename(String oldFilePath, String newFilePath) {
		String newFileName = "";
		if (CommonMethod.isFileExists(oldFilePath)) {
			File path = new File(oldFilePath);
			File[] files = path.listFiles();
			for (File file : files) {
				File oldfile = new File(file.toString());
				File newfile = new File(System.getProperty("user.dir") + newFilePath);
				newFileName = newfile.toString();
				if (oldfile.renameTo(newfile)) {
					System.out.println("File renamed" + newfile);
				} else {
					System.out.println("Sorry! the file can't be renamed");
				}
			}
		}
		return newFileName;
	}

	public static void IsFilePresent(String FilePath) {
		File file = new File(FilePath);
		assertTrue(file.exists());
	}

	public static String GenerateCurrentDate() {
		LocalDateTime current = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatted = current.format(formatter);
		return formatted;
	}

	public static void assertisNotElementPresent(String objectLocator, String message) throws IOException {
		boolean boo = findElements(objectLocator).size() > 0;
		System.out.println(boo);
		softAssert.assertEquals(boo, false, message);
	}

	public static void ScrollUpToElement(String locator) throws IOException, InterruptedException {
		do {
			Actions a = new Actions(driver);
			a.sendKeys(Keys.PAGE_UP).build().perform();
		} while (false);// !CommonMethod.isElementsExist(locator, 20)
	}

	public static void VerifyRadioOrCheckboxSelcted(String objectlocator, int Start, int End) throws IOException {
		List<WebElement> checkboxOrRadio = findElements(objectlocator);
		checkboxOrRadio = checkboxOrRadio.subList(Start, End);
		for (WebElement ele : checkboxOrRadio) {
			if (!CommonMethod.isSelected(ele)) {
				negativesoftAssert.fail("Radio Or Checkbox is not selected: " + objectlocator);
			}
		}
	}

	public static void softAssertContainsMessage(String Actual, List<String> expected, String message) {
		System.out.println("Actual: " + Actual + "expected: " + expected);
		softAssert.assertTrue(expected.contains(Actual),
				message + " expected code was Not Found: " + "ActualText: " + Actual + ", ExpectedText: " + expected);
	}

	public static void captureNegativeAssertScreenshot() throws IOException, InterruptedException {
		BufferedImage image = Shutterbug.shootPage(driver, Capture.FULL).getImage();
		File filetest = Paths.get(".").toAbsolutePath().normalize().toFile();
		ImageIO.write(image, "png", new File(filetest + File.separator + "Screenshots" + File.separator + TestCaseName
				+ "_NA_" + String.valueOf(CommonMethod.randomNumber(1500)) + ".png"));
	}

	public static void negativesoftassertFieldValid(String ActualText, List<String> ExpectedText, String message)
			throws IOException, InterruptedException {
		System.out.println("ActualText: " + ActualText.replaceAll("\\s+", " ") + ", ExpectedText: " + ExpectedText);
		negativeFieldSoftAssert.assertTrue(ExpectedText.contains(ActualText.replaceAll("\\s+", " ")), message + " expected code was Not Found: "
				+ "ActualText: " + ActualText + ", ExpectedText: " + ExpectedText + ", TESTCASE NAME: " + TestCaseName);
	}

	public static void StartTimer() {
		pageLoad = new StopWatch();
		pageLoad.start();
		System.out.println("Timer Started...");

	}

	public static long TimeDiffInSec() {
		pageLoad.stop();
		// Get the time
		long pageLoadTime_ms = pageLoad.getTime();
		long pageLoadTime_Seconds = pageLoadTime_ms / 1000;
		System.out.println("pageLoadTime_Seconds: " + pageLoadTime_Seconds);
		testlog.info("Scorecard pageLoadTime_Seconds: " + pageLoadTime_Seconds);
		return pageLoadTime_ms;
	}

	public static String getISTdatetimeAsString() {
		String DATE_FORMAT = "MMM dd, yyyy";
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		final String utcTime = sdf.format(new Date());
		System.out.println("utcTime: " + utcTime);
		return utcTime;
	}

	public static void VerifyListDropdownValues(String objectLocator, List<String> Expected)
			throws IOException, InterruptedException {

		Select se = new Select(findElement(objectLocator));
		List<WebElement> ele = se.getOptions();
		for (WebElement list : ele) {
			System.out.println(list.getText());
			testlog.info("Rating System available : " + list.getText());
			CommonMethod.softAssertContainsMessage(list.getText(), Expected,
					"Verify List DropdownValues data mismatch");
		}
	}

	public static void GenericWaitUntil(String ObjectLocator, String TextToBePresent)
			throws IOException, InterruptedException {

		int timeoutSeconds = 300;
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000);
		Boolean boo = false;
		while (System.currentTimeMillis() < endTime) {
			try {

				boo = CommonMethod.WaitUntilTextToBePresentInLocator(ObjectLocator, TextToBePresent, 5);
				Thread.sleep(20000);
				// If the WebElement is found, break out of the loop
				if (boo) {
					break;
				}
			} catch (Exception e) {
				// WebElement not found, continue to refresh the page
				CommonMethod.refreshBrowser();
			}
		}

		// Perform actions with the located WebElement
		if (boo) {
			// WebElement found, perform desired actions
			System.out.println("Text Found in the locator");
		} else {
			// WebElement not found within the timeout period
			System.out.println("Text is not found in the locator");
		}

	}

	public static List<String> ValidateDate() {
		String date = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 0);
		String tommorowDate = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 1);
		String yesterdayDate = CommonMethod.setDateFormatFuture("MMM dd, yyyy", -1);
		List<String> dateValid = new ArrayList<>();
		dateValid.add(date);
		dateValid.add(tommorowDate);
		dateValid.add(yesterdayDate);
		return dateValid;
	}

	public static List<String> ValidateDateYear() {
		String date = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 364);
		String tommorowDate = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 365);
		String yesterdayDate = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 366);
		String yesterday1Date = CommonMethod.setDateFormatFuture("MMM dd, yyyy", 367);
		List<String> dateValid = new ArrayList<>();
		dateValid.add(date);
		dateValid.add(tommorowDate);
		dateValid.add(yesterdayDate);
		dateValid.add(yesterday1Date);
		return dateValid;
	}

	public static int generateSpaceTypes() {
		Random random = new Random();
		int rand = 0;
		while (true) {
			rand = random.nextInt(35);
			if (rand != 0 && rand != 10)
				break;
		}
		return rand;
	}

	public static void uploadMultipleFile(String objectLocater, String file, String file1, String VerifyobjectLocater,
			int ValidDeleteIcon, String MultipleLink) throws IOException, InterruptedException {
		WaitUntilNumberOfElementToBePresentMoreThan(objectLocater, 0);
		findElement(objectLocater).sendKeys(file);
		Thread.sleep(3000);
		if (CommonMethod.isElementsExist(MultipleLink, 7)) {
			WaitUntilNumberOfElementToBePresentMoreThan(objectLocater, 0);
			findElement(objectLocater).sendKeys(file1);
			Thread.sleep(3000);
			int var = CommonMethod.WaitUntilNumberOfElementToBePresent(VerifyobjectLocater, ValidDeleteIcon).size();
			CommonMethod.negativesoftassertFieldValid(String.valueOf(var), String.valueOf(ValidDeleteIcon),
					"Multiple file Upload Mismatch");
		} else {
			boolean boo = findElements(MultipleLink).size() > 0;
			CommonMethod.negativesoftassertFieldValid(String.valueOf(boo), "true", "Multiple file Upload link disable");
		}

	}

	public static void ClearDownloadFile() throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		do {
			if ((System.currentTimeMillis() - startTime) < 60000) {
				Thread.sleep(3000);
				FileUtils.cleanDirectory(new File(downloadPath));
				System.out.println("Deleted the Downloaded file");
				break;
			} else {
				System.out.println("Timeout");
				break;
			}

		} while (!CommonMethod.isFileExists(downloadPath));
	}

	public static void DropdownOptionVisiblity(String objectLocator, String OptionName, boolean visibleType)
			throws IOException, InterruptedException {
		Boolean found = false;
		Select select = new Select(findElement(objectLocator));
		List<WebElement> allOptions = select.getOptions();
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.get(i).getText().equals(OptionName)) {
				found = true;
				break;
			}
		}
		String fileExists = Boolean.toString(found);
		CommonMethod.negativesoftassertFieldValid(fileExists, "true", "Option is not visible");
	}

	public static void DropdownOptionNotContains(String objectLocator, String OptionName)
			throws IOException, InterruptedException {
		Boolean found = false;
		Select select = new Select(findElement(objectLocator));
		List<WebElement> allOptions = select.getOptions();
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.get(i).getText().equals(OptionName)) {
				found = true;
				break;
			}
		}
		String fileExists = Boolean.toString(found);
		CommonMethod.negativesoftassertFieldValid(fileExists, "false", "Option is visible: ");
	}

	public static void negativesoftassertisElementPresentTrue(String objectLocator, String BooleanTrue, String message)
			throws IOException {

		boolean boo = findElements(objectLocator).size() > 0;
		negativeFieldSoftAssert.assertTrue(Boolean.toString(boo).contains(BooleanTrue),
				message + ", TESTCASE NAME: " + TestCaseName);
	}
	
	public static void negativesoftassertisElementPresentFalse(String objectLocator, String BooleanFalse, String message)
			throws IOException {

		boolean boo = findElements(objectLocator).size() > 0;
		negativeFieldSoftAssert.assertFalse(Boolean.toString(boo).contains(BooleanFalse),
				message + ", TESTCASE NAME: " + TestCaseName);
	}

	public static void WaitUntilNumberOfElementToBePresentWithException(String objectLocator, int ElementSize)
			throws IOException {
		try {
			// It should be used before assertion alone and if element not present after
			// 300sec also it will not fail
			WaitUntilNumberOfElementToBePresent(objectLocator, ElementSize);
		} catch (Exception e) {
			System.out.println("Element Not Visible: " + objectLocator);
		}
	}

	public static void WaitUntilNumberOfElementToBeNotPresentWithException(String objectLocator)
			throws IOException {
		try {
			// It should be used before assertion alone and if element not present after
			// 300sec also it will not fail
			CommonMethod.WaitUntilNumberOfElementToBePresentLessThan(objectLocator, 1);
		} catch (Exception e) {
			System.out.println("Element is Visible: " + objectLocator);
		}
	}
	
	public static void WaitUntilNumberOfElementToBeSelectedWithException(String objectLocator)
			throws IOException, InterruptedException {

		long startTime = System.currentTimeMillis();
		int optionSize = 0;
		Boolean flag = false;
		Select se = null;
		do {
			Thread.sleep(3000);
			if (!((System.currentTimeMillis() - startTime) < 60000)) {
				flag = true;
				break;
			}
			se = new Select(findElement(objectLocator));
			List<WebElement> ele = se.getOptions();
			optionSize = ele.size();
		} while (!(optionSize > 1));
		System.out.println("Dropdown Option "+optionSize);
		if (flag == true) {
			System.out.println("Dropdown Option size is lesser than one");
			Assert.assertFalse(flag, "Dropdown Option size is lesser than One," + " optionSize: " + optionSize);
		}
	}
	
	public static void selectdropdownValue(String objectLocator, String value)
			throws IOException, InterruptedException {

		long startTime = System.currentTimeMillis();
		int optionSize = 0;
		Boolean flag = false;
		Select se = null;
		do {
			Thread.sleep(3000);
			if (!((System.currentTimeMillis() - startTime) < 60000)) {
				flag = true;
				break;
			}
			se = new Select(findElement(objectLocator));
			List<WebElement> ele = se.getOptions();
			optionSize = ele.size();
		} while (!(optionSize > 1));
		if (flag == false) {
			se.selectByValue(value);
		} else {
			System.out.println("Dropdown Option size is lesser than one");
			Assert.assertFalse(flag, "Dropdown Option size is lesser than One," + " optionSize: " + optionSize);
		}
	}
	
	public static void selectdropdownVisiblePartialtext(String objectLocator, String text)
			throws IOException, InterruptedException {
		List<WebElement> options = findElements(objectLocator);

		for (WebElement option : options) {
		    if (option.getText().contains(text)) {
		        option.click();
		        break;
		    }
		}
	}

	public static void selectdropdownVisibletext(String objectLocator, String text)
			throws IOException, InterruptedException {
		long startTime = System.currentTimeMillis();
		int optionSize = 0;
		Boolean flag = false;
		Select se = null;
		do {
			Thread.sleep(2000);
			if (!((System.currentTimeMillis() - startTime) < 60000)) {
				flag = true;
				break;
			}
			se = new Select(findElement(objectLocator));
			List<WebElement> ele = se.getOptions();
			optionSize = ele.size();
		} while (!(optionSize > 1));
		if (flag == false) {
			se.selectByVisibleText(text);
		} else {
			System.out.println("Dropdown Option size is lesser than one");
			Assert.assertFalse(flag, "Dropdown Option size is lesser than One," + " optionSize: " + optionSize);
		}
	}

	public static void selectdropdownrandom(String objectLocator) throws IOException, InterruptedException {

		long startTime = System.currentTimeMillis();
		int optionSize = 0;
		int n = 0;
		Boolean flag = false;
		Select se = null;
		do {
			Thread.sleep(3000);
			if (!((System.currentTimeMillis() - startTime) < 60000)) {
				flag = true;
				break;
			}
			se = new Select(findElement(objectLocator));
			List<WebElement> ele = se.getOptions();
			n = new Random().nextInt(ele.size());
			if (n == 0) {
				n = n + 1;
			}
			optionSize = ele.size();
		} while (!(optionSize > 1));
		if (flag == false) {
			se.selectByIndex(n);
			Thread.sleep(2000);
		} else {
			System.out.println("Dropdown Option size is lesser than one");
			Assert.assertFalse(flag, "Dropdown Option size is lesser than One," + " optionSize: " + optionSize);
		}
	}
	
	
	public static String getNextYear() throws IOException, InterruptedException {
		Date today = new Date(); // Create a Calendar instance and set it to the current date 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(today); // Add one year to the current date 
		calendar.add(Calendar.YEAR, 1); // Get the date after adding one year 
		Date nextYear = calendar.getTime(); // Format the date as "yyyy-MM-dd" 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
		String formattedDate = dateFormat.format(nextYear); // Print the formatted date 
		return formattedDate;
}
	
	
}