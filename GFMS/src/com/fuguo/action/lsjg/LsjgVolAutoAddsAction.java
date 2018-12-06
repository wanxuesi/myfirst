//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.lsjg;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LsjgVolAutoAddsAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="历史价格VOL自动批量添加";
		String queryDateSql=xxjh;
		
		//当前system日期的前一天；
//		需要强制转换为不带时间的日期；
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		String tmpDateStr = sdf.format(new java.util.Date());
		DateUtil dUtil  =new DateUtil();
		
		Date systemDate = sdf.parse(tmpDateStr);//判断当前系统日期，应该加一个参数，判断当前日期加不加；
		//获取6个月内天前的日期；
		Date start_rqUtilDate = dUtil.getBeforeNDay(systemDate,3);
		
		Date end_rqUtilDate = dUtil.getBeforeNDay(systemDate,1);
		
		
		
		GpmcBO mBO = new GpmcBO();
		GpmcDTO[] mDTOs=null;
		
		String[] zqdms=null;
		
		mDTOs=mBO.loadAll("from GpmcDTO gpmcDTO  where gpmcDTO.volflag=1 order by gpmcDTO.flag1");
		
		zqdms = new String[mDTOs.length];
		for(int i=0;i<mDTOs.length;i++){
			zqdms[i] = mDTOs[i].getZqdm();
		}
		
		LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		LsjgBO lsjgBO = new LsjgBO();
		
		
		for(int i=0;i<zqdms.length;i++){
			lsjgdateDTO.setId(zqdms[i]);
			lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
			
//			需要判断有没有该lsjgDAte，如果没有，直接添加；
			Date oldStartDate=null;
			Date oldEndDate=null;
			if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()!=null){
//				自动获取该证券代码在lsjgDate表中存放的开始日期和截止日期
				oldStartDate=lsjgdateDTO.getDatestart();
				
				oldEndDate=lsjgdateDTO.getDate();
			}else if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()==null){
				oldStartDate=lsjgdateDTO.getDatestart();
			}
			
//			四个日期都有了。
			//start_rqUtilDate，end_rqUtilDate，oldStartDate，oldEndDate逻辑分析；
			Date sinaStratDate=null;
			Date sinaEndDate = null;
			Date newLsjgDateStart=null;
			Date newLsjgDateEnd=null;
//			System.out.println("判断条件是否成立？ (start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldEndDate.getTime()) && end_rqUtilDate.getTime()>oldStartDate.getTime()");
//			System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//			System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//			System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//			System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//			System.out.println("end_rqUtilDate.getTime"+end_rqUtilDate.getTime());
//			System.out.println("oldStartDate.getTime"+oldStartDate.getTime());
			

			//需要更新的起始日期；
			if(lsjgdateDTO==null){
				//表示从来没有添加过该股票的历史价格
				//直接赋值即可；
				sinaStratDate = start_rqUtilDate;
				sinaEndDate = end_rqUtilDate;
				
				newLsjgDateStart = start_rqUtilDate;
				newLsjgDateEnd = end_rqUtilDate;
				
			}else{
				

				if(lsjgdateDTO.getDate()==null){
					if(oldStartDate.getTime()>=start_rqUtilDate.getTime()){
//						直接赋值即可；说明oldStartDate这个点包含了。
						sinaStratDate = start_rqUtilDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = start_rqUtilDate;
						newLsjgDateEnd = end_rqUtilDate;				 
										
					}else{
						
						sinaStratDate = oldStartDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = oldStartDate;
						newLsjgDateEnd = end_rqUtilDate;
					}
					
								
				}else{
//					start_rqUtilDate，end_rqUtilDate，oldStartDate，oldEndDate逻辑分析；
					if(start_rqUtilDate.getTime()>=oldStartDate.getTime()){
						
						sinaStratDate = oldEndDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = oldStartDate;
						newLsjgDateEnd = end_rqUtilDate;
						
					}else{
						continue;//包含的话，就结束本次循环，直接跳到下一个zqdm
					}
					
								
							
				}
				
				
			}
			
			
			lsjgBO.updateLsjgsFromSina(sinaStratDate,sinaEndDate,zqdms[i],true);//当前日期的记录也加入，如果复权因子为null，就标记为tmp；
			
			if(lsjgdateDTO==null){
//				需要在ljsgDate中add的2个日期
//				再更新下lsjgdate里的Date
				lsjgdateDTO = new LsjgdateDTO();
				lsjgdateDTO.setId(zqdms[i]);
				lsjgdateDTO.setZqdm(zqdms[i]);
				
				lsjgdateDTO.setDatestart(newLsjgDateStart);
				lsjgdateDTO.setDate(newLsjgDateEnd);
//				添加该记录
				lsjgdateBO.add(lsjgdateDTO);
			}else{
//				再更新下lsjgdate里的Date
				lsjgdateDTO = new LsjgdateDTO();
				lsjgdateDTO.setId(zqdms[i]);
				lsjgdateDTO.setZqdm(zqdms[i]);
				
				lsjgdateDTO.setDatestart(newLsjgDateStart);
				lsjgdateDTO.setDate(newLsjgDateEnd);
//				更新该记录
				lsjgdateBO.update(lsjgdateDTO);
			}
			

			
			


			
			
			
			
			
			
		}
		
		
		
		
		

		
		

		

		

		
		
		
		
		

	
	}
}

