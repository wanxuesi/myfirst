package com.fuguo.action.lsjgdate;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgdateDTO;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LsjgdateShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		
		
		GpmcDTO gpmcDTO; 
		GpmcBO gBO = new GpmcBO();
		String zqdm="";
		//String gpmc="";
		LsjgdateBO mBO = new LsjgdateBO();
		LsjgdateDTO[] lsjgdateDTO=mBO.loadAll("from LsjgdateDTO lsjgdateDTO order by lsjgdateDTO.zqdm");
		for(int i=0;i<lsjgdateDTO.length;i++){
			zqdm  =lsjgdateDTO[i].getZqdm();
			gpmcDTO= new GpmcDTO();
			gpmcDTO.setZqdm(zqdm);
			gpmcDTO = gBO.query(gpmcDTO);
			lsjgdateDTO[i].setZqmc(gpmcDTO.getZqmc());
		}
		
		request.setAttribute("LSJGDATE",lsjgdateDTO);

	}

	
}

