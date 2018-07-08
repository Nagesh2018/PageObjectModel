package testcases;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import wdMethods.ProjectMethods;

public class UsingSeMethods extends ProjectMethods{
	
	@Test(enabled= false)
	public void testcase1() throws InterruptedException {
		String url = prop.getProperty("appurl");
		driver.get(url);
		WebElement careerlink = locateElement("linkText", "Careers");
		Actions bldr = new Actions(driver);
		bldr.moveToElement(careerlink).pause(10).build().perform();
		WebElement jobs = locateElement("linkText", "Indecomm Job Openings");
		jobs.click();		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("load--more--filter--search")));
		WebElement search = locateElement("load--more--filter--search");
		search.sendKeys("java");
		search.sendKeys(Keys.ENTER);
		//WebElement container = locateElement("load--more--container");
		WebElement container = driver.findElementById("load--more--container");
		/*WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(container)));*/
	
		Thread.sleep(2000);
		
		List<WebElement> listedJobs = driver.findElementsByXPath("//h2[@class='title']");
		List<WebElement> location = driver.findElementsByXPath("//p[@class='job_location']");
		for (int i=0;i<= listedJobs.size()-1;i++) {
			System.out.println(listedJobs.get(i).getText());
			System.out.println("(" +location.get(i).getText()+")");
		}
	}

}
