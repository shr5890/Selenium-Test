package old.utils;
//package com.wms.manhattan.utils;
//
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.NoAlertPresentException;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.ITestResult;
//import org.testng.Reporter;
//import org.testng.TestListenerAdapter;
//import org.testng.annotations.Listeners;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
//
///**
// * Base class for Selenium tests. Provides a variety of helper methods that are
// * useful across all tests.
// * 
// */
//@Listeners({ com.wms.manhattan.utils.TestBase.class, com.wms.manhattan.utils.CustomEmailableReporter.class })
//public class TestBase extends TestListenerAdapter {
//
//	final static String TEST_STATUS_KEY = "_TEST_STATUS_";
//	private static WebDriver _driver = null;
//	private static TestContext _context = null;
//	public static TestStatus testStatus = null;
//
//	protected static final Logger log = LogManager.getLogger("SeleniumTests");
//
//	/**
//	 * Create the Selenium WebDriver.
//	 * 
//	 * @return the webDriver
//	 */
//	public static WebDriver createDriver() {
//
//		if (getContext() == null) {
//			getLogger().error("ERROR: Must call initializeTestContext() before creating a driver");
//		} else {
//			String remoteServer = getContext().getStringProperty("remoteSeleniumServer", null);
//			_driver = WebDriverHelper.createDriver(remoteServer);
//		}
//		testStatus = new TestStatus();
//		
//		return _driver;
//	}
//
//	
//	/**
//	 * Close all windows and shut down the chromedriver process.
//	 * 
//	 * Note, it is important that you call closeDriver() or while working on
//	 * these tests the chromedriver.exe processes will not shut down and you
//	 * will have to manually end them using Task Manager.
//	 */
//	public static void closeDriver() {
//		_driver.quit();
//	}
//
//	/**
//	 * This gets the text of the alert message and it clicks the OK button to
//	 * make it go away.
//	 * 
//	 * @return String with the text of the alert message in it.
//	 */
//	public String closeAlertIfPresent() {
//		String str = null;
//		try {
//			Alert alert = _driver.switchTo().alert();
//			str = alert.getText();
//			alert.accept();
//			getLogger().warn("Dismissed Alert " + str);
//		} catch (NoAlertPresentException ex) {
//		}
//		return str;
//	}
//
//	/**
//	 * Set a previously created WebDriver into this test.
//	 * 
//	 * @param driver
//	 *            A previously created WebDriver instance.
//	 */
//	public static void setDriver(WebDriver driver) {
//		_driver = driver;
//	}
//
//	/**
//	 * Retrieve the WebDriver instance used for this test.
//	 * 
//	 * @return The current WebDriver instance.
//	 */
//	public static WebDriver getDriver() {
//		return _driver;
//	}
//
//	/**
//	 * Retrieve the log4j logger used for this test.
//	 * 
//	 * @return The configured log4j logger.
//	 */
//	public static Logger getLogger() {
//		return log;
//	}
//
//	/**
//	 * Initialize the TestContext object loading the properties from the default
//	 * properties file ("config.properties")
//	 */
//	public static void initializeTestContext() {
//		_context = new TestContext("config.xml");
//	}
//
//	/**
//	 * Initialize the TestContext object loading the properties from the
//	 * supplied properties file.
//	 * 
//	 * @param propertiesFile
//	 *            Path to a properties file containing properties for this test.
//	 */
//	public static void initializeTestContext(String propertiesFile) {
//		_context = new TestContext(propertiesFile);
//	}
//
//	/**
//	 * Retrieve the TestContext object for this test.
//	 * 
//	 * @return The TestContext object for this test.
//	 */
//	public static TestContext getContext() {
//		return _context;
//	}
//
//	/**
//	 * Pause between page transitions to slow down the test during development
//	 * and allow observation. The amount of time is controlled by the PagePause
//	 * property in the property file used by the TestContext. If not specified,
//	 * the default is 0 seconds.
//	 * <p>
//	 * Example config.properties:
//	 * 
//	 * <pre>
//	 *   :
//	 *   # number of seconds to pause between pages (for test observation during development)
//	 *   PagePause=2
//	 * </pre>
//	 */
//	public void pagePause() {
//		WebDriverHelper.wait(getContext().getDoubleProperty("pagePause", 0));
//	}
//
//	/**
//	 * Pause at the end of a test to slow down the test during development and
//	 * allow observation. The amount of time is controlled by the EndPause
//	 * property in the property file used by the TestContext. If not specified,
//	 * the default is 0 seconds.
//	 * <p>
//	 * Example config.properties:
//	 * <p>
//	 * 
//	 * <pre>
//	 *   :
//	 *   # number of seconds to pause at end of test (for test observation during development)
//	 *   EndPause=10
//	 * </pre>
//	 */
//	public void endPause() {
//		WebDriverHelper.wait(getContext().getDoubleProperty("endPause", 0));
//	}
//
//	/**
//	 * Validate a page object and log the results if it fails validation.
//	 * 
//	 * @param pageObject
//	 *            The page object that extends from BasePage.
//	 * @param testStatus
//	 *            The test status tracker.
//	 */
//	public boolean validatePage(BasePage pageObject, TestStatus testStatus) {
//		return validatePage(pageObject, testStatus, null);
//	}
//
//	/**
//	 * Validate a page object and log the results if it fails validation.
//	 * 
//	 * @param pageObject
//	 *            The page object that extends from BasePage.
//	 * @param testStatus
//	 *            The test status tracker.
//	 * @param pageValidator
//	 *            Additional page validator callback.
//	 */
//	public boolean validatePage(BasePage pageObject, TestStatus testStatus, IPageValidator pageValidator) {
//
//		// Call the overridden validate() method on the page object
//		ValidationResults results = pageObject.validate();
//
//		if (pageValidator != null) {
//			results.addAll(pageValidator.validate(pageObject));
//		}
//
//		boolean isValid = results.isValid();
//		if (!isValid) {
//			getLogger().error("ERROR:   " + pageObject.getPageName() + " failed validation.\r\n" + results.toString());
//		} else {
//			if (results.getValidationCount() > 0) {
//				getLogger().info(
//						"SUCCESS: " + pageObject.getPageName() + " passed validation.\r\n" + results.toString());
//			} else {
//				getLogger().warn(pageObject.getPageName() + " no validations performed");
//			}
//		}
//		testStatus.appendValidationResults(results);
//		return isValid;
//	}
//
//	/**
//	 * Log the validation results to TestNG reporter.
//	 * 
//	 * @param testStatus
//	 */
//	public void logValidationResults(TestStatus testStatus) {
//
//		ValidationResults results = testStatus.getValidationResults();
//
//		Reporter.setEscapeHtml(false);
//		Reporter.log(results.toHtml());
//
//		if (results.getValidationCount() > 0) {
//			getLogger().info("** Test Validation Results **\r\n" + results.toString());
//		}
//	}
//
//	/**
//	 * Returns the automatically created TestStatus object attached to this
//	 * test.
//	 * 
//	 * @return The current TestStatus object.
//	 */
//	public TestStatus getTestStatus() {
//		ITestResult testResult = Reporter.getCurrentTestResult();
//		return (TestStatus) testResult.getAttribute(TEST_STATUS_KEY);
//	}
//
//	/**
//	 * Assert that there were no validation failures
//	 * 
//	 * @param testStatus
//	 */
//	public void assertAllValidations(TestStatus testStatus) {
//		Assert.assertTrue(testStatus.isValid(), testStatus.getValidationResults().toString());
//	}
//
//	/**
//	 * Runs before every test.
//	 */
//	@Override
//	public void onTestStart(ITestResult result) {
//		super.onTestStart(result);
//
//		// Automatically create a new TestStatus object and attach it to the
//		// test results
//		result.setAttribute(TEST_STATUS_KEY, testStatus);
//
//		getLogger().trace("============= TEST START ==============");
//		getLogger().trace("Test Name: " + result.getName());
//	}
//
//	/**
//	 * Runs when a test succeeds.
//	 */
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		super.onTestSuccess(result);
//
//		getLogger().trace("Test success: " + result.getName());
//		logValidationResults(getTestStatus());
//	}
//
//	/**
//	 * Runs when a test fails.
//	 */
//	@Override
//	public void onTestFailure(ITestResult result) {
//		super.onTestFailure(result);
//
//		getLogger().error("Test failure: " + result.getName());
//		Throwable error = result.getThrowable();
//		if (error != null) {
//			getLogger().error(error.toString());
//		}
//		logValidationResults(getTestStatus());
//	}
//
//	/**
//	 * Runs when a test is skipped.
//	 */
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		super.onTestSkipped(result);
//		getLogger().trace("Test skipped: " + result.getName());
//	}
//	
//	/**
//	 * 
//	 * This method will only split the data with any delimiter and the result will be given as array
//	 * 
//	 * @param strDataValue,strSymbol
//	 * @return array
//	 */
//	public static String[] splitDataValues(String strDataValue,String strSymbol)
//	{		
//		int distinct=0;		
//		for (int i = 0; i < strDataValue.length(); i++) {	       
//			if(strDataValue.charAt(i)==(strSymbol.charAt(0)))
//			{	                
//				distinct++;
//			}
//		} 
//		String strMultipleValues[] = new String[distinct+1];
//		if (strDataValue.contains(strSymbol)){			
//			strMultipleValues = strDataValue.split("\\"+strSymbol,-1);
//		}
//		else{
//			strMultipleValues[0]=strDataValue;
//		}
//		return strMultipleValues;
//	}
//	
//	/**
//	 * Create the Selenium WebDriver for FedEx application
//	 * 
//	 * @return the webDriver
//	 */
//	public static WebDriver createDriverForFedEx() {
//
//		if (getContext() == null) {
//			getLogger().error("ERROR: Must call initializeTestContext() before creating a driver");
//		} else {
//			String remoteServer = getContext().getStringProperty("remoteSeleniumServer", null);
//			_driver = WebDriverHelper.createFedExDriver(remoteServer);
//		}
//		return _driver;
//	}
//		
//}
