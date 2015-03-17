package com.changhongit.guanhutong3.webservice;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class WebService {
	public static WebService instance = null;
	
	public static WebService getInstance(){  //单例模式
		if(instance == null){
			instance = new WebService();
		}
		return instance;
	}
	
	public String postRequest(String url, String xmlcontent) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		HttpPost request = new HttpPost(url);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-type", "application/xml");
		StringEntity entity;
		try {
			entity = new StringEntity(xmlcontent, "utf-8");
			entity.setContentType("text/xml charset=utf-8");
			request.setEntity(entity);
			HttpResponse re = httpclient.execute(request);
			if (re.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity2 = re.getEntity();
				String result = EntityUtils.toString(entity2, "utf-8");
				return result;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return null; 
	}
	
	public boolean PostRequestForBoolean(String url,String xmlcontent){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		HttpPost request = new HttpPost(url);
		request.addHeader("Accept", "application/xml");
		request.addHeader("Content-type", "application/xml");
		StringEntity entity;
		try {
			entity = new StringEntity(xmlcontent, "utf-8");
			entity.setContentType("text/xml charset=utf-8");
			request.setEntity(entity);
			HttpResponse re = httpclient.execute(request);
			if (re.getStatusLine().getStatusCode() == 200)return true;
			else return false;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String postAuthenticatedRequest(String url, String xmlcontent) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		HttpPost request = new HttpPost(url);
		request.addHeader("Content-type", "application/xml");
		request.addHeader("Authorizatoin","Basic "+ "dXNlcjpwYXNzd29yZA==");
		StringEntity entity;
		try {
			entity = new StringEntity(xmlcontent, "utf-8");
			entity.setContentType("text/xml charset=utf-8");
			request.setEntity(entity);
			HttpResponse re = httpclient.execute(request);
			if (re.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity2 = re.getEntity();
				String result = EntityUtils.toString(entity2, "utf-8");
				return result;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return null; 
	}
	
	public boolean PostAuthenticatedRequestForBoolean(String url,String xmlcontent){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		httpclient.getParams().setParameter(
				CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				20000);
		HttpPost request = new HttpPost(url);
		request.addHeader("Content-type", "application/xml");
		request.addHeader("Authorization","Basic "+ "dXNlcjpwYXNzd29yZA==");
		StringEntity entity;
		try {
			entity = new StringEntity(xmlcontent, "utf-8");
			entity.setContentType("text/xml charset=utf-8");
			request.setEntity(entity);
			HttpResponse re = httpclient.execute(request);
			if (re.getStatusLine().getStatusCode() == 200)return true;
			else return false;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String getRequest(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpGet request = new HttpGet(url);

			request.addHeader("Accept", "application/xml"); 
			request.addHeader("Content-type", "application/xml"); 
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() < 300) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");
				return result;
			}
			else if(response.getStatusLine().getStatusCode() == 404){
				return "empty data";
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
	
	public String getAuthenticatedRequest(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			httpclient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 20000);
			HttpGet request = new HttpGet(url);

			request.addHeader("Content-type", "application/xml");
			request.addHeader("Authorization","Basic "+ "dXNlcjpwYXNzd29yZA==");
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() < 300) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "UTF-8");
				return result;
			}
			else if(response.getStatusLine().getStatusCode() == 404){
				return "empty data";
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return null;
	}
}
