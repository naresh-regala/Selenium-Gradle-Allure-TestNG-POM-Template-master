package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

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

	@Step
	public void setUserName(String sUserName) {
		Username.sendKeys(sUserName);
	}

	@Step
	public void setEmail(String sEmail) {
		Username.sendKeys(sEmail);
	}

	@Step
	public void setPassword(String sPassword) {
		Password.sendKeys(sPassword);
	}

	@Step
	public void clickSignupButton() {
		Signup.click();
	}

	@Step
	public void signupToBBlog(String sUserName, String sEmail, String sPasword) {

		this.setUserName(sUserName);
		this.setEmail(sEmail);
		this.setPassword(sPasword);
		this.clickSignupButton();
	}

}
