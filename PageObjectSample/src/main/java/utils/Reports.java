package utils;

import java.io.File;
import java.io.IOException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import wdMethods.SeMethods;

public abstract class Reports {
	public static ExtentHtmlReporter html;
	public static ExtentReports extent;
	public ExtentTest test;
	public ExtentTest testSuite;
	public String testCaseName, testDesc, testNodes, category, author;


	public ExtentReports startReport() {
		html = new ExtentHtmlReporter(new File("./extentreports/testreports.html"));
		html.setAppendExisting(false);
		extent = new ExtentReports();
		extent.attachReporter(html);
		return extent;
	}


	public ExtentTest startSuite(String testCaseName, String testDesc) {
		System.out.println("TC Name"+testCaseName+ " TC desc " + testDesc);
		testSuite = extent.createTest(testCaseName, testDesc);
		return testSuite;
	}


	//@Parameters({"browser"})
	public ExtentTest startTest(String testNodes) throws IOException  {
		test = testSuite.createNode(testNodes);
		return test;
	}

	protected abstract long takeSnap();


	public void reportStep(String description, String status) {		
		MediaEntityModelProvider image = null;
		long reportNumber = 0;
		reportNumber =takeSnap() ;		
		
		try {
			image = MediaEntityBuilder.createScreenCaptureFromPath("./../images/"+reportNumber+".jpeg").build();
		} catch (IOException e) {

			e.printStackTrace();
		}
	
			status = status.toUpperCase();
			switch (status) {
			case "PASS":
				test.pass(description, image);

			break;
			case "FAIL":
				test.fail(description, image);
				break;
			case "INFO":
				test.info(description);
				break;
			default:
				break;
			}
		

	}


	protected abstract void  teardown() throws IOException;


	public void endReport() throws IOException {
		extent.flush();
	}


}


