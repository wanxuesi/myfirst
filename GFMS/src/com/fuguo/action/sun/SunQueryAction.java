//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.sun;
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

import com.fuguo.bo.SunBO;
import com.fuguo.dto.SunDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.DateUtilWeekChina;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class SunQueryAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="ϵͳ�Ľ����";
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
		
		String jhzt = qForm.getJhzt().trim();//�ƻ�״̬
//		String sblx = qForm.getSblx().trim();// �豸����
		String clgc = qForm.getClgc().trim();//
		
		//����Ϊ��ѡ;;;;

		
		StringBuffer sb=new StringBuffer();
		
		//���ӵ�sql������sql�ɣ�
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select ID,FXR,DYDJ,FSSJ,CLSJ,TS,BDZ,JHZT,SBLX,SBMC,GZQXXX,CLGC,BANZU,FLAG6 from sun where 1>0 ");
		
		DateUtil dateUtil=new DateUtil();

		
//		�ƻ�״̬
		if(jhzt.equals("")){
			//����
		}else{
			sb.append(" and jhzt='"+jhzt+"'");
		}
		

////		�豸����
//		if(sblx.equals("")){
//			//����
//		}else{
//			sb.append(" and sblx='"+sblx+"'");
//		}		
		
		
//		�����豸
		if(clgc.equals("")){
			//����
		}else{
			sb.append(" and clgc like '%"+clgc+"%'");
		}	
	
		
		
		
		
		if(start_rqUtilDate==null||end_rqUtilDate==null){
			//����
		}else{
//			
			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
			
			
			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
			
//			sb.append(" and ( (date(fssj)<='"+start_rq+"' and date(clsj)>='"+start_rq+"') or (date(fssj)<='"+last_rq+"' and date(clsj)>='"+last_rq+"') or ( '"+start_rq+"'<=date(fssj) and '"+last_rq+"'>=date(clsj) )  )");
			sb.append(" and ( (date(fssj)>='"+start_rq+"' and date(fssj)<='"+last_rq+"') or (date(clsj)>='"+start_rq+"' and date(clsj)<='"+last_rq+"') )");
		}
		sb.append(" order by JHZT DESC,FSSJ");
		String sql=sb.toString();//����SQL��䡣
		
		//����ҵ���߼���
		SunBO tBO = new SunBO();
		//�õ�Map�͵�list
		List list = tBO.sqlQuery(sql);
		//���ɷ���Ҫ���SunDTO����
//		����list<Map>��
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		Map _map=null;
		SunDTO mDTO;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		//DateUtil dateUtil=new DateUtil();
		Date fssj;
		Date clsj;
		
		//ID,FXR,FSSJ,CLSJ,TS,BDZ,JHZT,SBLX,SBMC,GZQXXX,CLGC,BANZU
		
		String fssjStr;
		String clsjStr;
		String fssjWeek;
		String clsjWeek;
		
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new SunDTO();
			mDTO.setId((Integer)_map.get("ID"));
			mDTO.setFxr((String)_map.get("FXR"));
			mDTO.setFssj((Date)_map.get("FSSJ"));
			mDTO.setClsj((Date)_map.get("CLSJ"));
			mDTO.setTs((Integer)_map.get("TS"));
			mDTO.setBdz((String)_map.get("BDZ"));
			mDTO.setJhzt((String)_map.get("JHZT"));
			
			mDTO.setSblx((String)_map.get("SBLX"));
			mDTO.setSbmc((String)_map.get("SBMC"));
			//mDTO.setSunxx((String)_map.get("GZQXXX"));
			mDTO.setClgc((String)_map.get("CLGC"));
			mDTO.setBanzu((String)_map.get("BANZU"));
			
			mDTO.setFlag6((String)_map.get("FLAG6"));
			

			mDTO.setBdz((String)_map.get("BDZ"));
			if((String)_map.get("DYDJ")==null){
				
				mDTO.setDydj("");
			}else{
				mDTO.setDydj((String)_map.get("DYDJ"));
			}

			fssj=mDTO.getFssj();
			if(fssj!=null&& !fssj.equals("")){
				fssjStr=sdf.format(fssj);
				fssjWeek = dateUtil.getWeekOfDate(fssj);
				fssjStr=fssjStr.replace(" ","<br>");
				mDTO.setFssjStr(fssjStr+fssjWeek);
			}
	
			
			
			clsj=mDTO.getClsj();	
			
			if(clsj!=null&& !clsj.equals("")){
				clsjStr=sdf.format(clsj);
				clsjWeek = dateUtil.getWeekOfDate(clsj);
				clsjStr=clsjStr.replace(" ","<br>");			
				mDTO.setClsjStr(clsjStr+clsjWeek);
			}
			
//			�Ե�ѹ�ȼ�����ȥ��kV��
			mDTO.setDydj(mDTO.getDydj().replace("kV",""));

			listDTOs.add(mDTO);
		}
		
		request.setAttribute("SUN",listDTOs);
		request.getSession().setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

