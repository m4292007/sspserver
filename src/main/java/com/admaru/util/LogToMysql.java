package com.admaru.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.sql.*;

public class LogToMysql {

	public static void main(String...args) throws IOException, ParseException {
	
		LogToMysql x = new LogToMysql();
		x.insert("D:/prebid/2022.06.13/tomcat1/request-2022-06-13.log");
		x.insert("D:/prebid/2022.06.13/tomcat2/request-2022-06-13.log");
		x.insert("D:/prebid/2022.06.13/tomcat3/request-2022-06-13.log");
				
		///////////////////////////////////////////////////////////////////////
		//x.insert("D:/prebid/2022.06.13/tomcat4/request-2022-06-13.log");
		//x.insert("D:/prebid/2022.06.13/tomcat5/request-2022-06-13.log");
		System.out.println("end");
	}
	public void insert(String logFilePath) throws IOException, ParseException 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		File file = new File(logFilePath);
		
		if(file.exists())
		{
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;

			try 
			{
				conn = ConnectionObject.getConnection();
				pstmt = conn.prepareStatement("insert into ssp_bid values(?,?,?,?,?,?,?,?,?,?,?) ");
				int linenum = 1;
				
			 	while( (sLine = inFile.readLine()) != null )
		    	{
		    		System.out.println(linenum++);
		    		if ( sLine.contains("v1012") || sLine.contains("v1013")) {
		    			continue;
		    		}
/*
[2022-06-13 00:00:00,161]#prod receive:2022:1792c202-7bbd-4f27-b2e6-6ce8e1f1e167:/30349040/JA_MO_article/mid/300x250:no_ad
[2022-06-13 00:00:00,376]#prod send:2022:47ebdc58-28ac-47d7-b9c8-419d783ba670:/30349040/JA_MO_article/mid/300x250:https://adx-exchange.toast.com/rtb/admaru:{"id": "47ebdc58-28ac-47d7-b9c8-419d783ba670",  "at": 1,  "cur": [ "USD" ], "test":0,             "imp": [ {"id": "1", "banner": { "pos": 0, "h": 250, "w": 300 }, "tagid":"div-gpt-ad-1628056912910-0", "bidfloor": 0.0,"bidfloorcur": "USD", "secure" : 1} ],            "site": {"id": "2022", "name": "joongang.co.kr","domain": "joongang.co.kr", "cat": [ "IAB3-1" ],  "page":"https://www.joongang.co.kr/", "ref":"https://www.joongang.co.kr", "mobile":1, "publisher":{"id":"2022"} },            "device": { "ip": "17.121.112.187","ua":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Safari/605.1.15 (Applebot/0.1; +http://www.apple.com/go/applebot)"},             "user": { "buyerid": ""}             , "source": { "ext": { "schain" : { "ver":"1.0", "complete": 1,"nodes": [ { "asi":"admaru.com", "sid":"938264", "rid":"47ebdc58-28ac-47d7-b9c8-419d783ba670", "hp":1 } ]   } } } }	
[2022-06-13 00:00:01,674]#prod receive:1001:dbbbc6b0-f9fc-4a3e-b04f-14c56079f979:/106061858/WomanDonga_MB_NEWS_300x250_Display:ad_ok:gross price=0.625:net price=0.5625
*/		    	
		    		String ts = sLine.substring(1,20);
		    		String[] y = sLine.substring(26).split(":");
		    		String site_id 		= y[1];//System.out.println(y[1]);//2022
					String id      		= y[2];//System.out.println(y[2]);//1792c202-7bbd-4f27-b2e6-6ce8e1f1e167
					String adspace_id 	= y[3]; //System.out.println(y[3]);///30349040/JA_MO_article/mid/300x250 
					String div = y[4];  //		System.out.println(y[4]);//no_ad, ad_ok, https
					String bid_json = "";
					String devicetype = "";
					String ip = "";
					String size = "";					     		
					String p1 = "0";//System.out.println(y[5]);//gross price=0.625
					String p2 = "0";
					
		    		if ("no_ad".equals( y[4] )) {
		    		
		    		} else if ("ad_ok".equals( y[4] )) {
		    			p1 = y[5].replace("gross price=", "");//gross price=0.625
		    			p2 = y[6].replace("net price=", "");//gross price=0.625
		    		} else if ("https".equals( y[4] )) {
						try {
			    			bid_json = sLine.substring(sLine.indexOf('{'));//System.out.println(x.substring(x.indexOf('{')));
			    			
			    			JSONParser parser = new JSONParser();
							Object obj = parser.parse( bid_json );
							JSONObject jsonObj = (JSONObject) obj;
							String ua = (String)((JSONObject) jsonObj.get("device")).get("ua");
							
							boolean mobile1 = ua.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
							boolean mobile2 = ua.matches(".*(LG|SAMSUNG|Samsung).*"); 
	
							if (mobile1 || mobile2) {
								devicetype ="1";
							} else {
								devicetype ="2";
							}
					    			
			    			ip = (String)((JSONObject) jsonObj.get("device")).get("ip");
			    			size = "";
						} catch(Exception e) {
						//"ua":"Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148;ref:nate_app;ndruk:Optional("2018101922090157BDE9");appver:5.5.1;GadID:82D4E80F-0B39-4184-895B-9E329D333A2B;"
							System.err.print(bid_json);
						}		    			
		    		}
		    		
					int idxx = 1;
					pstmt.setString(idxx++, ts);					
					pstmt.setString(idxx++, site_id);
					pstmt.setString(idxx++, id);
					pstmt.setString(idxx++, adspace_id);
					pstmt.setString(idxx++, bid_json);
					pstmt.setString(idxx++, devicetype);
					pstmt.setString(idxx++, ip);
					pstmt.setString(idxx++, size);
					pstmt.setString(idxx++, div);
					pstmt.setString(idxx++, p1);
					pstmt.setString(idxx++, p2);
					//System.out.println(pstmt);
					
					try {
						pstmt.executeUpdate();
					} catch(Exception ex) {
						System.err.println(ex.getMessage());
					}
		    	}
		    	pstmt.close();
			} catch (Exception e) {
				System.out.println(pstmt);
				System.err.println(e.getMessage());
				e.printStackTrace();
			} finally {
				try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
			}		
		}
	}
