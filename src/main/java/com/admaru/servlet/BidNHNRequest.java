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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admaru.util.StrUtil;
import com.atg.openssp.common.logadapter.LogFacade;

public class BidNHNRequest extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(BidNHNRequest.class);
	private static final String REDIS_CON_URL = "redis://127.0.0.1:6379/0";
	private static final long serialVersionUID = 1L;
       	
    public BidNHNRequest() {
        super();
    }

    private final String prod_nhnace = "https://adx-exchange.toast.com/rtb/admaru";
    
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
    
//	@SuppressWarnings("unchecked")
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//	{	
//		String site_id = StrUtil.nvl(request.getParameter("pub_id"), "2022");
//		String publisher = "";
//		String adspace_id = request.getParameter("adspace_id");
//		String tag_id = request.getParameter("tag_id"); //TODO 한개뿐인가? prebid.js에서 여러개를 넘겨줘야 하지 않을까?
//		String page = request.getHeader("referer");
//		double bidfloor = 0.00d; //USD
//		double net_price = 0d;
//		
//		String bidId = "";
//		long w = 0l;
//		long h = 0l;
//		
//		String adid = "";
//		String crid = "";
//		
//		//imp=[{"bidId":"211064655608a8","bidfloor":0,"banner":{"format":[{"w":300,"h":250}]}}]
//		String impJsonstr = StrUtil.nvl( request.getParameter("imp"), "");
//		//System.out.println(">>>>>>"+impJsonstr);
//	
//		JSONParser parser2 = new JSONParser();
//		Object obj2;
//
//		try {
//			obj2 = parser2.parse( impJsonstr );
//			JSONArray jsonArray = (JSONArray) obj2;
//			JSONObject jsonObj = (JSONObject) jsonArray.get( 0 );
//			
//			bidId = (String) jsonObj.get("bidId");
//			bidfloor = ((Long) jsonObj.get("bidfloor")).doubleValue();
//			
//			JSONObject banner = (JSONObject) jsonObj.get("banner");
//			JSONArray formatArray = (JSONArray) banner.get("format");
//			JSONObject formatObj = (JSONObject) formatArray.get( 0 );
//			
//			w = (Long) formatObj.get("w");
//			h = (Long) formatObj.get("h");
//			
//			//System.out.print("bidId="+bidId + ":bidfloor="+bidfloor + ":w="+w + ":h="+h);
//			
//		} catch (Exception e1) {
//			System.err.println(e1.getMessage());
//			e1.printStackTrace();
//		}
//		
////		if (bidfloor == 0.00d)
////			bidfloor = 0.02d;
//		
//		bidfloor = 0.00d; // nhn ace측 요청 5.25
//		
//		String ref  = "";
//		String nhnck = ""; //getnhnck(pcid);
//		String adm ="";
//		String site_name = "";
//		String default_ad = "";
//		String tracker = "";//impression
//		
//		String ep = "";
//		String secure = "1";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
//		               // If omitted, the secure state is unknown, but non-secure HTTP support can be assumed.
//		
//		String devicetype = "";
//		
//		UUID one = UUID.randomUUID();
//		String one_id = one.toString(); 		
//		
//		
//		/*
//		woman.donga.com   300X250           1001
//		shindonga.donga.com 300X250         1002
//		woman.donga.com 300X250             1003
//		bizn.donga.com 300X250              1004
//		*/
//		
//		if ("1001".equals(site_id)) 
//		{
//			publisher = "2023";
//			site_name = "woman.donga.com";
//			ref  = "https://woman.donga.com";
//			//default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Womandonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466493490-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466493490-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466493490-0'); }); </script> </div></body></html>";
//			//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.265500376;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
//			
//		} else if ("1002".equals(site_id)) 
//		{	
//			publisher = "2023";			
//			site_name = "shindonga.donga.com";
//			ref  = "https://shindonga.donga.com";
//			//default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Shindonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466445515-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466445515-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466445515-0'); }); </script> </div></body></html>"; 
//			//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267336737;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
//			
//		} else if ("1003".equals(site_id)) 
//		{
//			publisher = "2023";
//			site_name = "weekly.donga.com";
//			ref  = "https://weekly.donga.com";
//			//default_ad = "<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script> <script> window.googletag = window.googletag || {cmd: []}; googletag.cmd.push(function() { googletag.defineSlot('/1249652/Weeklydonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581466403810-0').addService(googletag.pubads()); googletag.pubads().enableSingleRequest(); googletag.enableServices(); }); </script></head><body> <div id='div-gpt-ad-1581466403810-0' style='width: 300px; height: 250px;'> <script> googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581466403810-0'); }); </script> </div></body></html>"; 
//			//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267655389;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
//		} else if ("1004".equals(site_id)) 
//		{
//			publisher = "2023";
//			site_name = "bizn.donga.com";
//			ref  = "https://bizn.donga.com";
//			//default_ad ="<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script><script>  window.googletag = window.googletag || {cmd: []};  googletag.cmd.push(function() { googletag.defineSlot('/1249652/BiznDonga_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1581470822767-0').addService(googletag.pubads());    googletag.pubads().enableSingleRequest();    googletag.enableServices();  });</script></head><body><div id='div-gpt-ad-1581470822767-0' style='width: 300px; height: 250px;'>  <script>    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1581470822767-0'); });  </script></div></body></html>";
//			//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.267336761;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
//		} 
//		else if ("2022".equals(site_id))
//		{
//			publisher = "2022";
//			site_name = "joongang.co.kr";
//			ref  = "https://www.joongang.co.kr";
//			//default_ad ="<html><head><script async src=\"https://securepubads.g.doubleclick.net/tag/js/gpt.js\"></script><script>  window.googletag = window.googletag || {cmd: []};  googletag.cmd.push(function() { googletag.defineSlot('/1249652/Joins_OSV_Mobile_WEB_300x250_01_House', [300, 250], 'div-gpt-ad-1582579510749-0').addService(googletag.pubads());    googletag.pubads().enableSingleRequest();    googletag.enableServices();  });</script></head><body><div id='div-gpt-ad-1582579510749-0' style='width: 300px; height: 250px;'>  <script>    googletag.cmd.push(function() { googletag.display('div-gpt-ad-1582579510749-0'); });  </script></div></body></html>";
//			//tracker = "<IMG SRC=\"https://ad.doubleclick.net/ddm/trackimp/N6103.1355588DOUBLECLICK.COMBID1/B23721879.268633060;dc_trk_aid=460485115;dc_trk_cid=127344156;ord=[timestamp];dc_lat=;dc_rdid=;tag_for_child_directed_treatment=;tfua=?\" BORDER=\"0\" HEIGHT=\"1\" WIDTH=\"1\" ALT=\"Advertisement\">";
//		} 		
//		
//		//2022.05.16 당분간 쿠키값 확인 제외	
//		if (/* !"".equals(nhnck) && */!"".equals(w) && !"".equals(h)) 
//{			
//		try 
//		{
//			secure = "1";
//			ep = prod_nhnace;
//
//			String browserDetails  =   request.getHeader("User-Agent");
//			String old_url = request.getHeader("referer");
//
//			boolean mobile1 = browserDetails.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
//			boolean mobile2 = browserDetails.matches(".*(LG|SAMSUNG|Samsung).*"); 
//
//			if (mobile1 || mobile2) {
//				devicetype ="1";
//			} else {
//				devicetype ="2";
//			}
//
//
//			String param = "";
//		
//			String json_String = "{\"id\": \""+one_id+"\",  \"at\": 1,  \"cur\": [ \"USD\" ], \"test\":0, "
//				+ "            \"imp\": [ {\"id\": \"1\", \"banner\": { \"pos\": 0, \"h\": "+h+", \"w\": "+w+" }, \"tagid\":\""+tag_id+"\", \"bidfloor\": "+bidfloor+",\"bidfloorcur\": \"USD\", \"secure\" : "+secure+"} ],"
//				+ "            \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ],  \"page\":\""+page+"\", \"ref\":\""+ref+"\", \"mobile\":1, \"publisher\":{\"id\":\""+publisher+"\"} },"
//				+ "            \"device\": { \"ip\": \""+getRemoteAddr(request)+"\",\"ua\":\""+browserDetails+"\", \"devicetype\": "+ devicetype+"}, "
//				+ "            \"user\": { \"buyerid\": \""+nhnck+"\"} "
//				+ "            , \"source\": { \"ext\": { \"schain\" : { \"ver\":\"1.0\", \"complete\": 1,\"nodes\": [ { \"asi\":\"admaru.com\", \"sid\":\"938264\", \"rid\":\""+one_id+"\", \"hp\":1 } ]   } } } "
//				+ "}";
//						
//			param = json_String;
//			
//			URL url = new URL(ep);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			
//			//--header "x-openrtb-version: 2.5" --header "Content-Type: application/json
//			conn.setRequestProperty("Content-Type","application/json");
//			conn.setRequestProperty("x-openrtb-version","2.5");
//				
//			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
//			osw.write(param);
//			osw.flush();
//		
//			LogFacade.logRequestAsync("prod send:"+site_id+":"+one_id+":"+adspace_id+":"+ep+":"+param);
//			 
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//		
//			String line =null;
//			StringBuffer outResult = new StringBuffer();
//		
//			while( (line = br.readLine()) != null) 
//			{
//				outResult.append(line);
//			}
//
//			String dspResult = outResult.toString();		
//			//LogFacade.logProviderAsync("json="+dspResult);
//			
//			JSONParser parser = new JSONParser();
//			Object obj = parser.parse(dspResult );
//			JSONObject jsonObj = (JSONObject) obj;
//	
//			JSONArray seatbid = (JSONArray) jsonObj.get("seatbid");
//			JSONObject x = (JSONObject) seatbid.get(0);
//			JSONArray bidArray = (JSONArray)x.get("bid");
//			JSONObject bid = (JSONObject) bidArray.get(0); 
//			double price = (double) bid.get("price");
//			net_price = price * 0.9d; //TODO redis적용
//			
//			if (net_price < 0.01) {
//				net_price = 0d;
//			}
//			
//			adid = (String) bid.get("adid");
//			crid = (String) bid.get("crid");
//			
//			adm = (String) bid.get("adm");
//			adm = adm.replace("${AUCTION_PRICE}", ""+price);
//			//adm = adm.replace("${CLICK_TRACK_URL}", click_track_url);
//			
//			tracker =                                    "<img src=\"https://p.admaru.net/sspserver/nhnace_imp?p_id=1000&site_id="+site_id+"&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one_id+"&p1="+price+"&p2="+net_price+"\" border=\"0\" height=\"1\" width=\"1\" alt=\"Advertisement\">";
//  tracker = tracker + "<img src=\"https://69bweelsej.execute-api.ap-northeast-2.amazonaws.com/default/vast_imp?p_id=1000&site_id="+site_id+"&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one_id+"&p1="+price+"&p2="+net_price+"\" border=\"0\" height=\"1\" width=\"1\" alt=\"Advertisement\">";
//			 
//			adm = adm + tracker;
//			
//			osw.close();
//			br.close();
//			conn.disconnect();
//			
//			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":ad_ok:"+"gross price="+price + ":net price="+net_price);
//
//		}catch(Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			adm = default_ad;
//			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":no_ad");
//		}		
//		
//		//LogFacade.logProviderAsync(one_id+":"+adm);						
//
//}
//
//		//pb.js로 리턴
//		JSONObject bid = new JSONObject();
//			bid.put("id",  request.getParameter("id") );
//			bid.put("price", net_price);
//			//bid.put("price", 7.03125);
//			bid.put("w", w);
//			bid.put("h", h);
//			bid.put("adm", adm);
//			bid.put("adid",  adid);
//			bid.put("impid", bidId);//t_imp[0].bidId
//			bid.put("crid",  crid);
//			bid.put("nhnck", nhnck);
//			bid.put("tagid", tag_id);
//		
//		JSONArray  vidArr = new JSONArray();
//		vidArr.add(bid);
//		
//		JSONObject seatbid = new JSONObject();
//		seatbid.put("bid", vidArr);
//  		seatbid.put("seat", "1");
//	
//		JSONArray seatbidArr = new JSONArray();
//		seatbidArr.add(seatbid);
//
//		JSONObject data = new JSONObject();
//		data.put("id",   request.getParameter("id")  );
//  			data.put("bidderCode", "admaru");
//  			data.put("bidder", "admaru");
//  			data.put("seatbid", seatbidArr);
//  			data.put("cur", "USD");
//  
//		response.setContentType("application/json");
//    	response.setCharacterEncoding("UTF-8");
//    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
//    	response.setHeader("Access-Control-Allow-Credentials","true");
//    	response.getWriter().println(data.toJSONString());
//    	
//    	//LogFacade.logProviderAsync("to_pbjs_res:"+one_id+":"+data.toJSONString());
//	}
			
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }

