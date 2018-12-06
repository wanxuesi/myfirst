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

import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class LsjgQueryAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="��ʷ�۸��ѯ";
		String queryDateSql=xxjh;
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		
		//�����չ����������
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			
		}else{
//			������ת��Ϊutil.Date����
			SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		
		String zqdm = qForm.getZqdm().trim();//֤ȯ����
		String zqmc = qForm.getZqmc().trim();//֤ȯ����

		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		
		//����Ϊ��ѡ;;;;

		
		StringBuffer sb=new StringBuffer();
		
		//���ӵ�sql������sql�ɣ�
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select ID,ZQDM,ZQMC,DATE,CLOSE,OPEN,HIGH,LOW,VOLUME,FQYZ from lsjg where 1>0 ");
		
		DateUtil dateUtil=new DateUtil();

		
//		֤ȯ����
		if(zqdm.equals("")){
			//����
		}else{
			sb.append(" and zqdm='"+zqdm+"'");
		}
		


		
//		֤ȯ����
		if(zqmc.equals("")){
			//����
		}else{
			sb.append(" and zqmc like '%"+zqmc+"%'");
		}	
	
		
		
		
		
		if(start_rqUtilDate==null||end_rqUtilDate==null){
			//����
		}else{
//			
			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
			
			
			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
			
//			sb.append(" and ( (date(fssj)<='"+start_rq+"' and date(clsj)>='"+start_rq+"') or (date(fssj)<='"+last_rq+"' and date(clsj)>='"+last_rq+"') or ( '"+start_rq+"'<=date(fssj) and '"+last_rq+"'>=date(clsj) )  )");
			sb.append(" and ( (date(date)>='"+start_rq+"' and date(date)<='"+last_rq+"') or (date(date)>='"+start_rq+"' and date(date)<='"+last_rq+"') )");
		}
		sb.append(" order by zqdm,date desc");
		String sql=sb.toString();//����SQL��䡣
		
		//����ҵ���߼���
		LsjgBO tBO = new LsjgBO();
		//�õ�Map�͵�list
		List list = tBO.sqlQuery(sql);
		//���ɷ���Ҫ���LsjgDTO����
//		����list<Map>��
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		Map _map=null;
		LsjgDTO mDTO;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		//DateUtil dateUtil=new DateUtil();
		Date date;
		
		//,,,,,,,
		String fssjStr;
		String clsjStr;
		String fssjWeek;
		String clsjWeek;
		
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new LsjgDTO();
			mDTO.setId((Integer)_map.get("ID"));
			mDTO.setZqdm((String)_map.get("ZQDM"));
			mDTO.setZqmc((String)_map.get("ZQMC"));
			mDTO.setDate((Date)_map.get("DATE"));
			mDTO.setOpen((Double)_map.get("OPEN"));
			mDTO.setClose((Double)_map.get("CLOSE"));
			mDTO.setHigh((Double)_map.get("HIGH"));
			
			mDTO.setLow((Double)_map.get("LOW"));
			mDTO.setVolume((Double)_map.get("VOLUME"));
			mDTO.setFqyz((Double)_map.get("FQYZ"));
			

			listDTOs.add(mDTO);
		}
		
		request.setAttribute("LSJG",listDTOs);
		request.getSession().setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

