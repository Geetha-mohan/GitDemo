package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepackage.ProjectSpecMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateNewAcctPage extends ProjectSpecMethods {

	public static String newAccName;

	@When("Enter the Account name as {string}")
	public CreateNewAcctPage enter_the_account_name_as(String acctName) throws IOException {
		try{
			driver.findElement(By.xpath("//label[text()='Account Name']/following::input")).sendKeys(acctName);
			newAccName = acctName;
			reportStep("validate account name field","pass");
		}
		catch (Exception e) {
			reportStep("validate account name field negative","Fail");
		}
		return this;
	}

	@When("Enter the Billing address as {string} or display a duplicate record warning")
	public CreateNewAcctPage enter_the_billing_address_as(String billAddr) throws IOException {
		try {
			WebElement addr = driver.findElement(By.xpath("//label[text()='Billing City']/following::input"));
			js.executeScript("arguments[0].scrollIntoView(true)", addr);
			addr.sendKeys(billAddr);
			if(handleOptionalDuplicateWarning())
				handleDuplicateWarning();
			reportStep("billing address validation","pass");
		}
		catch(Exception e) {
			reportStep("billing address validation failed","Fail");
		}
		return this;
	}

	@When("Select the customer priority as {string} or display a duplicate record warning")
	public CreateNewAcctPage select_the_customer_priority_as(String custPriority) throws IOException {
		try {
			WebElement priority = driver.findElement(By.xpath("//label[text()='Customer Priority']/following::button/span"));
			js.executeScript("arguments[0].scrollIntoView(true)", priority);
			priority.click();
			//String dynamicXPath = String.format("//span[contains(text(),'%s')]", custPriority);
			//WebElement pri = driver.findElement(By.xpath(dynamicXPath));
			WebElement prir=driver.findElement(By.xpath("//span[contains(text(),'"+custPriority+"')]"));
			visibilityOfElement(prir,30).click();
			reportStep("customer priority validation","pass");
		}
		catch(Exception e) {
			reportStep("customer priority validation failed","Fail");
		}
		return this;
	}

	@When("Click Save button")
	public AccountPage click_save_button() throws IOException {
		try {
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			reportStep("save account details validation","pass");
		}
		catch(Exception e) {
			reportStep("save account details validation failed","Fail");
		}
		return new AccountPage();
	}


}
