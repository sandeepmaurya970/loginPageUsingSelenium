package azureDevops;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LaunchBrowser {

    public static void main(String[] args) throws Exception {    
        WebDriver driver = new ChromeDriver();    
        try {
         
            driver.get("http://68.178.169.152:4091/APL/Login.aspx");
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        WebElement loginButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("btnLogin")));

                        wait.until(ExpectedConditions.titleIs("Credpro"));

            // Print the page title after it has fully loaded
            System.out.println("Page title is: " + driver.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
   }
