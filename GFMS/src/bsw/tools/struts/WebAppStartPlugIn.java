package bsw.tools.struts;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

//import bsw.tools.cache.CacheUtil;


/**
 * @描述: Struts PlugIn，完成系统缓冲的初始化，该配置必须配置到ActionServlet配置中。

 */
public class WebAppStartPlugIn implements PlugIn {

	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
//		初始化缓冲参数
//		CacheUtil.getInstance();
//		设置当前应用环境变量
//		LogUtil.info("gsf","设置当前应用环境变量");
		WebApplicationContext.setContext(servlet.getServletContext());
	}

	public void destroy() {
		//LogUtil.info("gsf", "CacheInitPlugIn 中止");
	}
}
