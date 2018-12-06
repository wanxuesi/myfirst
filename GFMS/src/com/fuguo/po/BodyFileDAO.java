package com.fuguo.po;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bsw.tools.exception.BSWException;

import com.fuguo.dto.BodyFileDTO;
import com.fuguo.util.DBConnection;



public class BodyFileDAO {
	public Connection conn			=null;
	public PreparedStatement pstmt	=null;
	public ResultSet rs				=null;
	
	

	
	
	
	public BodyFileDTO bodyFileQuery(String sql)throws BSWException{
		conn = DBConnection.getConnection();
		BodyFileDTO d=new BodyFileDTO();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				d=new BodyFileDTO();
				
				d.setFileName(rs.getString("fileName"));//只需要他就可以了
				d.setOtherFlag(rs.getString("otherFlag"));

			}
		} catch (SQLException e) {
			throw new BSWException("查找失败"+e.getMessage());
		}finally{
			DBConnection.close();
		}
		return d;
	}
	
	
public void bodyFileDelete(String otherFlag)throws BSWException{
		conn = DBConnection.getConnection();
		String sql="delete from bodyfile where otherFlag=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,otherFlag);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new BSWException("删除失败"+e.getMessage());
		}finally{
			DBConnection.close();
		}
	}



}
