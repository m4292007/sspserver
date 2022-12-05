package com.admaru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.json.HTTP;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

public class BidRequest3 extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(BidRequest.class);
	
	private static final long serialVersionUID = 1L;
       	
    public BidRequest3() {
        super();
    }

    // report url
    //  https://partner.admixer.co.kr/api/ssp/report?api_key=75364f3b7e6b54adf42e2dc3d666b0b35ea98f83&date=2020-12-29
	//  private final String test_admixer = "http://adn2.admixer.co.kr:25846/rtb/bidrequest/96";
    
    private final String prod_admixer = "http://adn.admixer.co.kr:10303/rtb/bidrequest/127";    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String adm ="";
		String site_name = "";
		String default_ad = "";
		String tracker = "";//impression
		String site_id = "";
		String ep = "";
		String secure = "0";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		               // If omitted, the secure state is unknown, but non-secure HTTP support can be assumed.
		//String click_track_url = "https://lwoc3cyar8.execute-api.ap-northeast-2.amazonaws.com/default/admixer_click";
		
		double bidfloor = 0.1d; //USD
		UUID one = UUID.randomUUID();
		 
		try 
		{
			String w = request.getParameter("w");
			String h = request.getParameter("h");
			site_id = request.getParameter("s");
			secure = StrUtil.nvl(request.getParameter("secure"),"0");
			
			if ("admixer1001".equals(site_id)) 
			{
				site_name = "h21.hani.co.kr";
				default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Womandonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466493490-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466493490-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466493490-0'); }); </script> </div></body></html>";
				//tracker = "<IMG SRC=\"https://msk87bvlh7.execute-api.ap-northeast-2.amazonaws.com/default/admix_imp\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				bidfloor  = 1.00d;
				ep = prod_admixer;
				
			} else if ("admixer_chosun".equals(site_id)) {
				//Creative: Chosun Mobile 300x250(nasmedia)
				site_name = "chosun.com";
				default_ad = "<script type=\"text/javascript\">google_ad_client = \"ca-pub-3533864070776314\";/* chosun_Marticlebottom_a_300_250 */google_ad_slot = \"chosun_Marticlebottom_a_300_250\";google_ad_width = 300;google_ad_height = 250;google_override_format=\"true\";google_page_url=\"//chosun.com\";</script><script type=\"text/javascript\" src=\"//pagead2.googlesyndication.com/pagead/show_ads.js\"></script>";
				//tracker = "<IMG SRC=\"https://msk87bvlh7.execute-api.ap-northeast-2.amazonaws.com/default/admix_imp\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				tracker = "<IMG SRC=\"https://msk87bvlh7.execute-api.ap-northeast-2.amazonaws.com/default/admix_imp?site=chosun&uuid="+one+"\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				secure = "1";
				bidfloor  = 0.1d;
				ep = prod_admixer;
			} else if ("admixer_bizndonga".equals(site_id)) {
				//Creative: Bizndonga Mobile 300x250_2.12-2.14 (Nasmedia)
				site_name = "bizn.donga.com";
				default_ad ="<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script><script>  window.googletag = window.googletag || {cmd: []};  googletag.cmd.push(function() { googletag.defineSlot('/1249652/BiznDonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581470822767-0').addService(googletag.pubads());    googletag.pubads().enableSingleRequest();    googletag.enableServices();  });</script></head><body><div id='div-gpt-ad-1581470822767-0' style='width: 300px; height: 250px;'>  <script>    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581470822767-0'); });  </script></div></body></html>";
				//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267336761;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				tracker = "<IMG SRC=\"https://msk87bvlh7.execute-api.ap-northeast-2.amazonaws.com/default/admix_imp?site=bizndonga&uuid="+one+"\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				secure = "1";				
				bidfloor  = 0.1d;
				ep = prod_admixer;
			}

			String  browserDetails  =   request.getHeader("User-Agent").toLowerCase();
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

			String param = "";
						
			String json_String = "{\"id\": \""+one.toString()+"\", "
			    + "            \"displaymanager\": \"Admaru SDK\",\"displaymanagerver\": \"0.1\","
				+ "            \"imp\": [ {\"id\": \"1\", \"banner\": { \"h\": "+h+", \"w\": "+w+" }, \"bidfloor\": "+bidfloor+",\"bidfloorcur\": \"USD\", \"secure\" : "+secure+"} ],"
				+ "            \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ]},"
				+ "            \"device\": { \"ip\": \""+getRemoteAddr(request)+"\",\"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\"}, "
				+ "            \"regs\": { \"ext\": { \"gdpr\": 0 } } "
//				+ "            \, "ext\": { \"clicktrack\": 1 }  "
						+ "}";
		
			param = json_String;
		
			URL url = new URL(prod_admixer);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type","application/json");
				
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
		
//			LogFacade.logRequestAsync("prod send:BidRequest3:"+site_id+":"+":"+ep+":"+param);
			LogFacade.logRequestAsync("prod send:BidRequest3:"+site_id+":"+":"+ep);
			 
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
			String line =null;
			StringBuffer outResult = new StringBuffer();
		
			while( (line = br.readLine()) != null) 
			{
				outResult.append(line);
			}

			String dspResult = outResult.toString();		
			LogFacade.logProviderAsync("json="+dspResult);
			
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
			//adm = adm.replace("${CLICK_TRACK_URL}", click_track_url);
			adm = adm + tracker;
			osw.close();
			br.close();
			conn.disconnect();
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok:"+price);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			adm = default_ad;
			LogFacade.logRequestAsync("prod receive:"+site_id+":no_ad");
		}
		
		LogFacade.logProviderAsync(one.toString()+":"+adm);
		response.getWriter().append(adm);
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String body = null;
	        StringBuilder stringBuilder = new StringBuilder();
	        BufferedReader bufferedReader = null;
	 
	        try {
	            InputStream inputStream = request.getInputStream();
	            if (inputStream != null) {
	                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	                char[] charBuffer = new char[128];
	                int bytesRead = -1;
	                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	                    stringBuilder.append(charBuffer, 0, bytesRead);
	                }
	            } else {
	                stringBuilder.append("");
	            }
	        } catch (IOException ex) {
	            throw ex;
	        } finally {
	            if (bufferedReader != null) {
	                try {
	                    bufferedReader.close();
	                } catch (IOException ex) {
	                    throw ex;
	                }
	            }
	        }
	 
	        body = stringBuilder.toString();
	        
	        //System.out.println(body);
	}
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }
}
