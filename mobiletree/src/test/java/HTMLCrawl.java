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

public class HTMLCrawl  {
	
	
	
	private static List<String> companyList = new ArrayList<String>();
	
	private static Map<String,Company> companyMap = new HashMap<String, Company>();
	
	private static List<Integer> yearList = new ArrayList<Integer>();
	
	private static List<String> monthList = new ArrayList<String>();
	
	private static Map<String,String> monthMap = new HashMap<String, String>();
	

}


