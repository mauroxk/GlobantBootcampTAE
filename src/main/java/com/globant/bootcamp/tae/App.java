package com.globant.bootcamp.tae; 


import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext; 
import org.testng.annotations.AfterMethod; 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


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
   
	public String[] credenciales(int n)
	{
		String[] dato = new String[2];
		String usuario = null;
		String clave = null;

		
		try
			{
			File fXmlFile = new File("credenciales.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			usuario = doc.getElementsByTagName("usuario").item(n).getTextContent();
			clave = doc.getElementsByTagName("clave").item(n).getTextContent();
			
			} 
		catch (Exception e) 
			{
			System.out.println("ERROR DE ARCHIVO");
			e.printStackTrace();
			}
		dato[0]=usuario;
		dato[1]=clave;;
		return dato;

	}
    
    public String[] datos(int n)
	{
		String[] dato = new String[3];
		String direccion = null;
		String asunto = null;
		String cuerpo = null;
		
		try
			{
			File fXmlFile = new File("destinatarios.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			direccion = doc.getElementsByTagName("email").item(n).getTextContent();
			asunto = doc.getElementsByTagName("asunto").item(n).getTextContent();
			cuerpo = doc.getElementsByTagName("cuerpo").item(n).getTextContent();

			} 
		catch (Exception e) 
			{
			System.out.println("ERROR DE ARCHIVO");
			e.printStackTrace();
			}
		dato[0]=direccion;
		dato[1]=asunto;
		dato[2]=cuerpo;
		return dato;

	}
	
	@DataProvider(name = "info")
	public Object[][] cargarDatos() {
	 return new Object[][] {
	   { datos(0)[1], datos(0)[2] },
	   { datos(1)[1], datos(1)[2] },
	   { datos(2)[1], datos(2)[2] },
	 };
	}
	 
	@DataProvider(name = "credenciales")
	public Object[][] cargarCredenciales() {
	 return new Object[][] {
	   { credenciales(0)[0], credenciales(0)[1] },
	   { credenciales(1)[0], credenciales(1)[1] },
	 };
	}
	
    @Test(description = "envio", priority = 1, dataProvider = "credenciales") 
    public void envio(String s1, String s2) { 
    	
    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	
    	driver.manage().window().maximize();
    	driver.get("https://mail.google.com/"); 
    
    	driver.findElement(By.id("Email")).sendKeys(s1);
    	driver.findElement(By.id("next")).click();

    	
    	driver.findElement(By.id("Passwd")).sendKeys(s2);
    	driver.findElement(By.id("signIn")).click();
    	
        for(int i=0; i<3; i++)
        {
        	driver.findElement(By.xpath("//div[contains(text(),'COMPOSE')]")).click();
        	driver.findElement(By.xpath("//textarea[@name='to']")).sendKeys(datos(i)[0]);
        	driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys(datos(i)[1]);
        	driver.findElement(By.xpath("//div[@class='Ar Au']//div")).sendKeys(datos(i)[2]);
        	driver.findElement(By.xpath("//div[contains(text(),'Send')]")).click();
        }
    	
    	
    	driver.get("https://mail.google.com/mail/u/0/#sent");
    	
        for(int i=0; i<3; i++)
        {
        	if (driver.getPageSource().contains(datos(i)[1]))
    		{
    			System.out.println(datos(i)[1] + " Enviado");
    		}
    		else
    		{
    			System.out.println(datos(i)[1] + " NO Enviado");
    		}
        }
 
    	driver.findElement(By.xpath("//span[@class='gb_1a gbii']")).click();
    	driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).click();
  
    	driver.close();
	
    }
    
    @Test(description = "check", priority = 2, dataProvider = "credenciales") 
    public void check(String s1, String s2) { 

    	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    	driver.manage().window().maximize();
    	driver.get("https://mail.google.com/"); 
    	    
    	driver.findElement(By.id("Email")).sendKeys(s1);
    	driver.findElement(By.id("next")).click();
    	
    	driver.findElement(By.id("Passwd")).sendKeys(s2);
    	driver.findElement(By.id("signIn")).click();
    	
        for(int i=0; i<3; i++)
        {
        	if (driver.getPageSource().contains(datos(i)[1]) && driver.getPageSource().contains(datos(i)[2]))
    		{
    			System.out.println(datos(i)[1] + " Recibido");
    		}
    		else
    		{
    			System.out.println(datos(i)[1] + " NO Recibido");
    		}
        }
        
    	driver.findElement(By.xpath("//span[@class='gb_1a gbii']")).click();
    	driver.findElement(By.xpath("//a[contains(.,'Sign out')]")).click();
  
    	driver.close();
    
    }

}