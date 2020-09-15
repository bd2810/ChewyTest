/**
 * 
 */
package com.chewy.test.seleniumTest.chewy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
 

/**
 * @author Bhavik_D
 * 
 * This class will store all the locators and methods of home page
 *
 */
public class HomePage {
	
	@FindBy(how=How.ID,using="search-autocomplete")
	WebElement search_bar;
	
	@FindBy(how=How.NAME,using="nav-submit-button")
	WebElement search_submit;
	
	@FindBy(how=How.CSS,using="a.cw-btn.cw-btn--default")
	WebElement navigate_to_cart;
	
	public void search_product_by_name(String name) {
		
		search_bar.sendKeys(name);
		search_submit.click();
	}
	
	public void search_product_by_num(String item_num) {
		
		search_bar.sendKeys(item_num);
		search_submit.click();
	}
	
	public void navigate_to_cart() {	
		
		navigate_to_cart.click();
	}	

}
