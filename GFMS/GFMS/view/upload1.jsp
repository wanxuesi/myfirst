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
//ʵ��������bean
com.jspsmart.upload.SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
//��ʼ��
mySmartUpload.initialize(pageContext); 
//�������ص����ֵ
mySmartUpload.setMaxFileSize(500 * 1024*1024);
//�����ļ�
mySmartUpload.upload();
//ѭ��ȡ���������ص��ļ�
for (int i=0;i<mySmartUpload.getFiles().getCount();i++){
//ȡ�����ص��ļ�
com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(i);
if (!myFile.isMissing())
{
//ȡ�����ص��ļ����ļ���
String myFileName=myFile.getFileName();
//ȡ�ò�����׺���ļ���
String suffix=myFileName.substring(0,myFileName.lastIndexOf('.'));
//ȡ�ú�׺��
String ext= mySmartUpload.getFiles().getFile(0).getFileExt(); 
//ȡ���ļ��Ĵ�С 
int fileSize=myFile.getSize();
//����·��
String aa=getServletContext().getRealPath("/")+"jsp\\";
String trace=aa+myFileName;
//ȡ�ñ�Ĳ���

//String otherFlag=(String)mySmartUpload.getRequest().getParameter("otherFlag");

//���ļ������ڷ������� 
myFile.saveAs(trace,mySmartUpload.SAVE_PHYSICAL);

		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(trace));
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int lastRowNum = sheet.getLastRowNum();
		HSSFRow row;
		HSSFCell cell;
		
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd");
		JiluBO mBO =new JiluBO();
		
		row = sheet.getRow(0);//��1�У�
		//int firstCellNum = row.getFirstCellNum();
		
		JiluDTO tDTO;
		for(int j =1;j<=lastRowNum;j++){
			row = sheet.getRow(j);
			System.out.println(j);
			if(row.getCell((short)0)==null||row.getCell((short)0).getNumericCellValue()<2000){
				continue;
			}
		
			tDTO = new JiluDTO();
			tDTO.setTimeid(Long.toString(new java.util.Date().getTime())+j);//��֤�˲��ظ�
		
		   
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
		   
		  //֤ȯ����
		   Double zqdmDouble  = row.getCell((short)3).getNumericCellValue();
		   int zqdmInt = zqdmDouble.intValue();
		   String zqdmStr = String.valueOf(zqdmInt); 
		   tDTO.setZqdm(zqdmStr);
		   //֤ȯ����
		   tDTO.setZqmc(row.getCell((short)4).getStringCellValue());
		   //�����־
		   tDTO.setMmflag(row.getCell((short)5).getStringCellValue());
		   //�ɽ��۸�
		   tDTO.setCjjg(row.getCell((short)6).getNumericCellValue());
		   //�ɽ�����
		   Double cjslDouble  = row.getCell((short)7).getNumericCellValue();
		   int cjslInt = cjslDouble.intValue();
		   if(tDTO.getMmflag().equals("����")){
		   		if(cjslInt>0){
		   			cjslInt=cjslInt*(-1);
		   		}
		   
		   }
		   
		   tDTO.setCjsl(cjslInt);
		   
		    //�ɽ����
		   tDTO.setCjje(row.getCell((short)8).getNumericCellValue());
		   
		    //������
		   Double qsjeDouble  = row.getCell((short)9).getNumericCellValue(); 
		   if(tDTO.getMmflag().equals("����")){
		   if(qsjeDouble>0){
		   qsjeDouble=qsjeDouble*(-1);
		   }
		   
		   }
		   tDTO.setQsje(qsjeDouble);
		   
		   
		    //�ʽ��˺�  ��Ϊ�û��Σ�
		  // Double zjzhDouble  = row.getCell((short)14).getNumericCellValue();
		  // int zjzhInt = zjzhDouble.intValue();
		  // tDTO.setZjzh(Integer.toString(zjzhInt));		   
		   tDTO.setZjzh(nx);
		    //�ͻ�����--��Ϊ�û�id
		   //Double khdmDouble  = row.getCell((short)15).getNumericCellValue();
		   //int khdmInt = khdmDouble.intValue();
		   //tDTO.setKhdm(Integer.toString(khdmInt));
		   tDTO.setKhdm(idStr);
	       //�ɶ����� ��ΪXM
	       
		   //tDTO.setGdmc(row.getCell((short)16).getStringCellValue());
		   tDTO.setGdmc(xm);
 	      
 	        //����������
		   tDTO.setJysmc(row.getCell((short)17).getStringCellValue());   
	      
	        //��ע
		   tDTO.setBz(row.getCell((short)18).getStringCellValue());  
	      
	      tDTO.setJifl("��Ƶ");	      
	      
	       tDTO.setFlag1("δ����") ;
	       		
	       		
	       		
	       //��Ҫ����Ʊ���ƺʹ����ʽ��һ�£����ȼ򻯣��������ݿ⣻	
	        Integer zqdmInteger = Integer.parseInt(tDTO.getZqdm());		
			String zqdm=zqdmInteger.toString();
			tDTO.setZqdm(zqdm);
			String zqmc = tDTO.getZqmc();
			zqmc = zqmc.replace(" ","");
			zqmc = zqmc.replace(" ","");
			tDTO.setZqmc(zqmc);	
				//Ȼ�����
			mBO.add(tDTO);
				
				
			
			
		}
		
		

}
}


//������³ɹ��Ļ�����ת��tmp.jspҳ�档
response.sendRedirect("tmp.jsp");



%>

