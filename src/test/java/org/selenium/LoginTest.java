package org.selenium;

import org.selenium.base.MainTest;
import org.selenium.pages.LoginPage;
import org.selenium.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends MainTest {
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Test
    public void testSuccessfulLogin() {
        loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();
        productsPage = new ProductsPage(driver);

        Assert.assertEquals(productsPage.getPageTitle(), "Products");
    }


    @Test
    public void testNegativeWithEmptyCredentials() {
        SoftAssert soft = new SoftAssert();
        loginPage = new LoginPage(driver);

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
