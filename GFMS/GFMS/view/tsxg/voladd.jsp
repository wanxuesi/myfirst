
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
   
    
    <title>添加观察股相关</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="javascript" type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
   <base href="<%=basePath%>">
<%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    
  			    int id = baseUserContext.getId();
  			    String idStr = Integer.toString(id);
    		%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
	<script type="text/javascript" src="dwr/interface/mygpmcbo.js"></script>

	
		<script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script> 
    
   
    <script language="javascript">   
   function getChangeZqmcAndJys(){
  		var gpmc = $("gpmc").value;
    	mygpmcbo.getZqdmAndJysByZqmc(gpmc,callbackGpmc);
  
  }
  
   function callbackGpmc(data){     
        //alert(data.length);
     	//alert(data[0]);
     	$("zqdm").value=data[0];
     	//alert(data[1]);
     	$("flag1").value=data[1];
     	
     	
     	
   }
	</script>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();'>
   <center> <br>
    <form id="add" name="add" action="<%=path%>/volAdd.do" method="post">
         <table  Width="750" class="tableframe" align="center">
         <%
    		
    		
    		%>
    		<tr  class="listtitle">
    		        <td colspan="2">
    		        添加观察股相关
    		        </td>
    		</tr>
    		<tr>
              <td  class="listcellrow">
    		     证券名称
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="gpmc" name="gpmc" value=""  onchange="getChangeZqmcAndJys();" class="text"><br>
    		    
    		    </td>
    	   </tr>
    	   <tr>
              <td  class="listcellrow">
    		     证券代码
    		     
    		     </td>
    		    
    		   
    		   <td  class="listcellrow">
    		    <input type="text" id="zqdm" name="zqdm" value="" class="text"><br>
    		    <input type="hidden" id="flag2" name="flag2" value="<%=idStr%>" class="text">
    		    </td>
    	   </tr>
    		<tr>
              <td   Width="100"  class="listcellrow">
    		     交易所名称
    		   </td>
    		   <td class="listcellrow">
				<select id="flag1" name="flag1" class="text">
    		   <OPTION value="上海A股" selected="selected">上海A股</OPTION>
    		   <OPTION value="深圳A股">深圳A股</OPTION>
    		  
			   </select>
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