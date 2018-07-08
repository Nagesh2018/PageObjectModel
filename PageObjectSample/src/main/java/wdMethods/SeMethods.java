package wdMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;

import utils.CommonFunctions;
import utils.Reports;

public class SeMethods  extends Reports implements WdMethods{
	public RemoteWebDriver driver;
	public Properties prop;
	public String browser;

	
	FileInputStream fis = null;
	String url;

	public SeMethods() {
		try {
			fis = new FileInputStream("src/main/resources/config.properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fis);
			url = prop.getProperty("appurl");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public RemoteWebDriver launchbrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(url);
				reportStep("Launch Browser", "Pass");
			}
			else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(url);
				reportStep("Launch Browser", "Pass");
			}
			else if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", "./drivers/MicrosoftWebDriver.exe");
				driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				driver.get(url);
				reportStep("Launch Browser", "Pass");
			}
			
		} catch (NullPointerException e) {
			System.out.println("Error " + e.getMessage());
			reportStep("Launch Browser", "Fail");
		}
		catch (WebDriverException e) {
			System.out.println("Error " + e.getMessage());	
			reportStep("Launch Browser", "Fail");
		}
		return driver;
	}



	public void closebrowser() {
		try {
				driver.quit();	
			}
		 catch (WebDriverException e) {
			System.out.println("Error in Closing Browser");
		}
		catch (Exception e) {
			System.out.println("Error in Closing Browser");
		}
	}

	//@AfterSuite
	public void teardown() throws IOException {
		if(fis!=null) {
			fis.close();
		}

	}


	public void clickwithoutSnap(WebElement ele) {
		try {
			if (ele!=null) {
				ele.click();
			}
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void clickWithSnap(WebElement ele)   {
		try {
			if (ele!=null) {
				//takeSnap();
				ele.click();
				reportStep("Click on WebElement "+ele , "Pass");
			}
		} catch (WebDriverException e) {
			reportStep("Click on WebElement "+ele, "Fail");
			System.out.println(e.getMessage());
		}
		finally {
			//takeSnap();
		}
	}

	public WebElement locateElement(String locator, String locateString) {
		WebElement ele=null;
		try {
			switch (locator) {
			case "name":
				ele = driver.findElementByName(locateString);
				//reportStep("Located Element with "+locator +"and String is "+locateString , "Pass");
				break;
			case "tagname":
				ele=driver.findElementByTagName(locateString);
	
				break;
			case "linkText":
				ele=driver.findElementByLinkText(locateString);

				break;
			case "partialLinkText":
				ele=driver.findElementByPartialLinkText(locateString);
	
				break;
			case "xpath":
				ele=driver.findElementByXPath(locateString);

				break;
			}

		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		return ele;
	}

	public WebElement locateElement(String id) {
		WebElement ele = null;
		try {
			ele = driver.findElementById(id);
			return ele;
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return ele;
	}


	@Override
	public long takeSnap() {
		if (driver!=null) {
		CommonFunctions cf = new CommonFunctions();
		long randomNum = cf.randomNum();
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		
		File destFile = new File("./images/"+randomNum+".jpeg");
		try {
			FileUtils.copyFile(source, destFile);
			return randomNum;
		} catch (IOException e) {
			System.out.println("Screen Capture Failed");
		}
		return randomNum;
		}
		return (Long) null;
		
	}


	public void mouseHover(WebElement ele, RemoteWebDriver driver) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(ele).pause(10).build().perform();
			reportStep("Mouse Hover on "+ele  , "Pass");
		} catch (NoSuchElementException e) {
			reportStep("Mouse Hover on Element "+ele  , "Fail");
			System.out.println("No Such WebElement Found");
		}
		catch (WebDriverException e) {
			reportStep("Mouse Hover on Element "+ele  , "Fail");
			System.out.println("Error Passing RemoteWebDriver");
		}

	}

	@Override
	public void ActionsType(WebElement ele, String text, int passDuration) {
		try {
			Actions builder = new Actions(driver);
			builder.sendKeys(ele, text).pause(passDuration).build().perform();
			builder.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void selectDropDownByValue(WebElement ele, String value) {
		try {
			Select sel = new Select(ele);
			sel.selectByValue(value);
			reportStep("Selected the Dropdown  "+ele +"and value " +value , "Pass");
			//System.out.println("Selected the Dropdown " + value);
		} 
		catch (NoSuchElementException e) {
			reportStep("Selected the Dropdown  "+ele +"and value " +value , "Fail");
			//System.out.println("Error finding Element");
		}
		catch (WebDriverException e) {
			reportStep("Selected the Dropdown  "+ele +"and value " +value , "Fail");
			//System.out.println("Error selecting DropDown");
		}

	}


	@Override
	public void selectDropDownByIndex(WebElement ele, int indexVal) {
		try {
			Select sel = new Select(ele);
			sel.selectByIndex(indexVal);
			//System.out.println("Selected the Dropdown " + indexVal);
			reportStep("Select Dropdown by Index " +ele + " and value "+indexVal, "Pass");
		} 
		catch (NoSuchElementException e) {
			reportStep("Select Dropdown by Index " +ele + " and value "+indexVal, "Fail");
			//System.out.println("Error finding Element");
		}
		catch (WebDriverException e) {
			reportStep("Select Dropdown by Index " +ele + " and value "+indexVal, "Fail");
			//System.out.println("Error selecting DropDown");
		}
		
	}


	@Override
	public void waitAndClick(WebElement ele, int timeToWait) throws IOException {
		try {
			WebDriverWait wt = new WebDriverWait(driver, timeToWait);
			WebElement desiredWebElem = wt.until(ExpectedConditions.elementToBeClickable(ele));
			
			if(desiredWebElem!=null) {
				ele.click();
				//System.out.println("Clicked on Element "+ele.getText());
				reportStep("Wait for "+timeToWait + "seconds before clicking on Element" +ele, "Info");
			}
			
		} catch (TimeoutException e) {
			//takeSnap();
			reportStep("Wait for "+timeToWait + "seconds before clicking on Element" +ele, "Info");
			//System.out.println("Timed out waiting for WebElement "+ele);
		}
		 catch (WebDriverException e) {
			// takeSnap();
				reportStep("Wait for "+timeToWait + "seconds before clicking on Element" +ele, "Info");
			//	System.out.println("Error Waiting for WebElement");
			}
		
	}


	@Override
	public void jsScroll(int xnum, int ynum) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, 200)","");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionsMoveTo(WebElement ele) {
	try {
		Actions builder = new Actions(driver);
		builder.moveToElement(ele).perform();
		reportStep("Move to Element " +ele , "Pass");
	} catch (Exception e) {
		reportStep("Move to Element " +ele , "Fail");
	}

	}







}




