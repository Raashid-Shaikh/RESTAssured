package tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import client.RestClient;
import util.TestUtil;

public class GetAPITest extends TestBase{

	
	TestBase testBase;
	String serviceUrl;
	String apiUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse closebaleHttpResponse;
	
	
	
	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException{
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		
		
		url = serviceUrl + apiUrl;
		
	}
	
	

	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closebaleHttpResponse = restClient.get(url);
		
	
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

	
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> "+ responseJson);
		
	
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
	
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is-->"+ totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

	
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");

		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		
		
		Header[] headersArray =  closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();	
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}	
		System.out.println("Headers Array-->"+allHeaders);
		
		
		
	}
	
	
	
	@Test(priority=2)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");


		
		closebaleHttpResponse = restClient.get(url,headerMap);
		
		
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code--->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("Response JSON from API---> "+ responseJson);
		
		
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
	
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is-->"+ totalValue);		
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

	
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");

		System.out.println(lastName);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstName);
		
		
		//c. All Headers
		Header[] headersArray =  closebaleHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();	
		for(Header header : headersArray){
			allHeaders.put(header.getName(), header.getValue());
		}	
		System.out.println("Headers Array-->"+allHeaders);
		
		
		
	}
	
}
