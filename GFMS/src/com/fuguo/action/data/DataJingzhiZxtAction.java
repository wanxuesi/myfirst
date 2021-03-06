//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.data;
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
import com.fuguo.util.MathUtil;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-20-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class DataJingzhiZxtAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String userZhanghaoLB=baseUserContext.getWx();
		
		String idStr  =Integer.toString(idUser);
		
		
		
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		
		
		
		String xxjh="每日净值折线图";
		String queryDateSql=_start_rq+" ～ "+_end_rq+" " +xxjh;
		
		//将它们转换为util.Date类型
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		//SimpleDateFormat   sdfSpc   =   new   SimpleDateFormat("yyyy-M-d");
		//SimpleDateFormat   sdfSpceil   =   new   SimpleDateFormat("M-d");
		Date   start_rq   =   sdf.parse(_start_rq);
		Date   end_rq   =   sdf.parse(_end_rq);
		//获取一共多少天；即天数；
		int daysNumbers=DateUtil.getDateDiff(start_rq,end_rq);
		
		String[] dateTitle=new String[daysNumbers];
		//Double[] dateValue=new Double[daysNumbers];
		Double[] KYZJValue=new Double[daysNumbers];
		Double[] GPSZValue=new Double[daysNumbers];
		Double[] ZZCValue=new Double[daysNumbers];
		
		Double[] FenEValue=new Double[daysNumbers];//份额数
		
		Double[] JingzhiValue=new Double[daysNumbers];//净值
		Date systemDate =new Date();
		
		DateUtil dateUtil=new DateUtil();
		//获取查询的日期中间的月份数目；
		
		Date nowDay = new Date();
		
		JiluBO jiluBO = new JiluBO();	
		LsjgBO lsjgBO = new LsjgBO();
		DataBO dBO  =new DataBO();
		LsjgdateBO lsjgdateBO = new LsjgdateBO();
		//复杂的sql还是用sql吧！
		String sqlStart = "select zqdm,sum(qsje) qsje ,sum(cjsl) cjsl  from jilu  where   khdm='"+idStr+"' ";
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
			
			//判断该nowDay是否是周末，
			//获取是星期几；
			String xingqiji = DateUtil.getWeekOfDate(nowDay);
			if(xingqiji.equals("(六)")||xingqiji.equals("(日)")){
//				如果是，那么不要操作，
				//直接将前一天的盈亏值放入到该dateValue中，continue；
				if(i==0){
					//dateValue[i]=0.0;
					KYZJValue[i]=0.0;
					GPSZValue[i]=0.0;
					ZZCValue[i]=0.0;
					
					FenEValue[i]=0.0;
					JingzhiValue[i]=0.0;
				}else{
					//double tmp = dateValue[i-1];
					//dateValue[i]=tmp;
					
					KYZJValue[i]= KYZJValue[i-1];
					GPSZValue[i]= GPSZValue[i-1];
					ZZCValue[i]= ZZCValue[i-1];
					
					FenEValue[i]= FenEValue[i-1];
					JingzhiValue[i]= JingzhiValue[i-1];
					//System.out.println(dateValue[i]);
				}
				
				continue;
			}
			
			//拼装SQL语句；
			java.sql.Date first_lastDay = new java.sql.Date(nowDay.getTime());
			//java.sql.Date firstDay = new java.sql.Date(nowDay.getTime());
			String sqlwhere = "and date(jysj)<='"+first_lastDay+"' ";
			sql=sqlStart+sqlwhere+sqlEnd;
			
			//System.out.println(sql);
			//执行sql语句
			List listJilu = jiluBO.sqlQuery(sql,JiluDTO.class);
			// 解析list<Map>；
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
			String sqlWhereDate=" and (date(date)='"+first_lastDay+"') ";
			
			String sqlWhereDateTp=" and (date(date)<'"+first_lastDay+"') ";//停牌
			while(itJilu.hasNext()){
				_jiluDTO=(JiluDTO)itJilu.next();
				zqdm = (String)_jiluDTO.getZqdm();
				System.out.println(zqdm);
				sumQsje = (Double)_jiluDTO.getQsje();
				sumCjsl = (Integer)_jiluDTO.getCjsl();
				
				//将每一行的sumqsje 加到allSumQsje上；
				allSumQsje+=sumQsje;

				
				if(userZhanghaoLB.equals("虚拟")){	
					System.out.println(nowDay+"的复权因子开始计算");
//					先获取该股票的最大当前复权因子maxFqyzNow；
					maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,start_rq);//这样才准确起始日期久远一点不要紧；
					System.out.println(nowDay+"的复权因子是："+ maxFqyzNow);
				}
				
				//判断sumCjsl是否大于0，
				//如果大于0，则表示该时间段内仍然持有该股票，需要计算他当时的历史价格；
				if(sumCjsl>0){
					
					//如果first_lastDay >=系统此刻日期（systemDate）；直接将当前价赋值给close；
					//	  2015年      1月              2015年  9月； >1 找历史价格；
//					  2015年      9月              2015年  9月； =1 找dqj价格；
//					  2015年      10月              2015年  9月； <1 找dqj价格；
					int days=DateUtil.getDateDiff(first_lastDay,systemDate);
					if(days<=1){
						double dqj  = StockUtil.getDqjByZqdm(zqdm);//当前价； 
						close = dqj;
					}else{
						//days>1
						String lsjgSql ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp' and date in(select max(date) maxdate  from lsjg where   flag1!='tmp' and zqdm='"+zqdm+"' "+sqlWhereDate+")"; 	
						//System.out.println(lsjgSql);
						//						执行sql语句
						List listLsjg = lsjgBO.sqlQuery(lsjgSql,LsjgDTO.class);
						// 解析list<Map>；
						Iterator itLsjg = listLsjg.iterator();
						LsjgDTO _lsjgDTO=null;
						close=0;
						if(itLsjg.hasNext()){
							_lsjgDTO=(LsjgDTO)itLsjg.next();
							
							if(userZhanghaoLB.equals("实际")){								
								//如果是实际账号，分析的时候，必须用收盘价；
								close = _lsjgDTO.getClose();
							}
							if(userZhanghaoLB.equals("虚拟")){								
//								如果是虚拟账号，分析的时候，必须用adjClose；
								fqyz = _lsjgDTO.getFqyz();
								close = _lsjgDTO.getClose();
								//通过上面三个参数，获得到adjClose 再返回给close；
								close = (close/maxFqyzNow)*fqyz;
								
							}
							
						}
					}
						
					//close==0 有2种情况，
					//一种是查询的该日期已经更新过了，但是停牌（sina数据）就没有收盘价。这时就要找到该股票的最近的历史价格，赋值给该股票的close价（也有可能停牌几个月的）。。。，如果没有，提示报错；
					//第二种是：确实没有更新的情况；因为登录就更新，这种情况应该不存在；
					if(close==0){
								//查找该股票的最后的更新日期；
								LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
								lsjgdateDTO.setId(zqdm);
								lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
								Date nowZqdmLsjgDate  =lsjgdateDTO.getDate();
								java.sql.Date nowZqdmLsjgDateSqlDate = new java.sql.Date(nowZqdmLsjgDate.getTime());
								//进行比对
								if(first_lastDay.getTime()<=nowZqdmLsjgDateSqlDate.getTime()){
									//说明查询的该日期已经更新过了
									String lsjgSql2 ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'   and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'  "+sqlWhereDateTp+")"; 	
									//System.out.println(lsjgSql2);
									//						执行sql语句
									List listLsjg2 = lsjgBO.sqlQuery(lsjgSql2,LsjgDTO.class);
									// 解析list<Map>；
									Iterator itLsjg2 = listLsjg2.iterator();
									LsjgDTO _lsjgDTO2=null;
									close=0;
									if(itLsjg2.hasNext()){
										_lsjgDTO2=(LsjgDTO)itLsjg2.next();
										
										if(userZhanghaoLB.equals("实际")){								
											//如果是实际账号，分析的时候，必须用收盘价；
											close = _lsjgDTO2.getClose();
										}
										if(userZhanghaoLB.equals("虚拟")){								
//											如果是虚拟账号，分析的时候，必须用adjClose；
											fqyz = _lsjgDTO2.getFqyz();
											close = _lsjgDTO2.getClose();
											//通过上面三个参数，获得到adjClose 再返回给close；
											close = (close/maxFqyzNow)*fqyz;
											
										}
									}
								}else{
									throw new BSWException("证券代码："+zqdm+"在该时段持有股票数量"+sumCjsl+",但是数据库目前没有该时段("+dateTitle[i]+")的历史价格记录，请添加！");
								}
	
							
					}
					
					cjsl_CY_Close=sumCjsl*close;
					sumCjsl_CY_Close+=cjsl_CY_Close;
				}
				if(sumCjsl==0){
					//股票市值就是0；
					//啥都不做
				}
				
			}	
				
			//将股票市值放入到该数组中；
			GPSZValue[i]=sumCjsl_CY_Close;
			
			KYZJValue[i]=dBO.getKYZJ(idStr,dateTitle[i]);
			
			ZZCValue[i]=GPSZValue[i]+KYZJValue[i];
			
