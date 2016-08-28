package com.ezone.app.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ezone.app.Constants_Flipkart;
import com.ezone.app.helper.EzoneHelper;
import com.jayway.jsonpath.JsonPath;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

class RunnableCrawler  implements Runnable {
   private Thread t;
   String affiliateId = null;
   String affiliateToken = null;
   String category = null;
   String parentUrl = null;
   Boolean isDeltaFeed = true;
        
        ResourceBundle bundle = null;//ResourceBundle.getBundle("config");
        
        private String affiliateBaseUrl;
   
        private MobilesFlipkartImpl flipkartImpl = null;

        private FlipkartHelper flipkartImplHelper = null;
        
        private FlipkartPersistImpl flipkartPersistImpl = null;
        
        public DB db = null;
        
   RunnableCrawler(String category) throws AffiliateAPIException {
       this.category = category;
       
       System.out.println("Creating " +  category );
      
                 flipkartImplHelper = new FlipkartHelper();
                 
                 flipkartPersistImpl = new FlipkartPersistImpl(category);
                
   }
   
   
   public void run() {
      System.out.println("Running " +  category );
      try {
            Thread.sleep(50);
            
          String parentJsonData =  flipkartPersistImpl.queryService(Constants_Flipkart.AFFILIATE_BASE_URL);
          flipkartPersistImpl.setParentJsonData(parentJsonData);
          flipkartImplHelper.setCategory(category);
          flipkartImplHelper.setParentJsonData(parentJsonData);
          
                Integer dbVersion = flipkartPersistImpl.getDBVersion();
                Integer currentVersion = flipkartPersistImpl.getCurrentVersion();
                flipkartPersistImpl.setCurrentVersion(currentVersion);
                        if(dbVersion!=null && currentVersion !=null && dbVersion.equals(currentVersion)){
                                return;
                        }
                        
                        // TODO Needs to remove
        //              flipkartPersistImpl.setCurrentVersion(1440);
        //              flipkartPersistImpl.saveCurrentVersion();
                        //flipkartPersistImpl.setCurrentVersion(currentVersion);
                        
                        
                        // update db version to current version and start fetching products
                                if (dbVersion == null) {
                                        flipkartPersistImpl.fetchProductListByUrl(null);
                                } else {
                                        String deltaFeedUrl = flipkartImplHelper.constructDeltaFeedUrl();
                                        deltaFeedUrl = String.format(deltaFeedUrl, dbVersion);
                                        flipkartPersistImpl.fetchProductListByUrl(deltaFeedUrl);
                                }
                                flipkartPersistImpl.saveCurrentVersion();
     } catch (InterruptedException e) {
         System.out.println("Thread " +  category + " interrupted.");
     } catch (AffiliateAPIException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
     System.out.println("Thread " +  category + " exiting.");
   }
   
   public void start ()
   {
      System.out.println("Starting " +  category );
      if (t == null)
      {
         t = new Thread (this, category);
         t.start ();
      }
   }
   
        

        public void setIsDeltaFeed(Boolean isDeltaFeed) {
                this.isDeltaFeed = isDeltaFeed;
        }

        
        
}