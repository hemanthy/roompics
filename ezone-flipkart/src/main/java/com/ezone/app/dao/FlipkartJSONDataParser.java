package com.ezone.app.dao;
/***
 * The class to parse JSON data.
 * Please refer to the instructions.txt
 *
 * @author vijay.v@flipkart.com
 * @version 1.0
 * Copyright (c) Flipkart India Pvt. Ltd.
 */

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

public class FlipkartJSONDataParser extends DataParser {
        
        String affiliateId = null;
        String affiliateToken = null;
        
        ResourceBundle bundle = null;//ResourceBundle.getBundle("config");
        
        
    private String affiliateBaseUrl;
    private Map<String, String> apiNameAndUrlMap;
    
    private List<String> categoryList = new ArrayList<String>();
    
    private List<String> apiList = null;
        private FlipkartPersistImpl flipkarPersisttImpl = null;
    

    FlipkartJSONDataParser() throws AffiliateAPIException {
        
        this.affiliateId = "allgadget"; //bundle.getString("flipkartaffiliateId");
                
                this.affiliateToken = "f638b2b4acb143769dc15785468c9475";//bundle.getString("flipkartaffiliateToken");
        
                this.affiliateBaseUrl = "https://affiliate-api.flipkart.net/affiliate/api/" + affiliateId + ".json";
                
                this.apiNameAndUrlMap = new HashMap<String, String>();
                
                flipkarPersisttImpl = new FlipkartPersistImpl("");
                
                categoryList.add("mobile_accessories");
                categoryList.add("mobiles");
                
    }

    /***
     *  It gets the API Directory information from the API service and stores it locally.
     * @return true if initialization is successful.
     * @throws AffiliateAPIException
     */
        public boolean initializeProductDirectory() throws AffiliateAPIException {
                boolean return_value = true;
                // Query the API service and get back the result.
                String parentJsonData = queryService(affiliateBaseUrl);
                
                List<String> apiNameList = JsonPath.read(parentJsonData,"$.apiGroups.affiliate.apiListings.[*].apiName");
                System.out.println(apiNameList);
                
                /*for (String apiName : apiNameList) {
                        String apiUrl = JsonPath.read(parentJsonData,"$.apiGroups.affiliate.apiListings."+apiName+".availableVariants.v_0_1_0.get");
                        apiNameAndUrlMap.put(apiName, apiUrl);
                        System.out.print(apiName);
                        System.out.println(apiUrl);
                }*/
                
                System.out.println(parentJsonData);
                for (String category : categoryList) {
                        String apiUrl = EzoneHelper.jsonPath("apiGroups.affiliate.apiListings."
                                        + category +".availableVariants.v_0_1_0.get",parentJsonData);
                        apiNameAndUrlMap.put(category, apiUrl);
                        System.out.println(apiUrl);
                }
                
                setApiList(apiNameList);

                return return_value;
        }
        
        public void getAllProductsListByUrl() throws AffiliateAPIException {
                
                for (String category : categoryList) {
                        String url = apiNameAndUrlMap.get(category);
                        getProductListByUrl(url,category);
                }

        }

        private void getProductListByUrl(String url, String category) throws AffiliateAPIException {
                String parentJsonData = queryService(url);
                
                
                
                String nextUrl = EzoneHelper.jsonPath(Constants_Flipkart.NEXT_URL, parentJsonData);
                JSONObject obj = null;
                try{
                        obj     = new JSONObject(parentJsonData);
                }catch(Exception e){
                        e.printStackTrace();
                }
                
                if(obj!=null){
                        JSONArray jsonArray = obj.getJSONArray("productInfoList");
                        for(int i=0;i<jsonArray.length();i++){
                                JSONObject json = (JSONObject) jsonArray.get(i);
                                //      personDAO.saveProductItem(json.toString());
                        //      flipkarPersisttImpl.upsertProductJson(json.toString(),null,category);
                        }
                }
                System.out.println("nextUrl::"+nextUrl);
                System.out.println(category);
                
                if (nextUrl != null) {
                        getProductListByUrl(nextUrl,category);
                }
                
                System.out.println("End of getProductById Method");
        }

    /***
     *
     * @return the locally stored product directory information (A list of categories and the corresponding URLs).
     * Originally updated using initializeProductDirectory() and it should be updated again if the URLs are expired.
     */

    public String getAffiliateId() {
        return affiliateId;
    }
    public Map<String, String> getApiNameAndUrlMap() {
                return apiNameAndUrlMap;
        }

        public void setApiNameAndUrlMap(Map<String, String> apiNameAndUrlMap) {
                this.apiNameAndUrlMap = apiNameAndUrlMap;
        }

        public String getAffiliateToken() {
        return affiliateToken;
    }

        public List<String> getApiList() {
                return apiList;
        }

        public void setApiList(List<String> apiList) {
                this.apiList = apiList;
        }

        @Override
        Map<String, String> getProductDirectory() {
                return null;
        }
    
}