//			//获取该时间前的份额总和DQFE；
			
			// 获取当前份额总数；
				
				DataBO dataBO = new DataBO();
				String dateStr = sdf.format(first_lastDay);
				
	  			Double DQFE = dataBO.getDQFE(idStr,dateStr);
	  			
	  			
	  			//放入到FenEValue[i]中；
	  			FenEValue[i]=DQFE;				  			
	  			//计算当前净值；
	  			JingzhiValue[i]=ZZCValue[i]/DQFE;
	  	      
			
			
			
			
			
			
		}
		
		
		
		//StringBuffer sb = new StringBuffer();
		//sb.append("[");
		
		//StringBuffer sb2 = new StringBuffer();
		//sb2.append("[");
		
		StringBuffer sb3 = new StringBuffer();//放净值
		sb3.append("[");
		//StringBuffer sb4 = new StringBuffer();
		//sb4.append("[");
		long dateTime;
		String tmpDateStr;
		for(int j=0;j<dateTitle.length;j++){
			tmpDateStr = dateTitle[j];
			dateTime = sdf.parse(tmpDateStr).getTime();
			
			
			if(j<1){
				
				
				sb3.append("["+dateTime+","+String.format("%.4f", JingzhiValue[j])+"]");
				

			}else{
				sb3.append(",["+dateTime+","+String.format("%.4f", JingzhiValue[j])+"]");
				
			}
		}
		
		
		
		//sb.append("]");
		//sb2.append("]");
		sb3.append("]");
		//sb4.append("]");
		//System.out.println(sb3.toString());
		//request.setAttribute("dataStr",sb.toString());
		//request.setAttribute("data2Str",sb2.toString());
		request.setAttribute("data3Str",sb3.toString());
		//request.setAttribute("data4Str",sb4.toString());
		
		MathUtil mathUtil  =new MathUtil();
		int  MAXINDEX = mathUtil.getMaxDoubleIndex(JingzhiValue);
		
		int MININDEX = mathUtil.getMinDoubleIndex(JingzhiValue);
		
		String MAXMINMessageStr="<font color=blue>净值最小值："+String.format("%.4f", JingzhiValue[MININDEX])+"("+dateTitle[MININDEX]+")</font>"+"&nbsp;&nbsp;&nbsp;<font color=red>净值最大值："+String.format("%.4f", JingzhiValue[MAXINDEX])+"("+dateTitle[MAXINDEX]+")</font>";
		
		
		request.setAttribute("MAXMINMessageStr",MAXMINMessageStr);
		request.setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

