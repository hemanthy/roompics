package com.ezone.app.dao;

import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.ezone.app.Constants_Flipkart;

import java.util.List;
import java.net.*;
import java.io.*;

public class Proxy {

    /***
     * queries the URL and gets back the response as string.
     * @param urlString
     * @return
     * @throws AffiliateAPIException, with different error codes explained.
     */
        
    public String queryService(String urlString) throws AffiliateAPIException {
         System.out.println("urlString::"+urlString);
         System.out.println("TOKEN::"+Constants_Flipkart.TOKEN);
         System.out.println("AFFILIATEID::"+Constants_Flipkart.AFFILIATEID);
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        //    con.setRequestProperty("Fk-Affiliate-Token", Constants_Flipkart.TOKEN);
       //     con.setRequestProperty("Fk-Affiliate-Id", Constants_Flipkart.AFFILIATEID);
            con.setRequestProperty("fk-affiliate-token", Constants_Flipkart.TOKEN);
            con.setRequestProperty("fk-affiliate-id", Constants_Flipkart.AFFILIATEID);
            con.setRequestProperty("inStock", "true");
            con.setConnectTimeout(10 * 1000);
            con.setRequestProperty("cache-control", "no-cache");

            int status = con.getResponseCode();
            
            System.out.println("status::"+status);

            switch(status) {

                case HttpURLConnection.HTTP_GONE:
                    // The timestamp is expired.
                    throw new AffiliateAPIException("URL expired");

                case HttpURLConnection.HTTP_UNAUTHORIZED:
                    // The API Token or the Tracking ID is invalid.
                    throw new AffiliateAPIException("API Token or Affiliate Tracking ID invalid.");

                case HttpURLConnection.HTTP_FORBIDDEN:
                    // Tampered URL, i.e., there is a signature mismatch.
                    // The URL contents are modified from the originally returned value.
                    throw new AffiliateAPIException("Tampered URL - The URL contents are modified from the originally returned value");

                case HttpURLConnection.HTTP_OK:

                   BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine = null;
                    StringBuffer response = new StringBuffer();

                    while (in.ready() && (inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    con.disconnect();
                    in.close();
                   // String responseJsonData = response.toString();
                        /*System.out.println("inputStream::"+con.getInputStream());
                    String responseJsonData = IOUtils.toString(con.getInputStream(), "UTF-8");*/
                    System.out.println("String Length:"+response.length());
                    if(response==null || response.toString().trim().isEmpty()){
                        throw new AffiliateAPIException("ResponseJsonData Cannot Be Null or Empty");
                    }
               //     System.out.println(response.toString());
                    return response.toString().replace("v1.1.0", "v_1_1_0");

                default:
                    throw new AffiliateAPIException("Connection error with the Affiliate API service: HTTP/" + status);
            }
        }
        catch(MalformedURLException mfe) {
                mfe.getStackTrace();
        }
        catch(IOException ioe) {
                ioe.getStackTrace();
        }

        return queryService(urlString);
    }
}