package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;
import wdMethods.ProjectMethods;

public class TC001 extends ProjectMethods{

	@BeforeTest
	public void TesData() {
		testNodes= "Jobs"; 
		testCaseName= "SearchAllJobs";
		testDesc="Search all jobs and print";
		browser = "chrome";
	}

	@Test(enabled= true)
	public void searchJobs() {
		new Home(driver, test)
		.clickCareersLink()
		.clickJobOpenings()
		.enterTextAndPause("java", 2000)
		.getJobsListed();
	}


}
