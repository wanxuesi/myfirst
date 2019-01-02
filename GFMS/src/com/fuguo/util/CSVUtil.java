package com.fuguo.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.LsjgBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.LsjgDTO;
/**
 * ����������ļ�ͳһ���浽���ݿ⹤����
 * @author Administrator
 *
 */
public class CSVUtil {

	/**
	 * @param args
	 */
	private BufferedReader bufferedreader = null;
	 private List list = new ArrayList();
	 
	 public CSVUtil(){

	 }
	 public CSVUtil(String filename) throws IOException{

	       bufferedreader = new BufferedReader(new FileReader(filename));
	       String stemp;
	       while((stemp = bufferedreader.readLine()) != null){
	           list.add(stemp);
	       }
	 }
	 public List getList() throws IOException {

	        return list;
	 }
	 // �õ�csv�ļ�������
	 public int getRowNum(){

	     return list.size();
	 }

	    //�õ�csv�ļ�������
	 public int getColNum(){

	       if(!list.toString().equals("[]")) {
	      
	         //csv�ļ��У�ÿ��֮�������','���ָ���
	            if(list.get(0).toString().contains(",")) { 
	                return list.get(0).toString().split(",").length;
	            }else if(list.get(0).toString().trim().length() != 0) {
	                return 1;
	            }else{
	                return 0;
	            }
	       }else{
	            return 0;
	        }
	 }
	 
	    //ȡ��ָ���е�ֵ
	 public String getRow(int index) {

	     if (this.list.size() != 0)
	      return (String) list.get(index);
	     else                      
	      return null;
	 }

	 //ȡ��ָ���е�ֵ
	 public String getCol(int index){

	       if (this.getColNum() == 0){
	                return null;
	       }
	      
	       StringBuffer scol = new StringBuffer();
	       String temp = null;
	       int colnum = this.getColNum();
	     
	       if (colnum > 1){
	          for (Iterator it = list.iterator(); it.hasNext();) {
	             temp = it.next().toString();
	             scol = scol.append(temp.split(",")[index] + ",");
	          }
	       }else{
	          for (Iterator it = list.iterator(); it.hasNext();) {
	            temp = it.next().toString();
	            scol = scol.append(temp + ",");
	          }
	       }
	       String str=new String(scol.toString());
	       str = str.substring(0, str.length() - 1);
	       return str;
	 }

	 //ȡ��ָ���У�ָ���е�ֵ
	 public String getString(int row, int col) {

	        String temp = null;
	        int colnum = this.getColNum();
	        if(colnum > 1){
	            temp = list.get(row).toString().split(",")[col];
	        }else if(colnum == 1) {
	            temp = list.get(row).toString();
	        }else{
	            temp = null;
	        }
	            return temp;
	 }


	 public void CsvClose() throws IOException {
	     this.bufferedreader.close();
	 }

	 public void run(String zqdm,String filename) throws BSWException, IOException {
	  
		 GpmcBO gBO = new GpmcBO();
		 GpmcDTO gDTO = new GpmcDTO();
		 gDTO.setId(zqdm);
		 gDTO = gBO.query(gDTO);
		 String zqmc = gDTO.getZqmc();
		 if(zqmc==null||zqmc.equals("")){
			 throw new BSWException("ͨ��֤ȯ�����ȡ��Ʊ����ʧ�ܣ���鿴����Ʊ����ά�����У��Ƿ��Ѿ���Ӹù�Ʊ��Ϣ��"); 
		 } 
	  CSVUtil cu = new CSVUtil(filename);
	  String dateString;
	  String openString;
	  String highString;
	  String lowString;
	  String closeString;
	  String volumeString;
	  String adjcloseString;
	  Date date=new Date();
	  double open;
	  double high;
	  double low;
	  double close;
	  double volume;
	  double adjclose;
	  LsjgDTO lsjgDTO=null;
	  LsjgBO lsjgBO=new LsjgBO();
	  SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	     for(int i=1;i<cu.getRowNum();i++){
	    	 
	    	 //�����в�����
	           dateString  = cu.getString(i,0);//�õ���i��.��1�е�����.
	           try {
				date = sdf.parse(dateString);
			} catch (ParseException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
	           openString  = cu.getString(i,1);//�õ���i��.��2�е�����.
	           open = Double.valueOf(openString);
	           highString  = cu.getString(i,2);//�õ���i��.��3�е�����.
	           high = Double.valueOf(highString);
	           lowString  = cu.getString(i,3);//�õ���i��.��4�е�����.
	           low = Double.valueOf(lowString);
	           closeString = cu.getString(i,4);//�õ���i��.��5�е�����.
	           close = Double.valueOf(closeString);
	           volumeString =cu.getString(i,5);//�õ���i��.��6�е�����.
	           volume = Double.valueOf(volumeString);
	           adjcloseString =cu.getString(i,6);//�õ���i��.��7�е�����.
	           adjclose = Double.valueOf(adjcloseString);
	           
	           //System.out.println("Date:"+dateString+"   ���̼ۣ�"+closeString);
	           lsjgDTO=new LsjgDTO();
	           //֤ȯ���ƣ�֤ȯ���룻
	           //lsjgDTO.setId(i+100);
	           lsjgDTO.setZqdm(zqdm);
	           lsjgDTO.setZqmc(zqmc);
	           lsjgDTO.setDate(date);
	           
	           lsjgDTO.setOpen(open);
	           lsjgDTO.setHigh(high);
	           lsjgDTO.setLow(low);
	           lsjgDTO.setClose(close);
	           lsjgDTO.setVolume(volume);
	           lsjgDTO.setAdjclose(adjclose);
	           java.sql.Date dateSql = new java.sql.Date(date.getTime()); 
	           //�����ݱ��浽���ݿ���
	           try {
	        	//*****��Ҫͨ�����ں�֤ȯ������ң�����Ѿ����ڣ�����Ҫ���
	        	   String sql = "select * from lsjg where zqdm='"+zqdm+"'and date(date)='"+dateSql+"'";
	        	   System.out.println(sql);
	        	   List listLsjg = lsjgBO.sqlQuery(sql,LsjgDTO.class);  
	        	   if(listLsjg.size()<1){
	        		   lsjgBO.add(lsjgDTO);
	        	   }
	        	   
	        	   
	        	   
				
			} catch (BSWException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
	     }
	     cu.CsvClose();
	     System.out.println("���ݱ�����ϣ�");
	 }

	
	 
	 public static void main(String[] args) throws IOException {

		  CSVUtil test = new CSVUtil();
		   String path="";
		   String zqdm="";
		   String zqmc=""; 
		   
		   zqdm="2582";  zqmc="������";
		   
		  
		   
//		   zqdm="600547";  zqmc="ɽ���ƽ�";
		   
		   
		   
		   path = "C:/"+zqmc+".csv";
		   try {
			test.run(zqdm,path);
		} catch (BSWException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}


	 }
}
