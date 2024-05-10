package FrameWorkDesign.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import FrameWorkDesign.Pages.CartPage;
import FrameWorkDesign.Pages.CheckoutPage;
import FrameWorkDesign.Pages.LandingPage;
import FrameWorkDesign.Pages.OrdersPage;
import FrameWorkDesign.Pages.ProductCatalog;
import FrameWorkDesign.TestComponent.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	@Test(dataProvider= "getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
	
		ProductCatalog pc = lp.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = pc.getProductsList();
		pc.addToCard(input.get("product"));
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyCartItem(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkpage = cp.goToCheckoutPage();
		checkpage.SelectCountry("India");
		Thread.sleep(2000);
		checkpage.goToConfimationPage();
		String message = checkpage.getConfirmMessage();
		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void verifyOrders() {
		ProductCatalog pc = lp.loginApplication("dsk@gmail.com", "Test@123");
		OrdersPage oc = pc.goToOrdersPage();
		//Assert.assertTrue( oc.getOrderName(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonData("D:\\Selenium\\Scripts\\FrameWorkDesign\\src\\test\\java\\FrameWorkDesign\\data\\data.json");
		
		return new Object[][]{{data.get(0)},{data.get(1)}};
		 
	}
}
