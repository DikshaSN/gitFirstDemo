package FrameWorkDesign.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkDesign.AbstractComponent.AbstractComponent;

public class ProductCatalog extends AbstractComponent{

	WebDriver driver;
	
	 public ProductCatalog(WebDriver driver) {
		 super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	 
	 @FindBy(css=".mb-3")
	 List<WebElement> products;
	 
	 @FindBy(css=".ng-animating")
	 WebElement spinner;
	 
	 @FindBy(css="button[routerlink*='cart']")
	 WebElement cartButton;
	 
	 By productList = By.cssSelector(".mb-3");
	 By addToCart = By.cssSelector(".card-body button:last-of-type");
	 By toastMessage = By.id("toast-container");
	 
	 public List<WebElement> getProductsList() {
		 waitForElementToAppear(productList);
		 return products;
	 }
	
	public WebElement getProductByName(String product) {
		
		WebElement item = getProductsList().stream().filter(p -> p.findElement(By.tagName("b")).getText().equals(product))
				.findFirst().orElse(null);
		return item;
	}
	
	public void addToCard(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		
		//cartButton.click();
	}

}
