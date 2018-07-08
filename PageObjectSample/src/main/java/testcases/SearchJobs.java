package testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import wdMethods.SeMethods;

public class SearchJobs extends SeMethods{

	@Test
	public void searchjob() throws InterruptedException {
		//launchbrowser("chrome");
		String url = prop.getProperty("appurl");
		driver.get(url);
		WebElement careersLink = driver.findElementByLinkText("Careers");
		Actions bldr = new Actions(driver);
		bldr.moveToElement(careersLink).pause(10).build().perform();
		WebElement jobs = driver.findElementByLinkText("Indecomm Job Openings");
		jobs.click();		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("load--more--filter--search")));

		WebElement search = driver.findElementById("load--more--filter--search");

		bldr.sendKeys(search, "java").pause(2000).build().perform();
		bldr.sendKeys(Keys.ENTER).build().perform();

		
		WebElement container = driver.findElementByXPath("//*[@id='load--more--container']");
		wait.until(ExpectedConditions.elementToBeClickable(container));
		
		//Get all jobs listed 
		
		List<WebElement> listedJobs = driver.findElementsByXPath("//h2[@class='title']");
		List<WebElement> location = driver.findElementsByXPath("//p[@class='job_location']");
		for (int i=0;i<= listedJobs.size()-1;i++) {
			System.out.println(listedJobs.get(i).getText());
			System.out.println("(" +location.get(i).getText()+")");
		}
	
	}
}
