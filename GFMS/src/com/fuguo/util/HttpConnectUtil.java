package com.fuguo.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import bsw.tools.exception.BSWException;

public class HttpConnectUtil {

	public Boolean isConnect(String url){
		
		InputStream in;
		try {
			in = new URL(url).openStream();
			in.close();// 关闭此输入流并释放与该流关联的所有系统资源
			System.out.println("网路连接正常");
			return true;
		} catch (MalformedURLException e) {
			// TODO 自动生成 catch 块
			System.out.println("网路连接失败");
			return false;
		} catch (IOException e) {
			// TODO 自动生成 catch 块
			System.out.println("网路连接失败");
			return false;
		}// 打开到此 URL 的连接并返回一个用于从该连接读入的
		
		
		
	}
	public static void main(String[] args) {
		
		HttpConnectUtil h=new HttpConnectUtil();
		
		String urlStr = "http://baidu.com/";
		boolean result = h.isConnect(urlStr);
		
		System.out.println(result);
	}

}
