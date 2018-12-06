package com.fuguo.action.tsxg;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.form.GpmcForm;
import com.fuguo.util.StockUtil;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class ShowOnePicAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		GpmcForm m = (GpmcForm)form;
		
		String zqdm = m.getGpdm();
		StockUtil sUtil = new StockUtil();
		String fullZqdm = sUtil.getFullZqdmByZqdm(zqdm);
		
		GpmcBO gpmcBO = new GpmcBO();
		
		GpmcDTO gpmcDTO= gpmcBO.getStockMessage(zqdm);
		
		request.setAttribute("FULLZQDM",fullZqdm);
		
		request.setAttribute("GPMCDTO",gpmcDTO);

	}

	
}

