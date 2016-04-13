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
   
   
    @Test(description = "Ejercicio01") 
    public void ejercicio01() { 
 
    	driver.manage().window().maximize();
    	driver.get("http://labrujula24.com"); 
    	
    	
    	driver.findElement(By.className("titulo-nota-destacada-a")).click();
    	driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    	
    	driver.switchTo().frame(driver.findElement(By.className("fb_ltr")));
    	
    	String texto = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div[3]/div[1]/div[2]/div/div[2]/div/div[1]/div/span[1]/span/span")).getText();
    	System.out.println("Texto extraido: " + texto);
    	texto = texto.toLowerCase();
    	
    	Assert.assertTrue(texto.indexOf("noticia") >= 0, "No se encontro Noticia en el comentario");
    	
    } 

} 
