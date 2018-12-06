package com.fuguo.bo;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.BodyFileDTO;
import com.fuguo.po.BodyFileDAO;
import com.fuguo.util.DBConnection;

public class BodyFileBO {

	/**
	 * ���������ص�������
	 * @param printObject
	 * @param output
	 * @return excel���
	 * @throws Exception
	 */
	private static Connection conn = null;

	private static PreparedStatement pstmt = null;

	private static ResultSet rs = null;
	/**
	 *  ������������ļ�
	 * @param bodyFileObject
	 * @param output
	 * @throws Exception
	 */
	public void printToFile(BodyFileDTO bodyFileObject, OutputStream output) throws Exception {		
				
		String otherFlag = bodyFileObject.getOtherFlag();
		//�����ݿ��л�ȡ������¼
		InputStream is=null;
		String sql=null;
		byte[] buffer =new byte[1444];

		conn = DBConnection.getConnection();
		sql = "select body from bodyfile where otherFlag=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, otherFlag);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				is=rs.getBinaryStream("body");   
				  int   bytesRead   =   0;   
				  while   ((bytesRead = is.read(buffer))!=-1){   
					  output.write(buffer,0,bytesRead);   
				  }   
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}finally{
			  
				  /*   
				  *   Clean   up.   
				  */     
				  if(is   !=   null)   {   
				  is.close();   
				  }   
				  if(output   !=   null)   {   
					  output.flush();   
					  output.close();   
				  }
			DBConnection.close();
		}
		
	}
	
	
	
	
	/**
	 * ��������ȡ�ļ�������
	 * @param otherFlag
	 * @return
	 * @throws Exception
	 */
	public String getFileName(String otherFlag) throws Exception {
		BodyFileDAO b =new BodyFileDAO();
		BodyFileDTO bDTO =  b.bodyFileQuery("select otherflag,filename from bodyfile where otherflag='"+otherFlag+"'");
		return bDTO.getFileName();
	}
	
	public void bodyFileDelete(String otherFlag)throws BSWException{
		BodyFileDAO b =new BodyFileDAO();
		b.bodyFileDelete(otherFlag);
		
	}

}
