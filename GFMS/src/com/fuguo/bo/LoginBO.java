package com.fuguo.bo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.FunctionDTO;
import com.fuguo.dto.UserDTO;

public class LoginBO {
	
	/**
	 * ��֤�û��������룻����һ��������UserDto
	 */
	public BaseUserContext login(UserDTO _userDTO) throws BSWException{
		BaseUserContext baseUserContext = null;
		String xm = _userDTO.getXm().trim();
		String kl = _userDTO.getKl().trim();
		String tmpsql="";
		if(kl.equals("")){
			tmpsql=" or user.kl is null";
		}
		
		
		
		
		
		
		UserBO userBO=new UserBO();
//	��������test1	��ǿ
		//List list = userBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")");
		
//�����Եĵ�½�쳣��userBO.tuplesQuery�쳣����дΪsqlQuery	
		String sqlQuery = " select user.ID,user.XM,user.KL,user.NX,user.WX,user.OTHERFLAG,user.DATESTART,user.DATEEND,user.DWID,danwei.PARENT,danwei.NAME from  user, danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")";
//		System.out.println(sqlQuery);
		List list = userBO.sqlQuery(sqlQuery);
		
		
		if(list.size()==0){
//			 �ж��û����������Ƿ���ȷ
			
			throw new BSWException("�û��������벻��ȷ�������µ�¼��");
			
		}
		Iterator it = list.iterator();
		Map queryMap=null;
		if(it.hasNext()){
//			------���������û���������------------
			baseUserContext = new BaseUserContext();
			queryMap =(Map)it.next();
			
			
			baseUserContext.setId((Integer)queryMap.get("ID"));
			
			//baseUserContext.setBanzu(userDTO.getBanzu());
			baseUserContext.setXm((String)queryMap.get("XM"));
			baseUserContext.setKl((String)queryMap.get("KL"));
			baseUserContext.setNx((String)queryMap.get("NX"));
			baseUserContext.setWx((String)queryMap.get("WX"));
			baseUserContext.setJuese((String)queryMap.get("OTHERFLAG"));
			baseUserContext.setDatestart((java.util.Date)queryMap.get("DATESTART"));
			baseUserContext.setDateend((java.util.Date)queryMap.get("DATEEND"));
			
			//�����һ�����ţ���ô��������Ϊ������Ȼ�����е��Ӱ�����뵽map��ȥ��
			Integer dwParent  = (Integer)queryMap.get("PARENT");
			if(dwParent==0){
				baseUserContext.setDwId((Integer)queryMap.get("DWID"));//һ������ID
				baseUserContext.setDwname((String)queryMap.get("NAME"));//һ����������
				baseUserContext.setBanzu("");
				
			}
//			����Ƕ������飬��ô��������Ϊ-dwDTO.getName()������mapΪ�ա�
			if(dwParent>0){
				//�ҳ�����һ������id����һ������name��
				DanweiBO danweiBO = new DanweiBO();
				DanweiDTO _dDTO=new DanweiDTO();
				_dDTO.setId(dwParent);
				_dDTO = danweiBO.query(_dDTO);
				//����һ������id������
				baseUserContext.setDwId(_dDTO.getId());//һ������ID
				baseUserContext.setDwname(_dDTO.getName());//һ����������
				baseUserContext.setBanzu((String)queryMap.get("NAME"));
			}
			//����ǹ�˾����(�Ⱥ���)
			//����Ȩ��
			String juesename = (String)queryMap.get("OTHERFLAG");
			JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
			List listjsF = juesefunctionBO.sqlQuery("select jf.juesename,jf.functioncode,f.funcname from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode");
			//��listת��Ϊ FunctionDTOS��
//			����list<Map>��
			Iterator itjsF = listjsF.iterator();
			Map _map=null;
			FunctionDTO mDTO;
			while(itjsF.hasNext()){
				_map=(Map)itjsF.next();
				baseUserContext.addFunction((String)_map.get("FUNCTIONCODE"));
			}
			
			
//			����ϵͳ���ã�
			SystemconfigBO sysBO = new SystemconfigBO();
			List listsys =sysBO.sqlQuery("select functioncode,functionvalue  from systemconfig");
			//����list<Map>��
			Iterator itsys = listsys.iterator();
			Map _mapsys=null;
			while(itsys.hasNext()){
				_mapsys=(Map)itsys.next();
				baseUserContext.addSystemconfig((String)_mapsys.get("FUNCTIONCODE"),(String)_mapsys.get("FUNCTIONVALUE"));
			}
			
			
		}
		
		
		

		

		
		
		return baseUserContext;
		
	}
	

}
