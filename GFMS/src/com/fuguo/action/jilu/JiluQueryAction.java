//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;
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
import bsw.fwk.BaseUserContext;

import com.fuguo.bo.JiluBO;
import com.fuguo.dto.JiluDTO;
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
public class JiluQueryAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String xxjh="记录查询";
		String queryDateSql=xxjh;
		
		

		DateUtilWeekChina util = new DateUtilWeekChina();
		int weekFlag	= qForm.getWeekFlag();
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		if(weekFlag==1){//上周
			start_rqUtilDate =util.getUpWeek_Of_FirstDay(new java.util.Date());
			
			end_rqUtilDate   =util.getUpWeek_Of_LastDay(new java.util.Date());
			//weeks = (util.getweekNum(new java.util.Date())-1);
		}else if(weekFlag==2){
			//本周
			start_rqUtilDate = util.getWeek_Of_FirstDay(new java.util.Date());
			end_rqUtilDate = util.getWeek_Of_LastDay(new java.util.Date());
			//weeks= util.getweekNum(new java.util.Date());
		}else if(weekFlag==4){
			//一个月
			start_rqUtilDate =util.getMonthFirtDay(new java.util.Date());
			
			end_rqUtilDate   =util.getMonthLastDay(new java.util.Date());
		}
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
		
		String flag1 = qForm.getFlag1().trim();//记录状态
		String zqdm = qForm.getZqdm().trim();//证券代码
		String zqmc = qForm.getZqmc().trim();//证券名称(如果是3个字，需要在中间加空格；)
//		if(zqmc.length()==3){
//			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
//			//System.out.println(zqmc);
//		}
		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		
		
		String mmflag = qForm.getMmflag();//买卖标志；
		String jifl = qForm.getJifl();//交易分类；
	//以下为多选;;;;
		StringBuffer sb=new StringBuffer();
		
		//复杂的sql还是用sql吧！
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select ID,ZQDM,ZQMC,JYSJ,MMFLAG,JIFL,FLAG1,CJJG,CJSL,CJJE,QSJE,GDMC from jilu where khdm='"+idStr+"' ");
		
		DateUtil dateUtil=new DateUtil();

		
//		记录状态
		if(flag1.equals("")){
			//忽略
		}else{
			sb.append(" and flag1='"+flag1+"'");
		}
		
//		证券代码
		if(zqdm.equals("")){
			//忽略
		}else{
			Integer zqdmInteger = Integer.parseInt(zqdm);
			
			zqdm=zqdmInteger.toString();
			sb.append(" and zqdm='"+zqdm+"'");
		}
//		证券名称
		if(zqmc.equals("")){
			//忽略
		}else{
			sb.append(" and zqmc='"+zqmc+"'");
		}
//		买卖标志；
		if(mmflag.equals("")){
			//忽略
		}else{
			sb.append(" and mmflag='"+mmflag+"'");
		}
		
//		交易分类；
		if(jifl.equals("")){
			//忽略
		}else{
			sb.append(" and jifl='"+jifl+"'");
		}
		

		
		
		
		if(start_rqUtilDate==null||end_rqUtilDate==null){
			//忽略
		}else{
//			
			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
			
			
			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
			
			sb.append(" and ( (date(jysj)>='"+start_rq+"' and date(jysj)<='"+last_rq+"')  )");
		}
		sb.append(" order by JYSJ asc");
		String sql=sb.toString();//生成SQL语句。
		
		//调用业务逻辑层
		JiluBO tBO = new JiluBO();
		//得到Map型的list
		List list = tBO.sqlQuery(sql);
		//生成符合要求的JiluDTO数据
//		解析list<Map>；
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		Map _map=null;
		JiluDTO mDTO;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		//DateUtil dateUtil=new DateUtil();
		Date jysj;
		
		
		
		
		String jysjStr;
		String jysjWeek;
		
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new JiluDTO();
			mDTO.setId((Integer)_map.get("ID"));
			mDTO.setZqdm((String)_map.get("ZQDM"));
			mDTO.setZqmc((String)_map.get("ZQMC"));
			
			mDTO.setJysj((Date)_map.get("JYSJ"));
			
			mDTO.setCjsl((Integer)_map.get("CJSL"));
			mDTO.setMmflag((String)_map.get("MMFLAG"));
			mDTO.setJifl((String)_map.get("JIFL"));
			
			mDTO.setFlag1((String)_map.get("FLAG1"));
			
			mDTO.setCjjg((Double)_map.get("CJJG"));
			
			mDTO.setCjje((Double)_map.get("CJJE"));
			mDTO.setQsje((Double)_map.get("QSJE"));
			
			mDTO.setGdmc((String)_map.get("GDMC"));//股东名称
			
			

			
			jysj=mDTO.getJysj();
			if(jysj!=null&& !jysj.equals("")){
				jysjStr=sdf.format(jysj);
				jysjWeek = dateUtil.getWeekOfDate(jysj);
				jysjStr=jysjStr.replace(" ","<br>");
				mDTO.setJysjStr(jysjStr+jysjWeek);
			}
	
			


			listDTOs.add(mDTO);
		}
		
		request.setAttribute("JILU",listDTOs);
		request.getSession().setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

