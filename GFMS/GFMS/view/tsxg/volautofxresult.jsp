
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
    
    <title>VOL动态分析结果</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
		 <%@ include file="/view/message.jsp"%>

    <CENTER>VOL动态分析结果(默认6个月内历史数据)[4个工作日内VOL新低，该日期显示为<font color='red'>红色</font>]</CENTER>
    <CENTER><font color='blue'>提示：点击记录即可显示日K线-VOL图、解禁详情</font></CENTER>
    <table Width="90%" align="center">
			<tr >
			<td align="center">
			 
			 
			 
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button"  onclick="javascript:location.href='<%=path%>/volAutoFx.do';">
			
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
				
				<td Width="80" align="center" class="listcelltitle">
					最小VOL
				</td>
				
				<td Width="80" align="center" class="listcelltitle">
					第二小VOL
				</td>
				<td Width="80" align="center" class="listcelltitle">
					第三小VOL
				</td>
				
				
				
			</tr>
			<logic:iterate id="gpmc" name="GPMCS" indexId="index"  type="com.fuguo.dto.GpmcDTO" scope="request">
				<tr onclick="showOnePic('<bean:write name="gpmc" property="zqdm" />');"  onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<td Width="60" align="center" class="listcellrow">
						${pageScope.index+1}
					</td>
					<td  Width="60"  align="center" class="listcellrow">
						<bean:write name="gpmc" property="zqmc" />
					</td>
					

				<td Width="60" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="zqdm" />
				</td>


					<td  Width="80" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="oneDateVol" filter="false"/>
				</td>
				<td  Width="80" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="twoDateVol"  filter="false"/>
				</td>
				<td  Width="80" align="center" class="listcelltitle">
					<bean:write name="gpmc" property="threeDateVol"  filter="false"/>
				</td>

				

			</logic:iterate>
		</TABLE>
		
			
		</body>
		<script language="javascript">
	
		
		function showOnePic(gpdm){
			
			
				targetForm.action ="<%=path%>/showOnePic.do?gpdm="+gpdm; 
			
			 	targetForm.submit();
			
		
		}
	
	</script>
</html>
