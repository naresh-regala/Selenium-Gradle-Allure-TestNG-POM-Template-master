package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.qameta.allure.Step;

public class CreatePostPage {

	WebDriver driver;
	WebDriverWait wait;
	String articleUrl;

	@FindBy(css = "[placeholder=\"Article Title\"]")
	WebElement articleTitle;

	@FindBy(css = "[placeholder=\"What's this article about?\"]")
	WebElement articleAbout;

	@FindBy(css = "[placeholder=\"Write your article (in markdown)\"]")
	WebElement articleDescription;

	@FindBy(css = "[placeholder=\"Enter tags\"]")
	WebElement articleTags;

	@FindBy(xpath = "//button[normalize-space()='Publish Article']")
	WebElement publichArtilcleButton;
	
	@FindBy(className= "author")
	WebElement author;

	public CreatePostPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
	}

	public void createPost(String sArticleTitle, String sArticleAbout, String sArticleDescription, String sArticleTags)
			throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(articleTitle));
		articleTitle.clear();
		articleTitle.sendKeys(sArticleTitle);
		articleAbout.clear();
		articleAbout.sendKeys(sArticleAbout);
		articleDescription.clear();
		articleDescription.sendKeys(sArticleDescription);
		articleTags.clear();
		articleTags.sendKeys(sArticleTags);
		wait.until(ExpectedConditions.visibilityOf(publichArtilcleButton));
		Actions actions = new Actions(driver);
		actions.moveToElement(publichArtilcleButton).click().build().perform();
	}

	public boolean verifyEditedPost(String addedText) {
		boolean editSuccess = false;
		if (articleTitle.getAttribute("value").contains(addedText) && articleAbout.getAttribute("value").contains(addedText)
				&& articleDescription.getAttribute("value").contains(addedText) && articleTags.getAttribute("value").contains(addedText)) {
			editSuccess = true;
			return editSuccess;
		}
		return editSuccess;
	}
	
	@Step("Verify whether post is successfuly publihsed or not")
	public void verifyPostPublishSuccess() {
		explicitWaitForElementVisbility(author);
		articleUrl =driver.getCurrentUrl();
		System.out.println(articleUrl);
		Assert.assertEquals(driver.getCurrentUrl().contains("article"), true);
	}
	
	@Step("Explicit wait for the presence of Element {0}")
	public void explicitWaitForElementVisbility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    } 

}
