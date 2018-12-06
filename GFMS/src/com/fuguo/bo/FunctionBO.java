package com.fuguo.bo;

import java.util.List;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.FunctionDTO;
import com.fuguo.po.FunctionPO;

public class FunctionBO {
	FunctionPO functionPO = null;


	/**
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO add(FunctionDTO functionDTO) throws BSWException{
		
		functionPO=new FunctionPO();		
		
		return (FunctionDTO)functionPO.add(functionDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param function0DTO
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO query(FunctionDTO functionDTO) throws BSWException{
		functionPO=new FunctionPO();
		return (FunctionDTO)functionPO.query(functionDTO);
	}


	/**
	 * 更新对象
	 * @param function0DTO
	 * @throws BSWException
	 */
	public void update(FunctionDTO functionDTO) throws BSWException{	
		functionPO=new FunctionPO();
		functionPO.update(functionDTO);
	}


	/**
	 * 删除一个对象
	 * @param functionDTO
	 * @throws BSWException
	 */
	public void delete(FunctionDTO functionDTO) throws BSWException{
		functionPO=new FunctionPO();
		functionPO.delete(functionDTO);
	}


	/**
	 * 删除多个对象
	 * @param functionDTOS
	 * @throws BSWException
	 */
	public void delete(FunctionDTO[] functionDTOS) throws BSWException{
		functionPO=new FunctionPO();
		functionPO.delete(functionDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO[] loadAll(String _hql)throws BSWException{	
		functionPO=new FunctionPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])functionPO.loadAll(_hql);//为什么不可以直接放入到FunctionDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		FunctionDTO[] functionDTOS=new FunctionDTO[baseDTOS.length];
		for(int i=0;i<functionDTOS.length;i++){
			functionDTOS[i]=new FunctionDTO();
			functionDTOS[i]=(FunctionDTO)baseDTOS[i];
		}
		return functionDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public FunctionDTO[] search(PageRoll pageRoll) {
		String hql = "from FunctionDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
		pageRoll.setPageSize(8);
		FunctionPO functionPO = new FunctionPO();
		BaseDTO[] baseDTO = functionPO.search(pageRoll);
		FunctionDTO[] functionDTO = null;
		functionDTO = new FunctionDTO[baseDTO.length];
		for (int i = 0; i < functionDTO.length; i++) {
			functionDTO[i] = (FunctionDTO) baseDTO[i];
		}
		return functionDTO;
	}


	/**
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		functionPO=new FunctionPO();
		List list = functionPO.tuplesQuery(hql);
		
		
		
		return list;
	}


	public static void main(String[] args)throws BSWException{
		FunctionBO dBO=new FunctionBO();
		FunctionDTO fDTO =new FunctionDTO();
		fDTO.setFuncname("周停电计划添加");
		fDTO.setFunctioncode("weekplanAdd");
		dBO.delete(fDTO);
		
		
////	直接去关联对象的属性,不过没有字段名，直接是数组test2 太强了
//		
//		List list = dBO.tuplesQuery("select function.xm,dwbm.name from FunctionDTO function,DwbmDTO dwbm where function.dwbmId=dwbm.id");
//		Iterator it = list.iterator();
//		while(it.hasNext()){
//			Object[] tuple=(Object[])it.next();
//			String functionname =(String)tuple[0];
//			String dwbmname=(String)tuple[1];
//			System.out.print(functionname);
//			System.out.print(dwbmname);
//			System.out.println();
//		}	
		

		
	}


}
