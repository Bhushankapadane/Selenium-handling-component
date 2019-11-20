package com.parallelTestcase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParalletTestcase {
	
	  WebDriver driver;
	@Parameters({"browser"})
	@BeforeClass
	public void RunBrowser(String browser){
		if(browser.equalsIgnoreCase("chrome")){
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\SeleniumHanlingElement\\chromedriver.exe");
		driver=new ChromeDriver();	
		}	
		else if (browser.equalsIgnoreCase("firefox")){
			
			//System.setProperty("webdriver.ie.driver", "D:\\selenium\\SeleniumHanlingElement\\IEDriverServer.exe");
			 driver=new FirefoxDriver();
			
		}
		
	}
	
	@Test
	public void Tc_1(){
		
		driver.get("http://www.google.co.in");
		driver.getTitle();
		}
	
}
