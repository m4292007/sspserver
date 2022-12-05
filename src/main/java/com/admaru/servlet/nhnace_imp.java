package com.admaru.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admaru.util.ConnectionObject;
import com.admaru.util.SlackBot;
import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

public class nhnace_imp extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(nhnace_imp.class);
	private static final long serialVersionUID = 1L;
       
    public nhnace_imp() {
        super();
    }
	
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

	/**
	 * 여기에 imp, clck 같은 로그 남김 auction.log에 남음
	 */
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
System.out.println("xxxxxxxxxxxxxxx");
		String p_id = request.getParameter("p_id");
		String site_id = request.getParameter("site_id");
		String size = request.getParameter("size");
		String devicetype = request.getParameter("devicetype");
		String adspace_id= request.getParameter("adspace_id");
		String ipaddr	 = getRemoteAddr(request);
		
		String id     = request.getParameter("id");
		String p1     = request.getParameter("p1");// gross( dsp에서 준 값)
		String p2     = request.getParameter("p2");// net  ( gross * 0.9 한 값)
/*		
tracker = "<img src=\"https://p.admaru.net/sspserver/nhnace_imp?p_id=1000&site_id="+site_id+"&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one_id+"&p1="+price+"&p2="+net_price+"\" border=\"0\" height=\"1\" width=\"1\" alt=\"Advertisement\">";
http://localhost/sspserver/nhnace_imp?p_id=1000&site_id=9999&size=300x300&devicetype=1&adspace_id=div-ssss-1&id=3a19982a-f99c-4c1a-8f3f-2060410b9cf6&p1=0.01&p2=0.01
*/
//		LogFacade.logAuction(      "imp:p_id="+p_id+"&site_id="+site_id+":adspace_id="+adspace_id+":ip="+ip+":id="+id+":p1="+p1+":p2="+p2+":size="+size+":devicetype="+devicetype);

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = ConnectionObject.getConnection();
			//pstmt = conn.prepareStatement("insert into ssp_imp values(?, ?, ?,  DATE_ADD(?,interval 9 hour), ?, ?, ?, ?, ?, ?)");
			pstmt = conn.prepareStatement("insert into ssp_imp2 values(?, ?, ?,  now(), ?, ?, ?, ?, ?, concat(now(),?), ?)");
		   
			int idxx = 1;
			pstmt.setString(idxx++, site_id);
			pstmt.setString(idxx++, p_id);
			pstmt.setString(idxx++, adspace_id);
			//pstmt.setString(idxx++, log_ts);
			pstmt.setString(idxx++, ipaddr);
			pstmt.setString(idxx++, devicetype);
			pstmt.setString(idxx++, size);
			pstmt.setString(idxx++, p1);
			pstmt.setString(idxx++, p2);
			pstmt.setString(idxx++, id);//2022-06-12T11:22:31.51200:3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			pstmt.setString(idxx++, id);//3a19982a-f99c-4c1a-8f3f-2060410b9cf6
			
			pstmt.executeUpdate();
	    	pstmt.close();
		} catch (Exception e) {
		
			System.out.println(pstmt);
			System.err.println(e.getMessage());
			
			SlackBot bot = new SlackBot();
			bot.sendMessageByAPITester("imp_insert_error");
			
			e.printStackTrace();
		} finally {
			try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			try {conn.close();} catch (SQLException e) {e.printStackTrace();}
		}		
	}
}