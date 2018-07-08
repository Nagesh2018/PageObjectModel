package wdMethods;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface WdMethods {
	
	/* This method will click the WebElement
	 * WebElement should be provided as parameters
	 * Throws noSuchElementFoundException if element is not passed or null
	 */
	public void clickwithoutSnap(WebElement ele);
	
	/* This method would locate WebElement using name, xpath, Tagname, LinkedText and PartialLinkedText
	 * Returns WebElement
	 */
	public WebElement locateElement(String locator, String locateString);
	
	/* This method would locate WebElement using id
	 * Returns WebElement
	 */
	public WebElement locateElement(String id);	
	
	/* This method will capture screenshot of the WebDriver
	 */
	public long takeSnap() throws IOException ;
		
	/* This Method will Mouse Hover the WebElement provided in parameters
	 *  Params required WebElement and RemoteWebDriver
	 *  Return NoSuchElementFoundException and/or WebDriver Exception
	 */
	public void  mouseHover(WebElement ele, RemoteWebDriver driver);

	/* This Method will type provided text in the located WebElement and passes for milli seconds provided in the parameters
	 *  input - WebElement, String text to be typed,PassDuration in milliseconds and then hits Enter Key
	 */
	public void ActionsType(WebElement ele,String text, int passDuration);
	
	/* This method will select the Dropdown values 
	 *  params required WebElement of type Select and value to select
	 */
	public void selectDropDownByValue(WebElement ele, String value);
	
	/* This method will select the Dropdown values using index
	 *  params required WebElement of type Select and Index to select
	 */
	public void selectDropDownByIndex(WebElement ele, int indexVal);
	
	/* This method waits few mins and clicks if the Element exists. Else returns TimeOut Exception
	 *  params required WebElement and Time in MilliSeconds to wait before click
	 */
	public void waitAndClick(WebElement ele, int timeToWait) throws IOException;
	
	/* This method will scroll down either vertically or horizontally 
	 * Based on the the x and y pixels provided in the argument
	 */
	public void jsScroll(int xnum, int ynum);
	
	/* This method will move to element using Actions class
	 *  Params required in the webelement where u need to move to
	 */
	public void actionsMoveTo(WebElement ele);
		
	
	
}
