package pageObjects;



import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	
	//constructor
public AccountRegistrationPage(WebDriver driver) 
{
	super(driver);
}
   //locators


@FindBy(xpath="//input[@id='input-firstname']") 
WebElement txtFirstname;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLasttname;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;


@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@name='agree']")
WebElement agree;

@FindBy(xpath="//button[normalize-space()='Continue']")
WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgconfirmation;

	//Action performed
public void setFirstName (String fname) {
txtFirstname. sendKeys (fname);
}


public void setLastName (String Iname) {
txtLasttname.sendKeys(Iname);
}

 public void setEmail (String email){
txtEmail.sendKeys(email);
}

public void setPassword(String pwd){
	txtPassword.sendKeys(pwd);
}

public void clickAgree(){

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", agree);

}

public void clickContinue(){

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    WebElement btn = wait.until(
        ExpectedConditions.elementToBeClickable(btnContinue)
    );

    // Scroll to button
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", btn);

    // Small wait (very important)
    try { Thread.sleep(1000); } catch (Exception e) {}

    // Click using JS (bypass blocking issue)
    js.executeScript("arguments[0].click();", btn);

}
//so12


//so13
//Actions act=new Actions(driver);
//act.moveToElement(btnContinue).click().perform();
//so14
//JavascriptExecutor js=(JavascriptExecutor)driver;
//js. executeScript("arguments[0].click();", btnContinue) ;

//So1 5
//btnContinue.sendkeys(Keys.RETURN);
//So16
//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds (10));
//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();

// confirmation message
public String getConfirmationMsg() 
{
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement msg = wait.until(
            ExpectedConditions.visibilityOf(msgconfirmation)
        );

        return msg.getText();

    } catch (Exception e) {
        return e.getMessage();
    }
}

}
