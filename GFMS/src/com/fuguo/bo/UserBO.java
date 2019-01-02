package com.fuguo.bo;

import java.util.List;
import java.util.Map;

import bsw.base.BaseBO;
import bsw.base.BaseDTO;
import bsw.fwk.PageRoll;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.UserDTO;
import com.fuguo.po.UserPO;

public class UserBO implements BaseBO{
UserPO userPO = null;


/**
 * 添加对象
 * @param user0DTO
 * @return
 * @throws BSWException
 */
public UserDTO add(UserDTO userDTO) throws BSWException{
	
	userPO=new UserPO();		
	
	return (UserDTO)userPO.add(userDTO);
}

/**
 * 查询某记录的详细信息
 * @param user0DTO
 * @return
 * @throws BSWException
 */
public UserDTO query(UserDTO userDTO) throws BSWException{
	userPO=new UserPO();
	return (UserDTO)userPO.query(userDTO);
}


/**
 * 更新对象
 * @param user0DTO
 * @throws BSWException
 */
public void update(UserDTO userDTO) throws BSWException{	
	userPO=new UserPO();
	userPO.update(userDTO);
}


/**
 * 删除一个对象
 * @param userDTO
 * @throws BSWException
 */
public void delete(UserDTO userDTO) throws BSWException{
	userPO=new UserPO();
	userPO.delete(userDTO);
}


/**
 * 删除多个对象
 * @param userDTOS
 * @throws BSWException
 */
public void delete(UserDTO[] userDTOS) throws BSWException{
	userPO=new UserPO();
	userPO.delete(userDTOS);
}
/**
 * 查找所有信息
 * @return
 * @throws BSWException
 */
public UserDTO[] loadAll(String _hql)throws BSWException{	
	userPO=new UserPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])userPO.loadAll(_hql);//为什么不可以直接放入到UserDTOS中呢？因为不支持数组的多态（没有数组多态这个定义）
	UserDTO[] userDTOS=new UserDTO[baseDTOS.length];
	for(int i=0;i<userDTOS.length;i++){
		userDTOS[i]=new UserDTO();
		userDTOS[i]=(UserDTO)baseDTOS[i];
	}
	return userDTOS;
}
/**
 * 分页显示相关信息
 * 
 * @return
 * @throws BSWException 
 */
public UserDTO[] search(PageRoll pageRoll) {
	String hql = "from UserDTO";
	pageRoll.setWhereClause(hql);
	//设置每页显示记录条数
	pageRoll.setPageSize(8);
	UserPO userPO = new UserPO();
	BaseDTO[] baseDTO = userPO.search(pageRoll);
	UserDTO[] userDTO = null;
	userDTO = new UserDTO[baseDTO.length];
	for (int i = 0; i < userDTO.length; i++) {
		userDTO[i] = (UserDTO) baseDTO[i];
	}
	return userDTO;
}


/**
 * 如果需要联合查询的时候，可以将该方法放入到业务类中；
 * @param hql
 * @return
 * @throws BSWException
 */
public List tuplesQuery(String hql) throws BSWException {
	userPO=new UserPO();
	List list = userPO.tuplesQuery(hql);
	
	
	
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



public List sqlQuery(String sql,Class classArg) throws BSWException {
	userPO=new UserPO();
	List list =userPO.sqlQuery(sql,classArg);
	
	return list;
}

public static void main(String[] args)throws BSWException{
	UserBO dBO=new UserBO();
	
	
////测试1：通过tuplesQuery 查询多对象（）关联结果放入到主对象中的技巧。相对简单点的查询；
	//非常棒，这样可以减少dto的数量。减少set，get的数量。
//
//	List list = dBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id  ");
//	
//	//解析方法1
//	UserDTO[] userDTOs = new UserDTO[list.size()];
//	Object[] tuple;
//	for(int i=0;i<list.size();i++){
//		tuple=(Object[])list.get(i);
//		userDTOs[i] = (UserDTO)tuple[0];
//		DanweiDTO dwbmDTO=(DanweiDTO)tuple[1];
//		userDTOs[i].setDwname(dwbmDTO.getName());
//	}
	
	//解析方法二
//	UserDTO[] userDTO2s = new UserDTO[list.size()];
//	Object[] tuple2;
//	Iterator it=list.iterator();
//	int j=0;
//	while(it.hasNext()){
//		tuple2=(Object[])it.next();		
//		userDTO2s[j] = (UserDTO)tuple2[0];
//		DanweiDTO dwbmDTO=(DanweiDTO)tuple2[1];
//		userDTO2s[j].setDwname(dwbmDTO.getName());
//		j++;
//	}
	
////测试2：通过sqlQuery 查询多对象结合体（）关联结果放入到List<map>。相对复杂的查询；
	//注意map中的键要大写，否则没有数据。
//	String sql ="select user.xm,user.dwid,danwei.name from user,danwei where user.dwid = danwei.id";
//	List list = dBO.sqlQuery(sql);
//	UserDTO[] userDTOs = new UserDTO[list.size()];
//	
//	for(int i=0;i<list.size();i++){
//		Map map=(Map)list.get(i);
//		userDTOs[i] =new UserDTO();
//		userDTOs[i].setXm((String)map.get("XM"));
//		userDTOs[i].setDwId((Integer)map.get("DWID"));
//		userDTOs[i].setDwname((String)map.get("NAME"));
//	}	
//
//
//	//测试3：在dto中增加属性（没有与数据库关联），对添加功能的影响；--测试结果为：没有影响。可以添加
//   UserDTO udto = new UserDTO();
//   udto.setDwId(5);
//   udto.setXm("test");
//   //udto.setKl("123");
//   dBO.add(udto);
	
//测试通过。dto中增加属性（没有与数据库关联），对查询功能的影响；--测试结果为：没有影响。
//	String hql = "from UserDTO user";
//	
//	UserDTO[] u= dBO.loadAll(hql);
//	System.out.println(u.length);
	
	
}


}
