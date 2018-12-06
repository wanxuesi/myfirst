package com.fuguo.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DwrDTO;
import com.fuguo.dto.LxDTO;
import com.fuguo.po.LxPO;

public class LxBO implements BaseBO{
LxPO lxPO = null;


/**
 * 添加对象
 * @param lx0DTO
 * @return
 * @throws BSWException
 */
public LxDTO add(LxDTO lxDTO) throws BSWException{
	
	lxPO=new LxPO();		
	
	return (LxDTO)lxPO.add(lxDTO);
}

/**
 * 查询某记录的详细信息
 * @param lx0DTO
 * @return
 * @throws BSWException
 */
public LxDTO query(LxDTO lxDTO) throws BSWException{
	lxPO=new LxPO();
	return (LxDTO)lxPO.query(lxDTO);
}


/**
 * 更新对象
 * @param lx0DTO
 * @throws BSWException
 */
public void update(LxDTO lxDTO) throws BSWException{	
	lxPO=new LxPO();
	lxPO.update(lxDTO);
}


/**
 * 删除一个对象
 * @param lxDTO
 * @throws BSWException
 */
public void delete(LxDTO lxDTO) throws BSWException{
	lxPO=new LxPO();
	lxPO.delete(lxDTO);
}


/**
 * 删除多个对象
 * @param lxDTOS
 * @throws BSWException
 */
public void delete(LxDTO[] lxDTOS) throws BSWException{
	lxPO=new LxPO();
	lxPO.delete(lxDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public LxDTO[] loadAll(String _hql)throws BSWException{	
	lxPO=new LxPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])lxPO.loadAll(_hql);//为什么不可以直接放入到LxDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	LxDTO[] lxDTOS=new LxDTO[baseDTOS.length];
	for(int i=0;i<lxDTOS.length;i++){
		lxDTOS[i]=new LxDTO();
		lxDTOS[i]=(LxDTO)baseDTOS[i];
	}
	return lxDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public LxDTO[] search(PageRoll pageRoll) {
	String hql = "from LxDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	LxPO lxPO = new LxPO();
	BaseDTO[] baseDTO = lxPO.search(pageRoll);
	LxDTO[] lxDTO = null;
	lxDTO = new LxDTO[baseDTO.length];
	for (int i = 0; i < lxDTO.length; i++) {
		lxDTO[i] = (LxDTO) baseDTO[i];
	}
	return lxDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	lxPO=new LxPO();
	List list = lxPO.tuplesQuery(hql);
	
	
	
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
	lxPO=new LxPO();
	List list =lxPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	lxPO=new LxPO();
	List list =lxPO.sqlQuery(sql);
	
	return list;
}


/**
 * dwr 专用
 * @param args
 */

public Map getLxs() throws BSWException{
	Map map=new LinkedHashMap();
	lxPO=new LxPO();
//	通过session获取userid‘501’；
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	
	lxPO=new LxPO();
	List list =lxPO.sqlQuery("select id, name from lx where flag1='"+idStr+"' or flag1='' or flag1 is Null  order by  id");
	
	//解析list<Map>；
	Iterator it = list.iterator();
	Map _map=null;
	while(it.hasNext()){
		_map=(Map)it.next();
		map.put(_map.get("NAME"),_map.get("NAME"));
	}
	
	
	return map;
}
public List<DwrDTO> getLxsList() throws BSWException{
//	通过session获取userid‘501’；
 	org.directwebremoting.WebContext wc = org.directwebremoting.WebContextFactory.get(); 

    //javax.servlet.http.HttpServletRequest request = wc.getHttpServletRequest();  
    javax.servlet.http.HttpSession session = wc.getSession();
    BaseUserContext baseUserContext = (BaseUserContext)session.getAttribute("#BASEUSERCONTEXT#");
    int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);

	List l=new ArrayList();
	lxPO=new LxPO();
	List list =lxPO.sqlQuery("select id, name,flag2 from lx where (flag1='"+idStr+"' or flag1='' or flag1 is Null) and  flag2!=''   order by  id");
	//解析list<Map>；
	Iterator it = list.iterator();
	Map _map=null;
	DwrDTO dwrDTO;
	while(it.hasNext()){
		_map=(Map)it.next();
		String tmp = (String)_map.get("FLAG2");
		if(tmp.contains("f")||tmp.contains("F")){
			continue;
		}
		dwrDTO=new DwrDTO();
		dwrDTO.setName((String)_map.get("NAME"));
		l.add(dwrDTO);
	}
	
	
	return l;
}


public static void main(String[] args)throws BSWException{
	LxBO dBO=new LxBO();
	LxDTO gdto = new LxDTO();
	gdto.setName("趋势线");
	//dBO.add(gdto);
	//System.out.print(dBO.getLxs().size());
//
//	周期
//	基建
//	消缺
//	技改
//	反措
//	运维
//	陪停
//	特检
//	用户
	
	

	
	
}


}
