package com.fuguo.bo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.fuguo.dto.GpylDTO;
import com.fuguo.util.DateUtil;

public class GpylPrintNowBO {
	SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
	
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	
	HSSFRow row;
	HSSFCell cell;
	
	
	
	
	public void setMyCellValue(int column,String value,HSSFCellStyle style){
		cell = row.createCell((short)column);
		//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}
	/**
	 * ��������ӡ��������
	 * @param printObject
	 * @param output
	 * @return excel���
	 * @throws Exception
	 */
	public void printToFile(int[] queryIds,String trace, OutputStream output) throws Exception {		
		workbook = new HSSFWorkbook(new FileInputStream(trace));
		sheet = workbook.getSheetAt(0);
		HSSFCellStyle style = workbook.createCellStyle();
		 
		 HSSFFont font = workbook.createFont();		
		font.setFontHeight((short) 180);
		font.setFontName("����");		
		// ����excel��������
		style.setFont(font);
		style.setWrapText(true);
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	
	
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
	
	
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	
	
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		GpylBO mBO = new GpylBO();
		GpylDTO mDTO;
		int indexId=0;
		for(int i=0;i<queryIds.length;i++){
			mDTO=new GpylDTO();
			mDTO.setId(queryIds[i]);
			mDTO=mBO.query(mDTO);//����id��ȡ�����dayplanDTO;
			if(!mDTO.getLx().equals("��¼")){
				continue;
			}
			
//			���뵽excel�У�
			row =sheet.createRow(indexId+1);
			
			setMyCellValue(0,new Integer(indexId+1).toString(),style);//id
			
			String _jlrq=mDTO.getFlag2();		
			
			Date jlrqDate  = sdf.parse(_jlrq);
			String jjlrqStr=sdf.format(jlrqDate);
			
			
			
			setMyCellValue(1,jjlrqStr,style);//��¼����
			
			
			
			setMyCellValue(2,mDTO.getLx(),style);
			
			setMyCellValue(3,mDTO.getTitle(),style);
			
			indexId++;
		}
		
		
		
		
			
			
			
			
			
			

		
		try {
//			FileOutputStream fOut = new FileOutputStream("d:/"
//					+ printObject.getCzrw() + ".xls");
			// ����Ӧ��Excel ����������
			workbook.write(output);			
			// ˢ�»��棬�����ļ��������ȫ
			output.flush();
			// �����������ر��ļ�
			output.close();			
		} catch (FileNotFoundException e) {
			// TODO �Զ����� catch ��
			throw new Exception("�����ļ�ʧ�ܣ�" + e);
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			throw new Exception("�����ļ�ʧ�ܣ�" + e);
		}
	}


}
