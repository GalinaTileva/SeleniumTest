package org.selenium.tests;

import com.opencsv.exceptions.CsvException;
import io.qameta.allure.*;
import org.example.utils.CsvReader;
import org.selenium.base.MainTest;
import org.selenium.pages.LoginPage;
import org.selenium.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends MainTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;

  /*  @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }*/

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Epic("Authentication")
    @Feature("Login")
    @Story("Successful Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test whether entering valid credentials on the login page leads to the correct landing page.")
    @Test(dataProvider = "login-data-file")
    public void testSuccessfulLogin(String username, String password) {
        loginPage = new LoginPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        productsPage = new ProductsPage();

        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }

    // allure serve target/allure-results;



}
