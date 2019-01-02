package com.fuguo.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.DwrDTO;
import com.fuguo.po.DanweiPO;
import com.fuguo.po.JuesePO;
import com.fuguo.po.LxPO;

public class DanweiBO {

	DanweiPO danweiPO = null;


	/**
	 * 添加对象
	 * @param danwei0DTO
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO add(DanweiDTO danweiDTO) throws BSWException{
		
		danweiPO=new DanweiPO();		
		
		return (DanweiDTO)danweiPO.add(danweiDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param danwei0DTO
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO query(DanweiDTO danweiDTO) throws BSWException{
		danweiPO=new DanweiPO();
		return (DanweiDTO)danweiPO.query(danweiDTO);
	}


	/**
	 * 更新对象
	 * @param danwei0DTO
	 * @throws BSWException
	 */
	public void update(DanweiDTO danweiDTO) throws BSWException{	
		danweiPO=new DanweiPO();
		danweiPO.update(danweiDTO);
	}


	/**
	 * 删除一个对象
	 * @param danweiDTO
	 * @throws BSWException
	 */
	public void delete(DanweiDTO danweiDTO) throws BSWException{
		danweiPO=new DanweiPO();
		danweiPO.delete(danweiDTO);
	}


	/**
	 * 删除多个对象
	 * @param danweiDTOS
	 * @throws BSWException
	 */
	public void delete(DanweiDTO[] danweiDTOS) throws BSWException{
		danweiPO=new DanweiPO();
		danweiPO.delete(danweiDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO[] loadAll(String _hql)throws BSWException{	
		danweiPO=new DanweiPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])danweiPO.loadAll(_hql);//为什么不可以直接放入到DanweiDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
		DanweiDTO[] danweiDTOS=new DanweiDTO[baseDTOS.length];
		for(int i=0;i<danweiDTOS.length;i++){
			danweiDTOS[i]=new DanweiDTO();
			danweiDTOS[i]=(DanweiDTO)baseDTOS[i];
		}
		return danweiDTOS;
	}
	/**
	 * 分页显示相关信息
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public DanweiDTO[] search(PageRoll pageRoll) {
		String hql = "from DanweiDTO";
		pageRoll.setWhereClause(hql);
		//设置每页显示记录条数
		pageRoll.setPageSize(8);
		DanweiPO danweiPO = new DanweiPO();
		BaseDTO[] baseDTO = danweiPO.search(pageRoll);
		DanweiDTO[] danweiDTO = null;
		danweiDTO = new DanweiDTO[baseDTO.length];
		for (int i = 0; i < danweiDTO.length; i++) {
			danweiDTO[i] = (DanweiDTO) baseDTO[i];
		}
		return danweiDTO;
	}

	public List sqlQuery(String sql,Class classArg) throws BSWException {
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery(sql,classArg);
		
		return list;
	}
	/**
	 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
	 * @param hql
	 * @return
	 * @throws BSWException
	 */
	public List tuplesQuery(String hql) throws BSWException {
		danweiPO=new DanweiPO();
		List list = danweiPO.tuplesQuery(hql);	
		return list;
	}
	

	/**
	 * dwr 专用
	 * @param args
	 */
	
	public Map getDanweis() throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select name from danwei",DanweiDTO.class);
		//解析list<Map>；
		Iterator it = list.iterator();
		DanweiDTO danweiDTO=null;
		while(it.hasNext()){
			danweiDTO=(DanweiDTO)it.next();
			map.put(danweiDTO.getName(),danweiDTO.getName());
		}
		
		
		return map;
	}
	
	
	public Map getDanweiIds() throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select id,name from danwei where parent=0",DanweiDTO.class);
		//解析list<Map>；
		Iterator it = list.iterator();
		DanweiDTO danweiDTO=null;
		while(it.hasNext()){
			danweiDTO=(DanweiDTO)it.next();
			map.put(danweiDTO.getId(),danweiDTO.getName());
		}
		
		
		return map;
	}
	public Map getBanzuIds(int parent) throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select id,name from danwei where parent>0 and parent="+parent, DanweiDTO.class);
		//解析list<Map>；
		Iterator it = list.iterator();
		DanweiDTO danweiDTO=null;
		while(it.hasNext()){
			danweiDTO=(DanweiDTO)it.next();
			map.put(danweiDTO.getId(),danweiDTO.getName());
		}
		
		
		return map;
	}
	public List<DwrDTO> getDanweisList() throws BSWException{
		List l=new ArrayList();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select name from danwei where parent=0",DanweiDTO.class);
		//解析list<Map>；
		Iterator it = list.iterator();
		DanweiDTO danweiDTO=null;
		DwrDTO dwrDTO;
		while(it.hasNext()){
			danweiDTO=(DanweiDTO)it.next();
			dwrDTO=new DwrDTO();
			dwrDTO.setName(danweiDTO.getName());
			l.add(dwrDTO);
		}
		
		
		return l;
	}
	public static void main(String[] args) throws BSWException{
		// TODO 自动生成方法存根
		DanweiBO dBO=new DanweiBO();
		Map map = dBO.getDanweis();
		System.out.println(map.size());
//		DanweiDTO d=new DanweiDTO();
//		d.setName("abc");

//		dBO.add(d);

		

	}

}
