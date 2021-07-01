package bbqa;

import java.util.Collections;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import resources.Logg;

public class SeleniumBase extends RunTests {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static String getInfo() {
		Capabilities cap = ((RemoteWebDriver) SeleniumBase.getDriver()).getCapabilities();
		String browserName = cap.getBrowserName();
		String platform = cap.getPlatform().toString();
		String version = cap.getVersion();
		return String.format("browser: %s v: %s platform: %s", browserName, version, platform);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUpDriver() {
		DesiredCapabilities capabilities = null;
		if (getBrowserName().equalsIgnoreCase("chrome")) {
			System.out.println(getBrowserName());
			Logg.info("Launching CHROME browser");
			WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
			/*
			 * HashMap<String, Object> chromePreferences = new HashMap<>();
			 * chromePreferences.put("profile.password_manager_enabled", false);
			 * options.setExperimentalOption("prefs", chromePreferences);
			 */
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(headless);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.merge(capabilities);
			if (proxyEnabled) {
				options.setProxy(setupProxy());
			}
			setDriver(new ChromeDriver(options));
			getDriver().manage().window().maximize();

		} else if (getBrowserName().equalsIgnoreCase("firefox")) {
			Logg.info("Launching FIREFOX browser");
			WebDriverManager.firefoxdriver().driverVersion(browserVersion).setup();
			capabilities = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			options.setHeadless(headless);
			options.merge(capabilities);
			if (proxyEnabled) {
				options.setProxy(setupProxy());
			}
			setDriver(new FirefoxDriver(options));
			getDriver().manage().window().maximize();

		} else if (getBrowserName().equalsIgnoreCase("edge")) {
			Logg.info("Launching EDGE browser");
			capabilities = DesiredCapabilities.edge();
			capabilities.setCapability("screenResolution", "1280x1024");
			EdgeOptions options = new EdgeOptions();
			options.merge(capabilities);
			if (proxyEnabled) {
				options.setProxy(setupProxy());
			}
			setDriver(new EdgeDriver(options));
			getDriver().manage().window().maximize();

		} else {
			Logg.info("Launching CHROME browser");
			WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
			capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.setHeadless(headless);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			options.merge(capabilities);
			if (proxyEnabled) {
				options.setProxy(setupProxy());
			}
			setDriver(new ChromeDriver(options));
			getDriver().manage().window().maximize();
		}
	}

	public Proxy setupProxy() {
		Proxy proxy = new Proxy();
		proxy.setAutodetect(false);
		proxy.setHttpProxy("http://" + proxyHost + ":" + proxyPort);
		proxy.setSslProxy("https://" + proxyHost + ":" + proxyPort);
		proxy.setNoProxy("<no_proxy-var>");
		return proxy;
	}

	@AfterMethod(alwaysRun = true)
	public void clearCookies() {
		if (driver != null) {
			driver.get().manage().deleteAllCookies();
			System.out.println("deleted cookies");
			driver.get().close();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.get().quit();
			driver.remove();
		}
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver driver) {
		SeleniumBase.driver.set(driver);
	}
}
