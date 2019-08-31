# UI Testing Travel Guide

_Note: do not write down project or customer names here as this file will be checked in to a public repostory_

## Introduction

### Topic

This sessions topic is about UI testing. We called it the "UI testing travel guide" because you can compare introducing UI tests to a project to traveling to a new country. You will go to new places and this travel guide helps you to visit (like implement) the sights worth visiting and avoid the dark corners.

### Claim

We claim that you can implement a reliable UI test suite using a Selenium-based approach which will strengthen your safety net.

### About us

Why are we standing here? Obviously because you voted for our session. Thanks for that.
But also: We worked together in the same project. And one of the firsts tasks there was to introduce UI testing. 

So, I am ...
* Marija
* Milan
* Christian

For this presentation we not only brought together our own experience but we also talked with a lot of people from inside and outside of Zühlke. So we will present you the condensed knowledge of the following projects:

* list of projects
* 

### Overview

Our presentation is structured in following five parts:

* How to develop
* What to test
* How to setup a stable environment
* How to implement maintainble tests 
* How to implement reliable tests

## How to develop

### Consider Alternatives

First of all, you have to decide whether or not to use Selenium at all. So what are the alternatives?

* Cypress - Open source, tests are written using Mocha and Chai, tests are running in real time in  browser.
* Cucumber - BDD syntax, plain-language tests, living documentations.
* Katalon Studio - Record/playback with page object models support.
* Screenster - Visual testing, automatic verification, self-healing locators, automatic waits, codeless test maintenance. 

#### Commercial Products

There are commercial products like "Ranorex" or "Tosca". The come with a price tag and a lot of features you probably do not need like:

* test case management
* testing non-web UIs 
* test data generation
* test case recording
* test-data setup

What both of them have in common is the ability to also run Selenium tests. Ranorex refers to Selenium as "todays web test automation standard".

Regarding the last point, test case recording, Selenium also offers a tool to record. This is called *Selenium IDE*. It lets you quickly record a test case.

#### Demo: Test case recoding with Selenium

![Selenium IDE Basic Test](./img/selenium-ide-basic-test.png)

This leads us to a first pattern. We try to describe patterns in the form of Problem, Solution, Advantages, Drawbacks

#### Pattern: Test case recording

*Problem*: Test case implementation is costly. It takes quite some time to implement a UI test. Non-functional requirements of a UI test include:

* Reliability: a UI test suceeding once should succeed always when nothing in the application or test code as changed
* Maintainability: a change in the UI structure should have as little impact to the test implementation as possible

*Solution*: Test cases can be recorded by a tool. The tool automatically detects a matching and stable selector.

*Advantages*:

* fast implementation
* lower learning curve

*Drawbacks*: 

* maintenance will grow as selectors are repeated over and over again 
* selectors might not be detected as you wish

*Related Pattern*: The alternative is to implement test yourself. 

#### Challengers

In the area of open software, there are currently strong challengers for Selenium. "Puppeteer" provides a high-level API to control a Chrome browser. It is very thight integrated which allows to control the browser in more ways than Selenium can. 

"Cypress" works with the same approach. It is tightly integrated with a Chrome browser. And, in addition to "Puppeteer" it offers amazing test running, test recording and test debuging possibilities. If you cannot restrict yourself to Chrome, then an alternative is "TestCafé".

While Selenium is restricted to black-box testing, a thight browser integration also allows for more white-box testing approach. For example, in the test code, you can mock backend-calls or you can emit an event inside your application.

All in all: "Cypress" is definitely worth evaluating. We will stick to Selenium in this presentation as this is the area where we gathered knowledge in many projects.  

### How does Selenium work

Selenium server (either standalone or started locally)

