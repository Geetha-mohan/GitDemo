package runner;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import basepackage.ProjectSpecMethods;
import pages.LoginPage;

public class LoginRunner extends ProjectSpecMethods {
	


@Test(dataProvider = "ExcelData")
public void runLogin(String un, String pw) throws IOException {
	LoginPage login = new LoginPage();
	login.enter_username_as(un).enter_the_password_as(pw).click_login_button().click_on_appLauncher();
}

@BeforeTest
public void set() {
	fileName = "SalesforceParam";
	sheetName = "Login";
	testName = "login GM";
	testDescription ="Login with 2 credentials in testNG";
	testAuthor= "geeTHA Mohan";
	testCategory="demo";
}

}
