package com.admaru.servlet;

public class TestServletAnnotation {

	public static void main(String[] args) {
		String adm = "hello world<IconClickTracking>xxxx</IconClickTracking>bbbbbbb<IconClickTracking>yyyy</IconClickTracking>zzzzz";
		System.out.println(adm);
		String button_click_tracker = "!!button_click_tracker!!";
		String skip_tracker = "!!skip_tracker!!";
		
		int buttonClickIndex = adm.indexOf("</IconClickTracking>")+"</IconClickTracking>".length();
		StringBuilder sb = new StringBuilder();
	    sb.append(adm);
	    sb.insert(buttonClickIndex, button_click_tracker);
		
		int skipIndex = sb.indexOf("</IconClickTracking>", buttonClickIndex + button_click_tracker.length());
		sb.insert(skipIndex+"</IconClickTracking>".length(), skip_tracker);
		adm = new String(sb);
		
		System.out.println(adm);
	}
}
