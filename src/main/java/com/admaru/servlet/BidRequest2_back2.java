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

public class BidRequest2_back2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BidRequest2_back2() {
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
				bidfloor  = 300;
				passback_url = "";			
			}
			
//			else if ("v1001".equals(site_id)) 
//			{
//				//Creative: Womandonga-IBV-Mobile-Nasmedia (KR)
//				site_name = "woman.donga.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00251&s=4elbg0i7l";
//				secure = "1";
//			} 
//			else if ("v1002".equals(site_id)) 
//			{
//				// ShinDonga_MB_NEWS_300x250_Video (NAS) - KR 
//				site_name = "shindonga.donga.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00249&s=d3l4crnqf";
//			}  	 	
//			else if ("v1003".equals(site_id)) 
//			{
//				// WeeklyDonga_MB_NEWS_300x250_Video (NAS) - KR
//				site_name = "weekly.donga.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00250&s=2vzu30hba";
//			}
//			else if ("v1004".equals(site_id)) 
//			{
//				//BiznDonga_MB_NEWS_300x250_Video (NAS) - KR
//				site_name = "bizn.donga.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00248&s=8tzvxrvmi";
//			}
//			else if ("v1005".equals(site_id)) 
//			{
//				// Wkorea-OSV-Mobile-ICV-Nasmedia (KR) PerformancePlay
//				site_name = "www.wkorea.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00271&s=shbkw25zf";
//			}
//			else if ("v1006".equals(site_id)) 
//			{
//				// Wkorea-OSV-Mobile-ICV-Nasmedia (KR) SignalPlay
//				site_name = "www.wkorea.com";				
//				bidfloor  = 1000;
//				passback_url = "https://mtag.mman.kr/get_ad.mezzo/?e_version=2&a_publisher=1547&a_media=32095&a_section=804557&i_response_format=xml&d_used_type=api&i_video_w=640&i_video_h=360&d_app_id=&d_app_name=";
//			}
//			else if ("v1007".equals(site_id)) 
//			{
//				// https://s2.admaru.net/sspserver/BidRequest2?site_id=v1007&w=640&h=360
//				// Allurekorea-OSV-Mobile-ICV-Nasmedia (KR) SignalPlay
//				site_name = "www.allurekorea.com";			
//				bidfloor  = 1000;
//				passback_url = "https://mtag.mman.kr/get_ad.mezzo/?e_version=2&a_publisher=1547&a_media=32096&a_section=804559&i_response_format=xml&d_used_type=api&i_video_w=640&i_video_h=360&d_app_id=&d_app_name=";
//			}
//			else if ("v1008".equals(site_id)) 
//			{
//				// Allurekorea-OSV-Mobile-ICV-Nasmedia (KR) PerformancePlay
//				// https://s2.admaru.net/sspserver/BidRequest2?site_id=v1008&w=640&h=360
//				site_name = "www.allurekorea.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00272&s=k9f4zzgu2";
//			}
//			//2020.07.13 추가
//			else if ("v1009".equals(site_id)) 
//			{
//				// BizChosun-OSV-Desktop-HP-SIDEVIEW-PerformancePlay (KR)
//				// https://s2.admaru.net/sspserver/BidRequest2?site_id=v1009&w=640&h=360
//				site_name = "https://biz.chosun.com";				
//				bidfloor  = 1000;
//				passback_url = "https://imp.performanceplay.co.kr/pp?m=M00240&s=4n3iqc769";
//			}
//			else if ("v1010".equals(site_id)) 
//			{
//				// BizChosun-OSV-Desktop-HP-SIDEVIEW-SignalPlay (KR)
//				// https://s2.admaru.net/sspserver/BidRequest2?site_id=v1010&w=640&h=360
//				site_name = "https://biz.chosun.com/";				
//				bidfloor  = 1000;
//				passback_url = "https://mtag.mman.kr/get_ad.mezzo/?e_version=2&a_publisher=1547&a_media=32107&a_section=804633&i_response_format=xml&d_used_type=api&i_video_w=640&i_video_h=360&d_app_id=&d_app_name=";
//			}

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
			/////////////////////////

			//TODO devicetype 실제값에 맞게 세팅
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
    	response.getWriter().println(adm);
	}

	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

}
