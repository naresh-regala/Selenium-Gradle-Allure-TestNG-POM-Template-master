package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import resources.Utils;

public class BBhomePage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(xpath = "//a[normalize-space()='Sign in']")
	private WebElement SigninButton;

	@FindBy(xpath = "//a[@routerlink='/settings']")
	private WebElement settings;

	public BBhomePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	@Step("Clicking SignIn button on Login Page ..")
	public void clickSignIn() throws IOException {
		driver.get(Utils.getKeyValue("basicAuthUrl"));
		driver.get(Utils.getKeyValue("baseUrl"));
		explicitWaitForElementVisbility(SigninButton);
		Actions action1 = new Actions(driver);
		action1.moveToElement(SigninButton).click().build().perform();
		System.out.println("HomePage: SignIn button is clicked");

	}

	@Step("Go to Settings Page")
	public void goToSettingsPage() throws IOException {
		explicitWaitForElementVisbility(settings);
		Actions actions = new Actions(driver);
		actions.moveToElement(settings).click().build().perform();
	}

	@Step("Explicit wait for the presence of Element {0}")
	public void explicitWaitForElementVisbility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
