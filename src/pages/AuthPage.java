package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage extends BasicPage {

	public AuthPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getAccountDropDown() {
		return this.driver.findElement(By.xpath("//div[@class='accounts-link accounts-popup']/ul/li/a"));
	}

	public WebElement getMyAccount() {
		return this.driver.findElement(By.xpath("//div[@class='my-account-dropdown']/ul/li/a"));
	}

	public WebElement getLogout() {
		return this.driver.findElement(By.xpath("//div[@class='my-account-dropdown']/ul/li[2]/a"));
	}

	public void logout() {
		this.getAccountDropDown().click();
		waiter.until(ExpectedConditions.visibilityOf(getLogout()));
		this.getLogout().click();
	}
}
