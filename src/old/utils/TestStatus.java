package old.utils;

import java.util.regex.Pattern;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

/**
 * This class is intended to be used to track the status of a test. In some
 * cases we wish the test to continue running even though there are validation
 * failures, and only fail at the end of a test.
 * 
 * The envisioned usage pattern is demonstrated below. Construct a TestStatus at
 * the beginning of the test. Pass it to validation methods, and then assert at
 * the end of the test whether any validation errors occurred during the test.
 * <code>
 * @Test
 * public void item_Fill_UI_Validation_Single_Location() { 
 * 		TestStatus testStatus = getTestStatus();
 * 
 *       MagicScreenPage magicScreen = MagicScreenPage.navigateTo(getDriver(),
 *       getContext()); validatePage(magicScreen, testStatus);
 *       :
 *       assertAllValidations(testStatus);
 * }
 * </code>
 */
public class TestStatus extends Assertion {

	private ValidationResults _allValidationResults = new ValidationResults();

	/**
	 * protected constructor to prevent someone from directly constructing it.
	 */
	protected TestStatus() {
	}

	/**
	 * Retrieve whether this test is valid or not.
	 * 
	 * @return True if the test is valid, False if not.
	 */
	public boolean isValid() {
		return _allValidationResults.isValid();
	}

	/**
	 * Append errors so they can all be reported at the end of the test.
	 * 
	 * @param errors
	 *            The errors collection from a page.
	 */
	public void appendValidationResults(ValidationResults validations) {
		_allValidationResults.addAll(validations);
	}

	/**
	 * Return all errors.
	 * 
	 * @return
	 */
	public ValidationResults getValidationResults() {
		return _allValidationResults;
	}

	/**
	 * Execute a soft assertion.
	 * 
	 * <code>	
	 * TestStatus status = getTestStatus();
	 * status.assertEquals(ctx.getStringProperty("runProfile"), "local", "MyTest|runProfile");
	 * status.assertEquals(ctx.getStringProperty("remoteSeleniumServer"), null, "MyTest|remoteSeleniumServer");
	 * :
	 * assertAllValidations(status);
	 * </code>
	 */
	@Override
	public void executeAssert(IAssert a) {

		String assertMessage = a.getMessage();

		String page = "Assert";
		String item = assertMessage;
		String description = null;

		if (assertMessage != null) {
			if (assertMessage.contains("|")) {
				String[] messageParts = assertMessage.split(Pattern.quote("|"));
				if (messageParts.length == 2) {
					item = messageParts[0];
					description = messageParts[1];
				} else if (messageParts.length >= 3) {
					page = messageParts[0];
					item = messageParts[1];
					description = messageParts[2];
				}
			}
		}
		System.out.println(description);
		try {
			a.doAssert();
			_allValidationResults.add(new ValidationResult(page, item, "Success", true));
		} catch (AssertionError ex) {
			_allValidationResults.add(new ValidationResult(page, item, ex.getMessage(), false));
		}
	}
}
