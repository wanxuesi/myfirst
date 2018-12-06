package com.fuguo.action.lsjg;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.form.LsjgForm;




/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LsjgShowJsonAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		LsjgForm m = (LsjgForm)form;
		String zqdm  = m.getZqdm();
		
		LsjgBO mBO = new LsjgBO();
		LsjgDTO[] lsjgDTO=mBO.loadAll("from LsjgDTO lsjgDTO where zqdm='"+zqdm+"' order by lsjgDTO.date");
		
		long lsjgTime;
		double open;
	    double high;
	    double low;
	    double close;

		
		StringBuffer sb  =new StringBuffer();
		sb.append("[");
		for(int i=0;i<lsjgDTO.length;i++){
			lsjgTime = lsjgDTO[i].getDate().getTime();
			open = lsjgDTO[i].getOpen();
			high = lsjgDTO[i].getHigh();
			low  = lsjgDTO[i].getLow();
			close = lsjgDTO[i].getClose();
			if(i<1){
				sb.append("["+lsjgTime+","+open+","+high+","+low+","+close+"]");
			}else{
				sb.append(",["+lsjgTime+","+open+","+high+","+low+","+close+"]");
			}
			
		}
		sb.append("]");
		
		//System.out.println(sb.toString());
		
		response.getWriter().write(sb.toString());

	}

	
}

