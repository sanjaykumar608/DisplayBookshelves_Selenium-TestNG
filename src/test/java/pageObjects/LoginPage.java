package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

    public class LoginPage extends BasePage {
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	// Locates the email text box element using its XPath
	@FindBy(xpath="//div[@id='password-credentials']//input[@id='spree_user_email']")
	// Declares a WebElement for the email text box element
	WebElement email;
	
	// Locates the errorMsg element using its XPath
	@FindBy(xpath="//label[text()='Please enter a valid email address.']")
	// Declares a WebElement for the errorMsg element
	WebElement errorMsg;
	
	// Method to enter a value into the text box and press enter
	public void enterInvalidEmail(String invalidEmail){
	     wait.until(ExpectedConditions.elementToBeClickable(email));
	     email.clear();
	     email.sendKeys(invalidEmail);
	     actions.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
	}
	
	// Method to get the error message
	public String errorMessage()
	{
		return errorMsg.getText();
	}
}
