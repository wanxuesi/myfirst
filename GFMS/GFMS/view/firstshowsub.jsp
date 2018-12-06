
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <%
	
//获取随机数
	int intNumber  =(int)(Math.random()*1000);
	
	//System.out.println(intNumber);
	
	//设置三种颜色数组红，蓝，...；
	String[] colors= {"red","blue","yellow","purple","brown"};	
	
	//根据周的求余获得颜色；
	String stateColor = colors[intNumber%colors.length];	
	
%>
    <title>My JSP 'test.jsp' starting page</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    <script type="text/javascript" src="dwr/interface/gpylbo.js"></script>
    <script type="text/javascript" src="dwr/interface/lsjgbo.js"></script>
    <script type="text/javascript" src="js/engine.js"></script>  
    <script type="text/javascript" src="js/util.js"></script>
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
   <script language="javascript">
   
   
   function getOneGpgw(){
				
    	gpylbo.getOneGpgw(callbackGpgw); 
    	
  }
  
   function callbackGpgw(data){  
        document.getElementById("gpgwtitle").innerHTML=data[0]+"["+data[2]+"]";    
     	document.getElementById("gpgwcontent").innerHTML=data[1];
     	
     	
     	
   } 
   </script>
   
  
  </head>
  
  <body  bgcolor="#ffffff" onload="getOneGpgw();">
    <CENTER>
    <br><br>
  <input type="button" name="换一篇" value="换一篇" class="button" onclick='javascript:getOneGpgw(); '>
			
  <br><br><br><br>
			<DIV id="gpgwtitle"  style="color:<%=stateColor%>;font-family:黑体;FONT-SIZE: 30pt;">
			</DIV>
	</CENTER>
	<br>
			<DIV id="gpgwcontent" style="color:<%=stateColor%>;font-family:黑体;FONT-SIZE: 25pt;">
			</DIV><br>
			
  </body>
</html>
