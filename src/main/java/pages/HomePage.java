package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import basepackage.ProjectSpecMethods;
import io.cucumber.java.en.When;

public class HomePage extends ProjectSpecMethods {
	@When("Click on AppLauncher toggle menu")
	public HomePage click_on_appLauncher() throws IOException {
		try {
			WebElement toggle = driver.findElement(By.xpath("//button[contains(@title,'App Launcher')]/div"));
			waitForClickability(toggle,30).click();
			reportStep("validate app launcher menu","PASS");
		} catch (Exception e) {
			reportStep("validate app launcher menu negative","Fail");
			//e.printStackTrace();}
		}return this;
	}

	@When("Click on View All option")
	public AppLauncherPage click_on_view_all_option() throws IOException {
		try{
			waitForClickability(driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications') and text()='View All']")),30).click();
			reportStep("validate View all link menu","PASS");
		} catch (Exception e) {
			reportStep("validate View all link negative","Fail");
		}
		return new AppLauncherPage();
	}
}
