package com.admaru.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import com.admaru.util.*;
import com.atg.openssp.common.logadapter.LogFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MezzoTracker extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(MezzoTracker.class);

	private static final long serialVersionUID = 1L;
    public MezzoTracker() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String site_id =  StrUtil.nvl( request.getParameter("site_id"),"");
		String ipaddr  =  getRemoteAddr(request);
		String etype   =  StrUtil.nvl( request.getParameter("etype"),"");
			
		LogFacade.logRequestAsync("mezzo_tracker=>"+"site_id="+site_id+":ipaddr="+ipaddr+":etype="+etype);
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = ConnectionObject.getConnection();
			pstmt = conn.prepareStatement("insert into mezzo_tracker values(?, now(), ?, ?)");
		
			int idxx = 1;
			pstmt.setString(idxx++, site_id);
			pstmt.setString(idxx++, ipaddr);
			pstmt.setString(idxx++, etype);
			pstmt.executeUpdate();
	    	pstmt.close();
		} catch (Exception e) {
			System.err.println(">> mezzo tracking log error");
			System.out.println(pstmt);
			System.err.println(e.getMessage());
			e.printStackTrace();

			SlackBot bot = new SlackBot();
			bot.sendMessageByAPITester("mezzo_tracker_insert_error");			
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	response.setHeader("Access-Control-Allow-Credentials","true");
    	response.getWriter().println("ok");		
	}
	
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }
}