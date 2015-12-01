import java.util.ArrayList;
import java.util.List;

public class StartCrawler  extends AbstractCheckinTestCase  {

	public StartCrawler() throws Exception {
		super();
	}

	static String url = "http://www.gsmarena.com/alcatel-phones-5.php";
	
	public static void main(String[] args) throws Exception {
		
		new AbstractCheckinTestCase();
		
		List<String> urlList =new ArrayList<String>();
		urlList.add(url);
		urlList.add("http://www.gsmarena.com/alcatel-phones-f-5-0-p2.php");
		urlList.add("http://www.gsmarena.com/alcatel-phones-f-5-0-p3.php");
		urlList.add("http://www.gsmarena.com/alcatel-phones-f-5-0-p4.php");
		urlList.add("http://www.gsmarena.com/alcatel-phones-f-5-0-p5.php");
		urlList.add("http://www.gsmarena.com/alcatel-phones-f-5-0-p6.php");

		for (String url1 : urlList) {
			
			BrandCrawlerGsm brandCrawler = new BrandCrawlerGsm(url1,sessionFactory,MobileServiceImpl);
		}
		
	}

}
