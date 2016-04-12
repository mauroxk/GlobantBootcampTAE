package com.globant.bootcamp.tae; 
 
import java.util.List;

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
   
   
    @Test(description = "Ejercicio04") 
    public void ejercicio04() { 
 
    	driver.manage().window().maximize();
    	driver.get("http://tn.com.ar/"); 
    	
    	driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/article[3]/h2/a")).click();
    	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/section/section[2]/div[2]/a")).click();
    
    	Assert.assertFalse(driver.getPageSource().contains("Dejá tu opinión!"), "Noticia sin comentarios");
    

    } 

} 
