//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package bsw.base;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bsw.fwk.BaseUserContext;


import bsw.tools.exception.BSWException;
/**
 * 
 * 
 * @描述:所有Action的基类，负责转发页面。
 * @邮箱:wanxuesi@163.com
 * @作者:万学思
 *
 */

public abstract class BaseAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		// 从session中获取用户信息对象
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		String errorMessage="";
		 //判断用户是否已经登录-----权限认证, 
//		用户是否已登录
		if(baseUserContext==null){
			errorMessage = "您还没有登录！";
			request.setAttribute("EXCEPTION",new BSWException(errorMessage));
			return mapping.findForward("fail");
		}
		
		//获取用户操作该功能的功能码(此处把Action的path别名作为功能码)
		String path = mapping.getPath();
		path = path.substring(1);
		
		
		
//临时屏蔽	
//		//用户是否有操作该功能的权限
//		if(!baseUserContext.allow(path)){
//			errorMessage = "对不起，您没有操作该功能的权限，请与管理员联系！";
//			request.setAttribute("EXCEPTION",new BSWException(errorMessage));
//			return mapping.findForward("fail");
//		}
		
			
		
		
		
		 try {
			 //调用子类的实现方法
			 myexecute(mapping,form,request, response);
		 	 return mapping.findForward("success");
		 } catch (Exception e) {
			 request.setAttribute("EXCEPTION",e);
			 return mapping.findForward("fail");
		 }
	}

	/**
	 * 业务控制方法，子类必须实现该方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public abstract void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception;
}

