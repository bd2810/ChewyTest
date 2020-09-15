package com.chewy.test.seleniumTest.chewy.tests;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.chewy.test.seleniumTest.chewy.pages.HomePage;
import com.chewy.test.seleniumTest.chewy.pages.ProductDetailPage;
import com.chewy.test.seleniumTest.chewy.pages.SearchResultPage;
import com.chewy.test.seleniumTest.chewy.pages.ShoppingCartPage;
import com.chewy.test.seleniumTest.chewy.utilities.BrowserUtilities;


/**
 * @author Bhavik_D
 * 
 * This class contains the actual test for Chewy code assessment
 *
 */
public class ChewySampleTest {
	
    private final static Logger logger = LoggerFactory.getLogger(ChewySampleTest.class);
	
	static String URL = "https://www.chewy.com/";
	static String PRODUCT_NAME = "Vetsulin";
	static String PRODUCT_NUM = "146103";
	static String FULL_PRODUCT_NAME = "Vetsulin Insulin U-40 for Dogs & Cats, 10-mL";
	int num_of_items = 1;
	String quantity = "1";
	WebDriver driver = null;
	
	
	@BeforeClass
	public void beforeClass () {
		
		BasicConfigurator.configure();
	}
	
	
	@Test
	public void verifyChewySampleTest() {

		try {
			
			// Step 1: Open a browser to chewy.com
			logger.info("Initializing browser and navigating to {}", URL);
			driver = BrowserUtilities.startBrowser(URL);
			logger.info("Initializing Web elements on pages using PageFactory..");			
			HomePage home_page = PageFactory.initElements(driver, HomePage.class);
			SearchResultPage result_page = PageFactory.initElements(driver, SearchResultPage.class);		
			ProductDetailPage product_detail_page = PageFactory.initElements(driver, ProductDetailPage.class);
			ShoppingCartPage cart_page = PageFactory.initElements(driver, ShoppingCartPage.class);
			
			// Step 2: Search a product by name: Vetsulin and verify
			logger.info("Search product using product name: {}", PRODUCT_NAME);
			home_page.search_product_by_name(PRODUCT_NAME);
			boolean verify_product_exists_by_name = result_page.verify_product_exists_by_name(FULL_PRODUCT_NAME);
			Assert.assertTrue(verify_product_exists_by_name, "Error: Product does NOT exist with name: " +PRODUCT_NAME);
			
			// Step 3: Search a product by Item Number: 146103
			logger.info("Search product using item number: {}", PRODUCT_NUM);
			home_page.search_product_by_num(PRODUCT_NUM);
			
			// Step 4: Verify result of search
			boolean verify_product_exists_by_num = result_page.verify_product_exists_by_num(PRODUCT_NUM, FULL_PRODUCT_NAME);
			Assert.assertTrue(verify_product_exists_by_num, "Error: Product does NOT exist with item number: " +PRODUCT_NUM);
			
			// Step 5: Click into specific item “Vetsulin Insulin U-40 for Dogs &amp; Cats, 10-mL”
			logger.info("Clicking on product: {}", FULL_PRODUCT_NAME);
			result_page.click_on_specific_product_by_name(FULL_PRODUCT_NAME);
			
			// Step 6: Verify landed on product detail
			logger.info("Verify it landed on correct product detail page..");
			boolean verify_landed_on_product_detail_page = product_detail_page.verify_product_detail_page(FULL_PRODUCT_NAME);
			Assert.assertTrue(verify_landed_on_product_detail_page, "Error: Did NOT land on correct product detail page");
			
			// Step 7: Add item to the cart
			logger.info("Add item to the cart..");
			product_detail_page.add_item_to_cart();
			
			// Step 8: Verify item added to cart
			logger.info("Verify item added to cart successfully..");
			boolean item_added_to_cart = product_detail_page.verify_item_added_to_cart(FULL_PRODUCT_NAME);
			Assert.assertTrue(item_added_to_cart, "Error: Item was NOT added to cart successfully");
			
			// Step 9: Navigate to cart
			logger.info("Navigate to cart..");
			home_page.navigate_to_cart();
			
			// Step 10: Verify cart contents
			logger.info("Verify cart contents..");
			boolean cart_contents = cart_page.verify_content_in_cart(num_of_items, FULL_PRODUCT_NAME, quantity);
			Assert.assertTrue(cart_contents, "Error: Cart contents are NOT as expected.");
			
			// Step 11: Empty cart
			logger.info("Empty cart contents..");
			cart_page.empty_cart_contents();	
			
			// Step 12: Verify cart is empty
			logger.info("Verify cart is empty..");
			Thread.sleep(2000);
			boolean cart_empty = cart_page.verify_cart_is_empty();
			Assert.assertTrue(cart_empty, "Error: Cart is NOT empty.");
			
			
		} catch (Exception e) {
			
	            e.printStackTrace();
	            
		} finally {
			
			// Step 13: Close browser
			BrowserUtilities.closeBrowser(driver);
			
		}		
		
	}	

}
