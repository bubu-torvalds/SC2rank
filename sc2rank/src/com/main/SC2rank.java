package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.commons.io.IOUtils;


/**
 * 
 * @author [мм]bubu
 * 
 * This java module aim to interface the sc2rank API which can be find at 
 * http://www.sc2ranks.com/api
 *
 */
public class SC2rank {
	
	String api_key;
		
	public static void main(String [] args){		
		SC2rank sc2 = new SC2rank();
		sc2.init("test.com");
		try {
			//test profile_search
			sc2.fetch_api("psearch/eu/bubutorvalds/1t/division/Scar");
			/*
			 * test base_character
			 * Fun fact : need the battle.net id, but just works fine putting only a $ after the name...
			 */
			sc2.fetch_api("base/char/eu/bubutorvalds$");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void init(String api_key){
		System.out.println("init the proxy using the given api_key "+ api_key);	
		this.api_key = api_key;
	}
	
	public void fetch_api(String request) throws IOException{
		String url = "http://sc2ranks.com/api/"+request+"?appKey=" + api_key;
		System.out.println("fetching "+ url);
		fetch_json(url);
	}
	
	public String fetch_json(String url) throws IOException{
		URL real_url = new URL(url);
		URLConnection con = real_url.openConnection();
		StringWriter writer = new StringWriter();
		IOUtils.copy(con.getInputStream(), writer, Charset.defaultCharset());
		String result = writer.toString();
		System.out.println(result);
		return result;		
	}
	
	/**
	 * Let's you search for profiles to find a characters battle.net id, an exact duplicate of the "Profile Finder" tab. 
	 * Replacement to allow you to search for characters without having the character code, or relying purely on names.
	 *	
	 * @param region Refer to the ladder's server. Valid regions are: us, eu, kr, tw, sea, ru and la
	 * @param name Name of the profile we are looking for.
	 * @param type Can be 1t, 2t, 3t, 4t, achieve. #t refers to team, so 1t is 1v1 team and so on, achieve refers to the characters achievements.
	 * @param sub_type Can be points, wins, losses, division for #t, otherwise it's just points.
	 * @param value Is the value of what to search on based on type + sub_type. 
	 *              If you passed division then it's the division name. 
	 *              If you pass losses it's the total number of losses and so on. 
	 *              Matches are all inexact, if you pass 500 for points it searches for >= 400 and <= 600.
	 *              If you search for division name it does an inexact match.
	 */
	public void search_profile(String region, String name, String type, String sub_type, String value){
		String request = "psearch/"+region+"/"+name+"/"+type+"/"+sub_type+"/"+value;
		try {
			fetch_api(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Minimum amount of character data, just gives achievement points, character code and battle.net id info.
	 * 
	 * @param region Refer to the ladder's server. Valid regions are: us, eu, kr, tw, sea, ru and la
	 * @param name Name of the profile we are looking for.
	 */
	public void fetch_base_character(String region, String name){
		String request = "base/char/"+region+"/"+name;
		try {
			fetch_api(request);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetch_base_character_team_info(String region, String name, int bracket, boolean random){
		
	}
	
	public SC2rank(){}
}
