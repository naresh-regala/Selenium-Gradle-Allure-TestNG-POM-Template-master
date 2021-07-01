package listeners;

import java.util.List;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import resources.Logg;

public class ConfigureSuite implements IAlterSuiteListener {

	@Override
	public void alter(List<XmlSuite> suites) {
		int count = Integer.parseInt(System.getProperty("threadcount", "3"));
		Logg.info("Configured number of parallel threads: " + count);
		XmlSuite suite = suites.get(0);
		suite.setDataProviderThreadCount(count);
	}
}
