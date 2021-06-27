package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BBlogSignupPage {

	WebDriver driver;
	@FindBy(css = "[placeholder=\"Username\"]")
	WebElement Username;

	@FindBy(css = "[placeholder=\"Email\"]")
	WebElement Email;

	@FindBy(css = "[placeholder=\"Password\"]")
	WebElement Password;

	@FindBy(linkText = "Sign up")
	WebElement Signup;

	public BBlogSignupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Set username in textbox
	public void setUserName(String sUserName) {
		Username.sendKeys(sUserName);
	}

	// Set Email in textbox
	public void setEmail(String sEmail) {
		Username.sendKeys(sEmail);
	}

	// Set password in password textbox
	public void setPassword(String sPassword) {
		Password.sendKeys(sPassword);
	}

	// Click on login button
	public void clickSignupButton() {
		Signup.click();
	}

	public void signupToBBlog(String sUserName, String sEmail, String sPasword) {

		// Fill user name
		this.setUserName(sUserName);
		// Fill Email
		this.setEmail(sEmail);
		// Fill password
		this.setPassword(sPasword);
		// Click Login button
		this.clickSignupButton();
	}

}
