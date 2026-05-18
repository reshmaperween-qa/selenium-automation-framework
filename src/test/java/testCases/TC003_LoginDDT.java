package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",
          dataProviderClass = DataProviders.class, groups="datadriven")

    public void verify_LoginDDT(String email,
                                String pwd,
                                String exp) {

        logger.info("**** START TC003_LoginDDT ****");

        try {

            // =========================================================
            // 🔧 IMPORTANT — Always start from homepage
            // =========================================================
            driver.get(p.getProperty("appURL"));

            HomePage hp = new HomePage(driver);

            // click Login
            hp.clickLogin();

            // =========================================================
            // LOGIN PAGE
            // =========================================================

            LoginPage lp = new LoginPage(driver);

            lp.setEmail(email);
            lp.setPassword(pwd);
            lp.clickLogin();

            Thread.sleep(800);


            // =========================================================
            // CHECK LOGIN SUCCESS
            // =========================================================

            MyAccountPage macc =
                    new MyAccountPage(driver);

            boolean targetpage =
                    macc.isMyAccountPageExits();

            System.out.println("Login status = " + targetpage);



            // =========================================================
            // VALID LOGIN
            // =========================================================

            if (exp.equalsIgnoreCase("Valid")) {

                Assert.assertTrue(targetpage,"Valid login failed - My Account page not found");
                // 🔧 BEST FIX — Direct logout URL
                driver.get(p.getProperty("appURL") + "index.php?route=account/logout" );
                Thread.sleep(500);

                // return to homepage
                driver.get(p.getProperty("appURL"));
            }


            // =========================================================
            // INVALID LOGIN
            // =========================================================

            else if (exp.equalsIgnoreCase("Invalid")) {

                Assert.assertFalse(targetpage,
                        "Invalid login passed - should NOT reach My Account page");
            }

        }

        catch (Exception e) {

            logger.error(
                    "Test failed due to exception: ", e);

            Assert.fail(
                    "Test failed due to exception: "
                    + e.getMessage());
        }

        logger.info("**** END TC003_LoginDDT ****");
    }
}