package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test
    public void verify_account_registration() {

        logger.info("***** Starting TC001 Account Registration Test *****");

        try {

            // ===== Home Page =====
            HomePage hp = new HomePage(driver);
            hp.clickMyAccount();
            logger.info("Clicking on MyAccount Link...");
           
            hp.clickRegister();   // handles dropdown internally
            logger.info("Clicking Register...");


            // ===== Registration Page =====
            AccountRegistrationPage regpage = new AccountRegistrationPage(driver);

            logger.info("Entering user details...");

            String firstName = randomstring().toUpperCase();
            String lastName = randomstring().toUpperCase();
            String email = randomstring() + "@gmail.com";
            String password = randomeAlphaNumberic();

            regpage.setFirstName(firstName);
            regpage.setLastName(lastName);
            regpage.setEmail(email);
            regpage.setPassword(password);

            logger.info("User details entered successfully");

            // ===== Submit form =====
            regpage.clickAgree();
            regpage.clickContinue();
            logger.info("Form submitted");
          
            // ===== Confirmation message =====
            String confmsg = regpage.getConfirmationMsg();

            logger.info("Confirmation message: " + confmsg);

            // ===== Assertion =====
            Assert.assertEquals(
                confmsg.trim(),
                "Your Account Has Been Created!",
                "Account registration failed - message mismatch"
            );

            logger.info("ASSERTION PASSED ✔ Account created successfully");

        } catch (Exception e) {

            logger.error("TEST FAILED DUE TO EXCEPTION ❌", e);
            Assert.fail("Exception occurred: " + e.getMessage());
        }

        logger.info("***** Finished TC001 Account Registration Test *****");        
}
    }