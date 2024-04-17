package org.selenium;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "org.selenium.steps", // Path to your step definitions
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-pretty.html"}, // Reporting options
        monochrome = true
)
public class CucumberTestNGRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
