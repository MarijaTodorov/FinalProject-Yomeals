package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopupPage extends BasicPage {

	public LocationPopupPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLocationHeader() {
		return driver.findElement(By.xpath("//*[@class = 'location-search-wrapper']"));
	}

	public WebElement getCloseElement() {
		return waiter
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='close-btn close-btn-white']")));
	}

	public WebElement getKeyword() {
		return driver.findElement(By.id("locality_keyword"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.id("location_id"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.name("btn_submit"));
	}

	public void openPopup() {
		this.getLocationHeader().click();
	}

	public void setLocation(String locationName) {
		this.getKeyword().click();
		String value = this.getLocationItem(locationName).getAttribute("data-value");
		js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), value);
		js.executeScript("arguments[0].click()", this.getSubmit());
	}

	public void closePopup() {
		this.getCloseElement().click();
	}

}
