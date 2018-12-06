package com.fuguo.bo;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DataDTO;
import com.fuguo.dto.JiluDTO;
import com.fuguo.dto.ListDTO;
import com.fuguo.dto.LsjgdateDTO;
import com.fuguo.dto.OrderDTO;
import com.fuguo.po.JiluPO;
import com.fuguo.util.DateUtil;

public class JiluBO implements BaseBO{
JiluPO jiluPO = null;


/**
 * 添加对象
 * @param weekPlan0DTO
 * @return
 * @throws BSWException
 */
public JiluDTO add(JiluDTO jiluDTO) throws BSWException{
	
	jiluPO=new JiluPO();		
	
	return (JiluDTO)jiluPO.add(jiluDTO);
}

/**
 * 查询某记录的详细信息
 * @param jilu0DTO
 * @return
 * @throws BSWException
 */
public JiluDTO query(JiluDTO jiluDTO) throws BSWException{
	jiluPO=new JiluPO();
	return (JiluDTO)jiluPO.query(jiluDTO);
}


/**
 * 更新对象
 * @param jilu0DTO
 * @throws BSWException
 */
public void update(JiluDTO jiluDTO) throws BSWException{	
	jiluPO=new JiluPO();
	jiluPO.update(jiluDTO);
}


/**
 * 删除一个对象
 * @param jiluDTO
 * @throws BSWException
 */
public void delete(JiluDTO jiluDTO) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.delete(jiluDTO);
}


/**
 * 删除多个对象
 * @param jiluDTOS
 * @throws BSWException
 */
public void delete(JiluDTO[] jiluDTOS) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.delete(jiluDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public JiluDTO[] loadAll(String _hql)throws BSWException{	
	jiluPO=new JiluPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])jiluPO.loadAll(_hql);//为什么不可以直接放入到JiluDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	JiluDTO[] jiluDTOS=new JiluDTO[baseDTOS.length];
	for(int i=0;i<jiluDTOS.length;i++){
		jiluDTOS[i]=new JiluDTO();
		jiluDTOS[i]=(JiluDTO)baseDTOS[i];
	}
	return jiluDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public JiluDTO[] search(PageRoll pageRoll) {
//	String hql = "from JiluDTO";
//	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	JiluPO jiluPO = new JiluPO();
	BaseDTO[] baseDTO = jiluPO.search(pageRoll);
	JiluDTO[] jiluDTO = null;
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm");
	DateUtil dateUtil=new DateUtil();
	Date jhkssj;
	Date jhjssj;
	String jhkssjStr;
	String jhjssjStr;
	String jhkssjWeek;
	String jhjssjWeek;
	String tmpgzcs;
	String gzcs;
	String bdz;
	String tbdw;
	String flag1;
	jiluDTO = new JiluDTO[baseDTO.length];
	for (int i = 0; i < jiluDTO.length; i++) {
		jiluDTO[i] = (JiluDTO) baseDTO[i];	
		
	}
	return jiluDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	jiluPO=new JiluPO();
	List list = jiluPO.tuplesQuery(hql);
	
	
	
	return list;
}


/**
 * 
 *描述:对象关联查询（基于sql）
 * @param sql
 * @return List<Map>
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql) throws BSWException {
	jiluPO=new JiluPO();
	List list =jiluPO.sqlQuery(sql);
	
	return list;
}


public List sqlQuery(String sql,Class classArg) throws BSWException {
	jiluPO=new JiluPO();
	List list =jiluPO.sqlQuery(sql,classArg);
	
	return list;
}
public void sqlUpdateOrDel(String sql) throws BSWException{
	jiluPO=new JiluPO();
	jiluPO.sqlUpdateOrDel(sql);
}

public void clearAllData(BaseUserContext baseUserContext)throws BSWException{
//	需要清除的数据有：
	//jilu表里面的数据；
	int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	String sqlJilu = "delete from jilu where khdm='"+idStr+"'";
	sqlUpdateOrDel(sqlJilu);
	//order里面的数据；
	OrderBO orderBO=new OrderBO();
	String sqlOrder = "delete from order where flag1='"+idStr+"'";
	orderBO.sqlUpdateOrDel(sqlOrder);
	
	//list里面的数据；
	ListBO listBO=new ListBO();
	String sqlList = "delete from list where flag1='"+idStr+"'";
	listBO.sqlUpdateOrDel(sqlList);
	//data里面的数据；
	DataBO dataBO=new DataBO();
	String sqlData = "delete from data where flag2='"+idStr+"'";
	dataBO.sqlUpdateOrDel(sqlData);
	DataDTO dDTO=new DataDTO();
	dDTO.setName("资金进出");
	
	String shujuStr = (String)baseUserContext.getSystemconfig().get("defaultmoney");
	Double shuju = Double.parseDouble(shujuStr);
	//double shuju=200000d;
	//DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();

	//System.out.println(df.format(shuju));
	dDTO.setShuju(shuju);
//	初始化份额数；
	dDTO.setFene(shuju);//1元对应1.00的份额；
	dDTO.setDate(new Date());
	dDTO.setFlag2(idStr);
	dataBO.add(dDTO);
	
	
	
}
/**
 * 将未处理数据，设定为已处理数据时，后台的相关逻辑运算；
 * @param jiluDTO
 * @throws BSWException
 */
