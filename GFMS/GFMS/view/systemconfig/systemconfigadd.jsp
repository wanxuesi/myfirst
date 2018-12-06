
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>添加系统配置相关</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


	
		<script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
    
   
    <script language="javascript">   
   
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
   <center> <br>
    <form id="add" name="add" action="<%=path%>/systemconfigAdd.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    		
    		
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        添加系统配置相关
    		        </td>
    		</tr>
    		<tr>
              <td  class="listcellrow">
    		     系统配置代码
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="functioncode" name="functioncode" value="" class="text"><br>
    		    
    		    </td>
    	   </tr>
    		<tr>
              <td  class="listcellrow">
    		     系统配置名称
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="functionname" name="functionname" value="" class="text"><br>
    		    
    		    </td>
    	   </tr>
    	   
    		 <tr>
              <td   Width="100"  class="listcellrow">
    		     系统配置值
    		   </td>
    		   <td class="listcellrow">
    		    <input type="text" id="functionvalue" name="functionvalue" value="" class="text"><br>

    		    </td>
    	   </tr>
    	  
          
    	     	
    		    <tr class="listtitle">
    		        <td >
    		              <input type="button" value="提交" class="button" onclick="chickme();">
    		        </td>
    		        <td >
    		              <input type="reset" value="重置" class="button">
    		        </td>
    		    </tr>
    		  </table>
    	</form>
    	</center>
  </body>
  <script language="javascript">
  
 	
function chickme(){
		
 		document.getElementById("add").submit();	
       
    } 

	
</script>
</html>