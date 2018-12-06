package bsw.tools.struts;

import java.net.URL;

import javax.servlet.ServletContext;

import bsw.base.BaseJSP;

/**

 */
public class WebApplicationContext {
	/**
	 * 当前应用的Servlet环境，如何获得context环境呢。
	 */
	private static ServletContext context = null;

	/**
	 * 此处的目的设置系统环境变量。
	 * 
	 * @param request
	 */
	public static void setContext(ServletContext context) {
		WebApplicationContext.context = context;
		//强制获取项目路径
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
