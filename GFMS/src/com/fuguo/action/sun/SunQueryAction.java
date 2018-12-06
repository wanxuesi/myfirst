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
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="系统改进意见";
		String queryDateSql=xxjh;
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		
		//类似日工作里的日期
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			
		}else{
//			将它们转换为util.Date类型
			SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		
		String jhzt = qForm.getJhzt().trim();//计划状态
//		String sblx = qForm.getSblx().trim();// 设备类型
		String clgc = qForm.getClgc().trim();//
		
		//以下为多选;;;;

		
		StringBuffer sb=new StringBuffer();
		
		//复杂的sql还是用sql吧！
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select ID,FXR,DYDJ,FSSJ,CLSJ,TS,BDZ,JHZT,SBLX,SBMC,GZQXXX,CLGC,BANZU,FLAG6 from sun where 1>0 ");
		
		DateUtil dateUtil=new DateUtil();

		
//		计划状态
		if(jhzt.equals("")){
			//忽略
		}else{
			sb.append(" and jhzt='"+jhzt+"'");
		}
		

////		设备类型
//		if(sblx.equals("")){
//			//忽略
//		}else{
//			sb.append(" and sblx='"+sblx+"'");
//		}		
		
		
//		故障设备
		if(clgc.equals("")){
			//忽略
		}else{
			sb.append(" and clgc like '%"+clgc+"%'");
		}	
	
		
		
		
		
		if(start_rqUtilDate==null||end_rqUtilDate==null){
			//忽略
		}else{
//			
			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
			
			
			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
			
//			sb.append(" and ( (date(fssj)<='"+start_rq+"' and date(clsj)>='"+start_rq+"') or (date(fssj)<='"+last_rq+"' and date(clsj)>='"+last_rq+"') or ( '"+start_rq+"'<=date(fssj) and '"+last_rq+"'>=date(clsj) )  )");
			sb.append(" and ( (date(fssj)>='"+start_rq+"' and date(fssj)<='"+last_rq+"') or (date(clsj)>='"+start_rq+"' and date(clsj)<='"+last_rq+"') )");
		}
		sb.append(" order by JHZT DESC,FSSJ");
		String sql=sb.toString();//生成SQL语句。
		
		//调用业务逻辑层
		SunBO tBO = new SunBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql);
		//生成符合要求的SunDTO数据
//		解析list<Map>；
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
			
//			对电压等级处理（去掉kV）
			mDTO.setDydj(mDTO.getDydj().replace("kV",""));

			listDTOs.add(mDTO);
		}
		
		request.setAttribute("SUN",listDTOs);
		request.getSession().setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

