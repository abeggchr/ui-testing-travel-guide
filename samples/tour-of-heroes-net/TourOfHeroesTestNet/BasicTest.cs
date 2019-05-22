using System;
using System.Linq;
using System.Threading;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using Xunit;

namespace TourOfHeroesTestNet
{
    public class BasicTest : IDisposable
    {
        private readonly IWebDriver _driver;

        public BasicTest()
        {
            // Note: using constructor to setup and Dispose() to teardown (XUnit)
            _driver = new ChromeDriver(".");
        }

        [Fact]
        public void A_First_Test()
        {
            // open the url
            _driver.Url = "http://localhost:4200/";

            // search for "Narco"
            var searchInput = _driver.FindElement(By.CssSelector("#search-box"));
            searchInput.SendKeys("Narco");

            // wait until UI is updated (don't use Sleep() in a real project)
            Thread.Sleep(1000);

            // assert there was a single search result found and that it text is Narco
            var searchResults = _driver.FindElements(By.CssSelector("app-hero-search ul.search-result > li"));
            Assert.Single(searchResults);
            var searchResult = searchResults.First();
            Assert.Equal("Narco", searchResult.Text);
        }

        public void Dispose()
        {
            _driver.Close();
        }
    }
}

