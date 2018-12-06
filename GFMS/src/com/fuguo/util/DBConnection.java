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
 * @描述:数据库连接类
 * @单位:东瑞电力
 * @邮箱:wanxuesi@163.com
 * @作者:万学思
 *
 */
public class DBConnection {

	/**
	 * 灭有使用连接池
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
			// TODO 自动生成 catch 块
			//e.printStackTrace();
			throw new BSWException("数据库发生异常！"+e);
		} catch (IllegalAccessException e) {
			// TODO 自动生成 catch 块
			//e.printStackTrace();
			throw new BSWException("数据库发生异常！"+e);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成 catch 块
			throw new BSWException("数据库驱动发生异常！"+e);
		} catch (SQLException e) {
			// TODO 自动生成 catch 块
			//e.printStackTrace();
			throw new BSWException("数据库连接发生异常！"+e);
			
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
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO 自动生成方法存根
//		String txrqStr="2008-1-16 14:46:58";
//		
//		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   
//		try {
//			Date   txrq   =   sdf.parse(txrqStr);
//			//System.out.println(txrq);//成功转换为date了
//			//将Date装换为TimeStamp类型；
//			//先装换为int类型
//			long txrqLong =txrq.getTime();
//			Timestamp t =new Timestamp(txrqLong);
//			System.out.println(t);
//		} catch (ParseException e) {
//			// TODO 自动生成 catch 块
//			e.printStackTrace();
//		}
		
	}

}
