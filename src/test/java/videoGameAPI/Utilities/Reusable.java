package videoGameAPI.Utilities;

import org.apache.commons.lang3.RandomStringUtils;


public class Reusable {
	
	// Random string generator
	public static String randomString(int chartnum) {
		return RandomStringUtils.randomAlphabetic(chartnum);
	}

	// Random Number generator
	public static String randomNumber(int countnum) {
		return RandomStringUtils.randomNumeric(countnum);
	}
	
}
