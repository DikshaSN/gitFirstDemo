package FrameWorkDesign.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FrameWorkDesign.AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".list-group-item ")
	List<WebElement> country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(className="hero-primary")
	WebElement confirmMsg;
	
	public List<WebElement> countrylist() {
		return country;
	}
	public void SelectCountry(String country) throws InterruptedException {
		Actions a = new Actions(driver);
		
		a.sendKeys(selectCountry,country).build().perform();
		Thread.sleep(1000);
		WebElement India = countrylist().stream().filter(c -> c.getText().equals(country)).findFirst().orElse(null);
		India.click();
	}
	
	public void goToConfimationPage() {
		
		submit.click();
	}
	
	public String getConfirmMessage() {
		String confirmmsg = confirmMsg.getText();
		return confirmmsg;
		
	}

}
