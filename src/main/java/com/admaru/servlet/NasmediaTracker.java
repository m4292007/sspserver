package com.admaru.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admaru.util.ConnectionObject;
import com.admaru.util.SlackBot;
import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

public class NasmediaTracker extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public NasmediaTracker() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String p_id = request.getParameter("p_id");
		String site_id = request.getParameter("site_id");
		String size = request.getParameter("size");
		String devicetype = request.getParameter("devicetype");
		String adspace_id= request.getParameter("adspace_id");
		String ipaddr	 = getRemoteAddr(request);
		
		String id     = request.getParameter("id");
		String p1     = request.getParameter("p1");// gross( dsp에서 준 값)
		String p2     = request.getParameter("p2");// net  ( gross * 0.9 한 값)
		
		String adtype= request.getParameter("adtype");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = ConnectionObject.getConnection();
			pstmt = conn.prepareStatement("insert into ssp_nas_imp values(?, ?, ?,  now(), ?, ?, ?, ?, ?, concat(now(),?), ?, ?)");
		   
			int idxx = 1;
			pstmt.setString(idxx++, site_id);
			pstmt.setString(idxx++, p_id);
			pstmt.setString(idxx++, adspace_id);			
			pstmt.setString(idxx++, ipaddr);
			pstmt.setString(idxx++, devicetype);
			pstmt.setString(idxx++, size);
			pstmt.setString(idxx++, p1);
			pstmt.setString(idxx++, p2);
			pstmt.setString(idxx++, id);//2022-06-12T11:22:31.51200:3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			pstmt.setString(idxx++, id);//3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			pstmt.setString(idxx++, adtype);
			
			pstmt.executeUpdate();
	    	pstmt.close();
		} catch (Exception e) {
		
			System.out.println(pstmt);
			System.err.println(e.getMessage());
			
			SlackBot bot = new SlackBot();
			bot.sendMessageByAPITester("nas_imp_insert_error");
			
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String p_id = request.getParameter("p_id");
		String site_id = request.getParameter("site_id");
		String size = request.getParameter("size");
		String devicetype = request.getParameter("devicetype");
		String adspace_id= request.getParameter("adspace_id");
		String ipaddr	 = getRemoteAddr(request);
		
		String id     = request.getParameter("id");
		String p1     = request.getParameter("p1");// gross( dsp에서 준 값)
		String p2     = request.getParameter("p2");// net  ( gross * 0.9 한 값)
		
		String adtype= request.getParameter("adtype");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = ConnectionObject.getConnection();
			pstmt = conn.prepareStatement("insert into ssp_nas_imp values(?, ?, ?,  now(), ?, ?, ?, ?, ?, concat(now(),?), ?, ?)");
		   
			int idxx = 1;
			pstmt.setString(idxx++, site_id);
			pstmt.setString(idxx++, p_id);
			pstmt.setString(idxx++, adspace_id);			
			pstmt.setString(idxx++, ipaddr);
			pstmt.setString(idxx++, devicetype);
			pstmt.setString(idxx++, size);
			pstmt.setString(idxx++, p1);
			pstmt.setString(idxx++, p2);
			pstmt.setString(idxx++, id);//2022-06-12T11:22:31.51200:3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			pstmt.setString(idxx++, id);//3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			pstmt.setString(idxx++, adtype);
			
			pstmt.executeUpdate();
	    	pstmt.close();
		} catch (Exception e) {
		
			System.out.println(pstmt);
			System.err.println(e.getMessage());
			
			SlackBot bot = new SlackBot();
			bot.sendMessageByAPITester("nas_imp_insert_error");
			
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}

 	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

}
