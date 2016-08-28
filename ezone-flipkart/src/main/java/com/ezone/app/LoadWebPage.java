package com.ezone.app;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoadWebPage {
        
        public static void main(String[] args) {
                
                System.setProperty("webdriver.chrome.driver", "C:\\Hemanth\\code\\lib\\selenium-2.50.1\\chromedriver.exe");
                
                WebDriver driver = new ChromeDriver();
                driver.get("http://www.flipkart.com/xolo-play-6x-1000/p/itme6g4ff9zghwdg?pid=MOBDXVTY75XAZ99U");
                WebElement elemHtml = driver.findElement(By.className("productSpecs"));
                String attribute = elemHtml.getAttribute("innerHTML");//.getAttribute("innerHTML");
                System.out.println(attribute);
        }
}
 