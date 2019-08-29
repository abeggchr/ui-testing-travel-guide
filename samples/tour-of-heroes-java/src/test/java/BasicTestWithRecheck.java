
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

import de.retest.recheck.Recheck;
import de.retest.web.RecheckWebImpl;
import de.retest.web.selenium.RecheckDriver;

public class BasicTestWithRecheck {
	
    private WebDriver driver;
    private Recheck re;
    
    @Before
    public void BrowserOpen() throws IOException {
    	setChromedriverPath();

    	driver = new RecheckDriver( new ChromeDriver() );
    	re = new RecheckWebImpl();
    }

    @Test 
    public void A_First_Test_With_Recheck() throws InterruptedException, IOException {
        // open the url
    	driver.navigate().to("http://localhost:4200/");
    	
    	// wait for page to be completely loaded to avoids comparison mistakes
    	// caution: do not use Sleep in your codebase
    	Thread.sleep(500); 
    	
    	// creates a checkpoint in initial run and compares to it on subsequent runs
    	// "unbreakable selenium" requires such a checkpoint before a changed selector
    	re.check(driver, "initial-page" ); 
    	
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
        
        // alternatively we could also assert whole page using Recheck
        // re.check( driver, "found-narco" );
        
        // conclude test case
    	re.capTest();
    }
    
    @After
    public void BrowserClose() {
    	driver.close();
    	
    	// produce the result file
    	re.cap(); 
    }

	private static void setChromedriverPath() throws IOException {
    	String current = new java.io.File( "." ).getCanonicalPath();
    	Path path = Paths.get(current, "..", "tour-of-heroes-net\\TourOfHeroesTestNet\\bin\\Debug\\netcoreapp2.1\\chromedriver.exe");
    	System.setProperty("webdriver.chrome.driver", path.toString());
	}

}