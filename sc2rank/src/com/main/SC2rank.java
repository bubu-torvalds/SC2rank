package com.main;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;


/**
 * 
 * @author bubuPrime
 * 
 * Test for binding sc2rank api in java.
 *
 */
public class SC2rank {
	
	String api_key;
		
	public static void main(String [] args){		
		SC2rank sc2 = new SC2rank();
		sc2.init("test.com");
		sc2.fetch_api("psearch", new HashMap<String, String>());
	}
	
	
	
	public void init(String api_key){
		System.out.println("init the proxy using the given api_key "+ api_key);	
		this.api_key = api_key;
	}
	
	public void fetch_api(String path, Map params){
		String url = "http://sc2ranks.com/api/clist/1/all/all/1/0.json?appKey=" + api_key;
		System.out.println("fetching "+ url);
		fetch_json(url, params);
	}
	
	public void fetch_json(String url, Map params){
		try {
			System.out.println("YO!");
			URL real_url = new URL(url);
			URLConnection con = real_url.openConnection();
			System.out.println(con.getContentLength());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("shit happens");
		}
		
	}
	
	public SC2rank(){}
}
