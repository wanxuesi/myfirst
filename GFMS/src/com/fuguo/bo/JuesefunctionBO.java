package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.po.JuesefunctionPO;

public class JuesefunctionBO implements BaseBO{
JuesefunctionPO juesefunctionPO = null;


/**
 * ��Ӷ���
 * @param juesefunction0DTO
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO add(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	
	juesefunctionPO=new JuesefunctionPO();		
	
	return (JuesefunctionDTO)juesefunctionPO.add(juesefunctionDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param juesefunction0DTO
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO query(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	return (JuesefunctionDTO)juesefunctionPO.query(juesefunctionDTO);
}


/**
 * ���¶���
 * @param juesefunction0DTO
 * @throws BSWException
 */
public void update(JuesefunctionDTO juesefunctionDTO) throws BSWException{	
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.update(juesefunctionDTO);
}


/**
 * ɾ��һ������
 * @param juesefunctionDTO
 * @throws BSWException
 */
public void delete(JuesefunctionDTO juesefunctionDTO) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.delete(juesefunctionDTO);
}


/**
 * ɾ���������
 * @param juesefunctionDTOS
 * @throws BSWException
 */
public void delete(JuesefunctionDTO[] juesefunctionDTOS) throws BSWException{
	juesefunctionPO=new JuesefunctionPO();
	juesefunctionPO.delete(juesefunctionDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public JuesefunctionDTO[] loadAll(String _hql)throws BSWException{	
	juesefunctionPO=new JuesefunctionPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])juesefunctionPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽JuesefunctionDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	JuesefunctionDTO[] juesefunctionDTOS=new JuesefunctionDTO[baseDTOS.length];
	for(int i=0;i<juesefunctionDTOS.length;i++){
		juesefunctionDTOS[i]=new JuesefunctionDTO();
		juesefunctionDTOS[i]=(JuesefunctionDTO)baseDTOS[i];
	}
	return juesefunctionDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public JuesefunctionDTO[] search(PageRoll pageRoll) {
	String hql = "from JuesefunctionDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	JuesefunctionPO juesefunctionPO = new JuesefunctionPO();
	BaseDTO[] baseDTO = juesefunctionPO.search(pageRoll);
	JuesefunctionDTO[] juesefunctionDTO = null;
	juesefunctionDTO = new JuesefunctionDTO[baseDTO.length];
	for (int i = 0; i < juesefunctionDTO.length; i++) {
		juesefunctionDTO[i] = (JuesefunctionDTO) baseDTO[i];
	}
	return juesefunctionDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	juesefunctionPO=new JuesefunctionPO();
	List list = juesefunctionPO.tuplesQuery(hql);
	
	
	
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
	juesefunctionPO=new JuesefunctionPO();
	List list =juesefunctionPO.sqlQuery(sql,classArg);
	
	return list;
}



/**
 * dwr ר��
 * @param args
 */

public Map getJuesefunctions() throws BSWException{
	Map map=new HashMap();
	juesefunctionPO=new JuesefunctionPO();
	List list =juesefunctionPO.sqlQuery("select * from juesefunction" ,JuesefunctionDTO.class);
	//����list<Map>��
	Iterator it = list.iterator();
	JuesefunctionDTO juesefunctionDTO=null;
	while(it.hasNext()){
		juesefunctionDTO=(JuesefunctionDTO)it.next();
		map.put(juesefunctionDTO.getJuesename(),juesefunctionDTO.getJuesename());
	}
	
	
	return map;
}


public static void main(String[] args)throws BSWException{
	JuesefunctionBO dBO=new JuesefunctionBO();
	
	


	
	

	
	
}


}
