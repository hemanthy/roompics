import java.io.IOException;
import java.sql.SQLException;

import com.mobilestree.mobile.MobileConstants;

public class DataDump extends AbstractCheckinTestCase {

	public DataDump() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception {
		new AbstractCheckinTestCase();
		
		MobileConstants.isRemote = false;
		
		PresistCrawelData presistCrawelData = new PresistCrawelData();
		presistCrawelData.setMobileServiceImpl(MobileServiceImpl);
		presistCrawelData.setSessionFactory(sessionFactory);
		
		
		
		//presistCrawelData.setMobile(mobile);
		for (int i = 0; i <= 3; i++) {
			String url =	presistCrawelData.getWebUrlById(i);
			if(url == null){
				continue;
			}
			String str1 = url.split("www.gsmarena.com/")[1];
			String brandName = str1.split("_")[0];
			MobileCrawlerGsm mobileCrawlerGsm = new MobileCrawlerGsm(url,brandName);
			presistCrawelData.setMobile(mobileCrawlerGsm.mobile);
			
			presistCrawelData.saveCompany(brandName);
		}
	}

}
