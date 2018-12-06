//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.LoginBO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;

public class LoginAction extends BaseAction {
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		//获取数据并封装到dto中
		UserForm userForm=(UserForm)form;
		String xm = userForm.getXm();
		String kl = userForm.getKl();
		String otherflag= userForm.getOtherflag();//临时变量；判断是否要保存cookies
		if(otherflag==null){
			otherflag="0";
		}else{
			otherflag="1";
		}
		UserDTO userDTO=new UserDTO();
		userDTO.setXm(xm);
		userDTO.setKl(kl);
		
		
		
		
		//调用业务逻辑层,将数据保存到baseusercontext中。
		LoginBO userBO = new LoginBO();
		try {
			BaseUserContext baseUserContext = userBO.login(userDTO);
//			此处需要放置coke
			if(otherflag.equals("1")){
				//将用户ID，密码放入cookies中，以便自动登录。
				xm = URLEncoder.encode(xm,"UTF-8");
				Cookie cookie1 = new Cookie("xm", xm);
				cookie1.setMaxAge(365*24*3600);
				response.addCookie(cookie1);
				Cookie cookie2 = new Cookie("kl", kl);
				cookie2.setMaxAge(365*24*3600);
				response.addCookie(cookie2);

				
				
				
			}else{
				
//				如果times=0的时候清空cookies
				Cookie[] cookiesD=request.getCookies();
				 if(cookiesD!=null)   
				 {   
				     for(int   i=0;i<cookiesD.length;i++){
				    	 
				     if(cookiesD[i].getName().equals("xm")||cookiesD[i].getName().equals("kl"))   
				      {   
				          cookiesD[i].setMaxAge(0);   
				          response.addCookie(cookiesD[i]);   
				      }   
				      
				    }    
				} 
				
			}

			//保存到session中// 跳转到成功页面。
			request.getSession().setAttribute("#BASEUSERCONTEXT#",baseUserContext);
			Map<Integer,Integer> mapSession = new  HashMap<Integer,Integer>();//放置语录的session
			request.getSession().setAttribute("#MAPSESSION#",mapSession);
			return mapping.findForward("success");
		} catch (BSWException e) {
			//捕获异常并抛出到错误页面。
			request.setAttribute("EXCEPTION", e);
			return mapping.findForward("fail");		
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成 catch 块
			request.setAttribute("EXCEPTION", e);
			return mapping.findForward("fail");		
		}
		
		
	}


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		
	}

}

