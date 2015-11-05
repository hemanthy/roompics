import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import junit.framework.TestCase;

public class HtmldbTest extends TestCase  {
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/testdb1";
	   
	  

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "hemanth";
	   
	   static Connection conn = null;
	   static Statement stmt = null;
	   
	   public void testdb(){
		   
	   }
	   
	
public	HtmldbTest() throws ClassNotFoundException, SQLException {
		
		 Class.forName("com.mysql.jdbc.Driver");
		 
	}

	public String crawlData(String url) throws SQLException, IOException, ClassNotFoundException {
		String content = readHtmlDataByURLInDB(url);
		if (content == null) {
			content =	readHtmlDataByURLInWeb(url);
		}
		return content;
	}
	
	private String readHtmlDataByURLInWeb(String url) throws IOException, ClassNotFoundException, SQLException {
		
		System.out.println("Reading data from Website ................... "+url);
		Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
		saveWebHTMLData(doc.toString(),url);
		return doc.toString();
	}

	public String readHtmlDataByURLInDB(String URL) throws SQLException{
		
		 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		  stmt = (Statement) conn.createStatement();
		
		  String sql;
	      sql = "SELECT id, website, url, content FROM crawler where url ='" +URL+"'";
	      ResultSet rs = stmt.executeQuery(sql);
	      String content = null;
	      while(rs.next()){
	    	  
	    	  System.out.println("Reading from local database ..................."+URL);
	          //Retrieve by column name
	          int id  = rs.getInt("id");
	          String website = rs.getString("website");
	          String url = rs.getString("url");
	          content = rs.getString("content");

	          //Display values
	          System.out.print("ID: " + id);
	          System.out.print(", website: " + website);
	          System.out.print(", URL: " + url);
	     //     System.out.println(", content: " + content);
	       }
	       //STEP 6: Clean-up environment
	       rs.close();
	       stmt.close();
	       conn.close();
		
		return content;
	}
	
	public static void saveWebHTMLData(String htmlContent, String url)  throws ClassNotFoundException, SQLException, IOException {
		
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
	      
	      
	  //    Document doc = Jsoup.connect("http://www.gsmarena.com/samsung_galaxy_note5_(cdma)-7504.php").userAgent("Mozilla").timeout(10*1000).get();
	      
	     
	      
	    	 
	    	      // create the mysql insert preparedstatement
	    	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	    	      preparedStmt.setString (1, url);
	    	      preparedStmt.setString (2, "www.gsmarena.com");
	    	      preparedStmt.setString(3,  htmlContent);
	    	      preparedStmt.setDate(4, startDate);
	    	 
	    	      // execute the preparedstatement
	    	      preparedStmt.execute();
	      
	      stmt.close();
	      conn.close();
	}
}