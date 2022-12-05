package com.admaru.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua_parser.Parser;
import ua_parser.Client;

public class TestUA1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	    
    public TestUA1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  devicetype = "";
		String userAgent = request.getHeader("user-agent");
		
		boolean mobile1 = userAgent.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
		boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*"); 

		if (mobile1 || mobile2) {
			devicetype ="1";
		} else {
			devicetype ="2";
		}
		
		System.out.println(userAgent);

		Parser uaParser = new Parser();
		Client c = uaParser.parse(userAgent);

		System.out.println(c.userAgent.family); // => "Mobile Safari"
		System.out.println(c.userAgent.major);  // => "5"
		System.out.println(c.userAgent.minor);  // => "1"

		System.out.println(c.os.family);        // => "iOS"
		System.out.println(c.os.major);         // => "5"
		System.out.println(c.os.minor);         // => "1"

		System.out.println(c.device.family);    // => "iPhone"
		String os = "";
		String osv = "";
		if (c.os.family != null && !"".equals(c.os.family)) {
			os = c.os.family;
		}
		
		if (c.os.major != null && !"".equals(c.os.major)) {
			osv = c.os.major;
		}
		
		if (c.os.minor != null && !"".equals(c.os.minor)) {
			osv = osv + "."+ c.os.minor;
		}
		System.out.println("============");
		System.out.println(os);
		System.out.println(osv);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
