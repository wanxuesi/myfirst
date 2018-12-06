package com.fuguo.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.GpmcDTO;
import com.fuguo.po.GpmcPO;
import com.fuguo.util.JsoupParser;
import com.fuguo.util.StockUtil;

public class GpmcBO {
	GpmcPO gpmcPO = null;
	StockUtil stockUtil = new StockUtil();

	/**
	 * ��Ӷ���
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO add(GpmcDTO gpmcDTO) throws BSWException{
		
		gpmcPO=new GpmcPO();		
		
		return (GpmcDTO)gpmcPO.add(gpmcDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param gpmc0DTO
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO query(GpmcDTO gpmcDTO) throws BSWException{
		gpmcPO=new GpmcPO();
		return (GpmcDTO)gpmcPO.query(gpmcDTO);
	}


	/**
	 * ���¶���
	 * @param gpmc0DTO
	 * @throws BSWException
	 */
	public void update(GpmcDTO gpmcDTO) throws BSWException{	
		gpmcPO=new GpmcPO();
		gpmcPO.update(gpmcDTO);
	}


	/**
	 * ɾ��һ������
	 * @param gpmcDTO
	 * @throws BSWException
	 */
	public void delete(GpmcDTO gpmcDTO) throws BSWException{
		gpmcPO=new GpmcPO();
		gpmcPO.delete(gpmcDTO);
	}


	/**
	 * ɾ���������
	 * @param gpmcDTOS
	 * @throws BSWException
	 */
	public void delete(GpmcDTO[] gpmcDTOS) throws BSWException{
		gpmcPO=new GpmcPO();
		gpmcPO.delete(gpmcDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO[] loadAll(String _hql)throws BSWException{	
		gpmcPO=new GpmcPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])gpmcPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽GpmcDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
		GpmcDTO[] gpmcDTOS=new GpmcDTO[baseDTOS.length];
		for(int i=0;i<gpmcDTOS.length;i++){
			gpmcDTOS[i]=new GpmcDTO();
			gpmcDTOS[i]=(GpmcDTO)baseDTOS[i];
		}
		return gpmcDTOS;
	}
	/**
	 * ��ҳ��ʾ�����Ϣ
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public GpmcDTO[] search(PageRoll pageRoll) {
		String hql = "from GpmcDTO";
		pageRoll.setWhereClause(hql);
		//����ÿҳ��ʾ��¼����
		pageRoll.setPageSize(8);
		GpmcPO gpmcPO = new GpmcPO();
		BaseDTO[] baseDTO = gpmcPO.search(pageRoll);
		GpmcDTO[] gpmcDTO = null;
		gpmcDTO = new GpmcDTO[baseDTO.length];
		for (int i = 0; i < gpmcDTO.length; i++) {
			gpmcDTO[i] = (GpmcDTO) baseDTO[i];
		}
		return gpmcDTO;
	}


	/**
	 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		gpmcPO=new GpmcPO();
		List list = gpmcPO.tuplesQuery(hql);
		
		
		
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
		gpmcPO=new GpmcPO();
		String results[]=new String[3];
		List list =gpmcPO.sqlQuery("select zqdm,flag1 from gpmc where zqmc='"+zqmc+"'");
		//����list<Map>��
		Iterator it = list.iterator();
		Map _map=null;
		if(it.hasNext()){
			_map=(Map)it.next();
			
			results[0]=(String)_map.get("ZQDM");
			Double dqj = sUtil.getDqjByZqdm(results[0]);//��ǰ�ۣ�
			String dqjStr = dqj.toString();
			results[1]=(String)_map.get("FLAG1");
			results[2]=dqjStr;
			
		}
		
		
		return results;
	}
	public List<GpmcDTO> getListGpmcsFromSina()throws BSWException{

		
		 List<GpmcDTO> list = new ArrayList<GpmcDTO>();
		
			 
		 String URL="http://quote.eastmoney.com/stocklist.html";
		 
	     JsoupParser jParser = new JsoupParser();
			 
	     list = jParser.getListGpmcsByUrl(URL);
			 
		
		
		return list;
	}
	public void updateGpmcsFromSina()  throws BSWException,IOException{
		// TODO �Զ����ɷ������
		
		List list =getListGpmcsFromSina();
		System.out.println("��Ʊ������ȫ�����浽list��!");
		
		Iterator it =list.iterator();
		
		GpmcDTO tDTO=null;
		GpmcDTO oDTO=null;
		String zqmc="";
		while(it.hasNext()){
			tDTO  = (GpmcDTO)it.next();
			zqmc  = tDTO.getZqmc();//sina�� �� ��֤ȯ���ƣ�
			oDTO = query(tDTO);//��ʵ��ͨ��id��zqdm�����ҵģ�
			if(oDTO==null){
				//�����ڵ�����£�����ӣ�
				add(tDTO);
			
			}else{
			
			//˵����zqdm���ڣ���������zqmc�Ƿ�һ�������һ����������������һ�����͸��£�
				if(oDTO.getZqmc().equals(zqmc)){
					//update(tDTO);//��ʱ
				}else{
					tDTO.setZgbnum(oDTO.getZgbnum());//����ʱ���������ܹɱ���
					update(tDTO);//ֻ�Ḳ�ǹ�Ʊ
				}
				
			
			}
			
		}
		
		
		
	}
	/**
	 * 
	 * @param zszLong  ����ֵ
	 * @param ltszLong��ͨ��ֵ
	 * @param queryDateDate
	 * @return
	 */
	
