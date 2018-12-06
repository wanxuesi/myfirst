
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
    
    <title>交易记录查询结果</title>
    
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
			
				targetForm.action ="<%=path%>/jiluShowOne.do?id="+id; 
			
			 targetForm.submit();
		
		
		}
		
		
		//设定为已处理方法
		function setYibaosong(){
			document.getElementById("updateMoreForm").action="jiluUpdateMoreToYi.do";
			document.getElementById("updateMoreForm").submit();
		}
		
		function deleteMore(){
		
			if(confirm("是否确定删除选中记录？\n只有'未处理'记录才能删除！'已处理'记录会忽略。")){
				document.getElementById("updateMoreForm").action="jiluDeleteMore.do";	
				document.getElementById("updateMoreForm").submit();
			}
		}
		
	</SCRIPT>
  </head>
  
  <body  onload='dwr.util.useLoadingMessage();getOneGpyl();'>
   
		 <%@ include file="/view/message.jsp"%>
   
    <form id="queryDateSqlForm" name="queryDateSqlForm"  action="" target="_blank" method="post">
  		<input type="hidden" id="isSend" name="isSend" value="0">
  		<input type="hidden" id="queryDateSql" name="queryDateSql" value="<bean:write name="queryDateSql" scope="session"/>">
  							
  	</form>
    <FORM id="targetForm" name="targetForm" target="_blank" method="post">
		</FORM>
		
		<form method="post" id="updateMoreForm" action="" target="_blank">
		<CENTER>交易记录查询结果</CENTER>
    <TABLE Width="95%"  align="center">
  
    		<TR>
					<TD colspan="4" align="center">
					<input type="button" name="刷新" value="&nbsp;&nbsp;&nbsp;刷&nbsp;&nbsp;新&nbsp;&nbsp;&nbsp;" class="button" onclick="javascript:location.href='<%=path%>/jiluQuery.do';">
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
				
				<td Width="60" align="center" class="listcelltitle">
					证券名称
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					交易时间
				</td>
				<td Width="60" align="center" class="listcelltitle">
					买卖标志
				</td>
				<td Width="60" align="center" class="listcelltitle">
					成交价格
				</td>
				<td Width="60" align="center" class="listcelltitle">
					成交数量
				</td>
				
				<td Width="60" align="center" class="listcelltitle">
					成交金额
				</td>
				<td Width="60" align="center" class="listcelltitle">
					清算金额
				</td>
				<td Width="*" align="center" class="listcelltitle">
					交易分类
				</td>
				
				<td  Width="50" align="center" class="listcelltitle">
					状态
				</td>
			</tr>
			<logic:iterate id="jilu" name="JILU"  indexId="index" type="com.fuguo.dto.JiluDTO" scope="request">
				<tr   onMouseOver="javascript:this.bgColor='#FFD39B'" onMouseOut="javascript:this.bgColor=''">
					<TD Width="30" align="center"  class="listcellrow">
					<input type="checkbox" id="<bean:write name="jilu" property="flag1" />"  name="ids" value="<bean:write name="jilu" property="id" />"/>
					<input type="hidden"  name="allIds" value="<bean:write name="jilu" property="id" />"/>
					</TD>
					<td Width="24" align="center" class="listcellrow"  onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red">${pageScope.index+1}</font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1">${pageScope.index+1}</logic:equal>
					</td>
					
					
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="zqmc"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="zqmc"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcellrow" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="jysjStr"   filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="jysjStr"   filter="false"/></logic:equal>
					
					</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="mmflag"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="mmflag"  filter="false"/></logic:equal>
					
				</td>
				
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="cjjg" format=".00"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="cjjg" format=".00"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="cjsl"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="cjsl"  filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="cjje" format=".00" filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="cjje"  format=".00" filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="qsje" format=".00" filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="qsje"  format=".00" filter="false"/></logic:equal>
					
				</td>
				<td Width="60" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red"><bean:write name="jilu" property="jifl"  filter="false"/></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1"><bean:write name="jilu" property="jifl"  filter="false"/></logic:equal>
					
				</td>
				
				<td Width="50" align="center" class="listcelltitle" onclick="showOne(<bean:write name="jilu" property="id" />);">
					<logic:equal value="未处理" name="jilu" property="flag1"><font color="red">&nbsp;<bean:write name="jilu" property="flag1" /></font></logic:equal>
					<logic:equal value="已处理" name="jilu" property="flag1">&nbsp;<bean:write name="jilu" property="flag1" /></logic:equal>
					
				</td>
				
					
				</tr>

			</logic:iterate>
				
		</TABLE>
		</form>
  </body>
</html>
