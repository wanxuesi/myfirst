
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%@ page import="java.text.SimpleDateFormat"%>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>人员维护主页面</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <%
    			BaseUserContext baseUserContext = (BaseUserContext)request.getSession().getAttribute("#BASEUSERCONTEXT#");
  			    String xm =	baseUserContext.getXm().trim();
  			    
  			    SimpleDateFormat   sdfymd   =   new   SimpleDateFormat("yyyy-MM-dd"); 
  			    java.util.Date datestart = baseUserContext.getDatestart();
  			    String datestartStr = sdfymd.format(datestart);
  			    java.util.Date dateend = baseUserContext.getDateend();
  			    String dateendStr = sdfymd.format(dateend);
  			    
    		%>
    <script type="text/javascript" src="dwr/interface/danweibumendao.js"></script>  
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script>  
    <script language="javascript"> 
   
   
	</script>
  </head>
  
    <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
  <form name="targetForm" action="" target="_blank" method="post">
  </form>
     <br><br><br><br>
      <center> 
        您的账号有效时间段为：<%=datestartStr%>——<%=dateendStr%>
      </center>
     <br>
   
       <center>  <input type="button" name="密码修改" value="密码修改" onclick="javascript:location.href='<%=path%>/view/user/koulingxiugaiindex.jsp'">
    	&nbsp;&nbsp;<input type="button" name="一键清除" value="一键清除" onclick="myalldelete();">
    	
    	</center>
    
    
  </body>
  <script language="javascript">
 function myalldelete(){
	if(confirm("您确定清除用户账号为：'<%=xm%> ' 下的所有信息？\n点击'确定'后，所有数据将不能回复！"))
	{
	
	targetForm.action ="<%=path%>/clearAll.do";
	targetForm.submit();
	
	}
  }
      		
</script>
</html>
