package org.example.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final String IGNORE_CERTIFICATE = "--ignore-certificate-errors";

    public static WebDriver getFirefoxDriver(int wait) {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriver driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

        return driver;
    }

    public static WebDriver getChromeDriver(int wait) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(IGNORE_CERTIFICATE);

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));

        return driver;
    }
}
