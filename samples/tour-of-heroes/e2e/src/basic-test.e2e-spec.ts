'use strict'; // necessary for es6 output in node

import { browser, element, by } from 'protractor';

fdescribe('search functionality', () => {

    it('finds Narco', async () => {
        // open the url
        await browser.get('');

        // search for "Narco"
        var searchInput = element(by.css("#search-box"));
        await searchInput.sendKeys("Narco");

        // assert there was a single search result found
        var searchResults = element.all(by.css("app-hero-search ul.search-result > li"));
        expect(await searchResults.count()).toBe(1);

        // assert search result was Narco
        var searchResult = await searchResults.first().getText();
        expect(searchResult).toBe('Narco');
    });
});