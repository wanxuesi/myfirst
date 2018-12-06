
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
    
    <title>股票历史价格显示</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
 <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>
    <CENTER>股票历史价格查询结果</CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">		 
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/lsjgQuery.do';">
			</td>
			</tr>
		</table>
	<FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		
		
		<TABLE   Width="850"  class="tableframe" align="center">
			<tr class="listtitle">
				<td Width="60" align="center" class="listcelltitle">
					ID
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					证券名称
				</td>
				
				
				<td Width="60" align="center" class="listcelltitle">
					证券代码
				</td>
				<td Width="60" align="center" class="listcelltitle">
					日期
				</td>
				<td Width="60" align="center" class="listcelltitle">
					开盘价
				</td>
				<td Width="60" align="center" class="listcelltitle">
					最高价
				</td>
				<td Width="60" align="center" class="listcelltitle">
					最低价
				</td>
				<td Width="60" align="center" class="listcelltitle">
					收盘价
				</td>
				<td Width="60" align="center" class="listcelltitle">
					复权因子
				</td>
				<td Width="60" align="center" class="listcelltitle">
					成交量
				</td>
				
				
				
				
				
				
				
			</tr>
			<logic:iterate id="lsjg" name="LSJG" indexId="index"  type="com.fuguo.dto.LsjgDTO" scope="request">
				<tr onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						${pageScope.index+1}
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="zqmc" />
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="zqdm" />
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="date" format="yyyy-MM-dd" />
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="open"  format=".00" filter="false"/>
					</td>
					
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="high"  format=".00" filter="false"/>
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="lsjg" property="low"  format=".00" filter="false"/>
					</td>

				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="lsjg" property="close"  format=".00" filter="false"/>
				</td>
				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="lsjg" property="fqyz"  format=".00" filter="false"/>
				</td>
				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="lsjg" property="volume"  format=".00" filter="false"/>
				</td>
				

				

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
			
	
	</script>
</html>
