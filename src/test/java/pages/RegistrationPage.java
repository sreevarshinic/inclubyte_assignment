package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;

    // Locators for registration form fields
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By emailField = By.id("email_address");
    private By passwordField = By.id("password");
    private By confirmPasswordField = By.id("password-confirmation");
    
    // Locator for the "Create an Account" button
    private By submitButton = By.xpath("//button[@title='Create an Account']");
    
    // Locator for a success message element (adjust if needed)
    private By successMessage = By.cssSelector(".message-success");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String password) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void submitRegistration() {
        driver.findElement(submitButton).click();
    }

    public boolean isRegistrationSuccessful() {
        try {
            // Use an explicit wait for up to 10 seconds until the success message is visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return true;
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source:\n" + driver.getPageSource());
            return false;
        }
    }
}
