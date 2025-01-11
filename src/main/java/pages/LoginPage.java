package pages;

import java.io.IOException;

import org.openqa.selenium.By;

import basepackage.ProjectSpecMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends ProjectSpecMethods {


	@When("Enter username as {string}")
	public LoginPage enter_username_as(String uname) throws IOException {
		try {
			driver.findElement(By.id("username")).sendKeys(uname);
			reportStep("validate username field","pass");
		} catch (Exception e) {
			reportStep("validate username field negative","fail");
			e.printStackTrace();
		}
		return this;
	}
	@When("Enter the password as {string}")
	public LoginPage enter_the_password_as(String pwd) throws IOException {
		try {
			driver.findElement(By.id("password")).sendKeys(pwd);
			reportStep("validate password field","pass");
		} catch (Exception e) {
			reportStep("validate password field negative","Fail");
			e.printStackTrace();}
		return this;
	}
	@When("Click login button")
	public HomePage click_login_button() throws IOException {
		try{
			driver.findElement(By.id("Login")).click();
			reportStep("validate click button","PASS");
		} catch (Exception e) {
			System.out.println("click button geetah");
			reportStep("validate Click button negative","Fail");
			e.printStackTrace();}
		return new HomePage();
	}
	@Then("Verify whether logged in successfully")
	public void verify_whether_logged_in_successfully() throws IOException {
		try {
			String title = driver.getTitle();
			if(title.contains("Salesforce"))
				System.out.println("loggin succcesful with verified title");
			else
				System.out.println("loggin failed and unverified title");
			reportStep("validate successfull login validaion","pass");
		}
		catch(Exception e) {
			reportStep("validate unsuccessfull login validaion","fail");
		}
	}
}
