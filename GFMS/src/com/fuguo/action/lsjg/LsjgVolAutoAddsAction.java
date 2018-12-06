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
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="��ʷ�۸�VOL�Զ��������";
		String queryDateSql=xxjh;
		
		//��ǰsystem���ڵ�ǰһ�죻
//		��Ҫǿ��ת��Ϊ����ʱ������ڣ�
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		String tmpDateStr = sdf.format(new java.util.Date());
		DateUtil dUtil  =new DateUtil();
		
		Date systemDate = sdf.parse(tmpDateStr);//�жϵ�ǰϵͳ���ڣ�Ӧ�ü�һ���������жϵ�ǰ���ڼӲ��ӣ�
		//��ȡ6��������ǰ�����ڣ�
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
			
//			��Ҫ�ж���û�и�lsjgDAte�����û�У�ֱ����ӣ�
			Date oldStartDate=null;
			Date oldEndDate=null;
			if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()!=null){
//				�Զ���ȡ��֤ȯ������lsjgDate���д�ŵĿ�ʼ���ںͽ�ֹ����
				oldStartDate=lsjgdateDTO.getDatestart();
				
				oldEndDate=lsjgdateDTO.getDate();
			}else if(lsjgdateDTO!=null &&lsjgdateDTO.getDate()==null){
				oldStartDate=lsjgdateDTO.getDatestart();
			}
			
//			�ĸ����ڶ����ˡ�
			//start_rqUtilDate��end_rqUtilDate��oldStartDate��oldEndDate�߼�������
			Date sinaStratDate=null;
			Date sinaEndDate = null;
			Date newLsjgDateStart=null;
			Date newLsjgDateEnd=null;
//			System.out.println("�ж������Ƿ������ (start_rqUtilDate.getTime()<=oldEndDate.getTime() &&start_rqUtilDate.getTime()>=oldEndDate.getTime()) && end_rqUtilDate.getTime()>oldStartDate.getTime()");
//			System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//			System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//			System.out.println("start_rqUtilDate.getTime"+start_rqUtilDate.getTime());
//			System.out.println("oldEndDate.getTime"+oldEndDate.getTime());
//			System.out.println("end_rqUtilDate.getTime"+end_rqUtilDate.getTime());
//			System.out.println("oldStartDate.getTime"+oldStartDate.getTime());
			

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
					if(oldStartDate.getTime()>=start_rqUtilDate.getTime()){
//						ֱ�Ӹ�ֵ���ɣ�˵��oldStartDate���������ˡ�
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
//					start_rqUtilDate��end_rqUtilDate��oldStartDate��oldEndDate�߼�������
					if(start_rqUtilDate.getTime()>=oldStartDate.getTime()){
						
						sinaStratDate = oldEndDate;
						sinaEndDate = end_rqUtilDate;
						
						newLsjgDateStart = oldStartDate;
						newLsjgDateEnd = end_rqUtilDate;
						
					}else{
						continue;//�����Ļ����ͽ�������ѭ����ֱ��������һ��zqdm
					}
					
								
							
				}
				
				
			}
			
			
			lsjgBO.updateLsjgsFromSina(sinaStratDate,sinaEndDate,zqdms[i],true);//��ǰ���ڵļ�¼Ҳ���룬�����Ȩ����Ϊnull���ͱ��Ϊtmp��
			
			if(lsjgdateDTO==null){
//				��Ҫ��ljsgDate��add��2������
//				�ٸ�����lsjgdate���Date
				lsjgdateDTO = new LsjgdateDTO();
				lsjgdateDTO.setId(zqdms[i]);
				lsjgdateDTO.setZqdm(zqdms[i]);
				
				lsjgdateDTO.setDatestart(newLsjgDateStart);
				lsjgdateDTO.setDate(newLsjgDateEnd);
//				��Ӹü�¼
				lsjgdateBO.add(lsjgdateDTO);
			}else{
//				�ٸ�����lsjgdate���Date
				lsjgdateDTO = new LsjgdateDTO();
				lsjgdateDTO.setId(zqdms[i]);
				lsjgdateDTO.setZqdm(zqdms[i]);
				
				lsjgdateDTO.setDatestart(newLsjgDateStart);
				lsjgdateDTO.setDate(newLsjgDateEnd);
//				���¸ü�¼
				lsjgdateBO.update(lsjgdateDTO);
			}
			

			
			


			
			
			
			
			
			
		}
		
		
		
		
		

		
		

		

		

		
		
		
		
		

	
	}
}

