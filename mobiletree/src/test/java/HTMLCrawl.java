import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.service.MobileService;

public class HTMLCrawl  implements Runnable{
	
	
	
	private static List<String> companyList = new ArrayList<String>();
	
	private static Map<String,Company> companyMap = new HashMap<String, Company>();
	
	private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	
	/*public static void main(String[] args) throws IOException {
		for(int i=1;i<1000;i++){
			System.out.println(i);
			Document document = Jsoup.connect("http://allgadgetsroundup.com/").userAgent("Chrome").timeout(10000).get();
			System.out.println(document.toString());
		}
	}*/
	
	
	 public static void main(String a[]){
         System.out.println("Starting Main Thread...");
         HTMLCrawl mrt = new HTMLCrawl();
         for(int i=1;i<100;i++){
         Thread t = new Thread(mrt);
         t.start();
       //  while(HTMLCrawl.myCount <= 10){
                 System.out.println("Main Thread: "+(++HTMLCrawl.myCount));
                 //Thread.sleep(100);
   //      }
         }
         System.out.println("End of Main Thread...");
     }
	
	 public static int myCount = 0;
	    public HTMLCrawl(){
	         
	    }
	    
	    public void run() {
	     //   while(HTMLCrawl.myCount <= 10){
				Document document = null;
				try {
					document = Jsoup.connect("http://localhost:8080.com/").userAgent("Chrome").timeout(10000).get();
					System.out.println(document.toString()+"............"+HTMLCrawl.myCount);
				} catch (IOException e) {
					e.printStackTrace();
				}
	  //      }
	    }
	    
}