public void logic(JiluDTO jiluDTO,String userIdStr,BaseUserContext baseUserContext, boolean isAddJilu)throws BSWException {

	
	
    String zqdm =jiluDTO.getZqdm();
    String zqmc =jiluDTO.getZqmc();
    String mmflag =jiluDTO.getMmflag();
    
//  判断是否可以买入该股票
    boolean isCcgpOK=false;
    OrderBO oBO = new OrderBO();
    if(mmflag.equals("买入")){
    	isCcgpOK  = oBO.isCcgpOK(zqdm,baseUserContext);
    }else{
    	isCcgpOK=true;
    }
    if(isCcgpOK==false){
    	throw new BSWException("sorry,暂时无法买入该股票，持仓股票数已满，请先卖出一只持仓股票，或者升级用户权限！");
    }
    //double cjjg =jiluDTO.getCjjg();
    int cjsl =jiluDTO.getCjsl();
    //double cjje =jiluDTO.getCjje();
    double qsje = jiluDTO.getQsje();
    String jifl =jiluDTO.getJifl(); 
    Date jysjDate = jiluDTO.getJysj();
		
        
    JiluBO tBO =new JiluBO();
	//在order中查找，有无该zqdm，如果没有，添加。
	//OrderBO oBO = new OrderBO();
	OrderDTO oDTO = new OrderDTO();
	
	ListBO lBO = new ListBO();
	ListDTO lDTO = new ListDTO();
	List list =oBO.sqlQuery("select id,zqdm,cysl,cbj from order where  flag1='"+userIdStr+"' and   zqdm='"+zqdm+"'");
	List listlist =lBO.sqlQuery("select id,zqdm,cysl,jifl from list where  flag1='"+userIdStr+"' and   zqdm='"+zqdm+"' and jifl='"+jifl+"'");
	
	
	if(list.size()==0){
		
		if(mmflag.equals("买入")){
			
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			说明没有该记录，直接在order和list中加即可；
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			oDTO.setCysl(cjsl);
			//要算下成本价的。
			oDTO.setCbj(Math.abs(qsje)/cjsl);
			oDTO.setFlag1(userIdStr);
			oBO.add(oDTO);
			//order中是空的话，说明list中肯定为空；直接添加即可；
			lDTO.setZqdm(zqdm);
			lDTO.setZqmc(zqmc);
			lDTO.setCysl(cjsl);
			lDTO.setJifl(jifl);
			lDTO.setFlag1(userIdStr);
			
			lBO.add(lDTO);
			//2015-10-7添加；
//			需要添加/更新lsjgdate记录；
			LsjgdateDTO lsjgdateDTO  =new LsjgdateDTO();
			lsjgdateDTO.setId(zqdm);
			
			LsjgdateBO  lsjgdateBO  =new LsjgdateBO();
			DateUtil dUtil  =new DateUtil();
			LsjgdateDTO tmpDTO = lsjgdateBO.query(lsjgdateDTO);
			
			//这行代码有bug-null
			if(tmpDTO!=null&&tmpDTO.getDate()!=null&&tmpDTO.getDate().getYear()>0){
				//如果有该股票的lsjgDate记录，则不添加；
			}else{
				//如果没有，就必须添加lsjgDate记录！
				lsjgdateDTO.setId(zqdm);
				lsjgdateDTO.setZqdm(zqdm);
				//获取该日期九天前的日期；
				Date startDate = dUtil.getBeforeNDay(jysjDate,9);
				//Date endDate = dUtil.getBeforeNDay(new java.util.Date(),1);
				lsjgdateDTO.setDate(null);
				lsjgdateDTO.setDatestart(startDate);
//				添加该记录
				lsjgdateBO.add(lsjgdateDTO);
				
//				//更新该股票的历史价格
//				LsjgBO lsjgBO = new LsjgBO();
//				lsjgBO.updateLsjgs(startDate,endDate,zqdm);
//				
//				//再更新下lsjgdate里的Date
//				lsjgdateDTO.setId(zqdm);
//				lsjgdateDTO.setZqdm(zqdm);
//				
//				lsjgdateDTO.setDate(endDate);
////				添加该记录
//				lsjgdateBO.update(lsjgdateDTO);
				
			}
			
		}else{
			//因为list.size()==0，order中没有该股票数量；
			throw new BSWException("无法卖出，该股票持仓量为0！");
		}
		
		
	}else{
		//order中有的情况（size>0）；需要update2个表。
		if(mmflag.equals("买入")){
			
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			说明有该记录，直接在orderUpdate;
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			
			Iterator it = list.iterator();
			
			Map _map=null;
			Integer cysl=0;
			double cbj=0;
			Integer id=0;
			if(it.hasNext()){
				_map=(Map)it.next();
				id = (Integer)_map.get("ID");
				cysl = (Integer)_map.get("CYSL");//原来的持有数量
				
				cbj  =(Double)_map.get("CBJ");//原来的成本价
				//cjjes = (Double)_map.get("CJJES");
			}
			
			
//			要算下叠加后的持有数量；。
			oDTO.setId(id);
			oDTO.setCysl(cjsl+cysl);
			
			//要算下买入叠加的成本价的。
			oDTO.setCbj((Math.abs(qsje)+cysl*cbj)/(cjsl+cysl));
			oDTO.setFlag1(userIdStr);
			oBO.update(oDTO);
			
			
			
			//list中分2种情况，
			
			
			Iterator itlist = listlist.iterator();
//			一种是没有该jifl；
			if(listlist.size()==0){
				//直接添加该交易分类的list数据；
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setCysl(cjsl);
				lDTO.setJifl(jifl);
				lDTO.setFlag1(userIdStr);
				lBO.add(lDTO);
				
			}else{
//				一种是有该jifl；
				Map _maplist=null;
				Integer cysllist=0;
				Integer idList = 0;
				
				if(itlist.hasNext()){
					_maplist=(Map)itlist.next();
					idList = (Integer)_maplist.get("ID");
					cysllist = (Integer)_maplist.get("CYSL");//原来的持有数量
					
	
				}
				
				lDTO.setId(idList);
//				要算下叠加后的持有数量；。
				lDTO.setCysl(cysllist+cjsl);					
				//order中是空的话，说明list中肯定为空；直接添加即可；
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setJifl(jifl);	
				lDTO.setFlag1(userIdStr);
				lBO.update(lDTO);
			}
			
			
		}else{
			if(isAddJilu==true){
				tBO.add(jiluDTO);
			}
//			order中有的情况（size>0）；
			//mmflag.equals("卖出")
			
			
			//需要update2个表。
			//因为卖出时，通过交易分类选择后，自动跳出可以卖出的数量，所以不需要判断。直接卖出即可。
			//即update两张表；
			

//			说明有该记录，直接在orderUpdate;
			oDTO.setZqdm(zqdm);
			oDTO.setZqmc(zqmc);
			
			Iterator it = list.iterator();
			
			Map _map=null;
			Integer cysl=0;
			double cbj=0;
			Integer idOrder= 0;
			if(it.hasNext()){
				_map=(Map)it.next();
				idOrder =  (Integer)_map.get("ID");
				cysl = (Integer)_map.get("CYSL");//原来的持有数量
				
				cbj  =(Double)_map.get("CBJ");//原来的成本价
				//cjjes = (Double)_map.get("CJJES");
			}
			
			oDTO.setId(idOrder);
			oDTO.setFlag1(userIdStr);
			
//			要算下卖出后叠加后的持有数量；。
			int cysltmp=0;
			cysltmp = cysl-Math.abs(cjsl);
			oDTO.setCysl(cysltmp);
			if(cysltmp==0){
				oDTO.setCbj(0);
			}else{
//				要算下卖出后的叠加成本价的。
				oDTO.setCbj((cysl*cbj-Math.abs(qsje))/(cysl-Math.abs(cjsl)));
			}

			
			oBO.update(oDTO);
			
			
			
			
			
			
			Iterator itlist = listlist.iterator();

//			肯定有该jifl；
			Map _maplist=null;
			Integer cysllist=0;
			Integer idList=0;
			if(itlist.hasNext()){
				_maplist=(Map)itlist.next();
				idList = (Integer)_maplist.get("ID");
				cysllist = (Integer)_maplist.get("CYSL");//原来的持有数量
				

			}
			
			lDTO.setId(idList);
//			要算下叠加后的持有数量；。
			lDTO.setCysl(cysllist-Math.abs(cjsl));					
			//order中是空的话，说明list中肯定为空；直接添加即可；
			lDTO.setZqdm(zqdm);
			lDTO.setZqmc(zqmc);
			lDTO.setJifl(jifl);	
			lDTO.setFlag1(userIdStr);
			lBO.update(lDTO);
		
			
		}
		
		
		
		
	}
	
	//还需要进行一次delete，将cysl为0的order和list都删除；
	lBO.sqlUpdateOrDel("delete from list where cysl=0");
	oBO.sqlUpdateOrDel("delete from order where cysl=0");
	
	//将清算金额Qsje 包含正负，买入为负，卖出为正；放入到Data表中；
	DataBO dBO = new DataBO();
	DataDTO dDTO = new DataDTO();
	dDTO.setName("资金进出");
	dDTO.setShuju(qsje);
	dDTO.setDate(new Date());
	dDTO.setFlag1("股票"+mmflag+"清算金额");
	dDTO.setFlag2(userIdStr);
	dBO.add(dDTO);
	

	
	
	
	
	
}

