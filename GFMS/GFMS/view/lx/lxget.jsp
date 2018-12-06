
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>单条股票交易分类明细</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

	<script type="text/javascript" src="dwr/interface/userdao.js"></script> 
     <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
		
<script language="javascript">   
     
  
   
   
 
   
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
  	<form name="targetForm" action="" method="post">
  		<input type="hidden" id="id" name="id" value="<bean:write name="LX" property="id" scope="request"/>">
  	</form>
   <center>  <br>
   <INPUT type="button" value="关闭本页面" class="button" onclick="window.close();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT type="button" value="删除" class="button" onclick="mydelete();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<INPUT type="button" value="修改" class="button" disabled="disabled" onclick="mypreupdate();">
    <form id="add" name="add" action="" method="post">
         <table Width="750" class="tableframe" align="center">
         	<input type="hidden" id="id" name="id" value="<bean:write name="LX" property="id" scope="request"/>">
           <tr  class="listtitle">
    		        <td colspan="2">
    		        单条股票交易分类明细
    		        </td>
    		</tr>
    		
    		
           <tr>
              <td  class="listcellrow">
    		     交易分类
    		    
    		   </td>
    		  <td  class="listcellrow">
    		  
    		     <input type="text" id="name" name="name" value='<bean:write name="LX" property="name" scope="request"/>' class="text" readonly>
    		   
    		    </td>
    	   </tr>
    	    <tr>
              <td  class="listcellrow">
    		      对应自动模拟参数
    		    
    		   </td>
    		  <td  class="listcellrow">
    		  
    		     <input type="text" id="flag2" name="flag2" value='<bean:write name="LX" property="flag2" scope="request"/>' class="text" readonly>
    		   
    		    </td>
    	   </tr>
    		
    	</table>	
    	
    	</form>
    	</center>
  </body>
  <script language="javascript"> 
  function mydelete(){
	if(confirm('您确定删除？'))
	{
	document.location.href='<%=path%>/lxDelete.do?id=<bean:write name="LX" property="id" scope="request"/>';
	//targetForm.action ="<%=path%>/lxDelete.do"; 
	//targetForm.submit();
	}
  }
  
  function mypreupdate(){
	targetForm.action ="<%=path%>/lxPreUpdate.do"; 
	targetForm.submit();
 }
 
 

</script>
</html>