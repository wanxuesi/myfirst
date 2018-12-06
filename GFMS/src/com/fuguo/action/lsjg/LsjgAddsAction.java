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
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="��ʷ�۸��������";
		String queryDateSql=xxjh;
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		DateUtil dUtil  =new DateUtil();
		//�����չ����������
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			 throw new BSWException("����д��ʼ���ڣ�"); 
		}else{
//			������ת��Ϊutil.Date����
			SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		
		String zqdm = qForm.getZqdm().trim();//֤ȯ����
//		֤ȯ����
		if(zqdm.equals("")){
			//����
		}else{
			Integer zqdmInteger = Integer.parseInt(zqdm);
			
			zqdm=zqdmInteger.toString();
			
		}
		
		LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		LsjgBO lsjgBO = new LsjgBO();
		
		lsjgdateDTO.setId(zqdm);
		lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
		//��Ҫ�ж���û�и�lsjgDAte�����û�У�ֱ����ӣ�
		Date oldStartDate=null;
		Date oldEndDate=null;
		if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()!=null){
//			�Զ���ȡ��֤ȯ������lsjgDate���д�ŵĿ�ʼ���ںͽ�ֹ����
			oldStartDate=lsjgdateDTO.getDatestart();
			
			oldEndDate=lsjgdateDTO.getDate();
		}else if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()==null){
			oldStartDate=lsjgdateDTO.getDatestart();
		}

		
		//�ĸ����ڶ����ˡ�
		//start_rqUtilDate��end_rqUtilDate��oldStartDate��oldEndDate�߼�������
		Date sinaStratDate=null;
		Date sinaEndDate = null;
		Date newLsjgDateStart=null;
		Date newLsjgDateEnd=null;
//		System.out.println("�ж������Ƿ������ (start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldEndDate.getTime()) && end_rqUtilDate.getTime()>oldStartDate.getTime()");
//		System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//		System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//		System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//		System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//		System.out.println("end_rqUtilDate.getTime"+end_rqUtilDate.getTime());
//		System.out.println("oldStartDate.getTime"+oldStartDate.getTime());

		
		//��Ҫ���µ���ʼ���ڣ�
		if(lsjgdateDTO==null){
			//��ʾ����û����ӹ��ù�Ʊ����ʷ�۸�
			//ֱ�Ӹ�ֵ���ɣ�
			sinaStratDate = start_rqUtilDate;
			sinaEndDate = end_rqUtilDate;
			
			newLsjgDateStart = start_rqUtilDate;
			newLsjgDateEnd = end_rqUtilDate;
			
		}else{
			

			if(lsjgdateDTO.getDate()==null){
				if(oldStartDate.getTime()>=start_rqUtilDate.getTime() && oldStartDate.getTime() <=end_rqUtilDate.getTime()){
//					ֱ�Ӹ�ֵ���ɣ�˵��oldStartDate���������ˡ�
					sinaStratDate = start_rqUtilDate;
					sinaEndDate = end_rqUtilDate;
					
					newLsjgDateStart = start_rqUtilDate;
					newLsjgDateEnd = end_rqUtilDate;				 
									
				}else{
					throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+_start_rq+"+~+"+_end_rq+")�Ĺ�Ʊ��ʷ�۸�û�а�����ʼ�۸�"); 
				}
				
							
			}else{
//				start_rqUtilDate��end_rqUtilDate��oldStartDate��oldEndDate�߼�������
				if(start_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime()){
	//				��ԭ���ķ�Χ�ڣ���oldStartDate��end_rqUtilDate�� 
					throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+_start_rq+"+~+"+_end_rq+")�Ĺ�Ʊ��ʷ�۸��Ѿ���ӣ�"); 
				}else if(start_rqUtilDate.getTime()<oldStartDate.getTime() && (end_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime())){
					//����н��������
					sinaStratDate = start_rqUtilDate;
					sinaEndDate = oldStartDate;
					
					newLsjgDateStart = start_rqUtilDate;
					newLsjgDateEnd = oldEndDate;
				}else if((start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldStartDate.getTime()) && end_rqUtilDate.getTime()>oldEndDate.getTime()){
	//				�ұ��н��������
					//���������
	//				����������죬��ֻ����ӵ����죻
					//end_rqUtilDate���������жϣ�������û��ɣ�
	//				��ǰsystem���ڵ�ǰһ�죻
					
					Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
					if(end_rqUtilDate.getTime()>endDate.getTime()){
						//endDate��Ҫ��ʽת��һ�£�
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
					//����������죬��ֻ����ӵ����죻
					//end_rqUtilDate���������жϣ�������û��ɣ�
	//				��ǰsystem���ڵ�ǰһ�죻
					
					Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
					if(end_rqUtilDate.getTime()>endDate.getTime()){
						//endDate��Ҫ��ʽת��һ�£�
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
					//��ȫ�����û�н����������
					//�ж�end_rqUtilDate��oldStartDate������2015-10-1,2015-10-2 �������2�죬�������2�졣
					int tmpNumbs = dUtil.getDateDiff(end_rqUtilDate,oldStartDate);
					if(tmpNumbs==2){
						sinaStratDate = start_rqUtilDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = start_rqUtilDate;
						newLsjgDateEnd = oldEndDate;
					}else{
						throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+_start_rq+"+~+"+_end_rq+")��ѡ��ʱ��κ�ԭ�������ʷ�۸��¼�м����������ѡ��ʱ��Σ�");
					}
				}else if(start_rqUtilDate.getTime()>oldEndDate.getTime()){
					
	//				��ȫ���ұ�û�н����������
					//�ж�oldEndDate��start_rqUtilDate������2015-10-1,2015-10-2 �������2�죬�������2�졣
					int tmpNumbs = dUtil.getDateDiff(oldEndDate,start_rqUtilDate);
					if(tmpNumbs==2){
						//��ǰsystem���ڵ�ǰһ�죻
						
						Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
						if(end_rqUtilDate.getTime()>endDate.getTime()){
							//endDate��Ҫ��ʽת��һ�£�
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
						throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+_start_rq+"+~+"+_end_rq+")��ѡ��ʱ��κ�ԭ�������ʷ�۸��¼�м����������ѡ��ʱ��Σ�");
					}
				}
			
			
				else{
					throw new BSWException("�ù�Ʊ("+zqdm+")��ʷ�۸� ��ʱ���("+_start_rq+"+~+"+_end_rq+")��ѡ��ʱ��κ�ԭ�������ʷ�۸��¼�м����������ѡ��ʱ��Σ�");
				}			
						
			}

			
			
			
					
			
			
		}
		
		
		lsjgBO.updateLsjgsFromSina(sinaStratDate,sinaEndDate,zqdm,true);
		
		if(lsjgdateDTO==null){
//			��Ҫ��ljsgDate��add��2������
//			�ٸ�����lsjgdate���Date
			lsjgdateDTO = new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);
			
			lsjgdateDTO.setDatestart(newLsjgDateStart);
			lsjgdateDTO.setDate(newLsjgDateEnd);
//			��Ӹü�¼
			lsjgdateBO.add(lsjgdateDTO);
		}else{
//			�ٸ�����lsjgdate���Date
			lsjgdateDTO = new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO.setZqdm(zqdm);
			
			lsjgdateDTO.setDatestart(newLsjgDateStart);
			lsjgdateDTO.setDate(newLsjgDateEnd);
//			���¸ü�¼
			lsjgdateBO.update(lsjgdateDTO);
		}
		

		
		



		

		
		
		
		
		

	
	}
}

