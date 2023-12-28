import com.codeborne.selenide.Configuration;

public class ChromeProperties {


    public static final String ROOT_PATH = "";
    public static final String CHROME_DRIVER_PATH =
            "E:\\Merge\\2023下\\SoftTest\\H7\\env\\chromedriver-win64\\chromedriver.exe";

    public static final String CHROME_BIN_PATH =
            "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
    public static final String TEST_URL = "http://192.168.2.169:1080/webtours/";

    public static final String USER_NAME = "A";
    public static final String PASSWORD = "A";

    public static void load() {
        System.setProperty("webdriver.chrome.driver", ChromeProperties.CHROME_DRIVER_PATH);
        System.setProperty("webdriver.chrome.bin", ChromeProperties.CHROME_BIN_PATH);
        System.setProperty("webdriver.chrome.whitelistedIps", "");
        Configuration.webdriverLogsEnabled = true;
        Configuration.timeout = 7000;
    }

    static {
        // 设置浏览器运行完不关闭
        Configuration.holdBrowserOpen = true;
    }
}
