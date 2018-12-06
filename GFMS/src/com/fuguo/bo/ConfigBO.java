package com.fuguo.bo;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.ConfigDTO;
import com.fuguo.po.ConfigPO;

public class ConfigBO {
	ConfigPO configPO = null;


	/**
	 * 添加对象
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public ConfigDTO add(ConfigDTO configDTO) throws BSWException{
		
		configPO=new ConfigPO();		
		
		return (ConfigDTO)configPO.add(configDTO);
	}

	/**
	 * 查询某记录的详细信息
	 * @param config0DTO
	 * @return
	 * @throws BSWException
	 */
	public ConfigDTO query(ConfigDTO configDTO) throws BSWException{
		configPO=new ConfigPO();
		return (ConfigDTO)configPO.query(configDTO);
	}


	/**
	 * 更新对象
	 * @param config0DTO
	 * @throws BSWException
	 */
	public void update(ConfigDTO configDTO) throws BSWException{	
		configPO=new ConfigPO();
		configPO.update(configDTO);
	}


	/**
	 * 删除一个对象
	 * @param configDTO
	 * @throws BSWException
	 */
	public void delete(ConfigDTO configDTO) throws BSWException{
		configPO=new ConfigPO();
		configPO.delete(configDTO);
	}


	/**
	 * 删除多个对象
	 * @param configDTOS
	 * @throws BSWException
	 */
	public void delete(ConfigDTO[] configDTOS) throws BSWException{
		configPO=new ConfigPO();
		configPO.delete(configDTOS);
	}
	/**
	 * 查找所有信息
	 * @return
	 * @throws BSWException
	 */
	



	
	
	public static void main(String[] args)throws BSWException{
		


		
	}


}
