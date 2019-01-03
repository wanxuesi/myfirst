//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;
import java.text.SimpleDateFormat;
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
import bsw.tools.exception.BSWException;

import com.fuguo.bo.DataBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.StockUtil;
import com.fuguo.util.MathUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class YkfxZxtAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String userZhanghaoLB=baseUserContext.getWx();
//		if(userZhanghaoLB.equals("����")){
//			//����������˺ţ�������ʱ�򣬱�����adjClose��
//		}
//		if(userZhanghaoLB.equals("ʵ��")){
//			
//			//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
//		}
		
		String idStr  =Integer.toString(idUser);
		String flag1 = qForm.getFlag1();
		//Double gxhl = Double.valueOf(flag1.trim());//��Ʊ����
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		
		
		
		
		
		int year = qForm.getYear();//��ȡ��ʼ���
		int year2 = qForm.getYear2();//��ȡ�������
		int start_month = qForm.getStart_month();//��ʼ�·�
		int end_month = qForm.getEnd_month();//�����·�
		
		Date systemDate =new Date();
		int systemYear = systemDate.getYear()+1900;
		int systemMonth =systemDate.getMonth()+1;
		String xxjh="ӯ����������ͼ";
		String queryDateSql=year+"��"+start_month+"�� �� "+year2+"��" +end_month+"��" +xxjh;
		
		DateUtil dateUtil=new DateUtil();
		//��ȡ��ѯ�������м���·���Ŀ��
		int monthNumbs = dateUtil.getStringMonthOf_TwoDates(year,start_month,year2,end_month);
		String monthTitle[] = new String[monthNumbs];
		Double monthValue[] = new Double[monthNumbs];
		int nowYear=0;
		int nowMonth=0;
		Date nowYearMonthLastDay = new Date();
		Date nowYearMonthFirtDay = new Date();
		
		JiluBO jiluBO = new JiluBO();	
		LsjgBO lsjgBO = new LsjgBO();
		DataBO dBO  =new DataBO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		//���ӵ�sql������sql�ɣ�
		String sqlStart = "select zqdm,sum(qsje) qsje ,sum(cjsl) cjsl  from jilu  where   khdm='"+idStr+"' ";
		String sql="";
		String sqlEnd =" group by zqdm";
		for(int i=0;i<monthNumbs;i++){
			if(i==0){
				nowYear = year;
				nowMonth=start_month;			
			}else{
				nowMonth = nowMonth+1;
				if(nowMonth==13){
					nowYear+=1;
					nowMonth=1;
				}				
			}
			
//			ͨ�����£���ȡ�������һ�죻
			nowYearMonthLastDay = dateUtil.getMonthLastDay_Of_Which_Year(nowYear,nowMonth);
			
//			ͨ�����£���ȡ���µ�һ�죻
			nowYearMonthFirtDay = dateUtil.getMonthFirtDay_Of_Which_Year(nowYear,nowMonth);
			
			monthTitle[i]=nowYear+"��"+nowMonth+"��";
			monthTitle[i] = monthTitle[i].substring(2);//eg:05��2��;
			//System.out.println(monthTitle[i]);
			
			//ƴװSQL��䣻
			java.sql.Date lastDay = new java.sql.Date(nowYearMonthLastDay.getTime());
			//��Ҫ�������ں����ʱ����ȥ����
			
			java.sql.Date firstDay = new java.sql.Date(nowYearMonthFirtDay.getTime());
			String sqlwhere = "and date(jysj)<='"+lastDay+"' ";
			sql=sqlStart+sqlwhere+sqlEnd;
			
			//System.out.println(sql);
			//ִ��sql���
			List listJilu = jiluBO.sqlQuery(sql,JiluDTO.class);
			// ����list<Map>��
			Iterator itJilu = listJilu.iterator();
			JiluDTO _jiluDTO=null;
			String zqdm="";
			double allSumQsje=0;
			double sumQsje=0;
			int sumCjsl = 0;
			double cjsl_CY_Close=0;
			double sumCjsl_CY_Close=0;
			double close=0;
			double maxFqyzNow = 0.0d;
			
			double fqyz=0.0d;
			String sqlWhereDate=" and (date(date)>='"+firstDay+"' and date(date)<='"+lastDay+"') ";
			String sqlWhereDateTp=" and (date(date)<'"+lastDay+"') ";
			
			while(itJilu.hasNext()){
				_jiluDTO=(JiluDTO)itJilu.next();
				zqdm = _jiluDTO.getZqdm();
				System.out.println(zqdm);
				sumQsje = _jiluDTO.getQsje();
				sumCjsl = _jiluDTO.getCjsl();
				
				//��ÿһ�е�sumqsje �ӵ�allSumQsje�ϣ�
				allSumQsje+=sumQsje;
				if(userZhanghaoLB.equals("����")){	
//					�Ȼ�ȡ�ù�Ʊ�����ǰ��Ȩ����maxFqyzNow��
					maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,nowYearMonthFirtDay);//������׼ȷ
				}
				
				//�ж�sumCjsl�Ƿ����0��
				//�������0�����ʾ��ʱ�������Ȼ���иù�Ʊ����Ҫ��������ʱ����ʷ�۸�
				if(sumCjsl>0){
					
					//���nowYear and nowMonth >=ϵͳ�˿���     �£�ֱ�ӽ���ǰ�۸�ֵ��close��
					//	  2015��      1��              2015��  9�£� >1 ����ʷ�۸�
//					  2015��      9��              2015��  9�£� =1 ��dqj�۸�
//					  2015��      10��              2015��  9�£� <1 ��dqj�۸�
					int Numbs = DateUtil.getStringMonthOf_TwoDates(nowYear,nowMonth,systemYear,systemMonth);
					if(Numbs<=1){
						double dqj  = StockUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�
						if(dqj==0){
							throw new BSWException("֤ȯ���룺"+zqdm+"�ڸ�ʱ�γ��й�Ʊ����"+sumCjsl+",���Ǹ�ʱ��("+monthTitle[i]+")�ľ�������ʱ��ܽ�����ȡ��ǰ�۸�ʧ�ܣ���ȷ�����������磡");
							
						}
						close = dqj;
					}else{
						String lsjgSql ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'   and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'  "+sqlWhereDate+")"; 	
//						ִ��sql���
						List listLsjg = lsjgBO.sqlQuery(lsjgSql,LsjgDTO.class);
						// ����list<Map>��
						Iterator itLsjg = listLsjg.iterator();
						//Map _mapLsjg=null;
						close=0;
						if(itLsjg.hasNext()){
							LsjgDTO _lsjgDTO=(LsjgDTO)itLsjg.next();
							
							
							if(userZhanghaoLB.equals("ʵ��")){								
								//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
								close = _lsjgDTO.getClose();
							}
							if(userZhanghaoLB.equals("����")){								
//								����������˺ţ�������ʱ�򣬱�����adjClose��
								fqyz = _lsjgDTO.getFqyz();
								close = _lsjgDTO.getClose();
								//ͨ������������������õ�adjClose �ٷ��ظ�close��
								close = (close/maxFqyzNow)*fqyz;
								
							}
							
						}
					}

//					close==0 ��2�������
					//һ���ǲ�ѯ�ĸ������Ѿ����¹��ˣ�����ͣ�ƣ�sina���ݣ���û�����̼ۡ���ʱ��Ҫ�ҵ��ù�Ʊ���������ʷ�۸񣬸�ֵ���ù�Ʊ��close�ۣ�Ҳ�п���ͣ�Ƽ����µģ������������û�У���ʾ����
					//�ڶ����ǣ�ȷʵû�и��µ��������Ϊ��¼�͸��£��������Ӧ�ò����ڣ�
					
					if(close==0){
						
//						���Ҹù�Ʊ�����ĸ������ڣ�
						LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
						lsjgdateDTO.setId(zqdm);
						lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
						Date nowZqdmLsjgDate  =lsjgdateDTO.getDate();
						java.sql.Date nowZqdmLsjgDateSqlDate = new java.sql.Date(nowZqdmLsjgDate.getTime());
						//���бȶ�
						
						System.out.println(zqdm);
						//System.out.println(lastDay.getTime());
						
						String lastDayFormat = sdf.format(lastDay);
						lastDay = new java.sql.Date(sdf.parse(lastDayFormat).getTime());
						//System.out.println(lastDay.getTime());
						//System.out.println(nowZqdmLsjgDateSqlDate);
						//System.out.println(nowZqdmLsjgDateSqlDate.getTime());
						if(lastDay.getTime()<=nowZqdmLsjgDateSqlDate.getTime()){
							//˵����ѯ�ĸ������Ѿ����¹���
							String lsjgSql2 ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"'  and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' "+sqlWhereDateTp+")"; 	
							//System.out.println(lsjgSql2);
							//						ִ��sql���
							List listLsjg2 = lsjgBO.sqlQuery(lsjgSql2,LsjgDTO.class);
							// ����list<Map>��
							Iterator itLsjg2 = listLsjg2.iterator();
							//Map _mapLsjg2=null;
							close=0;
							if(itLsjg2.hasNext()){
								LsjgDTO _lsjgDTO2=(LsjgDTO)itLsjg2.next();
								
								
								if(userZhanghaoLB.equals("ʵ��")){								
									//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
									close =_lsjgDTO2.getClose();
								}
								if(userZhanghaoLB.equals("����")){								
//									����������˺ţ�������ʱ�򣬱�����adjClose��
									fqyz = _lsjgDTO2.getFqyz();
									close = _lsjgDTO2.getClose();
									//ͨ������������������õ�adjClose �ٷ��ظ�close��
									close = (close/maxFqyzNow)*fqyz;
									
								}
								
							}
						}else{
						
						
							throw new BSWException("֤ȯ���룺"+zqdm+"�ڸ�ʱ�γ��й�Ʊ����"+sumCjsl+",�������ݿ�Ŀǰû�и�ʱ��("+monthTitle[i]+")����ʷ�۸��¼������ӣ�");
						}
					}
					
					cjsl_CY_Close=sumCjsl*close;
					sumCjsl_CY_Close+=cjsl_CY_Close;
				}
				
			}
			
