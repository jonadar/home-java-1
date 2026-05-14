package Utils;

import java.util.Arrays;

public class Validation {
	public static void main(String[] args) {}
	
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
	 * @return true if not null or only whitespaces
	 */
	public static boolean isEmptyString(String str) {
		return str == null || str.isBlank();
	}
	
	/**
	 * @param str String to check
	 * @return true if only contains alphabetic and number chars
	 */
	public static boolean isOnlyNumbersAndChars(String str) {
		if (str == null || str.isBlank()) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isLetterOrDigit(ch)) return false;
		}
		
		return true;
	}
	
	/**
	 * @param str String to check
	 * @return true if only contains alphabetic chars
	 */
	public static boolean isOnlyChars(String str) {
		if (str == null || str.isBlank()) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isAlphabetic(ch)) return false;
		}
	
		return true;	
	}

	/**
	 * @param str String to check
	 * @return true if only contains digits
	 */
	public static boolean isOnlyDigits(String str) {
		if (str == null || str.isBlank()) return false;
		
		for (char ch: str.toCharArray()) {
			if (!Character.isDigit(ch)) return false;
		}
	
		return true;	
	}
	
	
	/**
	 * @param str String to check
	 * @return true if str is valid email, otherwise false
	 */
	public static boolean isEmail(String str) {
		if (str == null || str.isBlank()) return false;
		
		for (char ch: str.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == '@')) {
				return false;
			}
		}
		
		// has a '@' and only 1.
		if (str.contains("@") && str.indexOf('@') == str.lastIndexOf('@')) return true;
		
		return false;
	}
	
	/**
	 * @param str String to check
	 * @return true if str is valid adress, otherwise false
	 */
	public static boolean isCity(String str) {
		if (str == null || str.isBlank()) return false;
		
		for (char ch: str.toCharArray()) {
			if (!(Character.isAlphabetic(ch) || ch == ' ')) {
			}
		}
		
		return true;
	}
	
	public static boolean isStreet(String str) {
		if (str == null || str.isBlank()) return false;
		
		// street city mikud
		for (char ch: str.toCharArray()) {
			if (!(Character.isLetterOrDigit(ch) || ch == ' ')) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isMikud(String str) {
		return isOnlyDigits(str);
	}
	
	// not empty, 10 digits
	public static boolean isPhoneNumber(String phoneNumber) {
		return isOnlyDigits(phoneNumber) && phoneNumber.length() == 10;
	}
	
	// not empty, numbers only, length of 9
	public static boolean isId(String id) {
		return isOnlyDigits(id) && id.length() == 9;
	}
	
	// not empty, not only spaces, letters only
	public static boolean isName(String name) {
		if (name == null || name.isBlank()) return false;
		
		for (char ch: name.toCharArray()) {
			if (!(Character.isAlphabetic(ch) || ch == ' ')) return false;
		}
	
		return true;
	}
	
	
}
