package com.fuguo.action.jilu;

//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl


import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;

import com.fuguo.bo.JiluBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgDTO;
import com.fuguo.form.JiluForm;


//zhou

/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluShowJsonAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO 自动生成方法存根

		JiluForm m = (JiluForm)form;
		String zqdm  = m.getZqdm();
		
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		
		JiluBO mBO = new JiluBO();
		JiluDTO[] jiluDTO=mBO.loadAll("from JiluDTO jiluDTO where  khdm='"+idStr+"' and zqdm='"+zqdm+"' and cjsl!=0 and cjjg >0 order by jiluDTO.jysj");

		String lsjgStr="";
		long lsjgTime;
		String mmflag="";
	    String jifl="";
	    double cjjg;
	    double close=3.37;//问什么无法显示成交数量呢？

	    SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	      SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
		StringBuffer sb  =new StringBuffer();
		sb.append("[");
		for(int i=0;i<jiluDTO.length;i++){
			
			//lsjgStr =sdf.format(jiluDTO[i].getJysj());
			lsjgTime = sdf.parse(sdf.format(jiluDTO[i].getJysj())).getTime();
			//lsjgTime = jiluDTO[i].getJysj().getTime();
			//System.out.println(lsjgTime);
			mmflag = jiluDTO[i].getMmflag();
			jifl = jiluDTO[i].getJifl();
			cjjg  = jiluDTO[i].getCjjg();
			//close = Integer.toString(jiluDTO[i].getCjsl());
			if(i<1){
				sb.append("["+lsjgTime+",\""+mmflag+"\",\""+jifl+"\","+cjjg+","+close+"]");
			}else{
				sb.append(",["+lsjgTime+",\""+mmflag+"\",\""+jifl+"\","+cjjg+","+close+"]");
			}
			
		}
		sb.append("]");
		response.setCharacterEncoding("utf-8");//解决汉字乱码
		PrintWriter out = null; 
		//response.setCharacterEncoding("utf-8");
		        out = response.getWriter();  
		        out.append(sb.toString());  
		       //out.print(sb.toString());
		    
		        if (out != null) {  
		            out.close();  
		        }  
		   
		//response.getWriter().write(sb.toString());

		
		
	}

	
}

