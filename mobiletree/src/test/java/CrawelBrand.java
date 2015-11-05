import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawelBrand {
	
	static Document doc;
	
	static String title;
	
	public CrawelBrand(String url) throws IOException{
		System.out.println("Reading url :"+url);
		doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		
		title = getTitle();
	}
	
	
	
	public Document getPageContentByUrl(String url) throws IOException {
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		return doc;
	}
	
	public String getTitle() {
		String title = doc.select("h1.article-info-name").text();
		return title;
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Entered");
		
		String url = "http://www.gsmarena.com/samsung-phones-9.php";
		
		new CrawelBrand(url);
		
		Element link = doc.select("div.makers").first();
		
		System.out.println(title);
		 Elements links = link.select("a[href]");
		 
		 for (Element element : links) {
			System.out.println(element.attr("abs:href"));
		}
		
	
		
	}
	

}
