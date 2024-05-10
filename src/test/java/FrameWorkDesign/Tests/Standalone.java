package FrameWorkDesign.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Standalone {

	ExtentReports report;
	@BeforeTest
	public void report() {
		String path = "D:\\Selenium\\Scripts\\FrameWorkDesign\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Test Results for Submit Order");
		reporter.config().setReportName("Submit Order");
		
		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Diksha Kamdi");
	}
	
	
	
	
	@Test
	public void SubmitOrder() throws InterruptedException {

		ExtentTest test = report.createTest("Submit Order");
		String productName = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		
		driver.findElement(By.id("userEmail")).sendKeys("dsk@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement zara = products.stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(productName))
				.findFirst().orElse(null);
		zara.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// class for loding icon .ng-animating toast-container for toast msg id
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Checkout']")));
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		Assert.assertTrue(cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName)));

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build()
				.perform();

		List<WebElement> country = driver.findElements(By.cssSelector(".list-group-item "));
		WebElement India = country.stream().filter(c -> c.getText().equals("India")).findFirst().orElse(null);
		India.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmMsg = driver.findElement(By.className("hero-primary")).getText();

		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

		test.fail("Order did not match");
		report.flush();
	}

}
