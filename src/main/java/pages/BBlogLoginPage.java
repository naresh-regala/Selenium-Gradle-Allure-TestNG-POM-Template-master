package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import resources.Logg;

public class BBlogLoginPage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "[placeholder=\"Email\"]")
	WebElement Username;

	@FindBy(css = "[placeholder=\"Password\"]")
	WebElement Password;

	@FindBy(xpath = "/html/body/app-root/app-login/div/div/div/div/button")
	WebElement SigninButton;
	
	@FindBy(xpath = "//a[@routerlink='/editor']")
	WebElement editorLink;
	
	@FindBy(xpath = "//a[normalize-space()='naresh']")
	WebElement profileLink;

	public BBlogLoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
	}

	@Step("Entering username: {0} on Login Page ..")
	public void setUserName(String sUserName) {
		explicitWaitForElementVisbility(Username);
		Username.sendKeys(sUserName);
	}

	@Step("Entering password: {0} on Login Page ..")
	public void setPassword(String sPassword) {
		explicitWaitForElementVisbility(Password);
		Password.sendKeys(sPassword);
	}

	@Step("Clicking SignIn button on Login Page ..")
	public void clickSignIn() {
		Password.submit();
	}

	@Step("Login Step with username: {0} and password: {1} for method {method} ..")
	public void loginToBBlog(String sUserName, String sPasword) throws InterruptedException {

		this.setUserName(sUserName);
		this.setPassword(sPasword);
		this.clickSignIn();		
	}
	
	@Step("Verifying successful Login for method {method} ..")
	public void verifySucccessfullLogin() throws InterruptedException {
		explicitWaitForElementVisbility(editorLink);
		Actions action = new Actions(driver);
		action.moveToElement(editorLink).click().build().perform();
        Logg.info("Home page: editor ink is clicked");
	}
	
	@Step("Explicit wait for the presence of Element {0}")
	public void explicitWaitForElementVisbility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    } 
	
	@Step("go to user profile page")
	public void goToProfilePage() {
		wait.until(ExpectedConditions.visibilityOf(profileLink));
		Actions action = new Actions(driver);
		action.moveToElement(profileLink).click().build().perform();
		Logg.info("I am in profile page");
	}
}
