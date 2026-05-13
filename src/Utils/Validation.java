package Utils;

import java.util.Arrays;

public class Validation {
	public static void main(String[] args) {
		System.out.println(validDate(""));
		System.out.println(validDate("///"));
		System.out.println(validDate("0/0/0"));
		System.out.println(validDate("12/1/2001"));
		System.out.println(validDate("2152"));
	}
	
	/**
	 * @param str String to check
	 * @param failMessage String message if validation fails
	 * @return true if str is not null or empty
	 */
	public static boolean validate(String str, String failMessage) {
		if (str == null || str.isBlank()) {
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
	 * @param date String to check
	 * @return true if valid date otherwise false
	 */
	public static boolean validDate(String date) {
		if (date == null || date.isBlank()) return false;
		
		String[] splitDate = date.split("/");
		if(splitDate.length != 3) return false;
		
		if(splitDate[0].length() > 2 || splitDate[0].length() == 0) return false;
		if(splitDate[1].length() > 2 || splitDate[1].length() == 0) return false;
		if(splitDate[2].length() != 4) return false;
		
		int day = Utilities.StringToInt(splitDate[0]);
		int month = Utilities.StringToInt(splitDate[1]);
		int year = Utilities.StringToInt(splitDate[2]);
		
		if (day < 0 || day > 31) return false;
		if (month < 1 || month > 12) return false;
		if (year < 2000 || year > 2026) return false;
		
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
