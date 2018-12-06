<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html>
	<head>
		<title></title>
	</head>
	<body>
	<br>
		<table>
			<tr style="background-color:powderblue; font-weight:bold;">
				<td>
					&nbsp;&nbsp;&nbsp;共
					<bean:write name="pageRoll" property="totalCount" />
					条记录&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="totalCount" value="<bean:write name="pageRoll" property="totalCount" />">
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;共
					<bean:write name="pageRoll" property="pageCount" />
					页&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="pageCount" value="<bean:write name="pageRoll" property="pageCount"/>">
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;第
					<bean:write name="pageRoll" property="currentPage" />
					页&nbsp;&nbsp;&nbsp;
					<input type="hidden" name="currentPage" value="<bean:write name="pageRoll" property="currentPage"/>">
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;<a href="javascript:firstPage();">首&nbsp;&nbsp;页</a>&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;<a href="javascript:previousPage();">上一页</a>&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;<a href="javascript:nextPage();">下一页</a>&nbsp;&nbsp;&nbsp;
				</td>
				<td>
					&nbsp;&nbsp;&nbsp;<a href="javascript:lastPage();">末&nbsp;&nbsp;页</a>&nbsp;&nbsp;&nbsp;
				</td>
			</tr>
		</table>
	</body>
</html>
<script type="text/javascript">
<!--
function firstPage(){
	var currentPage = eval("document.all.currentPage.value");
	if(currentPage==1||currentPage==0){
		alert("已经是第一页了！");
		return ;
	}
	document.all.currentPage.value=1;
	//alert(document.all.currentPage.value);
	document.all.rollForm.submit();
}
function nextPage(){
	var currentPage = eval("document.all.currentPage.value");
	var pageCount = eval("document.all.pageCount.value");
	if(currentPage==pageCount){
		alert("已经是最后一页了！");
		return;
	}
	currentPage++;
	document.all.currentPage.value = currentPage;
	//alert(document.all.currentPage.value);//返回的是2 正确
	document.getElementById("rollForm").submit();
}
function previousPage(){
	var currentPage = eval("document.all.currentPage.value");
	if(currentPage==1||currentPage==0){
		alert("已经是第一页了！");
		return;
	}
	currentPage--;
	document.all.currentPage.value = currentPage;
	document.all.rollForm.submit();
}
function lastPage(){
	var currentPage = eval("document.all.currentPage.value");
	var pageCount = eval("document.all.pageCount.value");
	if(currentPage==pageCount){
		alert("已经是最后一页了！");
		return;
	}
	document.all.currentPage.value = pageCount;
	document.all.rollForm.submit();
}
//-->
</script>
