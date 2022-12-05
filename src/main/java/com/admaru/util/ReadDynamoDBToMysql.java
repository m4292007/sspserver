package com.admaru.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.sql.*;

public class ReadDynamoDBToMysql {

	public static void main(String...args) throws IOException, ParseException {
	
		ReadDynamoDBToMysql x = new ReadDynamoDBToMysql();
//		//x.procTracker();
////		x.procImp("D:\\dynamodb\\eh5iulv7g4ydhde7wm3ljf4hey.json");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.001");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.003");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.004");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.005");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.006");
//
//		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.007");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.008");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.009");
////		x.procImp("D:\\dynamodb\\s57y7q4lc42mzn7rxlnffwt5wi.json.010");
				
		//x.procImp("D:\\dynamodb0704\\vh65uzsbxeym5pnjryopoxuuke.json");
		//x.procImp("D:\\dynamodb0704\\xaa");
		//x.procImp("D:\\dynamodb0704\\xab");
		//x.procImp("D:\\dynamodb0704\\xac");
		//x.procImp("D:\\dynamodb0704\\xad");
//		x.procImp("D:\\dynamodb0704\\xae");
//		x.procImp("D:\\dynamodb0704\\xaf");
//		x.procImp("D:\\dynamodb0704\\xag");
		
//		x.procImp("D:\\dynamodb0704\\xah");
//		x.procImp("D:\\dynamodb0704\\xai");
//		x.procImp("D:\\dynamodb0704\\xaj");
//		x.procImp("D:\\dynamodb0704\\xak");
		
//		x.procImp("D:\\dynamodb0704\\xal");
//		x.procImp("D:\\dynamodb0704\\xam");
		x.procImp("D:\\dynamodb0704\\xan");
		
		System.out.println("end");
		
	}
	public void procImp(String filePath) throws IOException, ParseException 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		File file = new File(filePath);
		
		int x = 1; 
		if(file.exists())
		{
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;

			try 
			{
				conn = ConnectionObject.getConnection();
				pstmt = conn.prepareStatement("insert into ssp_imp values(?, ?, ?,  DATE_ADD(?,interval 9 hour), ?, ?, ?, ?, ?, ?, null)");
			
			 	while( (sLine = inFile.readLine()) != null )
		    	{
			    	
				    JSONParser parser = new JSONParser();
					Object obj = parser.parse( sLine );
					JSONObject jsonObj = (JSONObject) obj;
					
					JSONObject item = (JSONObject) jsonObj.get("Item");
					
					String site_id = (String)((JSONObject) item.get("site_id")).get("S"); //site_id 1004  BiznDonga
					String p_id  = (String)((JSONObject) item.get("p_id")).get("S"); //p_id 1000 nhnace
					String adspace_id =  (String)((JSONObject) item.get("adspace_id")).get("S");
					String log_ts =  (String)((JSONObject) item.get("log_ts")).get("S");
					String log_ts_guid =  (String)((JSONObject) item.get("log_ts_guid")).get("S");
					String ipaddr =  (String)((JSONObject) item.get("ipaddr")).get("S");
					String devicetype =  (String)((JSONObject) item.get("devicetype")).get("S");
					String size =  (String)((JSONObject) item.get("size")).get("S");
					String p1 =  (String)((JSONObject) item.get("p1")).get("S");
					String p2 =  (String)((JSONObject) item.get("p2")).get("S");
				
					int idxx = 1;
					pstmt.setString(idxx++, site_id);
					pstmt.setString(idxx++, p_id);
					pstmt.setString(idxx++, adspace_id);
					pstmt.setString(idxx++, log_ts);
					pstmt.setString(idxx++, ipaddr);
					pstmt.setString(idxx++, devicetype);
					pstmt.setString(idxx++, size);
					pstmt.setString(idxx++, p1);
					pstmt.setString(idxx++, p2);
					pstmt.setString(idxx++, log_ts_guid);
					//pstmt.setString(idxx++, guid);
					
					System.out.println(filePath+":"+x++);
					//System.out.println(x++ + ":"+sLine);
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
	
	public void procTracker() throws IOException, ParseException 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		File file = new File("D:\\nasmedi-imp\\wrmzketbki3sffxq53tw43gb4a.json");
		
		int x = 1; 
		if(file.exists())
		{
		    BufferedReader inFile = new BufferedReader(new FileReader(file));
		    String sLine = null;

			try 
			{
				conn = ConnectionObject.getConnection();
				pstmt = conn.prepareStatement("insert into ssp_tracker values(?, ?, ?, ?, ?)");
			
			 	while( (sLine = inFile.readLine()) != null )
		    	{
			    	
				    JSONParser parser = new JSONParser();
					Object obj = parser.parse( sLine );
					JSONObject jsonObj = (JSONObject) obj;
					
					JSONObject item = (JSONObject) jsonObj.get("Item");
					
					String site_id = (String)((JSONObject) item.get("site_id")).get("S");
					String log_ts =  (String)((JSONObject) item.get("log_ts")).get("S");
					String ts =  (String)((JSONObject) item.get("ts")).get("S");
					String ipaddr =  (String)((JSONObject) item.get("ipaddr")).get("S");
					String etype =  (String)((JSONObject) item.get("etype")).get("S");
				
					int idxx = 1;
					pstmt.setString(idxx++, site_id);
					pstmt.setString(idxx++, log_ts);
					pstmt.setString(idxx++, ipaddr);
					pstmt.setString(idxx++, etype);
					pstmt.setString(idxx++, ts);
					
					System.out.println(x++ + ":"+sLine);
					System.out.println(pstmt);
					
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
	
}