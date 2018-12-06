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
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class DataUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DataForm m = (DataForm) form;
		String name = m.getName();
		double shuju = m.getShuju();
		//double fene = m.getFene();
		SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		    
	     
	    String dateStr =m.getDate();
	    Date dateDate = sdfTime.parse(dateStr);
		String flag1 = m.getFlag1();
		String flag2 = m.getFlag2();
		int id = m.getId();

		DataDTO tDTO = new DataDTO();

		tDTO.setId(id);
		DataBO tBO = new DataBO();
		tDTO = tBO.query(tDTO);

		tDTO.setName(name);
		tDTO.setShuju(shuju);
		//tDTO.setFene(fene);//份额  --修改相关数据时，份额
		tDTO.setDate(dateDate);
		tDTO.setFlag1(flag1);
		tDTO.setFlag2(flag2);
		tBO.update(tDTO);

	}

}
