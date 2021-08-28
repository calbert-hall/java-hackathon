# Applitools Hackathon: Java

### READ FIRST: Hackathon Structure 
1. At least **one week before the hackathon**, complete the pre-requisites as well as run the first example to ensure that everything has been installed correctly. 
2. On the day of the hackathon, follow the instructions in the hackathon section. 
3. If you finish the hackathon exercises with extra time available (or want to experiment more after the event), 
   see the "Additional Exercises" below. 


## Prerequisites:

1. Java is installed on your machine.
   
   * [Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   
2. Maven is installed on your machine, JAVA_HOME environment variable is created, added to PATH, path to Maven is added to PATH too

   * [Install Maven, configure enviroment variables](https://maven.apache.org/install.html)

3. Chrome Webdriver is on your machine and is in the PATH. Here are some resources from the internet that'll help you.

   * https://splinter.readthedocs.io/en/0.1/setup-chrome.html
   * https://stackoverflow.com/questions/38081021/using-selenium-on-mac-chrome
   * https://www.youtube.com/watch?time_continue=182&v=dz59GsdvUF8

4. IntelliJ IDEA (or Eclipse) is installed on your machine if you want to run example from IDE.

   * [Install IntelliJ IDEA](https://www.jetbrains.com/idea/download/)  or [Install Eclipse](https://www.eclipse.org/downloads/)
     

### Running the first example

1. Git clone this repo 
   
   * `git clone https://github.com/calbert-hall/java-hackathon`, or download as a Zip file and unzip. 
   
2. Navigate to just cloned folder java_hackathon.

3. Get an API key by logging into Applitools > Person Icon > My API Key.

4. Open in any editor file src\test\java\com\applitools\quickstarts\BasicDemo.java  and set your ApiKey in string 'config.setApiKey("...")' (or comment the string and set APPLITOOLS_API_KEY environment variable). 

5. Run 'mvn -Dtest=BasicDemo test' or click the 'Run' button in Eclipse/IntelliJ.

6. If you want to run example from IDE perform next steps:

   6.1. Import the project as a *Maven* project in IntelliJ IDEA or Eclipse.

   6.2 Set Project SDK to your JDK (installed in Pre-requisites)  in Intellij - File > Project Structure > Project.

   6.3 Run or Debug class BasicDemo or method test().

# Hackathon Exercise!

* In this exercise, you'll be a Test Engineer looking to run tests on an e-commerce site, "Applifashion". 
  You'll be using Applitools to find and report bugs and handle dynamic content using Applitools' visual AI.
  You'll be working in the `Hackathon_Activity` file in `src/test/java/Hackathon`. An example solution is available in the `Hackathon_Solution` file, but don't look unless you're stuck! If you want to see view the expected results, you can run `mvn -D test=Hackathon_Solution test
`.

1. Open up the `Hackathon_Activity` java file. Put your name (or team name) as the Batch name, and be sure to set your API key as you did in the first example. You can run this using `mvn -D test=Hackathon_Activity -D devUrl=applifashionv1 test`.


2. We'll start with a test on [Applifashion V1](https://demo.applitools.com/gridHackathonV1.html). Create an Applitools test that navigates to the main page and takes a full page screenshot. See the first example on how to do this.

   
3. Next, we'll use Selenium webdriver to click the "black" filter button, and then click the filter button. The needed CSS selectors are provided for your use. After filtering, take another screenshot with Applitools. 
   Both of these eyes.check calls should be within the same test.
   

4. Now, you'll use the Selenium webdriver to click the "Appli Air x Night" shoes and take another full page Applitools check. You should have a test with 3 checks. 
 

5. Set the `dynamicContent` boolean to `true`. Re-run your test, and notice that your initial test will fail due to dynamic content. 
Draw a layout region over the area, or set the match level to "Layout" from within the SDK. Once you see that your test now passes, you can delete the region and turn off `dynamicContent`. 
   
 
6. Next, run the same test on the [Applifashion dev site](https://demo.applitools.com/tlcHackathonDev.html). To do this, run your test using `mvn -D test=Hackathon_Activity -D devUrl=applifashiondev test`. Note that the main page doesn't have a shoe in the main area. 
   See that the black shoes filtering has a functional bug! Also notice how the "Appli Air x night" has a visual bug. 
   Place a bug region on each of the areas and accept the differences. Snooze failures on the bug regions for 2 weeks.
   

7. Development has made the changes to the bug region, which are on [Applifashion V2](https://demo.applitools.com/gridHackathonV2.html). 
Run your test suite on Applifashion V2  (` mvn -D test=Hackathon_Solution -D envUrl=applifashionv2 test  
   `) and accept the now-fixed change and delete the bug region. 
   You'll see several unresolved tests; accept them and add or remove regions as needed!
  
 
8. You now want to test your site on multiple browsers and viewport sizes, including mobile viewports. Set the `ulrafast_test_cloud` boolean to true, and find the browser configurations in the setup method. 
Add `iPhone_X` in portrait mode, and FireFox 1200 x 800 as browsers for the Ultrafast test cloud. Run the test to see your test 
   on these configurations!

### Additional Challenges
* Add another check that clicks the "Applifashion" home link in the upper left. What happens on v1 vs the dev environment? 


* Find a selector and apply a [coded region](https://help.applitools.com/hc/en-us/articles/360007188211-Coded-Ignore-Regions) to it. 


* Try testing a different site of your choice. Look for ways to handle dynamic content and get stable tests.  

## Resources
- [Applitools SDK Documentation](https://applitools.com/docs/api/eyes-sdk/index-gen/classindex-selenium-java.html)
- [Applitools Best Practices](https://applitools.com/docs/topics/general-concepts/visual-test-best-practices.html)