package old.utils;
//package com.wms.manhattan.utils;
//
//import java.sql.Connection;
//import java.sql.Statement;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.TimeZone;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.Select;
//import org.testng.Assert;
//
//import com.wms.manhattan.tests.TestConstants;
//import com.wms.pages.CommonPage;
//
///**
// * Base page for all Selenium page objects
// */
//public class BasePage {
//
//	protected static WebDriver _driver = null;
//	private static TestContext _context = null;
//	private static final Logger _logger = LogManager.getLogger("SeleniumPage");
//	public static  HashMap<String, String> colFieldMap = null;
//	public static HashMap<String,String> hmDOData = null;
//	public static  Connection conn = null;
//	public static Statement stmt = null;
//	
//	@FindBy(xpath = "//span[contains(text(),'- Target')]")
//	public static WebElement env_name;
//	
//	@FindBy(xpath = "//td[contains(text(),'Enter filter criteria or select Saved filter, then select Apply to retrieve data')]")
//	public static WebElement ele_BlankTableMessage;
//	
//	/**
//	 * Construct an instance of a BasePage with no WebDriver
//	 */
//	public BasePage() {
//	}
//
//	/**
//	 * Construct an instance of a BasePage with a WebDriver.
//	 * 
//	 * @param driver
//	 *            The selenium WebDriver instance for this page.
//	 */
//	public BasePage(WebDriver driver) {
//		_driver = driver;
//	}
//
//	/**
//	 * Override this to customize the name of the page
//	 * 
//	 * @return The name of the page.
//	 */
//	public String getPageName() {
//		return this.getClass().getSimpleName();
//	}
//
//	/**
//	 * Retrieve the log4j logger used for this test.
//	 * 
//	 * @return The configured log4j logger.
//	 */
//	public static Logger getLogger() {
//		return _logger;
//	}
//
//	/**
//	 * Set the WebDriver instance for this page.
//	 * 
//	 * @param driver
//	 *            The selenium WebDriver instance for this page.
//	 */
//	public void setDriver(WebDriver driver) {
//		_driver = driver;
//	}
//
//	/**
//	 * Retrieve the current WebDriver for this page.
//	 * 
//	 * @return The selenium WebDriver instance for this page.
//	 */
//	public static WebDriver getDriver() {
//		return _driver;
//	}
//
//	/**
//	 * Set the TestContext for this page.
//	 * 
//	 * @param context
//	 *            The TestContext for this page.
//	 */
//	public void setContext(TestContext context) {
//		_context = context;
//	}
//
//	/**
//	 * Retrieve the TestContext for this page.
//	 * 
//	 * @return The current TestContext for this page.
//	 */
//	public static TestContext getContext() {
//		return _context;
//	}
//
//	/**
//	 * Construct a page object. This method initializes all elements and sets
//	 * the TestContext. Internally it calls PageFactory.initElement(), the only
//	 * additional step is that the TestContext is also set automatically for
//	 * you.
//	 * 
//	 * @param driver
//	 *            The Selenium WebDriver.
//	 * @param context
//	 *            The TestContext.
//	 * @param pageClassToProxy
//	 *            The page class to create.
//	 * @return The instantiated page object.
//	 */
//	public static <T extends BasePage> T createPage(WebDriver driver,
//			TestContext context, Class<T> pageClassToProxy) {
//		T page = PageFactory.initElements(driver, pageClassToProxy);
//		page.setContext(context);
//		return page;
//	}
//
//	/**
//	 * Construct a page object. This method initializes all elements and sets
//	 * the TestContext. Internally it calls PageFactory.initElement(), the only
//	 * additional step is that the TestContext is also set automatically for
//	 * you.
//	 * 
//	 * @param pageClassToProxy
//	 *            The page class to create.
//	 * @return The instantiated page object.
//	 */
//	public <T extends BasePage> T createPage(Class<T> pageClassToProxy) {
//		return createPage(getDriver(), getContext(), pageClassToProxy);
//	}
//
//	// -- VALIDATION --
//
//	/**
//	 * Validate the page by calling the overloaded validate() method.
//	 * 
//	 * @return True if the page passed validation, False if not.
//	 */
//	public ValidationResults validate() {
//		return new ValidationResults();
//	}
//
//	/**
//	 * Check to see if an element is present. If the element is not found a
//	 * validation error will be added to the error collection.
//	 * 
//	 * @param name
//	 *            "Friendly" Name of the element (used for logging).
//	 * @param webElement
//	 *            The instance of the webElement.
//	 * @return True if element was found, False if not.
//	 */
//	public boolean checkElementPresent(ValidationResults validations, String itemName, WebElement webElement) {
//
//		boolean isElementPresent = false;
//
//		if (null != webElement) {
//			try {
//				webElement.getTagName();
//				isElementPresent = true;
//			} catch (NoSuchElementException e) {
//			}
//		}
//
//		String description = isElementPresent ? "Element found." : "Element not found.";
//		validations.addValidation(getPageName(), itemName, description, isElementPresent);
//
//		return isElementPresent;
//	}
//
//	/**
//	 * Check to see than an element is present using a locator. If the element
//	 * is not found a validation error will be added to the error collection.
//	 * 
//	 * @param name
//	 *            Name of element.
//	 * @param locator
//	 *            Locator for element
//	 * @return True if element was found, False if not.
//	 */
//	public boolean checkElementPresent(ValidationResults validations, String itemName, By locator) {
//
//		WebElement element = null;
//		try {
//			element = getDriver().findElement(locator);
//		} catch (NoSuchElementException e) {
//		}
//
//		return checkElementPresent(validations, itemName, element);
//	}
//	
//	/**
//	 * 
//	 * This method will only performs the check and will not add validation.
//	 * 
//	 * @param element
//	 * @return boolean
//	 */
//	public boolean checkElement(WebElement element, String typeOfCheck) {
//		try { 
//			
//			if("display".equals(typeOfCheck)) {
//				return element.isDisplayed();
//			} else if ("enable".equals(typeOfCheck)) {
//				return element.isEnabled();
//			} else if("select".equals(typeOfCheck)){
//				return element.isSelected();
//			} else {
//				return false;
//			}
//		}
//		catch (NoSuchElementException e) { 
//	    	return false;
//	    } 
//	}
//	
//	public static String curdate( )
//	{
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm");
//		dateFormat.setTimeZone(TimeZone.getTimeZone("CST"));
//		Date date = new Date();
//		getLogger().info(dateFormat.format(date));
//		String  tempdate = dateFormat.format(date).toString();
//		return tempdate;
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
//		for (int intInd = 0; intInd < strDataValue.length(); intInd++) {	       
//			if(strDataValue.charAt(intInd)==(strSymbol.charAt(0)))
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
//	 * 
//	 * This method will verify if the given element is present in the current page 
//	 * 
//	 * @param strObjectProperty,strElementType
//	 * @return bool
//	 */
//	public static boolean verifyElementPresent(String strObjectProperty, String strElementType){	
//		WebElement wbObject;
//		try {
//			if (strElementType.equalsIgnoreCase("CSS"))
//				wbObject = _driver.findElement(By.cssSelector(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("XPATH"))
//				wbObject = _driver.findElement(By.xpath(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("ID"))
//				wbObject = _driver.findElement(By.id(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("NAME"))
//				wbObject = _driver.findElement(By.name(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("LINKTEXT"))
//				wbObject = _driver.findElement(By.linkText(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("TAG"))
//				wbObject = _driver.findElement(By.tagName(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("CLASS"))
//				wbObject = _driver.findElement(By.className(strObjectProperty));
//			else
//				wbObject = null;
//
//			if (!(wbObject == null)){
//				return true;
//			}else{
//				return false;
//			}		
//
//		}catch(Exception e){
//			getLogger().info(e);
//		}	
//		return false;
//	}
//	
//	/**
//	 * 
//	 * This method will return the object present in the current page. 
//	 * 
//	 * @param strObjectProperty,strElementType
//	 * @return wbObject
//	 */
//	public static WebElement getElement(String strObjectProperty, String strElementType){	
//		WebElement wbObject = null;
//		try {
//			if (strElementType.equalsIgnoreCase("CSS"))
//				wbObject = _driver.findElement(By.cssSelector(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("XPATH"))
//				wbObject = _driver.findElement(By.xpath(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("ID"))
//				wbObject = _driver.findElement(By.id(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("NAME"))
//				wbObject = _driver.findElement(By.name(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("LINKTEXT"))
//				wbObject = _driver.findElement(By.linkText(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("TAG"))
//				wbObject = _driver.findElement(By.tagName(strObjectProperty));
//			else if (strElementType.equalsIgnoreCase("CLASS"))
//				wbObject = _driver.findElement(By.className(strObjectProperty));
//			else
//				wbObject = null;		
//		}catch(Exception e){
//			getLogger().info(e);
//		}	
//		return wbObject;
//	}
//	
//	/**
//	 * 
//	 * This method will wait for an object to be clicked until the object loads 
//	 * 
//	 * @param strObject,strObjectType
//	 * @return intpointer
//	 */
//	public static int WaitForObjectToBeClicked(String strObject,String strObjectType){
//		int intpointer=0;
//		try{			
//			do{
//				if(verifyElementPresent(strObject, strObjectType)){
//					WebElement el = getElement(strObject, strObjectType);
//					el.click();
//					intpointer=0;
//					break;
//				}
//				else{
//					intpointer=intpointer+1;
//					if(intpointer>60){
//						break;
//					}
//				}					
//			}
//			while(intpointer!=0);
//		}catch(Exception e){		
//			intpointer=1;
//			getLogger().info("Error : " + e);
//		}
//		return intpointer;
//	}
//
//	/**
//	 * 
//	 * This method will retrieve the text of an object 
//	 * 
//	 * @param strObject,strObjectType
//	 * @return strText
//	 */
//	public static String WaitForObjectGetText(String strObject,String strObjectType){
//		int intpointer=0;
//		String strText = null;
//		try{			
//			do{
//				if(verifyElementPresent(strObject, strObjectType)){
//					WebElement el = getElement(strObject, strObjectType);
//					strText = el.getText();	
//					intpointer=0;
//					break;
//				}
//				else{
//					intpointer=intpointer+1;
//					if(intpointer>60){
//						break;
//					}
//				}					
//			}
//			while(intpointer!=0);
//		}catch(Exception e){		
//			getLogger().info("Error: " + e);
//		}
//		return strText;
//	}
//
//		
//	/**
//	 * 
//	 * This method will clear the existing value and update the new value in a text box 
//	 * 
//	 * @param elemToUpdate,strValueToUpdate,strObjName
//	 * @return boolean
//	 */
//	public static boolean clearAndUpdateAnyElement(WebElement elemToUpdate,String strValueToUpdate, String strObjName) {
//		if (!strValueToUpdate.trim().equalsIgnoreCase("IGNORE")) {
//			try {
//				if (elemToUpdate.isDisplayed() && elemToUpdate.isEnabled()) {
//					WebDriverHelper.wait(1.0);
//					elemToUpdate.clear();
//					updateAnyElement(elemToUpdate, strValueToUpdate, strObjName);
//				} else{
//				}
//			} catch (org.openqa.selenium.NoSuchElementException nsee) {
//				getLogger().info(nsee);
//			} catch (Exception e) {
//				getLogger().info(e);
//			}
//			return false;
//		} else
//			return true;
//	}
//
//	/**
//	 * 
//	 * This method will verify if an element is present and then update its value 
//	 * 
//	 * @param strObjProperty,strObjPropertyType,strValueToUpdate,strObjName
//	 * @return boolean
//	 */
//	public boolean updateAnyElement(String strObjProperty,String strObjPropertyType, String strValueToUpdate,String strObjName) {
//		if (strValueToUpdate.trim().equalsIgnoreCase("IGNORE")) {
//			try {
//				if (isElementPresentVerification(strObjProperty,strObjPropertyType, strObjName)) {
//					WebElement elemToClick = getElementByProperty(strObjProperty, strObjPropertyType);
//					updateAnyElement(elemToClick, strValueToUpdate, strObjName);
//				} else{
//				}
//			} catch (org.openqa.selenium.NoSuchElementException nsee) {
//				getLogger().info(nsee);
//			} catch (Exception e) {
//				getLogger().info(e);
//			}
//			return false;
//		} else
//			return true;
//	}
//
//	
//	/**
//	 * 
//	 * This method will verify if an element is present in the current page 
//	 * 
//	 * @param strObjPropertyType,strFindElementType,strObjName
//	 * @return boolean
//	 */
//	public static boolean isElementPresentVerification(String strObjectProperty,String strFindElementType, String strObjName) {
//		try {
//			if (getElementByProperty(strObjectProperty, strFindElementType) != null) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (org.openqa.selenium.NoSuchElementException nsee) {
//			getLogger().info(nsee);
//			return false;
//		} catch (Exception e) {
//			getLogger().info(e);
//			return false;
//		}
//	}
//
//	/**
//	 * 
//	 * This method will retrieve the element based on its property
//	 * 
//	 * @param strObjPropertyType,strFindElementType
//	 * @return strObjectProperty
//	 */
//	public static WebElement getElementByProperty(String strObjectProperty,String strFindElementType) {
//		try {
//			if (strFindElementType.equalsIgnoreCase("CSS"))
//				return _driver.findElement(By.cssSelector(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("XPATH"))
//				return _driver.findElement(By.xpath(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("ID"))
//				return _driver.findElement(By.id(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("NAME"))
//				return _driver.findElement(By.name(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("LINKTEXT"))
//				return _driver.findElement(By.linkText(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("TAG"))
//				return _driver.findElement(By.tagName(strObjectProperty));
//			else if (strFindElementType.equalsIgnoreCase("CLASS"))
//				return _driver.findElement(By.className(strObjectProperty));
//			else
//				return null;
//		} catch (org.openqa.selenium.NoSuchElementException nsee) {
//			getLogger().info(nsee);
//			return null;
//		}
//		catch (Exception e) {
//			getLogger().info(e);
//			return null;
//		}
//	}
//
//	/**
//	 * 
//	 * This method will update the value of an element
//	 * 
//	 * @param elemToUpdate,strValueToUpdate,strObjName
//	 * @return boolean
//	 */
//	public static boolean updateAnyElement(WebElement elemToUpdate,String strValueToUpdate, String strObjName) {
//		if (!strValueToUpdate.trim().equalsIgnoreCase("IGNORE")) {
//			try {
//				if (elemToUpdate.isDisplayed() && elemToUpdate.isEnabled()) {
//					elemToUpdate.click();
//					elemToUpdate.clear();
//					elemToUpdate.sendKeys(strValueToUpdate);
//					return true;
//				} 
//			} catch (org.openqa.selenium.NoSuchElementException nsee) {
//				getLogger().info(nsee);
//			} catch (Exception e) {
//				getLogger().info(e);
//			}
//			return false;
//		} else
//			return true;
//	}
//	
//	/**
//	 * 
//	 * This method will wait for an object to load in the current page
//	 * 
//	 * @param strObject,strObjectType
//	 * @return intpointer
//	 */
//	public static int WaitForObjectToBeLoaded(String strObject,String strObjectType){
//		int intpointer=0;
//		try{			
//			do{
//				if(verifyElementPresent(strObject, strObjectType)){
//					intpointer=0;
//					break;
//				}
//				else{
//					intpointer=intpointer+1;
//					Thread.sleep(1000);
//					if(intpointer>60){
//						break;
//					}
//				}					
//			}
//			while(intpointer!=0);
//		}catch(Exception e){		
//			intpointer=1;
//			getLogger().info("Error : "+e);
//		}
//		return intpointer;
//	}
//	
//	
//	/**
//	 * 
//	 * This method will select a value from the dropdown box
//	 * 
//	 * @param elementToSelect,strValueToSelect
//	 * @return intpointer
//	 */
//	public static void selectAnyElement(WebElement elementToSelect, String strValueToSelect) {
//		if (!(strValueToSelect.trim().equalsIgnoreCase("IGNORE"))) {
//			try {
//				if (elementToSelect.isEnabled()) {
//					Select comSelElement = new Select(elementToSelect);
//					comSelElement.selectByVisibleText(strValueToSelect.trim());
//				}
//			} catch (org.openqa.selenium.NoSuchElementException nsee) {
//				getLogger().info(nsee);
//			} catch (Exception e) {
//				getLogger().info(e);
//			}
//		}
//	}
//	
//	/**
//	 * 
//	 * This method will retrieve the text of the column from a table
//	 * 
//	 * @param eleTable,intColNo
//	 * @return strData
//	 */
//	public static String[] dataFromTable(WebElement eleTable, int intColNo) {
//		WebDriverHelper.wait(2.0);
//		List<WebElement> lstDOTblRows = eleTable.findElements(By.tagName("tr"));
//		String[] strData = new String[lstDOTblRows.size()-1];
//		try {
//			for(int intInd=0;intInd<lstDOTblRows.size()-1;intInd++) {
//				List<WebElement> lstDOTblCols = lstDOTblRows.get(intInd).findElements(By.tagName("td"));
//				if(lstDOTblCols.get(intColNo).getText().toString()!=null) {
//					strData[intInd]= lstDOTblCols.get(intColNo).getText().toString();
//				}
//			}             
//		}
//		catch(Exception e) {
//			getLogger().info(e);
//		}
//		return strData;
//	}	
//	
//	/**
//	 * 
//	 * This method will click OK for a pop up
//	 * 
//	 * @param None
//	 * @return None
//	 */
//	public static  void clickOKAlert() throws InterruptedException{
//		WebDriverHelper.wait(2.0);
//		_driver.switchTo().alert().accept();
//		WebDriverHelper.wait(2.0);
//	}
//	
//	public static String[] removeDuplicates(String[] arrArray) {
//		ArrayList<String> alStr = new ArrayList<>();
//		for(int intInd=0;intInd<arrArray.length;intInd++) {
//			if(!alStr.contains(arrArray[intInd])) {
//				alStr.add(arrArray[intInd]);
//			}
//		}
//		return (String[]) alStr.toArray(new String[alStr.size()]);
//	}		
//	
//	/**
//	 * 
//	 * This method will verify if the given element is present in the current page 
//	 * 
//	 * @param strObjectProperty,strElementType
//	 * @return bool
//	 */
//	public static boolean checkElementPresent(WebElement wbObject){			
//		try {
//			if(wbObject.isDisplayed()){
//				return true;
//			}			
//			else{
//				return false;
//			}		
//		}catch(Exception e){			
//		}	
//		return false;
//	}
//	
//	public static String[] returnDuplicates(String[] strArray) {
//		ArrayList<String> arrListDuplicate = new ArrayList<String>();;
//		Set<String> set = new HashSet<String>();        
//		for(int intInd = 0; intInd < strArray.length ; intInd++)
//		{
//			//If same integer is already present then add method will return FALSE
//			if(set.add(strArray[intInd]) == false)
//			{
//				arrListDuplicate.add(strArray[intInd]);
//				getLogger().info("Duplicate element found : " + strArray[intInd]);
//			}
//		}
//
//		String[] arrDuplicate = new String[arrListDuplicate.size()];
//		arrDuplicate = arrListDuplicate.toArray(arrDuplicate);
//		return arrDuplicate;
//	}
//
//	/**
//	 * 
//	 * This method will wait for an object to load in the current page
//	 * 
//	 * @param strObject,strObjectType
//	 * @return intpointer
//	 */
//	public static boolean WaitForElementToLoad(WebElement wbObject){
//		int intpointer=0;
//		try{			
//			do{
//				if(verifyObjectPresence(wbObject)){
//					intpointer=0;
//					break;
//				}
//				else{
//					intpointer=intpointer+1;					
//					if(intpointer>3){
//						break;
//					}
//				}					
//			}
//			while(intpointer!=0);
//		}catch(Exception e){					
//			getLogger().info(" Error : "+e);
//			return false;
//		}
//		if(intpointer==0){
//			return true;	
//		}else{
//			return false;	
//		}		
//	}
//	
//	/**
//	 * 
//	 * This method will verify if the given element is present in the current page 
//	 * 
//	 * @param strObjectProperty,strElementType
//	 * @return boolean
//	 */
//	public static boolean verifyObjectPresence(WebElement wbObject){			
//		try {			
//			wbObject.getSize();			
//			return true;		
//		}catch(Exception e){
//			return false;
//		}			
//	}
//	
//	/**
//	 * 
//	 * This method will retrieve the text of the column from a table
//	 * 
//	 * @param eleTable,strColumnName
//	 * @return strData
//	 */
//	public static String[] ColumnValuesFromTable(List<WebElement> eleTable, String strColumnName) {
//		CommonPage.WaitTillObjectDisappers(ele_BlankTableMessage);
//		List<WebElement> lstDOHeaderTblRows = eleTable.get(0).findElements(By.tagName("td"));		
//		List<WebElement> lstDOTblRows = eleTable.get(1).findElements(By.tagName("tr"));
//		String[] strData = new String[lstDOTblRows.size()-1];
//		int intRowpointer = 0;
//		try{				
//			for(int intInd=0;intInd<lstDOHeaderTblRows.size();intInd++){
//				if(lstDOHeaderTblRows.get(intInd).getText().equals(strColumnName)){
//					intRowpointer=intInd;
//					break;
//				}
//			}						
//			for(int intInd=0;intInd<lstDOTblRows.size()-1;intInd++) {
//				List<WebElement> lstDOTblCols = lstDOTblRows.get(intInd).findElements(By.tagName("td"));
//				if(lstDOTblCols.get(intRowpointer).getText().toString()!=null) {
//					strData[intInd]= lstDOTblCols.get(intRowpointer).getText().toString().trim();
//				}
//			}             		  
//		}
//		catch(Exception e) {
//			Assert.fail("Unable to retrieve the data from Web Table, due to - " + e);
//		}
//		return strData;
//	}
//	
//	public static int returnDuplicateCount(String[] strArray,String strValue) {
//		int  intcount = 0;        
//		for(int intInd = 0; intInd < strArray.length ; intInd++){
//			if((strArray[intInd]) == strValue)
//				intcount++;
//			}
//		getLogger().info("Count of "+strValue+" : " +intcount);
//		return intcount;
//	}
//	
//	/**
//	 * 
//	 * This method will retrieve the Environment name from the application
//	 * @param none
//	 * @return strENV
//	 */
//	public static String checkEnvType()
//    {
//		String strEnvType = "";
//		try{			
//			if("ONT||SAV||LUG".contains(TestConstants.strEnv)){
//				strEnvType = "SA";
//			}else if("WOO||TUC".contains(TestConstants.strEnv)){
//				strEnvType = "OVP";
//			}else if("MID".contains(TestConstants.strEnv)){
//				
//			}else{
//				getLogger().info("Environment Type Not Found ");
//			}
//		}catch(Exception e){
//			Assert.fail("Unable to check the environment type, due to - " + e);
//		}
//		return strEnvType;                      
//    }
//
///**
//	 * 
//	 * This method will retrieve the Environment name from the application
//	 * 
//	 * @param none
//	 * @return strENV
//	 */
//	public static String retrieve_ENV()
//    {
//		_driver.switchTo().defaultContent();
//		String strENV = null;         
//		try{
//			strENV = env_name.getText();
//			WebDriverHelper.wait(1.0);
//			strENV = (strENV.split(" ")[0]).trim();			    
//			getLogger().info("The Current environment : " + strENV);
//			TestConstants.strEnv = strENV;
//			WebDriverHelper.wait(1.0);  	
//		}catch(Exception e){
//			Assert.fail("Unable to retrieve name of the Environment from WMS, due to - " + e);
//		}
//		return strENV;                        
//    }
//
//	public static String combineDataValues(String strDataValue[], String strSymbol)
//	{	
//		String strSingleValue="";
//		for(int intInd=0;intInd<strDataValue.length;intInd++){
//			if(intInd==0){
//				strSingleValue=strDataValue[intInd];
//			}else{
//				strSingleValue=strSingleValue+strSymbol+strDataValue[intInd];	
//			}			
//		}
//		return strSingleValue;
//	}
//	
//	/**
//	 * 
//	 * This method removes null values from array and returns reduced array
//	 * 
//	 * @param strArray
//	 * @return array
//	 */
//	public static String[] removeNull(String[] strArray) {	   
//	    List<String> list = new ArrayList<String>();
//	    for(String str : strArray) {
//	       if(str != null && str.length() > 0) {
//	          list.add(str);
//	       }
//	    }
//	    strArray = list.toArray(new String[list.size()]);
//	    return strArray;
//	  }
//	  
//	  /**
//	 * 
//	 * This method will wait for an text to appears
//	 * 
//	 * @param strObject,strObjectType
//	 * @return intpointer
//	 */
//	public static boolean WaitForTextToLoad(WebElement wbObject, String strText){
//		int intpointer=0;
//		try{			
//			do{
//				String strTemp="";
//				if(verifyObjectPresence(wbObject)){	
//					strTemp = wbObject.getText();
//				}
//				else{
//					intpointer=intpointer+1;					
//					if(intpointer>2){
//						break;
//					}
//				}				
//				if(strText.equals(strTemp)){
//					intpointer=0;
//					break;
//				}
//			}
//			while(intpointer!=0);
//		}catch(Exception e){					
//			getLogger().info("Error : " + e);
//			return false;
//		}
//		if(intpointer==0){
//			return true;	
//		}else{
//			return false;	
//		}		
//	}
//}