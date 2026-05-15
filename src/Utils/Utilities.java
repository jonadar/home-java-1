package Utils;

public class Utilities {
	
	public static void main(String[] args) {}
	
	/**
	 * only works for positive integers
	 * @param str string to convert to int
	 * @return int from str otherwise -1
	 */
	public static int StringToPositiveInt(String str) {
		if(str == null || str.isBlank()) return -1;
		
		// check all digits
		if(!Validation.isOnlyDigits(str)) return -1;
		
		// all characters are digits, convert to int
		int num = 0;
		for (int i = str.length() - 1; i >= 0 ; i--) {
			num += (str.charAt(i) - '0') * Math.pow(10, str.length()-(i+1));
		}
		
		return num;
	}
}
