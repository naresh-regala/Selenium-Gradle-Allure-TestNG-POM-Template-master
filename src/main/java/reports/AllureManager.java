package reports;

import org.openqa.selenium.TakesScreenshot;
import static org.openqa.selenium.OutputType.BYTES;

import java.io.IOException;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

import bbqa.RunTests;
import bbqa.SeleniumBase;
import io.qameta.allure.Attachment;
import resources.Utils;

public class AllureManager extends RunTests{

    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() throws IOException {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Website Url", Utils.getKeyValue("baseUrl")).
                        put("Thread Count", String.valueOf(getThreadcount())).
                        put("Headless Mode", String.valueOf(isHeadless())).
                        put("Browser Name", getBrowserName()).
                        put("Browser Version", getBrowserVersion()).
                        put("Is Proxy Enabled", String.valueOf(isProxyEnabled())).
                        put("Proxy Details", getProxyDetails()).
                        put("Use RemoteWebDriver", String.valueOf(isUseRemoteWebDriver())).
                        build());
    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) SeleniumBase.getDriver()).getScreenshotAs(BYTES);
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return SeleniumBase.getInfo();
    }

}
