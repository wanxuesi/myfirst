
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <script>
	function f(){
		url = '<%=path%>/userShow.do';
		window.opener.location.href = url;
		window.close();
	}
	
	setTimeout(f,1000);
	</script>
  </head>
  
  <body>
    <CENTER>
  <br><br><br><br>
			<P>操作成功！
			</p>

			<P>
				1秒钟后自动跳转到用户显示页面
			</P>
			如果页面长时间未能跳转,请点击<a href="<%=path%>/userShow.do">这里</a>			
		</CENTER>
  </body>
</html>
