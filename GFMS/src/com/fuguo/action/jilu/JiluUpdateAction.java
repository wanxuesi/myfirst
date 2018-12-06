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
public class JiluUpdateAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JiluForm m = (JiluForm)form;
		int id = m.getId();
		JiluDTO tDTO = new JiluDTO();
		
		tDTO.setId(id);
		
		JiluBO tBO =new JiluBO();
		tDTO=tBO.query(tDTO);
		
		
//		将String的日期转换成Date类型；
	      SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	      SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	     
	    
	      
	      
	      
	      	String jysjStr =m.getJysj();
	        Date jysjDate = sdfTime.parse(jysjStr);
	        String zqdm =m.getZqdm();
	        String zqmc =m.getZqmc();
	        zqmc = zqmc.replace(" ","");
			zqmc = zqmc.replace(" ","");
	        String mmflag =m.getMmflag();
	        double cjjg =m.getCjjg();
	        int cjsl =m.getCjsl();
	        //double cjje =m.getCjje();
	        double cjje = cjjg*cjsl;
	        
 //需要根据股数，上海A股，还是深圳A股，等，进行计算清算金额；
	       
			String jysmc =m.getJysmc();
			
			String khdm =m.getKhdm();
			StockUtil sUtil = new StockUtil();
//			判断是否是基金，如果是基金，则不收印花税；
			boolean isJiJin = sUtil.isJiJin(zqdm);
			Double qsje=sUtil.getQsje(khdm,cjje,jysmc,mmflag,isJiJin);//实际买入时的花销，或者实际卖出时的花销；
			
			
			
	
			if(mmflag.equals("卖出")){

				cjsl=(cjsl*(-1));
			}
			
			
	        //double qsje =m.getQsje();
	        
	        String ywmc =m.getYwmc();
	        String zjzh =m.getZjzh();
	       
	        
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
	      
		
		tBO.update(tDTO);
		
		
		
	}

	

}

