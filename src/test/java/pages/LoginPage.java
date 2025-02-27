package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    
    // Locator for the email input field (ensure the ID matches the actual page)
    private By emailField = By.id("email");
    
    // Locator for the password input field (ensure the ID matches the actual page)
    private By passwordField = By.id("pass");
    
    // Locator for the login button (ensure the ID matches the actual page)
    private By loginButton = By.id("send2");
    
    // Locator for an element indicating a successful login (e.g., a welcome message)
    private By welcomeMessage = By.cssSelector(".customer-welcome");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     * Logs in with the provided email and password.
     */
    public void login(String email, String password) {
        // Clear and enter email
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        
        // Clear and enter password
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        
        // Click the login button
        driver.findElement(loginButton).click();
    }
    
    /**
     * Verifies if the login was successful by waiting for the welcome message to be visible.
     *
     * @return true if login is successful, false otherwise.
     */
    public boolean isLoginSuccessful() {
        try {
            // Use an explicit wait of up to 10 seconds for the welcome message to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
            return true;
        } catch (Exception e) {
            System.out.println("Login not successful: " + e.getMessage());
            return false;
        }
    }
}
