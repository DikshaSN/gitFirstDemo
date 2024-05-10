package FrameWorkDesign.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkDesign.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".cartSection h3")
	List<WebElement> cartTitles;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkoutButton;
	
	public Boolean verifyCartItem(String productName) {
		Boolean match = cartTitles.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkoutButton.click();
		CheckoutPage checkpage = new CheckoutPage(driver);
		return checkpage;
	}
}
