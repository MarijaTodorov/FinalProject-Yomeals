package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	public WebElement getEditProfile() {
		return this.driver.findElement(By.xpath("//div[@class='action-profile']/a[2]"));
	}

	public WebElement getFirstName() {
		return this.driver.findElement(By.name("user_first_name"));
	}

	public WebElement getLastName() {
		return this.driver.findElement(By.name("user_last_name"));
	}

	public WebElement getAddress() {
		return this.driver.findElement(By.name("user_address"));
	}

	public WebElement getPhone() {
		return this.driver.findElement(By.name("user_phone"));
	}

	public WebElement getZipCode() {
		return this.driver.findElement(By.name("user_zip"));
	}

	public Select getCountry() {
		WebElement selectCountry = this.driver.findElement(By.id("user_country_id"));
		Select country = new Select(selectCountry);
		return country;
	}

	public Select getState() {
		WebElement selectState = this.driver.findElement(By.id("user_state_id"));
		Select state = new Select(selectState);
		return state;
	}

	public Select getCity() {
		WebElement selectCity = this.driver.findElement(By.id("user_city"));
		Select city = new Select(selectCity);
		return city;
	}

	public WebElement getSaveBtn() {
		return this.driver.findElement(By.xpath("//input[@class ='btn btn--primary block-on-mobile']"));

	}

	public WebElement getUpload() {
		return this.driver.findElement(By.xpath("//*[@class='upload uploadFile-Js']"));
	}

	public WebElement getUploadForm() {
		return this.driver.findElement(By.xpath("//*[@id='form-upload']/input"));
	}

	public void uploadImg(String imgPath) {
		js.executeScript("arguments[0].click();", this.getUpload());
		this.getUploadForm().sendKeys(imgPath);
	}

	public WebElement getRemoveImgBtn() {
		return this.driver.findElement(By.className("remove"));
	}

	public void deleteImg() {
		js.executeScript("arguments[0].click();", this.getRemoveImgBtn());
	}

	public void changeInfo(String firstName, String lastName, String address, String phone, String zipCode,
			String country, String state, String city) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		this.getPhone().clear();
		this.getPhone().sendKeys(phone);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(1000);
		this.getState().selectByVisibleText(state);
		Thread.sleep(1000);
		this.getCity().selectByVisibleText(city);
		Thread.sleep(4000);
		this.getSaveBtn().submit();

	}

}
