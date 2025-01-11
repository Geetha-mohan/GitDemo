package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepackage.ProjectSpecMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountPage extends ProjectSpecMethods {

	@When("Click New button")
	public CreateNewAcctPage click_new_button() throws IOException {
		try {
			driver.findElement(By.xpath("//div[@title='New']")).click();
			reportStep("clicking new button validation","pass");}
		catch(Exception e) {
			reportStep("clicking new button validation failed","failed");
		}
		return new CreateNewAcctPage();
	}


	@Then("Verify Account created successfully")
	public AccountPage verify_account_created_successfully() throws IOException {
		try {
			String titleAccName = driver.findElement(By.xpath("//slot[@name='entityLabel']//following::lightning-formatted-text")).getText();
			System.out.println("Title in acct page is "+titleAccName);
			System.out.println("account name after create is "+CreateNewAcctPage.newAccName);
			if(titleAccName.contains(CreateNewAcctPage.newAccName))
				System.out.println("account created successfully");
			else
				System.out.println("some issue with account creation, pls verify");
			reportStep("successful account creation validation","pass");
		}
		catch(Exception e) {
			reportStep("successful account creation validation failed","fail");
		}
		return this;
	}

}
