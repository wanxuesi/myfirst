//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ccgp;

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

import com.fuguo.bo.DataBO;
import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.ListBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.form.JiluForm;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class CqAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 TODO 自动生成方法存根
		JiluForm m = (JiluForm)form;
		
		JiluBO tBO =new JiluBO();
//		将String的日期转换成Date类型；
	    SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	    
	    int numb = m.getNumb();//order中的总数量；
		int baseNumb = m.getBaseNumb();
		int songNumb = m.getSongNumb();
		Double paiDouble = m.getPaiNumb();
		String jysjStr =m.getJysj();
	    Date jysjDate = sdfTime.parse(jysjStr);
	    
	    String zqdm =m.getZqdm();
        String zqmc =m.getZqmc();

        zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
	    
	    
		Date txrq =new Date();
		long timeidLong = txrq.getTime();
		String timeid = Long.toString(timeidLong);//
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String xm =	baseUserContext.getXm().trim();
		String zjzh = baseUserContext.getNx();//资金账号
		
		double cjjg =0;
	    
		//if(songNumb>0.1){}
		

		String mmflag ="买入";
	    GpmcBO gpmcBO = new GpmcBO();
	    GpmcDTO gpmcDTO  =new GpmcDTO();
	    DataBO dBO = new DataBO();
	    gpmcDTO.setId(zqdm);
	    gpmcDTO = gpmcBO.query(gpmcDTO);
        String jysmc=gpmcDTO.getFlag1();//要根据证券代码获取；
	        
        
				
	
			
			
			
	        String ywmc ="";
	        
	       
	        String khdm =idStr;
	        String gdmc =xm;
	        String bz ="除权";
	        
	       
	        double cjje = 0d;
			double qsje=0d;//这时就是一样
	        String flag1 = "已处理";
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
	       
	        tDTO.setCjje(cjje);
	        tDTO.setQsje(qsje);
	        tDTO.setYwmc(ywmc);
	        tDTO.setZjzh(zjzh);
	        tDTO.setJysmc(jysmc);
	        tDTO.setKhdm(khdm);
	        tDTO.setGdmc(gdmc);
	        tDTO.setBz(bz);
	        
	        
	        
	        
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
		
	        JiluDTO paiDTO = new JiluDTO();
	        paiDTO.setTimeid(timeid);
	        
	        paiDTO.setJysj(jysjDate);
	        paiDTO.setZqdm(zqdm);
	        paiDTO.setZqmc(zqmc);
	        paiDTO.setMmflag("卖出");
	        paiDTO.setCjsl(0);
	        
	        paiDTO.setCjjg(0);		       
//	        paiDTO.setCjje(cjje);
//	        paiDTO.setQsje(qsje);
	        
	        paiDTO.setYwmc(ywmc);
	        paiDTO.setZjzh(zjzh);
	        paiDTO.setJysmc(jysmc);
	        paiDTO.setKhdm(khdm);
	        paiDTO.setGdmc(gdmc);
	        paiDTO.setBz("除息");
	        
	        
	        
	        
	        paiDTO.setFlag1(flag1);
	        paiDTO.setFlag2(flag2);
	        paiDTO.setFlag3(flag3);
	        paiDTO.setFlag4(flag4);
	        paiDTO.setFlag5(flag5);
	        paiDTO.setFlag6(flag6);
	        paiDTO.setFlag7(flag7);
	        paiDTO.setFlag8(flag8);
	        paiDTO.setFlag9(flag9);
	        paiDTO.setFlag10(flag10);
	        
	        
	        
        ListBO lBO = new ListBO();
        ListDTO lDTO = new ListDTO();
        List listlist =lBO.sqlQuery("select id,zqdm,cysl,jifl from list where  flag1='"+idStr+"' and  zqdm='"+zqdm+"'");
		
        

        
		Iterator itlist = listlist.iterator();


//		一种是有该jifl；
		Map _maplist=null;
		Integer cysllist=0;
		
		Integer idList = 0;
		String jifl="";
		
		double k = 0d;
		if(songNumb>0.1){
			k = songNumb/baseNumb;
		}
		
		double kpai=0d;
		 if(paiDouble>0.001){
			 	
	        	kpai=paiDouble/baseNumb;
	     }
		while(itlist.hasNext()){
			_maplist=(Map)itlist.next();
			idList = (Integer)_maplist.get("ID");
			cysllist = (Integer)_maplist.get("CYSL");//原来的持有数量
			
			jifl = (String)_maplist.get("JIFL");//
			
			
	        
	        if(songNumb>0.1){
	        	tDTO.setJifl(jifl);
				int cjsl =(int)(cysllist*k); 
		        tDTO.setCjsl(cjsl);	
	        	tBO.add(tDTO);
	        	
	        	lDTO.setId(idList);
//				要算下叠加后的持有数量；。
				lDTO.setCysl(cysllist+cjsl);					
				//order中是空的话，说明list中肯定为空；直接添加即可；
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setJifl(jifl);	
				lDTO.setFlag1(idStr);
				lBO.update(lDTO);
			}
	       
	        
	        if(paiDouble>0.001){
	        	paiDTO.setJifl(jifl);
		        //需要计算该交易分类的cjje
		        paiDTO.setCjje(cysllist*kpai);
		        
		        paiDTO.setQsje(cysllist*kpai);
		        
		        tBO.add(paiDTO);
		        
		        DataDTO dDTO = new DataDTO();
				dDTO.setName("资金进出");
				
				dDTO.setShuju(cysllist*kpai);
				dDTO.setDate(jysjDate);
				dDTO.setFlag1(zqmc+"除息清算金额");
				dDTO.setFlag2(idStr);
				dBO.add(dDTO);
	        }
	        
	        
	        
						
		}
		

        OrderBO oBO = new OrderBO();
		OrderDTO oDTO = new OrderDTO();
        
		List list =oBO.sqlQuery("select id,zqdm,cysl,cbj from order where  flag1='"+idStr+"' and  zqdm='"+zqdm+"'");

//		说明有该记录，直接在orderUpdate;
		oDTO.setZqdm(zqdm);
		oDTO.setZqmc(zqmc);
		
		Iterator it = list.iterator();
		
		Map _map=null;
		Integer cysl=0;
		double cbj=0;
		Integer id=0;
		if(it.hasNext()){
			_map=(Map)it.next();
			id = (Integer)_map.get("ID");
			cysl = (Integer)_map.get("CYSL");//原来的持有数量
			
			cbj  =(Double)_map.get("CBJ");//原来的成本价
			//cjjes = (Double)_map.get("CJJES");
		}
		
		
//		要算下叠加后的持有数量；。
		oDTO.setId(id);
		oDTO.setCysl((int)(cysl*(1+k)));
		
		//要算下买入叠加的成本价的。
		oDTO.setCbj((cysl*cbj-cysl*kpai)/(cysl*(1+k)));
		
		oDTO.setFlag1(idStr);
		
		//只要一个条件满足，就更新成本价和持有数量；
		if(songNumb>0.1||paiDouble>0.001){
			oBO.update(oDTO);
		}
		
	}

	

}

