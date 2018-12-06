//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.UserBO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;

/** 
 * MyEclipse Struts
 * Creation date: 07-17-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserPwdUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		UserForm d = (UserForm)form;
		String kl = d.getKl();//旧口令。
		String otherflag2 = d.getOtherflag2();
		BaseUserContext baseUserContext =(BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int id = baseUserContext.getId();
		
		UserDTO uDTO = new UserDTO();
		uDTO.setId(id);
		UserBO uBO =new UserBO();
		uDTO = uBO.query(uDTO);
		String dbKl=uDTO.getKl();
		if(dbKl==null){
			dbKl="";
			
		}
		if(dbKl.equals(kl)){
			uDTO.setKl(otherflag2);
			uBO.update(uDTO);
		}else{
			//密码修改失败；
			throw new BSWException("密码修改失败！");
		}
		
		
		
		
	}


}

