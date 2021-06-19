package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import pages.AuthPage;
import pages.CartSummaryPage;
import pages.LocationPopupPage;
import pages.LoginPage;
import pages.MealPage;
import pages.NotificationSystemPage;
import pages.ProfilePage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected SoftAssert sa;

	protected String baseUrl;
	protected String email;
	protected String password;

	protected LocationPopupPage popUpPage;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationPage;
	protected ProfilePage profilePage;
	protected AuthPage authPage;
	protected MealPage mealPage;
	protected CartSummaryPage summaryPage;

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		this.driver = new ChromeDriver();
		this.waiter = new WebDriverWait(driver, 10);
		this.js = (JavascriptExecutor) driver;
		this.sa = new SoftAssert();

		this.baseUrl = "http://demo.yo-meals.com/";
		this.email = "customer@dummyid.com";
		this.password = "12345678a";

		this.popUpPage = new LocationPopupPage(driver, js, waiter);
		this.loginPage = new LoginPage(driver, js, waiter);
		this.profilePage = new ProfilePage(driver, js, waiter);
		this.notificationPage = new NotificationSystemPage(driver, js, waiter);
		this.authPage = new AuthPage(driver, js, waiter);
		this.mealPage = new MealPage(driver, js, waiter);
		this.summaryPage = new CartSummaryPage(driver, js, waiter);

		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

//	@AfterTest
//	public void cleanup() {
//		this.driver.quit();
//	}
}
