package com.ezone.app.dao;

import java.util.Map;

import com.ezone.app.Constants_Flipkart;
import com.ezone.app.helper.EzoneHelper;

public class FlipkartHelper {
        
        
        private String category;
        private String parentJsonData;

        public String constructDeltaFeedUrl() throws AffiliateAPIException{
                String parentCategoryUrl = EzoneHelper.jsonPath("apiGroups.affiliate.apiListings." + category +".availableVariants.v_1_1_0.get",parentJsonData);
                System.out.println("delta Url :"+parentCategoryUrl);
                parentCategoryUrl = parentCategoryUrl.replaceAll("https://", "");
                String[] querySplit = parentCategoryUrl.split("/");
                String category = querySplit[6].split(".json")[0];
                String query =querySplit[6].split(".json")[1];
                
                //https://affiliate-api.flipkart.net/affiliate/deltaFeeds/allgadget/category/t06-r3o/fromVersion/65.json?expiresAt=1458361001804&sig=5fcea6ddb4933bff018f1b3ae196ee5e
                String deltaUrl = Constants_Flipkart.DELTA_BASE_URL + category +"/fromVersion/%s.json"+query;
                //System.out.println(deltaUrl);
                return deltaUrl;
        }
        
        public Integer getCurrentVersion(String deltaUrl){
                
                return 0;
        }
        
        public static String categoryFromUrl(String url){
                url = url.replaceAll("https://", "");
                String[] querySplit = url.split("/");
                String category = querySplit[5].split(".json")[0];
                return category;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getParentJsonData() {
                return parentJsonData;
        }

        public void setParentJsonData(String parentJsonData) {
                this.parentJsonData = parentJsonData;
        }
        
        /*public static void main(String[] args) {
        FlipkartImplHelper flipkartImplHelper = new FlipkartImplHelper();
        String url = "https://affiliate-api.flipkart.net/affiliate/feeds/allgadget/category/tyy-4mr.json?expiresAt=1458365577181&sig=57975cfa4b84797bdd2747a05ce3665e";
        flipkartImplHelper.constructDeltaFeedUlr(url);
        }*/
        
}
 