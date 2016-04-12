package com.globant.bootcamp.tae; 
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver; 
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
   
   
    @Test(description = "Ejercicio02") 
    public void ejercicio02() { 
 
    	driver.manage().window().maximize();
    	driver.get("https://hotmail.com"); 
    	
    	WebElement element = driver.findElement(By.xpath("/html/body/div/form/div/div/section/div/div[1]/div/div/div/div[4]/div[2]/div/fieldset/div[2]/div/div[2]/div/div[2]/div/div[2]/div"));
    	element.sendKeys("DIRECCION DE CORREO ACA");
    	driver.findElement(By.xpath("/html/body/div/form/div/div/section/div/div[1]/div/div/div/div[4]/div[2]/div/fieldset/div[3]/div/div/div[2]/div/div")).sendKeys("CLAVE ACA");

    	driver.findElement(By.id("idSIButton9")).click();
    	driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[4]/div/div[1]/div[2]/div[5]/div[2]/div[1]/div/div/div[5]/div[2]/div[2]/div[1]/div/div/div[1]/div[1]/div/div[3]/div[3]")).click();
    
    	element = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[4]/div/div[1]/div[2]/div[5]/div[2]/div[4]/div[2]/div/div[1]/div[4]/div[2]/div[4]/div[2]/div[1]/div[1]/div[1]/div[1]/div/div[2]/div[1]/div[2]/div/span/div/span/span"));  
    
    	Assert.assertTrue(element.getText() == "support.com", "remitente no es support.com");

    } 

} 
