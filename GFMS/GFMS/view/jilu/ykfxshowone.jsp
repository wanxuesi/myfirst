
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
    
    <title><bean:write name="GPMCDTO"  property="zqmc"  scope="request"/>(<bean:write name="GPMCDTO"  property="zqdm"  scope="request"/>)交易分类盈亏分析页面</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
   <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <SCRIPT language="javascript">	
		function showMyJL(){
			
				
				
				targetForm.action ="<%=path%>/jiluSpecalQuery.do"; 
			    
			 	targetForm.submit();
			
		
		}
		
		function showMyZXT(){
			
				
				
				targetForm.action ="<%=path%>/ykfxZxtOneStock.do"; 
			    
			 	targetForm.submit();
			
		
		}
	</SCRIPT>
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
  
    <center><font color=red><bean:write name="GPMCDTO"  property="zqmc"  scope="request"/></font>&nbsp;&nbsp;交易分类盈亏分析页面</center>
    <br>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
    
     <input type="hidden" id="zqdm" name="zqdm" value=<bean:write name="GPMCDTO"  property="zqdm"  scope="request"/>>
  <input type="hidden" id="zqmc" name="zqmc" value=<bean:write name="GPMCDTO"  property="zqmc"  scope="request"/>>
  <input type="button" value="查看历史交易记录" class="button" onclick="showMyJL();">
	 &nbsp;&nbsp;&nbsp;&nbsp; <input type="button" value="该股盈亏折线图" class="button" onclick="showMyZXT();">
   	</FORM>
    <br>
			<logic:iterate id="jilu" name="JILU"  indexId="index" type="com.fuguo.dto.JiluDTO" scope="request">
				
				<bean:write name="jilu" property="jifl"  filter="false"/>&nbsp;交易盈亏：
				
				<logic:greaterEqual value="0"  name="jilu" property="qsje">
					<font color="red"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font>
				</logic:greaterEqual>
				<logic:lessThan value="0" name="jilu" property="qsje">
					<font color="green"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font>
				</logic:lessThan>
				
				
				
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
			</logic:iterate>
  </body>
</html>
