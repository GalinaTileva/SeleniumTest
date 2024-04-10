package org.selenium.tests;

import com.opencsv.exceptions.CsvException;
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

    @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readCsvFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void testSuccessfulLogin(String username, String password) {
        loginPage = new LoginPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
        productsPage = new ProductsPage();

        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }




    @Test
    public void testNegativeWithEmptyCredentials() {
        SoftAssert soft = new SoftAssert();
        loginPage = new LoginPage();

        loginPage.setUsername("");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        soft.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");

        loginPage.setUsername("standard_user");
        loginPage.setPassword("");
        loginPage.clickLoginButton();

        soft.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");

        soft.assertAll();

    }


}
