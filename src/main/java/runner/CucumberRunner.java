package runner;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import basepackage.ProjectSpecMethods;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/main/java/features",
glue="pages",
publish=true,
monochrome=true)


public class CucumberRunner extends ProjectSpecMethods{

	
	@BeforeTest
	  public void setValue() {
		 //fileName="SalesforceParameters";
		 testName = "login GM";
			testDescription ="Login with 2 credentials in cucumber";
			testAuthor= "gee Mohan";
			testCategory="demoCucumber";}
}
