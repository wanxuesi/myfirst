package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.JueseDTO;
import com.fuguo.po.DanweiPO;
import com.fuguo.po.JuesePO;

public class JueseBO implements BaseBO{
JuesePO juesePO = null;


/**
 * ��Ӷ���
 * @param juese0DTO
 * @return
 * @throws BSWException
 */
public JueseDTO add(JueseDTO jueseDTO) throws BSWException{
	
	juesePO=new JuesePO();		
	
	return (JueseDTO)juesePO.add(jueseDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param juese0DTO
 * @return
 * @throws BSWException
 */
public JueseDTO query(JueseDTO jueseDTO) throws BSWException{
	juesePO=new JuesePO();
	return (JueseDTO)juesePO.query(jueseDTO);
}


/**
 * ���¶���
 * @param juese0DTO
 * @throws BSWException
 */
public void update(JueseDTO jueseDTO) throws BSWException{	
	juesePO=new JuesePO();
	juesePO.update(jueseDTO);
}


/**
 * ɾ��һ������
 * @param jueseDTO
 * @throws BSWException
 */
public void delete(JueseDTO jueseDTO) throws BSWException{
	juesePO=new JuesePO();
	juesePO.delete(jueseDTO);
}


/**
 * ɾ���������
 * @param jueseDTOS
 * @throws BSWException
 */
public void delete(JueseDTO[] jueseDTOS) throws BSWException{
	juesePO=new JuesePO();
	juesePO.delete(jueseDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public JueseDTO[] loadAll(String _hql)throws BSWException{	
	juesePO=new JuesePO();	
	BaseDTO[] baseDTOS=(BaseDTO[])juesePO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽JueseDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	JueseDTO[] jueseDTOS=new JueseDTO[baseDTOS.length];
	for(int i=0;i<jueseDTOS.length;i++){
		jueseDTOS[i]=new JueseDTO();
		jueseDTOS[i]=(JueseDTO)baseDTOS[i];
	}
	return jueseDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public JueseDTO[] search(PageRoll pageRoll) {
	String hql = "from JueseDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
	pageRoll.setPageSize(8);
	JuesePO juesePO = new JuesePO();
	BaseDTO[] baseDTO = juesePO.search(pageRoll);
	JueseDTO[] jueseDTO = null;
	jueseDTO = new JueseDTO[baseDTO.length];
	for (int i = 0; i < jueseDTO.length; i++) {
		jueseDTO[i] = (JueseDTO) baseDTO[i];
	}
	return jueseDTO;
}


/**
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	juesePO=new JuesePO();
	List list = juesePO.tuplesQuery(hql);
	
	
	
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
	juesePO=new JuesePO();
	List list =juesePO.sqlQuery(sql,classArg);
	
	return list;
}




/**
 * dwr ר��
 * @param args
 */

public Map getJueses() throws BSWException{
	Map map=new HashMap();
	juesePO=new JuesePO();
	List list =juesePO.sqlQuery("select name from juese",JueseDTO.class);
	//����list<Map>��
	Iterator it = list.iterator();
	JueseDTO jueseDTO=null;
	while(it.hasNext()){
		jueseDTO=(JueseDTO)it.next();
		map.put(jueseDTO.getName(),jueseDTO.getName());
	}
	
	
	return map;
}


public static void main(String[] args)throws BSWException{
	JueseBO dBO=new JueseBO();
	
	


	
	

	
	
}


}
