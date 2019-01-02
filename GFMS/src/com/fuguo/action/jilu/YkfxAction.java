//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.bo.LsjgdateBO;
import com.fuguo.bo.OrderBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.util.DateUtil;
import com.fuguo.util.StockUtil;
/** 
 * MyEclipse Struts
 * Creation date: 03-21-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class YkfxAction extends BaseAction {

	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		��ȡids ��ת��Ϊint��
		BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int idUser  =baseUserContext.getId();
		String idStr  =Integer.toString(idUser);
		String[] ids_Str=request.getParameterValues("ids");
		if(ids_Str==null){
			throw new BSWException("��û��ѡ���κι�Ʊ��");
		}
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		GpmcBO gpmcBO = new GpmcBO();
		GpmcDTO gpmcDTO=new GpmcDTO();
		double dqj = 0;
		int cysl=0;
		double dqsz=0;
		OrderBO uBO =new OrderBO();
		List listTMP = uBO.sqlQuery("select id,zqdm,zqmc,cysl,cbj  from order where flag1='"+idStr+"'", OrderDTO.class);
		StockUtil sUtil = new StockUtil();
		Iterator itTMP = listTMP.iterator();
		OrderDTO _orderDTO=null;
		Map<String,Double> mapGupiaoShizhi=new HashMap<String,Double>();
		while(itTMP.hasNext()){
			_orderDTO=(OrderDTO)itTMP.next();
			
			String zqdm = _orderDTO.getZqdm();
			
			cysl = _orderDTO.getCysl();
			
			dqj = sUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�
			dqsz = dqj*cysl;
			
			mapGupiaoShizhi.put(zqdm,dqsz);
		}
		
		
		
		
		//int[] ids=new int[ids_Str.length];
//		for(int j=0;j<ids_Str.length;j++){
//			ids[j] = Integer.parseInt(ids_Str[j]);
//		}
		JiluBO t=new JiluBO();
		//ѭ������ÿһ��id
		JiluDTO tDTO;
		StringBuffer sb =new StringBuffer(); ; 
		sb.append("select distinct a.zqdm, qsje from ( select sum(qsje) qsje,zqdm  from jilu where  khdm='"+idStr+"' and ( ");
		
		
		for(int i=0;i<ids_Str.length;i++ ){
			//��ÿһ��id���д���
			if(i==0){
				sb.append("zqdm='"+ids_Str[i]+"'");
				
			}else{
				sb.append(" or zqdm='"+ids_Str[i]+"'");
			}
			
		}
		sb.append(")");	
		sb.append("  group by zqdm) a,jilu b where a.zqdm=b.zqdm order by qsjes desc");
		String sql  =sb.toString();
		//System.out.println(sql);
//		����ҵ���߼���
		JiluBO tBO = new JiluBO();
		//�õ�Map�͵�list
		List list = tBO.sqlQuery(sql,JiluDTO.class);
		//���ɷ���Ҫ���JiluDTO����
//		����list<Map>��
		List listDTOs=new ArrayList();
		Iterator it = list.iterator();
		//Map _map=null;
		JiluDTO mDTO;
		String zqdm="";
		double qsje=0.0;
		double qsjeFu=0.0;
		double qsjeZheng = 0.0;
		
		LsjgdateBO lsjgdateBO= new LsjgdateBO();
		LsjgdateDTO lsjgdateDTO;
		
		while(it.hasNext()){
			mDTO=(JiluDTO)it.next();
			
			
			zqdm = mDTO.getZqdm();
				
			//ͨ��֤ȯ�����ȡ֤ȯ����
			gpmcDTO.setZqdm(zqdm);
			mDTO.setZqmc(gpmcBO.query(gpmcDTO).getZqmc());
			qsje = mDTO.getQsje();
			
			//�жϵ�ǰ���еĹ�Ʊ���Ƿ��иù�Ʊ���������������Ҫ���ϸù�Ʊ����ֵ��
			if(mapGupiaoShizhi.containsKey(zqdm)){
				qsje = mapGupiaoShizhi.get(zqdm)+qsje;
			}
			mDTO.setQsje(qsje);
			if(qsje>0){
				qsjeZheng+=qsje;
			}else{
				qsjeFu+=qsje;
			}
			
			/*
			 * date:2018-11-22
			 * ���ӹ��ܣ�ӯ���������棬ÿֻ��Ʊ�Ľ�����ʼ����Ҫ����������ʾ��
			 * ����Ĺ�ƱK�߸�������ҲҪ�У����Բ鿴K�߸���ʱ����Ƿ��Ѱ���ʱ�佻�׼�¼���ڶΣ�
			 * �����Ǳ�������ͼ����ʱ���ã�����������ʱ��ʾ			 
			 */
			
			//��Ʊ���׼�¼��ʼ�Σ�
			//private Date jiludateMin;
		    // private Date jiludateMax;
			//����zqdm��ѯ���׵���С���ڣ��������2����¼�ķ�����
			//idUser,zqdm
			
			String sqlJiluDateMinMax = "select min(jysj) jiludateMin,max(jysj) jiludateMax from jilu where khdm='"+idStr+"' and  zqdm='"+zqdm+"'";
			
			List listJiluDateMinMax = tBO.sqlQuery(sqlJiluDateMinMax,JiluDTO.class);
			if(!listJiluDateMinMax.isEmpty()){
				JiluDTO jiluDTO = (JiluDTO)listJiluDateMinMax.get(0);
				
				
				mDTO.setJiludateMin(jiluDTO.getJiludateMin());
				mDTO.setJiludateMax(jiluDTO.getJiludateMax());
			}
			
			//��ƱK�߸���������ʼ��
			lsjgdateDTO=new LsjgdateDTO();
			lsjgdateDTO.setZqdm(zqdm);
			lsjgdateDTO = lsjgdateBO.query(lsjgdateDTO);
			if(lsjgdateDTO!=null){
				mDTO.setDatestart(lsjgdateDTO.getDatestart());
				
				mDTO.setDate(lsjgdateDTO.getDate());
			}
			
			//flag4//K�������Ƿ��Ѱ�����
			Date JiluMin = mDTO.getJiludateMin();
			Date JiluMax = mDTO.getJiludateMax();
			if(lsjgdateDTO==null ||JiluMin==null||JiluMax==null){
				mDTO.setFlag4("��");
			}else{
				
				Integer number = DateUtil.getDateDiff(JiluMin,JiluMax);
				mDTO.setFlag5(number.toString());
				//System.out.println(JiluMax+"��"+mDTO.getDate()+"��������ǣ�"+number);
				
				String JiluMaxStr = sdf.format(JiluMax);
				String KDateEndStr = sdf.format(mDTO.getDate());
				
				if(JiluMin.getTime()>=mDTO.getDatestart().getTime()&&(JiluMax.getTime()<=mDTO.getDate().getTime() ||JiluMaxStr.equals(KDateEndStr))){
					mDTO.setFlag4("��");
				}else{
					mDTO.setFlag4("��");
				}
				
			}
			
			
			
			
			listDTOs.add(mDTO);
			
			
		}
		
		
		request.setAttribute("JILU",listDTOs);
		request.setAttribute("ZHENG",qsjeZheng);
		request.setAttribute("FU",qsjeFu);
	}

	

}