	public List<GpmcDTO> tsxgQuery(long zszLong, long ltszLong, Date queryDateDate) throws BSWException{
		
		//���ҳ����й�Ʊ�Ĺ�Ʊ���룻

		//GpmcDTO[] gpmcDTOs = loadAll("from GpmcDTO gpmcDTO where gpmcDTO.flag2='1' and zqdm='300538'");
		GpmcDTO[] gpmcDTOs = loadAll("from GpmcDTO gpmcDTO where gpmcDTO.flag2='1' and zqdm!='300372' order by gpmcDTO.flag2 desc,flag1,zqdm");
		//����ÿֻ��Ʊ�ķ����жϣ�����Ҫ��ģ����뵽list��
		
		//һ�λ�ȡ���е�ǰ�ۣ�
		//Map allDqjMap=stockUtil.getDqjsByZqdms(gpmcDTOs);
		String zqdm="";
		String gubenjiegouFullZqdm="";
		String xianshoujiejinFullZqdm="";
		
		 List<GpmcDTO> list = new ArrayList<GpmcDTO>();
			
		 //�ɱ��ṹ���ܹɱ�����ͨ�ɣ�
		 String gubenjiegouURLLeft="http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructure/stockid/";
		 String gubenjiegouURLRight=".phtml";
		 String gubenjiegouFullURL="";
		 
		 //���۽��(�������)  
		 //http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sh300538
		 String xianshoujiejinURLLeft="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=";
		 String xianshoujiejinFullURL="";
	     JsoupParser jParser = new JsoupParser();
	     GpmcDTO _gpmcDTO=null;	 
	     //list = jParser.getListGpmcsByUrl(fullURL);
		for(int i=0;i<gpmcDTOs.length;i++){
			zqdm  = gpmcDTOs[i].getZqdm();
			gubenjiegouFullZqdm = stockUtil.getFullZqdmByZqdm3(zqdm);//��ȡ������sina���õ�֤ȯ����eg��002582;600000;
			//ƴװ�ɿ���url����fullURL��
			gubenjiegouFullURL=gubenjiegouURLLeft+gubenjiegouFullZqdm+gubenjiegouURLRight;
			
			//System.out.println(gubenjiegouFullURL);//test
			
			xianshoujiejinFullZqdm = stockUtil.getFullZqdmByZqdm(zqdm);
			xianshoujiejinFullURL = xianshoujiejinURLLeft+xianshoujiejinFullZqdm;
			
			//System.out.println(xianshoujiejinFullURL);//test
			//��ȡ��ǰ�۵�
			//���践��list,��Ϊ���ص��ǵ�������һ��list���Խ�����⣻
			_gpmcDTO = jParser.getTsxgByUrl(gubenjiegouFullURL,xianshoujiejinFullURL,gpmcDTOs[i],zszLong,ltszLong,queryDateDate);
			if(_gpmcDTO!=null){
				
				
				
			list.add(_gpmcDTO);	
			}
		}
		
		
		// TODO �Զ����ɷ������
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param String  zqdm
	 * @param 
	 * @param 
	 * @return 
	 */
	
	public GpmcDTO getStockMessage(String  zqdm) throws BSWException{
		
		String gubenjiegouFullZqdm="";
		String xianshoujiejinFullZqdm="";
		
		
			
		 //�ɱ��ṹ���ܹɱ�����ͨ�ɣ�
		 String gubenjiegouURLLeft="http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructure/stockid/";
		 String gubenjiegouURLRight=".phtml";
		 String gubenjiegouFullURL="";
		 
		 //���۽��(�������)  
		 //http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sh300538
		 String xianshoujiejinURLLeft="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=";
		 String xianshoujiejinFullURL="";
	     JsoupParser jParser = new JsoupParser();
	     GpmcDTO _gpmcDTO=new GpmcDTO();	
	     _gpmcDTO.setZqdm(zqdm);
	     _gpmcDTO = query(_gpmcDTO);
	     
	     //list = jParser.getListGpmcsByUrl(fullURL);

			
			gubenjiegouFullZqdm = stockUtil.getFullZqdmByZqdm3(zqdm);//��ȡ������sina���õ�֤ȯ����eg��002582;600000;
			//ƴװ�ɿ���url����fullURL��
			gubenjiegouFullURL=gubenjiegouURLLeft+gubenjiegouFullZqdm+gubenjiegouURLRight;
			
			System.out.println(gubenjiegouFullURL);//test
			
			xianshoujiejinFullZqdm = stockUtil.getFullZqdmByZqdm(zqdm);
			xianshoujiejinFullURL = xianshoujiejinURLLeft+xianshoujiejinFullZqdm;
			
			System.out.println(xianshoujiejinFullURL);//test
			//��ȡ��ǰ�۵�
			//���践��list,��Ϊ���ص��ǵ�������һ��list���Խ�����⣻
			
			_gpmcDTO = jParser.getTsxgByUrl(gubenjiegouFullURL,xianshoujiejinFullURL,_gpmcDTO);
			
		
		return _gpmcDTO;
	}
	
	
	
	
	public static void main(String[] args)throws BSWException{
		GpmcBO dBO=new GpmcBO();
		GpmcDTO gpmcDTO =new GpmcDTO();
		//gDTO.setZqdm("858");

		gpmcDTO = dBO.getStockMessage("603131");

		gpmcDTO = dBO.getStockMessage("603776");
		
	}

	


}
