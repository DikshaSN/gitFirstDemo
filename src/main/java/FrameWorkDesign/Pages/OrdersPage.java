package FrameWorkDesign.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkDesign.AbstractComponent.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	
	WebDriver driver;
	public OrdersPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> orders;
	
	public Boolean getOrderName(String productName) {
		Boolean match = orders.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}

}
