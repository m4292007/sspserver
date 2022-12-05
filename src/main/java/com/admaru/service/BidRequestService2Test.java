package com.admaru.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BidRequestService2Test")
public class BidRequestService2Test extends HttpServlet {

	
	private static final long serialVersionUID = 7615109299925800642L;

	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

		 // request처리
		 System.out.println("call..");

		 response.getWriter().println("Hello");
		 
		 // post dsp
		 
		 
		 // return to ad call tag
		 
	    
	 }
	 
}
/*

String testJSONString = 
"		{"+
"				\"id\": \"80ce30c53c16e6ede735f123ef6e32361bfc7b22\","+
"				\"bcat\": [ \"IAB25\", \"IAB7-39\", \"IAB8-18\", \"IAB8-5\", \"IAB9-9\" ],"+
"				\"imp\": [ { "+
"					\"id\": \"1\", "+ 
"					\"bidfloor\": 0.03,"+ 
"					\"bidfloorcur\": \"USD\","+
"					\"banner\": { \"h\": 250, \"w\": 300 }"+ 
"				} ], "+
"				\"site\": {"+ 
"					\"id\": \"102855\","+ 
"					\"cat\": [ \"IAB3-1\" ],"+ 
"					\"domain\": \"www.foobar.com\","+ 
"					\"name\": \"foobar\" "+
"				}, "+
"				\"device\": {"+ 
"					\"ua\": \"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.13 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2\","+ 
"					\"ip\": \"123.145.167.10\" "+
"				}, "+
"				\"user\": {"+ 
"					\"id\": \"55816b39711f9b5acf3b90e313ed29e51665623f\""+ 
"				}, "+
"				\"ext\" : {"+ 
"					\"contracttype\": 1,"+
"					\"clicktrack\": 1"+
"				} "+
"			}";
			

@RequestMapping("/")
String SendRequest() {
	Instant start = Instant.now();

	//1. send bidrequest to dsp
	String result = sendREST("http://hls.admaru.com:8080/", testJSONString);
	
	//1. get htp request ad 
	
	//2. build bid request
	
	//3. post bid to dsp
	
	//4. receive response
	
	//5. check price floor
	
	//6. response to http requester
	
	Instant finish = Instant.now();
	long timeElapsed = Duration.between(start, finish).toMillis();
	System.out.println("Elapsed ms ="+timeElapsed);
	return result;
}


public String sendREST(String sendUrl, String jsonValue) {
	String inputLine = null;
	StringBuffer outResult = new StringBuffer();
	
	try 
	{
		logger.debug("REST call to dsp");
		URL url = new URL(sendUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);//?
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Accept-Charset", "UTF-8");
		conn.setConnectTimeout(10000);//줄여야함..
		conn.setReadTimeout(10000);//
		
		OutputStream os = conn.getOutputStream();
		os.write(jsonValue.getBytes("UTF-8"));
		os.flush();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		while((inputLine = in.readLine()) != null) {
			outResult.append(inputLine);
		}
		
		conn.disconnect();
		logger.debug("end");
	} catch(Exception e) {
		logger.error(e.getMessage(),e);
		e.printStackTrace();
	}
	
	return outResult.toString();
}
*/