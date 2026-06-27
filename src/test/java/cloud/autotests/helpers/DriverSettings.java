package cloud.autotests.helpers;

import cloud.autotests.config.Project;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class DriverSettings {

    public static void configure() {
        // SSL errors
        System.setProperty("selenide.ignore.https.errors", "true");

        // Основные настройки
        Configuration.browser = Project.config.browser();
        Configuration.browserVersion = Project.config.browserVersion();
        Configuration.browserSize = Project.config.browserSize();
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.remote = "https://localhost:4444/wd/hub";

        // Настройки браузера
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--lang=en");

        // Selenoid опции
        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        selenoidOptions.put("screenResolution", "1920x1080x24");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "146.0");
        capabilities.setCapability("selenoid:options", selenoidOptions);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        Configuration.browserCapabilities = capabilities;

        // Отладка
        System.out.println("=== DRIVER SETTINGS ===");
        System.out.println("Remote URL: " + Configuration.remote);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Version: " + Configuration.browserVersion);
        System.out.println("Capabilities: " + Configuration.browserCapabilities);
        System.out.println("========================");
    }
}