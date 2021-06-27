package resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import bbqa.SeleniumBase;

public class TestListener extends SeleniumBase implements ITestListener {

	private static final Logger LOG = (Logger) LogManager.getLogger(TestListener.class);

	@Override
	public void onFinish(ITestContext Result) {

	}

	@Override
	public void onStart(ITestContext Result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

	// When Test case get failed, this method is called.
	@Override
	public void onTestFailure(ITestResult Result) {
		LOG.warn("The name of the testcase failed is :" + Result.getName());
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File("FailedScreenShots/" + Result.getName() + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}

	// When Test case get Skipped, this method is called.
	@Override
	public void onTestSkipped(ITestResult Result) {
		LOG.warn("The name of the testcase Skipped is :" + Result.getName());
	}

	// When Test case get Started, this method is called.
	@Override
	public void onTestStart(ITestResult Result) {
		Log.info(Result.getName() + " test case started");
	}

	// When Test case get passed, this method is called.
	@Override
	public void onTestSuccess(ITestResult Result) {
		Log.info("The name of the testcase passed is :" + Result.getName());
	}

}