package FrameWorkDesign.TestComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import FrameWorkDesign.Pages.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage lp;

	public WebDriver initBrowser() throws IOException {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("headless");
		driver = new ChromeDriver(option);
		driver.manage().window().setSize(new Dimension(1440,900));
		
//		String browserName = System.getProperty("browser");
//		if(browserName.equalsIgnoreCase("chrome")) {
//		driver = new ChromeDriver();
//		}
//		
//		if(browserName.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//		}
		/*
		 * Properties p = new Properties(); FileInputStream fis = new
		 * FileInputStream(System.getProperty("user.dir") +
		 * "\\src\\main\\java\\FrameWorkDesign\\resources\\GlobalData.properties");
		 * p.load(fis); String browserName = p.getProperty("browser");
		 * 
		 * if (browserName.equalsIgnoreCase("Chrome")) { driver = new ChromeDriver(); }
		 * else if (browserName.equalsIgnoreCase("edge")) { driver = new EdgeDriver(); }
		 * System.getProperty("browser")!=null?System.getProperty("browser"):p.getProperty("browser");
		 */

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		return driver;
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		String jsonData = FileUtils.readFileToString(new File (filePath)
				,StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(("D:\\Selenium\\Scripts\\FrameWorkDesign\\Reports"+testCaseName+".png"));
		FileUtils.copyFile(source, file);
		return "D:\\Selenium\\Scripts\\FrameWorkDesign\\Reports"+testCaseName+".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		initBrowser();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		driver.quit();
	}
}
