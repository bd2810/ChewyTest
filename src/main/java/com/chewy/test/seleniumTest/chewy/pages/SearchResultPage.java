/**
 * 
 */
package com.chewy.test.seleniumTest.chewy.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bhavik_D
 * 
 * This class will store all the locators and methods of products search result page
 *
 */
public class SearchResultPage {
	
    private final static Logger logger = LoggerFactory.getLogger(SearchResultPage.class);
	
	@FindBy(how=How.CSS,using="article.product-holder.js-tracked-product.cw-card.cw-card-hover")
	List<WebElement> result_items;
	
	public boolean verify_product_exists_by_name(String name) {
		
		boolean product_exists = false;
		
		for (WebElement item : result_items) {
			
			String product_name = item.getAttribute("data-name");
			
			if (product_name.equals(name)) {
				
				product_exists = true;
				logger.info("Success: Found product with name: {}", name);				
				break;				
			}			
		}		
		
		return product_exists;
	}
	
	public boolean verify_product_exists_by_num(String num, String name) {
		
		boolean product_exists = false;
		
		for (WebElement item : result_items) {
			
			String product_num = item.getAttribute("data-id");
			String product_name = item.getAttribute("data-name");
			
			if (product_num.equals(num) && product_name.equals(name)) {
				
				product_exists = true;
				logger.info("Success: Found product with item number: {}", num);				
				break;				
			}			
		}		
		
		return product_exists;
	}
	
	public void click_on_specific_product_by_name(String name) {		
		
		for (WebElement item : result_items) {
			
			String product_name = item.getAttribute("data-name");
			
			if (product_name.equals(name)) {
				
				item.click();
				break;
			}			
		}		
		
	}

}
