//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.lx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.LxBO;
import com.fuguo.dto.LxDTO;
import com.fuguo.form.LxForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LxShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		LxForm m = (LxForm)form;
		int id = m.getId();
		
		LxDTO tDTO = new LxDTO();
		
		tDTO.setId(id);
		LxBO tBO = new LxBO();
		tDTO=tBO.query(tDTO);

		
		request.setAttribute("LX",tDTO);

		
	}

	

}

