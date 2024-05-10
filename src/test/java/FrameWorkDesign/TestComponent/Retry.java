package FrameWorkDesign.TestComponent;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count =0;
	int maxRun =1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<maxRun) {
			count++;
			return true;
		}
		return false;
	}

}
