package testCases;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.logging.log4j.*;

import pageObjects.HomePage;
import resources.Base;

public class ValidateLoginTest extends Base {

	public static Logger log = LogManager.getLogger(Base.class.getName());

	@Test(dataProvider = "testData")
	public void multipleLogin(String username, String password) throws Exception {

		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		log.info("Page is loaded successfully");

		HomePage homePage = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.loginMethod()));

		homePage.loginMethod().click();
		log.debug("Clicked on Log in button successfully");
		homePage.usernameMethod().sendKeys(username);
		homePage.passwordMethod().sendKeys(password);
		homePage.submitMethod().click();
		log.debug("Clicked on Submit button successfully");

	}

	@DataProvider(name = "testData")

	public Object[][] pullData() throws Exception {
		Object[][] data = getData("Sheet1");
		return data;

	}

}
