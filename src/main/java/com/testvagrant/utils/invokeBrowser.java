package com.testvagrant.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class invokeBrowser {

	public static WebDriver driver;
	//int flag;

	//Cross Browser
	public WebDriver invokeChromeBrowser() throws Exception {

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\config.properties");
		Properties prop = new Properties();
		prop.load(fis);

		String driverPreference = prop.getProperty("driverPreference");
		

		if(driverPreference.equalsIgnoreCase("chrome")) {

			//Assign Chrome Browser
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");

			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver(options);
			

		}
		
		//Assign edge browser
		else if (driverPreference.equalsIgnoreCase("edge")) {
		
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		
		//Assign Firefox browser
		else if (driverPreference.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else {
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


		//Launch IMDB
		
		
		driver.manage().window().maximize();
		String baseUrl = prop.getProperty("baseUrl");
		driver.navigate().to(baseUrl);
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		return driver;
	}

}
