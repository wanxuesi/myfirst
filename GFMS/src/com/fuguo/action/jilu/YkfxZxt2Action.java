//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;
import java.math.BigDecimal;
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
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.MathUtil;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class YkfxZxt2Action extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String userZhanghaoLB=baseUserContext.getWx();
		
		String idStr  =Integer.toString(idUser);
		
		String flag1 = qForm.getFlag1();
		//Double gxhl = Double.valueOf(flag1.trim());
		
		
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		
		String xxjh="ӯ������������ͼ";
		String queryDateSql=_start_rq+" �� "+_end_rq+" " +xxjh;
		
		//������ת��Ϊutil.Date����
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		//SimpleDateFormat   sdfSpc   =   new   SimpleDateFormat("yyyy-M-d");
		//SimpleDateFormat   sdfSpceil   =   new   SimpleDateFormat("M-d");
		Date   start_rq   =   sdf.parse(_start_rq);
		Date   end_rq   =   sdf.parse(_end_rq);
		//��ȡһ�������죻��������
		int daysNumbers=DateUtil.getDateDiff(start_rq,end_rq);
		
		String[] dateTitle=new String[daysNumbers];
		Double[] dateValue=new Double[daysNumbers];
		
		
		
		Date systemDate =new Date();
		
		DateUtil dateUtil=new DateUtil();
		//��ȡ��ѯ�������м���·���Ŀ��
		
		Date nowDay = new Date();
		
		JiluBO jiluBO = new JiluBO();	
		LsjgBO lsjgBO = new LsjgBO();
		DataBO dBO  =new DataBO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		//���ӵ�sql������sql�ɣ�
		String sqlStart = "select zqdm,sum(qsje) sumqsje ,sum(cjsl) sumcjsl  from jilu  where   khdm='"+idStr+"' ";
		String sql="";
		String sqlEnd =" group by zqdm";
		for(int i=0;i<daysNumbers;i++){
			if(i==0){
				nowDay = start_rq;
							
			}else{
				nowDay	=	DateUtil.getAfterNDay(nowDay,1);		
			}
			
			String nowDayStr = sdf.format(nowDay);
			dateTitle[i]=nowDayStr;
			
			
			//System.out.println(dateTitle[i]);
			
			//�жϸ�nowDay�Ƿ�����ĩ��
			//��ȡ�����ڼ���
			String xingqiji = DateUtil.getWeekOfDate(nowDay);
			if(xingqiji.equals("(��)")||xingqiji.equals("(��)")){
//				����ǣ���ô��Ҫ������
				//ֱ�ӽ�ǰһ���ӯ��ֵ���뵽��dateValue�У�continue��
				if(i==0){
					dateValue[i]=0.0;
				}else{
					double tmp = dateValue[i-1];
					dateValue[i]=tmp;
					//System.out.println(dateValue[i]);
				}
				
				continue;
			}
			
			//ƴװSQL��䣻
			java.sql.Date first_lastDay = new java.sql.Date(nowDay.getTime());
			//java.sql.Date firstDay = new java.sql.Date(nowDay.getTime());
			String sqlwhere = "and date(jysj)<='"+first_lastDay+"' ";
			sql=sqlStart+sqlwhere+sqlEnd;
			
			//System.out.println(sql);
			//ִ��sql���
			List listJilu = jiluBO.sqlQuery(sql);
			// ����list<Map>��
			Iterator itJilu = listJilu.iterator();
			Map _map=null;
			String zqdm="";
			double allSumQsje=0;
			double sumQsje=0;
			int sumCjsl = 0;
			double cjsl_CY_Close=0;
			double sumCjsl_CY_Close=0;
			double close=0;
			
			double maxFqyzNow = 0.0d;
			
			double fqyz=0.0d;
			String sqlWhereDate=" and (date(date)='"+first_lastDay+"') ";
			
			String sqlWhereDateTp=" and (date(date)<'"+first_lastDay+"') ";//ͣ��
			while(itJilu.hasNext()){
				_map=(Map)itJilu.next();
				zqdm = (String)_map.get("ZQDM");
				System.out.println(zqdm);
				sumQsje = (Double)_map.get("SUMQSJE");
				sumCjsl = (Integer)_map.get("SUMCJSL");
				
				//��ÿһ�е�sumqsje �ӵ�allSumQsje�ϣ�
				allSumQsje+=sumQsje;

				
				if(userZhanghaoLB.equals("����")){	
					System.out.println(nowDay+"�ĸ�Ȩ���ӿ�ʼ����");
//					�Ȼ�ȡ�ù�Ʊ�����ǰ��Ȩ����maxFqyzNow��
					maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,start_rq);//������׼ȷ��ʼ���ھ�Զһ�㲻Ҫ����
					System.out.println(nowDay+"�ĸ�Ȩ�����ǣ�"+ maxFqyzNow);
				}
				
				//�ж�sumCjsl�Ƿ����0��
				//�������0�����ʾ��ʱ�������Ȼ���иù�Ʊ����Ҫ��������ʱ����ʷ�۸�
				if(sumCjsl>0){
					
					//���first_lastDay >=ϵͳ�˿����ڣ�systemDate����ֱ�ӽ���ǰ�۸�ֵ��close��
					//	  2015��      1��              2015��  9�£� >1 ����ʷ�۸�
//					  2015��      9��              2015��  9�£� =1 ��dqj�۸�
//					  2015��      10��              2015��  9�£� <1 ��dqj�۸�
					int days=DateUtil.getDateDiff(first_lastDay,systemDate);
					if(days<=1){
						double dqj  = StockUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ� 
						close = dqj;
					}else{
						//days>1
						String lsjgSql ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp' and date in(select max(date) maxdate  from lsjg where   flag1!='tmp' and zqdm='"+zqdm+"' "+sqlWhereDate+")"; 	
						//System.out.println(lsjgSql);
						//						ִ��sql���
						List listLsjg = lsjgBO.sqlQuery(lsjgSql);
						// ����list<Map>��
						Iterator itLsjg = listLsjg.iterator();
						Map _mapLsjg=null;
						close=0;
						if(itLsjg.hasNext()){
							_mapLsjg=(Map)itLsjg.next();
							
							if(userZhanghaoLB.equals("ʵ��")){								
								//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
								close = (Double)_mapLsjg.get("CLOSE");
							}
							if(userZhanghaoLB.equals("����")){								
//								����������˺ţ�������ʱ�򣬱�����adjClose��
								fqyz = (Double)_mapLsjg.get("FQYZ");
								close = (Double)_mapLsjg.get("CLOSE");
								//ͨ������������������õ�adjClose �ٷ��ظ�close��
								close = (close/maxFqyzNow)*fqyz;
								
							}
							
						}
					}
						
					//close==0 ��2�������
					//һ���ǲ�ѯ�ĸ������Ѿ����¹��ˣ�����ͣ�ƣ�sina���ݣ���û�����̼ۡ���ʱ��Ҫ�ҵ��ù�Ʊ���������ʷ�۸񣬸�ֵ���ù�Ʊ��close�ۣ�Ҳ�п���ͣ�Ƽ����µģ������������û�У���ʾ����
					//�ڶ����ǣ�ȷʵû�и��µ��������Ϊ��¼�͸��£��������Ӧ�ò����ڣ�
					if(close==0){
								//���Ҹù�Ʊ�����ĸ������ڣ�
								LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
								lsjgdateDTO.setId(zqdm);
								lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
								Date nowZqdmLsjgDate  =lsjgdateDTO.getDate();
								java.sql.Date nowZqdmLsjgDateSqlDate = new java.sql.Date(nowZqdmLsjgDate.getTime());
								//���бȶ�
								if(first_lastDay.getTime()<=nowZqdmLsjgDateSqlDate.getTime()){
									//˵����ѯ�ĸ������Ѿ����¹���
									String lsjgSql2 ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'   and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'  "+sqlWhereDateTp+")"; 	
									//System.out.println(lsjgSql2);
									//						ִ��sql���
									List listLsjg2 = lsjgBO.sqlQuery(lsjgSql2);
									// ����list<Map>��
									Iterator itLsjg2 = listLsjg2.iterator();
									Map _mapLsjg2=null;
									close=0;
									if(itLsjg2.hasNext()){
										_mapLsjg2=(Map)itLsjg2.next();
										
										if(userZhanghaoLB.equals("ʵ��")){								
											//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
											close = (Double)_mapLsjg2.get("CLOSE");
										}
										if(userZhanghaoLB.equals("����")){								
//											����������˺ţ�������ʱ�򣬱�����adjClose��
											fqyz = (Double)_mapLsjg2.get("FQYZ");
											close = (Double)_mapLsjg2.get("CLOSE");
											//ͨ������������������õ�adjClose �ٷ��ظ�close��
											close = (close/maxFqyzNow)*fqyz;
											
										}
									}
								}else{
									throw new BSWException("֤ȯ���룺"+zqdm+"�ڸ�ʱ�γ��й�Ʊ����"+sumCjsl+",�������ݿ�Ŀǰû�и�ʱ��("+dateTitle[i]+")����ʷ�۸��¼������ӣ�");
								}
	
							
					}
					
					cjsl_CY_Close=sumCjsl*close;
					sumCjsl_CY_Close+=cjsl_CY_Close;
				}
				
			}	
				
			
			
