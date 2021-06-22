package tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	String firstName = "Jackie";
	String lastName = "Smith";
	String address = "Mi Cordelia Drive Northwest";
	String phone = "+1 555 123 4567";
	String zipCode = "87120";
	String country = "United States";
	String state = "New Mexico";
	String city = "Albuquerque";

	String loginMsg = "Login Successfull";
	String loginMsgFail = "[ERROR] Unexpected login message";
	String setupMsg = "Setup Successful";
	String setupMsgFail = " [ERROR] Unexpected setup message";
	String logoutMsg = "Logout Successfull!";
	String logoutMsgFail = " [ERROR] Unexpected logout message";
	String imgMsg = "Profile Image Uploaded Successfully";
	String imgMsgFail = " [ERROR] Image Upload failed";
	String imgDelete = "Profile Image Deleted Successfully";
	String imgDeleteFail = "Profile Image Deleted Failed";

	@Test(priority = 1)
	public void editProfile() throws InterruptedException {

		// login
		this.driver.navigate().to(baseUrl + "guest-user/login-form");
		this.popUpPage.closePopup();
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(loginMsg), this.loginMsgFail);

		// edit
		this.driver.navigate().to(baseUrl + "member/profile");
		this.profilePage.changeInfo(firstName, lastName, address, phone, zipCode, country, state, city);
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(setupMsg), this.setupMsgFail);
		this.notificationPage.waitUntilMessageDisappears();

		// logout
		this.authPage.logout();
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(logoutMsg), this.logoutMsgFail);
	}

	@Test(priority = 2)
	public void changeImg() throws IOException {
		
		//login
		this.driver.navigate().to(baseUrl + "/guest-user/login-form");
		this.popUpPage.closePopup();
		this.loginPage.login(email, password);
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(loginMsg), this.loginMsgFail);
		this.notificationPage.waitUntilMessageDisappears();
		
		//Img upload
		this.driver.navigate().to(baseUrl + "/member/profile");
		String imgPath = new File("C:\\Users\\Maja\\Desktop\\Projekat\\FinalProject-Yomeals\\img\\wow.png").getCanonicalPath();
		this.profilePage.uploadImg(imgPath);
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(imgMsg), this.imgMsgFail);
		this.notificationPage.waitUntilMessageDisappears();
		
		//Img delete
		this.profilePage.deleteImg();
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(imgDelete), this.imgDeleteFail);
		this.notificationPage.waitUntilMessageDisappears();

		// logout
		this.authPage.logout();
		Assert.assertTrue(this.notificationPage.getMessageTxt().contains(logoutMsg), this.logoutMsgFail);
		this.notificationPage.waitUntilMessageDisappears();
	}
}
