package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	
	// GET Method without Headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpget = new HttpGet(url); 
	CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); 
	
	return closebaleHttpResponse;
		
	}
	
	// GET Method with Headers:
		public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); 
		
		for(Map.Entry<String,String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closebaleHttpResponse =  httpClient.execute(httpget); 
		return closebaleHttpResponse;
			
		}
		public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url); 
			httppost.setEntity(new StringEntity(entityString)); 
			
			//for headers:
			for(Map.Entry<String,String> entry : headerMap.entrySet()){
				httppost.addHeader(entry.getKey(), entry.getValue());
			}
			
			CloseableHttpResponse closebaleHttpResponse = httpClient.execute(httppost);
			return closebaleHttpResponse;
			
			
		}
		
		
		
			
		}
	


