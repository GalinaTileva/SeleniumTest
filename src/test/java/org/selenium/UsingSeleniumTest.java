package org.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class UsingSeleniumTest {
    @Test
    public void eightComponents() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


        WebElement form = driver.findElement(By.id("login-modal"));
        WebElement input = form.findElement(By.xpath(".//input[@name='password']"));


        driver.findElement(By.name("password"));
        driver.findElement(By.cssSelector("[type='button']"));

        input.sendKeys("Selenium");


        WebElement message = driver.findElement(By.id("message"));
        String value = message.getText();
        assertEquals("Received!", value);

        driver.quit();

        Point location = message.getLocation();
        Dimension size = message.getSize();
        assertEquals("MyText", message.getText());
        assertTrue(message.isDisplayed());
    }


    @Test
    public void slider(){
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the provided URL
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // Locate the slider using the provided selector
        WebElement slider = driver.findElement(By.cssSelector(".form-range"));

        // Create an instance of the Actions class
        Actions moveSlider = new Actions(driver);

        // Click and drag the slider by an offset
        // This example moves the slider 40 pixels to the right
        // You might need to adjust this value based on the slider's sensitivity and the desired position
        moveSlider.clickAndHold(slider).moveByOffset(40, 0).release().perform();

        // Optionally, verify the slider's new position or value if applicable

        // Close the browser
        driver.quit();

    }


    @Test
    public void dragAndDrop(){
        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the page
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        // Locate the elements
        WebElement source = driver.findElement(By.id("column-a"));
        WebElement target = driver.findElement(By.id("column-b"));

        // Create an instance of Actions class
        Actions actions = new Actions(driver);

        // Perform the drag and drop action
        actions.clickAndHold(source).moveToElement(target).release().perform();

        // Alternatively, using dragAndDrop() method directly (might not work in all cases)
        // actions.dragAndDrop(source, target).perform();

        // Add any verification or cleanup code here

        // Close the browser
        driver.quit();
    }


    @Test
    public void dropDwownExample(){

        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        dropdown.selectByVisibleText("Option 1");
        System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());
        dropdown.selectByVisibleText("Option 2");
        System.out.println("Selected: " + dropdown.getFirstSelectedOption().getText());

        driver.quit();
    }


    @Test
    public void tableExtraction() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/tables");

        List<WebElement> emails = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[3]"));
        for (WebElement email : emails) {
            System.out.println(email.getText());
        }

        driver.quit();
    }

    @Test
    public void sliderExample(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/slider/");

        driver.switchTo().frame(0); // Switch to the frame containing the slider

        WebElement slider = driver.findElement(By.xpath("//div[@id='slider']/span"));
        Actions move = new Actions(driver);
        move.dragAndDropBy(slider, 300, 0).perform();

        // Verification code here

        driver.quit();
    }



}
