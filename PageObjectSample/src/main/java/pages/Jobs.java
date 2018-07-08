package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class Jobs extends ProjectMethods{
	String property;
	public Jobs(RemoteWebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
			
	}

	@FindBy(id="load--more--filter--search") WebElement ele1;
	@FindBy(xpath="//*[@id='load--more--container']") WebElement container;

	public Jobs enterTextAndPause(String toType, int duration) {
		ActionsType(ele1, toType, duration);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.elementToBeClickable(container));
		//jsScroll(0, 200);
		actionsMoveTo(container);
		return this;

	}

	//@FindBy(xpath="//h2[@class='title']") List<WebElement> listedJobs;
	//@FindBy(xpath="//p[@class='job_location']") List<WebElement> location;

	public Jobs getJobsListed() {
		try {
			List<WebElement> listedJobs = driver.findElementsByXPath("//h2[@class='title']");
			List<WebElement> location = driver.findElementsByXPath("//p[@class='job_location']");
			System.out.println("Total Jobs :" +listedJobs.size());

			for (int i=0;i<= listedJobs.size()-1;i++) {
				System.out.println(listedJobs.get(i).getText());
				System.out.println("(" +location.get(i).getText()+")");
			}
			
			if(listedJobs.get(0).getText().contains("Java")) {
				reportStep("Get jobs Listed" , "Pass");
			}
			
		} catch (Exception e) {
			reportStep("Get jobs Listed" , "Fail");
			//e.printStackTrace();
		}
		return this;
	}


	//@FindBy(id="load--more--filter--division") WebElement eleDivision;
	@FindBy(xpath="//select[@id='load--more--filter--division']") WebElement eleDivision;
	public Jobs filterByDivision(String valDivision) {
		try {
		selectDropDownByIndex(eleDivision, 1);
		reportStep("filer By Division " + eleDivision, "pass");
	
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			reportStep("filer By Division " + eleDivision, "fail");
		} catch (WebDriverException e) {
			reportStep("filer By Division " + eleDivision, "fail");
		}
		return this;
	}



	@FindBy(id="load--more--filter--country") WebElement eleCountry;
	public Jobs filterByLocation(String loc) {
		try {
			selectDropDownByValue(eleCountry, loc);
			reportStep("Filter By Location "+loc , "Pass");
		} catch (Exception e) {
			reportStep("Filter By Location "+loc , "Fail");
		}
		return this;
	}


	//@FindBy(id="load--more--trigger") WebElement loadMore;
	public Jobs CheckLoadMoreBtn() throws InterruptedException, IOException {
		try {
		//WebElement loadMore = driver.findElementById("load--more--trigger");
		WebElement loadMore = locateElement("load--more--trigger");
		
		if(loadMore!=null) {
			waitAndClick(loadMore, 2);
			Thread.sleep(2000);
		}
		else {
			System.out.println("No items to Load Futher");
		}	
			
		}
	
		catch (WebDriverException e) {
			e.printStackTrace();
		}
		
		return this;
	}



}
