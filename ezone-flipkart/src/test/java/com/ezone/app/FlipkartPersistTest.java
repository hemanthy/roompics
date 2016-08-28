package com.ezone.app;


import com.ezone.app.dao.AffiliateAPIException;
import com.ezone.app.dao.FlipkartPersistImpl;

import junit.framework.TestCase;

public class FlipkartPersistTest extends TestCase {
        
        FlipkartPersistImpl flipkartPersistImpl = null;
        
        public FlipkartPersistTest() throws AffiliateAPIException {
                flipkartPersistImpl =  new FlipkartPersistImpl("mobiles");
        }
        
        public void testSortVersion(){
                Integer dbVersion = flipkartPersistImpl.getDBVersion();
                System.out.println(dbVersion);
        }
        
        public void testSortCategory(){
                String dbCategory = flipkartPersistImpl.getDBCategory();
                System.out.println(dbCategory);
        }
        
        public void testFragrancesdb(){
                Integer dbCategory = flipkartPersistImpl.getNextSequence("primaryId");
                System.out.println(dbCategory);
        }
        
        public void testSaveCurrentVersion(){
                flipkartPersistImpl.saveCurrentVersion();
        }

}