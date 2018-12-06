//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.0/xslt/JavaClass.xsl

package com.fuguo.action.jilu;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import bsw.base.BaseAction;
import bsw.fwk.BaseUserContext;
import bsw.tools.exception.BSWException;

import com.fuguo.bo.GpmcBO;
import com.fuguo.bo.JiluBO;
import com.fuguo.dto.GpmcDTO;
import com.fuguo.dto.JiluDTO;



/** 
 * MyEclipse Struts
 * Creation date: 01-10-2008
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 */
public class JiluUploadAction extends BaseAction {


	@Override
	public void myexecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
		int id  =baseUserContext.getId();
		String idStr = Integer.toString(id);
		String xm = baseUserContext.getXm();
		String nx  =baseUserContext.getNx();//�˺ţ�
		  			    
		  			    
		    		
//		ʵ��������bean
		com.jspsmart.upload.SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
		JspFactory _jaspFactory = JspFactory.getDefaultFactory();
		PageContext pageContext  =_jaspFactory.getPageContext(this.getServlet(),request,response,null,true,8192,true);
		//		��ʼ��
		mySmartUpload.initialize(pageContext); 
//		�������ص����ֵ
		mySmartUpload.setMaxFileSize(500 * 1024*1024);
//		�����ļ�
		mySmartUpload.upload();
//		ѭ��ȡ���������ص��ļ�
		for (int i=0;i<mySmartUpload.getFiles().getCount();i++){
//		ȡ�����ص��ļ�
		com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(i);
		if (!myFile.isMissing())
		{
//		ȡ�����ص��ļ����ļ���
		String myFileName=myFile.getFileName();
//		ȡ�ò�����׺���ļ���
		String suffix=myFileName.substring(0,myFileName.lastIndexOf('.'));
//		ȡ�ú�׺��
		String ext= mySmartUpload.getFiles().getFile(0).getFileExt(); 
//		ȡ���ļ��Ĵ�С 
		int fileSize=myFile.getSize();
//		����·��
		String aa=request.getRealPath("/")+"jsp\\";
		String trace=aa+myFileName;
//		ȡ�ñ�Ĳ���

//		String otherFlag=(String)mySmartUpload.getRequest().getParameter("otherFlag");

//		���ļ������ڷ������� 
		myFile.saveAs(trace,mySmartUpload.SAVE_PHYSICAL);

				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(trace));
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				int lastRowNum = sheet.getLastRowNum();
				HSSFRow row;
				HSSFCell cell;
				SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
				JiluBO mBO =new JiluBO();
				GpmcBO gBO = new GpmcBO();
				row = sheet.getRow(0);//��1�У�
				//int firstCellNum = row.getFirstCellNum();
				
				JiluDTO tDTO;
				GpmcDTO gDTO;
				 int cellType =0;
				 int cellTypeabc =0;
				 
		          
			for(int h=0;h<2;h++){
				
				for(int j =1;j<=lastRowNum;j++){
					
					row = sheet.getRow(j);
					System.out.println(j);
					
				
					tDTO = new JiluDTO();
					tDTO.setTimeid(Long.toString(new java.util.Date().getTime())+j);//��֤�˲��ظ�
					
					
					cell = row.getCell((short)0);
					if(cell==null){
						throw new BSWException("����д��"+j+"�еķ������ڣ�");
					}
					String yymmddStr="";
					cellType = row.getCell((short)0).getCellType();
				    if(cellType==0){
				    	
				    	   Double yymmdd  = row.getCell((short)0).getNumericCellValue();
						   int yymmddInt = yymmdd.intValue();
						   if(yymmddInt<1980){
						    	 throw new BSWException("����д��"+j+"�еķ������ڣ�");
						     }
						   yymmddStr = String.valueOf(yymmddInt);
				    }else{   
				    	yymmddStr = row.getCell((short)0).getStringCellValue();//��������
				    	if(yymmddStr.trim().length()<2){
				    		throw new BSWException("����д��"+j+"�еķ������ڣ�");
				    	}
				   }
				    
				    cell = row.getCell((short)1);
					if(cell==null){
						throw new BSWException("����д��"+j+"�еĳɽ�ʱ�䣡");
					}
					String hhmmssStr="";
					cellType = row.getCell((short)1).getCellType();
				    if(cellType==0){
				    	
				    	   java.util.Date hhmmssDate =row.getCell((short)1).getDateCellValue();
						   SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("HH:mm:ss");
						   hhmmssStr = sdfTime.format(hhmmssDate);
				    }else{   
				    	hhmmssStr = row.getCell((short)1).getStringCellValue();//�ɽ�ʱ��
				    	if(hhmmssStr.trim().length()<2){
				    	 throw new BSWException("����д��"+j+"�еĳɽ�ʱ�䣡");
				    	}
				   } 
					
				   
				   SimpleDateFormat   sdfTime2   =   new   SimpleDateFormat("yyyyMMdd HH:mm");
				   
				   String jysjStr=yymmddStr+" "+hhmmssStr;
				   java.util.Date jysjDate = sdfTime2.parse(jysjStr);
				   tDTO.setJysj(jysjDate);
				   
				  //֤ȯ����
				   cell = row.getCell((short)3);
				   if(cell==null){
						throw new BSWException("����д��"+j+"�е�֤ȯ���룡");
					}
				   String zqdmStr="";
				   cellType = row.getCell((short)3).getCellType();
				    if(cellType==0){
				    	
				    		Double zqdmDouble  = row.getCell((short)3).getNumericCellValue();
						   int zqdmInt = zqdmDouble.intValue();
						   
						   if(zqdmInt<1){
						    	 throw new BSWException("����д��"+j+"�е�֤ȯ���룡");
						     }
						   zqdmStr = String.valueOf(zqdmInt);
				    }else{   
				    	zqdmStr = row.getCell((short)3).getStringCellValue();//֤ȯ����
				    	if(zqdmStr.trim().length()<1){
				    		throw new BSWException("����д��"+j+"�е�֤ȯ���룡");
				    	}
				   }
				   
				   
				   tDTO.setZqdm(zqdmStr);
				   //֤ȯ����
				   cell = row.getCell((short)4);
				   if(cell==null){
						throw new BSWException("����д��"+j+"�е�֤ȯ���ƣ�");
					}
				   tDTO.setZqmc(row.getCell((short)4).getStringCellValue());
				   //�����־
				   cell = row.getCell((short)5);
				   if(cell==null){
						throw new BSWException("����д��"+j+"�е������־��");
					}
				   tDTO.setMmflag(row.getCell((short)5).getStringCellValue());
				   
				   //�ɽ��۸�
				   cell = row.getCell((short)6);
				   if(cell==null){
						throw new BSWException("����д��"+j+"�еĳɽ��۸�");
					}
				   
				   tDTO.setCjjg(row.getCell((short)6).getNumericCellValue());
				   
				   
				   
				   //�ɽ�����
				   cell = row.getCell((short)7);
					if(cell==null){
						throw new BSWException("����д��"+j+"�еĳɽ�������");
					}
					int cjslInt=0;
					String cjslStr="";
					cellType = row.getCell((short)7).getCellType();
				    if(cellType==0){
				    	
				    	   Double cjsl  = row.getCell((short)7).getNumericCellValue();
						   cjslInt = cjsl.intValue();
						   //�ֺ��ʱ�򣬳ɽ�����Ϊ��
//						   if(cjslInt==0){
//						    	 throw new BSWException("����д��"+j+"�еĳɽ�������");
//						     }
						   
				    }else{   
				    	cjslStr = row.getCell((short)7).getStringCellValue();//�ɽ�����
				    	if(cjslStr.trim().length()<1){
				    		throw new BSWException("����д��"+j+"�еĳɽ�������");
				    	}
				    	cjslInt = Integer.parseInt(cjslStr);
				   }
				   
				   
				   if(tDTO.getMmflag().equals("����")){
				   		if(cjslInt>0){
				   			cjslInt=cjslInt*(-1);
				   		}					   
				   }
				   
				   tDTO.setCjsl(cjslInt);
				   
				    //�ɽ����
				   
				   cell = row.getCell((short)8);
					if(cell==null){
						throw new BSWException("����д��"+j+"�еĳɽ���");
					}
					double cjje=0;
					String cjjeStr="";
					cellType = row.getCell((short)8).getCellType();
				    if(cellType==0){
				    	
				    	cjje  = row.getCell((short)8).getNumericCellValue();
						   
						   
				    }else{   
				    	cjjeStr = row.getCell((short)8).getStringCellValue();//�ɽ����
				    	if(cjjeStr.trim().length()<1){
				    		throw new BSWException("����д��"+j+"�еĳɽ���");
				    	}
				    	cjje  = Integer.parseInt(cjjeStr);
				   }
				    
				    if(cjje<0){
			    		throw new BSWException("��"+j+"�еĳɽ�����С��0��");
			    	}
				   //-------
				   tDTO.setCjje(cjje);
				   
				    //������
				   cell = row.getCell((short)9);
					if(cell==null){
						throw new BSWException("����д��"+j+"�е������");
					}
					double qsje=0;
					String qsjeStr="";
					cellType = row.getCell((short)9).getCellType();
				    if(cellType==0){
				    	
				    	qsje  = row.getCell((short)9).getNumericCellValue();
						   
						   
				    }else{   
				    	qsjeStr = row.getCell((short)9).getStringCellValue();//������
				    	if(qsjeStr.trim().length()<1){
				    		throw new BSWException("����д��"+j+"�е������");
				    	}
				    	qsje  = Integer.parseInt(qsjeStr);
				   }
				  
				  
				   if(tDTO.getMmflag().equals("����")){
					   if(qsje>0){
						   qsje=qsje*(-1);
					   }
				   
				   }
				   tDTO.setQsje(qsje);
				   
				   
				   //�ʽ��˺�  ��Ϊ�û��Σ�					  	   
				   tDTO.setZjzh(nx);
				    //�ͻ�����--��Ϊ�û�id					  
				   tDTO.setKhdm(idStr);
			       //�ɶ����� ��ΪXM
				   tDTO.setGdmc(xm);	
				   
				   
//				 ��Ҫ����Ʊ���ƺʹ����ʽ��һ�£����ȼ򻯣��������ݿ⣻	
			        Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
					String zqdm=zqdmInteger.toString();
					tDTO.setZqdm(zqdm);
					String zqmc = tDTO.getZqmc();
					zqmc = zqmc.replace(" ","");
					zqmc = zqmc.replace(" ","");
					tDTO.setZqmc(zqmc);	
		 	        //����������
				   //ͨ����Ʊ���룬��ȡ����������
				   gDTO = new GpmcDTO();
				   //֤ȯ������Ҫ�����ʽ--����ת�������ģ��϶��Ǽ����ʽ��
				   gDTO.setZqdm(zqdm);
				   gDTO = gBO.query(gDTO);
				   if(gDTO==null){
					   throw new BSWException("�뵽��Ʊ����ά������ӵ�"+j+"�е�֤ȯ���룬��Ʊ���ƣ�"); 
				   }
				   tDTO.setJysmc(gDTO.getFlag1());   
			      
			        //��ע
				   tDTO.setBz("");  
			      
			       tDTO.setJifl("��Ƶ");	      
			      
			       tDTO.setFlag1("δ����") ;
			       		
			       		
			       		
			       
						
					
					
								      
				   if(h==1){
					 //��һ���Ǽ���ã���h==1��ʱ�򣬲���ӡ�
//						 Ȼ�����
						mBO.add(tDTO);
						System.out.println("Excel��ţ�"+(j+1)+"����ɹ���");//Excel�ڶ��п�ʼ��
				   }
							
						
					
					
				}
					
			}
					
				
				
				

		}
		}


		
		
	}

}

