package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DataDTO;
import com.fuguo.po.DataPO;
import com.fuguo.po.JiluPO;

public class DataBO implements BaseBO{
DataPO dataPO = null;


/**
 * ��Ӷ���
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public DataDTO add(DataDTO dataDTO) throws BSWException{
	
	dataPO=new DataPO();		
	
	return (DataDTO)dataPO.add(dataDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param data0DTO
 * @return
 * @throws BSWException
 */
public DataDTO query(DataDTO dataDTO) throws BSWException{
	dataPO=new DataPO();
	return (DataDTO)dataPO.query(dataDTO);
}


/**
 * ���¶���
 * @param data0DTO
 * @throws BSWException
 */
public void update(DataDTO dataDTO) throws BSWException{	
	dataPO=new DataPO();
	dataPO.update(dataDTO);
}


/**
 * ɾ��һ������
 * @param dataDTO
 * @throws BSWException
 */
public void delete(DataDTO dataDTO) throws BSWException{
	dataPO=new DataPO();
	dataPO.delete(dataDTO);
}


/**
 * ɾ���������
 * @param dataDTOS
 * @throws BSWException
 */
public void delete(DataDTO[] dataDTOS) throws BSWException{
	dataPO=new DataPO();
	dataPO.delete(dataDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public DataDTO[] loadAll(String _hql)throws BSWException{	
	dataPO=new DataPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])dataPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽DataDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	DataDTO[] dataDTOS=new DataDTO[baseDTOS.length];
	for(int i=0;i<dataDTOS.length;i++){
		dataDTOS[i]=new DataDTO();
		dataDTOS[i]=(DataDTO)baseDTOS[i];
	}
	return dataDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public DataDTO[] search(PageRoll pageRoll) {
	String hql = "from DataDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	DataPO dataPO = new DataPO();
	BaseDTO[] baseDTO = dataPO.search(pageRoll);
	DataDTO[] dataDTO = null;
	dataDTO = new DataDTO[baseDTO.length];
	for (int i = 0; i < dataDTO.length; i++) {
		dataDTO[i] = (DataDTO) baseDTO[i];
	}
	return dataDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	dataPO=new DataPO();
	List list = dataPO.tuplesQuery(hql);
	
	
	
	return list;
}
/**
 * 
 *����:���������ѯ������sql��
 * @param sql,��Ҫ��װ��bean��
 * @return List
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql,Class classArg) throws BSWException {
	dataPO=new DataPO();
	List list =dataPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	dataPO=new DataPO();
	List list =dataPO.sqlQuery(sql);
	
	return list;
}

public void sqlUpdateOrDel(String sql) throws BSWException{
	dataPO=new DataPO();
	dataPO.sqlUpdateOrDel(sql);
}
/**
 * dwr ר��
 * @param args
 */

public Map getDatas() throws BSWException{
	Map map=new HashMap();
	dataPO=new DataPO();
	List list =dataPO.sqlQuery("select name from data");
	//����list<Map>��
	Iterator it = list.iterator();
	Map _map=null;
	while(it.hasNext()){
		_map=(Map)it.next();
		map.put(_map.get("NAME"),_map.get("NAME"));
	}
	
	
	return map;
}
/**
 * dwr ר�� ��ȡ�����ʽ�
 * @param args
 */
public Double getKYZJ() throws BSWException{
	//��ȡ��ǰ�����ʽ�
//	��ȡ��Ϣ�����ܺͣ�
	
//	ͨ��session��ȡuserid��501����
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='�ʽ����' or name='��Ϣ����')"; 
//	�õ�Map�͵�list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}

public Double getKYZJ(String idStr) throws BSWException{
	//��ȡ��ǰ�����ʽ�
//	��ȡ��Ϣ�����ܺͣ�
	

	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='�ʽ����' or name='��Ϣ����')"; 
//	�õ�Map�͵�list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}

public Double getKYZJ(String idStr,String dateStr) throws BSWException{
	//��ȡ��ǰ�����ʽ�
//	��ȡ��Ϣ�����ܺͣ�
	//����ʱ��date
	String sqlWhereDate=" and (date(date)<='"+dateStr+"') ";//ͣ��

	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='�ʽ����' or name='��Ϣ����') "+sqlWhereDate; 
//	�õ�Map�͵�list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}
public static void main(String[] args)throws BSWException{
	DataBO dBO=new DataBO();
	
	Double KYZj = dBO.getKYZJ("500","2018-11-29");
//	DataDTO gdto = new DataDTO();
//	gdto.setName("����");
//	dBO.add(gdto);
	System.out.print(KYZj);

	

	
	
}


}
