package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.Home;
import wdMethods.ProjectMethods;

public class TC002 extends ProjectMethods{

	@BeforeTest
	public void TesData() {
		testNodes= "Jobs"; 
		testCaseName= "SearchJobsCityWise";
		testDesc="Search Digital Services jobs in a City and print";
		browser = "chrome";
	}
	
	@Test(enabled=true)
	public void searchJobsBasedOnLocation() throws InterruptedException, IOException {
		new Home(driver, test)
		.clickCareersLink()
		.clickJobOpenings()
		.filterByDivision("Digital Services")
		.filterByLocation("MEXICO")
		.CheckLoadMoreBtn()
		.getJobsListed();
	}

}