public static void main(String[] args)throws BSWException{
	JiluBO dBO=new JiluBO();
//	JiluDTO mdto = new JiluDTO();
//	mdto.setTbdw("省检常州工区");
//	mdto.setDiqu("常州");
//	dBO.add(mdto);
	List list = dBO.tuplesQuery("from JiluDTO");
	System.out.println(list.size());
	
	

	
	
}

public void logicDelete(JiluDTO jiluDTO,String userIdStr) throws BSWException{
	 	String zqdm =jiluDTO.getZqdm();
	    String zqmc =jiluDTO.getZqmc();
	    String mmflag =jiluDTO.getMmflag();
	    //double cjjg =jiluDTO.getCjjg();
	    int cjsl =jiluDTO.getCjsl();
	    //double cjje =jiluDTO.getCjje();
	    double qsje = jiluDTO.getQsje();
	    String jifl =jiluDTO.getJifl(); 
			
	        

		//在order中查找，有无该zqdm，如果没有，添加。
		OrderBO oBO = new OrderBO();
		OrderDTO oDTO = new OrderDTO();
		
		ListBO lBO = new ListBO();
		ListDTO lDTO = new ListDTO();
		List listorder =oBO.sqlQuery("select id,zqdm,cysl,cbj from order where  flag1='"+userIdStr+"' and  zqdm='"+zqdm+"'");
		List listlist =lBO.sqlQuery("select id,zqdm,cysl,jifl from list where  flag1='"+userIdStr+"' and  zqdm='"+zqdm+"' and jifl='"+jifl+"'");
		
		//如果order里灭有记录；
		if(listorder.size()==0){
			//如果回撤的数据是卖出；
			if(mmflag.equals("卖出")){
				
				
//				说明没有该记录，直接在order和list中加即可；
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				oDTO.setCysl(cjsl);
				//要算下成本价的。
				oDTO.setCbj(Math.abs(qsje)/cjsl);
				
				oBO.add(oDTO);
				//order中是空的话，说明list中肯定为空；直接添加即可；
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setCysl(cjsl);
				lDTO.setJifl(jifl);
				lDTO.setFlag1(userIdStr);
				
				lBO.add(lDTO);
				
			}else{
				
				//如果撤销的是 (mmflag.equals("买入"))
				
				//因为list.size()==0，order中没有该股票数量；
				throw new BSWException("无法撤销该“买入”类型的记录，该股票持仓量为0，说明已经卖出了！");
			}
			
			
		}else{
			//order中有的情况（size>0）；需要update2个表。
			if(mmflag.equals("卖出")){
				
				
//				说明有该记录，直接在orderUpdate;
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				
				Iterator it = listorder.iterator();
				
				Map _map=null;
				Integer cysl=0;
				double cbj=0;
				Integer id=0;
				if(it.hasNext()){
					_map=(Map)it.next();
					id = (Integer)_map.get("ID");
					cysl = (Integer)_map.get("CYSL");//原来的持有数量
					
					cbj  =(Double)_map.get("CBJ");//原来的成本价
					//cjjes = (Double)_map.get("CJJES");
				}
				
				
//				要算下叠加后的持有数量；。
				oDTO.setId(id);
				oDTO.setCysl(Math.abs(cjsl)+cysl);
				
				//要算下卖出撤销后叠加的成本价的。
				oDTO.setCbj((Math.abs(qsje)+cysl*cbj)/(Math.abs(cjsl)+cysl));
				oDTO.setFlag1(userIdStr);
				
				oBO.update(oDTO);
				
				
				
				//list中分2种情况，
				
				
				Iterator itlist = listlist.iterator();
//				一种是没有该jifl；
				if(listlist.size()==0){
					//直接添加该交易分类的list数据；
					lDTO.setZqdm(zqdm);
					lDTO.setZqmc(zqmc);
					lDTO.setCysl(Math.abs(cjsl));
					lDTO.setJifl(jifl);
					lDTO.setFlag1(userIdStr);
					lBO.add(lDTO);
					
				}else{
//					一种是有该jifl；
					Map _maplist=null;
					Integer cysllist=0;
					Integer idList = 0;
					
					if(itlist.hasNext()){
						_maplist=(Map)itlist.next();
						idList = (Integer)_maplist.get("ID");
						cysllist = (Integer)_maplist.get("CYSL");//原来的持有数量
						
		
					}
					
					lDTO.setId(idList);
//					要算下叠加后的持有数量；。
					lDTO.setCysl(cysllist+Math.abs(cjsl));					
					
					lDTO.setZqdm(zqdm);
					lDTO.setZqmc(zqmc);
					lDTO.setJifl(jifl);	
					lDTO.setFlag1(userIdStr);
					lBO.update(lDTO);
				}
				
				
			}else{
				
//				order中有的情况（size>0）；
				//撤销的类型为mmflag.equals("买入")
				
				
				//需要update2个表。
				//因为卖出时，通过交易分类选择后，自动跳出可以卖出的数量，所以不需要判断。直接卖出即可。
				//即update两张表；
				

//				说明有该记录，直接在orderUpdate;
				oDTO.setZqdm(zqdm);
				oDTO.setZqmc(zqmc);
				
				Iterator it = listorder.iterator();
				
				Map _map=null;
				Integer cysl=0;
				double cbj=0;
				Integer idOrder= 0;
				if(it.hasNext()){
					_map=(Map)it.next();
					idOrder =  (Integer)_map.get("ID");
					cysl = (Integer)_map.get("CYSL");//原来的持有数量
					
					cbj  =(Double)_map.get("CBJ");//原来的成本价
					//cjjes = (Double)_map.get("CJJES");
				}
				
				oDTO.setId(idOrder);
//				要算下卖出后叠加后的持有数量；。
				int cysltmp=0;
				cysltmp = cysl-Math.abs(cjsl);
				oDTO.setCysl(cysltmp);
				if(cysltmp==0){
					oDTO.setCbj(0);
				}else{
//					要算下卖出后的叠加成本价的。
					oDTO.setCbj((cysl*cbj-Math.abs(qsje))/(cysl-Math.abs(cjsl)));
				}
				oDTO.setFlag1(userIdStr);
				oBO.update(oDTO);
				
				
				
				
				
				
				Iterator itlist = listlist.iterator();

//				肯定有该jifl；
				Map _maplist=null;
				Integer cysllist=0;
				Integer idList=0;
				if(itlist.hasNext()){
					_maplist=(Map)itlist.next();
					idList = (Integer)_maplist.get("ID");
					cysllist = (Integer)_maplist.get("CYSL");//原来的持有数量
					

				}
				
				lDTO.setId(idList);
//				要算下叠加后的持有数量；。
				lDTO.setCysl(cysllist-Math.abs(cjsl));					
				//order中是空的话，说明list中肯定为空；直接添加即可；
				lDTO.setZqdm(zqdm);
				lDTO.setZqmc(zqmc);
				lDTO.setJifl(jifl);	
				lDTO.setFlag1(userIdStr);
				
				lBO.update(lDTO);
			
				
			}
			
			
			
			
		}
		
		//还需要进行一次delete，将cysl为0的order和list都删除；
		lBO.sqlUpdateOrDel("delete from list where cysl=0");
		oBO.sqlUpdateOrDel("delete from order where cysl=0");
		
		//将清算金额Qsje 包含正负，买入为负，卖出为正；放入到Data表中；
		DataBO dBO = new DataBO();
		DataDTO dDTO = new DataDTO();
		dDTO.setName("资金进出");
		dDTO.setShuju(-qsje);
		dDTO.setDate(new Date());
		dDTO.setFlag1("撤销股票"+mmflag+"清算金额");
		dDTO.setFlag2(userIdStr);
		dBO.add(dDTO);
		

		
	
}


}
