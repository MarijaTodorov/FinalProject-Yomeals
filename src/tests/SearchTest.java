package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	String numberMsg = "[ERROR] Number of products doesn't match";
	String nameMsg = "[ERROR] Product names are different";

	@Test(priority = 0)
	public void searchResults() throws InterruptedException, IOException {

		File file = new File("data/data.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Meal Search Results");
		SoftAssert sa = new SoftAssert();

		this.driver.navigate().to(baseUrl + "meals");
		this.popUpPage.setLocation(locationName);

		for (int i = 1; i < 7; i++) {
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			this.driver.get(url);
			Thread.sleep(3000);

			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			this.popUpPage.openPopup();
			this.popUpPage.setLocation(location);
			Thread.sleep(4000);

			int resultNo = (int) sheet.getRow(i).getCell(2).getNumericCellValue();
			int numberOfMeals = this.searchPage.getMealNo();
			Thread.sleep(3000);

			sa.assertEquals(numberOfMeals, resultNo, numberMsg);
			Thread.sleep(4000);

			for (int j = 3; j < 3 + resultNo; j++) {
				String meal = sheet.getRow(i).getCell(j).getStringCellValue();
				String mealName = searchPage.getNames().get(j - 3);
				Thread.sleep(3000);
				sa.assertTrue(mealName.contains(meal), nameMsg);

			}
			Thread.sleep(3000);
		}
		sa.assertAll();
	}
}
