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
		String nx  =baseUserContext.getNx();//账号；
		  			    
		  			    
		    		
//		实例化上载bean
		com.jspsmart.upload.SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
		JspFactory _jaspFactory = JspFactory.getDefaultFactory();
		PageContext pageContext  =_jaspFactory.getPageContext(this.getServlet(),request,response,null,true,8192,true);
		//		初始化
		mySmartUpload.initialize(pageContext); 
//		设置上载的最大值
		mySmartUpload.setMaxFileSize(500 * 1024*1024);
//		上载文件
		mySmartUpload.upload();
//		循环取得所有上载的文件
		for (int i=0;i<mySmartUpload.getFiles().getCount();i++){
//		取得上载的文件
		com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(i);
		if (!myFile.isMissing())
		{
//		取得上载的文件的文件名
		String myFileName=myFile.getFileName();
//		取得不带后缀的文件名
		String suffix=myFileName.substring(0,myFileName.lastIndexOf('.'));
//		取得后缀名
		String ext= mySmartUpload.getFiles().getFile(0).getFileExt(); 
//		取得文件的大小 
		int fileSize=myFile.getSize();
//		保存路径
		String aa=request.getRealPath("/")+"jsp\\";
		String trace=aa+myFileName;
//		取得别的参数

//		String otherFlag=(String)mySmartUpload.getRequest().getParameter("otherFlag");

//		将文件保存在服务器端 
		myFile.saveAs(trace,mySmartUpload.SAVE_PHYSICAL);

				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(trace));
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				int lastRowNum = sheet.getLastRowNum();
				HSSFRow row;
				HSSFCell cell;
				SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
				JiluBO mBO =new JiluBO();
				GpmcBO gBO = new GpmcBO();
				row = sheet.getRow(0);//第1行；
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
					tDTO.setTimeid(Long.toString(new java.util.Date().getTime())+j);//保证了不重复
					
					
					cell = row.getCell((short)0);
					if(cell==null){
						throw new BSWException("请填写第"+j+"行的发生日期！");
					}
					String yymmddStr="";
					cellType = row.getCell((short)0).getCellType();
				    if(cellType==0){
				    	
				    	   Double yymmdd  = row.getCell((short)0).getNumericCellValue();
						   int yymmddInt = yymmdd.intValue();
						   if(yymmddInt<1980){
						    	 throw new BSWException("请填写第"+j+"行的发生日期！");
						     }
						   yymmddStr = String.valueOf(yymmddInt);
				    }else{   
				    	yymmddStr = row.getCell((short)0).getStringCellValue();//发生日期
				    	if(yymmddStr.trim().length()<2){
				    		throw new BSWException("请填写第"+j+"行的发生日期！");
				    	}
				   }
				    
				    cell = row.getCell((short)1);
					if(cell==null){
						throw new BSWException("请填写第"+j+"行的成交时间！");
					}
					String hhmmssStr="";
					cellType = row.getCell((short)1).getCellType();
				    if(cellType==0){
				    	
				    	   java.util.Date hhmmssDate =row.getCell((short)1).getDateCellValue();
						   SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("HH:mm:ss");
						   hhmmssStr = sdfTime.format(hhmmssDate);
				    }else{   
				    	hhmmssStr = row.getCell((short)1).getStringCellValue();//成交时间
				    	if(hhmmssStr.trim().length()<2){
				    	 throw new BSWException("请填写第"+j+"行的成交时间！");
				    	}
				   } 
					
				   
				   SimpleDateFormat   sdfTime2   =   new   SimpleDateFormat("yyyyMMdd HH:mm");
				   
				   String jysjStr=yymmddStr+" "+hhmmssStr;
				   java.util.Date jysjDate = sdfTime2.parse(jysjStr);
				   tDTO.setJysj(jysjDate);
				   
				  //证券代码
				   cell = row.getCell((short)3);
				   if(cell==null){
						throw new BSWException("请填写第"+j+"行的证券代码！");
					}
				   String zqdmStr="";
				   cellType = row.getCell((short)3).getCellType();
				    if(cellType==0){
				    	
				    		Double zqdmDouble  = row.getCell((short)3).getNumericCellValue();
						   int zqdmInt = zqdmDouble.intValue();
						   
						   if(zqdmInt<1){
						    	 throw new BSWException("请填写第"+j+"行的证券代码！");
						     }
						   zqdmStr = String.valueOf(zqdmInt);
				    }else{   
				    	zqdmStr = row.getCell((short)3).getStringCellValue();//证券代码
				    	if(zqdmStr.trim().length()<1){
				    		throw new BSWException("请填写第"+j+"行的证券代码！");
				    	}
				   }
				   
				   
				   tDTO.setZqdm(zqdmStr);
				   //证券名称
				   cell = row.getCell((short)4);
				   if(cell==null){
						throw new BSWException("请填写第"+j+"行的证券名称！");
					}
				   tDTO.setZqmc(row.getCell((short)4).getStringCellValue());
				   //买入标志
				   cell = row.getCell((short)5);
				   if(cell==null){
						throw new BSWException("请填写第"+j+"行的买入标志！");
					}
				   tDTO.setMmflag(row.getCell((short)5).getStringCellValue());
				   
				   //成交价格
				   cell = row.getCell((short)6);
				   if(cell==null){
						throw new BSWException("请填写第"+j+"行的成交价格！");
					}
				   
				   tDTO.setCjjg(row.getCell((short)6).getNumericCellValue());
				   
				   
				   
				   //成交数量
				   cell = row.getCell((short)7);
					if(cell==null){
						throw new BSWException("请填写第"+j+"行的成交数量！");
					}
					int cjslInt=0;
					String cjslStr="";
					cellType = row.getCell((short)7).getCellType();
				    if(cellType==0){
				    	
				    	   Double cjsl  = row.getCell((short)7).getNumericCellValue();
						   cjslInt = cjsl.intValue();
						   //分红的时候，成交数量为零
//						   if(cjslInt==0){
//						    	 throw new BSWException("请填写第"+j+"行的成交数量！");
//						     }
						   
				    }else{   
				    	cjslStr = row.getCell((short)7).getStringCellValue();//成交数量
				    	if(cjslStr.trim().length()<1){
				    		throw new BSWException("请填写第"+j+"行的成交数量！");
				    	}
				    	cjslInt = Integer.parseInt(cjslStr);
				   }
				   
				   
				   if(tDTO.getMmflag().equals("卖出")){
				   		if(cjslInt>0){
				   			cjslInt=cjslInt*(-1);
				   		}					   
				   }
				   
				   tDTO.setCjsl(cjslInt);
				   
				    //成交金额
				   
				   cell = row.getCell((short)8);
					if(cell==null){
						throw new BSWException("请填写第"+j+"行的成交金额！");
					}
					double cjje=0;
					String cjjeStr="";
					cellType = row.getCell((short)8).getCellType();
				    if(cellType==0){
				    	
				    	cjje  = row.getCell((short)8).getNumericCellValue();
						   
						   
				    }else{   
				    	cjjeStr = row.getCell((short)8).getStringCellValue();//成交金额
				    	if(cjjeStr.trim().length()<1){
				    		throw new BSWException("请填写第"+j+"行的成交金额！");
				    	}
				    	cjje  = Integer.parseInt(cjjeStr);
				   }
				    
				    if(cjje<0){
			    		throw new BSWException("第"+j+"行的成交金额不能小于0！");
			    	}
				   //-------
				   tDTO.setCjje(cjje);
				   
				    //清算金额
				   cell = row.getCell((short)9);
					if(cell==null){
						throw new BSWException("请填写第"+j+"行的清算金额！");
					}
					double qsje=0;
					String qsjeStr="";
					cellType = row.getCell((short)9).getCellType();
				    if(cellType==0){
				    	
				    	qsje  = row.getCell((short)9).getNumericCellValue();
						   
						   
				    }else{   
				    	qsjeStr = row.getCell((short)9).getStringCellValue();//清算金额
				    	if(qsjeStr.trim().length()<1){
				    		throw new BSWException("请填写第"+j+"行的清算金额！");
				    	}
				    	qsje  = Integer.parseInt(qsjeStr);
				   }
				  
				  
				   if(tDTO.getMmflag().equals("买入")){
					   if(qsje>0){
						   qsje=qsje*(-1);
					   }
				   
				   }
				   tDTO.setQsje(qsje);
				   
				   
				   //资金账号  即为用户ＮＸ					  	   
				   tDTO.setZjzh(nx);
				    //客户代码--即为用户id					  
				   tDTO.setKhdm(idStr);
			       //股东名称 即为XM
				   tDTO.setGdmc(xm);	
				   
				   
//				 需要将股票名称和代码格式化一下，极度简化，存入数据库；	
			        Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
					String zqdm=zqdmInteger.toString();
					tDTO.setZqdm(zqdm);
					String zqmc = tDTO.getZqmc();
					zqmc = zqmc.replace(" ","");
					zqmc = zqmc.replace(" ","");
					tDTO.setZqmc(zqmc);	
		 	        //交易所名称
				   //通过股票代码，获取交易所名称
				   gDTO = new GpmcDTO();
				   //证券代码需要极简格式--数字转化过来的，肯定是极简格式；
				   gDTO.setZqdm(zqdm);
				   gDTO = gBO.query(gDTO);
				   if(gDTO==null){
					   throw new BSWException("请到股票名称维护中添加第"+j+"行的证券代码，股票名称！"); 
				   }
				   tDTO.setJysmc(gDTO.getFlag1());   
			      
			        //备注
				   tDTO.setBz("");  
			      
			       tDTO.setJifl("高频");	      
			      
			       tDTO.setFlag1("未处理") ;
			       		
			       		
			       		
			       
						
					
					
								      
				   if(h==1){
					 //第一遍是检测用，当h==1的时候，才添加。
//						 然后添加
						mBO.add(tDTO);
						System.out.println("Excel编号："+(j+1)+"插入成功！");//Excel第二行开始。
				   }
							
						
					
					
				}
					
			}
					
				
				
				

		}
		}


		
		
	}

}

