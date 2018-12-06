//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.systemconfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.SystemconfigBO;
import com.fuguo.dto.SystemconfigDTO;
import com.fuguo.form.SystemconfigForm;

/**
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class SystemconfigUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		 SystemconfigForm m = (SystemconfigForm) form;
		 String functioncode =m.getFunctioncode();
		 String functionname =m.getFunctionname();
		 String functionvalue=m.getFunctionvalue();
		 //String flag1 = m.getFlag1();

		SystemconfigDTO tDTO = new SystemconfigDTO();

		tDTO.setId(functioncode);
		SystemconfigBO tBO = new SystemconfigBO();
		tDTO = tBO.query(tDTO);

		
	    tDTO.setFunctionname(functionname);
	    tDTO.setFunctionvalue(functionvalue);

	    
		tBO.update(tDTO);

	}

}
