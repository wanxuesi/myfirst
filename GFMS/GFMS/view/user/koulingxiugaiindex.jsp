
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="bsw.fwk.BaseUserContext"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>口令修改主页面</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
  </head>
  
  <body>
  	密码修改页面<br>
    <br><br><br>
    
    <center>
    <form id="add" name="add" action="<%=path%>/userPwdUpdate.do" method="post">
    * 您的旧密码：<INPUT type="password" id="kl" name="kl" value=""><br><br>
    * 输入新密码：<INPUT type="password" id="otherflag2" name="otherflag2" value=""><br><br>
    * 新密码确认：<INPUT type="password" id="otherflag2_chick" name="otherflag2_chick" value=""><br><br>
    <INPUT type="button" name="确认" value="确认" onclick="chick();">
	 <INPUT type="button" name="返回" value="返回" onclick="history.back();">
   </form>
    </center>
  </body>
  <Script lagguage="javaScript">
  function chick(){
  		var kl = document.getElementById("kl").value;
  		var otherflag2 = document.getElementById("otherflag2").value;
  		var otherflag2_chick = document.getElementById("otherflag2_chick").value;
  		if(otherflag2==otherflag2_chick){
  			document.getElementById("add").submit();
  			
  		}else{
  			
  			alert("新的登录密码两次不一致！！");
  		
  		}
  }

  </Script>
</html>
