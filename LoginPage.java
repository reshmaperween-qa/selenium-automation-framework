package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    // constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // locators (PageFactory)
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailInput;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordInput;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    WebElement loginButton;

    // enter email
    public void setEmail(String email) {
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    // enter password
    public void setPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    // click login button
    public void clickLogin() {
        loginButton.click();
    }
}