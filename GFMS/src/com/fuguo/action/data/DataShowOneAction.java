//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.DataBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.form.DataForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DataShowOneAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		DataForm m = (DataForm)form;
		int id = m.getId();
		
		DataDTO tDTO = new DataDTO();
		
		tDTO.setId(id);
		DataBO tBO = new DataBO();
		tDTO=tBO.query(tDTO);

		
		request.setAttribute("DATA",tDTO);

		
	}

	

}