//			��ȡ��ʱ��ǰ�Ĺ�Ϣ�����ܺͣ�
			Double GXHL = 0.0;  //��Ʊ����
			String sqlwhereGXHL = "and date(date)<='"+first_lastDay+"' ";
			String sql4 = "select sum(shuju) shuju from data where name='��Ϣ����' and  flag2='"+idStr+"' "+sqlwhereGXHL; 
//			�õ�Map�͵�list4
			
			
			
			List list4 = dBO.sqlQuery(sql4);
			
			Iterator it4 = list4.iterator();
			Map _map4=null;
			
			
			
			
			if(it4.hasNext()){
				_map4=(Map)it4.next();
				GXHL  =(Double)_map4.get("SHUJU");
				if(GXHL==null){
					GXHL=0.0;
				}
			}
			//����ѯ������뵽monthValue[i]�У�
			dateValue[i]=allSumQsje+sumCjsl_CY_Close+GXHL;
			
			//System.out.println(dateValue[i]);
			
		}
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		long dateTime;
		String tmpDateStr;
		for(int j=0;j<dateTitle.length;j++){
			tmpDateStr = dateTitle[j];
			dateTime = sdf.parse(tmpDateStr).getTime();
			
			
			if(j<1){
				sb.append("["+dateTime+","+dateValue[j].intValue()+"]");
			}else{
				sb.append(",["+dateTime+","+dateValue[j].intValue()+"]");
			}
		}
		
		
		
		sb.append("]");
		//System.out.println(sb.toString());
		request.setAttribute("dataStr",sb.toString());
		
		MathUtil mathUtil  =new MathUtil();
		int  MAXINDEX = mathUtil.getMaxDoubleIndex(dateValue);
		
		int MININDEX = mathUtil.getMinDoubleIndex(dateValue);
		
		String MAXMINMessageStr="<font color=blue>ӯ����Сֵ��"+String.format("%.0f", dateValue[MININDEX])+"("+dateTitle[MININDEX]+")</font>"+"&nbsp;&nbsp;&nbsp;<font color=red>ӯ�����ֵ��"+String.format("%.0f", dateValue[MAXINDEX])+"("+dateTitle[MAXINDEX]+")</font>";
		
		
		request.setAttribute("MAXMINMessageStr",MAXMINMessageStr);
		request.setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

