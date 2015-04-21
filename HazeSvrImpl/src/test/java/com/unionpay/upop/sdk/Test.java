package com.unionpay.upop.sdk;

import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class Test {
	public static void main(String args[]) throws Exception {

		HostnameVerifier hv = new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				System.out.println("Warning: URL Host: " + urlHostName
						+ " vs. " + session.getPeerHost());
				return true;
			}
		};

		HttpsURLConnection.setDefaultHostnameVerifier(hv);

		// 创建URL对象
		URL myURL = new URL("https://172.17.138.19:8443/UpopSDK/index.jsp");
		// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
		HttpsURLConnection httpsConn = (HttpsURLConnection) myURL
				.openConnection();
		// 取得该连接的输入流，以读取响应内容

		InputStreamReader insr = new InputStreamReader(
				httpsConn.getInputStream());
		// 读取服务器的响应内容并显示
		int respInt = insr.read();
		while (respInt != -1) {
			System.out.print((char) respInt);
			respInt = insr.read();
		}
	}
}
