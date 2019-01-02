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
	 * ��Ӷ���
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO add(MygpmcDTO mygpmcDTO) throws BSWException{
		
		mygpmcPO=new MygpmcPO();		
		
		return (MygpmcDTO)mygpmcPO.add(mygpmcDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param gpmc0DTO
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO query(MygpmcDTO mygpmcDTO) throws BSWException{
		mygpmcPO=new MygpmcPO();
		return (MygpmcDTO)mygpmcPO.query(mygpmcDTO);
	}


	/**
	 * ���¶���
	 * @param gpmc0DTO
	 * @throws BSWException
	 */
	public void update(MygpmcDTO mygpmcDTO) throws BSWException{	
		mygpmcPO=new MygpmcPO();
		mygpmcPO.update(mygpmcDTO);
	}


	/**
	 * ɾ��һ������
	 * @param mygpmcDTO
	 * @throws BSWException
	 */
	public void delete(MygpmcDTO mygpmcDTO) throws BSWException{
		mygpmcPO=new MygpmcPO();
		mygpmcPO.delete(mygpmcDTO);
	}


	/**
	 * ɾ���������
	 * @param mygpmcDTOS
	 * @throws BSWException
	 */
	public void delete(MygpmcDTO[] mygpmcDTOS) throws BSWException{
		mygpmcPO=new MygpmcPO();
		mygpmcPO.delete(mygpmcDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	public MygpmcDTO[] loadAll(String _hql)throws BSWException{	
		mygpmcPO=new MygpmcPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])mygpmcPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽MygpmcDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
		MygpmcDTO[] mygpmcDTOS=new MygpmcDTO[baseDTOS.length];
		double dqj=0d;
		for(int i=0;i<mygpmcDTOS.length;i++){
			mygpmcDTOS[i]=new MygpmcDTO();
			mygpmcDTOS[i]=(MygpmcDTO)baseDTOS[i];
			
			String zqdm = mygpmcDTOS[i].getZqdm();
			dqj = sUtil.getDqjByZqdm(zqdm);//��ǰ�ۣ�
			mygpmcDTOS[i].setScj(dqj);
		}
		return mygpmcDTOS;
	}
	/**
	 * ��ҳ��ʾ�����Ϣ
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public MygpmcDTO[] search(PageRoll pageRoll) {
		String hql = "from MygpmcDTO";
		pageRoll.setWhereClause(hql);
		//����ÿҳ��ʾ��¼����
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
	 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
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
		//����list<Map>��
		Iterator it = list.iterator();
		GpmcDTO _gpmcDTO=null;
		if(it.hasNext()){
			_gpmcDTO=(GpmcDTO)it.next();
			
			results[0]=_gpmcDTO.getZqdm();
			//Double dqj = sUtil.getDqjByZqdm(results[0]);//��ǰ�ۣ�
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
		String flag2  =mygpmcDTO.getFlag2();//�û�id
		
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
		gDTO.setZqmc("����Һ");
		gDTO.setFlag1("����A��");
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
