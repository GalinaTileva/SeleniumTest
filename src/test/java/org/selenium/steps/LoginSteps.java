package org.selenium.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.selenium.pages.LoginPage;

import static org.testng.AssertJUnit.assertEquals;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();


    @Given("the user is logged in with username {string} and password {string}")
    public void the_user_is_logged_in_with_username_and_password(String username, String password) {
      //  loginPage = new LoginPage();
        loginPage.loginAs(username, password);
    }

    @When("the user set username {string}")
    public void theUserSetUsername (String username) {
        loginPage.setUsername(username);
    }

    @When("the user set password {string}")
    public void theUserSetPassword(String password) {
        loginPage.setPassword(password);
    }

    @When("the user click Login button")
    public void theUserClickLogin() {
        loginPage.clickLoginButton();
    }

    @Then("verify error message is {string}")
    public void verifyErrorMessage(String message){
        assertEquals(message, loginPage.getErrorMessage());
    }
}
