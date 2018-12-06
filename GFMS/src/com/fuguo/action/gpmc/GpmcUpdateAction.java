//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.gpmc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpmcBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.form.GpmcForm;

/**
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class GpmcUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GpmcForm m = (GpmcForm) form;
		 String gpdm =m.getGpdm();
		 String gpmc =m.getGpmc();
		 String flag1 = m.getFlag1();

		GpmcDTO tDTO = new GpmcDTO();

		tDTO.setId(gpdm);
		GpmcBO tBO = new GpmcBO();
		tDTO = tBO.query(tDTO);

		tDTO.setZqdm(gpdm);
	    tDTO.setZqmc(gpmc);
	    tDTO.setFlag1(flag1);

//	  格式化一下；极度简化，存入数据库；
	      Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
			String zqdm=zqdmInteger.toString();
			tDTO.setZqdm(zqdm);
			
			gpmc = gpmc.replace(" ","");
			gpmc = gpmc.replace(" ","");
			tDTO.setZqmc(gpmc);
	    
		tBO.update(tDTO);

	}

}
