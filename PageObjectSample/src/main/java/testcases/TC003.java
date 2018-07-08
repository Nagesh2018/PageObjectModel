package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;
import wdMethods.ProjectMethods;

public class TC003 extends ProjectMethods{
	
	@BeforeTest
	public void TesData() {
		testNodes= "Jobs"; 
		testCaseName= "Search All  Digital ServicesJobs";
		testDesc="Search all Digital Services jobs and print";
		browser = "chrome";
	}
	
	
	@Test(enabled=true)
	public void getAllDigitalServicesJobs() throws InterruptedException, IOException {
		new Home(driver, test)
		.clickCareersLink()
		.clickJobOpenings()
		.filterByDivision("Digital Services")
		.CheckLoadMoreBtn()
		.getJobsListed();
	}

}
