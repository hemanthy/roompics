/*
 * Decompiled with CFR 0_102.
 */
package com.mobilestree.mobile.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mobilestree.mobile.MobileConstants;

public class CommonHelper {
	
	
    public static void extractCatageryName(Map<String, String[]> catagoryUrl, String url, String mobileConstants) {
        if (url.contains((CharSequence)"_")) {
            String substring = url.substring(mobileConstants.length() + 1);
            String[] spec = substring.split("_");
            catagoryUrl.put(mobileConstants, spec);
        } else {
            String substring = url.substring(mobileConstants.length() + 1);
            String[] spec = new String[]{substring};
            catagoryUrl.put(mobileConstants, spec);
        }
    }
    
    public static void downloadImgAndGetImgPath(Document doc, Map<String, String> mp,String title,String imgDiv,String brandName) throws IOException {
//		Element imagElement = doc.select("div.st_main_image"+imgDiv).get(0);
		Element imagElement = doc.select("div."+imgDiv).get(0);
        Elements img =   imagElement.getElementsByTag("img");

         //for each element get the srs url
         String src = img.get(0).absUrl("src");

         System.out.println("Image Found!");
         System.out.println("src attribute is : "+src);
         mp.put("image_path2", src.trim());
         
         String imgPath = title.replaceAll(" ", "-");
         
         String imgPathExtenstion = (imgPath+""+MobileConstants.IMG_EXTENSION).toLowerCase();
         
         mp.put("image_path",imgPathExtenstion);
         
         getImages(src,brandName,imgPathExtenstion);
	}
	
	private static void getImages(String src,String brandName, String imgPath) throws IOException {

        String folder = null;

        //Exctract the name of the image from the src attribute
        
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        
        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname, src.length());
        System.out.println(name);
        
        String dir = MobileConstants.FOLDER_PATH + brandName;
        Boolean isDirExist = isImgExists(dir);
        if(!isDirExist){
        	File f1 = new File(dir);
        	f1.mkdirs();
        }

        Boolean isExists = isImgExists(MobileConstants.FOLDER_PATH + brandName +"/" + imgPath);
        
        if(isExists){
        	return;
        }
        
        //Open a URL Stream
        URL url = new URL(src);
        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream(MobileConstants.FOLDER_PATH + brandName +"/" + imgPath));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
    }


	private static boolean isImgExists(String imgPath) {
		File file = new File(imgPath);
		if(file.exists()){
			return true;
		}else{
			return false;
		}
	}
}