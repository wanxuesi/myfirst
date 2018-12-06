
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>添加资金相关</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">

    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">


	<%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    
  			    int id = baseUserContext.getId();
  			    String idStr = Integer.toString(id);
    		%>
    <%
  			java.util.Date d = new Date();
  			java.text.SimpleDateFormat   sdf   =   new   java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");   
			String rq = sdf.format(d);
  		%>
		<script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
    
   
    <script language="javascript">   
   
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
   <center> <br>
    <form id="add" name="add" action="<%=path%>/dataAdd.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    		
    		
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        添加资金相关
    		        </td>
    		</tr>
    		<tr>
              <td   Width="100"  class="listcellrow">
    		     资金名称
    		   </td>
    		   <td class="listcellrow">
				<select id="name" name="name" class="text">
    		   <OPTION value="资金进出" selected="selected">资金进出</OPTION>
    		   <OPTION value="股息红利">股息红利</OPTION>
    		  
			   </select>
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     资金额
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="shuju" name="shuju" value="" class="text"><br>
    		    
    		    </td>
    	   </tr>
    	   
    	   <tr>
    	      <td   class="listcellrow">  
    		     交易时间
    		   </td>
    		   <td  class="listcellrow">
    		   <input type="text" id="date" name="date" size="20" class="text" value="<%=rq%>"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
    		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				   
    		   </td>
    		</tr>
    	   
           <tr>
              <td  class="listcellrow">
    		     备注
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="flag1" name="flag1" value="" class="text"><br>
    		    <input type="hidden" id="flag2" name="flag2" value="<%=idStr%>" class="text">
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