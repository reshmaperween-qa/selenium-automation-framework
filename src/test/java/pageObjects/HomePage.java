package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    // =========================================================
    // Constructor
    // =========================================================
    public HomePage(WebDriver driver) {

        // calling BasePage constructor
        super(driver);
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    // My Account dropdown
    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    // Login option inside dropdown
    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='Login']")
    WebElement login;

    // Register option inside dropdown
    @FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]//a[normalize-space()='Register']")
    WebElement register;


    // =========================================================
    // METHOD: Open My Account Dropdown
    // =========================================================
    public void clickMyAccount() {

        // wait until My Account clickable
        WebElement account = wait.until(
                ExpectedConditions.elementToBeClickable(myAccount)
        );

        // click dropdown
        account.click();

        // 🔧 small wait to allow dropdown animation
        try {
            Thread.sleep(500);
        } catch (Exception e) {}
    }


    // =========================================================
    // METHOD: Click Login
    // =========================================================
    public void clickLogin() {

        // open dropdown first
        clickMyAccount();

        // wait until Login clickable
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(login)
        );

        // click Login
        loginBtn.click();
    }


    // =========================================================
    // METHOD: Click Register
    // =========================================================
    public void clickRegister() {

        // open dropdown first
        clickMyAccount();

        // wait until Register clickable
        WebElement regBtn = wait.until(
                ExpectedConditions.elementToBeClickable(register)
        );

        // click Register
        regBtn.click();
    }
}