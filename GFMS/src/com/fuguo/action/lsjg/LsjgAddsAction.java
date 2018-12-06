//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.lsjg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.LsjgBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.dto.LsjgDTO;
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
public class LsjgAddsAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="历史价格批量添加";
		String queryDateSql=xxjh;
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		DateUtil dUtil  =new DateUtil();
		//类似日工作里的日期
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			 throw new BSWException("请填写起始日期！"); 
		}else{
//			将它们转换为util.Date类型
			SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		
		String zqdm = qForm.getZqdm().trim();//证券代码
//		证券代码
		if(zqdm.equals("")){
			//忽略
		}else{
			Integer zqdmInteger = Integer.parseInt(zqdm);
			
			zqdm=zqdmInteger.toString();
			
		}
		
		LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		LsjgBO lsjgBO = new LsjgBO();
		
		lsjgdateDTO.setId(zqdm);
		lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
		//需要判断有没有该lsjgDAte，如果没有，直接添加；
		Date oldStartDate=null;
		Date oldEndDate=null;
		if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()!=null){
//			自动获取该证券代码在lsjgDate表中存放的开始日期和截止日期
			oldStartDate=lsjgdateDTO.getDatestart();
			
			oldEndDate=lsjgdateDTO.getDate();
		}else if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()==null){
			oldStartDate=lsjgdateDTO.getDatestart();
		}

		
		//四个日期都有了。
		//start_rqUtilDate，end_rqUtilDate，oldStartDate，oldEndDate逻辑分析；
		Date sinaStratDate=null;
		Date sinaEndDate = null;
		Date newLsjgDateStart=null;
		Date newLsjgDateEnd=null;
//		System.out.println("判断条件是否成立？ (start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldEndDate.getTime()) && end_rqUtilDate.getTime()>oldStartDate.getTime()");
//		System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//		System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//		System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//		System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//		System.out.println("end_rqUtilDate.getTime"+end_rqUtilDate.getTime());
//		System.out.println("oldStartDate.getTime"+oldStartDate.getTime());

		
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
				if(oldStartDate.getTime()>=start_rqUtilDate.getTime() && oldStartDate.getTime() <=end_rqUtilDate.getTime()){
//					直接赋值即可；说明oldStartDate这个点包含了。
					sinaStratDate = start_rqUtilDate;
					sinaEndDate = end_rqUtilDate;
					
					newLsjgDateStart = start_rqUtilDate;
					newLsjgDateEnd = end_rqUtilDate;				 
									
				}else{
					throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+_start_rq+"+~+"+_end_rq+")的股票历史价格没有包括开始价格！"); 
				}
				
							
			}else{
//				start_rqUtilDate，end_rqUtilDate，oldStartDate，oldEndDate逻辑分析；
				if(start_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime()){
	//				在原来的范围内；【oldStartDate，end_rqUtilDate】 
					throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+_start_rq+"+~+"+_end_rq+")的股票历史价格已经添加！"); 
				}else if(start_rqUtilDate.getTime()<oldStartDate.getTime() && (end_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime())){
					//左边有交集的情况
					sinaStratDate = start_rqUtilDate;
					sinaEndDate = oldStartDate;
					
					newLsjgDateStart = start_rqUtilDate;
					newLsjgDateEnd = oldEndDate;
				}else if((start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldStartDate.getTime()) && end_rqUtilDate.getTime()>oldEndDate.getTime()){
	//				右边有交集的情况
					//第五种情况
	//				如果超出今天，则只能添加到今天；
					//end_rqUtilDate对他进行判断；、、还没完成；
	//				当前system日期的前一天；
					
					Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
					if(end_rqUtilDate.getTime()>endDate.getTime()){
						//endDate需要格式转换一下；
						SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
						String endDateStr = sdf.format(endDate);
						Date tmp =sdf.parse(endDateStr);
						end_rqUtilDate = tmp;
					}
					
					sinaStratDate = oldEndDate;
					sinaEndDate = end_rqUtilDate;
					
					newLsjgDateStart = oldStartDate;
					newLsjgDateEnd = end_rqUtilDate;
				}else if(start_rqUtilDate.getTime()<oldStartDate.getTime() && end_rqUtilDate.getTime()>oldEndDate.getTime()){
					//如果超出今天，则只能添加到今天；
					//end_rqUtilDate对他进行判断；、、还没完成；
	//				当前system日期的前一天；
					
					Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
					if(end_rqUtilDate.getTime()>endDate.getTime()){
						//endDate需要格式转换一下；
						SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
						String endDateStr = sdf.format(endDate);
						Date tmp =sdf.parse(endDateStr);
						end_rqUtilDate = tmp;
					}
					sinaStratDate = start_rqUtilDate;
					sinaEndDate = end_rqUtilDate;
					
					newLsjgDateStart = start_rqUtilDate;
					newLsjgDateEnd = end_rqUtilDate;
				}else if(end_rqUtilDate.getTime()<oldStartDate.getTime()){
					//完全在左边没有交集的情况；
					//判断end_rqUtilDate，oldStartDate，比如2015-10-1,2015-10-2 天数相差2天，如果就是2天。
					int tmpNumbs = dUtil.getDateDiff(end_rqUtilDate,oldStartDate);
					if(tmpNumbs==2){
						sinaStratDate = start_rqUtilDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = start_rqUtilDate;
						newLsjgDateEnd = oldEndDate;
					}else{
						throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+_start_rq+"+~+"+_end_rq+")的选择时间段和原有添加历史价格记录有间隔，请重新选择时间段！");
					}
				}else if(start_rqUtilDate.getTime()>oldEndDate.getTime()){
					
	//				完全在右边没有交集的情况；
					//判断oldEndDate，start_rqUtilDate，比如2015-10-1,2015-10-2 天数相差2天，如果就是2天。
					int tmpNumbs = dUtil.getDateDiff(oldEndDate,start_rqUtilDate);
					if(tmpNumbs==2){
						//当前system日期的前一天；
						
						Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
						if(end_rqUtilDate.getTime()>endDate.getTime()){
							//endDate需要格式转换一下；
							SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
							String endDateStr = sdf.format(endDate);
							Date tmp =sdf.parse(endDateStr);
							end_rqUtilDate = tmp;
						}
						
						sinaStratDate = start_rqUtilDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = oldStartDate;
						newLsjgDateEnd = end_rqUtilDate;
					}else{
						throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+_start_rq+"+~+"+_end_rq+")的选择时间段和原有添加历史价格记录有间隔，请重新选择时间段！");
					}
				}
			
			
				else{
					throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+_start_rq+"+~+"+_end_rq+")的选择时间段和原有添加历史价格记录有间隔，请重新选择时间段！");
				}			
						
			}

			
			
			
					
			
			
		}
		
		
		lsjgBO.updateLsjgsFromSina(sinaStratDate,sinaEndDate,zqdm,true);
		
		if(lsjgdateDTO==null){
//			需要在ljsgDate中add的2个日期
//			再更新下lsjgdate里的Date
			lsjgdateDTO = new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);
			
			lsjgdateDTO.setDatestart(newLsjgDateStart);
			lsjgdateDTO.setDate(newLsjgDateEnd);
//			添加该记录
			lsjgdateBO.add(lsjgdateDTO);
		}else{
//			再更新下lsjgdate里的Date
			lsjgdateDTO = new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);
			
			lsjgdateDTO.setDatestart(newLsjgDateStart);
			lsjgdateDTO.setDate(newLsjgDateEnd);
//			更新该记录
			lsjgdateBO.update(lsjgdateDTO);
		}
		

		
		



		

		
		
		
		
		

	
	}
}

