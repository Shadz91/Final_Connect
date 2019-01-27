package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//*[@class='actionable actionable_button actionable_prefix actionable_light button header-control--control']")
	private WebElement login;

	@FindBy(id = "username")
	private WebElement username;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-form--submit-button")
	private WebElement submit;

	public WebElement loginMethod() {
		return login;
	}

	public WebElement usernameMethod() {
		return username;
	}

	public WebElement passwordMethod() {
		return password;
	}

	public WebElement submitMethod() {
		return submit;
	}

}
