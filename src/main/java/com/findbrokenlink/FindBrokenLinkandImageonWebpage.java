package com.findbrokenlink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindBrokenLinkandImageonWebpage {

	static WebDriver driver;

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\SeleniumHanlingElement\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("https://www.olacabs.com");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);

		driver.get("https://www.javabykiran.com");
	   		
		/*WebElement email = driver.findElement(By.xpath("//*[@id='email']"));
		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement sign = driver.findElement(By.xpath("//*[@id='form']/div[3]/div/button"));
		email.sendKeys("kiran@gmail.com");
		password.sendKeys("123456");
		sign.click();*/

		List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		allLinks.addAll(driver.findElements(By.tagName("img"))); // add the
																	// links add
																	// image in
																	// all links
																	// object

		int sizeofallLinks = allLinks.size();
		System.out.println("sizeofallLinks=" + sizeofallLinks);

		// get only those that not have href attribute
		List<WebElement> ActiveLinks = new ArrayList<WebElement>();

		for (int i = 0; i < allLinks.size(); i++) {

			System.out.println("StringContain=" + allLinks.get(i).getAttribute("href"));// show
																						// All
																						// Links

			// check only contain href link and not contain javascripts
			if (allLinks.get(i).getAttribute("href") != null
					&& (!allLinks.get(i).getAttribute("href").contains("javascript"))) {

				// Get the size og active links
				ActiveLinks.add(allLinks.get(i));
			}
		}
		System.out.println("ActiveLinks=" + ActiveLinks.size());

		// 3 check the href url with httpConnection api
		// Response
		// 200=OK
		// 404=not found
		// 500 internal error
		// 400 bad request

		for (int j = 0; j < ActiveLinks.size(); j++) {

			HttpURLConnection connection = (HttpURLConnection) new URL(ActiveLinks.get(j).getAttribute("href"))
					.openConnection();

			
			connection.connect();
             String response = connection.getResponseMessage();// ok
			connection.disconnect();
			System.out.println(ActiveLinks.get(j).getAttribute("href") + "------>" + response);

		}
		driver.close();

	}

}
