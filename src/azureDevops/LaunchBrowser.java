package azureDevops;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
public class LaunchBrowser {

    public static void main(String[] args) throws Exception {    
        WebDriver driver = new ChromeDriver();    
        try {
           System.out.println("tEST DAT");
           driver.get("http://68.178.169.152:4091/APL/Login.aspx");
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        wait.until(ExpectedConditions.titleIs("Credpro"));
            // Print the page title after it has fully loaded
            System.out.println("Page title is: " + driver.getTitle());
            WebElement usernameField = driver.findElement(By.id("txtUserName"));
            WebElement passwordField = driver.findElement(By.id("txtPassword"));
            WebElement loginButton = driver.findElement(By.id("btnLogin"));

            assertTrue(usernameField.isDisplayed());
            assertTrue(passwordField.isDisplayed());
            assertTrue(loginButton.isDisplayed());
            
            usernameField.sendKeys("heramb.khandekar@bank.com");
            passwordField.sendKeys("DemoPwd@21");
            loginButton.click();
            // Handle  if alert is coming
            try {
                wait.until(ExpectedConditions.alertIsPresent());
                Alert alert = driver.switchTo().alert();
                System.out.println("Alert text: " + alert.getText());
                alert.accept(); // Accept the alert (you can also use alert.dismiss() to cancel it)
            } catch (Exception e) {
                System.out.println("No alert present");
            }
            
            // Verify that the user is redirected to the home page after login
            String expectedUrl = "http://68.178.169.152:4091/APL/MyTasks.aspx";
            String actualUrl = driver.getCurrentUrl();
            assertEquals(expectedUrl, actualUrl);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
   }
