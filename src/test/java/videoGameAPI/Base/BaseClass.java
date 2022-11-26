package videoGameAPI.Base;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	protected static Logger logger;
	private static Properties pro;
	private static FileInputStream inputFile;

	@BeforeClass
	public void setupTest() {
		logger = Logger.getLogger(getClass());
		try {
			pro = new Properties();
			inputFile = new FileInputStream("log4j.properties");
			logger.setLevel(Level.DEBUG);
			pro.load(inputFile);
			PropertyConfigurator.configure(pro);
		} catch (Exception e) {
			System.out.println("Exception Message " + e.getMessage());
		}
		logger.info("Test " +  getClass() + " is Start");
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("Test " +  getClass() + " is finshed");
	}
}

