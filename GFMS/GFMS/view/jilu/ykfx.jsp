
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
    
    <title>盈亏分析页面</title>
    
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
		function showOne(zqdm){
			
				targetForm.action ="<%=path%>/ykfxShowOne.do?zqdm="+zqdm; 
			
			 targetForm.submit();
		
		
		}
		
		
	</SCRIPT>
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
   
		 <%@ include file="/view/message.jsp"%>
    
    <center>
    	盈利亏损排行榜
    </center>
    <center>
    	盈利股票累计：<font color="red"><bean:write name="ZHENG" format=".00" /></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	亏损股票累计：<font color="green"><bean:write name="FU" format=".00" /></font>
    </center>
    
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
     <TABLE Width="1000" class="tableframe" align="center">
			<tr class="listtitle">
			
				<td Width="100" align="center" class="listcelltitle">
					排名榜
				</td>
				<td Width="100" align="center" class="listcelltitle">
					证券名称
				</td>
				<td Width="100" align="center" class="listcelltitle">
					证券代码
				</td>
				<td Width="100" align="center" class="listcelltitle">
					实际盈亏
				</td>
				<td Width="150" align="center" class="listcelltitle">
					初次～最后交易日期
				</td>
				<td Width="50" align="center" class="listcelltitle">
					天数
				</td>
				<td Width="150" align="center" class="listcelltitle">
					K线开始～截止更新日期
				</td>
				<td Width="100" align="center" class="listcelltitle">
					K线覆盖关系
				</td>
				
				<td Width="150" align="center" class="listcelltitle">
					查看详细交易分类盈亏
				</td>
			</tr>
			<logic:iterate id="jilu" name="JILU"  indexId="index" type="com.fuguo.dto.JiluDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					
					<td Width="100" align="center" class="listcellrow"  onclick="showOne(<bean:write name="jilu" property="zqdm" />);">
					
					第${pageScope.index+1}名
					</td>
					
					
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="zqdm" />);">
					<bean:write name="jilu" property="zqmc"  filter="false"/>
				</td>
				<td Width="100" align="center" class="listcellrow" onclick="showOne(<bean:write name="jilu" property="zqdm" />);">
					<bean:write name="jilu" property="zqdm"  filter="false"/>
					</td>
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="zqdm" />);">
					
					<logic:greaterEqual value="0"  name="jilu" property="qsje"><font color="red"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font></logic:greaterEqual>
					<logic:lessThan value="0"  name="jilu" property="qsje"><font color="green"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font></logic:lessThan>
					
					
				</td>
				<td  Width="150" align="center" class="listcelltitle">
					<bean:write name="jilu" property="jiludateMin" format="yyyy-MM-dd"/>～<bean:write name="jilu" property="jiludateMax" format="yyyy-MM-dd"/>
				</td>
				
					<td  Width="50" align="center" class="listcelltitle">
					<B>
					<bean:write name="jilu" property="flag5"/>
					</B>
				</td>
				
				
				<td  Width="150" align="center" class="listcelltitle">
					<bean:write name="jilu" property="datestart" format="yyyy-MM-dd"/>～<bean:write name="jilu" property="date" format="yyyy-MM-dd"/>
				</td>
				<td  Width="100" align="center" class="listcelltitle">
				<logic:equal value="否"  name="jilu" property="flag4"><font color="red"><bean:write name="jilu" property="flag4" /></font></logic:equal>
				<logic:equal value="是"  name="jilu" property="flag4"><font color="green"><bean:write name="jilu" property="flag4" /></font></logic:equal>
				
				</td>
				<td Width="150" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="zqdm" />);">
					&nbsp;请点击该记录&nbsp;
				</td>		
				</tr>

			</logic:iterate>
				
		</TABLE>
  </body>
</html>
