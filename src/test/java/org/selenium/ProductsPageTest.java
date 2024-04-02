package org.selenium;

import org.selenium.base.MainTest;
import org.selenium.pages.LoginPage;
import org.selenium.pages.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsPageTest extends MainTest {

    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBefore(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test
    public void canOpenCartPage() {
        productsPage = new ProductsPage(driver);
        productsPage.header().openCartPageByIcon();

    }
}
