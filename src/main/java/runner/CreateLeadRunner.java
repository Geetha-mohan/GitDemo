package runner;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basepackage.ProjectSpecMethods;
import pages.AppLauncherPage;
import pages.HomePage;
import pages.LoginPage;

public class CreateLeadRunner extends ProjectSpecMethods {


	private HomePage hp;

	@BeforeMethod public void setup() throws IOException { 
		LoginRunner logn = new LoginRunner(); 
		logn.runLogin("dilip@testleaf.com", "leaf@2024");
		hp = new  HomePage(); 
	}

	//@Test(dataProvider="ExcelData", dependsOnMethods = {"runner.LoginRunner.runLogin"}, alwaysRun = true)
	@Test(dataProvider="ExcelData", alwaysRun = true)
	public void runCreateAccount(String accName, String billCity, String custPrity) throws IOException {
		hp.click_on_view_all_option().click_accounts_menu_from_app_launcher().click_new_button().
		enter_the_account_name_as(accName).enter_the_billing_address_as(billCity).
		select_the_customer_priority_as(custPrity).click_save_button().verify_account_created_successfully();
	}

	@BeforeTest
	public void set() {
		fileName = "SalesforceParam";
		sheetName = "CreateAccount";
		testName = "create New Accounts";
		testDescription ="create account with testNG";
		testAuthor= "gee Mohan";
		testCategory="sanity";
	}
}
