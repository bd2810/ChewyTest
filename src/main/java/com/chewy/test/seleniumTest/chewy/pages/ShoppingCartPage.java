/**
 * 
 */
package com.chewy.test.seleniumTest.chewy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chewy.test.seleniumTest.chewy.utilities.BrowserUtilities;

/**
 * @author Bhavik_D
 * 
 * This class will store all the locators and methods of shopping cart page
 *
 */
public class ShoppingCartPage {	
	
    private final static Logger logger = LoggerFactory.getLogger(ShoppingCartPage.class);
	static String EMPTY_CART_TEXT = "Your cart is empty.";
			
	@FindBy(how=How.CLASS_NAME,using="sfw-cart-list__item")
	List<WebElement> list_items_in_cart;
	
	@FindBy(how=How.CLASS_NAME,using="sfw-cart-item__name")
	WebElement cart_item_name;
	
	@FindBy(how=How.CLASS_NAME,using="sfw-cart-item__close")
	WebElement delete_cart_content;
	
	@FindBy(how=How.CLASS_NAME,using="cw-type__h1")
	WebElement empty_cart;
	
	
	public boolean verify_content_in_cart(int list_items, String name, String quantity) {
		
		boolean cart_contents = false;
		
		String item_name = cart_item_name.getText();
		Select selectElem = new Select(BrowserUtilities.driver.findElement(By.xpath(".//select[@id='cw-form-field-2']")));
		WebElement qty_option = selectElem.getFirstSelectedOption();
		String item_quantity = qty_option.getText();
		
		if (list_items_in_cart.size() == list_items && item_name.equals(name) && item_quantity.equals(quantity)) {
			
			cart_contents = true;
			logger.info("Success: Cart contents are as expected");
		}	
		
		return cart_contents;	
	}
	
	
	public void empty_cart_contents() {		
		
		delete_cart_content.click();
	}
	
	public boolean verify_cart_is_empty() {
		
		boolean cart_empty = false;
		
		String text = empty_cart.getText();		
		
		if (text.equals(EMPTY_CART_TEXT)) {
			
			cart_empty = true;
			logger.info("Success: Cart is empty");
		}	
		
		return cart_empty;	
	}	

}
