import java.io.IOException;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.service.MobileService;

public class BrandCrawler {
	
	static Document doc;
	
	static String brandTitle;
	
	static String url;
	
	static SessionFactory sessionFactory = null;
	
	static MobileService mobileServiceImpl = null;
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void setSessionFactory(SessionFactory sessFactory){
		sessionFactory = sessFactory;
	}
	
	public BrandCrawler(String url,SessionFactory sessionFactory,MobileService mobileServiceImpl) throws IOException, ClassNotFoundException, SQLException {
		this.url = url;
		this.sessionFactory = sessionFactory;
		this.mobileServiceImpl = mobileServiceImpl;
		
		System.out.println("Reading url :" + url);
		
		doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		
		brandTitle = getTitle();
		
		extractMobileUrls();
		
		
	}
	
	
	public String getTitle() {
		String title = doc.select("h1.article-info-name").text();
		if(title!=null && title.contains("phones")){
			title = title.split("phones")[0];
		}
		return title;
	}
	
	public void getandSaveMobileInfo(Element element) throws IOException, ClassNotFoundException, SQLException{


		System.out.println(element.attr("abs:href"));

		String mobileUrl = element.attr("abs:href");
		
		HtmldbTest htmldbTest = new HtmldbTest();
		
		String content = htmldbTest.readHtmlDataByURLInDB(mobileUrl);
		
		if (content == null) {
			htmldbTest.readHtmlDataByURLInWeb(mobileUrl);
			MobileCrawler mobileCrawler = new MobileCrawler(mobileUrl);
			HTMLCrawl htmlCrawl = new HTMLCrawl(mobileCrawler.map, sessionFactory,mobileServiceImpl);
			htmlCrawl.saveCompany(brandTitle);
		}
		
		
		
		
	
		
	}
	
	public void extractMobileUrls() throws IOException, ClassNotFoundException, SQLException {
		
		System.out.println("Entered");
		
		Element link = doc.select("div.makers").first();
		
		System.out.println(brandTitle);
		 Elements links = link.select("a[href]");
		 
		for (Element element : links) {
			getandSaveMobileInfo(element);
		}
	
		
	}
	

}
