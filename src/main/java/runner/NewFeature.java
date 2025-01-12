package runner;


import org.testng.annotations.Test;

import basepackage.ProjectSpecMethods;


public class NewFeature extends ProjectSpecMethods {


	@Test(alwaysRun = true)
	public void runNewFeature() {
		
		System.out.println("new feature added");
	}


}
