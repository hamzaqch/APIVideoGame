package videoGameAPI.Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListener  implements ITestListener{
	
	// User Defined Method
	private static  String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(getTestMethodName(result) + " Start");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(getTestMethodName(result) + " Succeed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(getTestMethodName(result) + " Failed");

		saveTextLog(getTestMethodName(result));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName());
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName());	
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(getTestMethodName(result) + "Failed Within Success Percentage");
	}


}
