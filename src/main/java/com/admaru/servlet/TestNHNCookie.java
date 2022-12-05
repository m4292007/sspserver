package com.admaru.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestNHNCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	public static void main(String...args) throws IOException {

String a = "https://p2.admaru.net/UserSync/uid?p_id=1000&u_id=5WFNYYHEU3WF24E2C9JIMFQFA";
System.out.println(a.substring(50));


//		String srcUrl = "https://cm-exchange.toast.com/bi/pixel?cm_mid=1330086872&toast_push";
//
//		for (int i = 0; i < 100; i++) {
//	    	String redirectUrl = getRedirectUrl(srcUrl);
//	
//		
//	    	if (redirectUrl != null) {
//	      		System.out.println(redirectUrl);
//	    	}
//		}
   	
   	
   	}
    public TestNHNCookie() {
        super();
    }
    
	//매호출시 새로운 쿠키값이 리턴되는지 여부를 확인하기 위함.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String srcUrl = "https://cm-exchange.toast.com/bi/pixel?cm_mid=1330086872&toast_push";

		for (int i = 0; i < 100; i++) {
	    	String redirectUrl = getRedirectUrl(srcUrl);
	
		
	    	if (redirectUrl != null) {
	      		System.out.println(redirectUrl);
	    	}
		}
		// logfile에도 기록 해서 내중에 복구할일 있으면 처리
		// insert redirectUrl내 u_id 값과 request.getParameter("PCID")를 redis에 기록 
	}


  	public static String getRedirectUrl(String srcUrl) throws IOException {
    	//Note that 4xx and 5xx do not raise an exception
	    HttpURLConnection con = (HttpURLConnection) new URL(srcUrl).openConnection();
	    con.setRequestMethod("GET");
	    con.setInstanceFollowRedirects(false); //Setting not to redirect automatically
	    con.connect();
	    //Get Location header from HTTP response
	    String location = con.getHeaderField("location");
	    con.disconnect();
	    return location;
	}
}