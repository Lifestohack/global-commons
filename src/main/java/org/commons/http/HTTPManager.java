package org.commons.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.commons.models.WebRequest;

//GET, POST, PUT, PATCH, DELETE

public class HTTPManager {
	private HttpHost httpHost = null; 
	private CloseableHttpClient httpClient = null;
	
	public HTTPManager() {
		httpClient  = HttpClients.createDefault();
	}

	
	private void setHost(String host, int port, String connectionType){
		httpHost = new HttpHost(host,port,connectionType); 
	}
	
	public WebRequest getResponseFromURL(String url) 
	 { 
		WebRequest webRequest = new WebRequest();
	  try
	  {
		  HttpGet httpget = new HttpGet(url);
		  CloseableHttpResponse httpResponse = httpClient.execute(httpget);  //HttpRequest
		  HttpEntity entity = httpResponse.getEntity();
		  webRequest.setContent(EntityUtils.toString(entity, "UTF-8"));
		  webRequest.setHeader(httpResponse.toString());
	  }
	  catch (IOException e)
	  { 
	   e.printStackTrace(); 
	  } 
	  return webRequest;
	 }

	public WebRequest webResponse(String host, String target, int port, String httpsProtocol, String callMethod) throws Exception{
		setHost(host, port, httpsProtocol);
		if(callMethod == "GET"){
			return getGETResponse(target);
		}else if(callMethod == "POST"){
			return getPOSTResponse(target);
		} else{
			throw new Exception("No Call Method Found.");
		}
	}
	
	
	public WebRequest webResponse(String url, String callMethod) throws Exception{
		if(callMethod == "GET"){
			return getResponseFromURL(url);
		}else if(callMethod == "POST"){
			return getResponseFromURL(url);
		}else{
			throw new Exception("No Call Method Found");
		}
	}
	
	
	private WebRequest getPOSTResponse(String target) {
		  HttpPost httpPost = new HttpPost(target);
		  return getResponse(httpPost);
		
	}

	
	public WebRequest getGETResponse(String target){
		  HttpGet httpget = new HttpGet(target);
		  return getResponse(httpget);
	}


	
 	private WebRequest getResponse(HttpRequest httpRequest){
 		WebRequest webRequest = null;
		HttpEntity entity = null;
		try
		{
			CloseableHttpResponse httpResponse = httpClient.execute(httpHost, httpRequest); 
			entity = httpResponse.getEntity();
			webRequest = new WebRequest();
			webRequest.setContent(EntityUtils.toString(entity));
			webRequest.setHeader(httpResponse.toString());
		 } 
		  	catch (IOException e) 
		 { 
		  		e.printStackTrace(); 
		 }
		 
		return webRequest;
	}
	

	public void dispose(){
		try {
			httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
