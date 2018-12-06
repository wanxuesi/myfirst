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
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO add(GpmcDTO gpmcDTO) throws BSWException{
		
		gpmcPO=new GpmcPO();		
		
		return (GpmcDTO)gpmcPO.add(gpmcDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param gpmc0DTO
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO query(GpmcDTO gpmcDTO) throws BSWException{
		gpmcPO=new GpmcPO();
		return (GpmcDTO)gpmcPO.query(gpmcDTO);
	}


	/**
	 * 更新对象
	 * @param gpmc0DTO
	 * @throws BSWException
	 */
	public void update(GpmcDTO gpmcDTO) throws BSWException{	
		gpmcPO=new GpmcPO();
		gpmcPO.update(gpmcDTO);
	}


	/**
	 * 删除一个对象
	 * @param gpmcDTO
	 * @throws BSWException
	 */
	public void delete(GpmcDTO gpmcDTO) throws BSWException{
		gpmcPO=new GpmcPO();
		gpmcPO.delete(gpmcDTO);
	}


	/**
	 * 删除多个对象
	 * @param gpmcDTOS
	 * @throws BSWException
	 */
	public void delete(GpmcDTO[] gpmcDTOS) throws BSWException{
		gpmcPO=new GpmcPO();
		gpmcPO.delete(gpmcDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public GpmcDTO[] loadAll(String _hql)throws BSWException{	
		gpmcPO=new GpmcPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])gpmcPO.loadAll(_hql);//为什么不可以直接放入到GpmcDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		GpmcDTO[] gpmcDTOS=new GpmcDTO[baseDTOS.length];
		for(int i=0;i<gpmcDTOS.length;i++){
			gpmcDTOS[i]=new GpmcDTO();
			gpmcDTOS[i]=(GpmcDTO)baseDTOS[i];
		}
		return gpmcDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public GpmcDTO[] search(PageRoll pageRoll) {
		String hql = "from GpmcDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
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
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
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
		//解析list<Map>；
		Iterator it = list.iterator();
		Map _map=null;
		if(it.hasNext()){
			_map=(Map)it.next();
			
			results[0]=(String)_map.get("ZQDM");
			Double dqj = sUtil.getDqjByZqdm(results[0]);//当前价；
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
		// TODO 自动生成方法存根
		
		List list =getListGpmcsFromSina();
		System.out.println("股票名称已全部保存到list中!");
		
		Iterator it =list.iterator();
		
		GpmcDTO tDTO=null;
		GpmcDTO oDTO=null;
		String zqmc="";
		while(it.hasNext()){
			tDTO  = (GpmcDTO)it.next();
			zqmc  = tDTO.getZqmc();//sina里 新 的证券名称；
			oDTO = query(tDTO);//其实是通过id（zqdm）查找的；
			if(oDTO==null){
				//不存在的情况下，就添加；
				add(tDTO);
			
			}else{
			
			//说明该zqdm存在；看看他的zqmc是否一样，如果一样，不做操作，不一样，就更新；
				if(oDTO.getZqmc().equals(zqmc)){
					//update(tDTO);//临时
				}else{
					tDTO.setZgbnum(oDTO.getZgbnum());//更新时，不覆盖总股本；
					update(tDTO);//只会覆盖股票
				}
				
			
			}
			
		}
		
		
		
	}
	/**
	 * 
	 * @param zszLong  总市值
	 * @param ltszLong流通市值
	 * @param queryDateDate
	 * @return
	 */
	
	public List<GpmcDTO> tsxgQuery(long zszLong, long ltszLong, Date queryDateDate) throws BSWException{
		
		//先找出所有股票的股票代码；

		//GpmcDTO[] gpmcDTOs = loadAll("from GpmcDTO gpmcDTO where gpmcDTO.flag2='1' and zqdm='300538'");
		GpmcDTO[] gpmcDTOs = loadAll("from GpmcDTO gpmcDTO where gpmcDTO.flag2='1' and zqdm!='300372' order by gpmcDTO.flag2 desc,flag1,zqdm");
		//进行每只股票的分析判断，符合要求的，放入到list中
		
		//一次获取所有当前价；
		//Map allDqjMap=stockUtil.getDqjsByZqdms(gpmcDTOs);
		String zqdm="";
		String gubenjiegouFullZqdm="";
		String xianshoujiejinFullZqdm="";
		
		 List<GpmcDTO> list = new ArrayList<GpmcDTO>();
			
		 //股本结构（总股本，流通股）
		 String gubenjiegouURLLeft="http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructure/stockid/";
		 String gubenjiegouURLRight=".phtml";
		 String gubenjiegouFullURL="";
		 
		 //限售解禁(解禁日期)  
		 //http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sh300538
		 String xianshoujiejinURLLeft="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=";
		 String xianshoujiejinFullURL="";
	     JsoupParser jParser = new JsoupParser();
	     GpmcDTO _gpmcDTO=null;	 
	     //list = jParser.getListGpmcsByUrl(fullURL);
		for(int i=0;i<gpmcDTOs.length;i++){
			zqdm  = gpmcDTOs[i].getZqdm();
			gubenjiegouFullZqdm = stockUtil.getFullZqdmByZqdm3(zqdm);//获取完整的sina可用的证券代码eg：002582;600000;
			//拼装成可用url参数fullURL；
			gubenjiegouFullURL=gubenjiegouURLLeft+gubenjiegouFullZqdm+gubenjiegouURLRight;
			
			//System.out.println(gubenjiegouFullURL);//test
			
			xianshoujiejinFullZqdm = stockUtil.getFullZqdmByZqdm(zqdm);
			xianshoujiejinFullURL = xianshoujiejinURLLeft+xianshoujiejinFullZqdm;
			
			//System.out.println(xianshoujiejinFullURL);//test
			//获取当前价的
			//无需返回list,因为返回的是单个对象，一个list可以解决问题；
			_gpmcDTO = jParser.getTsxgByUrl(gubenjiegouFullURL,xianshoujiejinFullURL,gpmcDTOs[i],zszLong,ltszLong,queryDateDate);
			if(_gpmcDTO!=null){
				
				
				
			list.add(_gpmcDTO);	
			}
		}
		
		
		// TODO 自动生成方法存根
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
		
		
			
		 //股本结构（总股本，流通股）
		 String gubenjiegouURLLeft="http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_StockStructure/stockid/";
		 String gubenjiegouURLRight=".phtml";
		 String gubenjiegouFullURL="";
		 
		 //限售解禁(解禁日期)  
		 //http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=sh300538
		 String xianshoujiejinURLLeft="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/xsjj/index.phtml?symbol=";
		 String xianshoujiejinFullURL="";
	     JsoupParser jParser = new JsoupParser();
	     GpmcDTO _gpmcDTO=new GpmcDTO();	
	     _gpmcDTO.setZqdm(zqdm);
	     _gpmcDTO = query(_gpmcDTO);
	     
	     //list = jParser.getListGpmcsByUrl(fullURL);

			
			gubenjiegouFullZqdm = stockUtil.getFullZqdmByZqdm3(zqdm);//获取完整的sina可用的证券代码eg：002582;600000;
			//拼装成可用url参数fullURL；
			gubenjiegouFullURL=gubenjiegouURLLeft+gubenjiegouFullZqdm+gubenjiegouURLRight;
			
			System.out.println(gubenjiegouFullURL);//test
			
			xianshoujiejinFullZqdm = stockUtil.getFullZqdmByZqdm(zqdm);
			xianshoujiejinFullURL = xianshoujiejinURLLeft+xianshoujiejinFullZqdm;
			
			System.out.println(xianshoujiejinFullURL);//test
			//获取当前价的
			//无需返回list,因为返回的是单个对象，一个list可以解决问题；
			
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
