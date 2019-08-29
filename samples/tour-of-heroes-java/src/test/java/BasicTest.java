
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicTest {
	
    private WebDriver driver;
    
    @Before
    public void BrowserOpen() throws IOException {
    	setChromedriverPath();
    	
    	driver = new ChromeDriver();
    }

    @Test
    public void A_First_Test() throws InterruptedException, IOException {
        // open the url
    	driver.navigate().to("http://localhost:4200/");
    	
        // search for "Narco"
        WebElement searchInput = driver.findElement(By.id("search-box"));
        searchInput.sendKeys("Narco");

        // wait until UI is updated (do not use Sleep in your codebase)
        Thread.sleep(500); 

        // assert there was a single search result found
        List<WebElement> searchResults = driver.findElements(By.cssSelector("app-hero-search ul.search-result > li"));
        Assert.assertEquals(1, searchResults.size());

        // assert search result was Narco
        WebElement searchResult = searchResults.get(0);
        Assert.assertEquals("Narco", searchResult.getText());
    }
    
    @After
    public void BrowserClose() {
    	driver.close(); 
    }

	private static void setChromedriverPath() throws IOException {
    	String current = new java.io.File( "." ).getCanonicalPath();
    	Path path = Paths.get(current, "..", "tour-of-heroes-net\\TourOfHeroesTestNet\\bin\\Debug\\netcoreapp2.1\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.driver", path.toString());
	}

}