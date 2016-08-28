package com.ezone.app.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public class SaveProductItemsThread implements Runnable {
        
        String jsonData = null;
        
        FlipkartPersistImpl flipkartPersistImpl =null;
        
        String url;

        private Thread t;
        
        public SaveProductItemsThread(String jsonData) {
                this.jsonData = jsonData;
        }
        
        public void start (String url,FlipkartPersistImpl flipkartPersistImpl)
           {
                        this.flipkartPersistImpl = flipkartPersistImpl;
                        flipkartPersistImpl.saveCurrentUrl(url);
              System.out.println("Starting " +  url );
              if (t == null)
              {
                 t = new Thread (this, url);
                 t.start ();
              }
           }


        public void run() {
                JSONObject obj = null;
                try{
                        obj     = new JSONObject(jsonData);
                }catch(Exception e){
                        e.printStackTrace();
                }
                
                if(obj!=null){
                        JSONArray jsonArray = obj.getJSONArray("productInfoList");
                        for(int i=0;i<jsonArray.length();i++){
                                JSONObject json = (JSONObject) jsonArray.get(i);
                                //      personDAO.saveProductItem(json.toString());
                                try {
                                        flipkartPersistImpl.upsertProductJson(json.toString());
                                } catch (AffiliateAPIException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
        
}