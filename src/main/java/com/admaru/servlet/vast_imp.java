package com.admaru.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atg.openssp.common.logadapter.LogFacade;
import java.sql.*;
import com.admaru.util.*;

public class vast_imp extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(vast_imp.class);
	private static final long serialVersionUID = 1L;

    public vast_imp() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String ip	 = getRemoteAddr(request);
		String site_id = request.getParameter("site_id");
		String id     = request.getParameter("id");
		String p1     = request.getParameter("p1");// gross( dsp에서 준 값)
		String p2     = request.getParameter("p2");// net  ( gross * 0.9 한 값)
		String p_id = request.getParameter("p_id");
		String size = request.getParameter("size");
		String devicetype = request.getParameter("devicetype");
		String adspace_id= request.getParameter("adspace_id");
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = ConnectionObject.getConnection();
			pstmt = conn.prepareStatement("insert into ssp_impressions values(now(),?,  ?, ?, ?, ?, ?, ?, ?, ?)");
			
			int idxx = 1;
			pstmt.setString(idxx++, site_id);
			pstmt.setString(idxx++, p_id);
			pstmt.setString(idxx++, size);
			pstmt.setString(idxx++, devicetype);
			pstmt.setString(idxx++, adspace_id);
			pstmt.setString(idxx++, ip);
			pstmt.setString(idxx++, id);
			pstmt.setString(idxx++, p1);
			pstmt.setString(idxx++, p2);
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(pstmt);
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	 
	
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }
}