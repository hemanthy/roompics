package com.ezone.app;

import org.json.JSONObject;

public class Helper {
        public static String jsonPath(String path,String jsonData){
                
                 JSONObject obj = new JSONObject(jsonData);
                 String[] pathArray = path.split("\\.");
                 
                 for(int i=0;i<pathArray.length;i++){
                         if(i==(pathArray.length-1)){
                                 if(obj.get(pathArray[i])!=null){
                                        String value = obj.get(pathArray[i]).toString();
                                         return value;
                                 }
                         }else{
                                 obj = obj.getJSONObject(pathArray[i]);
                         }
                 }
                 
                return null;
        }

}
 