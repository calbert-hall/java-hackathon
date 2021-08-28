package Hackathon;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
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


public class Hackathon_Activity {

	public final String ApplifashionV1 = "https://demo.applitools.com/gridHackathonV1.html";
	public final String ApplifashionDev = "https://demo.applitools.com/tlcHackathonDev.html";
	public final String ApplifashionV2 = "https://demo.applitools.com/gridHackathonV2.html";

	// set this flag to true to simulate dynamic content
	public final Boolean dynamicContent = false;
	// set this flag to enable the Ultrafast Test Cloud!
	public final boolean ultrafast_Test_Cloud = false;

	/**
	 * Useful Selectors for navigating in the exercise.
	 */
	public By blackColorFilter = By.id("SPAN__checkmark__107");
	public By filterButton = By.id("filterBtn");
	public By blackShoesImage = By.xpath("/html/body/div[1]/main/div/div/div/div[4]/div[1]/div/figure/a/img");

	@Test
        public void test() {

		// Use Chrome browser
		WebDriver driver = new ChromeDriver();

		EyesRunner runner ;
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
			TestDemoApp(driver, eyes);

		} finally {
			tearDown(driver, runner);
		}
	}

	private void setUp(Eyes eyes) {

		// Initialize the eyes configuration.
		Configuration config = new Configuration();
		
		config.setStitchMode(StitchMode.CSS);

		// You can get your api key from the Applitools dashboard
        //TODO set your API key manually, or set it to the system env variable below
		config.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

		if (ultrafast_Test_Cloud) {
		    //TODO add additional browsers + devices
		}

		// set new batch
		config.setBatch(new BatchInfo("Demo batch"));

		// set the configuration to eyes
		eyes.setConfiguration(config);
	}

	private void TestDemoApp(WebDriver driver, Eyes eyes) {

		eyes.open(driver, "Hackathon", "Applifashion Filter Workflow Test", new RectangleSize(1200, 800));

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
		driver.get(environmentUrl);
		if (dynamicContent) simulateDynamicContent(driver);

		// TODO this is where you'll include your checks and navigate through the exercise!








		// End the test.
		eyes.closeAsync();
	}
	
	private void tearDown(WebDriver driver, EyesRunner runner) {
		driver.quit();

		TestResultsSummary allTestResults = runner.getAllTestResults(false);

		// Print results
		System.out.println(allTestResults);
	}

	//Don't touch; simulates dynamic content.
	public void simulateDynamicContent(WebDriver driver) {
		JavascriptExecutor executor =  (JavascriptExecutor) driver;
		String jsInjection1 = "document.querySelectorAll('h3').forEach(function(noFade) { noFade.innerHTML = Math.random().toString(36);})";
		executor.executeScript(jsInjection1);
	}
}
