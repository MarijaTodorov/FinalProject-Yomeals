package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getAddToCart() {
		return this.driver.findElement(
				By.xpath("//div[@class='d-flex align-items-center justify-content-between flex-lg--col']/a"));
	}

	public WebElement getQuantity() {
		return this.driver.findElement(By.name("product_qty"));
	}

	public WebElement getFavourite() {
		return this.driver.findElement(By.id("item_119"));
	}

	public void addToFavourite() {
		this.getFavourite().click();
	}

	public void addMealToCart(int qty) throws InterruptedException {
		String qt = Integer.toString(qty);
		this.getQuantity().sendKeys(Keys.CONTROL + "a");
		this.getQuantity().sendKeys(Keys.DELETE);
		Thread.sleep(2000);
		this.getQuantity().sendKeys(qt);
		this.js.executeScript("arguments[0].click()", this.getAddToCart());

	}

}
