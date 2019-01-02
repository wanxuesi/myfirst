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
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgDTO;
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
public class YkfxZxtOneStockAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡ���ݲ���װ
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String userZhanghaoLB=baseUserContext.getWx();
		
		String idStr  =Integer.toString(idUser);
		
		String zqdm = qForm.getZqdm();
		String zqmc = qForm.getZqmc();
		//��ʼ���ںͽ�������Ӧ��ͨ�����ݿ��ѯ���нӿ�
		//		��Ʊ���׼�¼��ʼ�Σ�
		Date jiludateMin=null;
	    Date jiludateMax=null;
		//����zqdm��ѯ���׵���С���ڣ��������2����¼�ķ�����
		//idUser,zqdm
		
		String sqlJiluDateMinMax = "select min(jysj) jiludateMin,max(jysj) jiludateMax from jilu where khdm='"+idStr+"' and  zqdm='"+zqdm+"'";
		JiluBO tBO = new JiluBO();
		List listJiluDateMinMax = tBO.sqlQuery(sqlJiluDateMinMax,JiluDTO.class);
		JiluDTO _jiluDTO=null;
		if(!listJiluDateMinMax.isEmpty()){
			_jiluDTO = (JiluDTO)listJiluDateMinMax.get(0);
			
			jiludateMin = _jiluDTO.getJiludateMin();
			jiludateMax = _jiluDTO.getJiludateMax();
			
		}
		
		
		
		
//		Ȼ���жϵ�ǰ�Ƿ��гֲ֣�����У��ǽ������ھ��ǵ��죻
		
		//�����жϸù�Ʊ���û�id��ǰ�ֲ������Ƿ�Ϊ�㣻order������ң�
