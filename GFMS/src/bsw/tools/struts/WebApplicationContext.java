package bsw.tools.struts;

import java.net.URL;

import javax.servlet.ServletContext;

import bsw.base.BaseJSP;

/**

 */
public class WebApplicationContext {
	/**
	 * ��ǰӦ�õ�Servlet��������λ��context�����ء�
	 */
	private static ServletContext context = null;

	/**
	 * �˴���Ŀ������ϵͳ����������
	 * 
	 * @param request
	 */
	public static void setContext(ServletContext context) {
		WebApplicationContext.context = context;
		//ǿ�ƻ�ȡ��Ŀ·��
		try {

			URL test = context.getResource("/");
			String path = test.getPath();
			path = path.substring(0, path.length() - 1);
			path = path.substring(path.lastIndexOf('/'));
			BaseJSP.root = path;
		} catch (Exception ex) {
			

		}
	}

}
