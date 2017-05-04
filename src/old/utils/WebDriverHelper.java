package old.utils;
//package com.wms.manhattan.utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.concurrent.TimeUnit;
//import org.apache.commons.io.FileUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.remote.CapabilityType;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import com.google.common.base.Function;
//
//
///**
// * Set of helper methods for interacting with pages with a WebDriver.
// */
//public class WebDriverHelper {
//
//	protected static final Logger log = LogManager.getLogger("WebDriverHelper");
//
//	/**
//	 * Create a local WebDriver for IOS.
//	 * 
//	 * @return A WebDriver.
//	 */
//	
//	public static JavascriptExecutor js = null;
//	
//	public static WebDriver createDriver() {
//		return createDriver(null);
//	}
//
//	
//	/**
//	 * Create a local or remote WebDriver for IOS.
//	 * 
//	 * @param remoteServer
//	 *            Name of remote server, null or empty to create a local driver.
//	 * @return A WebDriver.
//	 */
//	
//	public static WebDriver createDriver(String remoteServer) {
//
//		WebDriver driver = null;
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		
//		if (remoteServer == null || remoteServer.trim().equals("")) {
//			System.setProperty("webdriver.ie.driver", "lib/iedriver/IEDriverServer.exe");
//			capabilities.setCapability("IE.binary", "lib/iedriver/IEDriverServer.exe");
//		}
//		else {
//			File file =new File("C:\\SupplyChain\\IEDriverServer_x64_2.48.0\\IEDriverServer.exe");
//			System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
//			capabilities.setBrowserName("internet explorer");
//			capabilities.setCapability("IE.binary", file.getAbsolutePath());
//		}	
//		
//		capabilities.setCapability("initialBrowserUrl", "http://wmslx0013.hq.target.com:10300");
//		capabilities.setCapability("ignoreProtectedModeSettings", true);
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		capabilities.setCapability("nativeEvents",false);   
//		capabilities.setCapability("unexpectedAlertBehaviour", "accept");
//		capabilities.setCapability("disable-popup-blocking", true);
//		capabilities.setCapability("enablePersistentHover", true);
//		capabilities.setJavascriptEnabled(true);
//		
//		if (remoteServer == null || remoteServer.trim().equals("")) {
//			driver = (new InternetExplorerDriver(capabilities));
//		} else {
//			URL remoteSeleniumUrl = null;
//
//			try {
//
//				remoteSeleniumUrl = new URL("http://" + remoteServer + ":4444/wd/hub");
//				log.info("remoteServer Value = \"" + remoteSeleniumUrl + "\"");
//			} catch (MalformedURLException e) {
//				log.info("MalformedURLException: " + e);
//			}
//			driver = new RemoteWebDriver(remoteSeleniumUrl, capabilities);
//		}
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//
//		return driver;
//	}
//
//	/**
//	 * Wait for a certain number of seconds.
//	 * 
//	 * @param seconds
//	 *            Seconds to wait.
//	 */
//	public static void wait(double seconds) {
//		try {
//			Thread.sleep((int) (seconds * 1000.0));
//		} catch (InterruptedException e) {
//		}
//	}
//
//	/**
//	 * Wait for an element to appear.
//	 * 
//	 * @param driver
//	 * @param element
//	 */
//	@Deprecated
//	public static void wbWait(WebDriver driver, WebElement element) {
//		waitFor(driver, element, 30);
//	}
//
//	/**
//	 * Wait for an element to appear.
//	 * 
//	 * @param driver
//	 *            The WebDriver.
//	 * @param element
//	 *            The element to watch for.
//	 * @return The located WebElement.
//	 */
//	public static void waitFor(WebDriver driver, WebElement element) {
//		waitFor(driver, element, 60);
//	}
//
//	/**
//	 * Wait for an element to appear.
//	 * 
//	 * @param driver
//	 *            The WebDriver.
//	 * @param locator
//	 *            The location of the element to watch for.
//	 * @return The located WebElement.
//	 */
//	public static void waitFor(WebDriver driver, WebElement element, int timeout) {
//		WebDriverWait wait = new WebDriverWait(driver, timeout);
//		wait.until(ExpectedConditions.visibilityOf(element));
//	}
//
//	/**
//	 * Wait for an element to appear.
//	 * 
//	 * @param driver
//	 *            The WebDriver.
//	 * @param locator
//	 *            The location of the element to watch for.
//	 * @return The located WebElement.
//	 */
//	public static void waitFor(WebDriver driver, By locator) {
//		waitFor(driver, locator, 30);
//	}
//
//	/**
//	 * Wait for an element to appear.
//	 * 
//	 * @param driver
//	 *            The WebDriver.
//	 * @param locator
//	 *            The location of the element to watch for.
//	 * @param timeout
//	 *            Number of seconds to wait before giving up.
//	 * @return The located WebElement.
//	 */
//	public static void waitFor(WebDriver driver, By locator, int timeout) {
//		WebDriverWait wait = new WebDriverWait(driver, timeout);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//	}
//
//	/**
//	 * Wait for the next page to appear
//	 * 
//	 * @param driver
//	 *            The WebDriver.
//	 * @param timeout
//	 *            Number of seconds to wait before giving up.
//	 */
//	public static void waitForNextPage(WebDriver driver, int timeout) {
//		Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
//		wait.until(new Function<WebDriver, Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				//System.out.println("Current Window State       : "
//				//		+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
//				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
//						.equals("complete");
//			}
//		});
//	}
//
//	/**
//	 * Take a screenshot.
//	 * 
//	 * @param driver
//	 *            The webDriver.
//	 * @param fileName
//	 *            The path to the file you wish to save.
//	 */
//	public static void takeScreenshot(WebDriver driver, String fileName) {
//		// take a screenshot
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		try {
//			// now save the screenshot to a file some place
//			FileUtils.copyFile(scrFile, new File(fileName));
//		} catch (IOException ioex) {
//		}
//	}
//
//	/**
//	 * Run a Javascript snippet in the context of a browser.
//	 * 
//	 * @param driver
//	 *            WebDriver.
//	 * @param script
//	 *            Script to run.
//	 */
//	public static void runJavascript(WebDriver driver, String script) {
//
//		if (driver instanceof JavascriptExecutor) {
//			((JavascriptExecutor) driver).executeScript(script);
//		}
//	}
//
//	/**
//	 * Set services end point, stubs and store
//	 * 
//	 * @param driver
//	 * @param context
//	 */
//	public static void setServiceEndpoint(WebDriver driver, TestContext context) {
//
//		String servicesEndPoint = context.getStringProperty("servicesEndpoint", ""); // "http://usestubs";
//		String useStubs = context.getStringProperty("useStubs", "YES");
//		String storeId = context.getStringProperty("storeId", "T0000");
//
//		log.info("** Using the following deviceSettings **");
//		log.info(" deviceSettings.storeId = \"" + storeId + "\"");
//		log.info(" deviceSettings.useStubs = \"" + useStubs + "\"");
//		log.info(" deviceSettings.url = \"" + servicesEndPoint + "\"");
//
//		String script = 
//				// wrapping this in run() didn't work like we thought it should, the original did however.
//				// + "mydevice.run([function(){\r\n"
//				  "deviceSettings.storeId = \"" + storeId + "\";\r\n"
//				+ "deviceSettings.useStubs = \"" + useStubs + "\";\r\n" 
//				+ "deviceSettings.url = \"" + servicesEndPoint + "\";\r\n" 
//				+ "document.dispatchEvent(new Event(\"resume\"));";
//				// + "}]);";
//
//		WebDriverHelper.runJavascript(driver, script);
//	}
//	
//	/**
//	 * Create a local WebDriver for FedEx application
//	 *
//	 * @return A WebDriver
//	 */
//	
//	public static WebDriver createFedExDriver(String remoteServer) {
//		WebDriver driver = null;	
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//
//		if (remoteServer == null || remoteServer.trim().equals("")) {
//			System.setProperty("webdriver.ie.driver", "lib/iedriver/IEDriverServer.exe");
//			capabilities.setCapability("IE.binary", "lib/iedriver/IEDriverServer.exe");
//		}
//		else {
//			File file =new File("C:\\SupplyChain\\IEDriverServer_x64_2.42.0\\IEDriverServer.exe");
//			System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
//			capabilities.setBrowserName("internet explorer");
//			capabilities.setCapability("IE.binary", file.getAbsolutePath());
//		}
//
//		capabilities.setCapability("initialBrowserUrl", "https://www.fedex.com/fcl/;SHIPPINGSESSIONID=qnpyVP0QnhLt2yxdMp9LbsnXs71nSRbGLths5GcC8lZRbbQyglv6!464812890?appName=fclfsm&locale=us_en&step3URL=https%3A%2F%2Fwww.fedex.com%2Fshipping%2FshipEntryAction.do%3Fmethod%3DdoRegistration%26link%3D1%26locale%3Den_US%26urlparams%3Dus%26sType%3DF&returnurl=https%3A%2F%2Fwww.fedex.com%2Fshipping%2FshipEntryAction.do%3Fmethod%3DdoEntry%26link%3D1%26locale%3Den_US%26urlparams%3Dus%26sType%3DF&programIndicator=0");
//		capabilities.setCapability("ignoreProtectedModeSettings", true);
//		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//		capabilities.setCapability("nativeEvents",false);   
//		capabilities.setCapability("unexpectedAlertBehaviour", "accept");
//		capabilities.setCapability("disable-popup-blocking", true);
//		capabilities.setCapability("enablePersistentHover", true);
//		capabilities.setJavascriptEnabled(true);
//
//		if (remoteServer == null || remoteServer.trim().equals("")) {
//			driver = (new InternetExplorerDriver(capabilities));
//		} else {
//			URL remoteSeleniumUrl = null;
//
//			try {
//
//				remoteSeleniumUrl = new URL("http://" + remoteServer + ":4444/wd/hub");
//				log.info(" remoteServer Value = \"" + remoteSeleniumUrl + "\"");
//			} catch (MalformedURLException e) {
//				log.info(" remoteServer Value 1");
//			}
//			driver = new RemoteWebDriver(remoteSeleniumUrl, capabilities);
//			log.info(" remoteServer Value 2");
//		}
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//		return driver;
//	}
//
//}
