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
//		获取数据并封装
		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String userZhanghaoLB=baseUserContext.getWx();
//		if(userZhanghaoLB.equals("虚拟")){
//			//如果是虚拟账号，分析的时候，必须用adjClose；
//		}
//		if(userZhanghaoLB.equals("实际")){
//			
//			//如果是实际账号，分析的时候，必须用收盘价；
//		}
		
		String idStr  =Integer.toString(idUser);
		String flag1 = qForm.getFlag1();
		//Double gxhl = Double.valueOf(flag1.trim());//股票红利
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		
		
		
		
		
		int year = qForm.getYear();//获取开始年份
		int year2 = qForm.getYear2();//获取结束年份
		int start_month = qForm.getStart_month();//开始月份
		int end_month = qForm.getEnd_month();//结束月份
		
		Date systemDate =new Date();
		int systemYear = systemDate.getYear()+1900;
		int systemMonth =systemDate.getMonth()+1;
		String xxjh="盈亏分析折线图";
		String queryDateSql=year+"年"+start_month+"月 ～ "+year2+"年" +end_month+"月" +xxjh;
		
		DateUtil dateUtil=new DateUtil();
		//获取查询的日期中间的月份数目；
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
		//复杂的sql还是用sql吧！
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
			
//			通过年月，获取该月最后一天；
			nowYearMonthLastDay = dateUtil.getMonthLastDay_Of_Which_Year(nowYear,nowMonth);
			
