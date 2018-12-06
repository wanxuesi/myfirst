//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.DataBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.ListBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.form.JiluForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根
		JiluForm m = (JiluForm)form;
		
		Date txrq =new Date();
		long timeidLong = txrq.getTime();
		String timeid = Long.toString(timeidLong);//
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String juese = baseUserContext.getJuese();
		
		//将String的日期转换成Date类型；
	      SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	      SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	      DateUtil dUtil  =new DateUtil();
	    
	     
	        String jysjStr =m.getJysj();
	        Date jysjDate = sdfTime.parse(jysjStr);
	        String zqdm =m.getZqdm();
	        String mmflag =m.getMmflag();
	       
	       
	        
	        String zqmc =m.getZqmc();
//	        if(zqmc.length()==3){
//				zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
//		    
//			}
	        zqmc = zqmc.replace(" ","");
			zqmc = zqmc.replace(" ","");
	        
	        
	        double cjjg =m.getCjjg();
	        int cjsl =m.getCjsl();
	        double cjje = cjjg*cjsl;
	        //double cjje =m.getCjje();
	     
			String jysmc =m.getJysmc();
			
			StockUtil sUtil = new StockUtil();
			//判断是否是基金，如果是基金，则不收印花税；
			boolean isJiJin = sUtil.isJiJin(zqdm);
			
			Double qsje=sUtil.getQsje(idStr,cjje,jysmc,mmflag,isJiJin);//实际买入时的花销，或者实际卖出时的花销；
			
				
	
			if(mmflag.equals("卖出")){
				
				cjsl=(cjsl*(-1));
			}
			
			
	        //double qsje =m.getQsje();
	        String ywmc =m.getYwmc();
	        String zjzh =m.getZjzh();
	       
	        String khdm =m.getKhdm();
	        String gdmc =m.getGdmc();
	        String bz =m.getBz();
	        String jifl =m.getJifl();
	       
	    
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
		
		
	        JiluDTO tDTO = new JiluDTO();
	        tDTO.setTimeid(timeid);
	        
	        tDTO.setJysj(jysjDate);
	        tDTO.setZqdm(zqdm);
	        tDTO.setZqmc(zqmc);
	        tDTO.setMmflag(mmflag);
	        tDTO.setCjjg(cjjg);
	        tDTO.setCjsl(cjsl);
	        tDTO.setCjje(cjje);
	        tDTO.setQsje(qsje);
	        tDTO.setYwmc(ywmc);
	        tDTO.setZjzh(zjzh);
	        tDTO.setJysmc(jysmc);
	        tDTO.setKhdm(khdm);
	        tDTO.setGdmc(gdmc);
	        tDTO.setBz(bz);
	        tDTO.setJifl(jifl);
	        
	        
	        
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
		
	      
	        JiluBO t=new JiluBO();
	        
		    //此处肯定是已处理；逻辑运算和添加记录同时进行；
		
	        t.logic(tDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
		
		
	}

	

}

