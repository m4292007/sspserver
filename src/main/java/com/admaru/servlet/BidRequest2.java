package com.admaru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import ua_parser.Parser;
import ua_parser.Client;

import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

public class BidRequest2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BidRequest2() {
        super();
    }

    private final String prod_nasmedia = "http://adpv.admixer.co.kr/rtb/bidrequest/10";
    
    public static void main(String...args) throws ClientProtocolException, IOException 
    {
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adm ="";
		String site_name = "";
		String passback_url = "";
		String tracker = "";
		
		String complete_tracker = "";
		String progress_tracker = "";
		
		String button_click_tracker = "";
		String skip_tracker = "";
		String click_tracker = "";
		String site_id = "";
		String secure = "0";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		               // If omitted, the secure state is unknown, but non-secure HTTP support can be assumed.
		
		int bidfloor = 1;//2020.02.22 by 김부장요청
		UUID one = UUID.randomUUID();
		
		String browserDetails = "";
		
		//날짜 
		try 
		{
			String w = request.getParameter("w");
			String h = request.getParameter("h");
			site_id = request.getParameter("site_id");
			
			secure = StrUtil.nvl(request.getParameter("secure"),"0");
			
			if ("v1011".equals(site_id)) 
			{
				// 중앙일보
				// https://s1.admaru.net/sspserver/BidRequest2?site_id=v1011&w=640&h=360&secure=1
				
				site_name = "https://www.joongang.co.kr/";				
				bidfloor  = 1;//USD only 로 변경되어서 2022.2.10
				passback_url = "";			
			}

			browserDetails  =   request.getHeader("User-Agent").toLowerCase();
			String old_url = request.getHeader("referer");
			
			String d = browserDetails.substring( browserDetails.indexOf("(")+1, browserDetails.indexOf(")"));
			String[] e = d.split(";");

			String os = "unknown";
			String osv = "unknown";
			String model = "unknown";
			String devicetype ="2";
			
			try 
			{
				if ("linux".equals(e[0]))
				{
					os  = e[1].trim().split(" ")[0];
					osv = e[1].trim().split(" ")[1];
					model =  e[2].trim().split(" ")[0];
					devicetype ="1";
				} 
				else if ("iphone".equals(e[0]))
				{
					os  = "ios";
					// (iphone; cpu iphone os 13_3_1 like mac os x)
					osv = e[1].substring(e[1].indexOf("cpu iphone os ")+14,e[1].indexOf(" like")).replaceAll("_",".");
					model = "iphone";
					devicetype ="1";
				} 
			}catch(Exception ex) {
				
			}
			
			///////////////////////////
			String userAgent = request.getHeader("user-agent");
			boolean mobile1 = userAgent.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
			boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*"); 

			if (mobile1 || mobile2) {
				devicetype ="1";
			} else {
				devicetype ="2";
			}
			
			
			String uaString = request.getHeader("User-Agent");

			Parser uaParser = new Parser();
			Client c = uaParser.parse(uaString);

//			System.out.println(c.userAgent.family); // => "Mobile Safari"
//			System.out.println(c.userAgent.major);  // => "5"
//			System.out.println(c.userAgent.minor);  // => "1"
//
//			System.out.println(c.os.family);        // => "iOS"
//			System.out.println(c.os.major);         // => "5"
//			System.out.println(c.os.minor);         // => "1"
//
//			System.out.println(c.device.family);    // => "iPhone"
			
			if (c.os.family != null && !"".equals(c.os.family)) {
				os = c.os.family;
			}
			
			if (c.os.major != null && !"".equals(c.os.major)) {
				osv = c.os.major;
			}
			
			if (c.os.minor != null && !"".equals(c.os.minor)) {
				osv = osv + "."+ c.os.minor;
			}
			
			/////////////////////////

			//TODO devicetype 실제값에 맞게 세팅
			// at, tmax publisher cur 추가 todo
			String param = "{\"id\": \"" + one.toString() + "\",    "
					+ " \"imp\": [ { \"id\" : \"1\", \"video\" : { \"mimes\": [ \"video\\/mp4\" ], \"minduration\": 5, \"maxduration\": 360, \"protocols\": [ 3 ], "
					+ " \"w\": "+ w +", \"h\": "+ h + "}, \"bidfloor\": " + bidfloor + ",\"bidfloorcur\": \"KRW\",\"secure\": " + secure + " } ], "
					+ " \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ]}, "
					+ " \"device\": {  \"ip\": \""+getRemoteAddr(request)+ "\", \"devicetype\": "+ devicetype +", \"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"}"
					+ "}";
			
			URL url = new URL(prod_nasmedia);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			//--header "x-openrtb-version: 2.5"
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("x-openrtb-version","2.5");
					
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
			
			LogFacade.logRequestAsync("prod send:"+site_id+":"+":"+prod_nasmedia+":"+param);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line =null;
			StringBuffer outResult = new StringBuffer();
			
			while( (line = br.readLine()) != null) 
			{
				// read시 exception이 발생할 경우?? 
				outResult.append(line);
			}
	
			String dspResult = outResult.toString();		
			//LogFacade.logRequestAsync("prod receive:"+site_id+":"+dspResult);
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(dspResult );
			JSONObject jsonObj = (JSONObject) obj;
	
			JSONArray seatbid = (JSONArray) jsonObj.get("seatbid");
			JSONObject x = (JSONObject) seatbid.get(0);
			JSONArray bidArray = (JSONArray)x.get("bid");
			JSONObject bid = (JSONObject) bidArray.get(0); 
			double price = (double) bid.get("price"); 
			adm = (String) bid.get("adm");
			adm = adm.replace("${AUCTION_PRICE}", ""+price);
						
			osw.close();
			br.close();
			conn.disconnect();
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok");
			
		}catch(Exception e) {
			LogFacade.logRequestAsync("prod receive:"+site_id+":no_ad");

			if (!"".equals(passback_url))
			{
				CloseableHttpClient httpClient = HttpClients.createDefault();
		        HttpGet httpGet = new HttpGet(passback_url);
		        httpGet.addHeader("User-Agent", browserDetails);
		        httpGet.addHeader("Content-type", "application/xml");
		        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		        String xml = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		        httpClient.close();
		        
		        adm = xml;
			} else {
				adm = "<VAST version=\"4.0\"><Error>no ads</Error></VAST>";
			}

		}
		
		response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	response.setHeader("Access-Control-Allow-Credentials","true");
    	response.getWriter().println(adm);
	}

	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

}
