package org.selenium.base;

import org.apache.commons.io.FileUtils;
import org.example.driver.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class MainTest {
    private String url;
    private int implicitWait;
    private String browser;

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        setupBrowserDriver();
        loadUrl();
    }


    private void loadUrl() {
        driver.get(url);
    }

    private void setupBrowserDriver() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties")) {
            Properties config = new Properties();
            config.load(configFile);
            url = config.getProperty("urlAddress");
            implicitWait = Integer.parseInt(config.getProperty("implicitWait"));
            // browser to be taken from property file
            browser = config.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (browser) {
            case "chrome":
                driver = DriverFactory.getChromeDriver(implicitWait);
                break;
            case "firefox":
                driver = DriverFactory.getFirefoxDriver(implicitWait);
                break;
            default:
                //  log.error("Unsupported browser type");
                throw new IllegalStateException("Unsupported browser type");
        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
            String fileName = result.getName() + "_" + timestamp + ".png";
            try {
                FileUtils.copyFile(source, new File("./Screenshots/" + fileName));
                System.out.println("Screenshot taken: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        driver.quit();
    }


  /*  @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }*/
}
