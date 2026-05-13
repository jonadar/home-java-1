package Utils;

public class Validation {
	/**
	 * @param str String to check
	 * @param failMessage String message if validation fails
	 * @return true if str is not null or empty
	 */
	public static boolean validate(String str, String failMessage) {
		if (str == null || str.isEmpty()) {
			System.out.println(failMessage);
			return false;
		}
		return true;
	}
	
	/**
	 * @param num int to check
	 * @param failMessage String message if validation fails
	 * @return true if num >= 0
	 */
	public static boolean validate(int num, String failMessage) {
		if (num < 0) {
			System.out.println(failMessage);
			return false;
		}
		return true;
	}

	/**
	 * @param num double to check
	 * @param failMessage String message if validation fails
	 * @return true if num >= 0
	 */
	public static boolean validate(double num, String failMessage) {
		if (num < 0) {
			System.out.println(failMessage);
			return false;
		}
		return true;
	}
	
	/**
	 * @param newItem Object to check if already in array
	 * @param items Object[] of items to compare to newItem
	 * @param failMessage String message if validation fails
	 * @return true if newItem not in array
	 */
	public static boolean validateNotInArray(Object newItem, Object[] items, String failMessage) {
		for(Object item: items) {
			if (item.equals(newItem)) return false;
		}
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @param failMessage String message if validation fails
	 * @return true if str is not null or empty
	 */
	public static boolean isEmail(String str) {
		// <pre>@<domain>
		// <pre> is up to 64 letters and can be _-.(1-9)(a-z)
		// <domain> is first part up to 20 letters and contains -(1-9)(a-z) followed by . and 2 or more characters (.com)
		
		//TODO
		return true;
	}
}
