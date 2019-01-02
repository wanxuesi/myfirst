//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ccgp;

import java.util.ArrayList;
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
import com.fuguo.bo.DataBO;
import com.fuguo.bo.ListBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.util.StockUtil;

public class OrderShowAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		 TODO 自动生成方法存根
		ListBO lBO =new ListBO();
		lBO.sqlUpdateOrDel("delete from list where cysl=0");
		OrderBO uBO =new OrderBO();
		
		uBO.sqlUpdateOrDel("delete from order where cysl=0");
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
//		报警相关
		ConfigBO cBO=new ConfigBO();
		ConfigDTO m=new ConfigDTO();
		m.setId(idStr);
		m  = cBO.query(m);
		int onoff =m.getOnoff();//0表示关，1表示开；
        double confirmbfb =m.getConfirmbfb();
        
        double onegupiaocangweibfb =m.getOnegupiaocangweibfb();
        
        double onejiflcangweibfb =m.getOnejiflcangweibfb();
		
		
		
//      完全可以用hql
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+idStr+"'");
		
		Iterator it = listTMP.iterator();
		Map _map=null;
		OrderDTO mDTO;
		
		StockUtil sUtil = new StockUtil();
		double dqj = 0;
		double dqsz=0;
		double dqcb=0;
		double yk = 0;
		int cysl=0;
		double cbj = 0;
		double ykblDouble=0;
		double GPSZ=0;
		double GPZCB=0;
		double GPZYK=0;//持仓股票总盈亏
		boolean isJiJin=false;
		List list=new ArrayList();
		String baojingmessage="\n";
		while(it.hasNext()){
			_map=(Map)it.next();
			mDTO=new OrderDTO();
			mDTO.setId((Integer)_map.get("ID"));
			mDTO.setZqmc((String)_map.get("ZQMC"));
			String zqdm = (String)_map.get("ZQDM");
			mDTO.setZqdm(zqdm);
			cysl = (Integer)_map.get("CYSL");
			mDTO.setCysl(cysl);
			cbj = (Double)_map.get("CBJ");
			mDTO.setCbj(cbj);
			
			dqj = sUtil.getDqjByZqdm(zqdm);//当前价；
			isJiJin = sUtil.isJiJin(zqdm);//判断是否为基金；
			if(isJiJin==true){
				mDTO.setFlag1("YES");
			}else{
				mDTO.setFlag1("NO");
			}
			mDTO.setScj(dqj);
			dqsz = dqj*cysl;
			dqcb = cbj*cysl;
			GPSZ+=dqsz;
			GPZCB+=dqcb;
			mDTO.setDqsz(dqsz);//当前市值
			
			yk = (dqj-cbj)*cysl;
			GPZYK+=yk;
			mDTO.setYk(yk);//盈亏
			
			//ykblDouble=(dqj-cbj)/cbj;
			
			ykblDouble=(dqj-cbj)/Math.abs(cbj);
			//成本为负数时，收益率为负的bug；
			
			String ykbl = String.format("%.4f", ykblDouble*100d);
			mDTO.setYkbl(ykbl+"%");//盈亏比例
			if(onoff==1){
				if(ykblDouble<0 && Math.abs(ykblDouble)>(confirmbfb)){
					baojingmessage+=mDTO.getZqmc()+"亏损幅度为："+ykbl+"%,已经大于"+confirmbfb+"，请及时止损！！\n";
					
				}
			}
			//将该order的list，放入到order中；
//			完全可以用hql
			String sql2 = "select zqdm,zqmc,cysl,jifl from list where  flag1='"+idStr+"' and zqdm='"+zqdm+"'"; 
			
//			调用业务逻辑层
			//得到Map型的list
			List list2 = lBO.sqlQuery(sql2);
			List<ListDTO> listDTO2=new ArrayList<ListDTO>();
			Iterator it2 = list2.iterator();
			ListDTO aDTO;
			String listStr=(String)_map.get("ZQMC")+"(<font color='red'>"+zqdm+"</font>)：&nbsp;&nbsp;&nbsp;";
			while(it2.hasNext()){
				_map=(Map)it2.next();
				aDTO=new ListDTO();
				//mDTO.setId((Integer)_map.get("ID"));
				aDTO.setZqdm(zqdm);
				aDTO.setZqmc((String)_map.get("ZQMC"));
				aDTO.setCysl((Integer)_map.get("CYSL"));
				aDTO.setJifl((String)_map.get("JIFL"));

				listStr+="<font color='blue'>"+(String)_map.get("JIFL")+"</font>:<font color='red'>"+(Integer)_map.get("CYSL")+"</font>股；";
			}
			mDTO.setListStr(listStr);
			
			list.add(mDTO);
		}
		
		//获取当前可用资金；
