package bsw.base;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.runtime.HttpJspBase;

import bsw.fwk.BaseUserContext;

/**
 * 
 * @author Administrator
 *
 */

public class BaseJSP extends HttpJspBase {

	
	public static String root = "/";
	
	
	/** 
	 * 实现父类方法  
	 * @see org.apache.jasper.runtime.HttpJspBase#_jspService(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void _jspService(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
	}


	/**
	 * 返回当前用户对象
	 * @param request
	 * @param response
	 * @return baseUserContext 当前用户环境变量对象
	 */
	public BaseUserContext getUser(HttpServletRequest request){
		BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		return baseUserContext;
	}

	/**
	 * 将对象保存在当前Session中。
	 * 
	 * @param request Jsp页面request对象
	 * @param index Session对象的索引
	 * @param obj Session对象值。
	 */
	public void addObjectToSession(
			javax.servlet.http.HttpServletRequest request,
			java.lang.String index, java.lang.Object obj) {
		HttpSession session = request.getSession(false);
		if (session == null)
			session = request.getSession(true);
		if (obj == null) {
			session.removeAttribute(index);
		} else {
			session.setAttribute(index, obj);
		}
	}

	/**
	 * 获取Session对象。
	 * 
	 * @param request Jsp页面request对象。
	 * @param index  Session索引值。
	 * @return Session对象值
	 */
	public Object getObjectFromSession(HttpServletRequest request, String index) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session.getAttribute(index);
		} else
			return null;
	}	
}
