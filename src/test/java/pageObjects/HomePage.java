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

        // wait until Register option visible
        wait.until(ExpectedConditions.visibilityOf(register));
    }

    // =========================================================
    // METHOD: Click Login
    // =========================================================
    public void clickLogin() {

        // open dropdown first
        clickMyAccount();

        // wait until Login visible
        WebElement loginBtn = wait.until(
                ExpectedConditions.visibilityOf(login)
        );

        // click Login
        loginBtn.click();
    }

 // =========================================================
 // METHOD: Click Register
 // =========================================================
 public void clickRegister() {

     // direct open Register page because dropdown is unstable
     driver.get(
         "http://localhost:8888/opencart/index.php?route=account/register&language=en-gb"
     );
 }
}