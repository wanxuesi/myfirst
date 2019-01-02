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

import com.fuguo.bo.ConfigBO;
import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluSpecalQueryAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		String zqdm = qForm.getZqdm().trim();//֤ȯ����;
		
		GpmcBO gpmcBO = new GpmcBO();
		GpmcDTO gpmcDTO = new GpmcDTO();
		gpmcDTO.setZqdm(zqdm);
		String zqmc = gpmcBO.query(gpmcDTO).getZqmc();//֤ȯ����
		String xxjh=zqmc+"("+zqdm+")��¼��ѯ";
		String queryDateSql=xxjh;
		
		

//		DateUtilWeekChina util = new DateUtilWeekChina();
//		int weekFlag	= qForm.getWeekFlag();
//		
//		Date   start_rqUtilDate=null;
//		Date   end_rqUtilDate=null;
//		if(weekFlag==1){//����
//			start_rqUtilDate =util.getUpWeek_Of_FirstDay(new java.util.Date());
//			
//			end_rqUtilDate   =util.getUpWeek_Of_LastDay(new java.util.Date());
//			//weeks = (util.getweekNum(new java.util.Date())-1);
//		}else if(weekFlag==2){
//			//����
//			start_rqUtilDate = util.getWeek_Of_FirstDay(new java.util.Date());
//			end_rqUtilDate = util.getWeek_Of_LastDay(new java.util.Date());
//			//weeks= util.getweekNum(new java.util.Date());
//		}else if(weekFlag==4){
//			//һ����
//			start_rqUtilDate =util.getMonthFirtDay(new java.util.Date());
//			
//			end_rqUtilDate   =util.getMonthLastDay(new java.util.Date());
//		}
//		//�����չ����������
//		String _start_rq =qForm.getStart_rq().trim();
//		String _end_rq = qForm.getEnd_rq().trim();
//		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
//			
//		}else{
////			������ת��Ϊutil.Date����
//			SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
//			start_rqUtilDate   =   sdfymd.parse(_start_rq);
//			end_rqUtilDate   =   sdfymd.parse(_end_rq);
//		}
//		
//		String flag1 = qForm.getFlag1().trim();//��¼״̬
		//String zqdm = qForm.getZqdm().trim();//֤ȯ����
		//String zqmc = qForm.getZqmc().trim();//֤ȯ����
//		if(zqmc.length()==3){
//			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
//			//System.out.println(zqmc);
//		}
		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		
		
//		String mmflag = qForm.getMmflag();//������־��
//		String jifl = qForm.getJifl();//���׷��ࣻ
	//����Ϊ��ѡ;;;;
		StringBuffer sb=new StringBuffer();
		
		//���ӵ�sql������sql�ɣ�
		//sb.append("select * from weekplan where 1>0 ");
		sb.append("select * from jilu where khdm='"+idStr+"' ");
		
//		DateUtil dateUtil=new DateUtil();
//
//		
//		��¼״̬ ͨ����ȡ�����������ĵ����ݣ�ȷ��flag��
		
		ConfigBO cBO=new ConfigBO();
		ConfigDTO m=new ConfigDTO();
		m.setId(idStr);
		m  = cBO.query(m);
		String flag1 = m.getFlag1();
		//int onoff =m.getOnoff();
		if(flag1.equals("��")){
			//�Ƿ�������ɫ
			//
			sb.append(" and (flag2 is Null or flag2='')");
		}else{
//			�Ƿ�������ɫ������ --��
			
		}
		
//		֤ȯ����
		if(zqdm.equals("")){
			//����
		}else{
			Integer zqdmInteger = Integer.parseInt(zqdm);
			
			zqdm=zqdmInteger.toString();
			sb.append(" and zqdm='"+zqdm+"'");
		}
////		֤ȯ����
//		if(zqmc.equals("")){
//			//����
//		}else{
//			sb.append(" and zqmc='"+zqmc+"'");
//		}
////		������־��
//		if(mmflag.equals("")){
//			//����
//		}else{
//			sb.append(" and mmflag='"+mmflag+"'");
//		}
//		
////		���׷��ࣻ
//		if(jifl.equals("")){
//			//����
//		}else{
//			sb.append(" and jifl='"+jifl+"'");
//		}
//		
//
//		
//		
//		
//		if(start_rqUtilDate==null||end_rqUtilDate==null){
//			//����
//		}else{
////			
//			java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
//			
//			
//			java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime()); 
//			
//			sb.append(" and ( (date(jysj)>='"+start_rq+"' and date(jysj)<='"+last_rq+"')  )");
//		}
		sb.append(" order by JYSJ asc");
		String sql=sb.toString();//����SQL��䡣
		
		//����ҵ���߼���
		JiluBO tBO = new JiluBO();
		//�õ�Map�͵�list
		List<JiluDTO> list = tBO.sqlQuery(sql,JiluDTO.class);
		//���ɷ���Ҫ���JiluDTO����
//		����list<Map>��
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		Map _map=null;
		JiluDTO mDTO;
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		DateUtil dateUtil=new DateUtil();
		Date jysj;
		
		
		
		
		String jysjStr;
		String jysjWeek;
		Integer totalCJSL = 0;
		Double  totalQSJE = 0.0d;
		String _tmpflag2;
		
		while(it.hasNext()){
			mDTO=(JiluDTO)it.next();			
			_tmpflag2 = mDTO.getFlag2();						
			if(_tmpflag2!=null&&_tmpflag2.equals("1")){
				totalCJSL+=(Integer)mDTO.getCjsl();
				totalQSJE+=(Double)mDTO.getQsje();
			}
			
			jysj=mDTO.getJysj();
			if(jysj!=null&& !jysj.equals("")){
				jysjStr=sdf.format(jysj);
				jysjWeek = dateUtil.getWeekOfDate(jysj);
				jysjStr=jysjStr.replace(" ","<br>");
				mDTO.setJysjStr(jysjStr+jysjWeek);
			}
			listDTOs.add(mDTO);
		}
		
		if(totalCJSL==0){
			request.setAttribute("MESSAGE","��ɫ��������"+String.format("%.2f", totalQSJE));
		}else{
			request.setAttribute("MESSAGE","��ɫ���ֹ�Ʊ���ࣺ"+totalCJSL);
		}
		
		request.setAttribute("JILU",listDTOs);
		request.setAttribute("TITLE",zqmc+"("+zqdm+")");
		
		request.setAttribute("ZQDM",zqdm);
		request.getSession().setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

