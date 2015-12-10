import java.awt.image.BufferedImage;
import java.io.File;
import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; 
public class chromebrowser {
	
	
	public static void main(String args[]) throws Exception
	{
		PresistCrawelData presistCrawelData = new PresistCrawelData();
		
		Document doc = null;
		
		String brandName = null;
		
	       System.setProperty("webdriver.chrome.driver", "G://Hemanth//Hemanth Code//Spring Security//lib//chromedriver_win32//chromedriver.exe");
                WebDriver driver=new ChromeDriver(); 
                
                for (int i = 0; i <= 1000; i++) {
                	 long startTime = System.currentTimeMillis();
                	 if(i%2==0){
                		 System.out.println("odd");
                		 driver.get("http://localhost:8080/mobiles/samsung-galaxy-note-i717");
                	 }else{
                		 System.out.println("even");
                		 driver.get("http://localhost:8080/");
                	 }
                	 
                		long endTime = System.currentTimeMillis();
    					System.out.println(i + ".........." + (endTime - startTime));
                	
                	
                	/*
        			String url =	presistCrawelData.getWebUrlById(i);
        			if(url == null){
        				continue;
        			}
        			String content =  presistCrawelData.readHtmlDataByURLInDB(url);
        			doc = Jsoup.parse(content);
        			
        			Element imagElement = doc.select("div.specs-photo-main").get(0);
        	        Elements img =   imagElement.getElementsByTag("img");

        	         String src = img.get(0).absUrl("src");
        	         System.out.println(src);
        	         driver.get(src);
        	 		
        	 		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        	 		
        	 		WebElement Image = driver.findElement(By.xpath("//img"));
        	 		
        	 		int ImageWidth = Image.getSize().getWidth();
        	 		int ImageHeight = Image.getSize().getHeight();
        	 		Point point = Image.getLocation();
        	 		int xcord = point.getX();
        	 		int ycord = point.getY();
        	 		System.out.println(xcord);
        	 		System.out.println(ycord);
        	 		BufferedImage img1 = ImageIO.read(scrFile);
        	 		//BufferedImage dest = img1.getSubimage((xcord), (ycord+10), ImageWidth, (ImageHeight-10)); 
        	 		BufferedImage dest = img1.getSubimage((xcord), (ycord), ImageWidth, (ImageHeight));
        	 		ImageIO.write(dest, "png", scrFile);
        	 		
        	 		String title = doc.select("h1.specs-phone-name-title").text().toLowerCase();
        	 	//	isImgExists("G:\\selenium\\screenshot4");
        	 		
        	 		if(title.contains("apple")){
        	 			brandName = "sony";
        	 		}else if(title.contains("huawei")){
        	 			brandName = "huawei";
        	 		}else if(title.contains("alcatel")){
        	 			brandName = "alcatel";
        	 		}else if(title.contains("celkon")){
        	 			brandName = "celkon";
        	 		}else if(title.contains("gionee")){
        	 			brandName = "gionee";
        	 		}else if(title.contains("lenovo")){
        	 			brandName = "lenovo";
        	 		}else if(title.contains("micromax")){
        	 			brandName = "micromax";
        	 		}else if(title.contains("motorola")){
        	 			brandName = "motorola";
        	 		}else if(title.contains("samsung")){
        	 			brandName = "samsung";
        	 		}else if(title.contains("sony")){
        	 			brandName = "sony";
        	 		}
        	 		
        	 		if((title.contains("gear") || title.contains("watch") || title.contains("tab") || title.contains("samsung galaxy tab pro 8.4 3g")
        					|| title.contains("/"))){
        	 			continue;
        			}
        	 		
        	 		title = title.replace(" ", "-");
        	 		
        	 		FileUtils.copyFile(scrFile, new File("G:\\selenium\\"+brandName+"\\"+title+".jpg"), true);
                */}
                
                
                
                
		
		// Close Driver
		driver.quit();
	}
	
	private static boolean isImgExists(String imgPath) {
		File file = new File(imgPath);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
}