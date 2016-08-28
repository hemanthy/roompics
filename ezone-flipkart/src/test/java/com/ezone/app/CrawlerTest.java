package com.ezone.app;

import java.sql.SQLException;

import com.ezone.app.dao.FlipkartPersistImpl;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import junit.framework.TestCase;

public class CrawlerTest extends TestCase {
        
        Crawler crawler = null;
        
        public CrawlerTest() throws ClassNotFoundException, SQLException{
                crawler = new Crawler();
        }
        
        public void testGetProductReferIdById() {
                /*DBCursor cursor = crawler.getAllProductInStockItems();
                while(cursor.hasNext()){
                        String json = cursor.next().toString();
                        if(json!=null){
                                System.out.println(json);
                                String pID = Helper.jsonPath(Constants_Flipkart.PRODUCT_ID, json);
                                
                                //      crawler.getProductReferIdById("MOBDN9RGPKPZVVSG");
                                crawler.getProductReferIdById(pID);
                        }
                }*/
                
                String productReferIdByIds = crawler.getProductReferIdById("MOBDK8XHAZFXNFH3");
                if(productReferIdByIds!=null){
                String[] split = productReferIdByIds.split(",");
                for (String string : split) {
                        System.out.println(string.trim());
                }
                }
        }
        

        public void testsaveProductReferenceIDs() throws SQLException {
                crawler.saveProductReferenceIDs();
        }

}