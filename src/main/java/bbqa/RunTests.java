package bbqa;

import java.util.List;

import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

public class RunTests {
	static String browserName;
	static String browserVersion;
	static boolean useRemoteWebDriver;
	static boolean headless;
	static boolean proxyEnabled;
	static String proxyHost;
	static Integer proxyPort;
	static String proxyUsername;
	static String proxyPassword;
	static String proxyDetails;

	public static void main(String[] args) {
		// Get the path to testng.xml
		String xmlPath = System.getProperty("user.dir") + "/TestNG.xml";
		browserName = getCommandLineProperty("browserName");
		browserVersion = getCommandLineProperty("browserVersion");
		useRemoteWebDriver = Boolean.valueOf(getCommandLineProperty("useRemoteWebDriver"));
		headless = Boolean.valueOf(getCommandLineProperty("headless"));
		proxyEnabled = Boolean.valueOf(getCommandLineProperty("proxyEnabled"));
		if(proxyEnabled) {
		proxyHost = getCommandLineProperty("proxyHost");
		proxyPort = Integer.parseInt(getCommandLineProperty("proxyPort"));
		proxyUsername = getCommandLineProperty("proxyUsername");
		proxyPassword = getCommandLineProperty("proxyPassword");
		proxyDetails = getCommandLineProperty("proxyDetails");
		}else {
			System.out.println("Ignoring proxy parameters as proxy is NOT enabled");
		}
		System.out.println(browserName);
		System.out.println(browserVersion);
		System.out.println(useRemoteWebDriver);
		System.out.println(headless);
		System.out.println(proxyEnabled);
		System.out.println(proxyHost);
		System.out.println(proxyPort);
		System.out.println(proxyUsername);
		System.out.println(proxyPassword);
		System.out.println(proxyDetails);
		
		// Run all the tests in testng.xml
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(xmlPath);
		testng.setTestSuites(suites);
		testng.run();
	}

	public static String getCommandLineProperty(String dParameter) {
		dParameter = System.getProperty(dParameter);
		return dParameter;

	}
}