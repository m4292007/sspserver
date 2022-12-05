package com.admaru.servlet;

import java.io.IOException;
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
import com.atg.openssp.common.logadapter.LogFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MezzoVideo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger log = LoggerFactory.getLogger(MezzoVideo.class);

    public MezzoVideo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String adm = "";
		String site_id = "2022";
		//UUID one = UUID.randomUUID();

		try 
		{		 
			String url = "https://mtag.mman.kr/get_ad.mezzo/?e_version=2&a_publisher=1640&a_media=32572&a_section=805899&i_response_format=xml&d_used_type=api";
			
			CloseableHttpClient httpClient = HttpClients.createDefault();
		    HttpGet httpGet = new HttpGet(url);
			//httpGet.addHeader("User-Agent", browserDetails);
			httpGet.addHeader("Content-type", "application/xml");
			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        String xml = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
	        httpClient.close();
	        
//			System.out.println("before="+xml);
			
			String tracker          = "<Impression><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=impression ]]></Impression>";
			//String skip_tracker     = "<Tracking event=\"skip\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=skip ]]></Tracking>";	
			String complete_tracker = "<Tracking event=\"complete\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=complete ]]></Tracking>";
			String progress_tracker = "<Tracking event=\"progress\" offset=\"00:00:05\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=progress ]]></Tracking>";
			//String click_tracker    = "<ClickTracking><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=ClickTracking ]]></ClickTracking>";
			
			String start_tracker         = "<Tracking event=\"start\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=start ]]></Tracking>";
			String midpoint_tracker      = "<Tracking event=\"midpoint\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=midpoint ]]></Tracking>";
			String firstQuartile_tracker = "<Tracking event=\"firstQuartile\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=firstQuartile ]]></Tracking>";
			String thirdQuartile_tracker = "<Tracking event=\"thirdQuartile\"><![CDATA[ https://p.admaru.net/sspserver/MezzoTracker?site_id="+site_id+"&etype=thirdQuartile ]]></Tracking>";
			
			xml = xml.replace("</Impression>","</Impression>"+tracker);
			xml = xml.replace("<Tracking event=\"start\">", start_tracker+"<Tracking event=\"start\">");
			xml = xml.replace("<Tracking event=\"firstQuartile\">", firstQuartile_tracker+"<Tracking event=\"firstQuartile\">");
			xml = xml.replace("<Tracking event=\"midpoint\">", midpoint_tracker+"<Tracking event=\"midpoint\">");
			xml = xml.replace("<Tracking event=\"thirdQuartile\">", thirdQuartile_tracker+"<Tracking event=\"thirdQuartile\">");
			xml = xml.replace("<Tracking event=\"complete\">", complete_tracker+"<Tracking event=\"complete\">");
			//xml = xml.replace("<Tracking event=\"skip\">", skip_tracker+"<Tracking event=\"skip\">");
			xml = xml.replace("<Tracking event=\"progress\" offset=\"00:00:05\">", progress_tracker+"<Tracking event=\"progress\" offset=\"00:00:05\">");
			
						
			////////////////////////////////////
			adm = xml;
			//LogFacade.logRequestAsync("mezzo_vast=>"+adm);
		} catch(Exception e) {
			adm = "<VAST version=\"4.0\"><Error>no ads2</Error></VAST>";
			
		}		
		//System.out.println(adm);
		
		response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	response.setHeader("Access-Control-Allow-Credentials","true");
    	response.getWriter().println(adm);
	}
}
