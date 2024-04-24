package org.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BasePage {

    @FindBy(css = ".shopping_cart_link")
    private WebElement catyIcon;

    public void openCartPageByIcon() {
        catyIcon.click();
    }


}