//		��ȡĿǰ���û��ĳֲֹ�Ʊmap���ͣ�
		OrderBO orderBO = new OrderBO();
		Map<String,String> map = orderBO.getOrders(idStr);

		if(map.containsKey(zqdm)){
			//�õ�ǰ���ڸ��ǵ�jiludateMax��
			jiludateMax=new Date();
		}
		
		
		
		
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		
		//String _start_rq =qForm.getStart_rq().trim();
		//String _end_rq = qForm.getEnd_rq().trim();
		
		String xxjh="ӯ��/�ֲ��� ����������ͼ";
		String queryDateSql=sdf.format(jiludateMin)+" �� "+sdf.format(jiludateMax)+" " +xxjh;
		
		//������ת��Ϊutil.Date����
		
		//SimpleDateFormat   sdfSpc   =   new   SimpleDateFormat("yyyy-M-d");
		//SimpleDateFormat   sdfSpceil   =   new   SimpleDateFormat("M-d");
		Date   start_rq   =   jiludateMin;
		Date   end_rq   =   jiludateMax;
		//��ȡһ�������죻��������
		int daysNumbers=DateUtil.getDateDiff(start_rq,end_rq);
		
		String[] dateTitle=new String[daysNumbers];
		Double[] dateValue=new Double[daysNumbers];
		
		Integer[] cjslValue = new Integer[daysNumbers];
		
		
		
		Date systemDate =new Date();
		
		DateUtil dateUtil=new DateUtil();
		//��ȡ��ѯ�������м���·���Ŀ��
		
		Date nowDay = new Date();
		
		JiluBO jiluBO = new JiluBO();	
		LsjgBO lsjgBO = new LsjgBO();
		DataBO dBO  =new DataBO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		//���ӵ�sql������sql�ɣ�
		String sqlStart = "select zqdm,sum(qsje) qsje ,sum(cjsl) cjsl  from jilu  where zqdm='"+zqdm+"' and khdm='"+idStr+"' ";
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
					cjslValue[i]=0;
				}else{
					double tmp = dateValue[i-1];
					dateValue[i]=tmp;
					
					cjslValue[i]=cjslValue[i-1];
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
			List listJilu = jiluBO.sqlQuery(sql,JiluDTO.class);
			// ����list<Map>��
			Iterator itJilu = listJilu.iterator();
			JiluDTO jiludto;
			zqdm="";
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
				jiludto=(JiluDTO)itJilu.next();
				zqdm = jiludto.getZqdm();
				//System.out.println(zqdm);
				sumQsje = jiludto.getQsje();
				sumCjsl = jiludto.getCjsl();
				
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
						List listLsjg = lsjgBO.sqlQuery(lsjgSql,LsjgDTO.class);
						// ����list<Map>��
						Iterator itLsjg = listLsjg.iterator();
						LsjgDTO _lsjgDTO=null;
						close=0;
						if(itLsjg.hasNext()){
							_lsjgDTO=(LsjgDTO)itLsjg.next();
							
							if(userZhanghaoLB.equals("ʵ��")){								
								//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
								close = _lsjgDTO.getClose();
							}
							if(userZhanghaoLB.equals("����")){								
//								����������˺ţ�������ʱ�򣬱�����adjClose��
								fqyz = _lsjgDTO.getFqyz();
								close =_lsjgDTO.getClose();
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
									List listLsjg2 = lsjgBO.sqlQuery(lsjgSql2,LsjgDTO.class);
									// ����list<Map>��
									Iterator itLsjg2 = listLsjg2.iterator();
									LsjgDTO _lsjgDTO2=null;
									close=0;
									if(itLsjg2.hasNext()){
										_lsjgDTO2=(LsjgDTO)itLsjg2.next();
										
										if(userZhanghaoLB.equals("ʵ��")){								
											//�����ʵ���˺ţ�������ʱ�򣬱��������̼ۣ�
											close = _lsjgDTO2.getClose();
										}
										if(userZhanghaoLB.equals("����")){								
//											����������˺ţ�������ʱ�򣬱�����adjClose��
											fqyz =_lsjgDTO2.getFqyz();
											close = _lsjgDTO2.getClose();
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
				
			
			

			//����ѯ������뵽monthValue[i]�У�
			dateValue[i]=allSumQsje+sumCjsl_CY_Close;
			
			cjslValue[i]=sumCjsl;
			//System.out.println("�ùɳֲ�����Ϊ��"+cjslValue[i]);
			
		}
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		StringBuffer sb2 = new StringBuffer();
		sb2.append("[");
		long dateTime;
		String tmpDateStr;
		for(int j=0;j<dateTitle.length;j++){
			tmpDateStr = dateTitle[j];
			dateTime = sdf.parse(tmpDateStr).getTime();
			
			
			if(j<1){
				sb.append("["+dateTime+","+dateValue[j].intValue()+"]");
				
				sb2.append("["+dateTime+","+cjslValue[j].intValue()+"]");
				
			}else{
				sb.append(",["+dateTime+","+dateValue[j].intValue()+"]");
				
				sb2.append(",["+dateTime+","+cjslValue[j].intValue()+"]");
				
				
			}
		}
		
		
		
		sb.append("]");

		sb2.append("]");
		
		//System.out.println(sb2.toString());
		//System.out.println(sb.toString());
		request.setAttribute("dataStr",sb.toString());
		request.setAttribute("data2Str",sb2.toString());
		
		MathUtil mathUtil  =new MathUtil();
		int  MAXINDEX = mathUtil.getMaxDoubleIndex(dateValue);
		
		int MININDEX = mathUtil.getMinDoubleIndex(dateValue);
		
		String MAXMINMessageStr="<font color=blue>ӯ����Сֵ��"+String.format("%.0f", dateValue[MININDEX])+"("+dateTitle[MININDEX]+")</font>"+"&nbsp;&nbsp;&nbsp;<font color=red>ӯ�����ֵ��"+String.format("%.0f", dateValue[MAXINDEX])+"("+dateTitle[MAXINDEX]+")</font>";
		
		
		request.setAttribute("MAXMINMessageStr",MAXMINMessageStr);
		request.setAttribute("queryDateSql",queryDateSql);
		request.setAttribute("ZQDM",zqdm);
		request.setAttribute("ZQMC",zqmc);
		
	}

	

}

