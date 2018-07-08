package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import wdMethods.ProjectMethods;

public class Home extends ProjectMethods{

	public Home(RemoteWebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test= test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(how=How.LINK_TEXT, using="Careers") WebElement careersLink;
	public Home clickCareersLink() {
		mouseHover(careersLink, driver);
	
		return this;
	}

	@FindBy(linkText="Indecomm Job Openings") WebElement ele;
	//@FindBy(id="load--more--filter--search") WebElement ele2;
	public Jobs clickJobOpenings() {
		//clickwithoutSnap(ele);	
		clickWithSnap(ele);
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("load--more--container")));
		return new Jobs(driver, test);
	}

	

}
