package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MealItemTest extends BasicTest {

	int qty1 = 3;
	String locationMsg1 = "The Following Errors Occurred:";
	String locationMsg2 = "Please Select Location";
	String locationMsgfail = "[ERROR] No message shown";
	String cartMsg = "Meal Added To Cart";
	String cartMsgFail = " [ERROR] Adding to cart was unsuccessful";
	String favoriteMsg = "Please login first!";
	String favoriteMsgFail = " [ERROR] Unexpected login message";
	String favoriteMealMsg = "Product has been added to your favorites";
	String favoriteMealMsgFail = " [ERROR] Product has not been added to your favorites";
	String mealMsg = "Meal Added To Cart";
	String mealMsgFail = "[ERROR] Cart is empty";
	String cartRemoveMsg = "All meals removed from Cart successfully";
	String cartRemoveMsgError = "[ERROR] Items have not been removed";

//	@Test (priority = 3)
//	public void addMealToCart() throws InterruptedException {
//		
//		//Add meal
//		this.driver.navigate().to(baseUrl+"meal/lobster-shrimp-chicken-quesadilla-combo");
//		this.popUpPage.closePopup();
//		this.mealPage.addMealToCart(qty1);
//		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(locationMsg1),this.locationMsgfail);
//		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(locationMsg2),this.locationMsgfail);
//		this.notificationPage.waitUntilMessageDisappears();
//		
//		//Set location and add meal
//		this.popUpPage.openPopup();
//		this.popUpPage.setLocation(locationName);		
//		
//		this.mealPage.addMealToCart(qty1);
//		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(cartMsg), this.cartMsgFail);
//	}
//	@Test (priority = 4)
//	public void addToFavorite() throws InterruptedException {
//
//		//Add to favorite	
//		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
//		this.popUpPage.closePopup();
//		this.mealPage.addToFavourite();
//
//		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(favoriteMsg), this.favoriteMsgFail);
//		this.notificationPage.waitUntilMessageDisappears();
//		
//		//User login
//		this.driver.navigate().to(baseUrl + "guest-user/login-form");
//		Thread.sleep(2000);
//		this.loginPage.login(email, password);
//
//		//Add to favorite
//		this.driver.navigate().to(baseUrl + "meal/lobster-shrimp-chicken-quesadilla-combo");
//		this.mealPage.addToFavourite();
//		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(favoriteMealMsg), this.favoriteMealMsgFail); 
//		this.notificationPage.waitUntilMessageDisappears();
//
//	}
	@Test(priority = 5)
	public void clearCart() throws InterruptedException, IOException {

		this.driver.navigate().to(baseUrl + "meals");
		Thread.sleep(2000);
		this.popUpPage.setLocation(locationName);
		Thread.sleep(2000);

		// Import data
		File file = new File("data/Data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meals");

		// Adding meals to cart
		for (int i = 1; i < sheet.getLastRowNum(); i++) {
			String meal= sheet.getRow(i).getCell(0).getStringCellValue();
			int qty = (int) sheet.getRow(i).getCell(1).getNumericCellValue();

			this.driver.navigate().to(meal);
			this.mealPage.addMealToCart(qty);
			Assert.assertTrue(this.notificationPage.getMessageTxt().contains(mealMsg), this.mealMsgFail);
		}
		// Removing meals from cart
		this.summaryPage.cartClearAll();
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(cartRemoveMsg), this.cartRemoveMsgError);
		this.notificationPage.waitUntilMessageDisappears();

	}
}
