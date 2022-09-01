package com.testvagrant.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectIMDBWiki {
	WebDriver driver;
	
	@FindBy(xpath = "//input[@type='text' and @placeholder = 'Search IMDb']")
	public static WebElement imdbSearch;
	
	@FindBy(xpath = "//input[@type='search' and @placeholder = 'Search Wikipedia']")
	public static WebElement searchWikipedia;
	
	@FindBy(xpath = "(//a[@data-testid='search-result--const'])[1]")
	public static WebElement selectFirstIMDB;
	
	@FindBy(xpath = "(//a[@class='mw-searchSuggest-link'])[1]")
	public static WebElement selectFirstWiki;
	
	@FindBy(xpath = "(//*[@id='__next'])/main/div/section[1]/div/section/div/div[1]/section[9]")
	public static WebElement pageView;
	
	@FindBy(xpath = "(//*[@id='__next'])/main/div/section[1]/div/section/div/div[1]/section[9]/div[2]/ul/li[1]/a[2]")
	public static WebElement releaseSelectIMDB;
	
	@FindBy(xpath = "//a[.='Release date']//following-sibling::div/ul/li/a")
	public static WebElement releaseDateIMDB;
	
	@FindBy(xpath = "(//div[.='Release date']//parent::th)//following-sibling::td/div/ul/li")
	public static WebElement releaseDateWiki;
	
	@FindBy (xpath = "//th[.='Country']//following-sibling::td")
	public static WebElement countryWiki;
	
	@FindBy (xpath = "//span[.='Country of origin']//following-sibling::div/ul/li/a")
	public static WebElement countryIMDB;
	
	public PageObjectIMDBWiki(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
