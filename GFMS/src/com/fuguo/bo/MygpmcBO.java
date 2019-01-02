package com.fuguo.bo;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.MygpmcDTO;
import com.fuguo.po.MygpmcPO;
import com.fuguo.util.StockUtil;

public class MygpmcBO {
	MygpmcPO mygpmcPO = null;

	StockUtil sUtil = new StockUtil();

	/**
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO add(MygpmcDTO mygpmcDTO) throws BSWException{
		
		mygpmcPO=new MygpmcPO();		
		
		return (MygpmcDTO)mygpmcPO.add(mygpmcDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param gpmc0DTO
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO query(MygpmcDTO mygpmcDTO) throws BSWException{
		mygpmcPO=new MygpmcPO();
		return (MygpmcDTO)mygpmcPO.query(mygpmcDTO);
	}


	/**
	 * 更新对象
	 * @param gpmc0DTO
	 * @throws BSWException
	 */
	public void update(MygpmcDTO mygpmcDTO) throws BSWException{	
		mygpmcPO=new MygpmcPO();
		mygpmcPO.update(mygpmcDTO);
	}


	/**
	 * 删除一个对象
	 * @param mygpmcDTO
	 * @throws BSWException
	 */
	public void delete(MygpmcDTO mygpmcDTO) throws BSWException{
		mygpmcPO=new MygpmcPO();
		mygpmcPO.delete(mygpmcDTO);
	}


	/**
	 * 删除多个对象
	 * @param mygpmcDTOS
	 * @throws BSWException
	 */
	public void delete(MygpmcDTO[] mygpmcDTOS) throws BSWException{
		mygpmcPO=new MygpmcPO();
		mygpmcPO.delete(mygpmcDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO[] loadAll(String _hql)throws BSWException{	
		mygpmcPO=new MygpmcPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])mygpmcPO.loadAll(_hql);//为什么不可以直接放入到MygpmcDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		MygpmcDTO[] mygpmcDTOS=new MygpmcDTO[baseDTOS.length];
		double dqj=0d;
		for(int i=0;i<mygpmcDTOS.length;i++){
			mygpmcDTOS[i]=new MygpmcDTO();
			mygpmcDTOS[i]=(MygpmcDTO)baseDTOS[i];
			
			String zqdm = mygpmcDTOS[i].getZqdm();
			dqj = sUtil.getDqjByZqdm(zqdm);//当前价；
			mygpmcDTOS[i].setScj(dqj);
		}
		return mygpmcDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public MygpmcDTO[] search(PageRoll pageRoll) {
		String hql = "from MygpmcDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
		pageRoll.setPageSize(8);
		MygpmcPO mygpmcPO = new MygpmcPO();
		BaseDTO[] baseDTO = mygpmcPO.search(pageRoll);
		MygpmcDTO[] mygpmcDTO = null;
		mygpmcDTO = new MygpmcDTO[baseDTO.length];
		for (int i = 0; i < mygpmcDTO.length; i++) {
			mygpmcDTO[i] = (MygpmcDTO) baseDTO[i];
		}
		return mygpmcDTO;
	}


	/**
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		mygpmcPO=new MygpmcPO();
		List list = mygpmcPO.tuplesQuery(hql);
		
		
		
		return list;
	}

	public String[]  getZqdmAndJysByZqmc(String zqmc) throws BSWException{
		
//		if(zqmc.length()==3){
//			zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
//			//System.out.println(zqmc);
//		}
		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		Map map=new HashMap();
		StockUtil sUtil = new StockUtil();
		mygpmcPO=new MygpmcPO();
		String results[]=new String[3];
		List list =mygpmcPO.sqlQuery("select zqdm,flag1 from gpmc where zqmc='"+zqmc+"'", GpmcDTO.class);
		//解析list<Map>；
		Iterator it = list.iterator();
		GpmcDTO _gpmcDTO=null;
		if(it.hasNext()){
			_gpmcDTO=(GpmcDTO)it.next();
			
			results[0]=_gpmcDTO.getZqdm();
			//Double dqj = sUtil.getDqjByZqdm(results[0]);//当前价；
			//String dqjStr = dqj.toString();
			results[1]=_gpmcDTO.getFlag1();
			//results[2]=dqjStr;
			
		}
		
		
		return results;
	}
	
	public List sqlQuery(String sql,Class classArg) throws BSWException {
		mygpmcPO=new MygpmcPO();
		List list =mygpmcPO.sqlQuery(sql,classArg);
		
		return list;
	}

	public boolean isHave(MygpmcDTO mygpmcDTO)throws BSWException{
		boolean result = true;
		String zqdm = mygpmcDTO.getZqdm();
		String flag2  =mygpmcDTO.getFlag2();//用户id
		
		   String sql ="select zqdm from mygpmc where zqdm='"+zqdm+"' and flag2='"+flag2+"'";
		   
		   List listLsjg = sqlQuery(sql,MygpmcDTO.class);  
		   if(listLsjg.size()<1){
			   result=false;
		   }
		
		return result;
	}
	
	
	public static void main(String[] args)throws BSWException{
		MygpmcBO dBO=new MygpmcBO();
		MygpmcDTO gDTO =new MygpmcDTO();
		gDTO.setZqdm("000858");
		gDTO.setZqmc("五粮液");
		gDTO.setFlag1("深圳A股");
		String zqmc  = gDTO.getZqmc();
		

		Integer zqdmInteger = Integer.parseInt(gDTO.getZqdm());		
		String zqdm=zqdmInteger.toString();
		gDTO.setZqdm(zqdm);
		
//		if(zqmc.length()==3){
//		   zqmc  =zqmc.substring(0,1)+" "+zqmc.substring(1,2)+" "+zqmc.substring(2,3);
//		   gDTO.setZqmc(zqmc);
//		}
		zqmc = zqmc.replace(" ","");
		zqmc = zqmc.replace(" ","");
		gDTO.setZqmc(zqmc);
		dBO.add(gDTO);
		
		


		
	}


}
