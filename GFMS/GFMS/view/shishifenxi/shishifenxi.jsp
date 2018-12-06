
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
    
    <title>个股/指数实时分析</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    
  </head>
  
  <SCRIPT language="javascript">
  function showMyJLKXT(flag){
						
				targetForm.action ="<%=path%>/view/highstock/ssfenxijson.jsp?flag="+flag; 
			 	targetForm.submit();		
	}
  
  </SCRIPT>
  
  <body>
  <CENTER>个股指数实时分析</CENTER>
  <CENTER><FONT color="red"></FONT></CENTER>
  
  
  <br>
  <form id="targetForm" name="targetForm" action="" method="post" action="" target="_blank">
  
  请输入指数/基金代码/股票代码(例子：399106)<input type="text" id="zqdm"   name="zqdm" value="" class="text">
  <input type="hidden" value="" id="flag" name="flag">
  <input type="button" value="60F"  class="button" onclick="showMyJLKXT('60');">
  <input type="button" value="日线"  class="button" onclick="showMyJLKXT('240');">
  
  <br>
  </form>
    <br>
    
   <TABLE Width="1000" class="tableframe" align="center">
			<tr class="listtitle">
				
				
				<td Width="24" align="center" class="listcelltitle">
					序
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					股票代码
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					股票简称
				</td>
				
				
				<td Width="*" align="center" class="listcelltitle">
					备注
				</td>
				
				
			</tr>
			<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" >					
				<td Width="24" align="center" class="listcellrow"  >
					1
				</td>					
				<td Width="60" align="center" class="listcelltitle" >
					399106
				</td>
				<td Width="60" align="center" class="listcellrow" >
					深圳综指
				</td>				
				<td Width="*" align="center" class="listcelltitle" >
					深圳综指一直市场最具代表性的指数，应该重点关注
				</td>					
			</tr>
			
			<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" >					
				<td Width="24" align="center" class="listcellrow"  >
					2
				</td>					
				<td Width="60" align="center" class="listcelltitle" >
					399678
				</td>
				<td Width="60" align="center" class="listcellrow" >
					深圳次新
				</td>				
				<td Width="*" align="center" class="listcelltitle" >
					<FONT color="red">次新股版块是市场最活跃的版块，领涨领跌的风向标。</FONT>
				</td>					
			</tr>
			<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" >					
				<td Width="24" align="center" class="listcellrow"  >
					3
				</td>					
				<td Width="60" align="center" class="listcelltitle" >
					399102
				</td>
				<td Width="60" align="center" class="listcellrow" >
					创业板综
				</td>				
				<td Width="*" align="center" class="listcelltitle" >
					&nbsp;
				</td>					
			</tr>
			<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" >					
				<td Width="24" align="center" class="listcellrow"  >
					4
				</td>					
				<td Width="60" align="center" class="listcelltitle" >
					399905
				</td>
				<td Width="60" align="center" class="listcellrow" >
					中证500
				</td>				
				<td Width="*" align="center" class="listcelltitle" >
					中证500是小盘股代表
				</td>					
			</tr>
			<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''" >					
				<td Width="24" align="center" class="listcellrow"  >
					6
				</td>					
				<td Width="60" align="center" class="listcelltitle" >
					399905
				</td>
				<td Width="60" align="center" class="listcellrow" >
					中证500
				</td>				
				<td Width="*" align="center" class="listcelltitle" >
					中证500是小盘股代表
				</td>					
			</tr>
				
		</TABLE>
  </body>
</html>
