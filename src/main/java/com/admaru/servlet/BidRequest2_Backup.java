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

import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

/**
 * lineitem : Womandonga-IBV-Mobile-Nasmedia (KR)
 */

public class BidRequest2_Backup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BidRequest2_Backup() {
        super();
    }

    //private final String test_nasmedia = "http://183.110.238.182:25999/rtb/bidrequest/16";
    private final String prod_nasmedia = "http://adpv.admixer.co.kr/rtb/bidrequest/10";

    
    public static void main(String...args) throws ClientProtocolException, IOException 
    {
//    	BidRequest2 bidRequest2 = new BidRequest2();
//    	String result = bidRequest2.getPerformancePlay();
//    	System.out.println(result);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adm ="";
		String site_name = "";
		
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
			
			if ("v1001".equals(site_id)) 
			{
				//https://www.adopsinsider.com/ad-ops-basics/what-is-a-cache-buster-and-how-does-it-work/
				//ord=Math.random()*10000000000000000;
				
				site_name = "woman.donga.com";				
				tracker = "<Impression><![CDATA[https://69bweelsej.execute-api.ap-northeast-2.amazonaws.com/default/vast_imp]]></Impression>";
				tracker = tracker+"<Impression><![CDATA[https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.265500376;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?]]></Impression>";
				bidfloor  = 1000;
				complete_tracker = "<Tracking event=\"complete\"><![CDATA[https://josv0ffzl0.execute-api.ap-northeast-2.amazonaws.com/default/vast_complete]]></Tracking>";
				progress_tracker = "<Tracking event=\"progress\" offset=\"00:00:15\"><![CDATA[https://58jle0xepc.execute-api.ap-northeast-2.amazonaws.com/default/vast_progress]]></Tracking>";
				
				click_tracker="<ClickTracking><![CDATA[https://4zj0i6sqfl.execute-api.ap-northeast-2.amazonaws.com/default/vast_click]]></ClickTracking>";
				
				button_click_tracker="<IconClickTracking><![CDATA[https://g1josqyhjf.execute-api.ap-northeast-2.amazonaws.com/default/vast_button_click]]></IconClickTracking>";
				skip_tracker="<IconClickTracking><![CDATA[https://nw35aqqiq0.execute-api.ap-northeast-2.amazonaws.com/default/vast_skip]]></IconClickTracking>";
				
			}  		

			browserDetails  =   request.getHeader("User-Agent").toLowerCase();
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
					model =  e[2].trim().split(" ")[0];;
				} 
				else if ("iphone".equals(e[0]))
				{
					os  = "ios";
					// (iphone; cpu iphone os 13_3_1 like mac os x)
					osv = e[1].substring(e[1].indexOf("cpu iphone os ")+14,e[1].indexOf(" like")).replaceAll("_",".");
	
					model = "iphone";
				} 
			}catch(Exception ex) {
				
			}

			//TODO devicetype 실제값에 맞게 세팅
			
			String param = "{\"id\": \"" + one.toString() + "\",    "
					+ " \"imp\": [ { \"id\" : \"1\", \"video\" : { \"mimes\": [ \"video\\/mp4\" ], \"minduration\": 5, \"maxduration\": 360, \"protocols\": [ 3 ], "
					+ " \"w\": "+ w +", \"h\": "+ h + "}, \"bidfloor\": " + bidfloor + ",\"bidfloorcur\": \"KRW\",\"secure\": " + secure + " } ], "
					+ " \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ]}, "
					+ " \"device\": {  \"ip\": \""+getRemoteAddr(request)+ "\", \"devicetype\": 1, \"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"}"
					+ "}";
			
			URL url = new URL(prod_nasmedia);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type","application/json");
					
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
			LogFacade.logRequestAsync("prod receive:"+site_id+":"+dspResult);
			
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
			adm = adm.replace("<Impression>", tracker+"<Impression>");
			adm = adm.replace("<Tracking event=\"complete\">", complete_tracker+"<Tracking event=\"complete\">");
			adm = adm.replace("<Tracking event=\"progress\" offset=\"00:00:15\">", progress_tracker+"<Tracking event=\"progress\" offset=\"00:00:15\">");
			adm = adm.replace("<ClickTracking>", click_tracker+"<ClickTracking>");
			
			
			//if (<Icon program="ad" 있으면) {
			//	첫번째<IconClickTracking>에
			//	adm = adm.replace("<IconClickTracking>", button_click_tracker+"<ClickTracking>");
			//}

			int buttonClickIndex = adm.indexOf("</IconClickTracking>")+"</IconClickTracking>".length();
			StringBuilder sb = new StringBuilder();
		    sb.append(adm);
		    sb.insert(buttonClickIndex, button_click_tracker);
			
			int skipIndex = sb.indexOf("</IconClickTracking>", buttonClickIndex + button_click_tracker.length());
			sb.insert(skipIndex+"</IconClickTracking>".length(), skip_tracker);
			adm = new String(sb);
			LogFacade.logRequestAsync("prod vast:"+site_id+":"+adm);
			osw.close();
			br.close();
			conn.disconnect();
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok");
			
		}catch(Exception e) {
			LogFacade.logRequestAsync("prod receive:"+site_id+":no_ad");
			String urlString = "https://imp.performanceplay.co.kr/pp?m=M00251&s=4elbg0i7l";
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        HttpGet httpGet = new HttpGet(urlString);
	        httpGet.addHeader("User-Agent", browserDetails);
	        httpGet.addHeader("Content-type", "application/xml");
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        //System.out.println(httpResponse.getStatusLine().getStatusCode());
	        String xml = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			LogFacade.logProviderAsync("perpl:"+site_id+":"+xml);
	        httpClient.close();
	        adm = xml;
		}
		
		//LogFacade.logProviderAsync(one.toString()+":"+adm);
		response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	//adm = xml_header+"\n"+adm;
    	response.getWriter().println(adm);
	}

	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

}