using WebDriver protocol (specification: https://w3c.github.io/webdriver/)

BrowserDriver implementation per Browser

controlls the Browser

### TypeScript vs. C# or Java

Now the question is, how to control the Selenium server. There are libraries in JavaScript, C#, Java, Ruby and Phyton to control the API exposed by the Selenium server.

Criterias:

* How comfortable do you (or your team) feel with working with this language
  * tooling
  * refactoring possibilities 
* Debugging
* Test data creation (we'll come back to this later)

#### Demo: Basic test case in C# and in TypeScript

## What to test?

top of the test pyramid

UI tests are slow and costly

### Granularity

*Problem*: UI tests are costly to implement and slow to execute. 

Non-functional requirements of a UI test include:

* Reliability: a UI test suceeding once should succeed always when nothing in the application or test code as changed
* Maintainability: a change in the UI structure should have as little impact to the test implementation as possible
* Fast feedback: as every other test a UI test should provide fast feedback
* Documentation: a UI test acts as a documentation on how a system is supposed to work

#### Pattern: Avoid UI tests

*Solution*: Avoid implementing UI tests at all. Implement tests as contract tests, integration tests or unit tests (in both the UI and the backend).

*Advantages*:

* no need to pay for implementation, build setup, test data setup, external system simulators, maintenance, ...

*Drawbacks*: 

* You deliberately take a higher risk
* Many of the steps required for automated UI tests like test data or external system simulators are also useful for development

*Related Pattern*: see other patterns in this chapter "Granularity"

#### Pattern: Very few (< 10) UI tests

*Solution*: Design the tests so that they cover all possible connections between the components. Example: You have an we wich hosts the UI, an applicaiton server for the business logic (420 use cases), a job server for report creation (there are 500 different reports) and an distribution system for sending emails (42 different templates in 8 different languages). For that scenario there might be two test cases: one case creating a single report and another one calling the distribution service. 

*Advantages*:

* lower costs
* little need to go with a full blown, highly maintainable UI testing codebase
* fast execution, fast feedback (given that your deployment and test data setup is also fast)

*Drawbacks*: 

* there might be still a gap between what you can cover in lower stage tests and what you cover with UI tests
* documentation

*Related Pattern*: see other patterns in this chapter "Granularity"

#### Pattern: Few (< 100) UI tests

*Solution*: Design the tests so that they cover the happy-cases of the main features of the application. Like the main scenario. Prefer few but comprehensive tests over small but many tests. 

*Advantages*:

* reasonable feature coverage
* good documentation

*Drawbacks*: 

* reaching a good level reliability and maintainability is expensive
* execution time will suffer
* failure analysis: a comprehensive test which is failing does not directly point to the area in the application where something is wrong, it needs manual analysis

*Related Pattern*: see other patterns in this chapter "Granularity"

#### Pattern: Many (> 100) small UI tests

*Solution*: Test cases are split into small parts. These parts test a dedicated functionality. Test depend on each other. Example: there is a test A which tests the login functionality. And then there are two other tests B and C depending on the fact, that login is working. Even though they probably log in as well, they will not be executed when the test A fails.

*Advantages*:

* failure analysis: faster because test tests only 1 (or at least few) functionalities
* execution time is optimized, failing fast
* reasonable up to perfect feature coverage

*Drawbacks*: 

* getting a documentation out of the tests is more difficult
* an infrastructure when it is very easy to just add a new UI test might lead to having too many UI tests

*Related Pattern*: see other patterns in this chapter "Granularity"

### What browser to target?

Chrome only or do you need to support IE, Edge and Firefox as well? And, well, there are mobile devices as well.

Discussion "Focusing on Chrome only"

Pro:
* the tooling for Chrome is the most advanced
  * stable Chromedriver
  * Docker images easily available
* the UI test cases do not test for cross-browser issues

Cons:
* 80% of the users are not using Chrome but ...
* Chrome is not be on the list of the browsers which have to be supported

## How to setup a stable environment

### Deploy & Build System

### Versions

With so many libraries and applications (Browser, Selenium Server, Selenium library, Protractor library) it happens that tests run on one environment and fail in another due to different versions. 

In order to have stable environment for running UI tests, locally but on the build server also, we need to fix versions of Selenium Server, Chrome driver version, as well as NPM package version which is used to install Selenium standalone. They need to be the same on the build server as it is locally, so that we can have reliable tests.

### External Systems

*Problem*: Systems outside of your application are not under your control. They might be running or might be down. They might return what is specified in the interface agreement or the might return something else. When you are testing end-to-end, you have to specify where the end is. One definition could be from one external system, through your application to another external system. In order to have a stable environment where you can run automated tests reliably, you have to bring these external systems under control.

#### Pattern: Mock external systems in the application

*Solution*: Using dependency injection, mock services are injected for external sytems directly in the system under test. Instead of connecting to an external system they use the mocks service in-process.

*Advantages*:

* faster execution
* lower cost for implementation

*Drawbacks*: 

* the communication from your application to the external system is not tested
* having no test code in your productive codebase is a challenge
* could be potentialy misaligned with the real system if not mocked correctly or in proper level of detail.

*Related Pattern*: see other patterns in this chapter "External Systems"

#### Pattern: Mock external systems outside the application

*Solution*: The application connects to a system whith the same interface agreement as the real external system but which is developed and deployed by you.

*Advantages*:

* the communication from your application to the external system is tested
* no test code in your productive codebase
* allows to simulate network failures

*Drawbacks*: 

* more expensive

*Related Pattern*: see other patterns in this chapter "External Systems"

### Test Data

*Problem*: Tests build on a certain state.

*Requirements": 
* test data setup and management is as close a possible to the test code
* test do not depend on each other, so their test data is separated / do not depend on shared test data (except reference data)

#### Pattern: DB Dump

*Solution*: Using a (pre)created DB dump, which is restored before Test Suite start running.

*Advantages*:

* restoring a DB snapshot/backup allows to start testing very fast.

*Drawbacks*: 

* test data management is far away from the test implementation
* using DB modification scripts to setup the test data is cumbersome 

*Related Pattern*: see other patterns in this chapter "Test Data"


#### Pattern: Test Data Generator

*Solution*: Use a Test Data Generator (app), before running test to setup test data.

*Advantages*:

* improved test data management / overview
* re-use of builders from integrationtests possible
* can also feed your external system simulators

*Drawbacks*: 

* test data management is far away from the test implementation
* executing the generator might setup the data required for all test leading to slow execution during development
* implementing a test data reset/update mechanism (once the test changed the data) is hard
* going back to a DB snapshot and re-running the generator is time consuming
* expensive to implement

*Related Pattern*: see other patterns in this chapter "Test Data"


#### Pattern: Test setups Test Data directly in DB

*Solution*: Test data is setup directly in DB from the test.

*Advantages*:

* re-use of builder from integrationtests 
* faster test setup 
* only setting up the data required data (for a single test during development)

*Drawbacks*: 

* reusing builders from integration test is hard to implement when tests are implemented (i.e. TypeScript) in another language than in the backend (i.e. C#), calling .NET code from TypeScript is theoretically possible using Edge.js but such an attempt failed in one project because it requires node to be installed which was not possible then
* when using different languages in test and backend and connecting to DB directly from the test


*Related Pattern*: see other patterns in this chapter "Test Data"


#### Pattern: Test setups Test Data using Web API

*Solution*: Test setup data by using Web API in the backend to perform parts of a test setup

*Advantages*:

* tests that are faster, more focused, and more reliable.
* almost every modern programming language has libraries that can be used to make REST API calls.

*Drawbacks*: 

* either exposing test-only endpoints in productive code
* when using different languages in test and backend: leads to kind of a duplication of the builder infrastructure in the two languages

*Related Pattern*: see other patterns in this chapter "Test Data"


#### Pattern: Test setups Test Data while testing

*Solution*: Test data is setup in each test, by reading it from some file, such as .json. 

*Advantages*:

* no infrastructure required

*Drawbacks*: 

* works only in limited cases
* tests are force not to test "a single thing"
* slower test execution

*Related Pattern*: see other patterns in this chapter "Test Data"

## How to implement maintainble tests

### Pattern: Page Object

*Problem*: Instead of ad-hoc interactions with a page, a test controls the page using an instance that represents the page user interface.

*Solution*: Page Objects are used to make end-to-end tests readable and easy to maintain. 

*Advantages*:

* They keep all page element selectors in one place.
* They standardize how tests interact with the page.

*Drawbacks*: 

*

From related article "App Action":

* Page objects are hard to maintain and take away time from actual application development. I have never seen PageObjects documented well enough to actually help one write tests.
* Page objects introduce additional state into the tests, which is separate from the application’s internal state. This makes understanding the tests and failures harder.
* Page objects try to fit multiple cases into a uniform interface, falling back to conditional logic - a huge anti-pattern in our opinion.
* Page objects make tests slow because they force the tests to always go through the application user interface.

*Related Pattern*: "App Actions" (see https://www.cypress.io/blog/2019/01/03/stop-using-page-objects-and-start-using-app-actions/)

### Pattern: Selector with data-attribute

*Problem*: In order for Selenium to select an element on the page, a selector (aka. locator) has to be defined. CSS selectors can be used, as well as XPath queries or just the string of the element containing. With Protractor you can even use the ng-model name or a binding. As outlined in the page object pattern, selectors are best nested so that the area for Selenium where to search for an element is as small as possible. Nevertheless, it sometimes remains hard for a page object to specify the correct selector. 

*Solution*: Adding additional information to the DOM helps selecting elements in the DOM. One option is to add additional id attributes or additional classes. The problem with this approach is, that id are intended to be unique throughout the page and classes are supposed to be used for styling and not for testing. So the approach is to add a `data-testId` attribute. In order not to pollute the productive application with unused attributes, this data attributes are removed when building for production. Classes and regular IDs are no longer used for selectors.

*Advantages*:

* specific identifier for tests
* no HTML pollution in production

*Drawbacks*: 

* attribute has to be added, most probably during development of the test

*Related Pattern*: Self-Healing Automation

### Pattern: Self-Healing Automation

*Problem*: In order for an automated test to select an element on the page, a selector (aka. locator) has to be defined. These selectors might change while the application is developed without the test being updated.

*Solution*: Use a product with a "self-healing" functionality. They, sometimes supported by an AI, learn when a particular selector changed its selector and it adjusts the selector while the test is running.

*Advantages*:

* more reliable

*Drawbacks*: 

* changes to the test have to be accepted at some point

*Products*:

* recheck-web (Demo)
* Applitools
* mabl
* Perfecto

See also: https://www.yammer.com/zuehlke.com/threads/303810815508480

*Related Pattern*: Selector with data-attribute

*Demo*: Recheck / Java

## How to implement reliable tests

*Problem*: Even tough you have setup a stable environment and your tests suceeded multiple time, tests still fail randomly with `NoSuchElementException`, `ElementNotVisibleException` or a similar exception. This is primarly due to timing issues. The element might not be in the DOM because there is still an XHR request going on. Or it is not clickable because there is an animation running which takes its time. Or the UI framework just did not yet finish rendering. 

### Pattern: Implicit wait

*Solution*: Selenium has the possiblity to switch implicit wait on. An implicit wait is to tell WebDriver to poll the DOM for a certain amount of time when trying to find an element or elements if they are not immediately available. The default setting is 0 which means implicit wait is disabled. 

*Advantages*:

* promise to just work

*Drawbacks*: 

* there are more wait conditions checked than nescessary which results in slower execution time

*Related Pattern*: see in this chapter

### Pattern: Explicit wait (where needed)

*Solution*: An explicit wait is code you define to wait for a certain condition to occur before proceeding further in the code. The worst case of this is `Thread.sleep()`, which sets the condition to an exact time period to wait. There are convenience methods available to help you write code that will only wait as long as required. The Seleniums API `ExpectedCondition` is one way to do this, another way is to implement your own retry mechanism for example with the Polly library in .NET. Wait conditions are only added where needed (i.e. after triggering a page action which requires a backend call).

*Advantages*:

* only waiting for what needs to waited for
* failing fast and not unnesscesary waiting

*Drawbacks*: 

* its sometimes hard to figure out where to add the wait condition
* From documentation: Do not mix implicit and explicit waits! Doing so can cause unpredictable wait times. 

*Related Pattern*: see in this chapter

### Pattern: Explicit wait (generic)

*Solution*: Given you have a mean to determine when the page is expected to be ready, you can implement your own waiting strategy. For example if you can determine when the frontend is waiting for a backend call to respond, you can wait for that to happen and do not have to wait for a message to appear on the screen. 

*Advantages*:

* only waiting for what needs to waited for
* no unnesscesary waiting results in faster execution time
* failing fast because the test knows when to wait
* waiting solved in a 'one beats all' manner

*Drawbacks*: 

* requires means to inspect application and determine its internal state

*Related Pattern*: see in this chapter

### Pattern: Use Protractor 

*Solution*: If you happen to use Angular you can use the Selenium wrapper "Protractor". Protractor basically does an explicit wait in a generic fashion: "Protractor can automatically execute the next step in your test the moment the webpage finishes pending tasks, so you don’t have to worry about waiting for your test and webpage to sync."

*Advantages*:

* waiting solved in a 'one beats all' manner

*Drawbacks*: 

* unfortunately, what Protractor waits for is not enough
* special effort is required when your test leaves the Angular application

*Related Pattern*: see in this chapter

### Pattern: Wrap all calls to Selenium and

*Solution*: All calls to Selenium are passed through a wrapper. In this wrapper, every time an element is accessed, there is an explicit wait condition for the element to be visible, clickable or whatever is best suited. 

*Advantages*:

* it works

*Drawbacks*: 

* there are more wait conditions checked than nescessary which results in slower execution time
* special effort is required when your test leaves the Angular application
* does not fail fast because it first waits

*Related Pattern*: see in this chapter


### Sidenote: Cypress

*Solution*: The solution of Cypress to the timing is that they implement an explicit wait on every element interaction (see: https://docs.cypress.io/guides/core-concepts/interacting-with-elements.html#Actionability)

*Advantages*:

* 

*Drawbacks*: 

* 

*Related Pattern*: see in this chapter

## Conclusion