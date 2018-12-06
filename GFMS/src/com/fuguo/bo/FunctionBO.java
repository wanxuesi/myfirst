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
	 * ��Ӷ���
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO add(FunctionDTO functionDTO) throws BSWException{
		
		functionPO=new FunctionPO();		
		
		return (FunctionDTO)functionPO.add(functionDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param function0DTO
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO query(FunctionDTO functionDTO) throws BSWException{
		functionPO=new FunctionPO();
		return (FunctionDTO)functionPO.query(functionDTO);
	}


	/**
	 * ���¶���
	 * @param function0DTO
	 * @throws BSWException
	 */
	public void update(FunctionDTO functionDTO) throws BSWException{	
		functionPO=new FunctionPO();
		functionPO.update(functionDTO);
	}


	/**
	 * ɾ��һ������
	 * @param functionDTO
	 * @throws BSWException
	 */
	public void delete(FunctionDTO functionDTO) throws BSWException{
		functionPO=new FunctionPO();
		functionPO.delete(functionDTO);
	}


	/**
	 * ɾ���������
	 * @param functionDTOS
	 * @throws BSWException
	 */
	public void delete(FunctionDTO[] functionDTOS) throws BSWException{
		functionPO=new FunctionPO();
		functionPO.delete(functionDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	public FunctionDTO[] loadAll(String _hql)throws BSWException{	
		functionPO=new FunctionPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])functionPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽FunctionDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
		FunctionDTO[] functionDTOS=new FunctionDTO[baseDTOS.length];
		for(int i=0;i<functionDTOS.length;i++){
			functionDTOS[i]=new FunctionDTO();
			functionDTOS[i]=(FunctionDTO)baseDTOS[i];
		}
		return functionDTOS;
	}
	/**
	 * ��ҳ��ʾ�����Ϣ
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public FunctionDTO[] search(PageRoll pageRoll) {
		String hql = "from FunctionDTO";
		pageRoll.setWhereClause(hql);
		//����ÿҳ��ʾ��¼����
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
	 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
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
		fDTO.setFuncname("��ͣ��ƻ����");
		fDTO.setFunctioncode("weekplanAdd");
		dBO.delete(fDTO);
		
		
////	ֱ��ȥ�������������,����û���ֶ�����ֱ��������test2 ̫ǿ��
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
