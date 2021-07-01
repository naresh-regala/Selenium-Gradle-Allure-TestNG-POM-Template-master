package listeners;

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
import io.qameta.allure.Step;
import resources.Logg;

public class TestListener extends SeleniumBase implements ITestListener {

	@Step
	@Override
	public void onFinish(ITestContext Result) {

	}

	@Step
	@Override
	public void onStart(ITestContext Result) {

	}

	@Step
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

	@Step
	@Override
	public void onTestFailure(ITestResult Result) {
		Logg.warn("The name of the testcase failed is :" + Result.getName());
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File("FailedScreenShots/" + Result.getName() + System.currentTimeMillis() + ".png"));
		} catch (IOException e) {
			Logg.error(e.getMessage());
		}
	}

	@Step
	@Override
	public void onTestSkipped(ITestResult Result) {
		Logg.warn("The name of the testcase Skipped is :" + Result.getName());
	}

	@Step
	@Override
	public void onTestStart(ITestResult Result) {
		Logg.info(Result.getName() + " test case started");
	}

	@Step
	@Override
	public void onTestSuccess(ITestResult Result) {
		Logg.info("The name of the testcase passed is :" + Result.getName());
	}

}