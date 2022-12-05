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

import com.atg.openssp.common.logadapter.LogFacade;


public class BidRequest extends HttpServlet {
	
	private static final Logger log = LoggerFactory.getLogger(BidRequest.class);
	
	private static final long serialVersionUID = 1L;
       	
    public BidRequest() {
        super();
    }

    private final String test_nasmedia = "http://adn2.admixer.co.kr:25847/rtb/bidrequest/1044";
    private final String cpm_nasmedia = "http://adp.admixer.co.kr/rtb/bidrequest/71";
    private final String cpc_nasmedia = "http://adp.admixer.co.kr/rtb/bidrequest/76";


    /**
-       호출 URL : http://adp.admixer.co.kr/rtb/bidrequest/71
-       리포트 API Key : r3ftiu1q29h5w4e7k1f3l961w48hje7g
-       광고 노출 사이즈 : 302*50, 320*480, 300*250
     */
    // test 1. elasticache를사용할대와
    //         s.admaru.net에 redis를같이사용할때 처리시간 비교
    // redis 로 읽어와야 할것 floor price
    // redis 에 기록해야할 것 : app_key별 ad call, bid, no bid
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//start time 
		
		// 1. 톰캣 filtering
		
		//2. redis get data
		
		//3. dynamodb insert
		//4. referer 로그 남겨서 정상적인 광고호출인지 인위적인지 확인
		
		
		String adm ="";
		String site_name = "";
		String default_ad = "";
		String tracker = "";
		String site_id = "";
		String prod_nasmedia = "";
		int secure = 0;// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		               // If omitted, the secure state is unknown, but non-secure HTTP support can be assumed.
		
		int bidfloor = 1;//2020.02.22 by 김부장요청
		UUID one = UUID.randomUUID();
		//날짜 
		try 
		{
			String w = request.getParameter("w");
			String h = request.getParameter("h");
			site_id = request.getParameter("s");
			
			if ("1001".equals(site_id)) 
			{
				site_name = "woman.donga.com";
				default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Womandonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466493490-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466493490-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466493490-0'); }); </script> </div></body></html>";
				tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.265500376;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				bidfloor  = 40;
				prod_nasmedia = cpc_nasmedia;
				
			} else if ("1002".equals(site_id)) 
			{				
				site_name = "shindonga.donga.com";
				default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Shindonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466445515-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466445515-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466445515-0'); }); </script> </div></body></html>"; 
				tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267336737;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				prod_nasmedia = cpm_nasmedia;
				bidfloor  = 40;
				
			} else if ("1003".equals(site_id)) 
			{
				site_name = "weekly.donga.com";
				default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Weeklydonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466403810-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466403810-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466403810-0'); }); </script> </div></body></html>"; 
				tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267655389;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				bidfloor  = 40;
				prod_nasmedia = cpc_nasmedia;
			} else if ("1004".equals(site_id)) 
			{
				site_name = "bizn.donga.com";
				default_ad ="<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script><script>  window.googletag = window.googletag || {cmd: []};  googletag.cmd.push(function() { googletag.defineSlot('/1249652/BiznDonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581470822767-0').addService(googletag.pubads());    googletag.pubads().enableSingleRequest();    googletag.enableServices();  });</script></head><body><div id='div-gpt-ad-1581470822767-0' style='width: 300px; height: 250px;'>  <script>    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581470822767-0'); });  </script></div></body></html>";
				tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267336761;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				prod_nasmedia = cpm_nasmedia;
				bidfloor  = 40;
			} 
			else if ("1005".equals(site_id))
			{
				site_name = "joongang.joins.com";
				default_ad ="<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script><script>  window.googletag = window.googletag || {cmd: []};  googletag.cmd.push(function() { googletag.defineSlot('/1249652/Joins_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1582579510749-0').addService(googletag.pubads());    googletag.pubads().enableSingleRequest();    googletag.enableServices();  });</script></head><body><div id='div-gpt-ad-1582579510749-0' style='width: 300px; height: 250px;'>  <script>    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1582579510749-0'); });  </script></div></body></html>";
				tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.268633060;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
				//bidfloor  = 1000;
				bidfloor  = 300;
				prod_nasmedia = cpm_nasmedia;
				secure = 1;
			} 		

			String  browserDetails  =   request.getHeader("User-Agent").toLowerCase();
			String old_url = request.getHeader("referer");
			
			//LogFacade.logProviderAsync(one.toString()+":"+old_url+":"+browserDetails);
			
			String d = browserDetails.substring( browserDetails.indexOf("(")+1, browserDetails.indexOf(")"));
			String[] e = d.split(";");

			String os = "unknown";
			String osv = "unknown";
			String model = "unknown";

			try {
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

			// bidrequest.id생성
			String param = "";
			String cpm_json_String = "{\"id\": \""+one.toString()+"\", "
					+ "            \"imp\": [ {\"id\": \"1\", \"banner\": { \"h\": "+h+", \"w\": "+w+" }, \"bidfloor\": "+bidfloor+",\"bidfloorcur\": \"KRW\", \"secure\" : "+secure+"} ],"
					+ "            \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB1-1\" ]},"
					+ "            \"device\": { \"ip\": \""+getRemoteAddr(request)+"\",\"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"}"
							+ "}";
			
			String cpc_json_String = "{\"id\": \""+one.toString()+"\", "
					+ "            \"imp\": [ {\"id\": \"1\", \"banner\": { \"h\": "+h+", \"w\": "+w+" }, \"bidfloor\": "+bidfloor+",\"bidfloorcur\": \"KRW\", \"secure\" : "+secure+"} ],"
					+ "            \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB1-1\" ]},"
					+ "            \"device\": { \"ip\": \""+getRemoteAddr(request)+"\",\"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"},"
					+ "            \"ext\" : { \"contracttype\": 1 } " 
							+ "}";
			
			if ("1001".equals(site_id) || "1003".equals(site_id)) {
				param = cpc_json_String;
			} else {
				param = cpm_json_String;
			}
			
			URL url = new URL(prod_nasmedia);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type","application/json");
					
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
			
			LogFacade.logRequestAsync("prod send:"+site_id+":"+":"+prod_nasmedia+":"+param);
			//dynamodb에 기록		
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
			adm = adm.replace("<body>", "<body>"+tracker);
					
			osw.close();
			br.close();
			conn.disconnect();
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok");
			
		}catch(Exception e) {
			//e.printStackTrace();
			//System.err.println("bidrequest:218line=>"+e.getMessage());
			
			adm = default_ad;//default ad도 impression카운트 해야하나?
			LogFacade.logRequestAsync("prod receive:"+site_id+":no_ad");
			//LogFacade.logRequestAsync("no ad from dsp, default ad ="+site_id);
		}
		LogFacade.logProviderAsync(one.toString()+":"+adm);//광고소재 로그
		response.getWriter().append(adm);
		
		//end time
		//log time lapse
		
	}

	 
	
	public String sendREST(String sendUrl, String jsonValue) {
		String inputLine = null;
		StringBuffer outResult = new StringBuffer();
		
		try 
		{
			
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
			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return outResult.toString();
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
