package com.fuguo.bo;

import java.util.List;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.po.LsjgdatePO;
import com.fuguo.util.DateUtil;

public class LsjgdateBO {
	LsjgdatePO lsjgPO = null;


	/**
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public LsjgdateDTO add(LsjgdateDTO lsjgDTO) throws BSWException{
		
		lsjgPO=new LsjgdatePO();		
		
		return (LsjgdateDTO)lsjgPO.add(lsjgDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param lsjg0DTO
	 * @return
	 * @throws BSWException
	 */
	public LsjgdateDTO query(LsjgdateDTO lsjgDTO) throws BSWException{
		lsjgPO=new LsjgdatePO();
		return (LsjgdateDTO)lsjgPO.query(lsjgDTO);
	}


	/**
	 * 更新对象
	 * @param lsjg0DTO
	 * @throws BSWException
	 */
	public void update(LsjgdateDTO lsjgDTO) throws BSWException{	
		lsjgPO=new LsjgdatePO();
		lsjgPO.update(lsjgDTO);
	}


	/**
	 * 删除一个对象
	 * @param lsjgDTO
	 * @throws BSWException
	 */
	public void delete(LsjgdateDTO lsjgDTO) throws BSWException{
		lsjgPO=new LsjgdatePO();
		lsjgPO.delete(lsjgDTO);
	}


	/**
	 * 删除多个对象
	 * @param lsjgDTOS
	 * @throws BSWException
	 */
	public void delete(LsjgdateDTO[] lsjgDTOS) throws BSWException{
		lsjgPO=new LsjgdatePO();
		lsjgPO.delete(lsjgDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public LsjgdateDTO[] loadAll(String _hql)throws BSWException{	
		lsjgPO=new LsjgdatePO();	
		BaseDTO[] baseDTOS=(BaseDTO[])lsjgPO.loadAll(_hql);//为什么不可以直接放入到LsjgdateDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		LsjgdateDTO[] lsjgDTOS=new LsjgdateDTO[baseDTOS.length];
		for(int i=0;i<lsjgDTOS.length;i++){
			lsjgDTOS[i]=new LsjgdateDTO();
			lsjgDTOS[i]=(LsjgdateDTO)baseDTOS[i];
		}
		return lsjgDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public LsjgdateDTO[] search(PageRoll pageRoll) {
		String hql = "from LsjgdateDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
		pageRoll.setPageSize(8);
		LsjgdatePO lsjgPO = new LsjgdatePO();
		BaseDTO[] baseDTO = lsjgPO.search(pageRoll);
		LsjgdateDTO[] lsjgDTO = null;
		lsjgDTO = new LsjgdateDTO[baseDTO.length];
		for (int i = 0; i < lsjgDTO.length; i++) {
			lsjgDTO[i] = (LsjgdateDTO) baseDTO[i];
		}
		return lsjgDTO;
	}


	/**
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		lsjgPO=new LsjgdatePO();
		List list = lsjgPO.tuplesQuery(hql);
		
		
		
		return list;
	}

//	public String[]  getZqdmAndJysByZqmc(String zqmc) throws BSWException{
//		
////		if(zqmc.length()==3){
////			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
////			//System.out.println(zqmc);
////		}
//		zqmc = zqmc.replace(" ","");
//		zqmc = zqmc.replace(" ","");
//		Map map=new HashMap();
//		StockUtil sUtil = new StockUtil();
//		lsjgPO=new LsjgdatePO();
//		String results[]=new String[3];
//		List list =lsjgPO.sqlQuery("select zqdm,flag1 from lsjg where zqmc='"+zqmc+"'");
//		//解析list<Map>；
//		Iterator it = list.iterator();
//		Map _map=null;
//		if(it.hasNext()){
//			_map=(Map)it.next();
//			
//			results[0]=(String)_map.get("ZQDM");
//			Double dqj = sUtil.getDqjByZqdm(results[0]);//当前价；
//			String dqjStr = dqj.toString();
//			results[1]=(String)_map.get("FLAG1");
//			results[2]=dqjStr;
//			
//		}
//		
//		
//		return results;
//	}
	
	
	public static void main(String[] args)throws BSWException{
		LsjgdateBO dBO=new LsjgdateBO();
		LsjgdateDTO gDTO =new LsjgdateDTO();
		gDTO.setId("2582");
		gDTO.setZqdm("2582");
		DateUtil dUtil  =new DateUtil();
		
//		初始化Lsjgdate日期，获取该日期九天前的日期；
		gDTO.setDate(dUtil.getBeforeNDay(new java.util.Date(),9));
//		添加该记录
		dBO.add(gDTO);
		
		

		
		


		
	}


}
