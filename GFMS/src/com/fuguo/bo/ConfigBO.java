package com.fuguo.bo;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.ConfigDTO;
import com.fuguo.po.ConfigPO;

public class ConfigBO {
	ConfigPO configPO = null;


	/**
	 * ��Ӷ���
	 * @param user0DTO
	 * @return
	 * @throws BSWException
	 */
	public ConfigDTO add(ConfigDTO configDTO) throws BSWException{
		
		configPO=new ConfigPO();		
		
		return (ConfigDTO)configPO.add(configDTO);
	}

	/**
	 * ��ѯĳ��¼����ϸ��Ϣ
	 * @param config0DTO
	 * @return
	 * @throws BSWException
	 */
	public ConfigDTO query(ConfigDTO configDTO) throws BSWException{
		configPO=new ConfigPO();
		return (ConfigDTO)configPO.query(configDTO);
	}


	/**
	 * ���¶���
	 * @param config0DTO
	 * @throws BSWException
	 */
	public void update(ConfigDTO configDTO) throws BSWException{	
		configPO=new ConfigPO();
		configPO.update(configDTO);
	}


	/**
	 * ɾ��һ������
	 * @param configDTO
	 * @throws BSWException
	 */
	public void delete(ConfigDTO configDTO) throws BSWException{
		configPO=new ConfigPO();
		configPO.delete(configDTO);
	}


	/**
	 * ɾ���������
	 * @param configDTOS
	 * @throws BSWException
	 */
	public void delete(ConfigDTO[] configDTOS) throws BSWException{
		configPO=new ConfigPO();
		configPO.delete(configDTOS);
	}
	/**
	 * ����������Ϣ
	 * @return
	 * @throws BSWException
	 */
	



	
	
	public static void main(String[] args)throws BSWException{
		


		
	}


}
