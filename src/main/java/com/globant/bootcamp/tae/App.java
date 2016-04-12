package com.globant.bootcamp.tae; 
 
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext; 
import org.testng.annotations.AfterMethod; 
import org.testng.annotations.BeforeMethod; 
import org.testng.annotations.Test; 

import org.testng.Assert;
 
public class App  
{ 
    private WebDriver driver; 
 
    @BeforeMethod(alwaysRun = true) 
    public void beforeMethod(ITestContext context){ 
        try { 
            driver = new FirefoxDriver(); 
        } catch (Exception e) { 
        } 
    	System.out.println("@BeforeMethod");
    } 
 
    @AfterMethod(alwaysRun = true) 
    public void afterMethod(ITestContext context){ 
        try { 
            driver.close(); 
            driver.quit(); 
        } catch (Exception e) { 
        } 
    	System.out.println("@AfterMethod");
    } 
   
   
    @Test(description = "Ejercicio05") 
    public void ejercicio05() { 
    	
    	
    	driver.manage().window().maximize();
    	driver.get("http://amazon.com/"); 
    	WebElement element = driver.findElement(By.name("field-keywords"));
    	element.sendKeys("kindle");
    	driver.findElement(By.xpath("/html/body/div[2]/header/div/div[1]/div[3]/div/form/div[2]/div/input")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[2]/div/div[4]/div[1]/div/ul/li[5]/div/div/div/div[2]/div[2]/a/h2")).click();
    	Assert.assertTrue(driver.getTitle().toLowerCase().indexOf("kindle") >= 0, "no se encontro kindle en titulo");
    	driver.navigate().back();
    	driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]/div[2]/div/div[2]/ul[2]/li/span/a/span[2]")).click();
    	driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[3]/div[2]/div/div[2]/ul[3]/li[26]/a/span[1]")).click();
    	driver.findElement(By.name("field-keywords")).clear();
    	driver.findElement(By.name("field-keywords")).sendKeys("superman");
    	driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[2]/div/input")).click();
    	Assert.assertTrue(driver.findElements(By.xpath("/html/body/div[1]/div[1]/div[3]/div[2]/div/div[4]/div[1]/div/ul/li[4]/div/div/div/div[2]/div[2]/a/h2")).size() != 0, "no se encontraron al menos 4 resultados");    	
    	driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[1]/div/a[1]/span[1]")).click();
    	Assert.assertTrue(driver.getTitle() != "Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", "titulo no correcto");
    } 

} 
