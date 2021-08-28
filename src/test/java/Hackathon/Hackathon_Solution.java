package Hackathon;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Hackathon_Solution {

	public final String ApplifashionV1 = "https://demo.applitools.com/gridHackathonV1.html";
	public final String ApplifashionDev = "https://demo.applitools.com/tlcHackathonDev.html";
	public final String ApplifashionV2 = "https://demo.applitools.com/gridHackathonV2.html";

	// TODO set this flag to true to simulate dynamic content
	public final Boolean dynamicContent = false;
	// TODO set this flag to enable the Ultrafast Test Cloud!
	public final boolean ultrafast_Test_Cloud = true;
	/**
	 * Useful Selectors for navigating in the exercise.
	 */
	public By blackColorFilter = By.id("SPAN__checkmark__107");
	public By filterButton = By.id("filterBtn");
	public By blackShoesImage = By.xpath("/html/body/div[1]/main/div/div/div/div[4]/div[1]/div/figure/a/img");//By.id("IMG__imgfluid__215");

	public void simulateDynamicContent(WebDriver driver) {
		JavascriptExecutor executor =  (JavascriptExecutor) driver;

		String jsInjection1 = "document.querySelectorAll('h3').forEach(function(noFade) { noFade.innerHTML = Math.random().toString(36);})";

		executor.executeScript(jsInjection1);
	}

	@Test
        public void test() {

		// Use Chrome browser
		WebDriver driver = new ChromeDriver();

		EyesRunner runner;
		// Initialize the Runner for your test.
		if (ultrafast_Test_Cloud) {
			runner = new VisualGridRunner(5);
		}
		else {
			runner = new ClassicRunner();
		}

		// Initialize the eyes SDK
		Eyes eyes = new Eyes(runner);

		setUp(eyes);
		
		try {
			System.out.println("ENV param: " + System.getProperty("envUrl"));

			TestDemoApp(driver, eyes);

		} finally {

			tearDown(driver, runner);
		}
	}

	private void setUp(Eyes eyes) {

		// Initialize the eyes configuration.
		Configuration config = new Configuration();
		
		// Add this configuration if your tested page includes fixed elements.
		config.setStitchMode(StitchMode.CSS);

		// You can get your api key from the Applitools dashboard
		config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

		if (ultrafast_Test_Cloud) {
			config.addBrowser(1200, 800, BrowserType.CHROME);
			config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
		}

		// set new batch
		config.setBatch(new BatchInfo("Demo batch"));

		// set the configuration to eyes
		eyes.setConfiguration(config);
	}

	private void TestDemoApp(WebDriver driver, Eyes eyes) {

		eyes.open(driver, "Hackathon", "SOLUTION: Applifashion Filter Workflow Test", new RectangleSize(1200, 800));

		String environmentUrl;
		String env_param = System.getProperty("envUrl").toLowerCase();

		switch (env_param) {
			case "applifashionv1":
				environmentUrl = ApplifashionV1;
				break;
			case "applifashionv2":
				environmentUrl = ApplifashionV2;
				break;
			case "applifashiondev":
				environmentUrl = ApplifashionDev;
				break;
			default:
				environmentUrl = ApplifashionV1;
		}

		// Navigate the browser to the demo app.
		driver.get(environmentUrl);

		if (dynamicContent) simulateDynamicContent(driver);
		eyes.check(Target.window().fully().withName("Main Page"));

		// Filter by black
		driver.findElement(blackColorFilter).click();
		driver.findElement(filterButton).click();

		eyes.check(Target.window().fully().withName("Black Shoes Filter"));

		driver.findElement(blackShoesImage).click();

		eyes.check(Target.window().fully().withName("Air x Night"));

		// End the test.
		eyes.closeAsync();
	}
	
	private void tearDown(WebDriver driver, EyesRunner runner) {
		driver.quit();

		// Wait and collect all test results
		// we pass false to this method to suppress the exception that is thrown if we
		// find visual differences
		TestResultsSummary allTestResults = runner.getAllTestResults(false);

		// Print results
		System.out.println(allTestResults);
	}

}