//			通过年月，获取该月第一天；
			nowYearMonthFirtDay = dateUtil.getMonthFirtDay_Of_Which_Year(nowYear,nowMonth);
			
			monthTitle[i]=nowYear+"年"+nowMonth+"月";
			monthTitle[i] = monthTitle[i].substring(2);//eg:05年2月;
			//System.out.println(monthTitle[i]);
			
			//拼装SQL语句；
			java.sql.Date lastDay = new java.sql.Date(nowYearMonthLastDay.getTime());
			//需要将隐藏在后面的时分秒去掉；
			
			java.sql.Date firstDay = new java.sql.Date(nowYearMonthFirtDay.getTime());
			String sqlwhere = "and date(jysj)<='"+lastDay+"' ";
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
			String sqlWhereDate=" and (date(date)>='"+firstDay+"' and date(date)<='"+lastDay+"') ";
			String sqlWhereDateTp=" and (date(date)<'"+lastDay+"') ";
			
			while(itJilu.hasNext()){
				_jiluDTO=(JiluDTO)itJilu.next();
				zqdm = _jiluDTO.getZqdm();
				System.out.println(zqdm);
				sumQsje = _jiluDTO.getQsje();
				sumCjsl = _jiluDTO.getCjsl();
				
				//将每一行的sumqsje 加到allSumQsje上；
				allSumQsje+=sumQsje;
				if(userZhanghaoLB.equals("虚拟")){	
//					先获取该股票的最大当前复权因子maxFqyzNow；
					maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,nowYearMonthFirtDay);//这样才准确
				}
				
				//判断sumCjsl是否大于0，
				//如果大于0，则表示该时间段内仍然持有该股票，需要计算他当时的历史价格；
				if(sumCjsl>0){
					
					//如果nowYear and nowMonth >=系统此刻年     月；直接将当前价赋值给close；
					//	  2015年      1月              2015年  9月； >1 找历史价格；
//					  2015年      9月              2015年  9月； =1 找dqj价格；
//					  2015年      10月              2015年  9月； <1 找dqj价格；
					int Numbs = DateUtil.getStringMonthOf_TwoDates(nowYear,nowMonth,systemYear,systemMonth);
					if(Numbs<=1){
						double dqj  = StockUtil.getDqjByZqdm(zqdm);//当前价；
						if(dqj==0){
							throw new BSWException("证券代码："+zqdm+"在该时段持有股票数量"+sumCjsl+",但是该时段("+monthTitle[i]+")的距离现在时间很近，获取当前价格失败，请确认已连接网络！");
							
						}
						close = dqj;
					}else{
						String lsjgSql ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'   and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' and flag1!='tmp'  "+sqlWhereDate+")"; 	
//						执行sql语句
						List listLsjg = lsjgBO.sqlQuery(lsjgSql,LsjgDTO.class);
						// 解析list<Map>；
						Iterator itLsjg = listLsjg.iterator();
						//Map _mapLsjg=null;
						close=0;
						if(itLsjg.hasNext()){
							LsjgDTO _lsjgDTO=(LsjgDTO)itLsjg.next();
							
							
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

//					close==0 有2种情况，
					//一种是查询的该日期已经更新过了，但是停牌（sina数据）就没有收盘价。这时就要找到该股票的最近的历史价格，赋值给该股票的close价（也有可能停牌几个月的）。。。，如果没有，提示报错；
					//第二种是：确实没有更新的情况；因为登录就更新，这种情况应该不存在；
					
					if(close==0){
						
//						查找该股票的最后的更新日期；
						LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
						lsjgdateDTO.setId(zqdm);
						lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
						Date nowZqdmLsjgDate  =lsjgdateDTO.getDate();
						java.sql.Date nowZqdmLsjgDateSqlDate = new java.sql.Date(nowZqdmLsjgDate.getTime());
						//进行比对
						
						System.out.println(zqdm);
						//System.out.println(lastDay.getTime());
						
						String lastDayFormat = sdf.format(lastDay);
						lastDay = new java.sql.Date(sdf.parse(lastDayFormat).getTime());
						//System.out.println(lastDay.getTime());
						//System.out.println(nowZqdmLsjgDateSqlDate);
						//System.out.println(nowZqdmLsjgDateSqlDate.getTime());
						if(lastDay.getTime()<=nowZqdmLsjgDateSqlDate.getTime()){
							//说明查询的该日期已经更新过了
							String lsjgSql2 ="select zqdm,date,close,fqyz from lsjg where zqdm='"+zqdm+"'  and date in(select max(date) maxdate  from lsjg where zqdm='"+zqdm+"' "+sqlWhereDateTp+")"; 	
							//System.out.println(lsjgSql2);
							//						执行sql语句
							List listLsjg2 = lsjgBO.sqlQuery(lsjgSql2,LsjgDTO.class);
							// 解析list<Map>；
							Iterator itLsjg2 = listLsjg2.iterator();
							//Map _mapLsjg2=null;
							close=0;
							if(itLsjg2.hasNext()){
								LsjgDTO _lsjgDTO2=(LsjgDTO)itLsjg2.next();
								
								
								if(userZhanghaoLB.equals("实际")){								
									//如果是实际账号，分析的时候，必须用收盘价；
									close =_lsjgDTO2.getClose();
								}
								if(userZhanghaoLB.equals("虚拟")){								
//									如果是虚拟账号，分析的时候，必须用adjClose；
									fqyz = _lsjgDTO2.getFqyz();
									close = _lsjgDTO2.getClose();
									//通过上面三个参数，获得到adjClose 再返回给close；
									close = (close/maxFqyzNow)*fqyz;
									
								}
								
							}
						}else{
						
						
							throw new BSWException("证券代码："+zqdm+"在该时段持有股票数量"+sumCjsl+",但是数据库目前没有该时段("+monthTitle[i]+")的历史价格记录，请添加！");
						}
					}
					
					cjsl_CY_Close=sumCjsl*close;
					sumCjsl_CY_Close+=cjsl_CY_Close;
				}
				
			}
			
//			获取该时间前的股息红利总和；
			
			String dateStr = sdf.format(lastDay);
			
  			Double GXHL = dBO.getGXHL(idStr,dateStr);
			
							 
			//将查询结果放入到monthValue[i]中；
			monthValue[i]=allSumQsje+sumCjsl_CY_Close+GXHL;
			
			//System.out.println(monthValue[i]);
			
		}
		
		
		//获取monthValue中最大值，和最小值的index；
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
		
		String MAXMINMessageStr="<font color=blue>盈亏最小值："+String.format("%.0f", monthValue[MININDEX])+"("+monthTitle[MININDEX]+")</font>"+"&nbsp;&nbsp;&nbsp;<font color=red>盈亏最大值："+String.format("%.0f", monthValue[MAXINDEX])+"("+monthTitle[MAXINDEX]+")</font>";
		
		
		request.setAttribute("MAXMINMessageStr",MAXMINMessageStr);
		
		
		request.setAttribute("queryDateSql",queryDateSql);
		
	}

	

}