//			��ȡ��ʱ��ǰ�Ĺ�Ϣ�����ܺͣ�
			
			String dateStr = sdf.format(lastDay);
			
  			Double GXHL = dBO.getGXHL(idStr,dateStr);
			
							 
			//����ѯ������뵽monthValue[i]�У�
			monthValue[i]=allSumQsje+sumCjsl_CY_Close+GXHL;
			
			//System.out.println(monthValue[i]);
			
		}
		
		
		//��ȡmonthValue�����ֵ������Сֵ��index��
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		
		StringBuffer sb2 = new StringBuffer();
		sb2.append("[");
		
		for(int j=0;j<monthTitle.length;j++){
			
			
			
			if(j<1){
				sb.append("\""+monthTitle[j]+"\"");
				
				sb2.append(monthValue[j].intValue());
				
			}else{
				
				sb.append(",\""+monthTitle[j]+"\"");
				sb2.append(","+monthValue[j].intValue());
			}
		}
		
		
		
		sb.append("]");
		sb2.append("]");
		System.out.println(sb.toString());
		System.out.println(sb2.toString());
		request.setAttribute("dateStr",sb.toString());
		request.setAttribute("valueStr",sb2.toString());
		
		MathUtil mathUtil  =new MathUtil();
		
		int  MAXINDEX = mathUtil.getMaxDoubleIndex(monthValue);
		
		int MININDEX = mathUtil.getMinDoubleIndex(monthValue);
		
		String MAXMINMessageStr="<font color=blue>ӯ����Сֵ��"+String.format("%.0f", monthValue[MININDEX])+"("+monthTitle[MININDEX]+")</font>"+"&nbsp;&nbsp;&nbsp;<font color=red>ӯ�����ֵ��"+String.format("%.0f", monthValue[MAXINDEX])+"("+monthTitle[MAXINDEX]+")</font>";
		
		
		request.setAttribute("MAXMINMessageStr",MAXMINMessageStr);
		
		
		request.setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

