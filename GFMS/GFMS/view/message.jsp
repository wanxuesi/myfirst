<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<html>
	<head>
		<title></title>
	<script type="text/javascript" src="js/engine.js"></script>
	<script type="text/javascript" src="js/util.js"></script>
	<script type="text/javascript" src="dwr/interface/gpylbo.js"></script>
	</head>
	<%
    	 BaseUserContext baseUserContextMessage = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  		 String yuluisShow = (String)baseUserContextMessage.getSystemconfig().get("yuluisShow");	    
  			   
    %>
	<body><input type="text" id="message" name="message" value="" size="200" style="border: 1px solid #FFFFFF;width=500px;color: red;height:35px; font-size:32px;"   readonly>
	<br>
	</body>
</html>
<script type="text/javascript">
<!--
function getOneGpyl(){
		<%if(yuluisShow.equals("Y")){%> 		
    	gpylbo.getOneGpyl(callbackGpyl); 
    	<%}%>
  }
  <%if(yuluisShow.equals("Y")){%> 
   function callbackGpyl(data){             
     	$("message").value=data;     	
   }
   <%}%>
//-->
</script>
