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
   
   
    @Test(description = "Ejercicio03") 
    public void ejercicio03() { 
 
    	driver.manage().window().maximize();
    	driver.get("https://www.google.com/"); 
    	
    	WebElement element = driver.findElement(By.name("q"));
    	element.sendKeys("bahia blanca");
    	element.submit();
    	
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='rso']//h3/a")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElements( By.id("s") ).size() != 0, "barra de busqueda no existe");


    } 

} 
