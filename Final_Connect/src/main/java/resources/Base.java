package resources;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;

	public WebDriver initializeBrowser() throws Exception {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\M1036018\\oxygen-workspace\\Final_Connect\\src\\main\\java\\resources\\data.properties");
		prop = new Properties();
		prop.load(fis);

		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;

	}

	public void getScreenshot(String result) throws Exception {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C://test//" + result + "screenshot.png"));

	}

	public Object[][] getData(String SheetName) throws Exception {

		FileInputStream fis = new FileInputStream(
				"C:\\\\Users\\\\M1036018\\\\Documents\\\\Selenium_Udemy\\\\TestData.xlsx");
		workbook = new XSSFWorkbook(fis);
		int count = workbook.getNumberOfSheets();
		Object[][] data = null;

		for (int i = 0; i < count; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(SheetName)) {
				sheet = workbook.getSheetAt(i);

				int row = sheet.getLastRowNum();
				row = row + 1;
				int col = sheet.getRow(0).getLastCellNum();

				data = new Object[row][col];

				for (int j = 0; j < row; j++) {
					for (int k = 0; k < col; k++) {
						data[j][k] = sheet.getRow(j).getCell(k).getStringCellValue();
					}
				}

			}
		}

		return data;

	}

}
