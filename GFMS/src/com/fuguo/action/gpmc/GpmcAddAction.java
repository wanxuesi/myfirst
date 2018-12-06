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
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class GpmcAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		StockUtil sUtil = new StockUtil();

		  GpmcForm m = (GpmcForm)form;
		 String gpdm =m.getGpdm();
		 String gpmc =m.getGpmc();
		 String flag1 = m.getFlag1();
		//封装成数据传输对象
	      GpmcDTO tDTO = new GpmcDTO();
	      
	      tDTO.setZqdm(gpdm);
	      tDTO.setZqmc(gpmc);
	      tDTO.setFlag1(flag1);
	      
	      //格式化一下；极度简化，存入数据库；
	      Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());
	      tDTO.setFlag2(sUtil.gupiaoORjijin(zqdmInteger));
			String zqdm=zqdmInteger.toString();
			tDTO.setZqdm(zqdm);
			gpmc = gpmc.replace(" ","");
			gpmc = gpmc.replace(" ","");
			tDTO.setZqmc(gpmc);
			
	     
		
		GpmcBO tBO =new GpmcBO();
		tBO.add(tDTO);
		
		
	}

	

}

