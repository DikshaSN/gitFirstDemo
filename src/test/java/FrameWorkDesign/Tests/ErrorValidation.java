package FrameWorkDesign.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameWorkDesign.Pages.CartPage;
import FrameWorkDesign.Pages.ProductCatalog;
import FrameWorkDesign.TestComponent.BaseTest;
import FrameWorkDesign.TestComponent.Retry;

public class ErrorValidation extends BaseTest{
	
	@Test(groups={"Error"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		
		lp.loginApplication("dsk@gmail.com", "Test123");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());	
	
	}

	@Test
	public void productErrorValidation() throws InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalog pc = lp.loginApplication("dikshakamdi@123.com", "Diksha@12");
		List<WebElement> products = pc.getProductsList();
		pc.addToCard(productName);
		CartPage cp = pc.goToCartPage();
		Boolean match = cp.verifyCartItem("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
