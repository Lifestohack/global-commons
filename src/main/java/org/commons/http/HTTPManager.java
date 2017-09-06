package org.commons.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.commons.models.WebRequest;

//GET, POST, PUT, PATCH, DELETE

public class HTTPManager {
	private HttpHost httpHost = null; 
	private CloseableHttpClient httpClient = null;
	private CredentialsProvider credsProvider = null;
	private  AuthCache authCache = null;
	
	
	private void initialize(){
	
			httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		
		 
	}
	
	private WebRequest getResponse(HttpRequest httpRequest){
 		WebRequest webRequest = null;
		HttpEntity entity = null;
		CloseableHttpResponse httpResponse = null;
		HttpClientContext context = HttpClientContext.create();
		context.setAuthCache(authCache);
		try
		{
			if(httpHost == null){
				httpResponse = httpClient.execute((HttpUriRequest) httpRequest); 
			}else{
				httpResponse = httpClient.execute(httpHost, httpRequest, context); 
			}
			
			entity = httpResponse.getEntity();
			webRequest = new WebRequest();
			webRequest.setContent(EntityUtils.toString(entity));
			webRequest.setHeader(httpResponse.toString());
		 } 
		  	catch (IOException e) 
		 { 
		  		e.printStackTrace(); 
		 }finally{
			 try {
				 if(httpResponse!=null){
				httpResponse.close();
				 }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		return webRequest;
	}
	
	public void setHost(String host, int port, String scheme){
		httpHost = new HttpHost(host,port,scheme); 
	}

	public void setAuthentification(String username, final String password){
	
		credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(httpHost), new UsernamePasswordCredentials(username, password));
        // Create AuthCache instance
        authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(httpHost, basicAuth);
        
	}
			
	public WebRequest getPOSTResponse(String target) {
		initialize();
		HttpPost httpPost = new HttpPost(target);
		return getResponse(httpPost);
		
	}
	
	public WebRequest getGETResponse(String target){
		initialize();
        HttpGet httpGet = new HttpGet(target);
        return  getResponse(httpGet);
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
