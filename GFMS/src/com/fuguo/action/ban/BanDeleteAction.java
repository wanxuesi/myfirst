//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ban;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.DanweiBO;
import com.fuguo.dto.DanweiDTO;


public class BanDeleteAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idStr = request.getParameter("id").trim();
		int id = Integer.parseInt(idStr);
		DanweiBO dBO = new DanweiBO();
		DanweiDTO dDTO = new DanweiDTO();
		dDTO.setId(id);
		dBO.delete(dDTO);
		
	}


}

