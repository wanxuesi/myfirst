package com.fuguo.action.lsjg;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.form.LsjgForm;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LsjgShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		LsjgForm m = (LsjgForm)form;
		
		
		LsjgBO mBO = new LsjgBO();
		LsjgDTO[] lsjgDTO=mBO.loadAll("from LsjgDTO lsjgDTO order by lsjgDTO.zqdm");
		
		request.setAttribute("LSJG",lsjgDTO);

	}

	
}

