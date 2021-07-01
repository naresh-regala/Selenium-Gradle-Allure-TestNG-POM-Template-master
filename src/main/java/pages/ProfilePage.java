package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;
import resources.Logg;

public class ProfilePage {
	WebDriver driver;
	WebDriverWait wait;

	@FindBy(css = "body > app-root > app-profile-page > div > div.container > div > div > app-profile-articles > app-article-list > app-article-preview:nth-child(n) > div > a > h1")
	List<WebElement> articleList;

	@FindBy(xpath = "//app-article-preview[1]//div[1]//a[1]//h1[1]")
	WebElement articleElement;

	@FindBy(xpath = "//a[normalize-space()='Edit Profile Settings']")
	WebElement profileSettings;

	public ProfilePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		PageFactory.initElements(driver, this);
	}

	@Step("Opening an Aricle in My Articles page}")
	public void openArticle() {
		explicitWaitForElementVisbility(profileSettings);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)");
		explicitWaitForElementVisbility(articleElement);
		Logg.info("Size of articleList "+articleList.size());
		Logg.info("Profile Page: Click on article Link");
		articleList.get(0).click();
	}

	@Step("Explicit wait for the presence of Element")
	public void explicitWaitForElementVisbility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
