import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mobilestree.mobile.model.Company;
import com.mobilestree.mobile.model.Mobile;
import com.mobilestree.mobile.service.MobileService;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import junit.framework.TestCase;

public class PresistCrawelData extends TestCase  {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/testdb1";
	   
	  

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "hemanth";
	   
	   static Connection conn = null;
	   static Statement stmt = null;
	   
	   private SessionFactory sessionFactory;

	   MobileService mobileServiceImpl;

	   Map<String, String> map = null;

	   public Mobile mobile = null;

	   
	   public PresistCrawelData() throws ClassNotFoundException{
		   Class.forName("com.mysql.jdbc.Driver");
	   }
	   
	
	public Map<String, String> setMap(Map<String, String> map) {
		return this.map = map;
	}

	public PresistCrawelData(SessionFactory sessionFactory, MobileService mobileServiceImpl, Mobile mobile) throws ClassNotFoundException {
		this.sessionFactory = sessionFactory;
		this.mobileServiceImpl = mobileServiceImpl;
		this.mobile = mobile;
	}

	public String crawlData(String url) throws SQLException, IOException, ClassNotFoundException {
		String content = readHtmlDataByURLInDB(url);
		if (content == null) {
			content =	readHtmlDataByURLInWeb(url);
		}
		return content;
	}
	
	public String readHtmlDataByURLInWeb(String url) throws IOException, ClassNotFoundException, SQLException {
		
		System.out.println("Reading data from Website ................... "+url);
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		saveWebHTMLData(doc.toString(),url);
		return doc.toString();
	}

	public String readHtmlDataByURLInDB(String URL) throws SQLException {

		conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = (Statement) conn.createStatement();

		String sql;
		sql = "SELECT id, website, url, content FROM crawler where url ='" + URL + "'";
		ResultSet rs = stmt.executeQuery(sql);
		String content = null;
		while (rs.next()) {

			System.out.println("Reading from local database ..................." + URL);
			// Retrieve by column name
			int id = rs.getInt("id");
			String website = rs.getString("website");
			String url = rs.getString("url");
			content = rs.getString("content");

			// Display values
			System.out.print("ID: " + id);
			System.out.print(", website: " + website);
			System.out.print(", URL: " + url);
			// System.out.println(", content: " + content);
		}
		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();

		return content;
	}
	
	public String getWebUrlById(int id) throws SQLException {

		conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = (Statement) conn.createStatement();

		String sql;
		sql = "SELECT id, website, url, content FROM crawler where id ='" + id + "'";
		ResultSet rs = stmt.executeQuery(sql);
		String url = null;
		while (rs.next()) {

			System.out.println("Reading from local database ..................." + id);
			// Retrieve by column name
			int crawlerId = rs.getInt("id");
			String website = rs.getString("website");
			url = rs.getString("url");
			String content = rs.getString("content");

			// Display values
			System.out.print("ID: " + crawlerId);
			System.out.print(", website: " + website);
			System.out.print(", URL: " + url);
			// System.out.println(", content: " + content);
		}
		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();

		return url;
	}
	
	private static void saveWebHTMLData(String htmlContent, String url)  throws ClassNotFoundException, SQLException, IOException {
		
		 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		  stmt = (Statement) conn.createStatement();
		 
		 System.out.println("Connecting to database...");
	    

	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = (Statement) conn.createStatement();
	      
	      String query = " insert into crawler (url, website, content, date_created)"
	    	        + " values (?, ?, ?, ?)";
	      
	      
	      System.out.println("query ::"+query);
	      Calendar calendar = Calendar.getInstance();
	      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	      
	    	      // create the mysql insert preparedstatement
	    	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	    	      preparedStmt.setString (1, url);
	    	      preparedStmt.setString (2, "");
	    	      preparedStmt.setString(3,  htmlContent);
	    	      preparedStmt.setDate(4, startDate);
	    	 
	    	      // execute the preparedstatement
	    	      preparedStmt.execute();
	      
	      stmt.close();
	      conn.close();
	}
	
	public String getMobileTitleById(int id) throws SQLException {

		conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
		stmt = (Statement) conn.createStatement();

		String sql;
		sql = "SELECT title FROM mobile where id ='" + id + "'";
		ResultSet rs = stmt.executeQuery(sql);
		String title = null;
		while (rs.next()) {

			System.out.println("Reading from local database ..................." + id);
			// Retrieve by column name
			title = rs.getString("title");

			// Display values
			System.out.print("title : " + title);
			// System.out.println(", content: " + content);
		}
		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		conn.close();

		return title;
	}
	

	
	public void saveCompany(String brandName) throws ClassNotFoundException, SQLException {

		System.out.println("save company::" + brandName);

		// Read HTML

		if (mobile == null) {
			System.out.println("....." + brandName);
			return;
		} else {

			Session openSession = this.sessionFactory.openSession();
			Transaction transaction = openSession.beginTransaction();

			Set<Mobile> mobilesList = new HashSet<Mobile>();
			System.out.println("getBrandByName::"+brandName);
			Company cmpy = this.mobileServiceImpl.getBrandByName(brandName);
			System.out.println(cmpy);
			if (cmpy != null) {
				System.out.println(cmpy + "is already exists");
			} else if (cmpy == null) {
				cmpy = new Company();
				cmpy.setEnabled(false);
				cmpy.setBrandName(brandName.trim());
			}

			String title = mobile.getTitle();
			Mobile mob = this.mobileServiceImpl.getMobileByName(title.trim());
			if(mob!=null){
				System.out.println(mob + "is already exists");
			}else if (mob == null) {
				if (title != null) {
					String[] titleSplit = title.split(" ");
					int i = 0;
					String modelName = null;
					for (String str : titleSplit) {
						str = str + " ";
						if (i != 0) {
							if (modelName != null) {
								modelName += str;
							} else {
								modelName = str;
							}
						}
						i++;
					}
					if (modelName != null) {
						mobile.setModel(modelName.trim());
					}
					mobile.setBrandName(brandName.trim());
				}
				mobile.setCompany(cmpy);
				mobilesList.add(mobile);

				cmpy.setMobile(mobilesList);

				openSession.saveOrUpdate(cmpy);
				transaction.commit();
				openSession.close();
			}

		}
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public MobileService getMobileServiceImpl() {
		return mobileServiceImpl;
	}


	public void setMobileServiceImpl(MobileService mobileServiceImpl) {
		this.mobileServiceImpl = mobileServiceImpl;
	}


	public Mobile getMobile() {
		return mobile;
	}


	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}
	
	
}