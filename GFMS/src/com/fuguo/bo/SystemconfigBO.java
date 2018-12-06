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
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO add(SystemconfigDTO systemconfigDTO) throws BSWException{
		
		systemconfigPO=new SystemconfigPO();		
		
		return (SystemconfigDTO)systemconfigPO.add(systemconfigDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param systemconfig0DTO
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO query(SystemconfigDTO systemconfigDTO) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		return (SystemconfigDTO)systemconfigPO.query(systemconfigDTO);
	}


	/**
	 * 更新对象
	 * @param systemconfig0DTO
	 * @throws BSWException
	 */
	public void update(SystemconfigDTO systemconfigDTO) throws BSWException{	
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.update(systemconfigDTO);
	}


	/**
	 * 删除一个对象
	 * @param systemconfigDTO
	 * @throws BSWException
	 */
	public void delete(SystemconfigDTO systemconfigDTO) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.delete(systemconfigDTO);
	}


	/**
	 * 删除多个对象
	 * @param systemconfigDTOS
	 * @throws BSWException
	 */
	public void delete(SystemconfigDTO[] systemconfigDTOS) throws BSWException{
		systemconfigPO=new SystemconfigPO();
		systemconfigPO.delete(systemconfigDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public SystemconfigDTO[] loadAll(String _hql)throws BSWException{	
		systemconfigPO=new SystemconfigPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])systemconfigPO.loadAll(_hql);//为什么不可以直接放入到SystemconfigDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		SystemconfigDTO[] systemconfigDTOS=new SystemconfigDTO[baseDTOS.length];
		for(int i=0;i<systemconfigDTOS.length;i++){
			systemconfigDTOS[i]=new SystemconfigDTO();
			systemconfigDTOS[i]=(SystemconfigDTO)baseDTOS[i];
		}
		return systemconfigDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public SystemconfigDTO[] search(PageRoll pageRoll) {
		String hql = "from SystemconfigDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
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
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
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
