package bsw.base;


import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;
import bsw.tools.hibernate.HibernateUtil;

/**
 * @����������PO�Ļ���
 
 */

public abstract class BasePO {

	/**
	 * ��ȡHibernateUtil�������ʵ��
	 * 
	 * @return
	 */
	protected HibernateUtil hibernateUtil = HibernateUtil.getInstance();

	/**
	 * ��������Ӷ���
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO add(BaseDTO baseDTO)throws BSWException{			
			baseDTO = (BaseDTO)hibernateUtil.add(baseDTO);		
		return baseDTO;
	}

	/**
	 * ��������Ӷ���
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void update(BaseDTO baseDTO)throws BSWException{			
			hibernateUtil.update(baseDTO);		
	}

	/**
	 * ������ɾ����������
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void delete(BaseDTO baseDTO)throws BSWException{			
			hibernateUtil.delete(baseDTO);		
	}
	/**
	 * ������ɾ���������
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public void delete(BaseDTO[] baseDTO)throws BSWException{			
		hibernateUtil.delete(baseDTO);		
	}
	/**
	 * ��������ѯ��������
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO query(BaseDTO baseDTO)throws BSWException{		
			baseDTO = (BaseDTO)hibernateUtil.query(baseDTO);
		return baseDTO;
	}

	/**
	 * ��������ѯ�������
	 * @param baseDTO
	 * @return
	 * @throws BSWException
	 */
	public BaseDTO[] loadAll(String hql)throws BSWException{	
		BaseDTO[] baseDTOs = (BaseDTO[])hibernateUtil.loadAll(hql);
		return baseDTOs;
	}
	/**
	 * ��ҳ���ҷ���
	 * @param pageRoll
	 * @return
	 * @throws HibernateException
	 */
	public BaseDTO[] search(PageRoll pageRoll) throws HibernateException {	
		BaseDTO[] baseDTOs = (BaseDTO[])hibernateUtil.search(pageRoll);
		return baseDTOs;
	}
	
	/**
	 * 
	 *����:���������ѯ��Ԫ���ѯ��
	 * @param hql
	 * @return list �����ŵ�Ϊ�����Ķ�������list���������2�����󣬾���һ����¼����2������
	 * 
	 * @throws BSWException
	 */
	
	public List tuplesQuery(String hql) throws BSWException {
		List list = hibernateUtil.tuplesQuery(hql);
		return list;
	}
	
	
	
	/**
	 * 
	 *����:���������ѯ������sql��
	 * @param sql
	 * @return  List ����������map��ע���Сд����Ϊû����ӳ�����á�
	 * 
	 * @throws BSWException
	 */
	public List sqlQuery(String sql) throws BSWException {
		List list =hibernateUtil.sqlQuery(sql);
		return list;
	}
	/**
	 * 
	 *����:���������ѯ������sql��db2�����⣻
	 * @param sql,��Ҫ��װ��bean��
	 * @return BaseDTO[]
	 * 
	 * @throws BSWException
	 */
	public List sqlQuery(String sql,Class classArg) throws BSWException {
		List list = hibernateUtil.sqlQuery(sql,classArg);
		return list;
	}
	
	public void sqlUpdateOrDel(String sql) throws BSWException {
		hibernateUtil.sqlUpdateOrDel(sql);
		
	}
}
