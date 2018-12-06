package com.fuguo.action.mygpmc;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;

import com.fuguo.bo.LsjgdateBO;
import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.MygpmcForm;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		MygpmcForm m = (MygpmcForm)form;
		LsjgdateBO lsjgdateBO= new LsjgdateBO();
		LsjgdateDTO lsjgdateDTO;
		MygpmcBO mBO = new MygpmcBO();
		MygpmcDTO[] dataDTO=mBO.loadAll("from MygpmcDTO mygpmcDTO where flag2='"+idStr+"'order by mygpmcDTO.flag1,zqdm");
		String zqdm = "";
		
		for(int i=0;i<dataDTO.length;i++){
			zqdm = dataDTO[i].getZqdm();
			lsjgdateDTO=new LsjgdateDTO();
			lsjgdateDTO.setZqdm(zqdm);
			lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
			if(lsjgdateDTO!=null){
				dataDTO[i].setDatestart(lsjgdateDTO.getDatestart());
				
				dataDTO[i].setDate(lsjgdateDTO.getDate());
			}
			
		}
		
		request.setAttribute("MYGPMC",dataDTO);

	}

	
}

