/**
 * 
 */
package com.chewy.test.seleniumTest.chewy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bhavik_D
 * 
 * This class will store all the locators and methods of product detail page
 *
 */
public class ProductDetailPage {
	
    private final static Logger logger = LoggerFactory.getLogger(ProductDetailPage.class);
	
	@FindBy(how=How.ID,using="product-container")
	WebElement product_detail_page;
	
	@FindBy(how=How.ID,using="product-title")
	WebElement product_title;
	
	@FindBy(how=How.XPATH,using=".//input[@class='cw-btn cw-btn--action cw-btn--full js-add-cart']")
	WebElement add_to_cart;
	
	@FindBy(how=How.CLASS_NAME,using="sfw-product-sku__info__title")
	WebElement product_info_title;
	
	@FindBy(how=How.CLASS_NAME,using="sfw-product-sku__added__label")
	WebElement added_to_cart_text;
	
	@FindBy(how=How.XPATH,using="//*[name()='path' and contains(@d,'M4.452 11.')]")
	WebElement added_to_cart_confirm_icon;
	
	
	public boolean verify_product_detail_page(String name) {
		
		boolean on_product_detail_page = false;
		
		String title = product_title.getText();
		
		if (product_detail_page.isDisplayed() && title.contains(name)) {
			
			on_product_detail_page = true;
			logger.info("Success: Landed on correct product detail page for product: {}", name);
		}	
		
		return on_product_detail_page;		
		
	}	
	
	public void add_item_to_cart() {
		
		add_to_cart.click();
	}	
	
	public boolean verify_item_added_to_cart(String name) {
		
		boolean added_to_cart = false;
		
		String title_info_text = product_info_title.getText();
		String add_to_cart_text = added_to_cart_text.getText();
		
		if (title_info_text.contains(name) && add_to_cart_text.equals("added to your cart") && added_to_cart_confirm_icon.isDisplayed()) {
			
			added_to_cart = true;
			logger.info("Success: Item added to cart successfully");

		}	
		
		return added_to_cart;				
	}

}