//	public String getnhnck(String pcid) throws IOException {
//		
//		String result = "";
//		
//		GenericObjectPool<StatefulRedisConnection<String, String>> pool = nonClusterPoolUsage();
//	
//		try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) 
//		{
//			result = connection.sync().get(pcid);
//			System.out.println("nhnck="+result);
//
//			if ("".equals(result) || result == null) {
//				String srcUrl = "https://cm-exchange.toast.com/bi/pixel?cm_mid=1330086872&toast_push";
//				String redirectUrl = getRedirectUrl(srcUrl);
//				//https://p2.admaru.net/UserSync/uid?p_id=1000&u_id=5WFNYYHEU3WF24E2C9JIMFQFA
//	
//				result = redirectUrl.substring(50);
//					
//				connection.sync().set(pcid, result);
//			}
//		
//		} catch (RedisConnectionException e) {
//			System.out.println(String.format("Failed to connect to Redis server: %s", e));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		pool.close();
//		
//		return result;
//	}
	
//	public static String getRedirectUrl(String srcUrl) throws IOException {
//    	//Note that 4xx and 5xx do not raise an exception
//	    HttpURLConnection con = (HttpURLConnection) new URL(srcUrl).openConnection();
//	    con.setRequestMethod("GET");
//	    con.setInstanceFollowRedirects(false); //Setting not to redirect automatically
//	    con.connect();
//	    //Get Location header from HTTP response
//	    String location = con.getHeaderField("location");
//	    con.disconnect();
//	    return location;
//	}
//	public static GenericObjectPool<StatefulRedisConnection<String, String>> nonClusterPoolUsage() {
//		RedisClient client = RedisClient.create(REDIS_CON_URL);
//		client.setOptions(ClientOptions.builder().autoReconnect(true).build());
//
//		return ConnectionPoolSupport.createGenericObjectPool(() -> client.connect(), createPoolConfig());
//	}
//
//	public static GenericObjectPool<StatefulRedisClusterConnection<String, String>> useClusterPoolUsage() {
//		RedisClusterClient clusterClient = RedisClusterClient.create(REDIS_CON_URL);
//		clusterClient.setOptions(ClusterClientOptions.builder().autoReconnect(true).build());
//
//		return ConnectionPoolSupport.createGenericObjectPool(() -> clusterClient.connect(), createPoolConfig());
//	}
//
//	public static GenericObjectPoolConfig createPoolConfig() {
//		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
//		poolConfig.setMaxTotal(20);
//		poolConfig.setMaxIdle(10);
//		poolConfig.setBlockWhenExhausted(true);
//		poolConfig.setMaxWaitMillis(1000);
//		poolConfig.setMinIdle(5);
//		return poolConfig;
//	}
	
}