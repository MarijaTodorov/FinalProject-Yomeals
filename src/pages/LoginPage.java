package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage {

	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getLoginWindow() {
		return driver.findElement(By.className("filled"));
	}

	public WebElement getEmail() {
		return driver.findElement(By.name("username"));
	}

	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}

	public WebElement getRememberMe() {
		return driver.findElement(By.name("remember_me"));
	}

	public WebElement getLoginBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
	public void login (String email, String password) {
		this.getEmail().clear();
		this.getPassword().clear();
		this.getEmail().sendKeys(email);
		this.getPassword().sendKeys(password);
	}
}
