package com.fuguo.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;
import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.BaseUserContext;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.GpylDTO;
import com.fuguo.po.GpylPO;

public class GpylBO implements BaseBO{
GpylPO gpylPO = null;


/**
 * 添加对象
 * @param gzly0DTO
 * @return
 * @throws BSWException
 */
public GpylDTO add(GpylDTO gpylDTO) throws BSWException{
	
	gpylPO=new GpylPO();		
	
	return (GpylDTO)gpylPO.add(gpylDTO);
}

/**
 * 查询某记录的详细信息
 * @param gpyl0DTO
 * @return
 * @throws BSWException
 */
public GpylDTO query(GpylDTO gpylDTO) throws BSWException{
	gpylPO=new GpylPO();
	return (GpylDTO)gpylPO.query(gpylDTO);
}


/**
 * 更新对象
 * @param gpyl0DTO
 * @throws BSWException
 */
public void update(GpylDTO gpylDTO) throws BSWException{	
	gpylPO=new GpylPO();
	gpylPO.update(gpylDTO);
}


/**
 * 删除一个对象
 * @param gpylDTO
 * @throws BSWException
 */
public void delete(GpylDTO gpylDTO) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.delete(gpylDTO);
}


/**
 * 删除多个对象
 * @param gpylDTOS
 * @throws BSWException
 */
public void delete(GpylDTO[] gpylDTOS) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.delete(gpylDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public GpylDTO[] loadAll(String _hql)throws BSWException{	
	gpylPO=new GpylPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])gpylPO.loadAll(_hql);//为什么不可以直接放入到GpylDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	GpylDTO[] gpylDTOS=new GpylDTO[baseDTOS.length];
	for(int i=0;i<gpylDTOS.length;i++){
		gpylDTOS[i]=new GpylDTO();
		gpylDTOS[i]=(GpylDTO)baseDTOS[i];
	}
	return gpylDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public GpylDTO[] search(PageRoll pageRoll) {
	String hql = "from GpylDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	GpylPO gpylPO = new GpylPO();
	BaseDTO[] baseDTO = gpylPO.search(pageRoll);
	GpylDTO[] gpylDTO = null;
	gpylDTO = new GpylDTO[baseDTO.length];
	for (int i = 0; i < gpylDTO.length; i++) {
		gpylDTO[i] = (GpylDTO) baseDTO[i];
	}
	return gpylDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	gpylPO=new GpylPO();
	List list = gpylPO.tuplesQuery(hql);
	
	
	
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
	gpylPO=new GpylPO();
	List list =gpylPO.sqlQuery(sql,classArg);
	
	return list;
}

public List sqlQuery(String sql) throws BSWException {
	gpylPO=new GpylPO();
	List list =gpylPO.sqlQuery(sql);
	
	return list;
}

public void sqlUpdateOrDel(String sql) throws BSWException{
	gpylPO=new GpylPO();
	gpylPO.sqlUpdateOrDel(sql);
}
/**
 * dwr 专用
 * @param args
 */

public String getOneGpyl() throws BSWException{
	String message="";
	Map map=new HashMap();
	gpylPO=new GpylPO();
	//显示所有语录；
	GpylDTO[] gplydtos =loadAll("from GpylDTO gply  where gply.lx='语录'");
	//解析list<Map>；
	
	int listCount = gplydtos.length;
	//获取一个随机数[0,listCount)；如果大于listCount，重新获取；
//	System.out.println("总数为："+listCount);
	
	
	//先在Session中查找，如果没有，就放置进去，同时显示该条数据
	WebContext webc = WebContextFactory.get();
	HttpServletRequest request = webc.getHttpServletRequest();
	Map mapSession = (Map)request.getSession().getAttribute("#MAPSESSION#");
	
	int randomInt;
	
	while(1>0){
		
		randomInt =(int)(0+Math.random()*listCount );
		boolean isContain=mapSession.containsKey(randomInt);
		if(isContain==false){
			mapSession.put(randomInt,randomInt);
			request.getSession().setAttribute("#MAPSESSION#",mapSession);
			message  = gplydtos[randomInt].getTitle();
			//System.out.println(randomInt+":"+message);			
			break;
		}
		//System.out.println("随机数："+randomInt+"已包含，正在重新生成》》》》》》");		
	}	
	//System.out.println("mapSession的目前个数为："+mapSession.size());
	
	//如果有，接着获取下一条，指导map的个数=listCount，然后清空Map；
	if(mapSession.size()>listCount*0.7){
		mapSession.clear();
		//System.out.println("总数达到69*0.7，清空为零");
		request.getSession().setAttribute("#MAPSESSION#",mapSession);
	}
	
	return message;
}

/**
 * 获取股票感悟的专用接口
 * @return
 * @throws BSWException
 */
public String[] getOneGpgw() throws BSWException{
	
	WebContext webc = WebContextFactory.get();
	HttpServletRequest request = webc.getHttpServletRequest();
	BaseUserContext baseUserContext = (BaseUserContext) request.getSession().getAttribute("#BASEUSERCONTEXT#");
	int idUser  =baseUserContext.getId();
	String idStr  =Integer.toString(idUser);
	
	String[] message= new String[3];
	gpylPO=new GpylPO();
	//显示所有语录；
	GpylDTO[] gplydtos =loadAll("from GpylDTO gply  where flag1='"+idStr+"' and gply.lx='感悟'");
	//解析list<Map>；
	
	int listCount = gplydtos.length;
	//获取一个随机数[0,listCount)；如果大于listCount，重新获取；
//	System.out.println("总数为："+listCount);
	
	if(listCount==0){
		message[0]="";
		message[1]="";
		message[2]="";
		
	}else{

		int randomInt;
		randomInt =(int)(0+Math.random()*listCount );
		String t1 = gplydtos[randomInt].getTitle();
		String t2  = gplydtos[randomInt].getContent().replaceAll("\r\n","<br>");
		String t3  = gplydtos[randomInt].getFlag2();
		message[0]=t1;
		message[1]=t2;
		message[2]=t3;
	}
	
	
	
	
	
	return message;
}
public static void main(String[] args)throws BSWException{

	
	

	
	
}


}
