//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.mygpmc;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.fuguo.bo.LxBO;
import com.fuguo.bo.MygpmcBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.LxDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.form.Query_Of_AllForm;
import com.fuguo.util.DateUtil;
import com.fuguo.util.MathUtil;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class MygpmcAutomnczAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		Query_Of_AllForm qForm = (Query_Of_AllForm)form;
//		获取ids 并转换为int型
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("您没有选择任何自选股！");
		}
		String _lx = qForm.getLx().trim();//交易类型；得到：短线；中线；
		String[] lx=null;
		if(_lx==null||_lx.equals("")){
			
		}else{
			lx = _lx.split(";");
		}
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		String xm = baseUserContext.getXm();
		
		
		DataBO dataBO  = new DataBO();
		
		StockUtil sUtil = new StockUtil();
		JiluBO jBO = new JiluBO();
		//只有虚拟账号才能运行此模块；
		if(!baseUserContext.getWx().equals("虚拟")){
			throw new BSWException("此账号为非虚拟账号！因为会删除所有数据，所以无法进行虚拟操作！");
		}else{
			//将此虚拟账号的数据清空；
			

			jBO.clearAllData(baseUserContext);
		}
		
		
		
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String zjzh = baseUserContext.getNx();
		//根据lx的名称，获取该类型的自动模拟操作均线；
		
		LxBO lBO = new LxBO();
		StringBuffer sb=new StringBuffer();
		sb.append("from LxDTO where (flag1='' or flag1='"+idStr+"') ");
		if(lx==null){
			//忽略
		}else{
			for(int i=0;i<lx.length;i++){
				if(i==0){
					sb.append(" and (name='"+lx[i]+"'");
				}else{
					sb.append(" or name='"+lx[i]+"'");
				}	
			}
		sb.append(")");
		}
		LxDTO[] lxDTOs = lBO.loadAll(sb.toString());
		//通过name 得到均线参数，和数组的index；
		String flag2="";
		int maNumb=0;
		int MaxmaNumb=0;
		for(int i=0;i<lxDTOs.length;i++){
			flag2 = lxDTOs[i].getFlag2();
			flag2 = flag2.replace("MA","");
			flag2 = flag2.replace("ma","");
			
			maNumb = Integer.parseInt(flag2);
			lxDTOs[i].setMaNumb(maNumb);
			if(maNumb>MaxmaNumb){
				MaxmaNumb = maNumb;
			}
			lxDTOs[i].setArgsIndex(i);
		}
		
		
		
		Date   start_rqUtilDate=null;
		Date   end_rqUtilDate=null;
		
		
		//类似日工作里的日期
		String _start_rq =qForm.getStart_rq().trim();
		String _end_rq = qForm.getEnd_rq().trim();
		SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd");
		if(_start_rq.trim().equals("")||_end_rq.trim().equals("")){
			
		}else{
//			将它们转换为util.Date类型
 
			start_rqUtilDate   =   sdfymd.parse(_start_rq);
			
			end_rqUtilDate   =   sdfymd.parse(_end_rq);
		}
		//获取start_rqUtilDate前面的（MaxmaNumb+10）*1.4 的整数天日期；
		int dayNumb = (((MaxmaNumb+10)*14)/10);
		DateUtil dUtil = new DateUtil();
		start_rqUtilDate =dUtil.getBeforeNDay(start_rqUtilDate,dayNumb);//重新赋值给start_rqUtilDate
		
		java.sql.Date start_rq = new java.sql.Date(start_rqUtilDate.getTime()); 
		
		
		java.sql.Date last_rq = new java.sql.Date(end_rqUtilDate.getTime());
		
		int[] ids=new int[ids_Str.length];
		for(int j=0;j<ids_Str.length;j++){
			ids[j] = Integer.parseInt(ids_Str[j]);
		}
		MygpmcBO tBO =new MygpmcBO();
		LsjgBO lsjgBO = new LsjgBO();
		//循环处理每一个id
		MygpmcDTO tDTO;
		//获取该id的证券代码；
		String zqdm="";
		String zqmc="";
		String jysmc="";
		LsjgDTO[] lsjgDTOs=null;
		
		String defaultmoneyStr = (String)baseUserContext.getSystemconfig().get("defaultmoney");
		Double KYZJ = Double.parseDouble(defaultmoneyStr);//可用资金的便捷获取方法；因为初始化了用户数据，defaultmoney=KYZJ;
		
		//Double KYZJ =dataBO.getKYZJ(idStr);
		Double ONEKYZJ = KYZJ/(ids_Str.length*lxDTOs.length);  //算出单只股票，单个交易分类可以使用的资金；
		
		//以下两列每只股票重新重新循环的时候，重置；//确切的讲，应该每只股票、每个类型循环的时候重置；
		Double tmpONESYZJ = ONEKYZJ;//没有买入时，单只股票的默认可用资金为平均分配的资金；
		
		int tmpONESYGUPIAONUMBER=0;//没有买入时，单只股票的剩余股票数默认为0；
		
		for(int i=0;i<ids.length;i++ ){
			
			Date  indexUtilDate    =     sdfymd.parse(_start_rq);
			int INDEX=-1;//存放获取该日期的index；
			
			//对每一个id进行处理		
			//（一）根据这个id查找这条记录。			
			tDTO=new MygpmcDTO();
			tDTO.setId(ids[i]);
			tDTO = tBO.query(tDTO);			
			zqdm = tDTO.getZqdm();
			//StockUtil sUtil = new StockUtil();
//			判断是否是基金，如果是基金，则不收印花税；
			boolean isJiJin = sUtil.isJiJin(zqdm);
			zqmc = tDTO.getZqmc();
			jysmc = tDTO.getFlag1();
			//通过该证券代码，查找lsjgDate中的开始日期，和结束日期；
			LsjgdateDTO lsjgdateDTO=new LsjgdateDTO();
			LsjgdateBO lsjgdateBO = new LsjgdateBO();
			
			
			lsjgdateDTO.setId(zqdm);
			lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
			
//			需要判断有没有该lsjgDAte，如果没有，提示添加；
			Date oldStartDate=null;
			Date oldEndDate=null;
			if(lsjgdateDTO==null){
				throw new BSWException("该股票("+zqdm+")历史价格尚未添加，请到‘历史价格日期更新情况’菜单中添加日期数据！");
			}else{
			
//				自动获取该证券代码在lsjgDate表中存放的开始日期和截止日期
				oldStartDate=lsjgdateDTO.getDatestart();
				oldEndDate=lsjgdateDTO.getDate();
			}
			
			if(!(start_rqUtilDate.getTime()>=oldStartDate.getTime() && end_rqUtilDate.getTime()<=oldEndDate.getTime())){
//				不在原来的范围内；【oldStartDate，end_rqUtilDate】 
				throw new BSWException("该股票("+zqdm+")历史价格 该时间段("+start_rq+"~"+last_rq+")的股票历史价格部分还没有添加（结束日期"+last_rq+"是否已经添加呢？）！请到‘历史价格日期更新情况’菜单中添加缺失部分的日期数据！"); 
			}
			
			//看看是需要分析的日期是否在lsjgdate的日期范围内；
			
			
			
			
			//如果不在，提示更新历史价格日期后再模拟；[缓一缓]
			
			//默认已经下载的情况；
			lsjgDTOs =lsjgBO.loadAll("from LsjgDTO lsjgDTO where lsjgDTO.zqdm='"+zqdm+"' and  lsjgDTO.flag1!='tmp' and ( date(lsjgDTO.date)>='"+start_rq+"' and date(lsjgDTO.date)<='"+last_rq+"') order by lsjgDTO.date");
			//System.out.println(zqdm+"加载的历史价格lsjgDTOs的长度为："+lsjgDTOs.length);
			//此处应该获取最后一个DTO的复权因子；
			//此处应该调用一个方法，double fqyzNow  =lsjgBO.getMaxFqyz(zqdm,start_rqUtilDate);
			int dtoLength = lsjgDTOs.length;
			//double maxFqyzNow = lsjgDTOs[dtoLength-1].getFqyz();//不够准确，应当是系统当前日期的复权因子；
			double maxFqyzNow = lsjgBO.getMaxFqyz(zqdm,start_rqUtilDate);//这样才准确
			double fqyz=0.0d;
			double close=0.0d;
			double tmpAdjclose;
			for(int y=0;y<dtoLength;y++){	
				fqyz = lsjgDTOs[y]	.getFqyz();	
				close = lsjgDTOs[y]	.getClose();
				tmpAdjclose = (close/maxFqyzNow)*fqyz;
				lsjgDTOs[y]	.setAdjclose(tmpAdjclose);
			}
			//找出该lsjgDTOs中的首个大于等于indexUtilDate的index；
			for(int k=0;k<dtoLength;k++){	
				if(lsjgDTOs[k].getDate().getTime()>=indexUtilDate.getTime()){
					INDEX=k;//获取k的值，赋给INDEX;
					break;
				}				
			}
			
			double maDouble[][] = new double[lxDTOs.length][lsjgDTOs.length];
			
			//解析lxDTOs，得出maDouble[0] 应该放什么？
			//获取lxDTOs[i].getArgsIndex()==0 ...2的ma数值：120,20,60;//计算不分先后；
			//maDouble[0]放120，maDouble[1]放20，maDouble[2]放60
			
			MathUtil mathUtil = new MathUtil();
			String name="";
			
			
			for(int j=0;j<lxDTOs.length;j++){
				
//				确切的讲，应该每只股票、每个类型循环的时候重置；
				tmpONESYZJ = ONEKYZJ;//没有买入时，单只股票的默认可用资金为平均分配的资金；				
				tmpONESYGUPIAONUMBER=0;//没有买入时，单只股票的剩余股票数默认为0；
				
				name = lxDTOs[j].getName();//交易类型名称；
				
				maNumb = lxDTOs[j].getMaNumb();//当前lxDTOs[i].getArgsIndex()==i 的ma数值120;；
				
				//中长线减仓系数K 的获取；
				double k=mathUtil.getJianCangXishu(maNumb);
				int tmpCANGWEIFlag= 0;//0，空仓；1，部分仓；2，初次买入的小仓；3，满仓； 默认为空仓0；
				
				
				//maDouble[j]=mathUtil.mathMa(lsjgDTOs,"close",maNumb);//将计算得到的收盘价数组放入到maDouble[j]中；
				maDouble[j]=mathUtil.mathMa(lsjgDTOs,"adjclose",maNumb);//将计算得到的前复权价数组放入到maDouble[j]中；
				//此处应该进行模拟买卖操作；
				
				
				//int stepNumbs = 0;//默认一次买入的股票数量
				JiluDTO jiluDTO=null;
				//需要从INDEX+2 开始判断；
				for(int h=INDEX+2;h<maDouble[j].length;h++){
//					判断此类型的均线是否上拐（走平后上拐，由跌到涨都行）？ 收盘价是否在均线上面？
					if(tmpONESYGUPIAONUMBER==0){
						if(maDouble[j][h-2]!=0d&&maDouble[j][h-2]>=maDouble[j][h-1] && maDouble[j][h-1]<maDouble[j][h] && lsjgDTOs[h].getAdjclose()>maDouble[j][h]){
							
//							System.out.println(zqmc+name+",买入日期："+lsjgDTOs[h].getDate());
//							System.out.println(zqmc+name+",相关指标值"+maDouble[j][h-2]);
//							System.out.println(zqmc+name+",相关指标值"+maDouble[j][h-1]);
//							System.out.println(zqmc+name+",相关指标值"+maDouble[j][h]);
//							System.out.println(zqmc+name+",当天收盘价"+ lsjgDTOs[h].getClose());
//							如果是：那就买入；
							
							//根据收盘价，计算可以买入的数量，放入到stepNumbs;							
							//System.out.println(zqmc+name+",买入前可用资金："+tmpONESYZJ);
							
							double lsgs = tmpONESYZJ/lsjgDTOs[h].getAdjclose();
							//System.out.println("可买入的零散股数："+lsgs);
							
							tmpONESYGUPIAONUMBER = ( new Double( lsgs/100 ).intValue() )*100;
							
							//System.out.println("可买入的股数（未计算佣金）："+tmpONESYGUPIAONUMBER);
							//通过lsjgDTOs构造jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("买入");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//实际买入时的花销，或者实际卖出时的花销；
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER-100;
					        	
					        	jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);//重新设定新的买入数量；
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);//重新设定新的成交金额；
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//重新设定实际买入时的花销；
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("实际可买入的股数（包含所有成本）："+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",实际买入后可用资金："+tmpONESYZJ);
							
//							if(mmflag.equals("卖出")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//没有用的空字段
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("自动虚拟交易");
					        jiluDTO.setJifl(name);					        
					        jiluDTO.setFlag1("已处理");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//调用买卖方法
					        //此处肯定是已处理；逻辑运算和添加记录同时进行；
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 3;//买入后仓位为3满仓；
						}else if(maDouble[j][h-1]!=0d&&maDouble[j][h-1]<maDouble[j][h] &&(lsjgDTOs[h-1].getAdjclose()<=maDouble[j][h-1] || lsjgDTOs[h-1].getLow()<=maDouble[j][h-1])&& lsjgDTOs[h].getAdjclose()>maDouble[j][h]){
							//均线是持续向上的,并且前一天的收盘价或者最低价小于等于前一天的均价 并且当前天的收盘价高于当前天的均价
							//即可小仓位买入（第二买点）；
							double lsgs = (tmpONESYZJ/lsjgDTOs[h].getAdjclose())*0.5;
							//System.out.println("可买入的零散股数："+lsgs);
							
							tmpONESYGUPIAONUMBER = ( new Double( lsgs/100 ).intValue() )*100;
							
							//System.out.println("可买入的股数（未计算佣金）："+tmpONESYGUPIAONUMBER);
							//通过lsjgDTOs构造jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("买入");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//实际买入时的花销，或者实际卖出时的花销；
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER-100;
					        	
					        	jiluDTO.setCjsl(tmpONESYGUPIAONUMBER);//重新设定新的买入数量；
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);//重新设定新的成交金额；
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//重新设定实际买入时的花销；
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("实际可买入的股数（包含所有成本）："+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",实际买入后可用资金："+tmpONESYZJ);
							
//							if(mmflag.equals("卖出")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//没有用的空字段
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("自动虚拟交易");
					        jiluDTO.setJifl(name);
					        jiluDTO.setFlag1("已处理");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//调用买卖方法
					        //此处肯定是已处理；逻辑运算和添加记录同时进行；
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 2;//买入后仓位为2 小仓位；							
						}						
					}else{
						//tmpONESYGUPIAONUMBER==N的情况；
						
						if(maDouble[j][h-1]>maDouble[j][h] && lsjgDTOs[h].getAdjclose()<maDouble[j][h]){
//							如果是：那就卖出；不管是小仓位tmpCANGWEIFlag2，还是半仓tmpCANGWEIFlag1，还是满仓tmpCANGWEIFlag3，一律清空tmpCANGWEIFlag0
//							构造DTO
							
							//System.out.println(zqmc+name+",卖出前可用资金："+tmpONESYZJ);
//							通过lsjgDTOs构造jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("卖出");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        jiluDTO.setCjsl(tmpONESYGUPIAONUMBER*(-1));
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*tmpONESYGUPIAONUMBER);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//实际买入时的花销，或者实际卖出时的花销；
							
					        tmpONESYZJ = tmpONESYZJ + Math.abs(qsje);
					        
					        //System.out.println(zqmc+name+",卖出后可用资金："+tmpONESYZJ);
//							
//							if(jiluDTO.getMmflag().equals("卖出")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//没有用的空字段
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("自动虚拟交易");
					        jiluDTO.setJifl(name);
					        jiluDTO.setFlag1("已处理");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
					        
//					      调用买卖方法
					        //此处肯定是已处理；逻辑运算和添加记录同时进行；
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
					        
					        tmpONESYGUPIAONUMBER=0;
					        
					        tmpCANGWEIFlag= 0;//卖出后，为空仓；
						}else if( (tmpCANGWEIFlag==2||tmpCANGWEIFlag==3) && maDouble[j][h-1]<=maDouble[j][h] && lsjgDTOs[h].getAdjclose()<maDouble[j][h]){
//							如果是：那就卖出部分仓位；
//							构造DTO
							if(k!=0d){
								//如果是小半仓，即tmpCANGWEIFlag= 2;也是按比例卖出；
								int maichuGupiaonumber= ( new Double( (k*tmpONESYGUPIAONUMBER)/100 ).intValue() )*100;//卖出股票数量；
								tmpONESYGUPIAONUMBER =tmpONESYGUPIAONUMBER -maichuGupiaonumber;//剩余股票数量；
								//System.out.println(zqmc+name+",卖出前可用资金："+tmpONESYZJ);
//								通过lsjgDTOs构造jiluDTO
								jiluDTO = new JiluDTO();
								
								jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
						        jiluDTO.setJysj(lsjgDTOs[h].getDate());
						        jiluDTO.setZqdm(zqdm);
						        jiluDTO.setZqmc(zqmc);
						        jiluDTO.setMmflag("卖出");
						        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
						        jiluDTO.setCjsl(maichuGupiaonumber*(-1));
						        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*maichuGupiaonumber);
						        
						        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//实际买入时的花销，或者实际卖出时的花销；
								
						        tmpONESYZJ = tmpONESYZJ + Math.abs(qsje);
						        
						        //System.out.println(zqmc+name+",卖出后可用资金："+tmpONESYZJ);
//								
//								if(jiluDTO.getMmflag().equals("卖出")){
//									
//									cjsl=(cjsl*(-1));
//								}
						        jiluDTO.setQsje(qsje);					        
						        jiluDTO.setYwmc("");//没有用的空字段
						        jiluDTO.setZjzh(zjzh);
						        jiluDTO.setJysmc(jysmc);
						        jiluDTO.setKhdm(idStr);
						        jiluDTO.setGdmc(xm);
						        jiluDTO.setBz("自动虚拟交易");
						        jiluDTO.setJifl(name);						        
						        jiluDTO.setFlag1("已处理");
						        jiluDTO.setFlag2("");
						        jiluDTO.setFlag3("");
						        jiluDTO.setFlag4("");
						        jiluDTO.setFlag5("");
						        jiluDTO.setFlag6("");
						        jiluDTO.setFlag7("");
						        jiluDTO.setFlag8("");
						        jiluDTO.setFlag9("");
						        jiluDTO.setFlag10("");
						        
//						      调用买卖方法
						        //此处肯定是已处理；逻辑运算和添加记录同时进行；
								
						        jBO.logic(jiluDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
						        
						        //tmpONESYGUPIAONUMBER=tmpONESYGUPIAONUMBER;
						        //判断是半仓，
						        tmpCANGWEIFlag= 1;//卖出后
							}
							
						}else if (tmpCANGWEIFlag==1 && maDouble[j][h-1]<=maDouble[j][h] && lsjgDTOs[h].getAdjclose()>maDouble[j][h] ){
							//如果是：那就买入；
							
							//根据收盘价，计算可以买入的数量，放入到stepNumbs;
														
							//System.out.println(zqmc+name+",买入前可用资金："+tmpONESYZJ);
							
							double lsgs = tmpONESYZJ/lsjgDTOs[h].getAdjclose();
							//System.out.println("可买入的零散股数："+lsgs);
							int mairuGupiaonumber= ( new Double( lsgs/100 ).intValue() )*100;
							tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER+mairuGupiaonumber;
							
							//System.out.println("可买入的股数（未计算佣金）："+tmpONESYGUPIAONUMBER);
							//通过lsjgDTOs构造jiluDTO
							jiluDTO = new JiluDTO();
							
							jiluDTO.setTimeid(Long.toString(lsjgDTOs[h].getDate().getTime()));					        
					        jiluDTO.setJysj(lsjgDTOs[h].getDate());
					        jiluDTO.setZqdm(zqdm);
					        jiluDTO.setZqmc(zqmc);
					        jiluDTO.setMmflag("买入");
					        jiluDTO.setCjjg(lsjgDTOs[h].getAdjclose());
					        
					        jiluDTO.setCjsl(mairuGupiaonumber);
					        jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*mairuGupiaonumber);
					        
					        Double qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//实际买入时的花销，或者实际卖出时的花销；
							
					        if(tmpONESYZJ - Math.abs(qsje)<0){
					        	mairuGupiaonumber=mairuGupiaonumber-100;
					        	
					        	jiluDTO.setCjsl(mairuGupiaonumber);//重新设定新的买入数量；
					        	jiluDTO.setCjje(lsjgDTOs[h].getAdjclose()*mairuGupiaonumber);//重新设定新的成交金额；
						        
						        qsje=sUtil.getQsje(idStr,jiluDTO.getCjje(),jysmc,jiluDTO.getMmflag(),isJiJin);//重新设定实际买入时的花销；
						        
						        //tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        }
					        
					        tmpONESYZJ = tmpONESYZJ - Math.abs(qsje);
					        
					        //System.out.println("实际可买入的股数（包含所有成本）："+tmpONESYGUPIAONUMBER);
					        
					    	//System.out.println(zqmc+name+",实际买入后可用资金："+tmpONESYZJ);
							
//							if(mmflag.equals("卖出")){
//								
//								cjsl=(cjsl*(-1));
//							}
					        jiluDTO.setQsje(qsje);					        
					        jiluDTO.setYwmc("");//没有用的空字段
					        jiluDTO.setZjzh(zjzh);
					        jiluDTO.setJysmc(jysmc);
					        jiluDTO.setKhdm(idStr);
					        jiluDTO.setGdmc(xm);
					        jiluDTO.setBz("自动虚拟交易(使用的是前复权价)");
					        jiluDTO.setJifl(name);					        
					        jiluDTO.setFlag1("已处理");
					        jiluDTO.setFlag2("");
					        jiluDTO.setFlag3("");
					        jiluDTO.setFlag4("");
					        jiluDTO.setFlag5("");
					        jiluDTO.setFlag6("");
					        jiluDTO.setFlag7("");
					        jiluDTO.setFlag8("");
					        jiluDTO.setFlag9("");
					        jiluDTO.setFlag10("");
							//调用买卖方法
					        //此处肯定是已处理；逻辑运算和添加记录同时进行；
							
					        jBO.logic(jiluDTO,idStr,baseUserContext,true);//记录没有存在的情况下，要添加，为true ,做lsjgdate登记；
					        //tmpONESYGUPIAONUMBER = tmpONESYGUPIAONUMBER;
					        tmpCANGWEIFlag= 3;//买入后仓位为3满仓；
						}						
					}					
				}				
			}		
			//存入需要放入request的5个数据；zqdm,lxDTOs,lsjgDTOs,INDEX,maDouble;
			//request.setAttribute("ZQDM",zqdm);
			//request.setAttribute("LXDTOS",lxDTOs);
			//request.setAttribute("LSJGDTOS",lsjgDTOs);
			//request.setAttribute("INDEX",INDEX);
			//request.setAttribute("MADOUBLE",maDouble);	
			
			//临时输出到折线图标显示页面中；			
		}		
	}	
}

