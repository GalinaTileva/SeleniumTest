package org.selenium.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.selenium.pages.LoginPage;
import org.selenium.pages.ProductsPage;

import static org.testng.AssertJUnit.assertEquals;

public class ProductsPageSteps  {
    private ProductsPage productsPage = new ProductsPage();

    @When("the user is redirected to products page")
    public void the_user_opens_the_products_page() {
        assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Then("the user can open the cart page by clicking on the cart icon")
    public void the_user_can_open_the_cart_page_by_clicking_on_the_cart_icon() {
        productsPage.header().openCartPageByIcon();
    }
}
