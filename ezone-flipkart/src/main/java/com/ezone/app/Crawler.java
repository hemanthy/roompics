package com.ezone.app;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jayway.jsonpath.JsonPath;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Hello world!
 *
 */
public class Crawler extends db {
        
        static Crawler crawler = null;

public Crawler() throws ClassNotFoundException, SQLException{
        initializeMongodb();
        initializeMysqldb();
}
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, IOException
    {
        System.out.println( "Hello World!" );
        crawler = new Crawler();        
        DBCursor cursor = crawler.getAllProductInStockItems();
        
        int count = 0;
        
        while(cursor.hasNext()){
                System.out.println("count::"+count);
                String json = cursor.next().toString();
                if(count<=100){
                String pId = JsonPath.read(json, Constants_Flipkart.DOLLAR + Constants_Flipkart.PRODUCT_ID);
                System.out.println(pId);
                String url = JsonPath.read(json, Constants_Flipkart.DOLLAR + Constants_Flipkart.PRODUCT_URL);
                        crawler.startCrawlerPage(pId, url.replaceAll("&affid=allgadget", ""));
                //      String htmlContentBySelenium = crawler.getHtmlContentBySelenium("&affid=allgadget");
                }
                count ++;
        }
    }
    
    
    public DBCursor getAllProductInStockItems(){
                DBCollection collection = db.getCollection("mobiles");
                        BasicDBObject query = new BasicDBObject();
                        query.put(Constants_Flipkart.IN_STOCK, true);
                        DBCursor cursor  =  collection.find(query);
                        return cursor;
        }
    
        public Integer startCrawlerPage(String pId,String url) throws SQLException, IOException {
                boolean isExist = checkAlreadyCrawlerOrNot(pId);
                if(!isExist){
                        String html = getHtmlContentBySelenium(url,pId);
                                        //crawlWebPageByUrl(Url);
                        if(html!=null){
                                insertFlipkartCrawlerPage(html, pId);
                        }
                }
                return null;
        }
        
        private String crawlWebPageByUrl(String url) throws IOException {
                Document doc = Jsoup.connect(url).userAgent("Chrome").timeout(10000).get();
                String html = doc.toString().replaceAll("productSpecs specSection", "productSpecs_specSection");
                Document doc1 = Jsoup.parse(html);
                String div = doc1.select("div.productSpecs_specSection").toString();
                System.out.println("div::"+div);
                return div;
        }
        
        public String getHtmlContentBySelenium(String url,String pId){
                System.out.println(url);
                System.setProperty("webdriver.chrome.driver", "C:\\Hemanth\\code\\lib\\selenium-2.50.1\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                driver.get(url);
                String html = null;
                try{
                WebElement elemHtml = driver.findElement(By.className("productSpecs"));
                html = elemHtml.getAttribute("innerHTML");//.getAttribute("innerHTML");
                }catch(Exception ex){
                        try {
                                InsertErrorMsg(url, pId, ex);
                        } catch (SQLException e) {
                                e.printStackTrace();
                        }
                }
                driver.close();
                return html;
        }
        private void InsertErrorMsg(String url, String pId, Exception ex)
                        throws SQLException {
                Statement stmt = con.createStatement();
                
                String query = " insert into flipkart_exp (product_id, error,url)"
                        + " values (?, ?,?)";
                  PreparedStatement preparedStmt = con.prepareStatement(query);
                  preparedStmt.setString (1, pId);
                  preparedStmt.setString(2,  ex.getLocalizedMessage());
                  preparedStmt.setString(3,  url);
                  preparedStmt.execute();
                  stmt.close();
        }
        
        private boolean checkAlreadyCrawlerOrNot(String pId) throws SQLException {

                Statement stmt = (Statement) con.createStatement();

                String sql;
                sql = "SELECT id FROM flipkart where product_id ='" + pId + "' or productId_refers like '"+pId+"'";
                ResultSet rs = stmt.executeQuery(sql);
                Integer id = null;
                while (rs.next()) {

                        System.out.println("Reading from local database ..................." + pId);
                        // Retrieve by column name
                        id = rs.getInt("id");

                        System.out.print("Product Id : " + pId);
                }
                rs.close();
                stmt.close();
                
                if (id == null) {
                        sql = "SELECT id FROM flipkart_exp where product_id ='" + pId + "'";
                        Statement stmt1 = (Statement) con.createStatement();
                        ResultSet rs1 = stmt1.executeQuery(sql);
                        while (rs1.next()) {
                                id = rs1.getInt("id");

                                System.out.print("Error Product Id : " + pId);
                        }
                        rs1.close();
                        stmt1.close();
                }
                // STEP 6: Clean-up environment
                
                
        return id!=null;
        
        }
    
    public void insertFlipkartCrawlerPage(String html,String pId) throws SQLException{
        Statement stmt = con.createStatement();
        
        String query = " insert into flipkart (product_id, html)"
                + " values (?, ?)";
                PreparedStatement preparedStmt = con.prepareStatement(query);
              preparedStmt.setString (1, pId);
              preparedStmt.setString(2,  html);
         
              preparedStmt.execute();
         
         stmt.close();
        // conn.close();
    }
    
    public String getProductReferIdById(String id){
         DBCollection collection = db.getCollection("mobiles");
         DBObject json = collection.findOne(new BasicDBObject(Constants_Flipkart.PRODUCT_ID, id));
         if(json==null)
                 return "";
         String sizeVariantsIds = Helper.jsonPath(Constants_Flipkart.SIZE_VARIANTS, json.toString());
         String colorVariantsIds = Helper.jsonPath(Constants_Flipkart.COLOR_VARIANTS, json.toString());
         String color = colorVariantsIds.replaceAll("\\[", "").replaceAll("\\]", "");
        // String size =  sizeVariantsIds.replaceAll("\\[", "").replaceAll("\\]", "");
         StringBuffer sb = new StringBuffer();
         sb.append(color.trim());
         if(color!=null && !color.isEmpty()){
    //           sb.append(", ");
         }
        // sb.append(size.trim());
         System.out.println(sb.toString());
         return sb.toString();
    }
    
    public void saveProductReferenceIDs() throws SQLException{

        Statement stmt = (Statement) con.createStatement();

                String sql;
                sql = "SELECT product_id FROM flipkart;";
                ResultSet rs = stmt.executeQuery(sql);
                String id = null;
                while (rs.next()) {

                        // Retrieve by column name
                        id = rs.getString("product_id");
                        System.out.println("Reading from local database ..................." + id);
                        String productReferIdById = getProductReferIdById(id.toString());
                        if(productReferIdById!=null && !productReferIdById.isEmpty()){
                                String updateQuery = "update flipkart set productId_refers='"+productReferIdById+"' where product_id ='"+id+"';";
                                System.out.println(updateQuery);
                                Statement stmt1 = (Statement) con.createStatement();
                                stmt1.executeUpdate(updateQuery);
                                stmt1.close();
                        }
                
                }
                rs.close();
                stmt.close();
  }
}