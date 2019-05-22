describe('search functionality', () => {
    it('finds Narco', () => {
        // open the url
        cy.visit('http://localhost:4200')

        // search for "Narco"
        cy.get("#search-box").type('Narco');

        // assert there was a single search result found
        let searchResults = cy.get("app-hero-search ul.search-result > li");
        searchResults.should('have.length', 1);

        // assert search result was Narco
        cy.contains('Narco').within(() => searchResults);
    })
})