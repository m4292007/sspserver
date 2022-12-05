package com.admaru.util;

public class Tt {

	public static void main(String...args) {
		//String x = "[2022-06-13 00:00:00,161]#prod receive:2022:1792c202-7bbd-4f27-b2e6-6ce8e1f1e167:/30349040/JA_MO_article/mid/300x250:no_ad";
		//String x = "[2022-06-13 00:00:01,674]#prod receive:1001:dbbbc6b0-f9fc-4a3e-b04f-14c56079f979:/106061858/WomanDonga_MB_NEWS_300x250_Display:ad_ok:gross price=0.625:net price=0.5625";
		String x = "[2022-06-13 00:00:00,376]#prod send:2022:47ebdc58-28ac-47d7-b9c8-419d783ba670:/30349040/JA_MO_article/mid/300x250:https://adx-exchange.toast.com/rtb/admaru:{xxxxxx}";//{"id": "47ebdc58-28ac-47d7-b9c8-419d783ba670",  "at": 1,  "cur": [ "USD" ], "test":0,             "imp": [ {"id": "1", "banner": { "pos": 0, "h": 250, "w": 300 }, "tagid":"div-gpt-ad-1628056912910-0", "bidfloor": 0.0,"bidfloorcur": "USD", "secure" : 1} ],            "site": {"id": "2022", "name": "joongang.co.kr","domain": "joongang.co.kr", "cat": [ "IAB3-1" ],  "page":"https://www.joongang.co.kr/", "ref":"https://www.joongang.co.kr", "mobile":1, "publisher":{"id":"2022"} },            "device": { "ip": "17.121.112.187","ua":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Safari/605.1.15 (Applebot/0.1; +http://www.apple.com/go/applebot)"},             "user": { "buyerid": ""}             , "source": { "ext": { "schain" : { "ver":"1.0", "complete": 1,"nodes": [ { "asi":"admaru.com", "sid":"938264", "rid":"47ebdc58-28ac-47d7-b9c8-419d783ba670", "hp":1 } ]   } } } }";
//		System.out.println(x.substring(1,20));
//		String[] y = x.substring(26).split(":");
//		System.out.println(y[0]);//prod receive
//		System.out.println(y[1]);//2022
//		System.out.println(y[2]);//1792c202-7bbd-4f27-b2e6-6ce8e1f1e167
//		System.out.println(y[3]);///30349040/JA_MO_article/mid/300x250
//		System.out.println(y[4]);//no_ad, ad_ok, https
//		System.out.println(y[5]);//gross price=0.625
//		System.out.println(y[6]);//net price
		
		System.out.println(x.substring(x.indexOf('{')));		
		
	}
}
