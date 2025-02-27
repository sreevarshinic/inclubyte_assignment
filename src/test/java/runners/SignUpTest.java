package runners;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.PageLoadStrategy;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import org.openqa.selenium.By;
import pages.HomePage;
import pages.RegistrationPage;
import pages.LoginPage;

public class SignUpTest {

    private WebDriver driver;
    private WebDriverWait wait;
    
    // User details for registration and login
    private final String firstName = "Sree Varshini";
    private final String lastName = "C";
    private final String password = "Sree@0207!!"; // Ensure strong password
    private String email;

    @BeforeClass
    public void setUp() {
        System.out.println("SignUpTest: Running setUp()");
        WebDriverManager.chromedriver().setup();
        
        // Set ChromeOptions to optimize performance and reduce timeouts
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--blink-settings=imagesEnabled=false"); // Disable images for faster load
        // Set page load strategy to EAGER so Selenium waits only for the DOM to be ready
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        // Optionally, run in headless mode to reduce resource usage:
        // options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        // Set timeouts (adjust these if needed)
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));  // 30-second page load timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));      // 5-second implicit wait
        
        // Initialize an explicit wait (10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testSignUpAndLogin() throws InterruptedException {
        // Generate a unique email to avoid duplicate registration errors
        email = "sree" + System.currentTimeMillis() + "@gmail.com";

        long navStart = System.currentTimeMillis();
        driver.get("https://magento.softwaretestingboard.com/");
        long navTime = System.currentTimeMillis() - navStart;
        System.out.println("Homepage loaded in " + navTime + " ms, URL: " + driver.getCurrentUrl());

        // Wait until the "Create an Account" link is clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create an Account")));
        System.out.println("'Create an Account' link is clickable.");

        // Sign-up Flow
        HomePage homePage = new HomePage(driver);
        homePage.clickCreateAccount();
        System.out.println("Clicked 'Create an Account'. Current URL: " + driver.getCurrentUrl());

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationForm(firstName, lastName, email, password);
        registrationPage.submitRegistration();
        System.out.println("Submitted registration form. Current URL: " + driver.getCurrentUrl());

        if (!registrationPage.isRegistrationSuccessful()) {
            System.out.println("Registration failed. Please check the console output for details.");
            assert false : "Registration failed";
        } else {
            System.out.println("Registration successful!");
        }

        // Log out to get to the login page
        driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create an Account")));
        System.out.println("Logged out successfully. Current URL: " + driver.getCurrentUrl());

        // Sign-in Flow
        driver.get("https://magento.softwaretestingboard.com/customer/account/login/");
        System.out.println("Navigated to Login. Current URL: " + driver.getCurrentUrl());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);
        System.out.println("Submitted login form. Current URL: " + driver.getCurrentUrl());

        assert loginPage.isLoginSuccessful() : "Login failed";
        System.out.println("Login successful!");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            try {
                Thread.sleep(2000); // Optional: Wait 2 seconds for inspection
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
