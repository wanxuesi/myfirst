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
		//List list = userBO.tuplesQuery("from UserDTO user,DanweiDTO danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")");
		
//经常性的登陆异常报userBO.tuplesQuery异常，改写为sqlQuery	
		String sqlQuery = " select user.ID,user.XM,user.KL,user.NX,user.WX,user.OTHERFLAG,user.DATESTART,user.DATEEND,user.DWID,danwei.PARENT,danwei.NAME from  user, danwei where user.dwId=danwei.id and user.xm='"+xm+"' and (user.kl='"+kl+"'"+tmpsql+")";
//		System.out.println(sqlQuery);
		List list = userBO.sqlQuery(sqlQuery);
		
		
		if(list.size()==0){
//			 判断用户名或密码是否正确
			
			throw new BSWException("用户名或密码不正确，请重新登录！");
			
		}
		Iterator it = list.iterator();
		Map queryMap=null;
		if(it.hasNext()){
//			------以下生成用户环境对象------------
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
			
			//如果是一级部门，那么具体班组就为“”，然后将所有的子班组放入到map中去。
			Integer dwParent  = (Integer)queryMap.get("PARENT");
			if(dwParent==0){
				baseUserContext.setDwId((Integer)queryMap.get("DWID"));//一级别部门ID
				baseUserContext.setDwname((String)queryMap.get("NAME"));//一级别部门名称
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
				baseUserContext.setBanzu((String)queryMap.get("NAME"));
			}
			//如果是公司本部(先忽略)
			//加载权限
			String juesename = (String)queryMap.get("OTHERFLAG");
			JuesefunctionBO juesefunctionBO = new JuesefunctionBO();
			List listjsF = juesefunctionBO.sqlQuery("select jf.juesename,jf.functioncode,f.funcname from Juesefunction jf,function f where jf.juesename='"+juesename+"' and jf.functioncode=f.functioncode");
			//将list转换为 FunctionDTOS；
//			解析list<Map>；
			Iterator itjsF = listjsF.iterator();
			Map _map=null;
			FunctionDTO mDTO;
			while(itjsF.hasNext()){
				_map=(Map)itjsF.next();
				baseUserContext.addFunction((String)_map.get("FUNCTIONCODE"));
			}
			
			
//			加入系统配置；
			SystemconfigBO sysBO = new SystemconfigBO();
			List listsys =sysBO.sqlQuery("select functioncode,functionvalue  from systemconfig");
			//解析list<Map>；
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
