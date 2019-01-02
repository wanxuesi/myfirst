package com.fuguo.bo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.dto.DanweiDTO;
import com.fuguo.dto.FunctionDTO;
import com.fuguo.dto.JuesefunctionDTO;
import com.fuguo.dto.SystemconfigDTO;
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
		List list = userBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")");
		
//�����Եĵ�½�쳣��userBO.tuplesQuery�쳣����дΪsqlQuery	
//		String sqlQuery = " select * from  user, danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")";
//		System.out.println(sqlQuery);
//		List list = userBO.sqlQuery(sqlQuery);
		
		
		if(list.size()==0){
//			 �ж��û����������Ƿ���ȷ
			
			throw new BSWException("�û��������벻��ȷ�������µ�¼��");
			
		}
		Iterator it = list.iterator();
		Object[] tuple2;
		
		if(it.hasNext()){
//			------���������û���������------------
			baseUserContext = new BaseUserContext();
			tuple2=(Object[])it.next();		
			UserDTO userDTOs = (UserDTO)tuple2[0];
			DanweiDTO dwbmDTO=(DanweiDTO)tuple2[1];
			
			
			baseUserContext.setId(userDTOs.getId());
			
			
			baseUserContext.setXm(userDTOs.getXm());
			baseUserContext.setKl(userDTOs.getKl());
			baseUserContext.setNx(userDTOs.getNx());
			baseUserContext.setWx(userDTOs.getWx());
			baseUserContext.setJuese(userDTOs.getOtherflag());
			baseUserContext.setDatestart(userDTOs.getDatestart());
			baseUserContext.setDateend(userDTOs.getDateend());
			
			//�����һ�����ţ���ô��������Ϊ������Ȼ�����е��Ӱ�����뵽map��ȥ��
			Integer dwParent  = dwbmDTO.getParent();
			if(dwParent==0){
				baseUserContext.setDwId(userDTOs.getDwId());//һ������ID
				baseUserContext.setDwname(dwbmDTO.getName());//һ����������
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
				baseUserContext.setBanzu(dwbmDTO.getName());
			}
			//����ǹ�˾����(�Ⱥ���)
			//����Ȩ��
			String juesename = userDTOs.getOtherflag();
			JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
			List listjsF = juesefunctionBO.sqlQuery("select jf.functioncode from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode", JuesefunctionDTO.class);
			//��listת��Ϊ FunctionDTOS��
//			����list<Map>��
			Iterator itjsF = listjsF.iterator();
			JuesefunctionDTO juesefunctionDTO=null;
			FunctionDTO mDTO;
			while(itjsF.hasNext()){
				juesefunctionDTO=(JuesefunctionDTO)itjsF.next();
				baseUserContext.addFunction(juesefunctionDTO.getFunctioncode());
			}
			
			
//			����ϵͳ���ã�
			SystemconfigBO sysBO = new SystemconfigBO();
			List listsys =sysBO.sqlQuery("select functioncode,functionvalue  from systemconfig", SystemconfigDTO.class);
			//����list<Map>��
			Iterator itsys = listsys.iterator();
			SystemconfigDTO systemconfigDTO=null;
			while(itsys.hasNext()){
				systemconfigDTO=(SystemconfigDTO)itsys.next();
				baseUserContext.addSystemconfig(systemconfigDTO.getFunctioncode(),systemconfigDTO.getFunctionvalue());
			}
			
			
		}
		
		
		

		

		
		
		return baseUserContext;
		
	}
	

}
