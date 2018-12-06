package com.fuguo.bo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DataDTO;
import com.fuguo.po.DataPO;
import com.fuguo.po.JiluPO;

public class DataBO implements BaseBO{
DataPO dataPO = null;


/**
 * 添加对象
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public DataDTO add(DataDTO dataDTO) throws BSWException{
	
	dataPO=new DataPO();		
	
	return (DataDTO)dataPO.add(dataDTO);
}

/**
 * 查询某记录的详细信息
 * @param data0DTO
 * @return
 * @throws BSWException
 */
public DataDTO query(DataDTO dataDTO) throws BSWException{
	dataPO=new DataPO();
	return (DataDTO)dataPO.query(dataDTO);
}


/**
 * 更新对象
 * @param data0DTO
 * @throws BSWException
 */
public void update(DataDTO dataDTO) throws BSWException{	
	dataPO=new DataPO();
	dataPO.update(dataDTO);
}


/**
 * 删除一个对象
 * @param dataDTO
 * @throws BSWException
 */
public void delete(DataDTO dataDTO) throws BSWException{
	dataPO=new DataPO();
	dataPO.delete(dataDTO);
}


/**
 * 删除多个对象
 * @param dataDTOS
 * @throws BSWException
 */
public void delete(DataDTO[] dataDTOS) throws BSWException{
	dataPO=new DataPO();
	dataPO.delete(dataDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public DataDTO[] loadAll(String _hql)throws BSWException{	
	dataPO=new DataPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])dataPO.loadAll(_hql);//为什么不可以直接放入到DataDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	DataDTO[] dataDTOS=new DataDTO[baseDTOS.length];
	for(int i=0;i<dataDTOS.length;i++){
		dataDTOS[i]=new DataDTO();
		dataDTOS[i]=(DataDTO)baseDTOS[i];
	}
	return dataDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public DataDTO[] search(PageRoll pageRoll) {
	String hql = "from DataDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	DataPO dataPO = new DataPO();
	BaseDTO[] baseDTO = dataPO.search(pageRoll);
	DataDTO[] dataDTO = null;
	dataDTO = new DataDTO[baseDTO.length];
	for (int i = 0; i < dataDTO.length; i++) {
		dataDTO[i] = (DataDTO) baseDTO[i];
	}
	return dataDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	dataPO=new DataPO();
	List list = dataPO.tuplesQuery(hql);
	
	
	
	return list;
}
/**
 * 
 *描述:对象关联查询（基于sql）
 * @param sql,需要组装的bean。
 * @return List
 * 
 * @throws BSWException
 */
public List sqlQuery(String sql,Class classArg) throws BSWException {
	dataPO=new DataPO();
	List list =dataPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	dataPO=new DataPO();
	List list =dataPO.sqlQuery(sql);
	
	return list;
}

public void sqlUpdateOrDel(String sql) throws BSWException{
	dataPO=new DataPO();
	dataPO.sqlUpdateOrDel(sql);
}
/**
 * dwr 专用
 * @param args
 */

public Map getDatas() throws BSWException{
	Map map=new HashMap();
	dataPO=new DataPO();
	List list =dataPO.sqlQuery("select name from data");
	//解析list<Map>；
	Iterator it = list.iterator();
	Map _map=null;
	while(it.hasNext()){
		_map=(Map)it.next();
		map.put(_map.get("NAME"),_map.get("NAME"));
	}
	
	
	return map;
}
/**
 * dwr 专用 获取可用资金
 * @param args
 */
public Double getKYZJ() throws BSWException{
	//获取当前可用资金；
//	获取股息红利总和；
	
//	通过session获取userid‘501’；
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='资金进出' or name='股息红利')"; 
//	得到Map型的list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}

public Double getKYZJ(String idStr) throws BSWException{
	//获取当前可用资金；
//	获取股息红利总和；
	

	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='资金进出' or name='股息红利')"; 
//	得到Map型的list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}

public Double getKYZJ(String idStr,String dateStr) throws BSWException{
	//获取当前可用资金；
//	获取股息红利总和；
	//交易时间date
	String sqlWhereDate=" and (date(date)<='"+dateStr+"') ";//停牌

	Double KYZJ = 0.0;
	String sql4 = "select sum(shuju) shuju from data where  flag2='"+idStr+"' and  (name='资金进出' or name='股息红利') "+sqlWhereDate; 
//	得到Map型的list4

	List list4 = sqlQuery(sql4);
	
	Iterator it4 = list4.iterator();
	Map _map4=null;
	
	
	
	
	if(it4.hasNext()){
		_map4=(Map)it4.next();
		KYZJ  =(Double)_map4.get("SHUJU");
		if(KYZJ==null){
			KYZJ=0.0;
		}
	}
	
	return KYZJ;
}
public static void main(String[] args)throws BSWException{
	DataBO dBO=new DataBO();
	
	Double KYZj = dBO.getKYZJ("500","2018-11-29");
//	DataDTO gdto = new DataDTO();
//	gdto.setName("严重");
//	dBO.add(gdto);
	System.out.print(KYZj);

	

	
	
}


}
