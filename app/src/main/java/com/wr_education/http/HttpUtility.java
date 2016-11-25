package com.wr_education.http;

import android.text.TextUtils;

import com.wr_education.util.LogHelper;
import com.wr_education.util.Utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.zip.GZIPInputStream;
/**
 * http请求方式类
 *
 */
public class HttpUtility {

	private static final int CONNECT_TIMEOUT = 20 * 1000;
	private static final int READ_TIMEOUT = 20 * 1000;


	private static HttpUtility httpUtil=null;

	public static HttpUtility getInstance(){
		if(httpUtil==null)
			httpUtil = new HttpUtility();
		return httpUtil;
	}

	private static Proxy getProxy() {
		String proxyHost = System.getProperty("http.proxyHost");
		String proxyPort = System.getProperty("http.proxyPort");
		if (!TextUtils.isEmpty(proxyHost) && !TextUtils.isEmpty(proxyPort))
			return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					proxyHost, Integer.valueOf(proxyPort)));
		else
//			return new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(
//					"192.168.0.193", 8888));
			return null;
	}
    public String doPost_Picture(String urlAddress, Map<String, String> param){

		try {
			URL url = new URL(urlAddress);
			LogHelper.i("请求url---" + urlAddress);
//			LogHelper.i("请求参数---" + param);
			Proxy proxy = null;
			if(param!=null)
				proxy = getProxy();
			HttpURLConnection uRLConnection;
			if (proxy != null)
				uRLConnection = (HttpURLConnection) url.openConnection(proxy);
			else
				uRLConnection = (HttpURLConnection) url.openConnection();
//			LogHelper.e("Cookie:", MyApplication.getInstance().getCookie());
//			uRLConnection.setRequestProperty("Cookie",MyApplication.getInstance().getCookie());
//			if(MyApplication.isLogin){
//				loginInfo = MyApplication.getInstance().getHttpRequestHand();
//			}else{
//				if(MyApplication.loginInfo==null)
//					loginInfo = MyApplication.getInstance().getHttpRequestHand();
//				else
//					loginInfo = MyApplication.loginInfo;
//			}
//
//			uRLConnection.setRequestProperty("cu", loginInfo.getCruID()+"");
//			uRLConnection.setRequestProperty("pu", loginInfo.getsPhone());
//			uRLConnection.setRequestProperty("ct", loginInfo.getTimestamp());
//			uRLConnection.setRequestProperty("token", loginInfo.getToken());
//			uRLConnection.setRequestProperty("key", loginInfo.getKey());
//			uRLConnection.setRequestProperty("Cuiv", loginInfo.getCuiv());
//			uRLConnection.setRequestProperty("cct", loginInfo.getRandom_number());
//			uRLConnection.setRequestProperty("bt", loginInfo.getRandom_number_start());
//			uRLConnection.setRequestProperty("at", loginInfo.getRandom_number_end());
//			uRLConnection.setRequestProperty("lt", loginInfo.getLt());
//			uRLConnection.setRequestProperty("pl", loginInfo.getEncryption_String());

			uRLConnection.setDoInput(true);
			uRLConnection.setDoOutput(true);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setUseCaches(false);
			uRLConnection.setConnectTimeout(CONNECT_TIMEOUT*6);
			uRLConnection.setReadTimeout(READ_TIMEOUT*6);
			uRLConnection.setInstanceFollowRedirects(false);
			uRLConnection.setRequestProperty("Connection", "Keep-Alive");
			uRLConnection.setRequestProperty("Charset", "UTF-8");
//			uRLConnection.setRequestProperty("Content-Type", "application/json");
			uRLConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");
			uRLConnection.connect();
			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			out.write(Utility.encodeUrl(param).getBytes());
//			out.write("UserName=13730856867&Password=NirqTP324NL7E80d/luZt2YqB6WHM5SCJzNuLGQiNEA=&RememberMe=true".getBytes());
			out.flush();
			out.close();
			return handleResponse(uRLConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

    }
	public String doPost(String urlAddress, Map<String, String> param){
		try {
			URL url = new URL(urlAddress);
			LogHelper.i("请求url---" + urlAddress);
			LogHelper.i("请求参数---" + param);
			Proxy proxy = null;
			if(param!=null)
				proxy = getProxy();
			HttpURLConnection uRLConnection;
			if (proxy != null)
				uRLConnection = (HttpURLConnection) url.openConnection(proxy);
			else
				uRLConnection = (HttpURLConnection) url.openConnection();
//			LogHelper.e("Cookie:", MyApplication.getInstance().getCookie());
//			uRLConnection.setRequestProperty("Cookie",MyApplication.getInstance().getCookie());
//			if(MyApplication.isLogin){
//				loginInfo = MyApplication.getInstance().getHttpRequestHand();
//			}else{
//				if(MyApplication.loginInfo==null)
//					loginInfo = MyApplication.getInstance().getHttpRequestHand();
//				else
//					loginInfo = MyApplication.loginInfo;
//			}
//			uRLConnection.setRequestProperty("cu", loginInfo.getCruID()+"");
//			uRLConnection.setRequestProperty("pu", loginInfo.getsPhone());
//			uRLConnection.setRequestProperty("ct", loginInfo.getTimestamp());
//			uRLConnection.setRequestProperty("token", loginInfo.getToken());
//			uRLConnection.setRequestProperty("key", loginInfo.getKey());
//			uRLConnection.setRequestProperty("Cuiv", loginInfo.getCuiv());
//			uRLConnection.setRequestProperty("cct", loginInfo.getRandom_number());
//			uRLConnection.setRequestProperty("bt", loginInfo.getRandom_number_start());
//			uRLConnection.setRequestProperty("at", loginInfo.getRandom_number_end());
//			uRLConnection.setRequestProperty("lt", loginInfo.getLt());
//			uRLConnection.setRequestProperty("pl", loginInfo.getEncryption_String());
			uRLConnection.setDoInput(true);
			uRLConnection.setDoOutput(true);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setUseCaches(false);
			uRLConnection.setConnectTimeout(CONNECT_TIMEOUT);
			uRLConnection.setReadTimeout(READ_TIMEOUT);
			uRLConnection.setInstanceFollowRedirects(false);
			uRLConnection.setRequestProperty("Connection", "Keep-Alive");
			uRLConnection.setRequestProperty("Charset", "UTF-8");
//			uRLConnection.setRequestProperty("Content-Type", "application/json");
			uRLConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");
			uRLConnection.connect();
			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			out.write(Utility.encodeUrl(param).getBytes());
//			out.write("UserName=13730856867&Password=NirqTP324NL7E80d/luZt2YqB6WHM5SCJzNuLGQiNEA=&RememberMe=true".getBytes());
			out.flush();
			out.close();
			return handleResponse(uRLConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String doLoadingPost(String urlAddress, Map<String, String> param){
		try {
			URL url = new URL(urlAddress);
			LogHelper.i("请求url---" + urlAddress);
			LogHelper.i("请求参数---" + param);
			Proxy proxy = null;
			if(param!=null)
				proxy = getProxy();
			HttpURLConnection uRLConnection;
			if (proxy != null)
				uRLConnection = (HttpURLConnection) url.openConnection(proxy);
			else
				uRLConnection = (HttpURLConnection) url.openConnection();
//			LogHelper.e("Cookie:", MyApplication.getInstance().getCookie());
//			uRLConnection.setRequestProperty("Cookie",MyApplication.getInstance().getCookie());
//			if(MyApplication.isLogin){
//				loginInfo = MyApplication.getInstance().getHttpRequestHand();
//			}else{
//				if(MyApplication.loginInfo==null)
//					loginInfo = MyApplication.getInstance().getHttpRequestHand();
//				else
//					loginInfo = MyApplication.loginInfo;
//			}
//			uRLConnection.setRequestProperty("cu", loginInfo.getCruID()+"");
//			uRLConnection.setRequestProperty("pu", loginInfo.getsPhone());
//			uRLConnection.setRequestProperty("ct", loginInfo.getTimestamp());
//			uRLConnection.setRequestProperty("token", loginInfo.getToken());
//			uRLConnection.setRequestProperty("key", loginInfo.getKey());
//			uRLConnection.setRequestProperty("Cuiv", loginInfo.getCuiv());
//			uRLConnection.setRequestProperty("cct", loginInfo.getRandom_number());
//			uRLConnection.setRequestProperty("bt", loginInfo.getRandom_number_start());
//			uRLConnection.setRequestProperty("at", loginInfo.getRandom_number_end());
//			uRLConnection.setRequestProperty("lt", loginInfo.getLt());
//			uRLConnection.setRequestProperty("pl", loginInfo.getEncryption_String());
			uRLConnection.setDoInput(true);
			uRLConnection.setDoOutput(true);
			uRLConnection.setRequestMethod("POST");
			uRLConnection.setUseCaches(false);
			uRLConnection.setConnectTimeout(3000);
			uRLConnection.setReadTimeout(3000);
			uRLConnection.setInstanceFollowRedirects(false);
			uRLConnection.setRequestProperty("Connection", "Keep-Alive");
			uRLConnection.setRequestProperty("Charset", "UTF-8");
//			uRLConnection.setRequestProperty("Content-Type", "application/json");
			uRLConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");
			uRLConnection.connect();
			DataOutputStream out = new DataOutputStream(
					uRLConnection.getOutputStream());
			out.write(Utility.encodeUrl(param).getBytes());
//			out.write("UserName=13730856867&Password=NirqTP324NL7E80d/luZt2YqB6WHM5SCJzNuLGQiNEA=&RememberMe=true".getBytes());
			out.flush();
			out.close();
			return handleResponse(uRLConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



	private String handleResponse(HttpURLConnection httpURLConnection){
		int status = 0;
		try {
			status = httpURLConnection.getResponseCode();
			LogHelper.d("请求返回状态码：" + status);

			if (status != HttpURLConnection.HTTP_OK) {
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			httpURLConnection.disconnect();
		}
		return readResult(httpURLConnection);
	}

	private String readResult(HttpURLConnection urlConnection){
		InputStream is = null;
		BufferedReader buffer = null;
		try {
			is = urlConnection.getInputStream();
			String content_encode = urlConnection.getContentEncoding();
//			/***************获取cookie值*****************/
//			String cookieval = urlConnection.getHeaderField("Set-Cookie");
//			 if(cookieval != null) {
//				 MyApplication.getInstance().setCookie(cookieval);
//				 LogHelper.e("sessionID:", cookieval);
//			 }
//			 /***************获取cookie值*****************/
			if(urlConnection.getURL().toString().contains("Account/App_Login1")){
				String cct = urlConnection.getHeaderField("cct");
				String bt = urlConnection.getHeaderField("bt");
				int startInt = 1;
				if(bt!=null && !bt.equals(""))
					startInt=(int)Integer.parseInt(bt);
//				String at = urlConnection.getHeaderField("at");
//				String key = MyApplication.getInstance().getSecretkeyString(loginInfo.getsPhone(), cct, startInt);
//				MyApplication.key=key;
			}
			if (null != content_encode && !"".equals(content_encode)
					&& content_encode.equals("gzip")) {
				is = new GZIPInputStream(is);
			}

			buffer = new BufferedReader(new InputStreamReader(is));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			while ((line = buffer.readLine()) != null) {
				strBuilder.append(line);
			}
			LogHelper.d("" + strBuilder.toString());
			return strBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Utility.closeSilently(is);
			Utility.closeSilently(buffer);
			urlConnection.disconnect();
		}
		return null;

	}


	public String doGet(String urlStr,Map<String, String> param){
		try {
			StringBuilder urlBuilder = new StringBuilder(urlStr);
			urlBuilder.append("?").append(Utility.encodeUrl(param));
			URL url = new URL(urlBuilder.toString());
			LogHelper.i("请求url---" + urlStr);
			LogHelper.i("请求参数---" + param);
			Proxy proxy = getProxy();
			HttpURLConnection urlConnection;
			if (proxy != null)
				urlConnection = (HttpURLConnection) url.openConnection(proxy);
			else
				urlConnection = (HttpURLConnection) url.openConnection();
//			LogHelper.e("Cookie:", MyApplication.getInstance().getCookie());
//			urlConnection.setRequestProperty("Cookie",MyApplication.getInstance().getCookie());
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(false);
			urlConnection.setConnectTimeout(CONNECT_TIMEOUT);
			urlConnection.setReadTimeout(READ_TIMEOUT);
			urlConnection.setRequestProperty("Connection", "Keep-Alive");
			urlConnection.setRequestProperty("Charset", "UTF-8");
			urlConnection.setRequestProperty("Accept", "application/json");
//			urlConnection.setRequestProperty("Content-Type", "application/json");
			urlConnection
					.setRequestProperty("Accept-Encoding", "gzip, deflate");

			urlConnection.connect();
			return handleResponse(urlConnection);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return urlStr;

	}


}
