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
import com.fuguo.bo.DataBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.DataDTO;
import com.fuguo.form.DataForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ConfigPreUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ConfigForm m = (ConfigForm)form;
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		
		ConfigDTO tDTO = new ConfigDTO();
		
		tDTO.setId(idStr);
		ConfigBO tBO = new ConfigBO();
		tDTO=tBO.query(tDTO);

		
		request.setAttribute("CONFIG",tDTO);
	}

	

}