//		获取股息红利总和；
		Double KYZJ = 0.0;
		String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='资金进出' or name='股息红利')"; 
//		得到Map型的list4
		DataBO dBO  =new DataBO();
		List list4 = dBO.sqlQuery(sql4);
		
		Iterator it4 = list4.iterator();
		Map _map4=null;
		
		
		
		
		if(it4.hasNext()){
			_map4=(Map)it4.next();
			KYZJ  =(Double)_map4.get("SHUJU");
			if(KYZJ==null){
				KYZJ=0.0;
			}
		}
		
		
		if(onoff==1){
//			重新解析list中的每只股票的市值；
			   Iterator ittmp = list.iterator();
			   OrderDTO oDTO=null;
			   String tmpZqmc;
			   
			   Double tmpCbj;
			   int tmpCysl=0;
			   Double tmpOnegupiaocangweibfb;
			   while(ittmp.hasNext()){
			   		oDTO=(OrderDTO)ittmp.next();
			   		tmpZqmc = oDTO.getZqmc();
			   		tmpCbj = oDTO.getCbj(); 
			   		tmpCysl = oDTO.getCysl();
			   		
			   		tmpOnegupiaocangweibfb = (tmpCbj*tmpCysl)/(KYZJ+GPZCB);
			   		
			   		//System.out.println(tmpOnegupiaocangweibfb);
			   		String tmpbl = String.format("%.4f", tmpOnegupiaocangweibfb*100d);
			   		if(tmpOnegupiaocangweibfb>onegupiaocangweibfb){
			   			baojingmessage+=tmpZqmc+"仓位占比为："+tmpbl+"%,已经大于个人配置最大值"+onegupiaocangweibfb+"，请及时降低仓位！！\n";
						
					}
			   } 
		}
		
		
		request.setAttribute("ORDERS",list);
		request.setAttribute("KYZJ",String.format("%.2f", KYZJ));//可用资金
		request.setAttribute("GPSZ",String.format("%.2f", GPSZ));//股票市值
		request.setAttribute("GPZCB",String.format("%.2f", GPZCB));//股票ZCB
		request.setAttribute("GPZYK",String.format("%.2f", GPZYK));//股票ZYK
		request.setAttribute("ZZC",String.format("%.2f", (KYZJ+GPSZ)));//总资产
		request.setAttribute("DQZCW",String.format("%.2f", (GPSZ/(KYZJ+GPSZ))*100d)+"%");//当前总仓位
		request.setAttribute("CCYKB",String.format("%.2f", (GPZYK/GPZCB)*100d)+"%");//盈亏比
		request.setAttribute("BAOJINGMESSAGE",baojingmessage);//报警提示！
		
//		此处生成String类型的字符串更好；
		  
		   String dataStr="[['可用资金',"+KYZJ+"]";
		   
		   
		   OrderDTO oDTO=null;
		   
		   for(int i=0;i<list.size();i++){
			   oDTO = (OrderDTO) list.get(i);
		   		dataStr+=",['"+oDTO.getZqmc()+"',";
		   		dataStr+=oDTO.getDqsz()+"]";
		   
		   }
		   
		   	dataStr+="]";
		   	request.setAttribute("dataStr",dataStr);
		   	
		   	//System.out.println(dataStr);
		
	}


}

