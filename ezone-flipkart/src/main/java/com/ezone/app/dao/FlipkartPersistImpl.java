package com.ezone.app.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.ezone.app.Constants_Flipkart;
import com.ezone.app.helper.EzoneHelper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class FlipkartPersistImpl  extends Proxy {

         String affiliateId =   Constants_Flipkart.AFFILIATEID; //bundle.getString("flipkartaffiliateId");
         String affiliateToken =   Constants_Flipkart.TOKEN;// "f638b2b4acb143769dc15785468c9475";//bundle.getString("flipkartaffiliateToken");
         
         String affiliateBaseUrl = "https://affiliate-api.flipkart.net/affiliate/api/" + affiliateId + ".json";
         
         String parentJsonData = null;
         
         String category = null;
         DBCollection collection;
         
         String version = null;
         
         public DB db = null;
        private Integer currentVersion = 0;
        private String categoryId;
         
        public FlipkartPersistImpl(String category) throws AffiliateAPIException{
                this.category = category;
                initializeMongodb();
         }
        
        public boolean initializeMongodb(){
                MongoClient mongo = new MongoClient("localhost", 27017);
                db = mongo.getDB("testdb");
                DBCollection collection = db.getCollection(category);
                setCollection(collection);
                return db!=null;
        }
        
        public void fetchProductListByUrl(String url) throws AffiliateAPIException {
                if(url == null){
                        url = EzoneHelper.jsonPath("apiGroups.affiliate.apiListings." + category +".availableVariants.v_1_1_0.get",parentJsonData);
                }
                categoryId = EzoneHelper.categoryFromUrl(url);
                
                //url = "https://affiliate-api.flipkart.net/affiliate/1.0/feeds/allgadget/category/tyy-4mr/55abdc4e73a4773ffb9bcda4.json?expiresAt=1470230601676&sig=25db970e7d1b5afc6d6d3a20e16139a4";
        //        url = "https://affiliate-api.flipkart.net/affiliate/1.0/topFeeds/allgadget/category/tyy-4mr.json?expiresAt=1470230601676&sig=1f2f4cd3b2c3ba7dad37ce17a461e7fd";
                fetchAndpersistProductList(url);
                
        }
        
        private void fetchAndpersistProductList(String url) throws AffiliateAPIException {
                url = url.replaceAll("affiliate/deltaFeeds/", "affiliate/1.0/deltaFeeds/");
                System.out.println(url);
                String jsonData = queryService(url);
                
                String nextUrl = EzoneHelper.jsonPath(Constants_Flipkart.NEXT_URL, jsonData);
                
                FileWriter fw;
				try {
					fw = new FileWriter("f:/"+category+".txt");
					 fw.write(nextUrl);
		               fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
               
                
                SaveProductItemsThread saveProductItemsThread = new SaveProductItemsThread(jsonData);
                saveProductItemsThread.start(url,this);
                
                
                System.out.println("nextUrl::"+nextUrl);
                System.out.println(category);
                
                if (nextUrl != null) {
                        fetchAndpersistProductList(nextUrl);
                }
        }
        
        public WriteResult upsertProductJson(String json,Map<String,String>... source) throws AffiliateAPIException {
                
                /*if(source!=null && source.length >= 2 && source[1] != null){
                        String category = source[1];
                        collection = db.getCollection(category);
                }*/
                
        //      Map<String, String> map = source[0];
                
                String pId = EzoneHelper.jsonPath(Constants_Flipkart.PRODUCT_ID, json);
                DBObject existingJsonObj = getProductById(pId);
                
                
                DBObject documents = (DBObject) JSON.parse(json);
                /*if(source!=null && source.length >= 1 && map == null){
                        documents.put("source", map);
                }*/
                
                /*if(currentVersion > 0){
                        documents.put("version", Integer.valueOf(currentVersion));
                }*/
                
                /*if(currentVersion!=null){
                        map.put(Constants_Flipkart.VERSION, currentVersion.toString());
                }*/
                
/*              if(categoryId!=null){
                        documents.put("categoryId", categoryId);
                }*/
                
                if (existingJsonObj != null && existingJsonObj.containsField("otherInfo")) {
                        BasicDBObject object = (BasicDBObject) existingJsonObj.get("otherInfo");
                        documents.put("otherInfo", object);
                } else if (existingJsonObj == null || !existingJsonObj.containsField("_id")) {
                        Integer nextSequence = getNextSequence("primaryId");
                        System.out.println("nextSequence" + nextSequence);
                        if (nextSequence != null) {
                                documents.put("_id", nextSequence);
                        }
                }
                WriteResult update = collection.update(new BasicDBObject(Constants_Flipkart.PRODUCT_ID, pId),
                                new BasicDBObject("$set", documents),true,false);
                return update;
        }
        
        public Integer getNextSequence(String name) {
                DBCollection collection = db.getCollection(category);
                DBObject findAndModify = collection.findAndModify(new BasicDBObject("_id", name),new BasicDBObject("$inc", new BasicDBObject("seq", 1)));
                if(findAndModify!=null){
                        Object object = findAndModify.get("seq");
                        if(object instanceof Double){
                                Integer seq =  ((Double)object).intValue();
                                return (seq + 1);
                        }else if(object instanceof Integer){
                                Integer seq =  ((Integer)object).intValue();
                                return (seq+1);
                        }
                }else{
                        // Initailze seq value
                        BasicDBObject query = new BasicDBObject();
                        query.put(Constants_Flipkart.SEQ, 1);
                        query.put(Constants_Flipkart._ID, Constants_Flipkart.PRIMARY_ID);
                        collection.save(query);
                }
                return 1;
        //      System.out.println(findAndModify.get("seq").toString());
        //      return name;
        }
        
        public DBObject getProductById(String id) {
                System.out.println("product ID ::"+id);         
                        BasicDBObject query = new BasicDBObject();
                        query.put(Constants_Flipkart.PRODUCT_ID, id);
                        DBObject dbObject = collection.findOne(query);
                        return dbObject;
        }
        
        public Integer getDBVersion(){
                String version = null;
                DBObject max = new BasicDBObject();
                max.put("version", -1);
                DBCursor cursor = collection.find().sort(max).limit(1);
                while (cursor.hasNext()) {
                        DBObject next = cursor.next();
                        Object object = next.get(Constants_Flipkart.VERSION);
                        if(object!=null){
                                version = object.toString();
                        }
                }
                if(version!=null && !version.isEmpty()){
                        Double double1 = new Double(version);
                        if(double1>0){
                                System.out.println("db version : "+double1);
                                return double1.intValue();
                        }
                }
                return null;
        }
        
        public Integer getCurrentVersion() throws AffiliateAPIException{
                String deltaGetUrl = EzoneHelper.jsonPath("apiGroups.affiliate.apiListings." + category +".availableVariants.v_1_1_0.deltaGet",parentJsonData);
                String jsonData = queryService(deltaGetUrl);
                String version = EzoneHelper.jsonPath(Constants_Flipkart.VERSION,jsonData);
                if(version!=null){
                        Double double1 = new Double(version);
                        if(double1 > 0){
                                System.out.println("current version :"+double1);
                                return double1.intValue();
                        }
                }
                return null; 
        }
        
        public String getDBCategory(){
                String category = null;
                DBObject max = new BasicDBObject();
                max.put("version", -1);
                DBCursor cursor = collection.find().sort(max).limit(1);
                while (cursor.hasNext()) {
                        DBObject next = cursor.next();
                        category = next.get("categoryId").toString();
                }
                
                return category;
        }


        public void setCollection(DBCollection collection) {
                this.collection = collection;
        }

        public String getParentJsonData() {
                return parentJsonData;
        }

        public void setParentJsonData(String parentJsonData) {
                this.parentJsonData = parentJsonData;
        }

        public String getAffiliateId() {
                return affiliateId;
        }

        public void setAffiliateId(String affiliateId) {
                this.affiliateId = affiliateId;
        }

        public String getAffiliateToken() {
                return affiliateToken;
        }

        public void setAffiliateToken(String affiliateToken) {
                this.affiliateToken = affiliateToken;
        }

        public String getAffiliateBaseUrl() {
                return affiliateBaseUrl;
        }

        public void setAffiliateBaseUrl(String affiliateBaseUrl) {
                this.affiliateBaseUrl = affiliateBaseUrl;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getVersion() {
                return version;
        }

        public void setVersion(String version) {
                this.version = version;
        }

        public DB getDb() {
                return db;
        }

        public void setDb(DB db) {
                this.db = db;
        }

        public String getCategoryId() {
                return categoryId;
        }

        public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
        }

        public DBCollection getCollection() {
                return collection;
        }

        public void setCurrentVersion(Integer currentVersion) {
                this.currentVersion = currentVersion;
        }

        public void saveCurrentVersion() {
                if (currentVersion > 0) {
                        DBCollection collection = db.getCollection(category);
                        BasicDBObject basicDBObject = new BasicDBObject("version", currentVersion);
                //      BasicDBObject basicDBObject = new BasicDBObject("version", 1440);
                        basicDBObject.put("categoryId", categoryId);
                        collection.update(new BasicDBObject("_id", "primaryId"),
                                        new BasicDBObject("$set", basicDBObject), true, false);
                }
        }
        
        public void saveCurrentUrl(String url) {
                        DBCollection collection = db.getCollection(category);
                        BasicDBObject basicDBObject = new BasicDBObject("currenturl", url);
                        collection.update(new BasicDBObject("_id", "primaryId"),
                                        new BasicDBObject("$set", basicDBObject), true, false);
        }
        
        
        
}