package com.admaru.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class SlackBot {


	public static void main(String...args) throws ClientProtocolException, IOException 
	{
		SlackBot bot = new SlackBot();
		bot.SendMessageToSlack("message test!, ignore this!");
	}

	public void SendMessageToReceipiant(String message, String to) throws IOException {
		
	}
	
	public void sendMessageByAPITester(String message) throws IOException 
	{
		
	}
	public void SendMessageToSlack(String message) throws ClientProtocolException, IOException 
	{
		
	}
}