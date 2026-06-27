import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PureSeleniumTest {

    @Test
    public void testSelenoid() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion("146.0");

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        options.setCapability("selenoid:options", selenoidOptions);

        System.setProperty("java.net.useSystemProxies", "true");
        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        System.setProperty("jdk.http.auth.proxying.disabledSchemes", "");

        WebDriver driver = new RemoteWebDriver(
                new URL("https://user1:1234@selenoid.autotests.cloud/wd/hub"),
                options
        );

        driver.get("https://habr.com");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}