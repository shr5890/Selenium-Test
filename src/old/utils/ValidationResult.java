package old.utils;

/**
 * Represents a single validation result.
 */
public class ValidationResult {

	private String _pageName;
	private String _itemName;
	private String _description;
	private boolean _isValid;

	/**
	 * Construct an instance of a ValidationResult.
	 * 
	 */
	public ValidationResult(String pageName, String itemName, String description, boolean isValid) {
		_pageName = pageName;
		_itemName = itemName;
		_description = description;
		_isValid = isValid;
	}

	/**
	 * Retrieve the name of the page the validation check was performed on.
	 * 
	 * @return The name of page the validation check was performed on.
	 */
	public String getPageName() {
		return _pageName;
	}

	/**
	 * Retrieve the target of the validation check.
	 * 
	 * @return The target of the validation check.
	 */
	public String getItemName() {
		return _itemName;
	}

	/**
	 * Retrieve the description of the validation check.
	 */
	public String getDescription() {
		return _description;
	}

	/**
	 * Retrieve the description of the validation check.
	 */
	public boolean isValid() {
		return _isValid;
	}

	/**
	 * Constructs a string representation of the validation result for logging.
	 */
	@Override
	public String toString() {
		return String.format(
				"%-5b %-25s %-20s %s",
				isValid(), getPageName(), getItemName(), getDescription());
	}
}
