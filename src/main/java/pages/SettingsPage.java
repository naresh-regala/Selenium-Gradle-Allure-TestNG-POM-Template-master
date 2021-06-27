package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class SettingsPage {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//button[normalize-space()='Or click here to logout.']")
	WebElement logoutButton;
	
	
	public SettingsPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver,10);
		PageFactory.initElements(driver, this);
	}
	
	public void clickLogout() {
		explicitWaitForElementVisbility(logoutButton);
		Actions actions = new Actions(driver);
		actions.moveToElement(logoutButton).click().build().perform();
		
	}
	
	@Step("Explicit wait for the presence of Element {0}")
	public void explicitWaitForElementVisbility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    } 
	

}
