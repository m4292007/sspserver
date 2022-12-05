package com.admaru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdCall extends HttpServlet {
	
	//private static final Logger log = LoggerFactory.getLogger(BidRequest.class);
	
	private static final long serialVersionUID = 1L;
       	
    public AdCall() {
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
		String adm2 = "{\"version\":\"3.0.0\",\"tags\":[{\"uuid\":\"24e4c68413d531\",\"tag_id\":13144370,\"auction_id\":\"1072667135479736495\",\"nobid\":false,\"no_ad_url\":\"https://sin3-ib.adnxs.com/it?an_audit=0&referrer=https%3A%2F%2Fjsfiddle.net%2F&e=wqT_3QKQCqAQBQAAAwDWAAUBCOSy-44GEK_RhaS5orjxDhjlqJeCnfb362cqNgkAAAkCABEJBywAABkAAACA61HgPyEREgApEQkAMREb8GkwsqKiBjjtSEDtSEgAUABYnPFbYABotc95eACAAQGKAQCSAQNVU0SYAawCoAH6AagBAbABALgBAcABAMgBAtABANgBAOABAPABAIoCO3VmKCdhJywgMjUyOTg4NSwgMTY0MTk5NDU5Nik7AR0scicsIDk2ODQ2MDM1Nh4A8P2SAu0DIU9Vd3o2QWl1c0s0S0VOT0JseTRZQUNDYzhWc3dBRGdBUUFSSTdVaFFzcUtpQmxnQVlLMEJhQUJ3QUhnQWdBRUlpQUVBa0FFQm1BRUJvQUVCcUFFRHNBRUF1UUVwaTRpREFBRGdQOEVCS1l1SWd3QUE0RF9KQVRRLUV4ZE02LVFfMlFFQUFBQUFBQUR3UC1BQkFQVUJBQUFBQUpnQ0FLQUNBTFVDQUFBQUFMMENBQUFBQU1BQ0FNZ0NBTkFDQU5nQ0FPQUNBT2dDQVBnQ0FJQURBWmdEQWJvRENWTkpUak02TkRjMk5PQURyaTJJQkFDUUJBQ1lCQUhCQgVNCQEIeVFRCQkBARROZ0VBUEURlSxBQUFDSUJad2xxUVUBDQmoCDdFRgEKCQEIREJCHT8AeRUoDEFBQU4yKAAAWi4oALg0QVhRaGdQd0JlMzgyZ0w0QmQyMG1nR0NCZ05WVTBTSUJnQ1FCZ0dZQmdDaEJnQQFONEFBT0FfcUFZQnNnWWtDHXQARR0MAEcdDABJHQw4dUFZS5oClQEhelEta3FRNvEBJG5QRmJJQVFvQUQR-FhEZ1B6b0pVMGxPTXpvME56WTBRSzR0UxFRDFBBX1URDAxBQUFXHQwAWR0MAGEdDABjHQwQZUFDSkEdEPA-wgIvaHR0cDovL3ByZWJpZC5vcmcvZGV2LWRvY3MvZ2V0dGluZy1zdGFydGVkLmh0bWzYAvfpA-ACrZhI6gIVATzwQ3M6Ly9qc2ZpZGRsZS5uZXQvgAMAiAMBkAMAmAMXoAMBqgMAwAOsAsgDANgD_sRG4AMA6AMA-AMBgAQAkgQNL3V0L3YzDX7wYZgEAKIEDTM5LjEyMS4yMDAuMjmoBP_2NrIEEggEEAQYrAIg-gEoASgCMAA4ArgEAMAEAMgEANIEDjkzMjUjU0lOMzo0NzY02gQCCADgBAHwBNOBly6IBQGYBQCgBf______AQMUAcAFAMkFiR0U8D_SBQkJCQxwAADYBQHgBQHwBQH6BQQIABAAkAYAmAYAuAYAwQYJIyjwv9AG9S_aBhYKEAkRGQFcEAAYAOAGAfIGAggAgAcBiAcAoAcBugcPAUhIGAAgADAAOOsPQADIB57SBdIHDRV0ATgI2gcGCSdI4AcA6gcCCADwB_317QGKCAIQAA..&s=e30ba6457e9dfbd3dbe569ad94311ce36e4cf923\",\"timeout_ms\":0,\"ad_profile_id\":1182765,\"rtb_video_fallback\":false,\"ads\":[{\"cpm\":0.500000,\"cpm_publisher_currency\":0.500000,\"publisher_currency_code\":\"$\",\"content_source\":\"rtb\",\"ad_type\":\"banner\",\"buyer_member_id\":9325,\"advertiser_id\":2529885,\"creative_id\":96846035,\"media_type_id\":1,\"media_subtype_id\":1,\"brand_category_id\":0,\"brand_id\":1,\"client_initiated_ad_counting\":true,\"rtb\":{\"banner\":{\"content\":\"<!-- Creative 96846035 served by Member 9325 via AppNexus --><a href=\\\"https://sin3-ib.adnxs.com/click?AAAAAAAA4D8AAAAAAADgPwAAAIDrUeA_AAAAAAAA4D8AAAAAAADgP69ogZQT4eIOZdRF0LHf12dk2d5hAAAAADKRyABtJAAAbSQAAAIAAADTwMUFnPgWAAAAAABVU0QAVVNEACwB-gC1ZwAAAAABAQMCAAAAALwAlB5ucQAAAAA./bcr=AAAAAAAA8D8=/cnd=%21zQ-kqQiusK4KENOBly4YnPFbIAQoADEAAAAAAADgPzoJU0lOMzo0NzY0QK4tSQAAAAAAAPA_UQAAAAAAAAAAWQAAAAAAAAAAYQAAAAAAAAAAaQAAAAAAAAAAcQAAAAAAAAAAeACJAQAAAAAAAAAA/cca=OTMyNSNTSU4zOjQ3NjQ=/bn=92446/clickenc=http%3A%2F%2Fprebid.org%2Fdev-docs%2Fgetting-started.html\\\" target=\\\"_blank\\\"><img width=\\\"300\\\" height=\\\"250\\\" style=\\\"border-style: none\\\" src=\\\"https://vcdn.adnxs.com/p/creative-image/27/c0/52/67/27c05267-5a6d-4874-834e-18e218493c32.png\\\" /></a><iframe src=\\\"https://acdn.adnxs.com/dmp/async_usersync.html?gdpr=0&seller_id=9325&pub_id=1193043\\\" width=\\\"1\\\" height=\\\"1\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" marginheight=\\\"0\\\" marginwidth=\\\"0\\\" topmargin=\\\"0\\\" leftmargin=\\\"0\\\" style=\\\"position:absolute;overflow:hidden;clip:rect(0 0 0 0);height:1px;width:1px;margin:-1px;padding:0;border:0;\\\"></iframe><script>try {!function(){function e(e,t){return\\\"function\\\"==typeof __an_obj_extend_thunk?__an_obj_extend_thunk(e,t):e}function t(e,t){\\\"function\\\"==typeof __an_err_thunk&&__an_err_thunk(e,t)}function n(e,t){if(\\\"function\\\"==typeof __an_redirect_thunk)__an_redirect_thunk(e);else{var n=navigator.connection;navigator.__an_connection&&(n=navigator.__an_connection),window==window.top&&n&&n.downlinkMax<=.115&&\\\"function\\\"==typeof HTMLIFrameElement&&HTMLIFrameElement.prototype.hasOwnProperty(\\\"srcdoc\\\")?(window.__an_resize=function(e,t,n){var r=e.frameElement;r&&\\\"__an_if\\\"==r.getAttribute(\\\"name\\\")&&(t&&(r.style.width=t+\\\"px\\\"),n&&(r.style.height=n+\\\"px\\\"))},document.write('<iframe name=\\\"__an_if\\\" style=\\\"width:0;height:0\\\" srcdoc=\\\"<script type=\\\\'text/javascript\\\\' src=\\\\''+e+\\\"&\\\"+t.bdfif+\\\"=1'></sc\\\"),document.write('ript>\\\" frameborder=\\\"0\\\" scrolling=\\\"no\\\" marginheight=0 marginwidth=0 topmargin=\\\"0\\\" leftmargin=\\\"0\\\" allowtransparency=\\\"true\\\"></iframe>')):document.write('<script language=\\\"javascript\\\" src=\\\"'+e+'\\\"></scr'+'ipt>')}};var r=function(e){this.rdParams=e};r.prototype={constructor:r,walkAncestors:function(e){try{if(!window.location.ancestorOrigins)return;for(var t=0,n=window.location.ancestorOrigins.length;n>t;t++)e.call(null,window.location.ancestorOrigins[t],t)}catch(r){\\\"undefined\\\"!=typeof console}return[]},walkUpWindows:function(e){var t,n=[];do try{t=t?t.parent:window,e.call(null,t,n)}catch(r){return\\\"undefined\\\"!=typeof console,n.push({referrer:null,location:null,isTop:!1}),n}while(t!=window.top);return n},getPubUrlStack:function(e){var n,r=[],o=null,i=null,a=null,c=null,d=null,s=null,u=null;for(n=e.length-1;n>=0;n--){try{a=e[n].location}catch(l){\\\"undefined\\\"!=typeof console,t(l,\\\"AnRDModule::getPubUrlStack:: location\\\")}if(a)i=encodeURIComponent(a),r.push(i),u||(u=i);else if(0!==n){c=e[n-1];try{d=c.referrer,s=c.ancestor}catch(l){\\\"undefined\\\"!=typeof console,t(l,\\\"AnRDModule::getPubUrlStack:: prevFrame\\\")}d?(i=encodeURIComponent(d),r.push(i),u||(u=i)):s?(i=encodeURIComponent(s),r.push(i),u||(u=i)):r.push(o)}else r.push(o)}return{stack:r,detectUrl:u}},getLevels:function(){var e=this.walkUpWindows(function(e,n){try{n.push({referrer:e.document.referrer||null,location:e.location.href||null,isTop:e==window.top})}catch(r){n.push({referrer:null,location:null,isTop:e==window.top}),\\\"undefined\\\"!=typeof console,t(r,\\\"AnRDModule::getLevels\\\")}});return this.walkAncestors(function(t,n){e[n].ancestor=t}),e},getRefererInfo:function(){var e=\\\"\\\";try{var n=this.getLevels(),r=n.length-1,o=null!==n[r].location||r>0&&null!==n[r-1].referrer,i=this.getPubUrlStack(n);e=this.rdParams.rdRef+\\\"=\\\"+i.detectUrl+\\\"&\\\"+this.rdParams.rdTop+\\\"=\\\"+o+\\\"&\\\"+this.rdParams.rdIfs+\\\"=\\\"+r+\\\"&\\\"+this.rdParams.rdStk+\\\"=\\\"+i.stack+\\\"&\\\"+decodeURI(this.rdParams.rdQs)}catch(a){e=\\\"\\\",\\\"undefined\\\"!=typeof console,t(a,\\\"AnRDModule::getRefererInfo\\\")}return e}};var o=function(n){var o=\\\"\\\";try{n=e(n,0);var i=e(new r(n),1);return i.getRefererInfo()}catch(a){o=\\\"\\\",\\\"undefined\\\"!=typeof console,t(a,\\\"AnRDModule::executeRD\\\")}return o};;var c=\\\"https://sin3-ib.adnxs.com/rd_log?an_audit=0&referrer=https%3A%2F%2Fjsfiddle.net%2F&e=wqT_3QKeDKAeBgAAAwDWAAUBCOSy-44GEK_RhaS5orjxDhjlqJeCnfb362cqNgkAAAECCOA_EQEHNAAA4D8ZAAAAgOtR4D8hERIAKREJADERG6gwsqKiBjjtSEDtSEgCUNOBly5YnPFbYABotc95eJ7SBYABAYoBA1VTRJIBAQbwUpgBrAKgAfoBqAEBsAEAuAEBwAEDyAEC0AEA2AEA4AEA8AEAigI7dWYoJ2EnLCAyNTI5ODg1LCAxNjQxOTk0NTk2KTt1ZigncicsIDk2ODQ2MDM1Nh4A8P2SAu0DIU9Vd3o2QWl1c0s0S0VOT0JseTRZQUNDYzhWc3dBRGdBUUFSSTdVaFFzcUtpQmxnQVlLMEJhQUJ3QUhnQWdBRUlpQUVBa0FFQm1BRUJvQUVCcUFFRHNBRUF1UUVwaTRpREFBRGdQOEVCS1l1SWd3QUE0RF9KQVRRLUV4ZE02LVFfMlFFQUFBQUFBQUR3UC1BQkFQVUJBQUFBQUpnQ0FLQUNBTFVDQUFBQUFMMENBQUFBQU1BQ0FNZ0NBTkFDQU5nQ0FPQUNBT2dDQVBnQ0FJQURBWmdEQWJvRENWTkpUak02TkRjMk5PQURyaTJJQkFDUUJBQ1lCQUhCQgVNCQEIeVFRCQkBARROZ0VBUEURlSxBQUFDSUJad2xxUVUBDQmoCDdFRgEKCQEIREJCHT8AeRUoDEFBQU4yKAAAWi4oALg0QVhRaGdQd0JlMzgyZ0w0QmQyMG1nR0NCZ05WVTBTSUJnQ1FCZ0dZQmdDaEJnQQFONEFBT0FfcUFZQnNnWWtDHXQARR0MAEcdDABJHQw4dUFZS5oClQEhelEta3FRNvEBJG5QRmJJQVFvQUQR-FhEZ1B6b0pVMGxPTXpvME56WTBRSzR0UxFRDFBBX1URDAxBQUFXHQwAWR0MAGEdDABjHQwQZUFDSkEdEPA-wgIvaHR0cDovL3ByZWJpZC5vcmcvZGV2LWRvY3MvZ2V0dGluZy1zdGFydGVkLmh0bWzYAvfpA-ACrZhI6gIVATyAczovL2pzZmlkZGxlLm5ldC_yAhEKBkFEVl9JRBIHMjUyYR4FFAhDUEcFFBQ1Njg0ODQFFAgFQ1ABEzQIMjE3MzEzNzTyAg0KCAE8FEZSRVESAQkQNFJFTV9VU0VSEgEw8gIMCSAUQ09ERRIABQ8BVxEPEAsKB0NQFQ4QCQoFSU8BYAQA8gEaBElPFRo4EwoPQ1VTVE9NX01PREVMDSQIGgoWMhYAHExFQUZfTkFNBWoIHgoaNh0ACEFTVAE-EElGSUVEAWIcDQoIU1BMSVQBTdABMIADAIgDAZADAJgDF6ADAaoDAMADrALIAwDYA_7ERuADAOgDAPgDAYAEAJIEDS91dC92My2E8GGYBACiBA0zOS4xMjEuMjAwLjI5qAT_9jayBBIIBBAEGKwCIPoBKAEoAjAAOAK4BADABADIBADSBA45MzI1I1NJTjM6NDc2NNoEAggB4AQB8ATTgZcuiAUBmAUAoAX______wEDFAHABQDJBakrFPA_0gUJCQkMcAAA2AUB4AUB8AUB-gUECAAQAJAGAJgGALgGAMEGCSMo8D_QBvUv2gYWChAJERkBXBAAGADgBgHyBgIIAIAHAYgHAKAHAboHDwFISBgAIAAwADjrD0AAyAee0gXSBw0VdAE4CNoHBgknSOAHAOoHAggA8Af99e0BiggCEAA.&s=727a8e38e7d1afabb5dccd6fdf4f66c56f671b60\\\";c+=\\\"&\\\"+o({rdRef:\\\"bdref\\\",rdTop:\\\"bdtop\\\",rdIfs:\\\"bdifs\\\",rdStk:\\\"bstk\\\",rdQs:\\\"\\\"}),n(c,{bdfif:\\\"bdfif\\\"})}();} catch (e) { }</script><div name=\\\"anxv\\\" lnttag=\\\"v;tv=view7-1hs;st=0;d=300x250;vc=iab;vid_ccr=1;tag_id=13144370;cb=https%3A%2F%2Fsin3-ib.adnxs.com%2Fvevent%3Fan_audit%3D0%26referrer%3Dhttps%253A%252F%252Fjsfiddle.net%252F%26e%3DwqT_3QKYCqAYBQAAAwDWAAUBCOSy-44GEK_RhaS5orjxDhjlqJeCnfb362cqNgkAAAECCOA_EQEHNAAA4D8ZAAAAgOtR4D8hERIAKREJADERG6gwsqKiBjjtSEDtSEgCUNOBly5YnPFbYABotc95eJ7SBYABAYoBA1VTRJIBAQbwUpgBrAKgAfoBqAEBsAEAuAEBwAEDyAEC0AEA2AEA4AEA8AEAigI7dWYoJ2EnLCAyNTI5ODg1LCAxNjQxOTk0NTk2KTt1ZigncicsIDk2ODQ2MDM1Nh4A8P2SAu0DIU9Vd3o2QWl1c0s0S0VOT0JseTRZQUNDYzhWc3dBRGdBUUFSSTdVaFFzcUtpQmxnQVlLMEJhQUJ3QUhnQWdBRUlpQUVBa0FFQm1BRUJvQUVCcUFFRHNBRUF1UUVwaTRpREFBRGdQOEVCS1l1SWd3QUE0RF9KQVRRLUV4ZE02LVFfMlFFQUFBQUFBQUR3UC1BQkFQVUJBQUFBQUpnQ0FLQUNBTFVDQUFBQUFMMENBQUFBQU1BQ0FNZ0NBTkFDQU5nQ0FPQUNBT2dDQVBnQ0FJQURBWmdEQWJvRENWTkpUak02TkRjMk5PQURyaTJJQkFDUUJBQ1lCQUhCQgVNCQEIeVFRCQkBARROZ0VBUEURlSxBQUFDSUJad2xxUVUBDQmoCDdFRgEKCQEIREJCHT8AeRUoDEFBQU4yKAAAWi4oALg0QVhRaGdQd0JlMzgyZ0w0QmQyMG1nR0NCZ05WVTBTSUJnQ1FCZ0dZQmdDaEJnQQFONEFBT0FfcUFZQnNnWWtDHXQARR0MAEcdDABJHQw4dUFZS5oClQEhelEta3FRNvEBJG5QRmJJQVFvQUQR-FhEZ1B6b0pVMGxPTXpvME56WTBRSzR0UxFRDFBBX1URDAxBQUFXHQwAWR0MAGEdDABjHQwQZUFDSkEdEPA-wgIvaHR0cDovL3ByZWJpZC5vcmcvZGV2LWRvY3MvZ2V0dGluZy1zdGFydGVkLmh0bWzYAvfpA-ACrZhI6gIVATzwQ3M6Ly9qc2ZpZGRsZS5uZXQvgAMAiAMBkAMAmAMXoAMBqgMAwAOsAsgDANgD_sRG4AMA6AMA-AMBgAQAkgQNL3V0L3YzDX7wYZgEAKIEDTM5LjEyMS4yMDAuMjmoBP_2NrIEEggEEAQYrAIg-gEoASgCMAA4ArgEAMAEAMgEANIEDjkzMjUjU0lOMzo0NzY02gQCCAHgBAHwBNOBly6IBQGYBQCgBf______AQMUAcAFAMkFiSUU8D_SBQkJCQxwAADYBQHgBQHwBQH6BQQIABAAkAYAmAYAuAYAwQYJIyjwP9AG9S_aBhYKEAkRGQFcEAAYAOAGAfIGAggAgAcBiAcAoAcBugcPAUhIGAAgADAAOOsPQADIB57SBdIHDRV0ATgI2gcGCSdI4AcA6gcCCADwB_317QGKCAIQAA..%26s%3D6327c7f904f75520ea1e070069665261b7b113c4;ts=1641994596;cet=0;cecb=\\\" width=\\\"0\\\" height=\\\"0\\\" style=\\\"display: block; margin: 0; padding: 0; height: 0; width: 0;\\\"><script type=\\\"text/javascript\\\" async=\\\"true\\\" src=\\\"https://cdn.adnxs.com/v/s/222/trk.js\\\"></script></div>\",\"width\":300,\"height\":250},\"trackers\":[{\"impression_urls\":[\"https://sin3-ib.adnxs.com/it?an_audit=0&referrer=https%3A%2F%2Fjsfiddle.net%2F&e=wqT_3QKYCqAYBQAAAwDWAAUBCOSy-44GEK_RhaS5orjxDhjlqJeCnfb362cqNgkAAAECCOA_EQEHNAAA4D8ZAAAAgOtR4D8hERIAKREJADERG6gwsqKiBjjtSEDtSEgCUNOBly5YnPFbYABotc95eJ7SBYABAYoBA1VTRJIBAQbwUpgBrAKgAfoBqAEBsAEAuAEBwAEEyAEC0AEA2AEA4AEA8AEAigI7dWYoJ2EnLCAyNTI5ODg1LCAxNjQxOTk0NTk2KTt1ZigncicsIDk2ODQ2MDM1Nh4A8P2SAu0DIU9Vd3o2QWl1c0s0S0VOT0JseTRZQUNDYzhWc3dBRGdBUUFSSTdVaFFzcUtpQmxnQVlLMEJhQUJ3QUhnQWdBRUlpQUVBa0FFQm1BRUJvQUVCcUFFRHNBRUF1UUVwaTRpREFBRGdQOEVCS1l1SWd3QUE0RF9KQVRRLUV4ZE02LVFfMlFFQUFBQUFBQUR3UC1BQkFQVUJBQUFBQUpnQ0FLQUNBTFVDQUFBQUFMMENBQUFBQU1BQ0FNZ0NBTkFDQU5nQ0FPQUNBT2dDQVBnQ0FJQURBWmdEQWJvRENWTkpUak02TkRjMk5PQURyaTJJQkFDUUJBQ1lCQUhCQgVNCQEIeVFRCQkBARROZ0VBUEURlSxBQUFDSUJad2xxUVUBDQmoCDdFRgEKCQEIREJCHT8AeRUoDEFBQU4yKAAAWi4oALg0QVhRaGdQd0JlMzgyZ0w0QmQyMG1nR0NCZ05WVTBTSUJnQ1FCZ0dZQmdDaEJnQQFONEFBT0FfcUFZQnNnWWtDHXQARR0MAEcdDABJHQw4dUFZS5oClQEhelEta3FRNvEBJG5QRmJJQVFvQUQR-FhEZ1B6b0pVMGxPTXpvME56WTBRSzR0UxFRDFBBX1URDAxBQUFXHQwAWR0MAGEdDABjHQwQZUFDSkEdEPA-wgIvaHR0cDovL3ByZWJpZC5vcmcvZGV2LWRvY3MvZ2V0dGluZy1zdGFydGVkLmh0bWzYAvfpA-ACrZhI6gIVATzwQ3M6Ly9qc2ZpZGRsZS5uZXQvgAMAiAMBkAMAmAMXoAMBqgMAwAOsAsgDANgD_sRG4AMA6AMA-AMBgAQAkgQNL3V0L3YzDX7wYZgEAKIEDTM5LjEyMS4yMDAuMjmoBP_2NrIEEggEEAQYrAIg-gEoASgCMAA4ArgEAMAEAMgEANIEDjkzMjUjU0lOMzo0NzY02gQCCAHgBAHwBNOBly6IBQGYBQCgBf______AQMUAcAFAMkFiSUU8D_SBQkJCQxwAADYBQHgBQHwBQH6BQQIABAAkAYAmAYAuAYAwQYJIyjwP9AG9S_aBhYKEAkRGQFcEAAYAOAGAfIGAggAgAcBiAcAoAcBugcPAUhIGAAgADAAOOsPQADIB57SBdIHDRV0ATgI2gcGCSdI4AcA6gcCCADwB_317QGKCAIQAA..&s=a17005d48a4453aea76faa88e28e333442e944e0\"],\"video_events\":{}}]}}]}]}";
		
		String adm ="[]";
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
			
//			URL url = new URL(prod_nasmedia);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type","application/json");
//					
//			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
//			osw.write(param);
//			osw.flush();
//			
//			LogFacade.logRequestAsync("prod send:"+site_id+":"+":"+prod_nasmedia+":"+param);
//			//dynamodb에 기록		
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//			
//			String line =null;
//			StringBuffer outResult = new StringBuffer();
//			
//			while( (line = br.readLine()) != null) 
//			{
//				// read시 exception이 발생할 경우?? 
//				outResult.append(line);
//			}
	
//			String dspResult = outResult.toString();		
//			//LogFacade.logRequestAsync("prod receive:"+site_id+":"+dspResult);
//			
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
//			adm = (String) bid.get("adm");
//			adm = adm.replace("${AUCTION_PRICE}", ""+price);
//			adm = adm.replace("<body>", "<body>"+tracker);
//					
//			osw.close();
//			br.close();
//			conn.disconnect();
//			
//			LogFacade.logRequestAsync("prod receive:"+site_id+":ad_ok");
			
		}catch(Exception e) {
			//e.printStackTrace();
			//System.err.println("bidrequest:218line=>"+e.getMessage());
			
			adm = default_ad;//default ad도 impression카운트 해야하나?
//			LogFacade.logRequestAsync("prod receive:"+site_id+":no_ad");
			//LogFacade.logRequestAsync("no ad from dsp, default ad ="+site_id);
		}
		//LogFacade.logProviderAsync(one.toString()+":"+adm);
		
		adm = "[ {\r\n"
				+ "    \"bidderCode\": \"Admaru\",\r\n"
				+ "    \"width\": 336,\r\n"
				+ "    \"height\": 280,\r\n"
				+ "    \"statusMessage\": \"Bid available\",\r\n"
				+ "    \"adId\": \"249537ebe1f971b\",\r\n"
				+ "    \"requestId\": \"120f5bed5dda3ef\",\r\n"
				+ "    \"mediaType\": \"banner\",\r\n"
				+ "    \"source\": \"client\",\r\n"
				+ "    \"cpm\": 0,\r\n"
				+ "    \"creativeId\": \"ad-AA7B8D26DAB79822F8942EA9E277BD8E\",\r\n"
				+ "    \"dealId\": null,\r\n"
				+ "    \"currency\": \"USD\",\r\n"
				+ "    \"netRevenue\": true,\r\n"
				+ "    \"ttl\": 1800,\r\n"
				+ "    \"meta\": {},\r\n"
				+ "    \"ad\": \"\",\r\n"
				+ "    \"originalCpm\": 0,\r\n"
				+ "    \"originalCurrency\": \"USD\",\r\n"
				+ "    \"auctionId\": \"46804c7b-fe9a-432a-925f-457f53d4614d\",\r\n"
				+ "    \"responseTimestamp\": 1641250718003,\r\n"
				+ "    \"requestTimestamp\": 1641250717026,\r\n"
				+ "    \"bidder\": \"ucfunnel\",\r\n"
				+ "    \"adUnitCode\": \"div-gpt-ad-1628057156627-0\",\r\n"
				+ "    \"timeToRespond\": 977,\r\n"
				+ "    \"pbLg\": \"0.00\",\r\n"
				+ "    \"pbMg\": \"0.00\",\r\n"
				+ "    \"pbHg\": \"0.00\",\r\n"
				+ "    \"pbAg\": \"0.00\",\r\n"
				+ "    \"pbDg\": \"0.00\",\r\n"
				+ "    \"pbCg\": \"\",\r\n"
				+ "    \"adserverTargeting\": {}\r\n"
				+ "} ]";
		
		response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin") );
    	response.getWriter().println(adm2);
		
		
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
