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
			in.close();// �رմ����������ͷ����������������ϵͳ��Դ
			System.out.println("��·��������");
			return true;
		} catch (MalformedURLException e) {
			// TODO �Զ����� catch ��
			System.out.println("��·����ʧ��");
			return false;
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			System.out.println("��·����ʧ��");
			return false;
		}// �򿪵��� URL �����Ӳ�����һ�����ڴӸ����Ӷ����
		
		
		
	}
	public static void main(String[] args) {
		
		HttpConnectUtil h=new HttpConnectUtil();
		
		String urlStr = "http://baidu.com/";
		boolean result = h.isConnect(urlStr);
		
		System.out.println(result);
	}

}
