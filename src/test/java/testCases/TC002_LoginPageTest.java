package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginPageTest extends BaseClass {

    @Test(groups={"Sanity","Master"})
    public void verifyLogin() {

        logger.info("***** Starting TC002 Login Test *****");

        try {

            // logout existing session first
            driver.get(p.getProperty("appURL") + "index.php?route=account/logout");

            // open homepage again
            driver.get(p.getProperty("appURL"));

            // ===== Home Page =====
            HomePage hp = new HomePage(driver);

            // clickLogin already opens dropdown internally
            hp.clickLogin();

            // ===== Login Page =====
            LoginPage lp = new LoginPage(driver);

            lp.setEmail(p.getProperty("Email"));
            lp.setPassword(p.getProperty("password"));
            lp.clickLogin();
            
         // wait after login
            Thread.sleep(2000);

            // ===== My Account Page validation =====
            MyAccountPage macc = new MyAccountPage(driver);

            boolean targetpage = macc.isMyAccountPageExits();

            Assert.assertEquals(targetpage,true,"Login failed - My Account page not visible");

            // ===== DEBUG =====
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

        } 
        catch (Throwable e) 
        
        {

            e.printStackTrace();

            captureScreen("verifyLogin");

            Assert.fail("Test failed due to exception: " + e.getMessage());
        }

        logger.info("***** Finished TC002 Login Test *****");
    }
}