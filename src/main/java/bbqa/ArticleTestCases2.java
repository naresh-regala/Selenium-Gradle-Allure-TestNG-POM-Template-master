package bbqa;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BBhomePage;
import pages.BBlogLoginPage;
import pages.CreatePostPage;
import pages.EditDeleteArticlePage;
import pages.ProfilePage;
import pages.SettingsPage;
import resources.RU_Excel;
import resources.Utils;

public class ArticleTestCases2 extends SeleniumBase {

	String articleUrl;

	@Test(description = "Create article and publish it", groups = { "regression", "smoke" })
	public void createAndPublishArticle() throws InterruptedException, IOException {
		BBhomePage homepage = new BBhomePage(getDriver());
		homepage.clickSignIn();
		BBlogLoginPage loginpage = new BBlogLoginPage(getDriver());
		loginpage.loginToBBlog(Utils.getKeyValue("loginUsername"), Utils.getKeyValue("loginPassword"));
		loginpage.verifySucccessfullLogin();
		CreatePostPage ca = new CreatePostPage(getDriver());
		ca.createPost("Article Title :)", "This is About Article", "This is Atricle description!!!",
				"@NarTag1 @NarTag2 @NarTag3");
		ca.verifyPostPublishSuccess();
		EditDeleteArticlePage edpage = new EditDeleteArticlePage(getDriver());
		edpage.goToSettingsPage();
		SettingsPage settings = new SettingsPage(getDriver());
		settings.clickLogout();
	}

	@Test(description = "Verify edit button functionality", groups = { "regression" })
	public void EditArticle() throws InterruptedException, IOException {
		String editedText = "NAR Edit :)";
		System.out.println("I am in edit article test");
		BBhomePage homepage = new BBhomePage(getDriver());
		homepage.clickSignIn();
		BBlogLoginPage loginpage = new BBlogLoginPage(getDriver());
		loginpage.loginToBBlog(Utils.getKeyValue("loginUsername"), Utils.getKeyValue("loginPassword"));
		loginpage.goToProfilePage();
		ProfilePage profile = new ProfilePage(getDriver());
		profile.openArticle();
		EditDeleteArticlePage editpage = new EditDeleteArticlePage(getDriver());
		editpage.clickEditButton();
		CreatePostPage ca = new CreatePostPage(getDriver());
		ca.createPost(editedText, editedText, editedText, editedText);

		// Verify Edit is successful or not
		if (ca.verifyEditedPost(editedText)) {
			Assert.assertTrue(ca.verifyEditedPost(editedText));
		} else {
			Assert.fail("It seems Edit button is not working");
		}
		EditDeleteArticlePage edpage = new EditDeleteArticlePage(getDriver());
		edpage.goToSettingsPage();
		SettingsPage settings = new SettingsPage(getDriver());
		settings.clickLogout();
	}

	@Test(description = "Verify delete button functionality ", groups = { "regression" })
	public void deleteArticle() throws InterruptedException, IOException {
		BBhomePage homepage = new BBhomePage(getDriver());
		homepage.clickSignIn();
		BBlogLoginPage loginpage = new BBlogLoginPage(getDriver());
		loginpage.loginToBBlog(Utils.getKeyValue("loginUsername"), Utils.getKeyValue("loginPassword"));
		loginpage.goToProfilePage();
		ProfilePage profile = new ProfilePage(getDriver());
		profile.openArticle();
		EditDeleteArticlePage editpage = new EditDeleteArticlePage(getDriver());
		editpage.clickDeleteButton();
		Assert.assertEquals(getDriver().getCurrentUrl(), Utils.getKeyValue("baseUrl"));
		BBhomePage homepage1 = new BBhomePage(getDriver());
		homepage1.goToSettingsPage();
		SettingsPage settings = new SettingsPage(getDriver());
		settings.clickLogout();
	}

	@DataProvider(name = "inputData")
	public static Object[][] roles() throws Exception {
		return RU_Excel.readExcel();
	}

	@Test(dataProvider = "inputData", description = "Verify create articles With Dfferrent Kinds Of Text ex:javascript, emojis etc", priority = 4, groups = {
			"regression"})
	public void createArticlesWithDfferrentKindsOfText(String testCaseName, String title, String about, String desc,
			String tag) throws InterruptedException, IOException {
		System.out.println("executing: " + testCaseName);
		BBhomePage homepage = new BBhomePage(getDriver());
		homepage.clickSignIn();
		BBlogLoginPage loginpage = new BBlogLoginPage(getDriver());
		loginpage.loginToBBlog(Utils.getKeyValue("loginUsername"), Utils.getKeyValue("loginPassword"));
		loginpage.verifySucccessfullLogin();

		CreatePostPage ca = new CreatePostPage(getDriver());
		// Send random content from lorem ipsum api
		ca.createPost(title, about, desc, tag);
		ca.verifyPostPublishSuccess();
		EditDeleteArticlePage edpage = new EditDeleteArticlePage(getDriver());
		edpage.goToSettingsPage();
		SettingsPage settings = new SettingsPage(getDriver());
		settings.clickLogout();
	}

	@Test(description = "Verify create articles with lorem ipsum api", groups = { "regression", "smoke" })
	public void CreatingArticlesUsingLoremIpsumAPI() throws InterruptedException, IOException {
		BBhomePage homepage = new BBhomePage(getDriver());
		homepage.clickSignIn();
		BBlogLoginPage loginpage = new BBlogLoginPage(getDriver());
		loginpage.loginToBBlog(Utils.getKeyValue("loginUsername"), Utils.getKeyValue("loginPassword"));
		loginpage.verifySucccessfullLogin();
		CreatePostPage ca = new CreatePostPage(getDriver());
		// Send random content from lorem ipsum api
		String expectedArticleTitle = "Naresh Kumar" + Utils.getRandomText("1-2");
		ca.createPost(expectedArticleTitle, "About: " + Utils.getRandomText("1-2"),
				"Description: " + Utils.getRandomText("5-15"), "Tagg: " + Utils.getRandomText("1"));
		ca.verifyPostPublishSuccess();
		EditDeleteArticlePage edpage = new EditDeleteArticlePage(getDriver());
		edpage.goToSettingsPage();
		SettingsPage settings = new SettingsPage(getDriver());
		settings.clickLogout();
	}
}
