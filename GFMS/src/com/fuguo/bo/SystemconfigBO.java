package com.fuguo.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.SystemconfigDTO;
import com.fuguo.po.SystemconfigPO;
import com.fuguo.util.JsoupParser;
import com.fuguo.util.StockUtil;

public class SystemconfigBO {
	SystemconfigPO systemconfigPO = null;


	/**
	 * ��Ӷ���
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO add(SystemconfigDTO systemconfigDTO) throws BSWException{
		
		systemconfigPO=new SystemconfigPO();		
		
		return (SystemconfigDTO)systemconfigPO.add(systemconfigDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param systemconfig0DTO
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO query(SystemconfigDTO systemconfigDTO) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		return (SystemconfigDTO)systemconfigPO.query(systemconfigDTO);
	}


	/**
	 * ���¶���
	 * @param systemconfig0DTO
	 * @throws BSWException
	 */
	public void update(SystemconfigDTO systemconfigDTO) throws BSWException{	
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.update(systemconfigDTO);
	}


	/**
	 * ɾ��һ������
	 * @param systemconfigDTO
	 * @throws BSWException
	 */
	public void delete(SystemconfigDTO systemconfigDTO) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.delete(systemconfigDTO);
	}


	/**
	 * ɾ���������
	 * @param systemconfigDTOS
	 * @throws BSWException
	 */
	public void delete(SystemconfigDTO[] systemconfigDTOS) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.delete(systemconfigDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO[] loadAll(String _hql)throws BSWException{	
		systemconfigPO=new SystemconfigPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])systemconfigPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽SystemconfigDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
		SystemconfigDTO[] systemconfigDTOS=new SystemconfigDTO[baseDTOS.length];
		for(int i=0;i<systemconfigDTOS.length;i++){
			systemconfigDTOS[i]=new SystemconfigDTO();
			systemconfigDTOS[i]=(SystemconfigDTO)baseDTOS[i];
		}
		return systemconfigDTOS;
	}
	/**
	 * ��ҳ��ʾ�����Ϣ
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public SystemconfigDTO[] search(PageRoll pageRoll) {
		String hql = "from SystemconfigDTO";
		pageRoll.setWhereClause(hql);
		//����ÿҳ��ʾ��¼����
		pageRoll.setPageSize(8);
		SystemconfigPO systemconfigPO = new SystemconfigPO();
		BaseDTO[] baseDTO = systemconfigPO.search(pageRoll);
		SystemconfigDTO[] systemconfigDTO = null;
		systemconfigDTO = new SystemconfigDTO[baseDTO.length];
		for (int i = 0; i < systemconfigDTO.length; i++) {
			systemconfigDTO[i] = (SystemconfigDTO) baseDTO[i];
		}
		return systemconfigDTO;
	}

	public List sqlQuery(String sql) throws BSWException {
		systemconfigPO=new SystemconfigPO();
		List list =systemconfigPO.sqlQuery(sql);
		
		return list;
	}
	/**
	 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		systemconfigPO=new SystemconfigPO();
		List list = systemconfigPO.tuplesQuery(hql);
		
		
		
		return list;
	}

	
	
	
	
	public static void main(String[] args)throws BSWException{
		SystemconfigBO dBO=new SystemconfigBO();
		SystemconfigDTO gDTO =new SystemconfigDTO();
		
		dBO.add(gDTO);
		
		


		
	}


}