//	public void procImp() throws IOException, ParseException 
//	{
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		File file = new File("D:\\nasmedi-imp\\nasmedi-imp.json");
//		
//		int x = 1; 
//		if(file.exists())
//		{
//		    BufferedReader inFile = new BufferedReader(new FileReader(file));
//		    String sLine = null;
//
//			try 
//			{
//				conn = ConnectionObject.getConnection();
//				pstmt = conn.prepareStatement("insert into ssp_imp values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
//			
//			 	while( (sLine = inFile.readLine()) != null )
//		    	{
//			    	
//				    JSONParser parser = new JSONParser();
//					Object obj = parser.parse( sLine );
//					JSONObject jsonObj = (JSONObject) obj;
//					
//					JSONObject item = (JSONObject) jsonObj.get("Item");
//					
//					String site_id = (String)((JSONObject) item.get("site_id")).get("S");
//					String p_id  = (String)((JSONObject) item.get("p_id")).get("S");
//					String adspace_id =  (String)((JSONObject) item.get("adspace_id")).get("S");
//					String log_ts =  (String)((JSONObject) item.get("log_ts")).get("S");
//					String log_ts_guid =  (String)((JSONObject) item.get("log_ts_guid")).get("S");
//					String ipaddr =  (String)((JSONObject) item.get("ipaddr")).get("S");
//					String devicetype =  (String)((JSONObject) item.get("devicetype")).get("S");
//					String size =  (String)((JSONObject) item.get("size")).get("S");
//					String p1 =  (String)((JSONObject) item.get("p1")).get("S");
//					String p2 =  (String)((JSONObject) item.get("p2")).get("S");
//				
//					int idxx = 1;
//					pstmt.setString(idxx++, site_id);
//					pstmt.setString(idxx++, p_id);
//					pstmt.setString(idxx++, adspace_id);
//					pstmt.setString(idxx++, log_ts);
//					pstmt.setString(idxx++, ipaddr);
//					pstmt.setString(idxx++, devicetype);
//					pstmt.setString(idxx++, size);
//					pstmt.setString(idxx++, p1);
//					pstmt.setString(idxx++, p2);
//					pstmt.setString(idxx++, log_ts_guid);
//					
//					System.out.println(x++ + ":"+sLine);
//					System.out.println(pstmt);
//					
//					try {
//						pstmt.executeUpdate();
//					} catch(Exception ex) {
//						System.err.println(ex.getMessage());
//					}
//		    	}
//		    	pstmt.close();
//			} catch (Exception e) {
//				System.out.println(pstmt);
//				System.err.println(e.getMessage());
//				e.printStackTrace();
//			} finally {
//				try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
//				try {conn.close();} catch (SQLException e) {e.printStackTrace();}
//			}		
//		}
//	}
}