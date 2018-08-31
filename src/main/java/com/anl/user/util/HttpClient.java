package com.anl.user.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * @author yangyiqiang
 *
 */
public class HttpClient {
	public static final String CHARACTER_ENCODING = "UTF-8";

	/**
	 * post 请求，返回String的数据包
	 *
	 * @param url
	 * @param requestData
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(String url, String requestData) throws IOException {
		return postRequest(url, requestData.getBytes(CHARACTER_ENCODING), null);
	}

	/**
	 * post 请求，支持重定向
	 *
	 * @param url
	 * @param requestData
	 * @return
	 * @throws IOException
	 */
	public static String postRequest1(String url, String requestData) throws IOException {

		HttpURLConnection httpConn = null;
		StringBuffer sBuffer = new StringBuffer("");
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			String length = "0";
			if (requestData != null) {
				length = Integer.toString(requestData.getBytes(CHARACTER_ENCODING).length);
			}
			httpConn.setConnectTimeout(15000);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
			httpConn.setRequestProperty("Connection", "close");
			httpConn.setRequestProperty("Content-Length", length);
			httpConn.setInstanceFollowRedirects(false);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			OutputStream outStream = httpConn.getOutputStream();
			outStream.write(requestData.getBytes(CHARACTER_ENCODING));
			outStream.flush();
			outStream.close();
			int resultCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == resultCode) {
				BufferedReader in = null;
				String inputLine = null;
				in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), CHARACTER_ENCODING));
				while ((inputLine = in.readLine()) != null) {
					sBuffer.append(inputLine);
				}

				in.close();
			} else if (HttpURLConnection.HTTP_MOVED_PERM == resultCode
					|| HttpURLConnection.HTTP_MOVED_TEMP == resultCode) {
				url = httpConn.getHeaderField("Location");
				LogFactory.getInstance().getLogger().debug("请求上游定向流量鉴权重定向后的URL:" + url + ", 请求报文：" + requestData);
				return postRequest1(url, requestData);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
				httpConn = null;
			}
		}

		return sBuffer.toString();

	}

	/**
	 * post请求并返回数据包
	 *
	 * @param url
	 *            请求url
	 * @param requestData
	 *            请求数据
	 * @param requestProperties
	 *            请求包体
	 * @return byte[] 数据包
	 * @throws IOException
	 */
	public static String postRequest(String url, byte[] requestData, Properties requestProperties) throws IOException {
		HttpURLConnection httpConn = null;
		StringBuffer sBuffer = new StringBuffer("");
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			// 封住包体
			if (requestProperties != null) {
			}
			String length = "0";
			if (requestData != null) {
				length = Integer.toString(requestData.length);
			}
			httpConn.setConnectTimeout(15000);
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
			httpConn.setRequestProperty("Connection", "close");
			httpConn.setRequestProperty("Content-Length", length);
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			OutputStream outStream = httpConn.getOutputStream();
			outStream.write(requestData);
			outStream.flush();
			outStream.close();
			BufferedReader in = null;
			String inputLine = null;
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), CHARACTER_ENCODING));
			while ((inputLine = in.readLine()) != null) {
				sBuffer.append(inputLine);
			}

			in.close();

		} catch (IOException e) {
			throw e;
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
				httpConn = null;
			}
		}

		return sBuffer.toString();
	}

	/*
	 * get请求
	 */
	public static String getRequest(String url) throws Exception {
		HttpURLConnection httpConn = null;
		StringBuffer sBuffer = new StringBuffer("");
		try {
			httpConn = (HttpURLConnection) new URL(url).openConnection();
			httpConn.setConnectTimeout(15000);
			//httpConn.setReadTimeout(1000);
			httpConn.setRequestMethod("GET");
			httpConn.setRequestProperty("Content-type", "application/json;charset=UTF-8");
			httpConn.setRequestProperty("Connection", "close");
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			BufferedReader in = null;
			String inputLine = null;
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), CHARACTER_ENCODING));
			while ((inputLine = in.readLine()) != null) {
				sBuffer.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			int code = httpConn.getResponseCode();
			LogFactory.getInstance().getErrorLogger().error("http code == " + code);
			throw new Exception(e);
		} finally {
			if (httpConn != null) {
				httpConn.disconnect();
				httpConn = null;
			}
		}
		return sBuffer.toString();
	}

	public static void main(String[] args) {
		for(int i = 100;i <= 600;i++){
			int finalI = i;
			new Thread(() -> {
				String result = null;
				try {
					String url = "http://localhost:8080/api/downFlowApp?a=" + finalI;
					LogFactory.getInstance().getLogger().debug(url + " | start!");
					HttpClient.getRequest(url);
					LogFactory.getInstance().getLogger().debug(url + " | end!");
				} catch (Exception e) {
					LogFactory.getInstance().getErrorLogger().error("--" + e.getMessage());
					//e.printStackTrace();
				}
				//System.out.println(result);
			}).start();
		}
	}
}