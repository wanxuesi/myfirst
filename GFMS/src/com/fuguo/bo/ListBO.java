package com.fuguo.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.ListDTO;
import com.fuguo.po.ListPO;
import com.fuguo.po.OrderPO;
import com.fuguo.util.DateUtil;

public class ListBO implements BaseBO{
ListPO listPO = null;


/**
 * ��Ӷ���
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public ListDTO add(ListDTO listDTO) throws BSWException{
	
	listPO=new ListPO();		
	
	return (ListDTO)listPO.add(listDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param list0DTO
 * @return
 * @throws BSWException
 */
public ListDTO query(ListDTO listDTO) throws BSWException{
	listPO=new ListPO();
	return (ListDTO)listPO.query(listDTO);
}


/**
 * ���¶���
 * @param list0DTO
 * @throws BSWException
 */
public void update(ListDTO listDTO) throws BSWException{	
	listPO=new ListPO();
	listPO.update(listDTO);
}


/**
 * ɾ��һ������
 * @param listDTO
 * @throws BSWException
 */
public void delete(ListDTO listDTO) throws BSWException{
	listPO=new ListPO();
	listPO.delete(listDTO);
}


/**
 * ɾ���������
 * @param listDTOS
 * @throws BSWException
 */
public void delete(ListDTO[] listDTOS) throws BSWException{
	listPO=new ListPO();
	listPO.delete(listDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public ListDTO[] loadAll(String _hql)throws BSWException{	
	listPO=new ListPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])listPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽ListDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	ListDTO[] listDTOS=new ListDTO[baseDTOS.length];
	for(int i=0;i<listDTOS.length;i++){
		listDTOS[i]=new ListDTO();
		listDTOS[i]=(ListDTO)baseDTOS[i];
	}
	return listDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public ListDTO[] search(PageRoll pageRoll) {
//	String hql = "from ListDTO";
//	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	ListPO listPO = new ListPO();
	BaseDTO[] baseDTO = listPO.search(pageRoll);
	ListDTO[] listDTO = null;
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateUtil dateUtil=new DateUtil();
	Date jhkssj;
	Date jhjssj;
	String jhkssjStr;
	String jhjssjStr;
	String jhkssjWeek;
	String jhjssjWeek;
	String tmpgzcs;
	String gzcs;
	String bdz;
	String tbdw;
	String flag1;
	listDTO = new ListDTO[baseDTO.length];
	for (int i = 0; i < listDTO.length; i++) {
		listDTO[i] = (ListDTO) baseDTO[i];
		
		
		
//		jhkssj=listDTO[i].getJhkssj();		
//		jhjssj=listDTO[i].getJhjssj();
//		
//		jhkssjStr=sdf.format(jhkssj);
//		jhjssjStr=sdf.format(jhjssj);
//		
//		jhkssjWeek = dateUtil.getWeekOfDate(jhkssj);
//		jhjssjWeek = dateUtil.getWeekOfDate(jhjssj);
//		
//		jhkssjStr=jhkssjStr.replace(" ","<br>");
//		jhjssjStr=jhjssjStr.replace(" ","<br>");
//		//���뵽tdto��
//		listDTO[i].setJhkssjStr(jhkssjStr+jhkssjWeek);
//		listDTO[i].setJhjssjStr(jhjssjStr+jhjssjWeek);
//		
//		if(listDTO[i].getGzjgorxl()==null){
//			listDTO[i].setGzjgorxl("");
//		}
//		listDTO[i].setGzjgorxl(listDTO[i].getGzjgorxl().replace("\r\n","<br>"));
//		listDTO[i].setGznr(listDTO[i].getGznr().replace("\r\n","<br>"));
//		listDTO[i].setGzjgorxl(listDTO[i].getGzjgorxl().replace("\n","<br>"));
//		listDTO[i].setGznr(listDTO[i].getGznr().replace("\n","<br>"));
////		�Ե�ѹ�ȼ�����ȥ��kV��
//		if(listDTO[i].getDydj()==null){
//			listDTO[i].setDydj("");
//		}
//		listDTO[i].setDydj(listDTO[i].getDydj().replace("kV",""));
//		if(listDTO[i].getGzly()==null){
//			listDTO[i].setGzly("");
//		}
//		listDTO[i].setGzly(listDTO[i].getGzly().replace(";","<br>"));
//		if(listDTO[i].getGzbz()!=null){
//			listDTO[i].setGzbz(listDTO[i].getGzbz().replace(";","<br>"));
//		}
////		�Թ������� ����ƴ�ӱ��վ��
//		if(listDTO[i].getGzcs()==null){
//			listDTO[i].setGzcs("");
//		}
//		tmpgzcs=listDTO[i].getGzcs();
//		bdz=listDTO[i].getBdz();
//		if(tmpgzcs.equals("��·")){
//			gzcs = tmpgzcs;
//		}else{
//			gzcs = bdz;
//		}
//
//		listDTO[i].setGzcs(gzcs);
//		
//		
//		//�ǲ��Ǳ���λ�����ж�
//		flag1=listDTO[i].getFlag1();
//		if(flag1==null){
//			
//		}else if(flag1.equals("��")){			
//		}else{
//			
//			listDTO[i].setGzbz(listDTO[i].getSgdw());
//		}
		
		
		
		
	}
	return listDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	listPO=new ListPO();
	List list = listPO.tuplesQuery(hql);
	
	
	
	return list;
}


/**
 * 
 *����:���������ѯ������sql��
 * @param sql
 * @return List<Map>
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql) throws BSWException {
	listPO=new ListPO();
	List list =listPO.sqlQuery(sql);
	
	return list;
}


public List sqlQuery(String sql,Class classArg) throws BSWException {
	listPO=new ListPO();
	List list =listPO.sqlQuery(sql,classArg);
	
	return list;
}
public void sqlUpdateOrDel(String sql) throws BSWException{
	listPO=new ListPO();
	listPO.sqlUpdateOrDel(sql);
}

public int getCyslByZqdmAndJifl(String zqdm,String jifl)throws BSWException{
	//ͨ��session��ȡuserid��501����
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	ListDTO[] listDTOs=null;
	int cysl=0;
	listDTOs = loadAll("from ListDTO listDTO where listDTO.flag1='"+idStr+"' and listDTO.zqdm='"+zqdm+"' and listDTO.jifl='"+jifl+"'");
	if(listDTOs.length>0){
		//zqdm = listDTOs[0].getZqdm();
		cysl = listDTOs[0].getCysl();	
	}	
	return cysl;
}
public static void main(String[] args)throws BSWException{
	ListBO dBO=new ListBO();
//	ListDTO mdto = new ListDTO();
//	mdto.setTbdw("ʡ�쳣�ݹ���");
//	mdto.setDiqu("����");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from ListDTO");
	System.out.println(list.size());
	
	

	
	
}


}
