//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ccgp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.OrderBO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CqPreAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		int id = m.getId();
		OrderDTO oDTO  =new OrderDTO();
		oDTO.setId(id);
		
		
		OrderBO uBO =new OrderBO();
		oDTO = uBO.query(oDTO);
//		StockUtil sUtil = new StockUtil();
//		double dqj = sUtil.getDqjByZqdm(oDTO.getZqdm());//当前价；
//		oDTO.setScj(dqj);//市场价
		
		request.setAttribute("ORDER",oDTO);
		
		
	}

	

}

