package wdMethods;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.Reports;

public class ProjectMethods extends SeMethods{


@BeforeSuite
public void startReport1() {
	startReport();
}
	
@BeforeClass
public void startTestModule() {
	 startSuite(testCaseName, testDesc);
}

@BeforeMethod
public void startTestCase() throws IOException {
	test = startTest(testNodes);
	launchbrowser(browser);
}

@AfterMethod
public void afterMethod() {
	closebrowser() ;
}

@AfterSuite
public void closeReport() throws IOException {
	endReport();
	teardown();
}



}
