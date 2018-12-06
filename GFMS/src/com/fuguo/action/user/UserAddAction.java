//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.user;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.ConfigBO;
import com.fuguo.bo.DataBO;
import com.fuguo.bo.UserBO;
import com.fuguo.dto.ConfigDTO;
import com.fuguo.dto.DataDTO;
import com.fuguo.dto.UserDTO;
import com.fuguo.form.UserForm;
import com.fuguo.util.DateUtil;

/** 
 * MyEclipse Struts
 * Creation date: 07-16-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class UserAddAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserForm d = (UserForm)form;
		String xm = d.getXm();
		
		
		
		
		UserBO bBO =new UserBO();
		//通过姓名查找有没有该姓名的用户，如果有：就报：已有该用户；
		UserDTO tmpDTO[] = bBO.loadAll("from UserDTO u where u.xm='"+xm+"'"); 
		if(tmpDTO.length>0){
			throw new BSWException("该用户已存在！");
		}
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		
		String wx = d.getWx();
		
		int dwId = d.getDwId();
		String otherflag=d.getOtherflag();
		
		
		UserDTO bDTO = new UserDTO();
		bDTO.setXm(xm);
		bDTO.setDwId(dwId);
		
		bDTO.setOtherflag(otherflag);
		
		bDTO.setNx("999999");
		bDTO.setWx(wx);
		
//		String datestart = d.getDatestart();
		//String dateend = d.getDateend();
		//应该直接获取系统时间；然后获取系统配置参数，通过DateUtil获取dateend;
		Date datestart = new java.util.Date();
		
//		改为从系统配置里调用；
		String xzhmryxsjStr = (String)baseUserContext.getSystemconfig().get("xzhmryxsj");
		Integer xzhmryxsj = Integer.parseInt(xzhmryxsjStr);
		
		Date dateend = DateUtil.getAfterNDay(datestart, xzhmryxsj);
		
		bDTO.setDatestart(datestart);
		bDTO.setDateend(dateend);
		//调用业务逻辑层
		
		bDTO = bBO.add(bDTO);
		//增加个人配置；
		int userId=bDTO.getId();
		String idStr = Integer.toString(userId);
		ConfigDTO tDTO  = new ConfigDTO();
		
		
		tDTO.setId("-1");
		ConfigBO tBO = new ConfigBO();
		tDTO=tBO.query(tDTO);
		tDTO.setId(idStr);

		//为什么double变量的值传过去，有错误；显示为E-24等等；
//      tDTO.setOnoff(1);
//      tDTO.setYongjinmin(5);
//      tDTO.setGuohufei(0.00002D);
//      tDTO.setGuohufeimin(0);
//      
//      tDTO.setConfirmbfb(0.1D);
//      tDTO.setYinhuashuibuy(0.0D);
//      tDTO.setYinhuashuisell(0.001D);
//      tDTO.setOnegupiaocangweibfb(0.5D);
//      tDTO.setOnejiflcangweibfb(0.35D);
//      tDTO.setYongjin(0.0003D);
	
      tBO.add(tDTO);
      
//    初始化data里面的数据；
		DataBO dataBO=new DataBO();
      	DataDTO dDTO=new DataDTO();
		dDTO.setName("资金进出");
		//为什么赋值不成功；怎么变成这个值呢？？3.626915799848539E-275
		
		//double shuju=200000d;
		//改为从系统配置里调用；
		String shujuStr = (String)baseUserContext.getSystemconfig().get("defaultmoney");
		Double shuju = Double.parseDouble(shujuStr);
		System.out.println("赋值数据为："+shuju);
		DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();

		//System.out.println(df.format(shuju));
		dDTO.setShuju(shuju);
		//初始化份额数；
		dDTO.setFene(shuju);//1元对应1.00的份额；
		dDTO.setDate(new Date());
		dDTO.setFlag2(idStr);
		dataBO.add(dDTO);
		
	}

}

