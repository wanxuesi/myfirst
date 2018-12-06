
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>登录页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  
	</head>
<% 

	String xm="";
	String kl="";
  //读取cookie
   Cookie cookie[]=request.getCookies();

   if(cookie!=null){
   		Cookie c=null;
    	for(int i=0;i<cookie.length;i++){
     		c=cookie[i];
     		//System.out.println(c.getName());
     		if(c.getName().equals("xm")){//查找cookie里面的是否存在cookie键位xm的cookie
      		//如果存在该键 取该键对应的值==>相当于Map取值
      			//xm=c.getValue();
      			xm =URLDecoder.decode(c.getValue(),"UTF-8");
      			//System.out.println("已经设置了cookie,cookie的xm值为:"+xm);
      
     		}
     		if(c.getName().equals("kl")){//查找cookie里面的是否存在cookie键位kl的cookie
      		//如果存在该键 取该键对应的值==>相当于Map取值
      			kl=c.getValue();
      			//System.out.println("已经设置了cookie,cookie的kl值为:"+c.getValue());
      
     		}
    	}
   }
   
  %>
	<body>
		<form id="login" name="login" action="<%=path%>/login.do" method="post">
			<TABLE align="center" border="0" cellpadding="2" cellspacing="2" width="98%">
			<TR>
				<TD colspan="3" height="50"></TD>
			</TR>
			<TR>
				<TD></TD>
				<TD valign="top" width="500" height="344" background="<%=path%>/images/login.jpg">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td height="50" colspan="3" scope="col">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="25" colspan="3">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="50" colspan="3">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="25" colspan="3">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="20" colspan="3">&nbsp;</td>
                  </tr>
                  <tr>
                    <td width="47%" height="30">&nbsp;</td>
                    <td width="27%" valign="middle"><input type="text" name="xm" value='<%=xm%>' maxlength="50" size="21"></td>
                    <td width="26%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="35">&nbsp;</td>
                    <td height="25" valign="middle"><input type="password" name="kl"  value='<%=kl%>' maxlength="50" size="21" border="50"></td>
                    <td height="25">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="25">&nbsp;</td>
                    <td height="25"><input type="button" id="btmlogin" value="登&nbsp;&nbsp;录" class="button" onclick="chickme();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" name="reset" value="重置"><br>保存用户名和密码<input type="checkbox" name="otherflag" value="1" checked="checked"></td>
                    <td height="25">&nbsp;</td>
                  </tr>
                </table></TD>
				<TD></TD>
			</TR>
			<TR>
				<TD></TD>
				<TD align="center" height="20"></TD>
				<TD></TD>
			</TR>
			<TR>
				<TD></TD>
				<TD align="center" valign="top" bgcolor="#cccccc" ></TD>
				<TD></TD>
			</TR>
			<TR>
				<TD></TD>
				<TD align="center"><font size="2"> 版本号 V9.0 &nbsp;2018-11-30<a href="<%=path%>/readme.txt" target="_blank">新版本更新内容</a></font></TD>
				<TD></TD>
			</TR>
		</TABLE>

			

		</form>


	</body>
</html>
<script language="javascript">
function chickme(){

			document.getElementById("btmlogin").value="loading";
			document.getElementById("btmlogin").disabled="disabled";
 		   document.getElementById("login").submit();	
      
} 
</script>
