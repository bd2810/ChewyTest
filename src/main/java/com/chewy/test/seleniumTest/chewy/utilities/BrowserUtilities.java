
package com.chewy.test.seleniumTest.chewy.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 * @author Bhavik_D
 * 
 * This class will store all the methods for browser activity
 *
 */
public class BrowserUtilities {
	
	public static WebDriver driver;
	public static final String chromeDriverPath = "./src/main/resources/chromedriver.exe";	
	
	public static WebDriver startBrowser(String url) {
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(url);
		
		return driver;
		
	}
	
	public static void closeBrowser(WebDriver driver) {
		
		driver.close();
		
	}

}
