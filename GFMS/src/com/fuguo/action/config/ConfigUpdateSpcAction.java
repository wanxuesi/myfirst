//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;

import com.fuguo.bo.ConfigBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.form.ConfigForm;
import com.fuguo.form.Query_Of_AllForm;

/**
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class ConfigUpdateSpcAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
	 	
		//Query_Of_AllForm qForm = (Query_Of_AllForm)form;
        
		
		//String zqdm = qForm.getZqdm().trim();//证券代码;

		ConfigDTO tDTO = new ConfigDTO();

		tDTO.setId(idStr);
		ConfigBO tBO = new ConfigBO();
		tDTO = tBO.query(tDTO);

		 String flag1  = tDTO.getFlag1();//是否隐藏
	        if(flag1==null||flag1.equals("")||flag1.equals("否")){
	        	
	        	flag1="是";
	        }else{
	        	flag1="否";
	        }
	        
	        
	    
		
        tDTO.setFlag1(flag1);
		tBO.update(tDTO);
		//这个地方需要构造一个 name="query_Of_AllForm" scope="session"//供jiluSpecalQuery使用；只要放入证券代码即可；
		

	}

}
