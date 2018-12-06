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
import com.fuguo.po.LxPO;

public class DanweiBO {

	DanweiPO danweiPO = null;


	/**
	 * ��Ӷ���
	 * @param danwei0DTO
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO add(DanweiDTO danweiDTO) throws BSWException{
		
		danweiPO=new DanweiPO();		
		
		return (DanweiDTO)danweiPO.add(danweiDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param danwei0DTO
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO query(DanweiDTO danweiDTO) throws BSWException{
		danweiPO=new DanweiPO();
		return (DanweiDTO)danweiPO.query(danweiDTO);
	}


	/**
	 * ���¶���
	 * @param danwei0DTO
	 * @throws BSWException
	 */
	public void update(DanweiDTO danweiDTO) throws BSWException{	
		danweiPO=new DanweiPO();
		danweiPO.update(danweiDTO);
	}


	/**
	 * ɾ��һ������
	 * @param danweiDTO
	 * @throws BSWException
	 */
	public void delete(DanweiDTO danweiDTO) throws BSWException{
		danweiPO=new DanweiPO();
		danweiPO.delete(danweiDTO);
	}


	/**
	 * ɾ���������
	 * @param danweiDTOS
	 * @throws BSWException
	 */
	public void delete(DanweiDTO[] danweiDTOS) throws BSWException{
		danweiPO=new DanweiPO();
		danweiPO.delete(danweiDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	public DanweiDTO[] loadAll(String _hql)throws BSWException{	
		danweiPO=new DanweiPO();	
		BaseDTO[] baseDTOS=(BaseDTO[])danweiPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽DanweiDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
		DanweiDTO[] danweiDTOS=new DanweiDTO[baseDTOS.length];
		for(int i=0;i<danweiDTOS.length;i++){
			danweiDTOS[i]=new DanweiDTO();
			danweiDTOS[i]=(DanweiDTO)baseDTOS[i];
		}
		return danweiDTOS;
	}
	/**
	 * ��ҳ��ʾ�����Ϣ
	 * 
	 * @return
	 * @throws BSWException 
	 */
	public DanweiDTO[] search(PageRoll pageRoll) {
		String hql = "from DanweiDTO";
		pageRoll.setWhereClause(hql);
		//����ÿҳ��ʾ��¼����
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


	/**
	 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
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
	 * 
	 *����:���������ѯ������sql��
	 * @param sql
	 * @return List<Map>
	 * 
	 * @throws BSWException
	 */
	public List sqlQuery(String sql) throws BSWException {
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery(sql);
		
		return list;
	}

	/**
	 * dwr ר��
	 * @param args
	 */
	
	public Map getDanweis() throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select name from danwei");
		//����list<Map>��
		Iterator it = list.iterator();
		Map _map=null;
		while(it.hasNext()){
			_map=(Map)it.next();
			map.put(_map.get("NAME"),_map.get("NAME"));
		}
		
		
		return map;
	}
	
	
	public Map getDanweiIds() throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select id,name from danwei where parent=0");
		//����list<Map>��
		Iterator it = list.iterator();
		Map _map=null;
		while(it.hasNext()){
			_map=(Map)it.next();
			map.put(_map.get("ID"),_map.get("NAME"));
		}
		
		
		return map;
	}
	public Map getBanzuIds(int parent) throws BSWException{
		Map map=new HashMap();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select id,name from danwei where parent>0 and parent="+parent);
		//����list<Map>��
		Iterator it = list.iterator();
		Map _map=null;
		while(it.hasNext()){
			_map=(Map)it.next();
			map.put(_map.get("ID"),_map.get("NAME"));
		}
		
		
		return map;
	}
	public List<DwrDTO> getDanweisList() throws BSWException{
		List l=new ArrayList();
		danweiPO=new DanweiPO();
		List list =danweiPO.sqlQuery("select name from danwei where parent=0");
		//����list<Map>��
		Iterator it = list.iterator();
		Map _map=null;
		DwrDTO dwrDTO;
		while(it.hasNext()){
			_map=(Map)it.next();
			dwrDTO=new DwrDTO();
			dwrDTO.setName((String)_map.get("NAME"));
			l.add(dwrDTO);
		}
		
		
		return l;
	}
	public static void main(String[] args) throws BSWException{
		// TODO �Զ����ɷ������
		DanweiBO dBO=new DanweiBO();
		Map map = dBO.getDanweis();
		System.out.println(map.size());
//		DanweiDTO d=new DanweiDTO();
//		d.setName("abc");

//		dBO.add(d);

		

	}

}
