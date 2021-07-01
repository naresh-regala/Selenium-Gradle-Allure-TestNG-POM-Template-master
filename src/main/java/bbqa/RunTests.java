package bbqa;

import java.util.List;

import org.testng.TestNG;

import com.beust.jcommander.internal.Lists;

import resources.Logg;

public class RunTests {
	private static String browserName;
	static String browserVersion;
	static boolean useRemoteWebDriver;
	static boolean headless;
	static boolean proxyEnabled;
	static String proxyHost;
	static Integer proxyPort;
	static String proxyUsername;
	static String proxyPassword;
	static String proxyDetails;
	static Integer threadcount;

	public static void main(String[] args) {
		String xmlPath = System.getProperty("user.dir") + "/TestNG.xml";
		threadcount = Integer.parseInt(getCommandLineProperty("threadcount"));
		browserName = getCommandLineProperty("browserName");
		browserVersion = getCommandLineProperty("browserVersion");
		useRemoteWebDriver = Boolean.valueOf(getCommandLineProperty("useRemoteWebDriver"));
		headless = Boolean.valueOf(getCommandLineProperty("headless"));
		proxyEnabled = Boolean.valueOf(getCommandLineProperty("proxyEnabled"));
		if (proxyEnabled) {
			proxyHost = getCommandLineProperty("proxyHost");
			proxyPort = Integer.parseInt(getCommandLineProperty("proxyPort"));
			proxyUsername = getCommandLineProperty("proxyUsername");
			proxyPassword = getCommandLineProperty("proxyPassword");
			proxyDetails = getCommandLineProperty("proxyDetails");
		} else {
			Logg.info("Ignoring proxy parameters as proxy is NOT enabled");
		}
		Logg.info("browserName: " + getBrowserName());
		Logg.info("browserVersion: " + browserVersion);
		Logg.info("useRemoteWebDriver: " + useRemoteWebDriver);
		Logg.info("headless: " + headless);
		Logg.info("proxyEnabled: " + proxyEnabled);
		Logg.info("proxyHost: " + proxyHost);
		Logg.info("proxyPort: " + proxyPort);
		Logg.info("proxyUsername: " + proxyUsername);
		Logg.info("proxyPassword: " + proxyPassword);
		Logg.info("proxyDetails: " + proxyDetails);
		Logg.info("threadcount: " + threadcount);

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

	public static String getBrowserName() {
		return browserName;
	}

	public static void setBrowserName(String browserName) {
		RunTests.browserName = browserName;
	}

	public static String getBrowserVersion() {
		return browserVersion;
	}

	public static void setBrowserVersion(String browserVersion) {
		RunTests.browserVersion = browserVersion;
	}

	public static boolean isUseRemoteWebDriver() {
		return useRemoteWebDriver;
	}

	public static void setUseRemoteWebDriver(boolean useRemoteWebDriver) {
		RunTests.useRemoteWebDriver = useRemoteWebDriver;
	}

	public static boolean isHeadless() {
		return headless;
	}

	public static void setHeadless(boolean headless) {
		RunTests.headless = headless;
	}

	public static boolean isProxyEnabled() {
		return proxyEnabled;
	}

	public static void setProxyEnabled(boolean proxyEnabled) {
		RunTests.proxyEnabled = proxyEnabled;
	}

	public static String getProxyHost() {
		return proxyHost;
	}

	public static void setProxyHost(String proxyHost) {
		RunTests.proxyHost = proxyHost;
	}

	public static Integer getProxyPort() {
		return proxyPort;
	}

	public static void setProxyPort(Integer proxyPort) {
		RunTests.proxyPort = proxyPort;
	}

	public static String getProxyDetails() {
		return proxyDetails;
	}

	public static void setProxyDetails(String proxyDetails) {
		RunTests.proxyDetails = proxyDetails;
	}

	public static Integer getThreadcount() {
		return threadcount;
	}

	public static void setThreadcount(Integer threadcount) {
		RunTests.threadcount = threadcount;
	}

}