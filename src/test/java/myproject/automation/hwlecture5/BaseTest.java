package myproject.automation.hwlecture5;

import myproject.automation.hwlecture5.utils.logging.CustomReporter;
import myproject.automation.hwlecture5.utils.logging.EventHandler;
import org.apache.http.util.TextUtils;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseTest {
    protected EventFiringWebDriver driver;
    protected GeneralActions actions;
    protected boolean isMobileTest;
    protected WebDriverWait wait;


    /**
     * Prepares {@link WebDriver} instance with timeout and browser window configurations.
     *
     * Driver type is based on passed parameters to the automation project,
     * creates {@link ChromeDriver} instance by default.
     *
     */
    @BeforeClass
    @Parameters({"selenium.browser", "selenium.grid"})
    public void setUp(@Optional("chrome") String browser, @Optional("") String gridUrl) {
        // TODO create WebDriver instance according to passed parameters --- DONE
        // driver = new EventFiringWebDriver(....);
        // driver.register(new EventHandler());
        // ...
        if (TextUtils.isEmpty(gridUrl)) {driver = new EventFiringWebDriver(getDriver(browser));}
                else {driver = new EventFiringWebDriver(getDriver(browser, gridUrl));}

        driver.register(new EventHandler());

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        // unable to maximize window in mobile mode
        if (!isMobileTesting(browser))
            driver.manage().window().maximize();

        isMobileTest = isMobileTesting(browser);

        actions = new GeneralActions(driver);

        wait = new WebDriverWait(driver, 5);
    }

    /**
     * Closes driver instance after test class execution.
     */
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     *
     * @return Whether required browser displays content in mobile mode.
     */
    protected boolean isMobileTesting(String browser) {
        switch (browser) {
            case "android":
            case "mobile":
                return true;
            case "firefox":
            case "ie":
            case "internet explorer":
            case "chrome":
            default:
                return false;
        }
    }

    /**
     *
     * @param browser Driver type to use in myproject.automation.hwlecture4.tests.
     *
     * @return New instance of {@link WebDriver} object.
     */
    private WebDriver getDriver(String browser) {
        switch (browser) {
            case "firefox":
                System.out.println("Browser FF");
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseTest.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();

            case "ie":
            case "internet explorer":
                System.setProperty(
                        "webdriver.ie.driver",
                        new File(BaseTest.class.getResource("/IEDriverServer.exe").getFile()).getPath());
                InternetExplorerDriver ieDriver;
                InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                        .destructivelyEnsureCleanSession();
                ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                ieDriver = new InternetExplorerDriver(ieOptions);
                return ieDriver;

            case "mobile":
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                Map<String,String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone 6");

                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                return new ChromeDriver(chromeOptions);

            case "chrome":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();

        }
    }

    private WebDriver getDriver(String browser, String gridUrl)
    {
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptionsRemote = new FirefoxOptions();
                try{
                    return new RemoteWebDriver(new URL(gridUrl), firefoxOptionsRemote);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            case "ie":
            case "internet explorer":
                InternetExplorerOptions  internetExplorerOptions = new InternetExplorerOptions();
                try{
                    return new RemoteWebDriver(new URL(gridUrl), internetExplorerOptions);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;

            case "chrome":
            default:
                ChromeOptions optionsRemote = new ChromeOptions();
                try {
                    return new RemoteWebDriver(new URL(gridUrl), optionsRemote);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
        }
    }


}
