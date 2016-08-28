package com.ezone.app.dao;

public class StartCrawler {

        public static void main(String[] args) throws AffiliateAPIException {
                
                FlipkartJSONDataParser flipkartJSONDataParser = new FlipkartJSONDataParser();
                if(flipkartJSONDataParser.initializeProductDirectory()){
                        flipkartJSONDataParser.getAllProductsListByUrl();
                }
        }
}
 