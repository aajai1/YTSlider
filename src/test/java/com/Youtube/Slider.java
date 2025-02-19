package com.Youtube;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Slider {
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeSuite
	public void launch() {
		ChromeOptions options = new ChromeOptions();
		 options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.6943.127 Safari/537.36");
		
		
		driver=new ChromeDriver(options);
		 wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get("https://www.youtube.com/watch?v=KUN5Uf9mObQ");
        driver.manage().window().maximize();
	}
	
	@Test
	public void skipAdd() throws InterruptedException {
	
		
		
		
		 WebElement play = driver.findElement(By.id("movie_player"));
		 WebElement pplayMe= wait.until(ExpectedConditions.elementToBeClickable(play));
		 pplayMe.click();
		 	
		
		 try {
				WebElement button = driver.findElement(By.id("skip-button:2"));
				wait.until(ExpectedConditions.elementToBeClickable(button));
				
				if(button.isDisplayed()) {
					button.click();
					System.out.println(driver.findElement(By.xpath("//ytd-menu-renderer[@class='style-scope ytd-watch-metadata']//ytd-download-button-renderer[@class='style-scope ytd-menu-renderer']//div[@class='yt-spec-touch-feedback-shape__fill']")).getLocation());
					System.out.println("Skip clicked");
				}
			} catch (NoSuchElementException e) {
				System.out.println("Skip ad not available");
				
			}
			
		
		
			
			try {
				WebElement noThanks = driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]/yt-button-shape/button/yt-touch-feedback-shape/div"));
				
				if(noThanks.isDisplayed()) {
					noThanks.click();
					WebElement nothanks2 = driver.findElement(By.className("yt-spec-touch-feedback-shape yt-spec-touch-feedback-shape--touch-response"));
					wait.until(ExpectedConditions.elementToBeClickable(nothanks2));
					nothanks2.click();
				}
			} catch (NoSuchElementException e) {
				System.out.println("No thanks butoon not available.");
				
			}
			
		
		WebElement point = driver.findElement(By.className("ytp-scrubber-container"));
		wait.until(ExpectedConditions.elementToBeClickable(point));
		
			
	}
	
	@Test(dependsOnMethods = {"skipAdd"})
	public void slider() throws InterruptedException {
		
		WebElement point = driver.findElement(By.className("ytp-scrubber-container"));
		wait.until(ExpectedConditions.elementToBeClickable(point));
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		//action.dragAndDropBy(point, 1000, 0).perform();
		action.clickAndHold(point).moveByOffset(1000, 0).release().perform();
		Thread.sleep(10000);
		
		
	}
	
	
	@AfterSuite
	public void teardown() throws InterruptedException  {
		//Thread.currentThread().join(5);
	       // driver.quit();
	}
}
