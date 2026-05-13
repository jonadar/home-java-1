package Utils;

public class Utilities {
	
	public static void main(String[] args) {
		System.out.println(StringToInt("242"));
		System.out.println(StringToInt("6122"));
		System.out.println(StringToInt("000421512"));
		System.out.println(StringToInt("02512.23421"));
		System.out.println(StringToInt(""));
	}
	
	/**
	 * only works for positive integers
	 * @param str string to convert to int
	 * @return int from str otherwise -1
	 */
	public static int StringToInt(String str) {
		if(str == null || str.isBlank()) return -1;
		
		// check all digits
		for (int i = 0; i < str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) return -1;
		}
		
		// all characters are digits, convert to int
		int num = 0;
		for (int i = str.length() - 1; i >= 0 ; i--) {
			num += (str.charAt(i) - '0') * Math.pow(10, str.length()-(i+1));
		}
		
		return num;
	}
}
