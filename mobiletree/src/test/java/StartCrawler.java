import java.util.ArrayList;
import java.util.List;

public class StartCrawler  extends AbstractCheckinTestCase  {

	public StartCrawler() throws Exception {
		super();
	}

	static String url = "http://www.gsmarena.com/celkon-phones-75.php";
	
	public static void main(String[] args) throws Exception {
		
		new AbstractCheckinTestCase();
		
		List<String> urlList =new ArrayList<String>();
		

		for (String url1 : urlList) {
			
			BrandCrawlerGsm brandCrawler = new BrandCrawlerGsm(url1,sessionFactory,MobileServiceImpl);
		}
		
	}

}
