package com.admaru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
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

import ua_parser.Client;
import ua_parser.Parser;

public class NasmediaDisplay extends HttpServlet {

	private static final Logger log = LoggerFactory.getLogger(NasmediaDisplay.class);

	private static final long serialVersionUID = 1L;
           
    private final String prod_nasmedia = "http://adn.admixer.co.kr:10303/rtb/bidrequest/127";
       
    public NasmediaDisplay() {
        super();
    }
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		prebid(request, response);
	}
		
	public void printRequestParameters(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		System.out.println("\n\n=================================================");
		String impJsonstr = StrUtil.nvl( request.getParameter("imp"), "");
	
 
        Enumeration<String> parameterNames = request.getParameterNames();
 
        while (parameterNames.hasMoreElements()) {
 
            String paramName = parameterNames.nextElement();
            System.out.println(paramName);
 
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println(":" + paramValue);
                System.out.println("\n");
            }
 
        }
 		System.out.println("=================================================\n\n");

	}
	public void tagBased(HttpServletRequest request, HttpServletResponse response) {	}
	
	@SuppressWarnings("unchecked")
	public void prebid(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		String tracker = "";//impression
		String adm ="";
		String ep = prod_nasmedia;
		String site_name = "www.joongang.co.kr";
		String site_id = "2022-pc";//2022-mo
		String publisher = "2022";
		String adspace_id = request.getParameter("adspace_id");
		String tag_id = StrUtil.nvl( request.getParameter("tag_id"), "");
		
		String page = StrUtil.nvl( StrUtil.nvl(  request.getHeader("referer"), ""), "");
		String ref = "https://www.joongang.co.kr";
		
		double bidfloor = 0.4d; //USD
		double net_price = 0d;
		String buyerid = StrUtil.nvl( request.getParameter("buyerid"), "");
		
		String bidId = "";
		long w = 300l;
		long h = 250l;
		
		String adid = "";
		String crid = "";
		
		//imp=[{"bidId":"211064655608a8","bidfloor":0,"banner":{"format":[{"w":300,"h":250}]}}]
		String impJsonstr = StrUtil.nvl( request.getParameter("imp"), "");
		//System.out.println(">>>>>>"+impJsonstr);
		
	
		JSONParser parser2 = new JSONParser();
		Object obj2;

		try {
			obj2 = parser2.parse( impJsonstr );
			JSONArray jsonArray = (JSONArray) obj2;
			JSONObject jsonObj = (JSONObject) jsonArray.get( 0 );
			
			bidId = (String) jsonObj.get("bidId");
			bidfloor = ((Long) jsonObj.get("bidfloor")).doubleValue();
			
			JSONObject banner = (JSONObject) jsonObj.get("banner");
			JSONArray formatArray = (JSONArray) banner.get("format");
			JSONObject formatObj = (JSONObject) formatArray.get( 0 );
			
			w = (Long) formatObj.get("w");
			h = (Long) formatObj.get("h");
			
			//System.out.print("bidId="+bidId + ":bidfloor="+bidfloor + ":w="+w + ":h="+h);
			
		} catch (Exception e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		String secure = "1";// Flag to indicate if the impression requires secure HTTPS URL creative assets and markup, where 0 = non-secure, 1 = secure. 
		String devicetype = "";
		
		UUID one = UUID.randomUUID();
		String one_id = one.toString(); 		
		
		if ("/30349040/JA_PC_article/JA_PC_article_standard/standard_mid/336x280(1)".equals(adspace_id)) 
		{
			site_id = "2022-PC";
			
		} else if ("/30349040/JA_MO_article/mid/300x250".equals(adspace_id)) 
		{	
			site_id = "2022-MO";
		} 
		

		if (!"".equals(w) && !"".equals(h)) 
{			
		try 
		{
			secure = "1";
			String os = "unknown";
			String osv = "unknown";
			String model = "unknown";
			
			String browserDetails  =   request.getHeader("User-Agent");
			String old_url = request.getHeader("referer");

			boolean mobile1 = browserDetails.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
			boolean mobile2 = browserDetails.matches(".*(LG|SAMSUNG|Samsung).*"); 

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
			
			String param = "";
			
			//2022.07.28 0.1 usd로 변경 prebid에서 bidfloor가 0으로 와서 변경함
			bidfloor = 0.1;//"bidfloor": 0.10000000149011612
			 
			String json_String = "{\"id\": \""+one_id+"\", \"tmax\":1000, \"at\": 1,  \"cur\": [ \"USD\" ],  "
			    + "            \"displaymanager\": \"Admaru SDK\",\"displaymanagerver\": \"0.1\","			
				+ "            \"imp\": [ {\"id\": \"1\", \"banner\": { \"pos\": 0, \"h\": "+h+", \"w\": "+w+" }, \"tagid\":\""+tag_id+"\", \"bidfloor\": "+bidfloor+",\"bidfloorcur\": \"USD\", \"secure\" : "+secure+"} ],"
				+ "            \"site\": {\"id\": \""+site_id+"\", \"name\": \""+site_name+"\",\"domain\": \""+site_name+"\", \"cat\": [ \"IAB3-1\" ],  \"page\":\""+page+"\", \"mobile\":1, \"ref\":\""+ref+"\",  \"publisher\":{\"id\":\""+publisher+"\"} },"
				+ " \"device\": {  \"ip\": \""+getRemoteAddr(request)+ "\",  \"ua\":\""+browserDetails+"\",  \"devicetype\": "+ devicetype +", \"model\":\""+model+"\",\"os\":\""+os+"\",\"osv\":\""+osv+"\",\"carrier\":\"KT\", \"ifa\":\"\"}, "
				+ "            \"user\": { \"buyerid\": \""+buyerid+"\"} "
				//+ "            , \"source\": { \"ext\": { \"schain\" : { \"ver\":\"1.0\", \"complete\": 1,\"nodes\": [ { \"asi\":\"admaru.com\", \"sid\":\"938264\", \"rid\":\""+one_id+"\", \"hp\":1 } ]   } } } "
				+ "}";
			param = json_String;
			
			URL url = new URL(prod_nasmedia);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			
			//--header "x-openrtb-version: 2.5" --header "Content-Type: application/json
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("x-openrtb-version","2.5");
				
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
			osw.write(param);
			osw.flush();
		
			//LogFacade.logRequestAsync("prod send:NasmediaDisplay:"+site_id+":"+one_id+":"+adspace_id+":"+ep+":"+param);
			LogFacade.logRequestAsync("prod send:NasmediaDisplay:"+site_id+":"+one_id+":"+adspace_id+":"+ep);
			 
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
			String line =null;
			StringBuffer outResult = new StringBuffer();
		
			while( (line = br.readLine()) != null) 
			{
				outResult.append(line);
			}

			String dspResult = outResult.toString();		
			LogFacade.logProviderAsync("nasmediadisplay_json="+dspResult);
			
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(dspResult );
			JSONObject jsonObj = (JSONObject) obj;
	
			JSONArray seatbid = (JSONArray) jsonObj.get("seatbid");
			JSONObject x = (JSONObject) seatbid.get(0);
			JSONArray bidArray = (JSONArray)x.get("bid");
			JSONObject bid = (JSONObject) bidArray.get(0); 
			double price = (double) bid.get("price");
			net_price = price * 0.9d; //TODO redis적용
			
			if (net_price < 0.01) {
				net_price = 0d;
			}
			
			adid = (String) bid.get("adid");
			crid = (String) bid.get("crid");
			
			adm = (String) bid.get("adm");
			adm = adm.replace("${AUCTION_PRICE}", ""+price);
			//adm = adm.replace("${CLICK_TRACK_URL}", click_track_url);
			
			tracker = "<img src=\"https://p.admaru.net/sspserver/NasmediaTracker?adtype=display&p_id=1001&site_id=2022&size="+w+"x"+h+"&devicetype="+devicetype+"&adspace_id="+adspace_id+"&id="+one_id+"&p1="+price+"&p2="+net_price+"\" border=\"0\" height=\"1\" width=\"1\" alt=\"Advertisement\">";
			adm = adm + tracker;
			
			osw.close();
			br.close();
			conn.disconnect();
			
			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":ad_ok:"+"gross price="+price + ":net price="+net_price);

		}catch(Exception e) {
			//e.printStackTrace();
			//System.out.println(e.getMessage());
//			adm = default_ad;
			LogFacade.logRequestAsync("prod receive:"+site_id+":"+one_id+":"+adspace_id+":tag_id="+tag_id+":devicetype="+devicetype+":no_ad");
		}		
}

		//pb.js로 리턴
		JSONObject bid = new JSONObject();
			bid.put("id",  request.getParameter("id") );
			bid.put("price", net_price);
			//bid.put("price", 7.03125);
			bid.put("w", w);
			bid.put("h", h);
			bid.put("adm", adm);
			bid.put("adid",  adid);
			bid.put("impid", bidId);//t_imp[0].bidId
			bid.put("crid",  crid);
			
			bid.put("tagid", tag_id);
		
		JSONArray  vidArr = new JSONArray();
		vidArr.add(bid);
		
		JSONObject seatbid = new JSONObject();
		seatbid.put("bid", vidArr);
  		seatbid.put("seat", "1");
	
		JSONArray seatbidArr = new JSONArray();
		seatbidArr.add(seatbid);

		JSONObject data = new JSONObject();
		data.put("id",   request.getParameter("id")  );
  			data.put("bidderCode", "admaru");
  			data.put("bidder", "admaru");
  			data.put("seatbid", seatbidArr);
  			data.put("cur", "USD");
  
		response.setContentType("application/json");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	response.setHeader("Access-Control-Allow-Credentials","true");
    	response.getWriter().println(data.toJSONString());    	    
	}
	
	protected String getRemoteAddr(HttpServletRequest request){
        return (null != request.getHeader("X-FORWARDED-FOR")) ? request.getHeader("X-FORWARDED-FOR") : request.getRemoteAddr();
    }
}