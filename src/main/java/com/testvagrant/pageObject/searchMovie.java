package com.testvagrant.pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

import com.testvagrant.pageObject.PageObjectIMDBWiki;
import com.testvagrant.utils.ReadInputData;

public class searchMovie
{
	WebDriver driver;
	PageObjectIMDBWiki pob;
	String  movie, releaseDateIMDB,releaseDateWiki,countryIMDB,countryWiki;
	//***********Constructor to initialize the driver**************
	
	public searchMovie(WebDriver driver) throws Exception
	{
		this.driver = driver;
		pob = new PageObjectIMDBWiki(driver);
	}
	
	

	//*******************Read Data from Input sheet********************
	public void getData(String str) throws Exception
	{
		ReadInputData read = new ReadInputData();
		movie = read.readData(str);
		System.out.println("Movie Name: "+movie);
	}
	
	public void searchMovieDetails(String platform) throws Exception
	{
		try {
			searchIMDB(platform);
			getReleaseDateIMDB(platform);
			getCountryIMDB(platform);
			navigateToWiki();
			searchWiki(platform);
			getReleaseDateWiki(platform);
			getCountryWiki(platform);
			compareValuesCountry();
			compareValuesReleaseDate();

		}
		catch(Exception e)
		{
			System.out.print(e);
		}
	}
	//*******************Navigating to Wiki*******************
	public void navigateToWiki() throws Exception
	{
		driver.navigate().to("https://en.wikipedia.org/wiki/India");
		
	}
	//*******************Searching the movie in IMDB*******************
	public void searchIMDB(String platform) throws Exception
	{
		try {
			if(platform.equalsIgnoreCase("IMDB"))
			{
				PageObjectIMDBWiki.imdbSearch.click();
				PageObjectIMDBWiki.imdbSearch.sendKeys(movie);
				Thread.sleep(3000);
				PageObjectIMDBWiki.selectFirstIMDB.click();
			}}
		catch(Exception e)
		{
			System.out.println("Not able to search the movie in IMDB");
			
		}
		
		}
	//*******************Searching the movie in Wiki*******************
	public void searchWiki(String platform) throws Exception
	{
		
		try {
			if(platform.equalsIgnoreCase("Wikipedia"))
			{
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				PageObjectIMDBWiki.searchWikipedia.click();
				PageObjectIMDBWiki.searchWikipedia.sendKeys(movie);
				Thread.sleep(3000);
				PageObjectIMDBWiki.selectFirstWiki.click();
			}
		}catch(Exception e)
		{
			System.out.println("Not able to search the movie in Wiki");
		}
	}
	
	//*******************Fetching Country value from IMDB*******************
	public void getCountryIMDB(String platform) throws Exception
	{
		try {
			if(platform.equalsIgnoreCase("IMDB"))
			{

				countryIMDB = PageObjectIMDBWiki.countryIMDB.getText();
				System.out.println("Country IMDB: "+countryIMDB);
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Country from IMDB");
		}
	}
	
	//*******************Fetching Country Value from Wiki*******************
	public void getCountryWiki(String platform) throws Exception
	{
		try
		{
				if(platform.equalsIgnoreCase("Wikipedia"))
			{
				countryWiki=PageObjectIMDBWiki.countryWiki.getText();
				System.out.println("Country Wiki: "+countryWiki);
	
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Country from Wiki");
		}

	}


	//*******************Fetching Release Date value from IMDB*******************
	public void getReleaseDateIMDB(String platform) throws Exception
	{
		try {
			JavascriptExecutor j = (JavascriptExecutor) driver;
			if(platform.equalsIgnoreCase("IMDB"))
			{
				
				j.executeScript("arguments[0].scrollIntoView();",PageObjectIMDBWiki.pageView);
				releaseDateIMDB = PageObjectIMDBWiki.releaseDateIMDB.getText();
				System.out.println("Release Date IMDB: "+releaseDateIMDB);
			}
		}
		catch(Exception e){
			System.out.println("Not able to fetch Release Date from IMDB");
		}
	}
	
	//*******************Fetching Release Date Value from Wiki*******************
	public void getReleaseDateWiki(String platform) throws Exception
	{
		try
		{
			JavascriptExecutor j = (JavascriptExecutor) driver;
			if(platform.equalsIgnoreCase("Wikipedia"))
			{
				j.executeScript("arguments[0].scrollIntoView();",PageObjectIMDBWiki.releaseDateWiki);
				releaseDateWiki=PageObjectIMDBWiki.releaseDateWiki.getText();
				System.out.println("Release Date Wikipedia: "+releaseDateWiki);
			}
		}catch(Exception e)
		{
			System.out.println("Not able to fetch Release Date from Wiki");
		}

	}
	
	//*******************Comparing Country values in IMDB and Wiki*******************
	public boolean compareValuesCountry()
	{
		System.out.println("IMDB Release Country: " +countryIMDB);
		System.out.println("Wiki Release Country: " +countryWiki);

		if((countryWiki.equalsIgnoreCase(countryIMDB)))
		{
			System.out.println("Country in IMDB and Wiki matched");
			return true;
		}
		else
		{
			System.out.println("Country in IMDB and Wiki did not match");
			return false;
		}
		
		
	}
	
	//***************Comparing Release Date values in IMDB and Wiki***************
	public boolean compareValuesReleaseDate()
	{
		System.out.println("IMDB Release Date: " +releaseDateIMDB);
		System.out.println("Wiki Release Date: " +releaseDateWiki);
		String[] dateIMDB=releaseDateIMDB.split(" ");
		String[] dateWiki=releaseDateWiki.split(" ");

		dateIMDB[1]=dateIMDB[1].substring(0, 2);
		
		
		
		dateWiki[2]=dateWiki[2].substring(0, 4);
		
		if((dateIMDB[0].equalsIgnoreCase(dateWiki[1]) && (dateIMDB[1].equalsIgnoreCase(dateWiki[0]) && (dateIMDB[2].equalsIgnoreCase(dateWiki[2])))))
		{
			System.out.println("Release Date in IMDB and Wiki matched");
			return true;
		}
		else
		{
			System.out.println("Release Date in IMDB and Wiki did not match");
			return false;
		}
		
		
	}
	
	


}
