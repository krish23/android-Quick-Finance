package com.example.account;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {

	private JSONParser jsonParser;
	
	private static String uploadURL = "http://3dcovers.tool-monks.us/api/";
    private static String upload_tag = "upload";
    
     // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
    
    public JSONObject uploadData(String account_name,String account_number,String org_name,String balance)
    {
    	// Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", upload_tag));
        params.add(new BasicNameValuePair("acc_name", account_name));
        params.add(new BasicNameValuePair("acc_number",account_number));
        params.add(new BasicNameValuePair("org_name", org_name));
        params.add(new BasicNameValuePair("balance",balance));
        
        // getting JSON Object
       //JSONObject json = null;
        JSONObject json = null;
        try
        { 
        	json = jsonParser.getJSONFromUrl(uploadURL, params);     
        }catch(Exception e){ 
        	
        } 
       
		// Log.e("JSON", json.toString());
        return json;

    }
	
}
