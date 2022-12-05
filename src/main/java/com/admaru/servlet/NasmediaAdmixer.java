package com.admaru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

import ua_parser.Parser;
import ua_parser.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Video
 * 
 */ 
public class NasmediaAdmixer extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(NasmediaAdmixer.class);
	private static final long serialVersionUID = 1L;

	private final String prod_nasmedia = "http://adn.admixer.co.kr:10303/rtb/bidrequest/127";
       
    public NasmediaAdmixer() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String adspace_id = request.getParameter("adspace_id");
		String adm ="";
		String site_name = "";
		String passback_url = "";
		double net_price = 0d;
		String site_id = "";
		String publisher = "2022";
		String secure = "0";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		String tag_id = "";
		String devicetype ="2";
		
		// 2022.07.18 2500KRW로 변경 
		float bidfloor = 1.9f;//0.5f;
		
		
		UUID one = UUID.randomUUID();
		String one_id = one.toString();
		
		String browserDetails = "";
		 
		try 
		{
			String w = StrUtil.nvl( request.getParameter("w"), "300");
			String h = StrUtil.nvl( request.getParameter("h"), "250");
			site_id  = StrUtil.nvl( request.getParameter("site_id"), "2022");
			String buyerid = StrUtil.nvl( request.getParameter("buyerid"), "");
			secure = StrUtil.nvl(request.getParameter("secure"),"1");
						
			// 중앙일보 모바일 상단 비디오
			site_name = "https://www.joongang.co.kr/";				
			

			browserDetails  =   request.getHeader("User-Agent");
			String old_url = request.getHeader("referer");
			
			String d = browserDetails.substring( browserDetails.indexOf("(")+1, browserDetails.indexOf(")"));
			String[] e = d.split(";");

			String os = "unknown";
			String osv = "unknown";
			String model = "unknown";
			
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
			
			if (c.os.family != null && !"".equals(c.os.family)) {
				os = c.os.family;
			}
			
			if (c.os.major != null && !"".equals(c.os.major)) {
				osv = c.os.major;
			}
			
			if (c.os.minor != null && !"".equals(c.os.minor)) {
				osv = osv + "."+ c.os.minor;
			}

			// bidfloor USD만 지원
			String param = "{\"id\": \"" + one_id + "\", \"tmax\":1000, \"at\": 1, \"cur\": [ \"USD\" ],  "
			        + " \"displaymanager\": \"Admaru SDK\",\"displaymanagerver\": \"0.1\","			
					+ " \"imp\": [ { \"id\" : \"1\", \"video\" : { \"mimes\": [ \"video\\/mp4\" ], \"minduration\": 5, \"maxduration\": 360, \"protocols\": [ 3 ], "
					+ " \"w\": "+ w +", \"h\": "+ h + "}, \"bidfloor\": " + bidfloor + ",\"bidfloorcur\": \"USD\",\"secure\": " + secure + " } ], "
					
					+ " \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ], \"publisher\":{\"id\":\""+publisher+"\"} },"					
					
					
					+ " \"device\": {  \"ip\": \""+getRemoteAddr(request)+ "\",  \"ua\":\""+browserDetails+"\",  \"devicetype\": "+ devicetype +", \"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"}, "
					+ " \"user\": { \"buyerid\": \""+buyerid+"\"} "
					+ "}";
			
			URL url = new URL(prod_nasmedia);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("x-openrtb-version","2.5");
					
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
						
			//LogFacade.logRequestAsync("prod send:NasmediaAdmixer:"+site_id+":"+":"+prod_nasmedia+":"+param);
			LogFacade.logRequestAsync("prod send:NasmediaAdmixer:"+site_id+":"+":"+prod_nasmedia);
						
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line =null;
			StringBuffer outResult = new StringBuffer();
			
			while( (line = br.readLine()) != null) 
			{
				// read시 exception이 발생할 경우?? 
				outResult.append(line);
			}
	
			String dspResult = outResult.toString();		
			LogFacade.logProviderAsync("vast_xml:"+site_id+":"+dspResult);
			
			osw.close();
			br.close();
			conn.disconnect();
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(dspResult );
			JSONObject jsonObj = (JSONObject) obj;
	
			JSONArray seatbid = (JSONArray) jsonObj.get("seatbid");
			JSONObject x = (JSONObject) seatbid.get(0);
			JSONArray bidArray = (JSONArray)x.get("bid");
			JSONObject bid = (JSONObject) bidArray.get(0); 
			double price = (double) bid.get("price");
			net_price = price * 0.9d;			
			// 아래 NasmediaTracker에 호출하는 서블릿 식별값 추가?
			String tracker = "<Impression><![CDATA[ https://p.admaru.net/sspserver/NasmediaTracker?adtype=video&p_id=1001&site_id=2022&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one_id+"&p1="+price+"&p2="+net_price+"]]></Impression>";
			adm = (String) bid.get("adm");
			adm = adm.replace("${AUCTION_PRICE}", ""+price);
			adm = adm.replace("<InLine><Impression>", "<InLine>"+tracker+"<Impression>");
		
			LogFacade.logProviderAsync("vast_xml:"+site_id+":"+adm);
				
			if (net_price < 0.01) {
				net_price = 0.0d;
				adm = "<VAST version=\"4.0\"><Error>no ads!</Error></VAST>";
			}
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":ad_ok:"+"gross price="+price + ":net price="+net_price);
		}catch(Exception e) {
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":no_ad");
			
			adm = "<VAST version=\"4.0\"><Error>no ads2</Error></VAST>";
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