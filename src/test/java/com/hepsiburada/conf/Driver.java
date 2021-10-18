package com.hepsiburada.conf;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class Driver {

	protected static WebDriver driver;
	protected static WebDriver wait;
	protected static BrowserTypeEnum browserType=BrowserTypeEnum.chrome;
	
	public Driver(BrowserTypeEnum browser) {
	browserType=browser;
	}
	
	@BeforeClass
	public void getDriver() {
		switch(browserType) {
		case chrome:
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver");
			driver=new ChromeDriver();
			break;
		case firefox:
			System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/firefox/geckodriver");
			driver=new FirefoxDriver();
			break;
			default:
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/chromedriver");
				driver=new ChromeDriver();
				break;
		}
		driver.get("https://www.hepsiburada.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void waitPageLoad(int timeSeconds) {
		driver.manage().timeouts().implicitlyWait(timeSeconds, TimeUnit.SECONDS);	
		}
	
	public void takeScreenShot() throws IOException {
		SimpleDateFormat sdf=new SimpleDateFormat("YYYYMMDD'T'HHMMSSsss");
		TakesScreenshot screenshot=((TakesScreenshot)driver);
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.newFileUtils().copyFile(src,new File("./src/test/resources/screenshots/" + sdf.format(new Date())+".jpg"));
	}
}
