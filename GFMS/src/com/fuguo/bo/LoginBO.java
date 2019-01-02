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
	 * 验证用户名和密码；返回一个完整的UserDto
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
//	关联对象test1	很强
		List list = userBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")");
		
//经常性的登陆异常报userBO.tuplesQuery异常，改写为sqlQuery	
//		String sqlQuery = " select * from  user, danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")";
//		System.out.println(sqlQuery);
//		List list = userBO.sqlQuery(sqlQuery);
		
		
		if(list.size()==0){
//			 判断用户名或密码是否正确
			
			throw new BSWException("用户名或密码不正确，请重新登录！");
			
		}
		Iterator it = list.iterator();
		Object[] tuple2;
		
		if(it.hasNext()){
//			------以下生成用户环境对象------------
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
			
			//如果是一级部门，那么具体班组就为“”，然后将所有的子班组放入到map中去。
			Integer dwParent  = dwbmDTO.getParent();
			if(dwParent==0){
				baseUserContext.setDwId(userDTOs.getDwId());//一级别部门ID
				baseUserContext.setDwname(dwbmDTO.getName());//一级别部门名称
				baseUserContext.setBanzu("");
				
			}
//			如果是二级班组，那么具体班组就为-dwDTO.getName()，班组map为空。
			if(dwParent>0){
				//找出他的一级部门id，和一级部门name；
				DanweiBO danweiBO = new DanweiBO();
				DanweiDTO _dDTO=new DanweiDTO();
				_dDTO.setId(dwParent);
				_dDTO = danweiBO.query(_dDTO);
				//放入一级部门id和名称
				baseUserContext.setDwId(_dDTO.getId());//一级别部门ID
				baseUserContext.setDwname(_dDTO.getName());//一级别部门名称
				baseUserContext.setBanzu(dwbmDTO.getName());
			}
			//如果是公司本部(先忽略)
			//加载权限
			String juesename = userDTOs.getOtherflag();
			JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
			List listjsF = juesefunctionBO.sqlQuery("select jf.functioncode from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode", JuesefunctionDTO.class);
			//将list转换为 FunctionDTOS；
//			解析list<Map>；
			Iterator itjsF = listjsF.iterator();
			JuesefunctionDTO juesefunctionDTO=null;
			FunctionDTO mDTO;
			while(itjsF.hasNext()){
				juesefunctionDTO=(JuesefunctionDTO)itjsF.next();
				baseUserContext.addFunction(juesefunctionDTO.getFunctioncode());
			}
			
			
//			加入系统配置；
			SystemconfigBO sysBO = new SystemconfigBO();
			List listsys =sysBO.sqlQuery("select functioncode,functionvalue  from systemconfig", SystemconfigDTO.class);
			//解析list<Map>；
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
