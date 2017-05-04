package old.utils;
//package com.wms.manhattan.utils;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.configuration.CombinedConfiguration;
//import org.apache.commons.configuration.ConfigurationException;
//import org.apache.commons.configuration.HierarchicalConfiguration;
//import org.apache.commons.configuration.XMLConfiguration;
//import org.apache.commons.configuration.tree.DefaultExpressionEngine;
//import org.apache.commons.configuration.tree.NodeCombiner;
//import org.apache.commons.configuration.tree.UnionCombiner;
//import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
///**
// * Class to contain settings that affect the way tests are performed. A
// * properties file is passed to the constructor that contains basic settings,
// * but it is possible that this class will be extended to include data from
// * other sources as well such as Excel files. Wrapping multiple data sources
// * together in a single object should make passing this data to tests and pages
// * easier.
// * 
// */
//public class TestContext {
//
//	protected static final Logger log = LogManager.getLogger("TestContext");
//
//	protected CombinedConfiguration _baseConfig = null;
//	protected HierarchicalConfiguration _runProfile = null;
//	protected Properties _userProperties = new Properties();
//	protected String _dataProfileName = null;
//
//	/**
//	 * Construct an instance of a TestContext.
//	 * 
//	 * @param configurationFile
//	 *            An XML file containing settings.
//	 */
//	public TestContext(String propertiesFile) {
//		loadProperties(propertiesFile);
//	}
//
//	/**
//	 * Load an XML configuration file into this TestContext.
//	 * 
//	 * @param configurationFile
//	 *            The path to an XML configuration file.
//	 */
//	public void loadProperties(String propertiesFile) {
//
//		try {
//
//			FileInputStream configStream = new FileInputStream("config.properties.user.xml");
//			_userProperties.load(configStream);
//		} catch (Exception ex) {
//			log.warn("No user properties file found.");
//		}
//
//		try {
//			XMLConfiguration config = new XMLConfiguration();
//
//			// use the XPath expression engine
//			// http://commons.apache.org/proper/commons-configuration/userguide/howto_xml.html#The_XPATH_expression_engine
//			config.setExpressionEngine(new XPathExpressionEngine());
//			config.setDelimiterParsingDisabled(true);
//			config.setAttributeSplittingDisabled(true);
//			config.load(propertiesFile);
//
//			// Create and initialize the node combiner
//			NodeCombiner combiner = new UnionCombiner();
//
//			// Construct the combined configuration
//			CombinedConfiguration cc = new CombinedConfiguration(combiner);
//			cc.addConfiguration(config);
//
//			for (Object includeElementObject : config.getList("includes/include")) {
//				String file = includeElementObject.toString();
//
//				XMLConfiguration includedConfig = new XMLConfiguration();
//				includedConfig.setExpressionEngine(new DefaultExpressionEngine());
//				includedConfig.setDelimiterParsingDisabled(true);
//				includedConfig.setAttributeSplittingDisabled(true);
//				includedConfig.load(file);
//
//				cc.addConfiguration(includedConfig);
//			}
//
//			_baseConfig = cc;
//
//			// Wire up the desired run configuration (allowing for cascading
//			// overrides)
//			String desiredRunProfile = System.getProperty("selenium.tests.runProfile");
//			if (desiredRunProfile == null || desiredRunProfile.isEmpty()) {
//				desiredRunProfile = _userProperties.getProperty("runProfile");
//				if (desiredRunProfile == null || desiredRunProfile.isEmpty()) {
//					String defaultRunProfile = _baseConfig.getString("runProfiles/@default");
//					desiredRunProfile = _baseConfig.getString("runProfile", defaultRunProfile);
//				}
//			}
//
//			_runProfile = config.configurationAt("runProfiles/runProfile[@name='" + desiredRunProfile + "']");
//
//			// Determine the desired data configuration (allow for cascading
//			// overrides)
//			String desiredDataProfile = System.getProperty("selenium.tests.dataProfile");
//			if (desiredDataProfile == null || desiredDataProfile.isEmpty()) {
//				desiredDataProfile = _userProperties.getProperty("dataProfile");
//				if (desiredDataProfile == null || desiredDataProfile.isEmpty()) {
//					desiredDataProfile = _baseConfig.getString("dataProfile");
//					if (desiredDataProfile == null || desiredDataProfile.isEmpty()) {
//						desiredDataProfile = _runProfile.getString("dataProfile");
//					}
//				}
//			}
//
//			_dataProfileName = desiredDataProfile;
//
//			log.info("Profiles: runProfile = " + desiredRunProfile + ", dataProfile = " + desiredDataProfile);
//
//		} catch (ConfigurationException cex) {
//			log.error("ERROR: Could not load configuration file: " + propertiesFile, cex.getMessage());
//		}
//	}
//
//
//	/**
//	 * Test to see if a configuration property equals the passed in value.
//	 * @param propertyName The name of the property to test.
//	 * @param propertyValue The value to test against.
//	 * @return true if property equals the value passed in
//	 */
//	public boolean propertyEquals(String propertyName, String propertyValue) {
//		boolean areEqual = false;
//		String configValue = getStringProperty(propertyName);
//		if (configValue != null) {
//			areEqual = configValue.trim().equalsIgnoreCase(propertyValue);
//		}
//		return areEqual;
//	}
//
//	/**
//	 * Retrieve a string property value.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * 
//	 * @return The integer property value
//	 */
//	public String getStringProperty(String propertyName) {
//		return getStringProperty(propertyName, null);
//	}
//
//	/**
//	 * Set an override property.
//	 * 
//	 * @param propertyName
//	 * @param value
//	 */
//	public void setStringProperty(String propertyName, String value) {
//		_userProperties.setProperty(propertyName, value);
//	}
//
//	/**
//	 * Retrieve a string property value or a default value if not defined.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * @param defaultValue
//	 *            A default value if the property is not defined.
//	 * 
//	 * @return The integer property value
//	 */
//	public String getStringProperty(String propertyName, String defaultValue) {
//
//		// resolve any replacements
//		String returnValue = defaultValue;
//
//		// look in base configuration
//		if (_baseConfig != null) {
//			returnValue = _baseConfig.getString(propertyName, defaultValue);
//		}
//
//		// look in run profile
//		if (_runProfile != null) {
//			returnValue = _runProfile.getString(propertyName, returnValue);
//		}
//
//		// look in data profile
//		// Warning: This will be removed soon in favor of using
//		// getContext().getDataProfile().getString("foo");
//		if (_dataProfileName != null && !_dataProfileName.isEmpty()) {
//			HierarchicalConfiguration dataProfile = _baseConfig.configurationAt(_dataProfileName);
//			if (dataProfile != null) {
//				returnValue = dataProfile.getString(propertyName, returnValue);
//			}
//		}
//
//		// allow overrides from user properties file
//		if (_userProperties != null) {
//			returnValue = _userProperties.getProperty(propertyName, returnValue);
//		}
//
//		// Allow for system property overrides
//		// for any property you are going after you can
//		// set a system property when you run the JVM named
//		// selenium.tests.<propertyName>
//		// example:
//		// java -Dselenium.tests.baseUrl=http://fooBar
//		returnValue = System.getProperty("selenium.tests." + propertyName, returnValue);
//
//		// resolve any replacements
//		returnValue = replaceVariables(returnValue);
//
//		if (returnValue == null) {
//			log.warn("Config property '" + propertyName + "' was null");
//		}
//
//		return returnValue;
//	}
//
//	/**
//	 * Retrieve a string property value or a default value if not defined.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * @param defaultValue
//	 *            A default value if the property is not defined.
//	 * 
//	 * @return The integer property value
//	 */
//	public HierarchicalConfiguration getDataProfile(String dataProfileName) {
//		if (dataProfileName != null && !dataProfileName.isEmpty()) {
//			return _baseConfig.configurationAt(dataProfileName);
//		}
//		return null;
//	}
//
//	/**
//	 * Retrieve a string property value or a default value if not defined.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * @param defaultValue
//	 *            A default value if the property is not defined.
//	 * 
//	 * @return The integer property value
//	 */
//	public HierarchicalConfiguration getDataProfile() {
//		if (_dataProfileName != null && !_dataProfileName.isEmpty()) {
//			return getDataProfile(_dataProfileName);
//		}
//		return null;
//	}
//
//	/**
//	 * Replace any variables in the property value.
//	 * 
//	 * Example: baseUrl=${baseUrl.local.cordova}
//	 * baseUrl.local.cordova=http://localhost:8000/ios/www/app/index-mock.html
//	 * baseUrl.local.iis=http://localhost/MyStoreUI/app/index-mock.html
//	 * 
//	 * @param input
//	 *            the input string.
//	 * @return The string with any variables replaced.
//	 */
//	private String replaceVariables(String input) {
//
//		if (input == null)
//			return input;
//
//		Matcher m = Pattern.compile("\\$\\{([^}]*)\\}").matcher(input);
//
//		StringBuffer sb = new StringBuffer();
//		while (m.find()) {
//			String value = getStringProperty(m.group(1), null);
//			if (value != null)
//				m.appendReplacement(sb, value);
//		}
//		m.appendTail(sb);
//		return sb.toString();
//	}
//
//	/**
//	 * Retrieve an integer property value.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * @param defaultValue
//	 *            A default value if the property is not defined.
//	 * 
//	 * @return The integer property value
//	 */
//	public int getIntProperty(String propertyName, int defaultValue) {
//		int returnValue = defaultValue;
//		String stringValue = getStringProperty(propertyName, null);
//		try {
//			returnValue = Integer.parseInt(stringValue);
//		} catch (Exception ex) {
//		}
//		return returnValue;
//	}
//
//	/**
//	 * Retrieve a double property value.
//	 * 
//	 * @param propertyName
//	 *            The name of the property to retrieve.
//	 * @param defaultValue
//	 *            A default value if the property is not defined.
//	 * 
//	 * @return The integer property value
//	 */
//	public double getDoubleProperty(String propertyName, double defaultValue) {
//		double returnValue = defaultValue;
//		String stringValue = getStringProperty(propertyName, null);
//		try {
//			returnValue = Double.parseDouble(stringValue);
//		} catch (Exception ex) {
//		}
//		return returnValue;
//	}
//}
