package azureDevops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPageTest extends BaseTest {

    // Common locators for the login page
    private By usernameField = By.id("txtUserName");
    private By passwordField = By.id("txtPassword");
    private By loginButton = By.id("btnLogin");
    private By errorMessageLocator = By.id("lbError");

    @Test(priority = 1)
    public void testLoginPageLoad() {
        // Verify that the login page has loaded by checking the title
        String expectedTitle = "Credpro";
        wait.until(ExpectedConditions.titleIs(expectedTitle));
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title did not match!");
    }

    @Test(priority = 2)
    public void testLoginPageElementsPresence() {
        // Verify that the username and password fields are present
        Assert.assertTrue(driver.findElement(usernameField).isDisplayed(), "Username field not visible!");
        Assert.assertTrue(driver.findElement(passwordField).isDisplayed(), "Password field not visible!");
        Assert.assertTrue(driver.findElement(loginButton).isDisplayed(), "Login button not visible!");
    }

    @Test(priority = 3)
    public void testSuccessfulLogin() {
        // Enter valid credentials and submit the form
        driver.findElement(usernameField).sendKeys("heramb.khandekar@bank.com");
        driver.findElement(passwordField).sendKeys("DemoPwd@21");
        driver.findElement(loginButton).click();

        // Verify successful login
        String expectedUrl = "http://68.178.169.152:4091/APL/MyTasks.aspx";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL after login did not match!");
    }

    @Test(priority = 4)
    public void testInvalidLogin() {
        // Enter invalid credentials and submit the form
        driver.findElement(usernameField).sendKeys("invalid_user");
        driver.findElement(passwordField).sendKeys("invalid_password");
        driver.findElement(loginButton).click();

        // Verify error message
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message not displayed!");
        Assert.assertEquals(errorMessage.getText(), "Invalid username or password", "Incorrect error message!");
    }
}
