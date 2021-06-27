package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class EditDeleteArticlePage {

	WebDriver driver;
	WebDriverWait wait;

	@FindBy(linkText = "Edit Article")
	private WebElement editButton;

	@FindBy(xpath = "//div[@class='article-actions']//button[@class='btn btn-sm btn-outline-danger'][normalize-space()='Delete Article']")
	private WebElement deleteButton;

	@FindBy(xpath = "//a[@routerlink='/settings']")
	private WebElement settings;

	public EditDeleteArticlePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
	}

	@Step("Click Edit Buttom")
	public void clickEditButton() {
		wait.until(ExpectedConditions.visibilityOf(editButton));
		Actions action = new Actions(driver);
		action.moveToElement(editButton).click().build().perform();
		editButton.click();
	}

	@Step("Click Edit Buttom")
	public void clickDeleteButton() {
		wait.until(ExpectedConditions.visibilityOf(deleteButton));
		Actions action = new Actions(driver);
		action.moveToElement(deleteButton).click().build().perform();
	}

	@Step("Go to settings page from Edit/Delete Article page")
	public void goToSettingsPage() {
		explicitWaitForElementVisbility(settings);
		Actions actions = new Actions(driver);
		actions.moveToElement(settings).click().build().perform();
	}

	@Step("Explicit wait for the presence of Element {0}")
	public void explicitWaitForElementVisbility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

}
