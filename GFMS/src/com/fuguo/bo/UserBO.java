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
 * ��Ӷ���
 * @param user0DTO
 * @return
 * @throws BSWException
 */
public UserDTO add(UserDTO userDTO) throws BSWException{
	
	userPO=new UserPO();		
	
	return (UserDTO)userPO.add(userDTO);
}

/**
 * ��ѯĳ��¼����ϸ��Ϣ
 * @param user0DTO
 * @return
 * @throws BSWException
 */
public UserDTO query(UserDTO userDTO) throws BSWException{
	userPO=new UserPO();
	return (UserDTO)userPO.query(userDTO);
}


/**
 * ���¶���
 * @param user0DTO
 * @throws BSWException
 */
public void update(UserDTO userDTO) throws BSWException{	
	userPO=new UserPO();
	userPO.update(userDTO);
}


/**
 * ɾ��һ������
 * @param userDTO
 * @throws BSWException
 */
public void delete(UserDTO userDTO) throws BSWException{
	userPO=new UserPO();
	userPO.delete(userDTO);
}


/**
 * ɾ���������
 * @param userDTOS
 * @throws BSWException
 */
public void delete(UserDTO[] userDTOS) throws BSWException{
	userPO=new UserPO();
	userPO.delete(userDTOS);
}
/**
 * ����������Ϣ
 * @return
 * @throws BSWException
 */
public UserDTO[] loadAll(String _hql)throws BSWException{	
	userPO=new UserPO();	
	BaseDTO[] baseDTOS=(BaseDTO[])userPO.loadAll(_hql);//Ϊʲô������ֱ�ӷ��뵽UserDTOS���أ���Ϊ��֧������Ķ�̬��û�������̬������壩
	UserDTO[] userDTOS=new UserDTO[baseDTOS.length];
	for(int i=0;i<userDTOS.length;i++){
		userDTOS[i]=new UserDTO();
		userDTOS[i]=(UserDTO)baseDTOS[i];
	}
	return userDTOS;
}
/**
 * ��ҳ��ʾ�����Ϣ
 * 
 * @return
 * @throws BSWException 
 */
public UserDTO[] search(PageRoll pageRoll) {
	String hql = "from UserDTO";
	pageRoll.setWhereClause(hql);
	//����ÿҳ��ʾ��¼����
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
 * �����Ҫ���ϲ�ѯ��ʱ�򣬿��Խ��÷������뵽ҵ�����У�
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
 *����:���������ѯ������sql��
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
	
	
////����1��ͨ��tuplesQuery ��ѯ����󣨣�����������뵽�������еļ��ɡ���Լ򵥵�Ĳ�ѯ��
	//�ǳ������������Լ���dto������������set��get��������
//
//	List list = dBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id  ");
//	
//	//��������1
//	UserDTO[] userDTOs = new UserDTO[list.size()];
//	Object[] tuple;
//	for(int i=0;i<list.size();i++){
//		tuple=(Object[])list.get(i);
//		userDTOs[i] = (UserDTO)tuple[0];
//		DanweiDTO dwbmDTO=(DanweiDTO)tuple[1];
//		userDTOs[i].setDwname(dwbmDTO.getName());
//	}
	
	//����������
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
	
////����2��ͨ��sqlQuery ��ѯ��������壨������������뵽List<map>����Ը��ӵĲ�ѯ��
	//ע��map�еļ�Ҫ��д������û�����ݡ�
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
//	//����3����dto���������ԣ�û�������ݿ������������ӹ��ܵ�Ӱ�죻--���Խ��Ϊ��û��Ӱ�졣�������
//   UserDTO udto = new UserDTO();
//   udto.setDwId(5);
//   udto.setXm("test");
//   //udto.setKl("123");
//   dBO.add(udto);
	
//����ͨ����dto���������ԣ�û�������ݿ���������Բ�ѯ���ܵ�Ӱ�죻--���Խ��Ϊ��û��Ӱ�졣
//	String hql = "from UserDTO user";
//	
//	UserDTO[] u= dBO.loadAll(hql);
//	System.out.println(u.length);
	
	
}


}
