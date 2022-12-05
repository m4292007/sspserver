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

public class NasmediaPrebid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private final String prod_nasmedia = "http://test.admixer.co.kr:8080/rtb/bidrequest/96";
	
    private final String prod_nasmedia = "http://adn.admixer.co.kr:10303/rtb/bidrequest/127";
    
    public NasmediaPrebid() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		String adspace_id = request.getParameter("adspace_id");
		String adm ="";
		String site_name = "";
		String passback_url = "";
		String tracker = "";
		double net_price = 0d;
		String complete_tracker = "";
		String progress_tracker = "";
		String button_click_tracker = "";
		String skip_tracker = "";
		String click_tracker = "";
		String site_id = "";
		String secure = "0";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		
		float bidfloor = 0.5f;
		
		UUID one = UUID.randomUUID();
		
		String browserDetails = "";
		 
		try 
		{
			String w = request.getParameter("w");
			String h = request.getParameter("h");
			site_id = request.getParameter("site_id");
			String buyerid = StrUtil.nvl( request.getParameter("buyerid"), "");
			secure = StrUtil.nvl(request.getParameter("secure"),"1");

			// v1012, v1013
						
//			if ("v1012".equals(site_id)) 
//			{
				// 중앙일보 신규
				site_name = "https://www.joongang.co.kr/";				
				bidfloor  = 0.5f;
//passback_url = "https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator=";
//<Impression><![CDATA[https://69bweelsej.execute-api.ap-northeast-2.amazonaws.com/default/vast_imp?p_id=1001&site_id=2022&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one.toString()+"&p1="+price+"&p2="+net_price+"]]></Impression>				
//			}

			browserDetails  =   request.getHeader("User-Agent");
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

			//2022.09.15
			bidfloor = 0.1f;
			
			String param = "{\"id\": \"" + one.toString() + "\", \"tmax\":1000, \"at\": 1, \"cur\": [ \"USD\" ],  "
			        + " \"displaymanager\": \"Admaru SDK\",\"displaymanagerver\": \"0.1\","
					+ " \"imp\": [ { \"id\" : \"1\", \"video\" : { \"mimes\": [ \"video\\/mp4\" ], \"minduration\": 5, \"maxduration\": 360, \"protocols\": [ 3 ], "
					+ " \"w\": "+ w +", \"h\": "+ h + "}, \"bidfloor\": " + bidfloor + ",\"bidfloorcur\": \"USD\",\"secure\": " + secure + " } ], "
					+ " \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ]}, "
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
			
			//////////////////////////
			//System.out.println(param);
			
			//LogFacade.logRequestAsync("prod send:NasmediaPrebid:"+site_id+":"+":"+prod_nasmedia+":"+param);
			LogFacade.logRequestAsync("prod send:NasmediaPrebid:"+site_id+":"+":"+prod_nasmedia);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			String line =null;
			StringBuffer outResult = new StringBuffer();
			
			while( (line = br.readLine()) != null) 
			{
				// read시 exception이 발생할 경우?? 
				outResult.append(line);
			}
	
			String dspResult = outResult.toString();		
			LogFacade.logRequestAsync("nasmediaPreibd:"+site_id+":"+dspResult);
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(dspResult );
			JSONObject jsonObj = (JSONObject) obj;
	
			JSONArray seatbid = (JSONArray) jsonObj.get("seatbid");
			JSONObject x = (JSONObject) seatbid.get(0);
			JSONArray bidArray = (JSONArray)x.get("bid");
			JSONObject bid = (JSONObject) bidArray.get(0); 
			double price = (double) bid.get("price");
			net_price = price * 0.9d;

            tracker = "<Impression><![CDATA[https://69bweelsej.execute-api.ap-northeast-2.amazonaws.com/default/vast_imp?p_id=1001&site_id=2022&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one.toString()+"&p1="+price+"&p2="+net_price+"]]></Impression>";				
			skip_tracker     = "<Tracking event=\"skip\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=skip&site_id=2022]]></Tracking>";	
			complete_tracker = "<Tracking event=\"complete\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=complete&site_id=2022]]></Tracking>";
			progress_tracker = "<Tracking event=\"progress\" offset=\"00:00:15\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=progress&site_id=2022]]></Tracking>";
			click_tracker    = "<ClickTracking><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=click&site_id=2022]]></ClickTracking>";
			button_click_tracker = "<IconClickTracking><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=buttonclick&site_id=2022]]></IconClickTracking>";
			String icon_skip_tracker         = "<IconClickTracking><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=iconskip&site_id=2022]]></IconClickTracking>";
			
			
			adm = (String) bid.get("adm");
			adm = adm.replace("${AUCTION_PRICE}", ""+price);
			adm = adm.replace("<InLine><Impression>", "<InLine>"+tracker+"<Impression>");
			adm = adm.replace("<Tracking event=\"complete\">", complete_tracker+"<Tracking event=\"complete\">");
			adm = adm.replace("<Tracking event=\"progress\" offset=\"00:00:15\">", progress_tracker+"<Tracking event=\"progress\" offset=\"00:00:15\">");
			adm = adm.replace("<ClickTracking>", click_tracker+"<ClickTracking>");

			//<Tracking event="start">
			String start_tracker = "<Tracking event=\"start\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=start&site_id=2022]]></Tracking>";
			adm = adm.replace("<Tracking event=\"start\">", start_tracker+"<Tracking event=\"start\">");

			//<Tracking event="midpoint">
			String midpoint_tracker = "<Tracking event=\"midpoint\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=midpoint&site_id=2022]]></Tracking>";
			adm = adm.replace("<Tracking event=\"midpoint\">", midpoint_tracker+"<Tracking event=\"midpoint\">");
			
			//<Tracking event="firstQuartile">
			String firstQuartile_tracker = "<Tracking event=\"firstQuartile\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=firstQuartile&site_id=2022]]></Tracking>";
			adm = adm.replace("<Tracking event=\"firstQuartile\">", firstQuartile_tracker+"<Tracking event=\"firstQuartile\">");
			
			//<Tracking event="thirdQuartile">
			String thirdQuartile_tracker = "<Tracking event=\"thirdQuartile\"><![CDATA[https://k0awe7giv4.execute-api.ap-northeast-2.amazonaws.com/default/nas_tracker?id="+one.toString()+"&etype=thirdQuartile&site_id=2022]]></Tracking>";
			adm = adm.replace("<Tracking event=\"thirdQuartile\">", thirdQuartile_tracker+"<Tracking event=\"thirdQuartile\">");

			int buttonClickIndex = adm.indexOf("</IconClickTracking>")+"</IconClickTracking>".length();
			StringBuilder sb = new StringBuilder();
		    sb.append(adm);
		    sb.insert(buttonClickIndex, button_click_tracker);
			
			int skipIndex = sb.indexOf("</IconClickTracking>", buttonClickIndex + button_click_tracker.length());
			sb.insert(skipIndex+"</IconClickTracking>".length(), icon_skip_tracker);
			adm = new String(sb);
			
			//LogFacade.logAuction("prod vast:"+site_id+":"+adm);
								
			osw.close();
			br.close();
			conn.disconnect();
			
				
			if (net_price < 0.01) {
				net_price = 0.0d;
				adm = "<VAST version=\"4.0\"><Error>no ads!</Error></VAST>";
			}
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok:"+"gross price="+price + ":net price="+net_price);			
			
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
		        System.out.println(xml);
		        //tracker = "<Impression><![CDATA[https://p2.admaru.net/sspserver/vast_imp?site_id="+site_id+"&id="+one.toString()+"&p1="+0+"&p2="+0+"]]></Impression>";
		        xml = xml.replace("<Impression>", tracker+"<Impression>");
		        adm = xml;
			} else {
				adm = "<VAST version=\"4.0\"><Error>no ads2</Error></VAST>";
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