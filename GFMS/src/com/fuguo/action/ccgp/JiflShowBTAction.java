//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.ccgp;

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
import com.fuguo.bo.LxBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.LxDTO;
import com.fuguo.util.StockUtil;

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiflShowBTAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		LxBO lxBO = new LxBO();
		
		//String[] jiflTitle = new String[]{"高频","短线","中线","长线","超长线"};//以后可以动态定义；
		LxDTO[] lxDTJOs = lxBO.loadAll("from LxDTO where flag1='"+idStr+"' or flag1='' or flag1 is Null  order by  id");
		
		String[] jiflTitle = new String[lxDTJOs.length];
		
		for(int i=0;i<lxDTJOs.length;i++){
			jiflTitle[i] = lxDTJOs[i].getName();
		}
		
		
		Double[] jiflValue  =new Double[jiflTitle.length];


		String baojingmessage="\n";
		double dqj = 0;
		int cysl=0;
		double dqsz=0;
		double dqszAll=0;
		String zqdm="";
		ListDTO[] listDTOs=null;
		ListBO uBO =new ListBO();
		StockUtil sUtil = new StockUtil();
		for(int i=0;i<jiflTitle.length;i++){
			listDTOs = uBO.loadAll("from ListDTO listDTO where listDTO.flag1='"+idStr+"' and listDTO.jifl='"+jiflTitle[i]+"'");
			
			for(int j=0;j<listDTOs.length;j++){
				zqdm = listDTOs[j].getZqdm();
				cysl = listDTOs[j].getCysl();
				dqj = sUtil.getDqjByZqdm(zqdm);//当前价；
				dqsz = dqj*cysl;
				//System.out.println(dqsz);
				//System.out.println(jiflValue[i]);
				dqszAll+=dqsz;
				
				
			}
			jiflValue[i]=dqszAll;
			dqszAll=0;
			//System.out.println(jiflTitle[i]+":"+jiflValue[i]);
		}
		
		
		//获取当前可用资金；
//		获取股息红利总和；
		Double KYZJ = 0.0;
		String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='资金进出' or name='股息红利')"; 
//		得到Map型的list4
		DataBO dBO  =new DataBO();
		List list4 = dBO.sqlQuery(sql4,DataDTO.class);
		
		
		Iterator it4 = list4.iterator();
		DataDTO dataDTO=null;
		
		
		
		
		if(it4.hasNext()){
			dataDTO=(DataDTO)it4.next();
			KYZJ  =dataDTO.getShuju();
			if(KYZJ==null){
				KYZJ=0.0;
			}
		}
		
		
		
//		报警相关
		ConfigBO cBO=new ConfigBO();
		ConfigDTO m=new ConfigDTO();
		m.setId(idStr);
		m  = cBO.query(m);
		int onoff =m.getOnoff();//0表示关，1表示开；
        double confirmbfb =m.getConfirmbfb();
        
        double onegupiaocangweibfb =m.getOnegupiaocangweibfb();
        double onejiflcangweibfb =m.getOnejiflcangweibfb();
        
        if(onoff==1){
//        	计算总资金；
    		Double ZZJ=0d;
    		Double ZSZ=0d;//总市值
    		for(int i=0;i<jiflValue.length;i++){
    			ZSZ=ZSZ+jiflValue[i];
    		}
    		
    		ZZJ=ZSZ+KYZJ;
    		Double tmpOnejiflcangweibfb=0d;
    		
    		for(int i=0;i<jiflValue.length;i++){
    			tmpOnejiflcangweibfb = jiflValue[i]/ZZJ;
    			String tmpbl = String.format("%.4f", tmpOnejiflcangweibfb*100d);//这行运行不稳定，结果有时候为零，原因？？？
    			if(tmpOnejiflcangweibfb>onejiflcangweibfb){
		   			baojingmessage+=jiflTitle[i]+"仓位占比为："+tmpbl+"%,已经大于个人配置中交易分类仓位报警"+onejiflcangweibfb+"，请及时降低仓位！！\n";
					
				}
    		}
    		
        }
		
		
		request.setAttribute("KYZJ",String.format("%.2f", KYZJ));//可用资金
		//request.setAttribute("JIFLTITLE",jiflTitle);
		//request.setAttribute("JIFLVALUE",jiflValue);
		
		
		request.setAttribute("BAOJINGMESSAGE",baojingmessage);//报警提示！
		
//		此处生成String类型的字符串更好；
		   String KYZJStr = (String)request.getAttribute("KYZJ");
		  	Double KYZJDouble = Double.parseDouble(KYZJStr);
		   String dataStr="[['可用资金',"+KYZJDouble+"]";
		   for(int i=0;i<jiflTitle.length;i++){
		  
		   		dataStr+=",['"+jiflTitle[i]+"',";
		   		dataStr+=jiflValue[i]+"]";
		   
		   }
		   
		   	dataStr+="]";
		   	request.setAttribute("dataStr",dataStr);		   
		   //System.out.println(dataStr);
		   
		   
	}

	

}

