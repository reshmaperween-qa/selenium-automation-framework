package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

    // =========================================================
    // Constructor
    // =========================================================
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    // =========================================================
    // LOCATORS
    // =========================================================

    // My Account heading (used to verify login success)
    By myAccountHeader =
            By.xpath("//h2[normalize-space()='My Account']");

    // My Account dropdown (header)
    By myAccountDropdown =
            By.xpath("//span[normalize-space()='My Account']");

    // Logout inside dropdown (your correct XPath)
    By linkLogout =
    		By.xpath("//a[contains(@href,'account/logout')]");


    // =========================================================
    // VERIFY LOGIN SUCCESS
    public boolean isMyAccountPageExits() {

        try {

            // check using URL (most reliable)
            String url = driver.getCurrentUrl();

            System.out.println("Current URL: " + url);

            // login success URL contains this
            if (url.contains("route=account/account")) {

                return true;

            } else {

                return false;
            }

        } catch (Exception e) {

            return false;
        }
    }


    // =========================================================
    // CLICK LOGOUT
    // Open dropdown → Click Logout
    // =========================================================
    public void clickLogout() {

        // Step 1: Open My Account dropdown
        WebElement account = wait.until(
                ExpectedConditions.elementToBeClickable(
                        myAccountDropdown
                )
        );

        account.click();

        // Step 2: Click Logout link
        WebElement logout = wait.until(
                ExpectedConditions.elementToBeClickable(
                        linkLogout
                )
        );

        logout.click();
    }
}