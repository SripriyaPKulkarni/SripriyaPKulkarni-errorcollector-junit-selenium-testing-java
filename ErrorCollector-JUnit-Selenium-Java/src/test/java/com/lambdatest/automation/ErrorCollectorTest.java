package com.lambdatest.automation;

import java.net.MalformedURLException;
import java.net.URL;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class ErrorCollectorTest {

	public String username = "sripriyapkulkarni";
	public String accesskey = "uZ6HzeU8B0IRGOHNM62SKflls6ciT35ZOzRoWlhuS1ZEppPE5z";
	public static RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "70.0");
		capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any
															// available one
		capabilities.setCapability("build", "LambdaTestSampleApp");
		capabilities.setCapability("name", "LambdaTestJavaSample");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	 @Rule
	    public final ErrorCollector collector = new ErrorCollector();

	  @Test
	    public void testWithErrorCollector() throws InterruptedException {
		  System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());
		// navigating to the application under test
			driver.get("https://ecommerce-playground.lambdatest.io/");

			// maximize window
			driver.manage().window().maximize();

		Thread.sleep(1000);

			// validating the Page Title
			String ActualString = "Your Stores";
			String ExpectedString = driver.getTitle();
			
			
			  System.out.println("value coming in : " + ExpectedString);
		        System.out.println("value to check  : " + ActualString);
		        collector.checkThat("Check values match", ActualString, equalTo(ExpectedString));
				
				 collector.checkThat(ExpectedString, equalTo(ActualString));
				 
				 collector.checkThat(ExpectedString, "Not matched",equalTo(ActualString));
				 
	        
	        driver.findElement(By.cssSelector("div[id='entry_217822'] input[placeholder='Search For Products']")).click();
			Thread.sleep(1000);
			driver.findElement(By.cssSelector("div[id='entry_217822'] input[placeholder='Search For Products']"))
					.sendKeys("IPod");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
			Thread.sleep(1000);
			String ActualProductPageTitle = "Search - iPod";
			String ExpectedProductPageTitle = driver.getTitle();
			System.out.println(ExpectedProductPageTitle);
			Thread.sleep(3000);
			System.out.println("Test Completed");
	    }
	  
	  
	  

	private Matcher equalTo(String actualString) {
		// TODO Auto-generated method stub
		return null;
	}
}
