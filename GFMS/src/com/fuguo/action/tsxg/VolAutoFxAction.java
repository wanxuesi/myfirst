//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.tsxg;

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

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class VolAutoFxAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO �Զ����ɷ������
//		��ȡids ��ת��Ϊint��
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κμ�¼��");
		}

	    //�������£�
		GpmcBO tBO = new GpmcBO();
		LsjgBO lBO = new LsjgBO();
		GpmcDTO tDTO;
		String sql="";
		DateUtil dUtil = new DateUtil();
		Date startDate = dUtil.getBeforeNDay(new Date(),6*30);
		String xingqiji =  dUtil.getWeekOfDate(new Date());
		Date redDate=null;
//		�ĸ������յ��㷨��
		if(xingqiji.equals("(��)")){
			redDate = dUtil.getBeforeNDay(new Date(),4);
		}else if(xingqiji.equals("(��)") ||xingqiji.equals("(��)")){
			redDate = dUtil.getBeforeNDay(new Date(),3);
		}else{
			//if(xingqiji.equals("(��)") ||xingqiji.equals("(��)") ||xingqiji.equals("(��)") ||xingqiji.equals("(һ)")   )
			
			redDate = dUtil.getBeforeNDay(new Date(),5);
			
		}
		SimpleDateFormat   sdf0   =   new   SimpleDateFormat("yyyy-MM-dd");
		//��Ҫ��redDate ȥʱ�䴦����ת��ΪString����ת��ΪDate
		String dateString = sdf0.format(redDate);
		redDate =sdf0.parse(dateString);
		java.sql.Date start_rq = new java.sql.Date(startDate.getTime());
		String dateSql=" and date(date)>='"+start_rq+"'";//Ĭ�ϲ���6�����ڵ����ݣ�
		List<GpmcDTO> listGpmcs = new ArrayList<GpmcDTO>();
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("MM-dd");
		for(int i=0;i<ids_Str.length;i++ ){
			sql="select ZQDM,ZQMC,DATE,CLOSE,VOLUME from lsjg where  DATE not in(select min(DATE) as DATE from lsjg where zqdm='"+ids_Str[i]+"') and zqdm='"+ids_Str[i]+"' and  high!=low "+dateSql+" order by volume asc fetch first 3 rows only";//�������У���С�ĵĳɽ��������棻
			//��ÿһ��id���д���
			//System.out.println(sql);
			
			tDTO = new GpmcDTO();
			tDTO.setId(ids_Str[i]);
			
			tDTO = tBO.query(tDTO);//����Ҫ�Բ�ѯ���������ݣ��������ڣ��ɽ�������3����ӣ�

			List listLsjg = lBO.sqlQuery(sql);
			//����������ʷ�۸��¼
			Iterator it = listLsjg.iterator();
			Map _map=null;
			Date dateDate=new Date();
			String dateStr="";
			Double volDouble=0.0d;
			String volStr="";
			String result="";
			int j=0;
			while(it.hasNext()){
				
				_map=(Map)it.next();
				dateDate = (Date)_map.get("DATE");
				volDouble = (Double)_map.get("VOLUME");
				//��ת����String��
				result=sdf.format(dateDate)+"["+String.valueOf(volDouble).replace(".0","")+"]";
				if(dateDate.getTime()>=redDate.getTime()){
					result="<font color='red'>"+dUtil.getWeekOfDate(dateDate)+result+"</font>";
				}
				//�ж�ʱ���Ƿ�����������ڣ�����ǣ��ͺ�ɫ
				if(j==0){
					tDTO.setOneDateVol(result);
				}else if(j==1){
					tDTO.setTwoDateVol(result);
				}else if(j==2){
					tDTO.setThreeDateVol(result);
				}else{
					
				}
				j++;
				
				
			}	
			
			listGpmcs.add(tDTO);
		}
		
		
		request.setAttribute("GPMCS",listGpmcs);
		
		
	}

	

}

