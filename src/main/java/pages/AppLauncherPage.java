package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepackage.ProjectSpecMethods;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppLauncherPage extends ProjectSpecMethods {

	@When("Click Accounts menu from App Launcher")
	public AccountPage click_accounts_menu_from_app_launcher() throws IOException {
		try {
			WebElement acct = driver.findElement(By.xpath("//p[text()='Accounts']"));
			js.executeScript("arguments[0].scrollIntoView(true)",acct);
			acct.click();
			reportStep("validate account link functionality success","pass");
		}
		catch (Exception e) {
			reportStep("validate account link functionality negative","Fail");
		}
		return new AccountPage();
	}

}
