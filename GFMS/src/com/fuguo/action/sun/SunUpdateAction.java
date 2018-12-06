//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.sun;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.SunBO;
import com.fuguo.dto.SunDTO;
import com.fuguo.form.SunForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SunUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SunForm m = (SunForm)form;
		int id = m.getId();
		SunDTO tDTO = new SunDTO();
		
		tDTO.setId(id);
		
		SunBO tBO =new SunBO();
		tDTO=tBO.query(tDTO);
		
		
//		将String的日期转换成Date类型；
	      SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	      SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	     
	    
	      
	      
	      
	       // String sunxx = m.getSunxx();
	        String clgc = m.getClgc();
	        String fssj = m.getFssj();
	        String clsj = m.getClsj();
	        Date fssjDate = sdfTime.parse(fssj);
		    Date clsjDate = sdfTime.parse(clsj);
	      
	        String fxr = m.getFxr();
	       
	        String ysr = m.getYsr();
	        String tbr = m.getTbr();
	       
	        String bz = m.getBz();
	        String jhzt = m.getJhzt();
	        String flag1 = m.getFlag1();
	        String flag2 = m.getFlag2();
	        String flag3 = m.getFlag3();
	        String flag4 = m.getFlag4();
	        String flag5 = m.getFlag5();
	        String flag6 = m.getFlag6();
	        String flag7 = m.getFlag7();
	        String flag8 = m.getFlag8();
	        String flag9 = m.getFlag9();
	        String flag10 = m.getFlag10();
	        
	        

	       // tDTO.setSunxx(sunxx);
	        tDTO.setClgc(clgc);
	        tDTO.setFssj(fssjDate);
	        tDTO.setClsj(clsjDate);

	        tDTO.setFxr(fxr);

	        tDTO.setYsr(ysr);
	        tDTO.setTbr(tbr);
	       
	        tDTO.setBz(bz);
	        tDTO.setJhzt(jhzt);
	        tDTO.setFlag1(flag1);
	        tDTO.setFlag2(flag2);
	        tDTO.setFlag3(flag3);
	        tDTO.setFlag4(flag4);
	        tDTO.setFlag5(flag5);
	        tDTO.setFlag6(flag6);
	        tDTO.setFlag7(flag7);
	        tDTO.setFlag8(flag8);
	        tDTO.setFlag9(flag9);
	        tDTO.setFlag10(flag10);
	      
		
		tBO.update(tDTO);
		
		
		
	}

	

}

