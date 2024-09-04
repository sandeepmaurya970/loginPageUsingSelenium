package azureDevops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class LoginPageTest {
    
    private WebDriver driver;
    private String baseUrl = "http://68.178.169.152:4091/APL/Login.aspx";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @Test
    public void testLoginPageLoad() {
        // Verify that the login page has loaded by checking the title
        String expectedTitle = "Credpro";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginPageElementsPresence() {
        // Verify that the username and password fields are present
        WebElement usernameField = driver.findElement(By.id("txtUserName"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        assertTrue(usernameField.isDisplayed());
        assertTrue(passwordField.isDisplayed());
        assertTrue(loginButton.isDisplayed());
    }

    @Test
    public void testSuccessfulLogin() {
        // Enter valid credentials and submit the form
        WebElement usernameField = driver.findElement(By.id("txtUserName"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        usernameField.sendKeys("heramb.khandekar@bank.com");
        passwordField.sendKeys("DemoPwd@21");
        loginButton.click();
        // Verify that the user is redirected to the home page after login
        String expectedUrl = "http://68.178.169.152:4091/APL/MyTasks.aspx";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testInvalidLogin() {
        // Enter invalid credentials and submit the form
        WebElement usernameField = driver.findElement(By.id("txtUserName"));
        WebElement passwordField = driver.findElement(By.id("txtPassword"));
        WebElement loginButton = driver.findElement(By.id("btnLogin"));

        usernameField.sendKeys("sandeep");
        passwordField.sendKeys("123421");
        loginButton.click();

        // Verify that an error message is displayed
        WebElement errorMessage = driver.findElement(By.id("lbError"));
        assertTrue(errorMessage.isDisplayed());
        assertEquals("Invalid username or password", errorMessage.getText());
    }

    @After
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
