package old.utils;

import java.util.ArrayList;

/**
 * A collection of page validation results
 */
public class ValidationResults extends ArrayList<ValidationResult> {

	private static final long serialVersionUID = 1L;


	/**
	 * Add a validation result to the collection.
	 * 
	 * @param pageName Name of page.
	 * @param itemName Name of item.
	 * @param description Description of outcome.
	 * @param isValid Is it valid?
	 */
	public void addValidation(String pageName, String itemName, String description, boolean isValid) {
		ValidationResult validation = new ValidationResult(pageName, itemName, description, isValid);
		this.add(validation);
	}


	/**
	 * Add a validation result to the collection.
	 * 
	 * @param pageName Name of page.
	 * @param itemName Name of item.
	 * @param isValid Is it valid?
	 * @param invalidDescription Description of failure.
	 * @param validDescription Description of success.
	 */
	public void addValidation(String pageName, String itemName, boolean isValid, String validDescription, String invalidDescription) {
		addValidation(pageName, itemName, isValid ? validDescription : invalidDescription, isValid);
	}

	/**
	 * Determine if everything is valid by looking at the validation results in
	 * the collection.
	 * 
	 * @return True if all validation results are true, false if not.
	 */
	public boolean isValid() {
		for (ValidationResult validation : this) {
			if (!validation.isValid())
				return false;
		}
		return true;
	}

	/**
	 * Retrieve the valid count.
	 * 
	 * @return The valid count.
	 */
	public int getValidCount() {
		int validCount = 0;
		for (ValidationResult result : this) {
			if (result.isValid())
				validCount++;
		}
		return validCount;
	}

	/**
	 * Retrieve the invalid count.
	 * 
	 * @return The invalid count.
	 */
	public int getInvalidCount() {
		int invalidCount = 0;
		for (ValidationResult result : this) {
			if (!result.isValid())
				invalidCount++;
		}
		return invalidCount;
	}

	/**
	 * Retrieve number of validations.
	 * 
	 * @return The number of validations.
	 */
	public int getValidationCount() {
		return this.size();
	}

	/**
	 * Build a string representation of a ValidationResults collection for
	 * logging.
	 * 
	 * @return An XML string representing all page validation results.
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		if (this.getValidationCount() > 0) {
			sb.append(" === Validation Results ===\r\n");
			sb.append(" Valid: " + isValid() + "\r\n");
			sb.append(" Count: " + getValidationCount() + "\r\n");
			sb.append(" Valid Count: " + getValidCount() + "\r\n");
			sb.append(" Invalid Count: " + getInvalidCount() + "\r\n");

			sb.append(String.format(" %-5s %-25s %-20s %s\r\n", "Valid", "Page", "Item",
					"Description"));
			sb.append(String.format(" %-5s %-25s %-20s %s\r\n", "-----", "----", "----",
					"-----------"));
			for (ValidationResult result : this) {
				sb.append(" " + result.toString() + "\r\n");
			}
			sb.append(" ==========================");
		} else {
			sb.append(" No validations performed.");
		}

		return sb.toString();
	}

	/**
	 * Build a string representation of a ValidationResults collection for
	 * logging.
	 * 
	 * @return An HTML string representing all page validation results.
	 */
	public String toHtml() {

		StringBuilder sb = new StringBuilder();

		final String classTable = "google-visualization-table-table";
		final String classTableHeaderRow = "google-visualization-table-tr-head";
		final String classTableHeaderCell = "google-visualization-table-th gradient";
		final String classRowOdd = "google-visualization-table-tr-odd";
		final String classRowEven = "google-visualization-table-tr-even";
		final String classCell = "google-visualization-table-td";

		String color = "Green";
		if (!isValid()) {
			color = "Red";
		}

		sb.append("<div><b>Validation Results</b></div>\r\n");

		sb.append("<table class=\"" + classTable + "\" style=\"width:auto\">\r\n");
		sb.append(" <tr class=\"" + classRowOdd + "\">\r\n");
		sb.append("  <td class=\"" + classCell + "\">Passed Validation</td>\r\n");
		sb.append("  <td class=\"" + classCell + "\" style=\"font-weight:bold;color:" + color
				+ ";\">" + isValid() + "</td>\r\n");
		sb.append(" </tr>\r\n");
		sb.append(" <tr class=\"" + classRowEven + "\">\r\n");
		sb.append("  <td class=\"" + classCell + "\">Validations Performed</td>\r\n");
		sb.append("  <td class=\"" + classCell + "\">" + getValidationCount() + "</td>\r\n");
		sb.append(" </tr>\r\n");
		sb.append(" <tr class=\"" + classRowOdd + "\">\r\n");
		sb.append("  <td class=\"" + classCell + "\">Validations Passed</td>\r\n");
		sb.append("  <td class=\"" + classCell + "\">" + getValidCount() + "</td>\r\n");
		sb.append(" </tr>\r\n");
		sb.append(" <tr class=\"" + classRowEven + "\">\r\n");
		sb.append("  <td class=\"" + classCell + "\">Validations Failed</td>\r\n");
		sb.append("  <td class=\"" + classCell + "\">" + getInvalidCount() + "</td>\r\n");
		sb.append(" </tr>\r\n");
		sb.append("</table>\r\n");

		sb.append("<table class=\"" + classTable + "\">\r\n");
		sb.append(" <tr class=\"" + classTableHeaderRow + "\">\r\n");
		sb.append("  <td class=\"" + classTableHeaderCell + "\">Valid</td>\r\n");
		sb.append("  <td class=\"" + classTableHeaderCell + "\">Page</td>\r\n");
		sb.append("  <td class=\"" + classTableHeaderCell + "\">Item</td>\r\n");
		sb.append("  <td class=\"" + classTableHeaderCell + "\">Description</td>\r\n");
		sb.append(" </tr>\r\n");

		int rowCount = 1;
		for (ValidationResult result : this) {
			String cellColor = "Green";
			if (!result.isValid()) {
				cellColor = "Red";
			}
			String rowClass = classRowOdd;
			if (rowCount % 2 == 0) {
				rowClass = classRowEven;
			}
			sb.append(" <tr class=\"" + rowClass + "\">\r\n");
			sb.append("  <td class=\"" + classCell + "\" style=\"font-weight:bold;color:"
					+ cellColor + ";\">" + result.isValid() + "</td>\r\n");
			sb.append("  <td class=\"" + classCell + "\">" + result.getPageName() + "</td>\r\n");
			sb.append("  <td class=\"" + classCell + "\">" + result.getItemName() + "</td>\r\n");
			sb.append("  <td class=\"" + classCell + "\">" + result.getDescription() + "</td>\r\n");
			sb.append(" </tr>\r\n");

			rowCount++;
		}
		sb.append("</table>");

		return sb.toString();
	}
}
