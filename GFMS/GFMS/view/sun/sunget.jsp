
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
    
    <title>系统问题及改进意见明细</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
    <script language="javascript" type="text/javascript" src="<%=path%>/js/datepicker/WdatePicker.js" charset="gb2312"></script>
<link href="<%=path%>/js/datepicker/default/datepicker.css" rel="stylesheet" type="text/css" charset="gb2312">
    
		
	
    <script language="javascript">   

	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
  
   <form name="targetForm" action="" method="post">
  		<input type="hidden" id="id" name="id" value="<bean:write name="SUN" property="id" scope="request"/>">
  		  		<input type="hidden" id="parentid" name="parentid" value="<bean:write name="SUN" property="timeid" scope="request"/>">
  	</form>
  	
	<form name="targetForm2" action="" target="_blank" method="post">
	<input type="hidden" id="id" name="id" value="<bean:write name="SUN" property="id" scope="request"/>">
		 <input type="hidden" id="otherFlag" name="otherFlag" value="<bean:write name="SUN" property="timeid" scope="request"/>">
  		<input type="hidden" id="parentid" name="parentid" value="<bean:write name="SUN" property="timeid" scope="request"/>">
  		<input type="hidden" id="fileName" name="fileName" value="<bean:write name="SUN" property="fileName" scope="request"/>">
  		<input type="hidden" id="parentid" name="timeid" value="<bean:write name="SUN" property="timeid" scope="request"/>">
  	</form>	
  	
  	
   <center>  <br>
   
		<INPUT type="button" value="修改" class="button" onclick="mypreupdate();">
		&nbsp;&nbsp;&nbsp;&nbsp;
		
				<INPUT type="button" value="删除" class="button" onclick="mydelete();">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</center>
   <center> <br>
    <form id="add" name="add" action="" method="post">
         <table  Width="750" class="tableframe" align="center">
        
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        系统问题及改进意见明细
    		        </td>
    		</tr>
           
    		
    	   
    		<tr>

    	      <td   class="listcellrow">  
    		     问题及改进意见
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;
    		   
    		   <TEXTAREA id="clgc" name="clgc" cols="100" rows=8 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"><bean:write name="SUN" property="clgc" scope="request"/></TEXTAREA>  
    		   </td>
    		</tr>
    		
    		<tr>
    	      <td   class="listcellrow">  
    		     发生时间
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="SUN" property="fssj" scope="request"  format="yyyy-MM-dd HH:mm"/>
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    		<tr>
    	      <td   class="listcellrow">  
    		     处理时间
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="SUN" property="clsj" scope="request"  format="yyyy-MM-dd HH:mm"/>

    		      
    		   </td>
    		</tr>
    		
    		
    		<tr>
              <td  Width="100" class="listcellrow">
    		     发现人
				</td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="SUN" property="fxr" scope="request"/>

    		   </td>
    		   
    		</tr>
    		
    		<tr>
              <td  Width="100" class="listcellrow"> 
    		  验收人
    		  </td>
    		   <td  class="listcellrow"> 
    		      		   <bean:write name="SUN" property="ysr" scope="request"/>

    		   
    		    
    		    </td>
    	   </tr>
    	   
    	   
    	
    	   
    	  
    	   
    	   
    	   
    		
    		 <tr>
    	      <td   class="listcellrow">  
    		     备注 
    		   </td>
    		   <td  class="listcellrow">
    		   &nbsp;&nbsp;
    		       		   
    		   <TEXTAREA id="bz" name="bz" cols="100" rows=3 style="BACKGROUND-COLOR: #ffffdd;border: solid 1px #888888;"><bean:write name="SUN" property="bz" scope="request"/></TEXTAREA>
    		   </td>
    		</tr>
    		 <tr>

    	      <td   class="listcellrow">  
    		     填报人
    		   </td>
    		   <td  class="listcellrow">
    		       		   <bean:write name="SUN" property="tbr" scope="request"/>

    		   
    		    
    		   

    		       
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
	
	targetForm.action ="<%=path%>/sunDelete.do";
	targetForm.submit();
	
	}
  }
 
  function mypreupdate(){
	targetForm.action ="<%=path%>/sunPreUpdate.do";
	targetForm.submit();
	}
 



	
</script>
</html>