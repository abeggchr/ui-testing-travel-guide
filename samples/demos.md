# Demos

# Pre-Requisites

* node.js
* ng cli
* Chrome
* Microsoft Visual Studio 
* .net core 2.1 (comes with Visual Studio 2019)
* Eclipse
* 


## Start Tour Of Heroes

* `cd tour-of-heroes`
* `ng serve`
* open browser at `http://localhost:4200`

# Demo Protractor

* `ng e2e`

# Demo .NET

* open solution
* build solution
* run tests on BasicTest

Note: you might need to update the NuGet package Chromedriver to match the locally installed Chrome instance

# Demo Java

Note: requires .NET project to be built beforehand because it references the chromedriver.exe from there

## same as .NET

* open project
* maven update project
* run BasicTest.java as JUnit Test (under src/test/java)
* Difference: explicit wait

## changed selector

* go to "Tour of Heroes" Angular app source code
* search for "search-box", replace with "search-box-new"
* run BasicTest.java, test fails

## Recheck to the rescue!

* compare BasicTestWithRecheck.java and BasicTest.java with each other
* run BasicTestWithRecheck.java, test fails because it creates a golden master
* re-run, test succeed
* change selector
* test succeeds!
* inspect console: 

16:52:16.822 [main] WARN de.retest.web.selenium.TestHealer - *************** recheck warning ***************
16:52:16.822 [main] WARN de.retest.web.selenium.TestHealer - The HTML id attribute used for element identification changed from 'search-box' to 'search-box-new'.
16:52:16.822 [main] WARN de.retest.web.selenium.TestHealer - retest identified the element based on the persisted Golden Master.
16:52:16.824 [main] WARN de.retest.web.selenium.TestHealer - If you apply these changes to the Golden Master , your test BasicTestWithRecheck will break.
16:52:16.824 [main] WARN de.retest.web.selenium.TestHealer - Use `By.id("search-box-new")` or `By.retestId("input")` to update your test BasicTestWithRecheck.java:48.
