//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;

import com.fuguo.bo.ConfigBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.form.ConfigForm;

/**
 * MyEclipse Struts Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class ConfigUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ConfigForm m = (ConfigForm)form;
		
	 	String userid =m.getUserid();
        int onoff =m.getOnoff();
        double confirmbfb =m.getConfirmbfb();
        double yinhuashuibuy =m.getYinhuashuibuy();
        double yinhuashuisell =m.getYinhuashuisell();
        double onegupiaocangweibfb =m.getOnegupiaocangweibfb();
        double onejiflcangweibfb =m.getOnejiflcangweibfb();
        double yongjin =m.getYongjin();
        int yongjinmin =m.getYongjinmin();
        double guohufei =m.getGuohufei();
        int guohufeimin =m.getGuohufeimin();
        
        String flag1 = m.getFlag1().trim();// «ªÚ’ﬂ∑Ò£ª
	    if(flag1.trim().equals("")){
	    	flag1 = "∑Ò";
	    }
		

		ConfigDTO tDTO = new ConfigDTO();

		tDTO.setId(userid);
		ConfigBO tBO = new ConfigBO();
		tDTO = tBO.query(tDTO);

		tDTO.setUserid(userid);
        tDTO.setOnoff(onoff);
        tDTO.setConfirmbfb(confirmbfb);
        tDTO.setYinhuashuibuy(yinhuashuibuy);
        tDTO.setYinhuashuisell(yinhuashuisell);
        tDTO.setOnegupiaocangweibfb(onegupiaocangweibfb);
        tDTO.setOnejiflcangweibfb(onejiflcangweibfb);
        tDTO.setYongjin(yongjin);
        tDTO.setYongjinmin(yongjinmin);
        tDTO.setGuohufei(guohufei);
        tDTO.setGuohufeimin(guohufeimin);
        tDTO.setFlag1(flag1);
		tBO.update(tDTO);

	}

}
