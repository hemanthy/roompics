package com.ezone.app.dao;

public class TestThread {
   public static void main(String args[]) throws AffiliateAPIException {
           

           RunnableCrawler R1 = new RunnableCrawler("mobiles");
           R1.start();
           
           RunnableCrawler R2 = new RunnableCrawler("mobile_accessories");
           R2.start();
           
             RunnableCrawler R3 = new RunnableCrawler("fragrances");
           R3.start();
           
           RunnableCrawler R4 = new RunnableCrawler("computer_storage");
           R4.start();
           
           RunnableCrawler R5 = new RunnableCrawler("tablets");
           R5.start();
           
          
   }   
}