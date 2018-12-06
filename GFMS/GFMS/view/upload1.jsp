<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.apache.poi.hssf.usermodel.*"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="com.fuguo.bo.JiluBO"%>
<%@ page import="com.fuguo.dto.JiluDTO"%>
<%@ page import="com.fuguo.util.DateUtil"%>
<%@ page import="com.jspsmart.upload.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="bsw.fwk.BaseUserContext"%>

<%@ page import="bsw.tools.exception.BSWException"%>


<%
	BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
	int id  =baseUserContext.getId();
	String idStr = Integer.toString(id);
	String xm = baseUserContext.getXm();
	String nx  =baseUserContext.getNx();	
		
    //String juese = baseUserContext.getJuese();
//实例化上载bean
com.jspsmart.upload.SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
//初始化
mySmartUpload.initialize(pageContext); 
//设置上载的最大值
mySmartUpload.setMaxFileSize(500 * 1024*1024);
//上载文件
mySmartUpload.upload();
//循环取得所有上载的文件
for (int i=0;i<mySmartUpload.getFiles().getCount();i++){
//取得上载的文件
com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(i);
if (!myFile.isMissing())
{
//取得上载的文件的文件名
String myFileName=myFile.getFileName();
//取得不带后缀的文件名
String suffix=myFileName.substring(0,myFileName.lastIndexOf('.'));
//取得后缀名
String ext= mySmartUpload.getFiles().getFile(0).getFileExt(); 
//取得文件的大小 
int fileSize=myFile.getSize();
//保存路径
String aa=getServletContext().getRealPath("/")+"jsp\\";
String trace=aa+myFileName;
//取得别的参数

//String otherFlag=(String)mySmartUpload.getRequest().getParameter("otherFlag");

//将文件保存在服务器端 
myFile.saveAs(trace,mySmartUpload.SAVE_PHYSICAL);

		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(trace));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int lastRowNum = sheet.getLastRowNum();
		HSSFRow row;
		HSSFCell cell;
		
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		JiluBO mBO =new JiluBO();
		
		row = sheet.getRow(0);//第1行；
		//int firstCellNum = row.getFirstCellNum();
		
		JiluDTO tDTO;
		for(int j =1;j<=lastRowNum;j++){
			row = sheet.getRow(j);
			System.out.println(j);
			if(row.getCell((short)0)==null||row.getCell((short)0).getNumericCellValue()<2000){
				continue;
			}
		
			tDTO = new JiluDTO();
			tDTO.setTimeid(Long.toString(new java.util.Date().getTime())+j);//保证了不重复
		
		   
		   Double yymmdd  = row.getCell((short)0).getNumericCellValue();
		   int yymmddInt = yymmdd.intValue();
		   String yymmddStr = String.valueOf(yymmddInt);
		   java.util.Date hhmmssDate =row.getCell((short)1).getDateCellValue();
		   SimpleDateFormat   sdfTime   =   new   SimpleDateFormat("HH:mm:ss");
		   SimpleDateFormat   sdfTime2   =   new   SimpleDateFormat("yyyyMMdd HH:mm");
		   String hhmmssStr = sdfTime.format(hhmmssDate);
		   String jysjStr=yymmddStr+" "+hhmmssStr;
		   java.util.Date jysjDate = sdfTime2.parse(jysjStr);
		   tDTO.setJysj(jysjDate);
		   
		  //证券代码
		   Double zqdmDouble  = row.getCell((short)3).getNumericCellValue();
		   int zqdmInt = zqdmDouble.intValue();
		   String zqdmStr = String.valueOf(zqdmInt); 
		   tDTO.setZqdm(zqdmStr);
		   //证券名称
		   tDTO.setZqmc(row.getCell((short)4).getStringCellValue());
		   //买入标志
		   tDTO.setMmflag(row.getCell((short)5).getStringCellValue());
		   //成交价格
		   tDTO.setCjjg(row.getCell((short)6).getNumericCellValue());
		   //成交数量
		   Double cjslDouble  = row.getCell((short)7).getNumericCellValue();
		   int cjslInt = cjslDouble.intValue();
		   if(tDTO.getMmflag().equals("卖出")){
		   		if(cjslInt>0){
		   			cjslInt=cjslInt*(-1);
		   		}
		   
		   }
		   
		   tDTO.setCjsl(cjslInt);
		   
		    //成交金额
		   tDTO.setCjje(row.getCell((short)8).getNumericCellValue());
		   
		    //清算金额
		   Double qsjeDouble  = row.getCell((short)9).getNumericCellValue(); 
		   if(tDTO.getMmflag().equals("买入")){
		   if(qsjeDouble>0){
		   qsjeDouble=qsjeDouble*(-1);
		   }
		   
		   }
		   tDTO.setQsje(qsjeDouble);
		   
		   
		    //资金账号  即为用户ＮＸ
		  // Double zjzhDouble  = row.getCell((short)14).getNumericCellValue();
		  // int zjzhInt = zjzhDouble.intValue();
		  // tDTO.setZjzh(Integer.toString(zjzhInt));		   
		   tDTO.setZjzh(nx);
		    //客户代码--即为用户id
		   //Double khdmDouble  = row.getCell((short)15).getNumericCellValue();
		   //int khdmInt = khdmDouble.intValue();
		   //tDTO.setKhdm(Integer.toString(khdmInt));
		   tDTO.setKhdm(idStr);
	       //股东姓名 即为XM
	       
		   //tDTO.setGdmc(row.getCell((short)16).getStringCellValue());
		   tDTO.setGdmc(xm);
 	      
 	        //交易所名称
		   tDTO.setJysmc(row.getCell((short)17).getStringCellValue());   
	      
	        //备注
		   tDTO.setBz(row.getCell((short)18).getStringCellValue());  
	      
	      tDTO.setJifl("高频");	      
	      
	       tDTO.setFlag1("未处理") ;
	       		
	       		
	       		
	       //需要将股票名称和代码格式化一下，极度简化，存入数据库；	
	        Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
			String zqdm=zqdmInteger.toString();
			tDTO.setZqdm(zqdm);
			String zqmc = tDTO.getZqmc();
			zqmc = zqmc.replace(" ","");
			zqmc = zqmc.replace(" ","");
			tDTO.setZqmc(zqmc);	
				//然后添加
			mBO.add(tDTO);
				
				
			
			
		}
		
		

}
}


//如果更新成功的话，跳转到tmp.jsp页面。
response.sendRedirect("tmp.jsp");



%>

