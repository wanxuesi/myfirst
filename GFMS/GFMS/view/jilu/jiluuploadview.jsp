
<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title> ���׼�¼excel�ļ��ϴ�</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


  </head>

<script language="javascript"> 

function xiazai(){


}


function check()
{

   
	var n=0;
	
	var files =document.getElementById("file").value;
	//alert(file.length);
   if(files.length<2){
	   alert("��ѡ��Ҫ�ϴ����ļ�!");
	   return false;
	}
	
	
}


   
 
   </script>
  <body>
   <%
   String otherFlag = (String)request.getParameter("otherFlag");
   
   
   %>
   

<br><br><br><br><br>



<!-- ================================================ -->
	
	<form action="<%=path%>/jiluUpload.do" method="post" enctype="multipart/form-data" name="form1"  method=post>
	<table class="tableframe" align="center">
	<tr  class="listtitle">
    		        <td colspan="2">
    		        ���׼�¼excel�ļ��ϴ�
    		        </td>
    		</tr>


			<tr>
    	      <td  class="listcellrow">  
    		     �������ļ���С�������20MB����! ��<input type="hidden" name="otherFlag" value="<%=otherFlag%>">
    		   </td>
    		   <td class="listcellrow">
    		   <input type="file" name="file" id="file" size="60">
    		   </td>
    		</tr>
    		
			<tr>

    	      <td colspan=1 class="listcellrow">  
    		     <input type="submit" name="Send" value=" ����ύ " onClick="return check();" id="submit"> <input type="reset" name="reset" value=" ��� "  id="submit">   
    		   </td>
    		   <td colspan=1 class="listcellrow" align="right">  
    		      <input type="button" name="button" value=" ����ģ�弰ʹ��˵�� " onClick="javascript:document.location.href='<%=path%>/view/Excel.zip';">   
    		   </td>
   			 </tr>
	
   
	</TABLE>
	
    </form>

  </body>
 
	

	

</html>
