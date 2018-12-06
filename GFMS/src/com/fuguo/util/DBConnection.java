package com.fuguo.util;

import io.ResourceBundleUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bsw.tools.exception.*;

/**
 * 
 * 
 * @����:���ݿ�������
 * @��λ:�������
 * @����:wanxuesi@163.com
 * @����:��ѧ˼
 *
 */
public class DBConnection {

	/**
	 * ����ʹ�����ӳ�
	 */
	
	public final static String driver=ResourceBundleUtil.bundle.getString("driver");
	public final static String url=ResourceBundleUtil.bundle.getString("url");
	public final static String username=ResourceBundleUtil.bundle.getString("username");	
	public final static String password=ResourceBundleUtil.bundle.getString("password");	
	public static Connection getConnection() throws BSWException{
		Connection conn = null;
	
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
		} catch (InstantiationException e) {
			// TODO �Զ����� catch ��
			//e.printStackTrace();
			throw new BSWException("���ݿⷢ���쳣��"+e);
		} catch (IllegalAccessException e) {
			// TODO �Զ����� catch ��
			//e.printStackTrace();
			throw new BSWException("���ݿⷢ���쳣��"+e);
		} catch (ClassNotFoundException e) {
			// TODO �Զ����� catch ��
			throw new BSWException("���ݿ����������쳣��"+e);
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			//e.printStackTrace();
			throw new BSWException("���ݿ����ӷ����쳣��"+e);
			
		}

		return conn;

	}

	
	public static void close(){
		try {
			ResultSet rs    = null;
			PreparedStatement pstmt=null;
			Connection conn =null;
			if (rs!=null){
				rs.close();
			}

			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
			conn.close();
			}
		} catch (SQLException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO �Զ����ɷ������
//		String txrqStr="2008-1-16 14:46:58";
//		
//		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
//		try {
//			Date   txrq   =   sdf.parse(txrqStr);
//			//System.out.println(txrq);//�ɹ�ת��Ϊdate��
//			//��Dateװ��ΪTimeStamp���ͣ�
//			//��װ��Ϊint����
//			long txrqLong =txrq.getTime();
//			Timestamp t =new Timestamp(txrqLong);
//			System.out.println(t);
//		} catch (ParseException e) {
//			// TODO �Զ����� catch ��
//			e.printStackTrace();
//		}
		
	}

}
