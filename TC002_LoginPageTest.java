package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginPageTest extends BaseClass {

    @Test
    public void verifyLogin() {

        logger.info("***** Starting TC002 Login Test *****");

        try {

            // ===== Home Page =====
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();// open myAccount dropdown
            hp.clickLogin(); // navigate to login page

            // ===== Login Page =====
            LoginPage lp = new LoginPage(driver);

            lp.setEmail(p.getProperty("Email"));      // enter email
            lp.setPassword(p.getProperty("password")); // enter password
            lp.clickLogin();                             // click login

            // ===== My Account Page validation =====
            MyAccountPage macc = new MyAccountPage(driver);

            boolean targetpage = macc.isMyAccountPageExits();

            Assert.assertEquals(targetpage, true,"Login failed - My Account page not visible");

            // ===== DEBUG =====
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

        } catch (Exception e) {

            e.printStackTrace();
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC002 Login Test *****");
    }
}