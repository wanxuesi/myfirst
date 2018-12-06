//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.JiluBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluUpdateSpcAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		int id = m.getId();
		JiluDTO tDTO = new JiluDTO();
		
		tDTO.setId(id);
		
		JiluBO tBO =new JiluBO();
		tDTO=tBO.query(tDTO);
		
		

	        
	        
	        String flag2  = tDTO.getFlag2();
	        String flag3  = tDTO.getFlag3();
	        if(flag2==null||flag2.equals("")){
	        	
	        	flag2="1";
	        	flag3 = Integer.toString(id);//flag3需要加识别串码直（接用2者的第一个id号就行）；
	        }else{
	        	flag2="";
	        	flag3="";//flag3需要清除别串码；
	        }
	        
	        
	        tDTO.setFlag2(flag2);
	        tDTO.setFlag3(flag3);
	      
		
		tBO.update(tDTO);
		
		request.setAttribute("ZQMC",tDTO.getZqmc());
		request.setAttribute("ZQDM",tDTO.getZqdm());
		
	}

	

}

