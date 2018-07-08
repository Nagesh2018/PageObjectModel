package wdMethods;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser implements IRetryAnalyzer{

	
	int count=0; int maxCount=2;
	@Override
	public boolean retry(ITestResult arg0) {
		if(count < maxCount) {
			count++;
			return true;
		}
		
		return false;
	}

}
