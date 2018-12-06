
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
    
    <title>系统改进意见查询结果</title>
    
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
   <SCRIPT language="javascript">
	     function checkAll(checkBoxId) {     
		    var el = document.getElementsByTagName('input');     
		    var len = el.length;     
		    for(var i=0; i<len; i++){         
		    	if((el[i].type=="checkbox") && (el[i].id==checkBoxId)){
		    		el[i].checked = true;         
		        }     
		    } 
	   } 
	   function clearAll(checkBoxName) {
		   var el = document.getElementsByTagName('input');
		   var len = el.length;     
		   for(var j=0; j<len; j++){         
			   if((el[j].type=="checkbox") && (el[j].name==checkBoxName)){             
			   	   el[j].checked = false;        
			   }     
		   } 
	   } 
		
		function showOne(id){
			
				targetForm.action ="<%=path%>/sunShowOne.do?id="+id; 
			
			 targetForm.submit();
		
		
		}
		
		
		//设定为已处理方法
		function setYibaosong(){
			document.getElementById("updateMoreForm").action="sunUpdateMoreToYi.do";
			document.getElementById("updateMoreForm").submit();
		}
		
		function deleteMore(){
			document.getElementById("updateMoreForm").action="sunDeleteMore.do";
			document.getElementById("updateMoreForm").submit();
		}
		
	</SCRIPT>
  </head>
  
    <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
  
		 <%@ include file="/view/message.jsp"%>
    <CENTER></CENTER> <br>
    <form id="queryDateSqlForm" name="queryDateSqlForm"  action="" target="_blank" method="post">
  		<input type="hidden" id="isSend" name="isSend" value="0">
  		<input type="hidden" id="queryDateSql" name="queryDateSql" value="<bean:write name="queryDateSql" scope="session"/>">
  							
  	</form>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		<form method="post" id="updateMoreForm" action="" target="_blank">
		<CENTER>系统改进意见查询结果</CENTER>
    <TABLE Width="95%"  align="center">
  
    		<TR>
					<TD colspan="4" align="center">
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button" onclick="javascript:location.href='<%=path%>/sunQuery.do';">
					<input type="button" value="选中'未处理'" class="button" onclick="checkAll('未处理')"/>
					</TD>
					<TD colspan="3" align="center">
					<input type="button" value="取消选中" class="button" onclick="clearAll('ids')"/>
					</TD>
					<TD colspan="3" align="left">
					<input type="button" value="设定为已处理" class="button" onclick="setYibaosong()"/>
					</TD>
					<TD colspan="3" align="left">
					<input type="button" value="批量删除" class="button" onclick="deleteMore()"/>
					</TD>
					
			</TR>
	</TABLE>
    <TABLE Width="1000" class="tableframe" align="center">
			<tr class="listtitle">
			<TD  Width="30" align="center" class="listcelltitle">选择</TD>
				<td Width="24" align="center" class="listcelltitle">
					序
				</td>
				
				<td Width="40" align="center" class="listcelltitle">
					发现人
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					发生时间
				</td>
				<td Width="60" align="center" class="listcelltitle">
					处理时间
				</td>
				
				
				
				<td Width="*" align="center" class="listcelltitle">
					问题及改进意见
				</td>
				
				<td  Width="50" align="center" class="listcelltitle">
					状态
				</td>
			</tr>
			<logic:iterate id="sun" name="SUN"  indexId="index" type="com.fuguo.dto.SunDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<TD Width="30" align="center"  class="listcellrow">
					<input type="checkbox" id="<bean:write name="sun" property="jhzt" />"  name="ids" value="<bean:write name="sun" property="id" />"/>
					<input type="hidden"  name="allIds" value="<bean:write name="sun" property="id" />"/>
					</TD>
					<td Width="24" align="center" class="listcellrow"  onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red">${pageScope.index+1}</font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt">${pageScope.index+1}</logic:equal>
					</td>
					
					
				<td Width="40" align="center" class="listcelltitle" onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red"><bean:write name="sun" property="fxr"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt"><bean:write name="sun" property="fxr"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcellrow" onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red"><bean:write name="sun" property="fssjStr"   filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt"><bean:write name="sun" property="fssjStr"   filter="false"/></logic:equal>
					
					</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red"><bean:write name="sun" property="clsjStr"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt"><bean:write name="sun" property="clsjStr"  filter="false"/></logic:equal>
					
				</td>
				
				<td Width="100" align="center" class="listcelltitle" onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red"><bean:write name="sun" property="clgc"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt"><bean:write name="sun" property="clgc"  filter="false"/></logic:equal>
					
				</td>
				
				
				<td Width="50" align="center" class="listcelltitle" onclick="showOne(<bean:write name="sun" property="id" />);">
					<logic:equal value="未处理" name="sun" property="jhzt"><font color="red">&nbsp;<bean:write name="sun" property="jhzt" /></font></logic:equal>
					<logic:equal value="已处理" name="sun" property="jhzt">&nbsp;<bean:write name="sun" property="jhzt" /></logic:equal>
					
				</td>
				
					
				</tr>

			</logic:iterate>
				
		</TABLE>
		</form>
  </body>
</html>
