package com.ezone.app.helper;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;

import com.ezone.app.Constants_Flipkart;
import com.ezone.app.dao.AffiliateAPIException;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EzoneHelper {
        
        public static String jsonPath(String path,String jsonData) throws AffiliateAPIException{
                System.out.println("jsonPath::"+path);
        //      System.out.println("jsonData::"+jsonData);
                
        if(jsonData==null || jsonData.trim().isEmpty()){
                throw new AffiliateAPIException("Json Cannot be Null Or Empty");
        }
                 JSONObject obj = new JSONObject(jsonData);
                 String[] pathArray = path.split("\\.");
                 
                 for(int i=0;i<pathArray.length;i++){
                         if(i==(pathArray.length-1)){
                                 if(obj.has(pathArray[i]) && obj.get(pathArray[i])!=null){
                                        String value = obj.get(pathArray[i]).toString();
                                        if(value==null && value.trim().isEmpty()  || value.equalsIgnoreCase("null")){
                                                return null;
                                        }else{
                                                return value;
                                        }
                                 }
                         }else{
                                 if(obj.has(pathArray[i])){
                                         obj = obj.getJSONObject(pathArray[i]);
                                 }
                         }
                 }
                 
                return null;
        }
        
        
        public String constructDeltaFeedUlr(String url){
                System.out.println(url);
                url = url.replaceAll("https://", "");
                String[] querySplit = url.split("/");
                String dbVersion = "1389";
                String category = querySplit[5].split(".json")[0];
                String query =querySplit[5].split(".json")[1];
                
                //https://affiliate-api.flipkart.net/affiliate/deltaFeeds/allgadget/category/t06-r3o/fromVersion/65.json?expiresAt=1458361001804&sig=5fcea6ddb4933bff018f1b3ae196ee5e
                String deltaUrl = Constants_Flipkart.DELTA_BASE_URL + category +"/fromVersion/"+dbVersion+ ".json"+query;
                System.out.println(deltaUrl);
                return deltaUrl;
        }
        
        public Integer getCurrentVersion(String deltaUrl){
                
                return 0;
        }
        
        public static String categoryFromUrl(String url){
                if(url.toLowerCase().contains("deltafeeds")){
                        url = url.replaceAll("https://", "");
                        String[] querySplit = url.split("/");
                        String category = querySplit[6].split(".json")[0];
                        return category;
                }else if(url.toLowerCase().contains("feeds")){
                        url = url.replaceAll("https://", "");
                        String[] querySplit = url.split("/");
                        String category = querySplit[6].split(".json")[0];
                        return category;
                }
                
                return null;
        }
        

}