package videoGameAPI.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	private static Properties pro;

	// Method to read the DATA from config file
	public static String read(String yourString) {
		final File path = new File(System.getProperty("user.dir") + "/Configuration/config.properties");
		try {
			FileInputStream inputFile = new FileInputStream(path);
			pro = new Properties();
			pro.load(inputFile);
		}
		catch (Exception e) {
			System.out.println("Exception Message: " + e.getMessage());
		}
		return pro.getProperty(yourString);
	}
	
	public static String idData() {
		final File path = new File(System.getProperty("user.dir") + "/data.id");
		try {
			FileInputStream inputFile = new FileInputStream(path);
			pro = new Properties();
			pro.load(inputFile);
		}
		catch (Exception e) {
			System.out.println("Exception Message: " + e.getMessage());
		}
		return pro.getProperty("id");
	}
}
