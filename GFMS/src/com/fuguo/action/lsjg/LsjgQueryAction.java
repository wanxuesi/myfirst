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
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		String xxjh="历史价格查询";
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
		
		String zqdm = qForm.getZqdm().trim();//证券代码
		String zqmc = qForm.getZqmc().trim();//证券名称

		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		
		//以下为多选;;;;

		
		StringBuffer sb=new StringBuffer();
		
		//复杂的sql还是用sql吧！
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select ID,ZQDM,ZQMC,DATE,CLOSE,OPEN,HIGH,LOW,VOLUME,FQYZ from lsjg where 1>0 ");
		
		DateUtil dateUtil=new DateUtil();

		
//		证券代码
		if(zqdm.equals("")){
			//忽略
		}else{
			sb.append(" and zqdm='"+zqdm+"'");
		}
		


		
//		证券名称
		if(zqmc.equals("")){
			//忽略
		}else{
			sb.append(" and zqmc like '%"+zqmc+"%'");
		}	
	
		
		
		
		
		if(start_rqUtilDate==null||end_rqUtilDate==null){
			//忽略
		}else{
//			
			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
			
			
			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
			
//			sb.append(" and ( (date(fssj)<='"+start_rq+"' and date(clsj)>='"+start_rq+"') or (date(fssj)<='"+last_rq+"' and date(clsj)>='"+last_rq+"') or ( '"+start_rq+"'<=date(fssj) and '"+last_rq+"'>=date(clsj) )  )");
			sb.append(" and ( (date(date)>='"+start_rq+"' and date(date)<='"+last_rq+"') or (date(date)>='"+start_rq+"' and date(date)<='"+last_rq+"') )");
		}
		sb.append(" order by zqdm,date desc");
		String sql=sb.toString();//生成SQL语句。
		
		//调用业务逻辑层
		LsjgBO tBO = new LsjgBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql);
		//生成符合要求的LsjgDTO数据
//		解析list<Map>；
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

