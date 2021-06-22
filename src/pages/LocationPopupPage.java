package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLocationHeader() {
		return this.driver.findElement(By.xpath("//*[@class = 'location-selector']"));
	}

	public WebElement getCloseElement() {
		return this.driver.findElement(By.xpath("//*[@class='close-btn close-btn-white']"));
	}

	public WebElement getKeyword() {
		return this.driver.findElement(By.id("locality_keyword"));
	}

	public void openPopup() {
		this.getLocationHeader().click();
	}

	public WebElement getLocationItem(String locationName) {
		return this.driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return this.driver.findElement(By.id("location_id"));
	}

	public WebElement getSubmit() {
		return this.driver.findElement(By.name("btn_submit"));
	}

	public void getLocationForm() {
		this.getLocationHeader().click();
	}

	public void setLocation(String locationName) throws InterruptedException {
		this.getKeyword().click();
		Thread.sleep(300);

		String dataValue = this.getLocationItem(locationName).getAttribute("data-value");
		Thread.sleep(300);

		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), dataValue);
		Thread.sleep(300);
		js.executeScript("arguments[0].click();", this.getSubmit());
	}

	public void closePopup() {
		this.getCloseElement().click();
	}

}
