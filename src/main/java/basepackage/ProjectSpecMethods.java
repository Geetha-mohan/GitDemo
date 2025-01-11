package basepackage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import utils.DataLibrary;

public class ProjectSpecMethods extends AbstractTestNGCucumberTests{

	public String fileName,sheetName;	
	public static RemoteWebDriver driver;
	public static ChromeOptions options;
	public static WebDriverWait wait;
	public static ExtentHtmlReporter html;
	public static ExtentReports repo;
	public static ExtentTest test , node;
	public String testName,testDescription,testAuthor,testCategory;
	public static JavascriptExecutor js;

	//acctname used in multiplepages, so creating ref here
	public static String newAccName;

	@BeforeMethod
	public void preConditions() {
		options = new ChromeOptions();
		options.addArguments("--incognito","--disable-notifications");
		driver = new ChromeDriver(options);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		js = (JavascriptExecutor) driver;
	}

	@AfterMethod
	public void postConditions() {
		driver.close();
	}

	@DataProvider(name="ExcelData")
	public String[][] getExcelData() throws IOException {
		System.out.println("file name is "+fileName);
		System.out.println("sheet name is "+sheetName);
		return DataLibrary.readExcelValue(fileName,sheetName);
	}

	public static WebElement waitForVisibility(WebElement ele, int time) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static WebElement waitForClickability(WebElement ele, int time) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	public static WebElement visibilityOfElement(WebElement ele, int time) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait.until(ExpectedConditions.visibilityOf(ele));
	}


	@BeforeSuite
	public void startReport() {
		html = new ExtentHtmlReporter("./report/createAcctseqReport.html");
		html.setAppendExisting(true);
		repo = new ExtentReports();
		repo.attachReporter(html);


	}

	@BeforeClass
	public void reportDetails() {
		test= repo.createTest(testName,testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}

	//this step is not mandatory for sequential run, but to get proper report creating node for each test step
	@BeforeMethod
	public void setNode() {
		node = test.createNode("detailed step for each step");
	}

	public void reportStep(String detail, String status) throws IOException {
		String path ="../"+takeSnapShot();
		if(status.equalsIgnoreCase("pass")) 
			node.pass(detail, MediaEntityBuilder.createScreenCaptureFromPath(path).build());

		else if(status.equalsIgnoreCase("fail")) 			
			node.fail(detail, MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}

	@AfterSuite
	public void stopReport() {
		repo.flush();
	}

	public String takeSnapShot() throws IOException {
		String filePath = "./snapShot/creaetAccSeq_ss_"+System.currentTimeMillis()+".png";
		File trg = new File(filePath);
		File src = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, trg);
		return filePath;
	}

	public boolean handleOptionalDuplicateWarning() {
		//boolean warning = false;
		WebElement warningMessage = driver.findElement(By.xpath("//h2[text()='Similar Records Exist']"));
		if (warningMessage.isDisplayed()) {
			//handleDuplicateWarning();
			return true;
		}
		else
			return false;
	}
	public void handleDuplicateWarning() {
		// Logic to handle the duplicate record warning, such as choosing to overwrite or cancel
		System.out.println("Handling duplicate record warning...");
		WebElement closeButton = driver.findElement(By.xpath("//button[@title='Close error dialog']//lightning-primitive-icon[@exportparts='icon']//*[name()='svg']"));
		closeButton.click();
	}

}
